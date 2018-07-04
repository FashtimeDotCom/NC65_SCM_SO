package nc.bs.so.m30.rule.maintainprocess;

import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForCashSale;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * ���۶��������󱣴��տ������ϵ
 * @scene
 * ���۶�������������տ������ϵʱ������տ������ϵ
 * @param 
 * ��
 */
public class InsertSoBalanceWhenAddNewRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    @SuppressWarnings("unchecked")
    List<SoBalanceVO> sobalancevos =
    (List<SoBalanceVO>) BSContext.getInstance().getSession(
        "cashsale.sobalancevos");
    if (sobalancevos == null || sobalancevos.size() == 0) {
      return;
    }
    if (sobalancevos.size() != vos.length) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0112")/*@res "�����Ƿ�������ǰ̨����Ĳ��������Ƿ��붩���ĵ�����һ�¡�"*/);
    }
    int i = 0;
    for (SoBalanceVO balancevo : sobalancevos) {
      if (balancevo != null && balancevo.getChildrenVO() != null
          && balancevo.getChildrenVO().length > 0) {
        // ��Ҫ���䶩����ID
        SaleOrderVO saleordervo = vos[i++];
        SaleOrderHVO soheadvo = saleordervo.getParentVO();
        SoBalanceHVO headvo = balancevo.getParentVO();
        headvo.setCsaleorderid(soheadvo.getPrimaryKey());
        headvo.setVbillcode(soheadvo.getVbillcode());
        headvo.setNtotalorigtaxmny(soheadvo.getNtotalorigmny());

        SobalanceBPFactoryForCashSale.getInstance().getInsertBP()
        .insert(new SoBalanceVO[] {
            balancevo
        });
      }
    }
  }

}