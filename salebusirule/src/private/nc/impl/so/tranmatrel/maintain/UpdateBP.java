package nc.impl.so.tranmatrel.maintain;

import nc.bs.so.tranmatrel.plugin.BPPlugInPoint;
import nc.bs.so.tranmatrel.rule.CheckInvSaleorgRule;
import nc.bs.so.tranmatrel.rule.CheckSaveBillRule;
import nc.bs.so.tranmatrel.rule.NullRule;
import nc.bs.so.tranmatrel.rule.TranMatRelPriorityRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * �޸ı��涯��
 * 
 * @author gdsjw
 */
public class UpdateBP {
  public UpdateBP() {
    //
  }

  public TranMatRelVO[] update(TranMatRelVO[] bills, TranMatRelVO[] originBills) {
    CompareAroundProcesser<TranMatRelVO> processer =
        new CompareAroundProcesser<TranMatRelVO>(BPPlugInPoint.UpdateBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills, originBills);

    TimeLog.info("�����޸ı���ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillUpdate<TranMatRelVO> bo = new BillUpdate<TranMatRelVO>();
    TranMatRelVO[] vos = bo.update(bills, originBills);

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
  protected void addAfterRule(CompareAroundProcesser<TranMatRelVO> processer) {
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
   * @time 2010-9-10 ����03:17:09
   */
  protected void addBeforeRule(CompareAroundProcesser<TranMatRelVO> processer) {
    IRule<TranMatRelVO> rule = null;

    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    rule = new CheckInvSaleorgRule();
    processer.addBeforeRule(rule);

    rule = new TranMatRelPriorityRule();
    processer.addBeforeRule(rule);
  }

}
