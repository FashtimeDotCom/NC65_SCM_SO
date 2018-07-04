package nc.impl.so.m4331.action.maintain.rule.send;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * @description
 * ���۷������ύǰ����VO״̬
 * @scene
 * ���۷������ύǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-7-13 ����07:18:56
 * @author ô��
 */
public class SetCommitStatusRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
    }
  }

}
