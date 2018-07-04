package nc.impl.so.tranmatrel.maintain;

import nc.bs.so.tranmatrel.plugin.BPPlugInPoint;
import nc.bs.so.tranmatrel.rule.NullRule;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * ɾ��BP
 * 
 * @author gdsjw
 */
public class DeleteBP {
  public DeleteBP() {
    //
  }

  public TranMatRelVO[] delete(TranMatRelVO[] bills) {
    AroundProcesser<TranMatRelVO> processer =
        new AroundProcesser<TranMatRelVO>(BPPlugInPoint.DeleteBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("����ɾ��ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillDelete<TranMatRelVO> bo = new BillDelete<TranMatRelVO>();
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
  protected void addBeforeRule(AroundProcesser<TranMatRelVO> processer) {
    processer.addBeforeRule(new NullRule<TranMatRelVO>());
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
  protected void addAfterRule(AroundProcesser<TranMatRelVO> processer) {
    processer.addAfterRule(new NullRule<TranMatRelVO>());
  }

}
