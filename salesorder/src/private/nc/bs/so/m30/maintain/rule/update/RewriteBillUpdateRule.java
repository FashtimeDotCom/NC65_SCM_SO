package nc.bs.so.m30.maintain.rule.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m30.maintain.util.RewriteBillUtil;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.pubitf.ct.saledaily.so.IReWriteZ3For30;
import nc.vo.ct.entity.CtWriteBackForOrderVO;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.ct.saledaily.entity.CtSaleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۶����޸ĺ���»�д��Դ��Դͷ����д���۵�����ͬ��Ԥ�������������ۡ����۶��������ⵥ���������
 * @scene
 * ���۶����޸ı����
 * @param 
 * ��
 * @since 6.0
 * @version 2012-4-21 ����11:05:18
 * @author ô��
 */
public class RewriteBillUpdateRule implements ICompareRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
    /****** ��д��Դ���� ***********/
    RewriteBillUtil rewriteUtil = new RewriteBillUtil();
    BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
    Map<String, List<RewritePara>> srcParaIndex =
        srctool.splitForUpdate(vos, originVOs);

    // ��д����
    List<RewritePara> srcSaleOrder =
        srcParaIndex.get(SOBillType.Order.getCode());
    if (!VOChecker.isEmpty(srcSaleOrder)) {
      rewriteUtil.reWriteSrc30(srcSaleOrder);
    }
    // ���ⵥ
    List<RewritePara> srcpara = srcParaIndex.get(ICBillType.SaleOut.getCode());
    if (!VOChecker.isEmpty(srcpara)) {
      rewriteUtil.reWriteSrc4C(srcpara);
    }
    // Ԥ����
    srcpara = srcParaIndex.get(SOBillType.PreOrder.getCode());
    if (!VOChecker.isEmpty(srcpara)) {
      rewriteUtil.reWriteSrc38(srcpara);
    }
    // ���۵�
    srcpara = srcParaIndex.get(SOBillType.SaleQuotation.getCode());
    if (!VOChecker.isEmpty(srcpara)) {
      rewriteUtil.reWriteSrc4310(srcpara, Integer.valueOf(VOStatus.UPDATED));
    }
    // ���Ӷ���
    srcpara = srcParaIndex.get(OPCBillType.OPCORDER.getCode());
    if (!VOChecker.isEmpty(srcpara)) {
      rewriteUtil.reWriteSrcOPC(srcpara);
    }
    // �������
    srcpara = srcParaIndex.get(ICBillType.BorrowOut.getCode());
    if (!VOChecker.isEmpty(srcpara)) {
      rewriteUtil.reWriteSrc4H(srcpara);
    }
    // ���ۺ�ͬ
    this.rewriteZ3(vos, originVOs);
    /****** ��д��Դ���� ***********/

    /** ------------��дԴͷ����---------- */
    BillRewriter firsttool = rewriteUtil.getFirstBillRewriter();
    Map<String, List<RewritePara>> firstParaIndex =
        firsttool.splitForUpdate(vos, originVOs);
    List<RewritePara> firstSaleOrder =
        firstParaIndex.get(SOBillType.Order.getCode());
    // ���˲���
    firstSaleOrder = rewriteUtil.filtrateSrc30(firstSaleOrder, srcSaleOrder);
    if (!VOChecker.isEmpty(firstSaleOrder)) {
      rewriteUtil.reWriteFirst30(firstSaleOrder);
    }
    /** ------------��дԴͷ����---------- */
  }

  private void rewriteZ3(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
    SaleOrderVO[] newOriginVOs = this.fillCtType(originVOs);
    SaleOrderVO[] newVOs = this.fillCtType(vos);
    if (newVOs.length == 0 && newOriginVOs.length == 0) {
      return;
    }
    // �������ε������͡�ID��BID�������ֶ�
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(SaleOrderBVO.VCTTYPE);
    mapping.setCsrcidKey(SaleOrderBVO.CCTMANAGEID);
    mapping.setCsrcbidKey(SaleOrderBVO.CCTMANAGEBID);
    mapping.setNnumKey(SaleOrderBVO.NNUM);
    mapping.setNnum2Key(SaleOrderBVO.NORIGTAXMNY);
    mapping.setSrcTSKey(SaleOrderBVO.SRCTS);
    // ���۶���û�����ӱ�,�������º�ͬ��Ҫ���к�
    mapping.setCsrcbbidKey(SaleOrderBVO.CROWNO);
    // ������ε�������
    BillRewriter tool = new BillRewriter(mapping);
    // �������ε���VO����
    tool.addSRCHeadClazz(CTBillType.SaleDaily.getCode(), CtSaleVO.class);
    tool.addSRCItemClazz(CTBillType.SaleDaily.getCode(), CtSaleBVO.class);

    Map<String, UFDouble> bodymap = new HashMap<String, UFDouble>();
    // ȡ�µ���ԭ�Һ�˰����
    this.buildSaleOrderBVOData(bodymap, newVOs);
    this.buildSaleOrderBVOData(bodymap, newOriginVOs);
    SaleOrderVO[] destVOS = this.buildSplitVO(vos, originVOs);
    // �ֵ�
    Map<String, List<RewritePara>> paraIndex =
        tool.splitForUpdate(destVOS, originVOs);

    // ��д
    List<RewritePara> paraList = paraIndex.get(CTBillType.SaleDaily.getCode());
    if (null != paraList && paraList.size() > 0) {
      int size = paraList.size();
      CtWriteBackForOrderVO[] paras = new CtWriteBackForOrderVO[size];
      for (int i = 0; i < size; i++) {
        String id = paraList.get(i).getCsrcid();
        String bid = paraList.get(i).getCsrcbid();
        UFDouble nnum = paraList.get(i).getNnum();
        UFDouble norigtaxmny = paraList.get(i).getNnum2();
        String crowno = paraList.get(i).getCsrcbbid();
        paras[i] = new CtWriteBackForOrderVO();
        paras[i].setPk_ctpu(id);
        paras[i].setPk_ctpu_b(bid);
        paras[i].setNum(nnum);
        paras[i].setPriceNum(norigtaxmny);
        paras[i].setcRowNum(crowno);
        if (bodymap.containsKey(paraList.get(i).getCbill_bid())) {
          // ����λԭ�Һ�˰����
          UFDouble price = bodymap.get(paraList.get(i).getCbill_bid());
          paras[i].setPrice(price);
        }
      }
      IReWriteZ3For30 api =
          NCLocator.getInstance().lookup(IReWriteZ3For30.class);
      try {
        api.rewriteBackZ3For30(paras);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
  }

  /**
   * jilu add for 633 ɽ����ú_���۶����޸����ϻ�д���ۺ�ͬ,�ϲ�����,���۶�����Դ���ۺ�ͬ���޸����ϲ���д���ۺ�ͬ;
   * ��ͨ������,�жϵ�ǰҪ��д��vo�Ƿ�����Դ��ͬ��,�����,���clone������VO��pk���,�����ͻ��д���κ�ͬ����.
   * 
   * @param vos
   * @return
   */
  private SaleOrderVO[] buildSplitVO(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
    Map<String, SaleOrderBVO> voIndex = new HashMap<String, SaleOrderBVO>();
    for (SaleOrderVO originVO : originVOs) {
      for (SaleOrderBVO bvo : originVO.getChildrenVO()) {
        voIndex.put(bvo.getPrimaryKey(), bvo);
      }
    }
    List<SaleOrderVO> destVOList = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO vo : vos) {
      SaleOrderVO destvo = new SaleOrderVO();
      List<SaleOrderBVO> bvoList = new ArrayList<SaleOrderBVO>();
      destvo.setParentVO(vo.getParentVO());
      for (SaleOrderBVO bvo : vo.getChildrenVO()) {
        SaleOrderBVO destbvo = (SaleOrderBVO) bvo.clone();
        if (voIndex.containsKey(destbvo.getPrimaryKey())) {
          String originsrctype =
              voIndex.get(destbvo.getPrimaryKey()).getVcttype();
          String originctcode =
              voIndex.get(destbvo.getPrimaryKey()).getVctcode();
          if (null != originctcode
              && CTBillType.SaleDaily.getCode().equals(originsrctype)) {
            String originctbid =
                voIndex.get(destbvo.getPrimaryKey()).getCctmanagebid();
            if (!originctbid.equals(destbvo.getCctmanagebid())) {
              destbvo.setPrimaryKey(null);
            }
          }
          if (null == originctcode && null != destbvo.getVctcode()) {
            destbvo.setPrimaryKey(null);
          }
        }
        bvoList.add(destbvo);
      }
      destvo.setChildrenVO(bvoList.toArray(new SaleOrderBVO[bvoList.size()]));
      destVOList.add(destvo);
    }
    return destVOList.toArray(new SaleOrderVO[destVOList.size()]);
  }

  private SaleOrderVO[] fillCtType(SaleOrderVO[] vos) {
    List<SaleOrderVO> z3vos = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bodys = vo.getChildrenVO();
      List<SaleOrderBVO> z3bvos = new ArrayList<SaleOrderBVO>();
      for (SaleOrderBVO body : bodys) {
        if (body.getCctmanagebid() != null
            && body.getCctmanagebid().trim().length() > 0) {
          body.setVcttype(CTBillType.SaleDaily.getCode());
          z3bvos.add(body);
        }

      }
      if (z3bvos.size() > 0) {
        SaleOrderVO newvo = new SaleOrderVO();
        newvo.setParentVO(vo.getParentVO());
        newvo.setChildrenVO(z3bvos.toArray(new SaleOrderBVO[z3bvos.size()]));
        z3vos.add(newvo);
      }
    }
    return z3vos.toArray(new SaleOrderVO[z3vos.size()]);
  }

  private void buildSaleOrderBVOData(Map<String, UFDouble> bodymap,
      SaleOrderVO[] billvos) {
    for (SaleOrderVO billvo : billvos) {
      SaleOrderBVO[] childvos = billvo.getChildrenVO();
      for (SaleOrderBVO bodyvo : childvos) {
        if (!bodymap.containsKey(bodyvo.getPrimaryKey())) {
          bodymap.put(bodyvo.getPrimaryKey(), bodyvo.getNorigtaxprice());
        }
      }
    }
  }

}
