package nc.bs.so.m30.maintain.rule.insert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.PromoteLimitUtil;
import nc.vo.so.m30.util.RewriteProPriceUtil;

/**
 * 
 * @description
 * ���۶���������������
 * @scene
 * ���۶������������д���������۸�ִ����
 * @param
 * ��
 *
 * @since 6.5
 * @version 2014-02-24 15:51:45
 * @author zhangyfr
 */
public class RewritePromotePriceInsertRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    // ��д��������ִ���������б�
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

    if (map.size() > 0) {
      // ���ǵ��������������ζ�������-Ԥ�������������泡������д����
      RewriteProPriceUtil util = new RewriteProPriceUtil();
      paras = util.setSrcParas(map);
    }
    if (paras != null && paras.size() > 0) {
      PromoteLimitUtil.InsertPPLimit(paras
          .toArray(new SOUpdatePPLimitExePara[paras.size()]));
    }

  }
}
