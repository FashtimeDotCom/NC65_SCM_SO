package nc.impl.so.depmatrel.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.depmatrel.maintain.UpdateBP;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

/**
 * �޸ı��涯��
 * 
 * @author gdsjw
 */
public class UpdateAction {

  public DepMatRelVO update(DepMatRelVO bill) {
    DepMatRelVO[] bills = new DepMatRelVO[] {
      bill
    };

    TimeLog.logStart();
    BillTransferTool<DepMatRelVO> transferTool =
        new BillTransferTool<DepMatRelVO>(bills);
    DepMatRelVO[] updatebills = transferTool.getClientFullInfoBill();
    DepMatRelVO[] originBills = transferTool.getOriginBills();

    TimeLog.info("��ȫǰ̨VO������޸�ǰVO�����������ʱ���"); /*-=notranslate=-*/

    // CompareAroundProcesser<SoBalanceVO> processer = new
    // CompareAroundProcesser<SoBalanceVO>(
    // ActionPlugInPoint.UpdateAction);
    // // ����ҵ�����
    // this.addBeforeRule(processer);
    // this.addAfterRule(processer);
    //
    // TimeLog.getInstance().logStart();
    // processer.before(bills, originBills);
    /*-=notranslate=-*/

    TimeLog.logStart();
    UpdateBP action = new UpdateBP();
    DepMatRelVO[] vos = action.update(updatebills, originBills);

    TimeLog.info("�����޸ı���BP�����б���"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(vos, originBills);
    /*-=notranslate=-*/

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);

    TimeLog.info("��֯����ֵ,����������VO"); /*-=notranslate=-*/

    return vos[0];

  }

  // private void addAfterRule(CompareAroundProcesser<SoBalanceVO> processer) {
  // //
  // }

  // private void addBeforeRule(CompareAroundProcesser<SoBalanceVO> processer) {
  //
  // }

}
