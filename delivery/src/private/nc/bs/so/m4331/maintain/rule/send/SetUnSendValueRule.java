package nc.bs.so.m4331.maintain.rule.send;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * ���۷������ջز���ǰ�ջ�ʱ��������ֶ�ֵ
 * @scene
 * ���۷������ջز���ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-2-22 ����11:01:45
 * @author ô��
 */
public class SetUnSendValueRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
      vo.getParentVO().setFstatusflag((Integer) BillStatus.FREE.value());
    }
  }

}
