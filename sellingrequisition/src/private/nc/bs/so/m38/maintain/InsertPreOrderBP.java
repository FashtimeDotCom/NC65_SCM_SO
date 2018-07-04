package nc.bs.so.m38.maintain;

import nc.bs.so.m38.maintain.rule.CheckDateRule;
import nc.bs.so.m38.maintain.rule.insert.CheckBillAfterRule;
import nc.bs.so.m38.maintain.rule.insert.CheckBillBeforeRule;
import nc.bs.so.m38.maintain.rule.insert.FillDataBeforeRule;
import nc.bs.so.m38.maintain.rule.insert.RewritePriceFormRule;
import nc.bs.so.m38.plugin.BPPlugInPoint;
import nc.bs.so.pub.rule.FillBillTailInfoRuleForIns;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetAddAuditInfoRule;
import nc.vo.scmpub.rule.SaleOrgEnableCheckRule;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * Ԥ������������BP
 * 
 * @author ��־ΰ
 */
public class InsertPreOrderBP {

  public PreOrderVO[] insert(PreOrderVO[] bills) {
    AroundProcesser<PreOrderVO> processer =
        new AroundProcesser<PreOrderVO>(BPPlugInPoint.InsertBP);

    TimeLog.logStart();
    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    processer.before(bills);
    TimeLog.info("������������ǰBP�����"); /* -=notranslate=- */

    TimeLog.logStart();
    BillInsert<PreOrderVO> bo = new BillInsert<PreOrderVO>();
    PreOrderVO[] vos = bo.insert(bills);
    TimeLog.info("���浥�ݵ����ݿ�"); /* -=notranslate=- */

    TimeLog.logStart();
    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);
    processer.after(vos);
    TimeLog.info("�������������BP�����"); /* -=notranslate=- */

    return vos;
  }

  private void addAfterRule(AroundProcesser<PreOrderVO> processer) {

    IRule<PreOrderVO> rule = new CheckBillAfterRule();
    processer.addAfterRule(rule);

    rule = new RewritePriceFormRule();
    processer.addAfterRule(rule);

  }

  private void addBeforeRule(AroundProcesser<PreOrderVO> processer) {

    // ������֯ͣ�ü��
    IRule<PreOrderVO> rule = new SaleOrgEnableCheckRule<PreOrderVO>();
    processer.addBeforeRule(rule);

    // ���ݺϷ��Լ��
    rule = new CheckBillBeforeRule();
    processer.addBeforeRule(rule);
    // ���ڼ��
    rule = new CheckDateRule();
    processer.addBeforeRule(rule);

    // ���Ĭ��ֵ
    rule = new FillDataBeforeRule();
    processer.addBeforeRule(rule);

    // ����Ƶ���Ϣ
    rule = new FillBillTailInfoRuleForIns<PreOrderVO>();
    processer.addBeforeRule(rule);

    // ��������Ϣ
    // ��������Ϣ:�����ˡ�����ʱ�䡢����޸��ˡ�����޸�ʱ��
    rule = new SetAddAuditInfoRule<PreOrderVO>();
    processer.addBeforeRule(rule);
    /* rule = new SetUpdateAuditInfoRule<PreOrderVO>();
     * processer.addBeforeRule(rule); */

  }
}
