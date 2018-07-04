package nc.impl.so.m30.action.main;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * ������
 * 
 * @author gdsjw
 */
public class CommitSaleOrderAction {

  public SaleOrderVO[] sendApprove(SaleOrderVO[] clientBills,
      SaleOrderVO[] originBills) {
    // ������״̬ת��Ϊ����״̬
    for (SaleOrderVO newvo : clientBills) {
      SOPfStatusChgRule statuschgrule = new SOPfStatusChgRule();
      statuschgrule.changePfToBillStatus(newvo);

    }
    // ��VO�־û������ݿ���
    BillUpdate<SaleOrderVO> update = new BillUpdate<SaleOrderVO>();
    SaleOrderVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }

}
