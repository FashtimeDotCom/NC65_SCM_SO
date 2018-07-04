package nc.bs.so.m32.maintain;

import nc.bs.so.m32.maintain.rule.insert.AutoUniteInvoiceRule;
import nc.bs.so.m32.maintain.rule.insert.CheckBillCodeRule;
import nc.bs.so.m32.maintain.rule.insert.CheckBillValityRule;
import nc.bs.so.m32.maintain.rule.insert.CheckOppValityRule;
import nc.bs.so.m32.maintain.rule.insert.FillNewDefaultRule;
import nc.bs.so.m32.maintain.rule.insert.OppOffsetRule;
import nc.bs.so.m32.maintain.rule.insert.RewriteBillInsertRule;
import nc.bs.so.m32.maintain.rule.insert.UpdateOppFlagInsertRule;
import nc.bs.so.m32.plugin.BP32PlugInPoint;
import nc.bs.so.pub.rule.FillBillTailInfoRuleForIns;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetAddAuditInfoRule;
import nc.vo.scmpub.rule.FinanceOrgEnableCheckRule;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.SaleInvoiceScaleCheckRule;
import nc.vo.so.m32.rule.SaveHyfjsdAfterRule;

/**
 * ���۷�Ʊ��������bp
 * 
 * @since 6.3
 * @version 2012-12-21 ����09:12:10
 * @author yaogj
 */
public class InsertSaleInvoiceBP {

  /**
   * 
   * @param bills ��Ʊvo
   * @return �����vo
   */
  public SaleInvoiceVO[] insert(SaleInvoiceVO[] bills) {
    AroundProcesser<SaleInvoiceVO> processer =
        new AroundProcesser<SaleInvoiceVO>(BP32PlugInPoint.InsertAction);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);

    // "4006008_0", "04006008-0023"
    TimeLog.info("����ǰִ��ҵ�����"); /* -=notranslate=- */

    TimeLog.logStart();
    BillInsert<SaleInvoiceVO> bo = new BillInsert<SaleInvoiceVO>();
    SaleInvoiceVO[] vos = bo.insert(bills);
    // "4006008_0", "04006008-0024"
    TimeLog.info("���浥�ݵ����ݿ�"); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(vos);
    // "4006008_0", "04006008-0025"
    TimeLog.info("�����ִ��ҵ�����"); /* -=notranslate=- */

    return vos;
  }

  /**
   * ����������������ӱ����ִ�й���
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-21 ����05:08:24
   */
  private void addAfterRule(AroundProcesser<SaleInvoiceVO> processer) {
    // ���ݺ��ظ���У��
    IRule<SaleInvoiceVO> rule = new CheckBillCodeRule();
    processer.addAfterRule(rule);
    // �Գ����ɷ�Ʊ������Դ��Ʊ�Գ��־
    rule = new UpdateOppFlagInsertRule();
    processer.addAfterRule(rule);
    // ��д��Ʊ��Դ����
    rule = new RewriteBillInsertRule();
    processer.addAfterRule(rule);

    // ���ɶԳ巢Ʊʱ�����
    rule = new OppOffsetRule();
    processer.addAfterRule(rule);
    
  }

  /**
   * ����������������ӱ���ǰִ�й���
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-21 ����05:07:25
   */
  private void addBeforeRule(AroundProcesser<SaleInvoiceVO> processer) {

    // ����֯ͣ�ü��
    IRule<SaleInvoiceVO> rule = new FinanceOrgEnableCheckRule<SaleInvoiceVO>();
    processer.addBeforeRule(rule);

    // ����Ƶ���Ϣ
    rule = new FillBillTailInfoRuleForIns<SaleInvoiceVO>();
    processer.addBeforeRule(rule);

    // ��������Ϣ:�����ˡ�����ʱ��
    rule = new SetAddAuditInfoRule<SaleInvoiceVO>();
    processer.addBeforeRule(rule);

    // �Գ����ɷ�ƱУ��
    rule = new CheckOppValityRule();
    processer.addBeforeRule(rule);

    // ���Ĭ��ֵ
    rule = new FillNewDefaultRule();
    processer.addBeforeRule(rule);
  
    // ���ݺϷ���У��
    rule = new CheckBillValityRule();
    processer.addBeforeRule(rule);

    // �Զ����ó�ֹ���
    rule = new AutoUniteInvoiceRule();
    processer.addBeforeRule(rule);

    // У���ͷ����¼����Զ������Ƿ�����
    rule = new UserDefSaveRule<SaleInvoiceVO>(new Class[] {
      SaleInvoiceHVO.class, SaleInvoiceBVO.class
    });
    processer.addBeforeRule(rule);
    
    //���۷�Ʊ�����˷ѽ��㵥��д
    rule = new SaveHyfjsdAfterRule();
    processer.addBeforeRule(rule);

  }
}
