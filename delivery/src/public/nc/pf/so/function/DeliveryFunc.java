package nc.pf.so.function;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 
 * <p>
 * ��������麯��
 * <li>���ι����������α���
 * 
 * @author ף����
 * @time 2010-10-22 ����10:42:38
 */
public class DeliveryFunc {
  /**
   * 
   * ����������������������Ƿ������ι���
   * 
   * @author ף����
   * @time 2010-10-22 ����10:43:20
   */
  public UFBoolean examBatchInv(AggregatedValueObject aggVO) {
    DeliveryVO newvo = this.getFullBill(aggVO);
    return new FlowDeliveryFuncImpl().examBatchInv(newvo);
  }

  private DeliveryVO getClientInfoFullBill(DeliveryVO bill) {
    DeliveryVO[] bills = new DeliveryVO[] {
      bill
    };
    BillTransferTool<DeliveryVO> transferTool =
        new BillTransferTool<DeliveryVO>(bills);
    return transferTool.getClientFullInfoBill()[0];
  }

  private DeliveryVO getFullBill(AggregatedValueObject vo) {
    // ����
    DeliveryVO bill = (DeliveryVO) vo;
    // �޸�
    String id = bill.getParentVO().getCdeliveryid();
    if ((id != null) && (id.trim().length() > 0)) {
      bill = this.getClientInfoFullBill(bill);
    }
    return bill;
  }
}
