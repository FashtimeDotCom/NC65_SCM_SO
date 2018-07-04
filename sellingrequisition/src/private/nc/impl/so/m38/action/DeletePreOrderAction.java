package nc.impl.so.m38.action;

import nc.bs.so.m38.maintain.DeletePreOrderBP;
import nc.bs.so.m38.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * Ԥ����ɾ������
 * 
 * @author ��־ΰ
 */
public class DeletePreOrderAction {
  public PreOrderVO[] delete(PreOrderVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<PreOrderVO> transferTool =
        new BillTransferTool<PreOrderVO>(bills);
    PreOrderVO[] newBills = transferTool.getClientFullInfoBill();
    TimeLog.info("��ȫǰ̨VO"); /*-=notranslate=-*/

    AroundProcesser<PreOrderVO> processer =
        new AroundProcesser<PreOrderVO>(ActionPlugInPoint.DeleteAction);

    TimeLog.logStart();
    processer.before(newBills);
    TimeLog.info("����ɾ��ǰ���������"); /*-=notranslate=-*/

    TimeLog.logStart();
    DeletePreOrderBP action = new DeletePreOrderBP();
    action.delete(newBills);
    TimeLog.info("����ɾ��BP������ɾ��"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(newBills);
    TimeLog.info("����ɾ������������"); /*-=notranslate=-*/

    TimeLog.logStart();
    // TODO
    TimeLog.info("ҵ����־"); /*-=notranslate=-*/

    return newBills;
  }
}
