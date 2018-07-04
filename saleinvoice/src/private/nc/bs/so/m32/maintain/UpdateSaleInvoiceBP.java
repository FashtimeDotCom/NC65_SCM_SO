package nc.bs.so.m32.maintain;

import nc.bs.so.m32.maintain.rule.insert.CheckBillCodeRule;
import nc.bs.so.m32.maintain.rule.insert.CheckBillValityRule;
import nc.bs.so.m32.maintain.rule.update.CheckBillState;
import nc.bs.so.m32.maintain.rule.update.CheckCanUpdateWhenAuditing;
import nc.bs.so.m32.maintain.rule.update.FillUpdateDefaultRule;
import nc.bs.so.m32.maintain.rule.update.RewriteBillUpdateRule;
import nc.bs.so.m32.plugin.BP32PlugInPoint;
import nc.bs.so.pub.rule.CheckApproverRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetUpdateAuditInfoRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * �޸ı���bp
 * 
 * @since 6.3
 * @version 2012-12-21 ����09:12:51
 * @author yaogj
 */
public class UpdateSaleInvoiceBP {

  /**
   * 
   * @param bills �ͻ��˴�����������
   * @param originBills ԭʼvo
   * @return ��Ʊvo
   */
  public SaleInvoiceVO[] update(SaleInvoiceVO[] bills,
      SaleInvoiceVO[] originBills) {
    CompareAroundProcesser<SaleInvoiceVO> processer =
        new CompareAroundProcesser<SaleInvoiceVO>(BP32PlugInPoint.UpdateAction);
    for (SaleInvoiceVO bill : bills) {
      bill.getParentVO().setStatus(VOStatus.UPDATED);
    }
    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills, originBills);
    /* -=notranslate=- */
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0026")/* @res "�޸ı���ǰִ��ҵ�����" */);

    TimeLog.logStart();
    BillUpdate<SaleInvoiceVO> bo = new BillUpdate<SaleInvoiceVO>();
    SaleInvoiceVO[] vos = bo.update(bills, originBills);
    /* -=notranslate=- */
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0027")/* @res "�޸ı��浥�ݵ����ݿ�" */);

    TimeLog.logStart();
    processer.after(bills, originBills);
    /* -=notranslate=- */
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0028")/* @res "�޸ı����ִ��ҵ�����" */);

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
  private void addAfterRule(CompareAroundProcesser<SaleInvoiceVO> processer) {
    // ���ݺ��ظ���У��
    IRule<SaleInvoiceVO> rule = new CheckBillCodeRule();
    processer.addAfterRule(rule);

    // ��д��Ʊ��Դ����
    ICompareRule<SaleInvoiceVO> rewriteRule = new RewriteBillUpdateRule();
    processer.addAfterRule(rewriteRule);
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
  private void addBeforeRule(CompareAroundProcesser<SaleInvoiceVO> processer) {
    IRule<SaleInvoiceVO> rule = null;
    // �������Ƿ�֧���������޸�
    rule = new CheckCanUpdateWhenAuditing();
    processer.addBeforeRule(rule);
    
    // add by wangshu6 for 636 2014-01-20 ���۷�Ʊ������֧���޶�
    // ��鵱ǰ�������ǲ��������ˣ� �����������״̬���ҵ�ǰ�����˲��������ˣ��������޸�
    rule =  new CheckApproverRule();
    processer.addBeforeRule(rule);
    // end 
    
    // ���Ĭ��ֵ
    ICompareRule<SaleInvoiceVO> fillRule = new FillUpdateDefaultRule();
    processer.addBeforeRule(fillRule);

    // ���ݺϷ���У��
    rule = new CheckBillValityRule();
    processer.addBeforeRule(rule);
 
    
    // У�鵥��״̬
    rule = new CheckBillState();
    processer.addBeforeRule(rule);

    // ���������Ϣ:����޸��ˡ�����޸�ʱ��
    rule = new SetUpdateAuditInfoRule<SaleInvoiceVO>();
    processer.addBeforeRule(rule);

  }

}
