package nc.impl.so.depmatrel.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.depmatrel.maintain.DeleteBP;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

/**
 * ɾ������
 * 
 * @author gdsjw
 */
public class DeleteAction {
  public DepMatRelVO delete(DepMatRelVO bill) {
    DepMatRelVO[] bills = new DepMatRelVO[] {
      bill
    };

    // ��ȫǰ̨VO�����������ʱ���
    TimeLog.logStart();
    BillTransferTool<DepMatRelVO> transferTool =
        new BillTransferTool<DepMatRelVO>(bills);
    DepMatRelVO[] delbills = transferTool.getOriginBills();

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
    /*-=notranslate=-*/

    TimeLog.logStart();
    DeleteBP action = new DeleteBP();
    action.delete(delbills);

    TimeLog.info("����ɾ��BP������ɾ��"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(bills);
    /*-=notranslate=-*/

    return delbills[0];
  }

  // private void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }

  // private void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }
}
