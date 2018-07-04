package nc.impl.so.m30.sobalance.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.m30.sobalance.maintain.IDeleteBP;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForSoManual;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * ɾ������
 * 
 * @author gdsjw
 */
public class DeleteAction {
  public SoBalanceVO[] delete(SoBalanceVO[] bills) {

    // ��ȫǰ̨VO�����������ʱ���
    TimeLog.logStart();
    BillTransferTool<SoBalanceVO> transferTool =
        new BillTransferTool<SoBalanceVO>(bills);
    SoBalanceVO[] delbills = transferTool.getOriginBills();

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
    IDeleteBP action =
        SobalanceBPFactoryForSoManual.getInstance().getDeleteBP();
    action.delete(delbills);

    TimeLog.info("����ɾ��BP������ɾ��"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(bills);
    /*-=notranslate=-*/

    return delbills;

  }

  // private void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }

  // private void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }
}
