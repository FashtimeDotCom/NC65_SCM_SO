/**
 * 
 */
package nc.impl.so.custmatrel.maintain;

import nc.bs.so.custmatrel.plugin.BPPlugInPoint;
import nc.bs.so.custmatrel.rule.CheckInvSaleorgRule;
import nc.bs.so.custmatrel.rule.CheckSaveBillRule;
import nc.bs.so.custmatrel.rule.CustMatRelPriorityRule;
import nc.bs.so.custmatrel.rule.NullRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * ��������BP
 * 
 * @author gdsjw
 */
public class InsertBP {
  public CustMatRelVO[] insert(CustMatRelVO[] bills) {
    AroundProcesser<CustMatRelVO> processer =
        new AroundProcesser<CustMatRelVO>(BPPlugInPoint.InsertBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("������������ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillInsert<CustMatRelVO> bo = new BillInsert<CustMatRelVO>();
    CustMatRelVO[] vos = bo.insert(bills);

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
  protected void addAfterRule(AroundProcesser<CustMatRelVO> processer) {
    processer.addAfterRule(new NullRule<CustMatRelVO>());
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
  protected void addBeforeRule(AroundProcesser<CustMatRelVO> processer) {
    IRule<CustMatRelVO> rule = null;

    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    rule = new CheckInvSaleorgRule();
    processer.addBeforeRule(rule);

    rule = new CustMatRelPriorityRule();
    processer.addBeforeRule(rule);
  }

}
