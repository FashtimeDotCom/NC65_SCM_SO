package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.revise.rule.CheckExistWorkflowRule;
import nc.bs.so.m30.rule.approve.CheckMaxIversionRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m30.action.main.CommitSaleOrderAction;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 *  ���۶����޶�����
 *
 * @since 6.36
 * @version 2014-12-26 ����2:49:00
 * @author wangshu6
 */
public class N_30R_SAVE extends AbstractPfAction<SaleOrderVO> {
  /**
   * ���췽��
   */
  public N_30R_SAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<SaleOrderVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<SaleOrderVO> processor =
        new CompareAroundProcesser<SaleOrderVO>(BP30PlugInPoint.ReviseSendBP);
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
    // У�鵥��״̬�Ƿ�����ύ
    IRule rule = new CheckExistWorkflowRule();
    processor.addBeforeRule(rule);
    // У���ύ�汾�Ƿ����޶��������°汾
    rule = new CheckMaxIversionRule();
    processor.addBeforeRule(rule);
  }
}
