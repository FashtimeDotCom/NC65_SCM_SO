package nc.impl.so.tranmatrel.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.tranmatrel.maintain.UpdateBP;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * 修改保存动作
 * 
 * @author gdsjw
 */
public class UpdateAction {

  public TranMatRelVO update(TranMatRelVO bill) {
    TranMatRelVO[] bills = new TranMatRelVO[] {
      bill
    };

    TimeLog.logStart();
    BillTransferTool<TranMatRelVO> transferTool =
        new BillTransferTool<TranMatRelVO>(bills);
    TranMatRelVO[] updatebills = transferTool.getClientFullInfoBill();
    TranMatRelVO[] originBills = transferTool.getOriginBills();

    TimeLog.info("补全前台VO、获得修改前VO、加锁、检查时间戳"); /*-=notranslate=-*/

    // CompareAroundProcesser<SoBalanceVO> processer = new
    // CompareAroundProcesser<SoBalanceVO>(
    // ActionPlugInPoint.UpdateAction);
    // // 增加业务规则
    // this.addBeforeRule(processer);
    // this.addAfterRule(processer);
    //
    // TimeLog.getInstance().logStart();
    // processer.before(bills, originBills);
    /*-=notranslate=-*/

    TimeLog.logStart();
    UpdateBP action = new UpdateBP();
    TranMatRelVO[] vos = action.update(updatebills, originBills);

    TimeLog.info("调用修改保存BP，进行保存"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(vos, originBills);
    /*-=notranslate=-*/

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);

    TimeLog.info("组织返回值,返回轻量级VO"); /*-=notranslate=-*/

    return vos[0];

  }

  // private void addAfterRule(CompareAroundProcesser<SoBalanceVO> processer) {
  // //
  // }

  // private void addBeforeRule(CompareAroundProcesser<SoBalanceVO> processer) {
  //
  // }

}
