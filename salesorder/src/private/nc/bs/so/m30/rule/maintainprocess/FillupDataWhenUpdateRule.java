package nc.bs.so.m30.rule.maintainprocess;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * UpdateSaleOrderAction������ǰ����޲����ݹ���
 * @scene
 * ���۶����޸ı���ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-8-25 ����08:25:29
 * @author ��־ΰ
 */
public class FillupDataWhenUpdateRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      this.cleanUpApproveInfo(bill);
    }
  }

  /**
   * Ϊ������ͨ�����޸ĵ��ݶ������������Ϣ
   * ���߼����ܷ���UpdateSaleOrderBP�У���Ϊ�޶�����BPʱ����Ҫ���������Ϣ��
   */
  private void cleanUpApproveInfo(SaleOrderVO bill) {
    SaleOrderHVO head = bill.getParentVO();
    head.setApprover(null);
    head.setTaudittime(null);
  }

}
