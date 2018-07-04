package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m32.maintain.CommitSaleInvoiceBP;
import nc.bs.so.m32.plugin.BP32PlugInPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m32.action.rule.commit.CheckCommitEnableRule;
import nc.impl.so.m32.action.rule.commit.SetCommitStatusRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ�ύ�ű�
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-5-31 ����09:35:21
 */
public class N_32_SAVE extends AbstractPfAction<SaleInvoiceVO> {

  /**
   * N_32_SAVE �Ĺ�����
   */
  public N_32_SAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<SaleInvoiceVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<SaleInvoiceVO> processor =
        new CompareAroundProcesser<SaleInvoiceVO>(BP32PlugInPoint.SendAction);
    // TODO �ڴ˴�������ǰ����
    this.addBeforeRule(processor);
    // TODO �ڴ˴������˺����
    return processor;
  }

  @Override
  protected SaleInvoiceVO[] processBP(Object userObj,
      SaleInvoiceVO[] clientFullVOs, SaleInvoiceVO[] originBills) {
    CommitSaleInvoiceBP bp = new CommitSaleInvoiceBP();
    return bp.sendApprove(clientFullVOs, originBills);
  }

  private void addBeforeRule(CompareAroundProcesser<SaleInvoiceVO> processer) {
    processer.addBeforeRule(new CheckCommitEnableRule());

    processer.addBeforeRule(new SetCommitStatusRule());
  }

}
