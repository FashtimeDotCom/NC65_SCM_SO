package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m30.bp.SaleOrderUnSendApproveBP;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.rule.send.CheckUnSendEnableRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * �ջؽű���
 * 
 * @since 6.0
 * @version 2011-2-22 ����10:41:12
 * @author ô��
 */
public class N_30_UNSAVE extends AbstractPfAction<SaleOrderVO> {
  /**
   * N_30_UNSEND �Ĺ�����
   */
  public N_30_UNSAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<SaleOrderVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<SaleOrderVO> processor =
        new CompareAroundProcesser<SaleOrderVO>(BP30PlugInPoint.UnSendBP);
    this.addRule(processor);
    return processor;
  }

  @Override
  protected SaleOrderVO[] processBP(Object userObj,
      SaleOrderVO[] clientFullVOs, SaleOrderVO[] originBills) {
    SaleOrderUnSendApproveBP unsavebp = new SaleOrderUnSendApproveBP();
    SaleOrderVO[] bills = unsavebp.unSend(clientFullVOs, originBills);
    return bills;
  }

  private void addRule(CompareAroundProcesser<SaleOrderVO> processer) {
    // У�����۶����Ƿ������ջ�
    IRule<SaleOrderVO> rule = new CheckUnSendEnableRule();
    processer.addBeforeRule(rule);

  }
}
