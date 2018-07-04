package nc.impl.so.custmatrel.maintain;

import nc.bs.so.custmatrel.plugin.BPPlugInPoint;
import nc.bs.so.custmatrel.rule.NullRule;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * ɾ��BP
 * 
 * @author gdsjw
 */
public class DeleteBP {
  public DeleteBP() {
    //
  }

  public CustMatRelVO[] delete(CustMatRelVO[] bills) {
    AroundProcesser<CustMatRelVO> processer =
        new AroundProcesser<CustMatRelVO>(BPPlugInPoint.DeleteBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("����ɾ��ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillDelete<CustMatRelVO> bo = new BillDelete<CustMatRelVO>();
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
  protected void addBeforeRule(AroundProcesser<CustMatRelVO> processer) {
    processer.addBeforeRule(new NullRule<CustMatRelVO>());
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
  protected void addAfterRule(AroundProcesser<CustMatRelVO> processer) {
    processer.addAfterRule(new NullRule<CustMatRelVO>());
  }

}
