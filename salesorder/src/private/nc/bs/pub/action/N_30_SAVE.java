package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.rule.approve.CheckExistWorkflowRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m30.action.main.CommitSaleOrderAction;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * �������ű�
 * 
 * @author ƽ̨�ű�����
 * @since 6.0
 */
public class N_30_SAVE extends AbstractPfAction<SaleOrderVO> {
  /**
   * ���췽��
   */
  public N_30_SAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<SaleOrderVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<SaleOrderVO> processor =
        new CompareAroundProcesser<SaleOrderVO>(BP30PlugInPoint.SendBP);
    this.addBeforeRule(processor);
    return processor;
  }

  @Override
  protected SaleOrderVO[] processBP(Object userObj,
      SaleOrderVO[] clientFullVOs, SaleOrderVO[] originBills) {
    CommitSaleOrderAction action = new CommitSaleOrderAction();
    return action.sendApprove(clientFullVOs, originBills);
  }

  private void addBeforeRule(CompareAroundProcesser<SaleOrderVO> processor) {
    // У�����۶����Ƿ����������������ύ
    IRule<SaleOrderVO> rule = new CheckExistWorkflowRule();
    processor.addBeforeRule(rule);
  }
}
