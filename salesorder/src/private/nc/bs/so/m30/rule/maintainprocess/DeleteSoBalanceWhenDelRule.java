package nc.bs.so.m30.rule.maintainprocess;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForSoAuto;
import nc.itf.so.m30.sobalance.ISOBalanceQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * ���۶���ɾ����ɾ��ԭ�����տ������ϵ
 * @scene
 * ���۶���ɾ��������տ������ϵʱɾ�����տ������ϵ
 * @param 
 * ��
 */
public class DeleteSoBalanceWhenDelRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    List<SoBalanceVO> sobalancevos = new ArrayList<SoBalanceVO>();
    for (SaleOrderVO saleordervo : vos) {
      SoBalanceVO[] oldbalancevos = null;
      ISOBalanceQuery queryservice =
          NCLocator.getInstance().lookup(ISOBalanceQuery.class);
      try {
        oldbalancevos =
            queryservice.querySoBalanceVOBySaleOrderIDs(new String[] {
              saleordervo.getParentVO().getPrimaryKey()
            });
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (oldbalancevos != null && oldbalancevos.length > 0) {
        sobalancevos.add(oldbalancevos[0]);
      }
    }
    if (sobalancevos.size() > 0) {
      SobalanceBPFactoryForSoAuto.getInstance().getDeleteBP()
          .delete(sobalancevos.toArray(new SoBalanceVO[0]));
    }
  }

}
