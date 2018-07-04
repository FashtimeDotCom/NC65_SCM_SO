package nc.vo.so.m30.vochange;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;

public class M30ToM30ChangeVOAdjust extends AbstractSaleOrderChangeVOAdjust {

  @Override
  protected String getSrcBillTypeCode() {
    return SOBillType.Order.getCode();
  }

  @Override
  protected void fillRefAddValue(SaleOrderVO[] vos) {
    super.fillRefAddValue(vos);
    // ��Դ�������������Ƿ�ֱ�˲ɹ�
    Map<String, Boolean> mapPodirtype = this.queryIsDirectPOByTranType(vos);
    for (SaleOrderVO billvo : vos) {

      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(billvo);
      // 1.�����˻�����־
      int bodycount = keyValue.getBodyCount();
      for (int i = 0; i < bodycount; i++) {
        keyValue.setBodyValue(i, SaleOrderBVO.FRETEXCHANGE,
            Fretexchange.WITHDRAW.getIntegerValue());

        // 2.����Դͷ�������������˻�����Ĭ��ֵ
        String srctrantype =
            keyValue.getBodyStringValue(i, SaleOrderBVO.VSRCTRANTYPE);
        // ��������
        UFDouble nnum = keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NNUM);
        // ʵ����������
        UFDouble totaloutnum =
            keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NTOTALOUTNUM);
        // �ۼ��˻�����
        UFDouble totalreturnnum =
            keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NTOTALRETURNNUM);

        UFDouble canretnum = null;
        Boolean ispodir = mapPodirtype.get(srctrantype);
        if (null != ispodir && ispodir.booleanValue() && null == totaloutnum) {
          canretnum = MathTool.oppose(MathTool.add(nnum, totalreturnnum));
        }
        else {
          canretnum =
              MathTool.oppose(MathTool.sub(totaloutnum, totalreturnnum));
        }
        keyValue.setBodyValue(i, SaleOrderBVO.NNUM, canretnum);
        // ����ۼƳ�������������ۼ��˻�����
        keyValue.setBodyValue(i, SaleOrderBVO.NTOTALOUTNUM, null);
        keyValue.setBodyValue(i, SaleOrderBVO.NTOTALRETURNNUM, null);
      }

      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
      int[] rows = bodycouuitl.getMarNotNullRows();
      // 2.ֱ�˲�
      DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
      dirstorerule.setDirectStore(rows);
    }
  }

  @Override
  protected boolean isDownSymbolMinus() {
    return true;
  }

  private Map<String, Boolean> queryIsDirectPOByTranType(SaleOrderVO[] vos) {

    Set<String> setSrcTran = new HashSet<String>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bodys = vo.getChildrenVO();
      for (SaleOrderBVO body : bodys) {
        setSrcTran.add(body.getVsrctrantype());
      }
    }

    String[] srctranids = new String[setSrcTran.size()];
    setSrcTran.toArray(srctranids);

    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    M30TranTypeVO[] tranTypeVOs = null;
    try {
      tranTypeVOs = service.queryTranTypeVOs(srctranids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    Map<String, Boolean> retMap = new HashMap<String, Boolean>();
    if (null == tranTypeVOs || tranTypeVOs.length == 0) {
      return retMap;
    }

    for (M30TranTypeVO tranTypeVO : tranTypeVOs) {
      Integer fdirecttype = tranTypeVO.getFdirecttype();
      if (DirectType.DIRECTTRAN_PO.equalsValue(fdirecttype)) {
        retMap.put(tranTypeVO.getCtrantypeid(), Boolean.TRUE);
      }
      else {
        retMap.put(tranTypeVO.getCtrantypeid(), Boolean.FALSE);
      }
    }
    return retMap;
  }
}
