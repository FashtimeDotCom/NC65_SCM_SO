package nc.impl.so.depmatrel.maintain;

import nc.bs.so.depmatrel.plugin.BPPlugInPoint;
import nc.bs.so.depmatrel.rule.NullRule;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

/**
 * ɾ��BP
 * 
 * @author gdsjw
 */
public class DeleteBP {
  public DeleteBP() {
    //
  }

  public DepMatRelVO[] delete(DepMatRelVO[] bills) {
    AroundProcesser<DepMatRelVO> processer =
        new AroundProcesser<DepMatRelVO>(BPPlugInPoint.DeleteBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("����ɾ��ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillDelete<DepMatRelVO> bo = new BillDelete<DepMatRelVO>();
    bo.delete(bills);

    TimeLog.info("д���ݿ⣬ɾ������"); /*-=notranslate=-*/

    // ע���
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(bills);

    TimeLog.info("����ɾ����BP�����"); /*-=notranslate=-*/

    return bills;
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
   * @time 2010-9-10 ����03:17:29
   */
  protected void addBeforeRule(AroundProcesser<DepMatRelVO> processer) {
    processer.addBeforeRule(new NullRule<DepMatRelVO>());
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
   * @time 2010-9-10 ����03:17:31
   */
  protected void addAfterRule(AroundProcesser<DepMatRelVO> processer) {
    processer.addAfterRule(new NullRule<DepMatRelVO>());
  }

}
