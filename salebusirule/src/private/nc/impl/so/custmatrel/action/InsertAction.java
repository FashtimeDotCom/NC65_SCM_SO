/**
 * 
 */
package nc.impl.so.custmatrel.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.custmatrel.maintain.InsertBP;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * �������涯��
 * 
 * @author gdsjw
 * 
 */
public class InsertAction {
  public CustMatRelVO insert(CustMatRelVO bill) {
    CustMatRelVO[] bills = new CustMatRelVO[] {
      bill
    };
    TimeLog.logStart();
    BillTransferTool<CustMatRelVO> transferTool =
        new BillTransferTool<CustMatRelVO>(bills);
    // CustMatRelVO[] insertbills = transferTool.getClientFullInfoBill();
    TimeLog.info("�õ�ǰ̨VO"); /*-=notranslate=-*/

    // AroundProcesser<SoBalanceVO> processer = new
    // AroundProcesser<SoBalanceVO>(
    // ActionPlugInPoint.InsertAction);
    // // ����ҵ�����
    // this.addBeforeRule(processer);
    // this.addAfterRule(processer);

    // TimeLog.getInstance().logStart();
    // processer.before(bills);
    // TimeLog.getInstance().info("������������ǰ���������"); /* -=notranslate=- */

    TimeLog.logStart();
    InsertBP action = new InsertBP();
    CustMatRelVO[] vos = action.insert(bills);
    TimeLog.info("������������BP�����б���"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(vos);
    // TimeLog.getInstance().info("���������������������"); /* -=notranslate=- */

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);
    TimeLog.info("��֯����ֵ,����������VO"); /*-=notranslate=-*/

    return vos[0];

  }

  // private void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }

  // private void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
  //
  // }

}
