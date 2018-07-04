package nc.impl.so.tranmatrel.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.tranmatrel.maintain.DeleteBP;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * ɾ������
 * 
 * @author gdsjw
 */
public class DeleteAction {
  public TranMatRelVO delete(TranMatRelVO bill) {
    TranMatRelVO[] bills = new TranMatRelVO[] {
      bill
    };

    // ��ȫǰ̨VO�����������ʱ���
    TimeLog.logStart();
    BillTransferTool<TranMatRelVO> transferTool =
        new BillTransferTool<TranMatRelVO>(bills);
    TranMatRelVO[] delbills = transferTool.getOriginBills();

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
