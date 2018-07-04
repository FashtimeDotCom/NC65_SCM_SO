package nc.bs.so.m30.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * 
 * @since 6.0
 * @version 2011-3-10 ����02:28:06
 * @author ô��
 */
public class SaleOrderUnSendApproveBP {

  public SaleOrderVO[] unSend(SaleOrderVO[] clientBills,
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
