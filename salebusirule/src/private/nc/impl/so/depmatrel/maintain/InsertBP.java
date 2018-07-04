/**
 * 
 */
package nc.impl.so.depmatrel.maintain;

import nc.bs.so.depmatrel.plugin.BPPlugInPoint;
import nc.bs.so.depmatrel.rule.CheckInvSaleorgRule;
import nc.bs.so.depmatrel.rule.CheckSaveBillRule;
import nc.bs.so.depmatrel.rule.DeptMatRelPriorityRule;
import nc.bs.so.depmatrel.rule.NullRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

/**
 * ��������BP
 * 
 * @author gdsjw
 */
public class InsertBP {
  public DepMatRelVO[] insert(DepMatRelVO[] bills) {
    AroundProcesser<DepMatRelVO> processer =
        new AroundProcesser<DepMatRelVO>(BPPlugInPoint.InsertBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("������������ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillInsert<DepMatRelVO> bo = new BillInsert<DepMatRelVO>();
    DepMatRelVO[] vos = bo.insert(bills);

    TimeLog.info("���浥�ݵ����ݿ�"); /*-=notranslate=-*/

    // ע���
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(vos);

    TimeLog.info("�������������BP�����"); /*-=notranslate=-*/

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
   * @time 2010-9-10 ����03:17:23
   */
  protected void addAfterRule(AroundProcesser<DepMatRelVO> processer) {
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
   * @time 2010-9-10 ����03:17:20
   */
  protected void addBeforeRule(AroundProcesser<DepMatRelVO> processer) {
    IRule<DepMatRelVO> rule = null;

    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    rule = new CheckInvSaleorgRule();
    processer.addBeforeRule(rule);

    rule = new DeptMatRelPriorityRule();
    processer.addBeforeRule(rule);
  }

}
