package nc.impl.so.m4331.action.maintain;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * ������
 * 
 * @author gdsjw
 */
public class CommitDeliveryAction {

  public DeliveryVO[] sendApprove(DeliveryVO[] clientBills,
      DeliveryVO[] originBills) {
    // ��VO�־û������ݿ���
    BillUpdate<DeliveryVO> update = new BillUpdate<DeliveryVO>();
    DeliveryVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }

}
