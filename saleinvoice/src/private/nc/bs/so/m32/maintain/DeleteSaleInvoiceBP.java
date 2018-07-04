package nc.bs.so.m32.maintain;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.SaveHyfjsdAfterRule;
import nc.bs.so.m32.maintain.rule.delete.CheckEnableDeleteRule;
import nc.bs.so.m32.maintain.rule.delete.ReturnBillCodeRule;
import nc.bs.so.m32.maintain.rule.delete.RewriteARSubDeleteRule;
import nc.bs.so.m32.maintain.rule.delete.RewriteBillDeleteRule;
import nc.bs.so.m32.maintain.rule.delete.UpdateOppFlagDeleteRule;
import nc.bs.so.m32.plugin.BP32PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * ���۷�Ʊɾ��bp
 * 
 * @since 6.3
 * @version 2012-12-21 ����09:11:26
 * @author yaogj
 */
public class DeleteSaleInvoiceBP {

  /**
   * 
   * @param bills ���۷�Ʊvo
   */
  public void delete(SaleInvoiceVO[] bills) {

    AroundProcesser<SaleInvoiceVO> processer =
        new AroundProcesser<SaleInvoiceVO>(BP32PlugInPoint.DeleteAction);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);

    /*-=notranslate=-*/
    TimeLog.info(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
        "04006008-0020")/*@res "ɾ��ǰִ��ҵ�����"*/);

    TimeLog.logStart();
    BillDelete<SaleInvoiceVO> bo = new BillDelete<SaleInvoiceVO>();
    bo.delete(bills);
    /*-=notranslate=-*/
    TimeLog.info(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
        "04006008-0021")/*@res "д���ݿ⣬ɾ������"*/);

    TimeLog.logStart();
    processer.after(bills);
    /*-=notranslate=-*/
    TimeLog.info(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
        "04006008-0022")/*@res "ɾ����ִ��ҵ�����"*/);

  }

  private void addAfterRule(AroundProcesser<SaleInvoiceVO> processer) {
    // ���˵��ݺ�
    IRule<SaleInvoiceVO> rule = new ReturnBillCodeRule();
    processer.addAfterRule(rule);
    // �Գ����ɷ�Ʊɾ��ʱ������Դ��Ʊ�Գ��־
    rule = new UpdateOppFlagDeleteRule();
    processer.addAfterRule(rule);
    // ��д���۷��õ���ֹ�ϵ
    rule = new RewriteARSubDeleteRule();
    processer.addAfterRule(rule);
    // ��д��Դ����
    rule = new RewriteBillDeleteRule();
    processer.addAfterRule(rule);
    // �Զ�����ر�
    // rule = new AutoSaleBalEndRule();
    // processer.addAfterRule(rule);
    //���۷�Ʊ�����˷ѽ��㵥��д
    rule = new SaveHyfjsdAfterRule();
    processer.addAfterRule(rule);
  }

  /**
   * �����������������ɾ��ǰ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-22 ����08:57:00
   */
  private void addBeforeRule(AroundProcesser<SaleInvoiceVO> processer) {

    // ������۷�Ʊ��ǰ״̬�Ƿ�ɱ�ɾ��
    IRule<SaleInvoiceVO> rule = new CheckEnableDeleteRule();
    processer.addBeforeRule(rule);
  }

}
