package nc.bs.so.m38.state;

import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * Ԥ����״̬�жϹ�����
 * @author ��־ΰ
 *
 * @time 2010-04-08 ����10:37:16
 */
public class BillStateUtil {

  private boolean canBeExecuteState(PreOrderHVO head) {
    // ֻ���������ģ�����ģ��رյĵ��ݲſ��Դ���ִ��״̬
    Integer status = head.getFstatusflag();
    boolean flag =
        BillStatus.AUDIT.equalsValue(status)
            || BillStatus.CLOSED.equalsValue(status)
            || BillStatus.FREEZE.equalsValue(status);
    return flag;
  }

  public boolean canBeExecuteState(PreOrderViewVO view) {
    boolean flag = this.canBeExecuteState(view.getHead());
    return flag;
  }

  public boolean canBeExecuteState(PreOrderVO bill) {
    boolean flag = this.canBeExecuteState(bill.getParentVO());
    return flag;
  }
}
