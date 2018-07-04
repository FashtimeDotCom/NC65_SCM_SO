package nc.impl.so.m30.revise.action.rule;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * ReviseSaveSaleOrderAction:�޶�����ǰ������ݹ���
 * @scene
 * ���۶����޶�����ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-8-26 ����09:38:33
 * @author ��־ΰ
 */
public class FillupDataRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      // ����޶���Ϣ
      this.fillUpReviseInfo(bill);
    }
  }

  private void fillUpReviseInfo(SaleOrderVO bill) {
    SaleOrderHVO head = bill.getParentVO();
    InvocationInfoProxy proxy = InvocationInfoProxy.getInstance();
    String userId = proxy.getUserId();
    head.setCreviserid(userId);
    head.setTrevisetime(TimeUtils.getsrvBaseDate());
  }

}
