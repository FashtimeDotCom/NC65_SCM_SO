package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m32.plugin.Action32PlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m32.action.UnCommitSaleInvoiceAction;
import nc.impl.so.m32.action.rule.uncommit.CheckUnCommitEnableRule;
import nc.impl.so.m32.action.rule.uncommit.SetUnCommitValueRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * �ջؽű���
 * 
 * @since 6.0
 * @version 2011-2-22 ����10:41:12
 * @author ô��
 */
public class N_32_UNSAVE extends AbstractPfAction<SaleInvoiceVO> {
  /**
   * N_32_UNCOMMIT �Ĺ�����
   */
  public N_32_UNSAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<SaleInvoiceVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<SaleInvoiceVO> processor =
        new CompareAroundProcesser<SaleInvoiceVO>(
            Action32PlugInPoint.UnSendAppAction);
    // TODO �ڴ˴�������ǰ����
    this.addRule(processor);
    // TODO �ڴ˴������˺����
    return processor;
  }

  @Override
  protected SaleInvoiceVO[] processBP(Object userObj,
      SaleInvoiceVO[] clientFullVOs, SaleInvoiceVO[] originBills) {
    SaleInvoiceVO[] bills =
        new UnCommitSaleInvoiceAction().unSend(clientFullVOs, originBills);
    return bills;
  }

  private void addRule(CompareAroundProcesser<SaleInvoiceVO> processer) {
    // У�����۷�Ʊ�Ƿ������ջ�
    IRule<SaleInvoiceVO> rule = new CheckUnCommitEnableRule();
    processer.addBeforeRule(rule);
    // �����ջغ�����ֶ�ֵ
    rule = new SetUnCommitValueRule();
    processer.addBeforeRule(rule);

  }
}
