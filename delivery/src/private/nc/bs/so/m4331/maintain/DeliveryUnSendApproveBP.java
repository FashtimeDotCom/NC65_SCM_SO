package nc.bs.so.m4331.maintain;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 
 * @since 6.0
 * @version 2011-3-10 ����02:28:06
 * @author ô��
 */
public class DeliveryUnSendApproveBP {

  public DeliveryVO[] unSend(DeliveryVO[] clientBills, DeliveryVO[] originBills) {
    // ��VO�־û������ݿ���
    BillUpdate<DeliveryVO> update = new BillUpdate<DeliveryVO>();
    DeliveryVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }
}
