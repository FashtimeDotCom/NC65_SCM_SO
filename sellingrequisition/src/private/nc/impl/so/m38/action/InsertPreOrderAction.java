package nc.impl.so.m38.action;

import nc.bs.so.m38.maintain.InsertPreOrderBP;
import nc.bs.so.m38.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * �����������涯��
 * 
 * @author ��־ΰ
 * 
 */
public class InsertPreOrderAction {
  public PreOrderVO[] insert(PreOrderVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<PreOrderVO> transferTool =
        new BillTransferTool<PreOrderVO>(bills);
    TimeLog.info("����ǰ̨VO����֯����ֵʱʹ��"); /*-=notranslate=-*/

    AroundProcesser<PreOrderVO> processer =
        new AroundProcesser<PreOrderVO>(ActionPlugInPoint.InsertAction);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("������������ǰ���������"); /*-=notranslate=-*/

    TimeLog.logStart();
    InsertPreOrderBP action = new InsertPreOrderBP();
    PreOrderVO[] vos = action.insert(bills);
    TimeLog.info("������������BP�����б���"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("���������������������"); /*-=notranslate=-*/

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);
    TimeLog.info("��֯����ֵ,����������VO"); /*-=notranslate=-*/

    return vos;
  }
}
