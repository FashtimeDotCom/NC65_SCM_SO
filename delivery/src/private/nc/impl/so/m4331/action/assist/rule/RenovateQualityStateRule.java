package nc.impl.so.m4331.action.assist.rule;

import nc.impl.so.m4331.action.quality.DeliverycheckCloseAction;
import nc.impl.so.m4331.action.quality.DeliverycheckOpenAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m4331.entity.DeliveryVO;

public class RenovateQualityStateRule {

  public void renovateState(DeliveryVO[] bills, UFBoolean isclose) {
    if (null == bills) {
      return;
    }
    if (!isclose.booleanValue()) {
      // �򿪷�����ʱ���򿪷�������Ӧ���ʼ���Ϣ
      DeliverycheckOpenAction open = new DeliverycheckOpenAction();
      open.openQualityInfo(bills);
      return;
    }
    // �رշ��������رշ�������Ӧ���ʼ���Ϣ
    DeliverycheckCloseAction close = new DeliverycheckCloseAction();
    close.closeQualityInfo(bills);
  }
}
