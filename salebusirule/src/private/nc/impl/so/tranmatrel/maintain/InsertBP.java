/**
 * 
 */
package nc.impl.so.tranmatrel.maintain;

import nc.bs.so.tranmatrel.plugin.BPPlugInPoint;
import nc.bs.so.tranmatrel.rule.CheckInvSaleorgRule;
import nc.bs.so.tranmatrel.rule.CheckSaveBillRule;
import nc.bs.so.tranmatrel.rule.NullRule;
import nc.bs.so.tranmatrel.rule.TranMatRelPriorityRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * ��������BP
 * 
 * @author gdsjw
 */
public class InsertBP {
  public TranMatRelVO[] insert(TranMatRelVO[] bills) {
    AroundProcesser<TranMatRelVO> processer =
        new AroundProcesser<TranMatRelVO>(BPPlugInPoint.InsertBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("������������ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillInsert<TranMatRelVO> bo = new BillInsert<TranMatRelVO>();
    TranMatRelVO[] vos = bo.insert(bills);

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
  protected void addAfterRule(AroundProcesser<TranMatRelVO> processer) {
    processer.addAfterRule(new NullRule<TranMatRelVO>());
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
  protected void addBeforeRule(AroundProcesser<TranMatRelVO> processer) {
    IRule<TranMatRelVO> rule = null;

    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    rule = new CheckInvSaleorgRule();
    processer.addBeforeRule(rule);

    rule = new TranMatRelPriorityRule();
    processer.addBeforeRule(rule);
  }

}
