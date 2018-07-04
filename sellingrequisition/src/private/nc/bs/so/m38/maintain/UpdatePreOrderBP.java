package nc.bs.so.m38.maintain;

import nc.bs.so.m38.maintain.rule.CheckDateRule;
import nc.bs.so.m38.maintain.rule.insert.CheckBillAfterRule;
import nc.bs.so.m38.maintain.rule.insert.CheckBillBeforeRule;
import nc.bs.so.m38.maintain.rule.insert.RewritePriceFormRule;
import nc.bs.so.m38.maintain.rule.update.FillDataBeforeRule;
import nc.bs.so.m38.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetUpdateAuditInfoRule;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * Ԥ�����޸ı��涯��
 * 
 * @author ��־ΰ
 */
public class UpdatePreOrderBP {
  public PreOrderVO[] update(PreOrderVO[] bills, PreOrderVO[] originBills) {
    CompareAroundProcesser<PreOrderVO> processer =
        new CompareAroundProcesser<PreOrderVO>(BPPlugInPoint.UpdateBP);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills, originBills);
    TimeLog.info("�����޸ı���ǰBP�����"); /* -=notranslate=- */

    TimeLog.logStart();
    BillUpdate<PreOrderVO> bo = new BillUpdate<PreOrderVO>();
    PreOrderVO[] vos = bo.update(bills, originBills);
    TimeLog.info("�����޸ĵ��ݵ����ݿ�"); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(vos, originBills);
    TimeLog.info("�����޸ı����BP�����"); /* -=notranslate=- */

    return vos;
  }

  private void addAfterRule(CompareAroundProcesser<PreOrderVO> processer) {
    IRule<PreOrderVO> rule = new CheckBillAfterRule();
    processer.addAfterRule(rule);

    rule = new RewritePriceFormRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(CompareAroundProcesser<PreOrderVO> processer) {
    ICompareRule<PreOrderVO> fillRule = new FillDataBeforeRule();
    processer.addBeforeRule(fillRule);

    // ��������Ϣ:����޸��ˡ�����޸�ʱ��
    IRule<PreOrderVO> Rule = new SetUpdateAuditInfoRule<PreOrderVO>();
    processer.addBeforeRule(Rule);

    IRule<PreOrderVO> rule = new CheckBillBeforeRule();
    processer.addBeforeRule(rule);
    // ���ڼ��
    rule = new CheckDateRule();
    processer.addBeforeRule(rule);
  }

}
