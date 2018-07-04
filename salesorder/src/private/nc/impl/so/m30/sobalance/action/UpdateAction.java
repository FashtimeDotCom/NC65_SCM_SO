package nc.impl.so.m30.sobalance.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.m30.sobalance.maintain.IUpdateBP;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForSoManual;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * �޸ı��涯��
 * 
 * @author gdsjw
 */
public class UpdateAction {

  public SoBalanceVO[] update(SoBalanceVO[] bills) {

    TimeLog.logStart();
    BillTransferTool<SoBalanceVO> transferTool =
        new BillTransferTool<SoBalanceVO>(bills);
    SoBalanceVO[] updatebills = transferTool.getClientFullInfoBill();
    SoBalanceVO[] originBills = transferTool.getOriginBills();

    TimeLog.info("��ȫǰ̨VO������޸�ǰVO�����������ʱ���"); /*-=notranslate=-*/

    TimeLog.logStart();
    IUpdateBP action =
        SobalanceBPFactoryForSoManual.getInstance().getUpdateBP();
    SoBalanceVO[] vos = action.update(updatebills, originBills);

    TimeLog.info("�����޸ı���BP�����б���"); /*-=notranslate=-*/

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);

    TimeLog.info("��֯����ֵ,����������VO"); /*-=notranslate=-*/

    return vos;
  }
}
