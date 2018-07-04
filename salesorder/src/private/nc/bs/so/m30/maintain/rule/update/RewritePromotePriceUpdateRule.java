package nc.bs.so.m30.maintain.rule.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.PromoteLimitUtil;
import nc.vo.so.m30.util.RewriteProPriceUtil;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 
 * @description
 * ���۶����޸ı���
 * @scene
 * ���۶����޸ı����д���������۸�ִ����
 * @param
 * ��
 *
 * @since 6.5
 * @version 2014-02-24 16:01:35
 * @author zhangyfr
 */
public class RewritePromotePriceUpdateRule implements ICompareRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
    List<SOUpdatePPLimitExePara> paras =
        new ArrayList<SOUpdatePPLimitExePara>();
    // �����ݴ�<��Դ������ID����д����>������B2BԤ������ѯ�ӿڷ���ֵ����д������ֵ
    Map<String, SOUpdatePPLimitExePara> map =
        new HashMap<String, SOUpdatePPLimitExePara>();

    for (SaleOrderVO sovo : vos) {
      for (SaleOrderBVO bvo : sovo.getChildrenVO()) {
        if (bvo.getCpromotpriceid() == null) {
          continue;
        }
        UFBoolean bsendendflag = bvo.getBbsendendflag();
        UFBoolean boutendflag = bvo.getBboutendflag();
        // ���ǵ��޶��ǵ��ô˹���������������ⶼ���رղŸ�����������ִ����
        if ((!bsendendflag.booleanValue() || null == bvo.getNtotalsendnum())
            && (!boutendflag.booleanValue() || null == bvo.getNtotaloutnum())) {
          SOUpdatePPLimitExePara para = new SOUpdatePPLimitExePara();
          para.setBilltypecode(SOBillType.Order.getCode());
          para.setCcustomerid(sovo.getParentVO().getCcustomerid());
          para.setCpromoetpriceid(bvo.getCpromotpriceid());
          para.setRowID(bvo.getCsaleorderbid());
          para.setRowNo(bvo.getCrowno());
          para.setNnum(bvo.getNqtunitnum());
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
      }
    }

    List<SOUpdatePPLimitExePara> releaseparas =
        new ArrayList<SOUpdatePPLimitExePara>();
    // �����ݴ�<��Դ������ID����д����>������B2BԤ������ѯ�ӿڷ���ֵ����д������ֵ
    Map<String, SOUpdatePPLimitExePara> releasemap =
        new HashMap<String, SOUpdatePPLimitExePara>();

    for (SaleOrderVO sovo : originVOs) {
      for (SaleOrderBVO bvo : sovo.getChildrenVO()) {
        if (bvo.getCpromotpriceid() == null) {
          continue;
        }
        UFBoolean bsendendflag = bvo.getBbsendendflag();
        UFBoolean boutendflag = bvo.getBboutendflag();
        // ���ǵ��޶��ǵ��ô˹���������������ⶼ���رղŸ�����������ִ����
        if ((!bsendendflag.booleanValue() || null == bvo.getNtotalsendnum())
            && (!boutendflag.booleanValue() || null == bvo.getNtotaloutnum())) {
          SOUpdatePPLimitExePara para = new SOUpdatePPLimitExePara();
          para.setBilltypecode(SOBillType.Order.getCode());
          para.setCcustomerid(sovo.getParentVO().getCcustomerid());
          para.setCpromoetpriceid(bvo.getCpromotpriceid());
          para.setRowID(bvo.getCsaleorderbid());
          para.setRowNo(bvo.getCrowno());
          Integer oldbillstatus = sovo.getParentVO().getFstatusflag();
          // ���ǵ�������ͨ�����Ƶ����޸ı�����龰
          if (BillStatus.NOPASS.equalsValue(oldbillstatus)) {
            int scale = bvo.getNqtunitnum().getPower();
            UFDouble zero = new UFDouble(0, scale);
            para.setNnum(zero);
          }
          else {
            para.setNnum(bvo.getNqtunitnum());
          }
          // ��Դ��������Ϊ���Ӷ���'ECC1'
          if (OPCBillType.OPCORDER.getCode().equals(bvo.getVsrctype())) {
            para.setSrcbilltypecode(bvo.getVsrctype());
            String srcbic = bvo.getCsrcbid();
            releasemap.put(srcbic, para);
          }
          else {
            releaseparas.add(para);
          }
        }
      }
    }

    if (map.size() > 0) {
      RewriteProPriceUtil util = new RewriteProPriceUtil();
      paras = util.setSrcParas(map);
    }
    if (releasemap.size() > 0) {
      RewriteProPriceUtil util = new RewriteProPriceUtil();
      releaseparas = util.setSrcParas(map);
    }
    if ((paras != null && paras.size() > 0)
        || (releaseparas != null && releaseparas.size() > 0)) {
      PromoteLimitUtil
          .updatePLimit(releaseparas
              .toArray(new SOUpdatePPLimitExePara[releaseparas.size()]), paras
              .toArray(new SOUpdatePPLimitExePara[paras.size()]));

    }
  }
}
