package nc.impl.so.depmatrel.maintain;

import nc.bs.so.depmatrel.plugin.BPPlugInPoint;
import nc.bs.so.depmatrel.rule.CheckInvSaleorgRule;
import nc.bs.so.depmatrel.rule.CheckSaveBillRule;
import nc.bs.so.depmatrel.rule.DeptMatRelPriorityRule;
import nc.bs.so.depmatrel.rule.NullRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

/**
 * �޸ı��涯��
 * 
 * @author gdsjw
 */
public class UpdateBP {
  public UpdateBP() {
    //
  }

  public DepMatRelVO[] update(DepMatRelVO[] bills, DepMatRelVO[] originBills) {
    CompareAroundProcesser<DepMatRelVO> processer =
        new CompareAroundProcesser<DepMatRelVO>(BPPlugInPoint.UpdateBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills, originBills);

    TimeLog.info("�����޸ı���ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillUpdate<DepMatRelVO> bo = new BillUpdate<DepMatRelVO>();
    DepMatRelVO[] vos = bo.update(bills, originBills);

    TimeLog.info("�����޸ĵ��ݵ����ݿ�"); /*-=notranslate=-*/

    // ע���
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(vos, originBills);

    TimeLog.info("�����޸ı����BP�����"); /*-=notranslate=-*/

    return vos;
  }

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author zhangcheng
   * @time 2010-9-10 ����03:17:12
   */
  protected void addAfterRule(CompareAroundProcesser<DepMatRelVO> processer) {
    processer.addAfterRule(new NullRule<DepMatRelVO>());
  }

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author zhangcheng
   * @time 2010-9-10 ����03:17:09
   */
  protected void addBeforeRule(CompareAroundProcesser<DepMatRelVO> processer) {
    IRule<DepMatRelVO> rule = null;

    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    rule = new CheckInvSaleorgRule();
    processer.addBeforeRule(rule);

    rule = new DeptMatRelPriorityRule();
    processer.addBeforeRule(rule);
  }

}
