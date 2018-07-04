package nc.bs.so.m4331.assist.state;

import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ������״̬�жϹ�����
 * 
 * @author ף����
 * @time 2010-04-08 ����10:37:16
 */
public class BillStateUtil {
  public boolean canBeExecuteState(DeliveryViewVO view) {
    boolean flag = this.canBeExecuteState(view.getHead());
    return flag;
  }

  public boolean canBeExecuteState(DeliveryVO bill) {
    boolean flag = this.canBeExecuteState(bill.getParentVO());
    return flag;
  }

  private boolean canBeExecuteState(DeliveryHVO head) {
    // ֻ���������ģ�����ģ��رյĵ��ݲſ��Դ���ִ��״̬
    Integer status = head.getFstatusflag();
    boolean flag =
        BillStatus.AUDIT.equalsValue(status)
            || BillStatus.CLOSED.equalsValue(status)
            || BillStatus.FREEZE.equalsValue(status);
    return flag;
  }
}
