package nc.impl.so.custmatrel.maintain;

import nc.bs.so.custmatrel.plugin.BPPlugInPoint;
import nc.bs.so.custmatrel.rule.CheckInvSaleorgRule;
import nc.bs.so.custmatrel.rule.CheckSaveBillRule;
import nc.bs.so.custmatrel.rule.CustMatRelPriorityRule;
import nc.bs.so.custmatrel.rule.NullRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * �޸ı��涯��
 * 
 * @author gdsjw
 */
public class UpdateBP {
  public UpdateBP() {
    //
  }

  public CustMatRelVO[] update(CustMatRelVO[] bills, CustMatRelVO[] originBills) {
    CompareAroundProcesser<CustMatRelVO> processer =
        new CompareAroundProcesser<CustMatRelVO>(BPPlugInPoint.UpdateBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills, originBills);

    TimeLog.info("�����޸ı���ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillUpdate<CustMatRelVO> bo = new BillUpdate<CustMatRelVO>();
    CustMatRelVO[] vos = bo.update(bills, originBills);

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
  protected void addAfterRule(CompareAroundProcesser<CustMatRelVO> processer) {
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
   * @time 2010-9-10 ����03:17:09
   */
  protected void addBeforeRule(CompareAroundProcesser<CustMatRelVO> processer) {
    IRule<CustMatRelVO> rule = null;

    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    rule = new CheckInvSaleorgRule();
    processer.addBeforeRule(rule);

    rule = new CustMatRelPriorityRule();
    processer.addBeforeRule(rule);
  }

}
