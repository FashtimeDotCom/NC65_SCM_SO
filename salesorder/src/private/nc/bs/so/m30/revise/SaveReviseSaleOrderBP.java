package nc.bs.so.m30.revise;

import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.revise.rule.FillDataHistoryRule;
import nc.bs.so.m30.revise.rule.RewriteVbillcodeFor30R;
import nc.bs.so.m30.revise.rule.UpdateVersionNumRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * ���۶����޶����¡����۶�����UpdateBP
 * <p>
 * �޶�updateBP�ڰ�������updateBP���޶�updateBP�����޶��߼�
 * </p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author ��־ΰ
 * @time 2010-8-11 ����01:27:22
 */
public class SaveReviseSaleOrderBP {

  public SaleOrderHistoryVO[] update(SaleOrderHistoryVO[] bills,
      SaleOrderHistoryVO[] originBills) throws BusinessException {
    CompareAroundProcesser<SaleOrderHistoryVO> processer =
        new CompareAroundProcesser<SaleOrderHistoryVO>(
            BP30PlugInPoint.ReviseSaveBP);

    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills, originBills);
    TimeLog.info("�����޸ı���ǰBP�����"); /* -=notranslate=- */

    TimeLog.logStart();
    BillInsert<SaleOrderHistoryVO> bo = new BillInsert<SaleOrderHistoryVO>();
    SaleOrderHistoryVO[] vo = (SaleOrderHistoryVO[]) bo.insert(bills);
    TimeLog.info("�����޸ĵ��ݵ����ݿ�"); /* -=notranslate=- */
    //����ܲ 2018-04-02 �������µĴ���Э��Ż�д
    processer.addAfterRule(new RewriteVbillcodeFor30R());
    TimeLog.logStart();
    processer.after(bills, originBills);
    TimeLog.info("�����޸ı����BP�����");/* -=notranslate=- */

    return vo;
  }

  private void addBeforeRule(
      CompareAroundProcesser<SaleOrderHistoryVO> processer) {
    // �޶�ǰ�������
    ICompareRule<SaleOrderHistoryVO> rule = new FillDataHistoryRule();
    processer.addBeforeRule(rule);

    // ���°汾��
    ICompareRule<SaleOrderHistoryVO> comparerule = new UpdateVersionNumRule();
    processer.addBeforeRule(comparerule);
  }
}
