package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m4331.action.maintain.CommitDeliveryAction;
import nc.impl.so.m4331.action.maintain.rule.send.CheckExistWorkflowRule;
import nc.impl.so.m4331.action.maintain.rule.send.SetCommitStatusRule;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * ����ű�����
 * 
 * @since 6.0
 * @version 2011-7-13 ����07:27:33
 * @author ô��
 */
public class N_4331_SAVE extends AbstractPfAction<DeliveryVO> {
  /**
   * ���췽��
   */
  public N_4331_SAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<DeliveryVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<DeliveryVO> processor =
        new CompareAroundProcesser<DeliveryVO>(
            Action4331PlugInPoint.SendApproveAction);
    // TODO �ڴ˴�������ǰ����
    this.addBeforeRule(processor);
    // TODO �ڴ˴������˺����
    return processor;
  }

  @Override
  protected DeliveryVO[] processBP(Object userObj, DeliveryVO[] clientFullVOs,
      DeliveryVO[] originBills) {
    CommitDeliveryAction action = new CommitDeliveryAction();
    return action.sendApprove(clientFullVOs, originBills);
  }

  private void addBeforeRule(CompareAroundProcesser<DeliveryVO> processor) {
    // У�����۶����Ƿ����������������ύ
    IRule<DeliveryVO> rule = new CheckExistWorkflowRule();
    processor.addBeforeRule(rule);

    rule = new SetCommitStatusRule();
    processor.addBeforeRule(rule);

  }
}
