package nc.impl.so.m32.action;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.bs.pub.action.N_32_UNAPPROVE;
import nc.bs.so.m32.plugin.Action32PlugInPoint;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m32.action.rule.unapprove.BusiLog;
import nc.impl.so.m32.action.rule.unapprove.CancleSquareRule;
import nc.impl.so.m32.action.rule.unapprove.CheckConsumeRule;
import nc.impl.so.m32.action.rule.unapprove.CheckUnAppEnableRule;
import nc.impl.so.m32.action.rule.unapprove.DeleteVoucherRule;
import nc.impl.so.m32.action.rule.unapprove.ReWriteArsubUnAppRule;

/**
 * ��Ʊ����action
 * 
 * @since 6.0
 * @version 2011-10-27 ����12:41:35
 * @author ô��
 */
public class UnApproveSaleInvoiceAction {

  /**
   * �����������������۷�Ʊ�������ʵ�֡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param script
   * @return <p>
   * @author ��ӱ�
   * @time 2010-1-22 ����11:02:45
   */
  public SaleInvoiceVO[] unapprove(N_32_UNAPPROVE script) {
    SaleInvoiceVO[] retvos = null;
    try {
      SaleInvoiceVO[] inCurVOs =
          (SaleInvoiceVO[]) script.getPfParameterVO().m_preValueVos;

      AroundProcesser<SaleInvoiceVO> processer =
          new AroundProcesser<SaleInvoiceVO>(
              Action32PlugInPoint.UnApproveAction);

      this.addBeforeRule(processer);
      TimeLog.logStart();
      processer.before(inCurVOs);
      TimeLog.info("����������ǰִ��ҵ�����"); /*-=notranslate=-*/

      TimeLog.logStart();
      script.procUnApproveFlow(script.getPfParameterVO());
      TimeLog.info("�����������������˴�����������޸�"); /*-=notranslate=-*/

      this.addAfterRule(processer);
      TimeLog.logStart();
      processer.after(inCurVOs);
      TimeLog.info("������������ִ��ҵ�����"); /*-=notranslate=-*/

      TimeLog.logStart();
      retvos = this.queryNewVO(inCurVOs);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return retvos;
  }

  /**
   * �����������������ȡ����˺�ҵ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author fengjb
   * @time 2010-6-23 ����11:33:08
   */
  private void addAfterRule(AroundProcesser<SaleInvoiceVO> processer) {
    // ��д��Ӧ�յ�
    IRule<SaleInvoiceVO> rule = new ReWriteArsubUnAppRule();
    processer.addAfterRule(rule);
    // ɾ��ƾ֤
    rule = new DeleteVoucherRule();
    processer.addAfterRule(rule);

    // ��Ʊ��˺�����Զ���������
    rule = new CancleSquareRule();
    processer.addAfterRule(rule);

  }

  /**
   * �����������������ȡ�����ǰҵ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-22 ����08:57:00
   */
  private void addBeforeRule(AroundProcesser<SaleInvoiceVO> processer) {
    // ������۷�Ʊ��ǰ״̬�Ƿ������
    IRule<SaleInvoiceVO> rule = new CheckUnAppEnableRule();
    processer.addBeforeRule(rule);
    rule = new CheckConsumeRule();
    processer.addBeforeRule(rule);
    // ҵ����־
    rule = new BusiLog();
    processer.addBeforeRule(rule);
  }

  /**
   * ����������������ѯ��������·�ƱVO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   * @return <p>
   * @author ��ӱ�
   * @time 2010-1-22 ����02:20:47
   */
  private SaleInvoiceVO[] queryNewVO(SaleInvoiceVO[] bills) {
    int ilength = bills.length;
    String[] ids = new String[ilength];
    for (int i = 0; i < ilength; i++) {
      ids[i] = bills[i].getPrimaryKey();
    }
    BillQuery<SaleInvoiceVO> query =
        new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
    return query.query(ids);

  }

}
