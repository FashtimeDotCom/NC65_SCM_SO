package nc.impl.so.custmatrel.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.custmatrel.maintain.DeleteBP;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * ɾ������
 * 
 * @author gdsjw
 */
public class DeleteAction {
  public CustMatRelVO delete(CustMatRelVO bill) {
    CustMatRelVO[] bills = new CustMatRelVO[] {
      bill
    };
    // ��ȫǰ̨VO�����������ʱ���
    TimeLog.logStart();
    BillTransferTool<CustMatRelVO> transferTool =
        new BillTransferTool<CustMatRelVO>(bills);
    CustMatRelVO[] delbills = transferTool.getOriginBills();
    TimeLog.info("��ȫǰ̨VO�����������ʱ���"); /*-=notranslate=-*/

    // AroundProcesser<SoBalanceVO> processer = new
    // AroundProcesser<SoBalanceVO>(
    // ActionPlugInPoint.DeleteAction);
    // // ����ҵ�����
    // this.addBeforeRule(processer);
    // this.addAfterRule(processer);
    //
    // TimeLog.getInstance().logStart();
    // processer.before(bills);
    // TimeLog.getInstance().info("����ɾ��ǰ���������"); /* -=notranslate=- */

    TimeLog.logStart();
    DeleteBP action = new DeleteBP();
    action.delete(delbills);
    TimeLog.info("����ɾ��BP������ɾ��"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(bills);
    // TimeLog.getInstance().info("����ɾ������������"); /* -=notranslate=- */

    return delbills[0];
  }

  // private void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }

  // private void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }
}
