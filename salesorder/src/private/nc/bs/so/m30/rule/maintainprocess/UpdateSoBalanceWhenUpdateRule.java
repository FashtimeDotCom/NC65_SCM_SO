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
 * ���۶����޸ı�����޸��տ������ϵ
 * @scene
 * ���۶����޸ĺ�����տ������ϵʱ�޸ĸ��տ������ϵ
 * @param 
 * ��
 */
public class UpdateSoBalanceWhenUpdateRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    @SuppressWarnings("unchecked")
    List<SoBalanceVO> sobalancevos =
        (List<SoBalanceVO>) BSContext.getInstance().getSession(
            "cashsale.sobalancevos");
    if (sobalancevos == null || sobalancevos.size() == 0
        || sobalancevos.get(0) == null) {
      return;
    }
    if (sobalancevos.size() != vos.length) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0112")/*@res "�����Ƿ�������ǰ̨����Ĳ��������Ƿ��붩���ĵ�����һ�¡�"*/);
    }
    // ���������ͷ���ó�ֻ��߶����տ������Ϊ��ʱ��������������������:ǰ̨���ƣ��ܵ�����Ķ��������տ������ϵ
    for (int i = 0; i < vos.length; i++) {
      SaleOrderVO saleordervo = vos[i];
      SoBalanceVO newbalancevo = sobalancevos.get(i);
      if (newbalancevo != null && newbalancevo.getChildrenVO() != null
          && newbalancevo.getChildrenVO().length > 0) {
        SaleOrderHVO soheadvo = saleordervo.getParentVO();
        SoBalanceHVO headvo = newbalancevo.getParentVO();
        headvo.setVbillcode(soheadvo.getVbillcode());
        headvo.setNtotalorigtaxmny(soheadvo.getNtotalorigmny());
        SobalanceBPFactoryForCashSale.getInstance().getInsertBP()
            .insert(new SoBalanceVO[] {
              newbalancevo
            });
      }

    }

  }

}