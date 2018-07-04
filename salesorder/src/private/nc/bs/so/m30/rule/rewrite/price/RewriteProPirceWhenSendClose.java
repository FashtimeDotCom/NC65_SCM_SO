package nc.bs.so.m30.rule.rewrite.price;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.util.PromoteLimitUtil;
import nc.vo.so.m30.util.RewriteProPriceUtil;
import nc.vo.so.m30.util.SaleOrderNumScaleUtil;

/**
 * 
 * @description
 * �����رջ�д���������۸�ִ����
 * @scene
 * �����رջ�д���������۸�ִ����
 * @param
 * ��
 * @since 6.5
 * @version 2014-02-26 10:35:33
 * @author zhangyfr
 */
public class RewriteProPirceWhenSendClose implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {

    List<SOUpdatePPLimitExePara> paras =
        new ArrayList<SOUpdatePPLimitExePara>();
    // �����ݴ�<��Դ������ID����д����>������B2BԤ������ѯ�ӿڷ���ֵ����д������ֵ
    Map<String, SOUpdatePPLimitExePara> map =
        new HashMap<String, SOUpdatePPLimitExePara>();
    for (SaleOrderViewVO vo : vos) {
      SaleOrderBVO bvo = vo.getBody();

      int scale = SaleOrderNumScaleUtil.getNumPower(bvo);
      // �ۼƷ�������Ϊ��ֱ�ӷ���
      UFDouble ntotalsendnum = bvo.getNtotalsendnum();
      UFBoolean Bboutendflag = bvo.getBboutendflag();
      if (Bboutendflag.booleanValue() || null == ntotalsendnum) {
        return;
      }
      // �ۼƷ���������Ϊ�գ�ռ���ۼƷ�������
      SOUpdatePPLimitExePara para = new SOUpdatePPLimitExePara();
      para.setBilltypecode(SOBillType.Order.getCode());
      para.setCcustomerid(vo.getHead().getCcustomerid());
      para.setCpromoetpriceid(bvo.getCpromotpriceid());
      para.setRowID(bvo.getCsaleorderbid());
      para.setRowNo(bvo.getCrowno());
      String qtunitrate = bvo.getVqtunitrate();
      UFDouble nqtunittotalsendnum =
          HslParseUtil.hslDivUFDouble(qtunitrate, ntotalsendnum);
      nqtunittotalsendnum =
          nqtunittotalsendnum.setScale(scale, UFDouble.ROUND_HALF_UP);
      para.setNnum(nqtunittotalsendnum);
      // ��Դ��������Ϊ���Ӷ���'ECC1'
      if (OPCBillType.OPCORDER.getCode().equals(bvo.getVsrctype())) {
        para.setSrcbilltypecode(bvo.getVsrctype());
        String srcbic = bvo.getCsrcbid();
        map.put(srcbic, para);
      }
      else {
        paras.add(para);
      }

    }

    if (map.size() > 0) {
      RewriteProPriceUtil util = new RewriteProPriceUtil();
      paras = util.setSrcParas(map);

    }
    PromoteLimitUtil.updateExecutedNumByOpenOrClose(paras
        .toArray(new SOUpdatePPLimitExePara[paras.size()]));

  }
}
