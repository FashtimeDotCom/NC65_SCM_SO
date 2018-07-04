package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m4331.maintain.DeliveryUnSendApproveBP;
import nc.bs.so.m4331.maintain.rule.send.CheckUnSendEnableRule;
import nc.bs.so.m4331.maintain.rule.send.SetUnSendValueRule;
import nc.bs.so.m4331.plugin.BP4331PlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * �ջؽű���
 * 
 * @since 6.0
 * @version 2011-2-22 ����10:41:12
 * @author ô��
 */
public class N_4331_UNSAVE extends AbstractPfAction<DeliveryVO> {
  /**
   * N_4331_UNSEND �Ĺ�����
   */
  public N_4331_UNSAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<DeliveryVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<DeliveryVO> processor =
        new CompareAroundProcesser<DeliveryVO>(BP4331PlugInPoint.UnSendBP);
    // TODO �ڴ˴�������ǰ����
    this.addRule(processor);
    // TODO �ڴ˴������˺����
    return processor;
  }

  @Override
  protected DeliveryVO[] processBP(Object userObj, DeliveryVO[] clientFullVOs,
      DeliveryVO[] originBills) {
    DeliveryVO[] bills =
        new DeliveryUnSendApproveBP().unSend(clientFullVOs, originBills);
    return bills;
  }

  private void addRule(CompareAroundProcesser<DeliveryVO> processer) {
    // У���Ƿ������ջ�
    IRule<DeliveryVO> rule = new CheckUnSendEnableRule();
    processer.addBeforeRule(rule);
    // �����ջغ�����ֶ�ֵ
    rule = new SetUnSendValueRule();
    processer.addBeforeRule(rule);

  }
}
