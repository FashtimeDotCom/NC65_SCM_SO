package nc.bs.so.m30.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * @since 6.0
 * @version 2011-3-10 ����02:28:06
 * @author ô��
 */
public class SaleOrderSendApproveBP {

  public SaleOrderVO[] sendApprove(SaleOrderVO[] clientBills,
      SaleOrderVO[] originBills) {
    // ��VO�־û������ݿ���
    BillUpdate<SaleOrderVO> update = new BillUpdate<SaleOrderVO>();
    SaleOrderVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }
}
