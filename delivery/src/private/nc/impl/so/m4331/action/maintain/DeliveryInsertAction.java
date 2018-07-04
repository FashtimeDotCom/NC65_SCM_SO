package nc.impl.so.m4331.action.maintain;

import nc.bs.so.m4331.maintain.InsertDeliveryBP;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryVO;

public class DeliveryInsertAction {
  public DeliveryVO[] insert(DeliveryVO[] newvos) {
    TimeLog.logStart();
    BillTransferTool<DeliveryVO> transferTool =
        new BillTransferTool<DeliveryVO>(newvos);
    TimeLog.info("����ǰ̨VO����֯����ֵʱʹ��"); /*-=notranslate=-*/
    TimeLog.logStart();
    TimeLog.info("�Է�������Դ���ݼ�����У��ʱ���"); /*-=notranslate=-*/
    AroundProcesser<DeliveryVO> processer =
        new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.InsertAction);
    TimeLog.logStart();
    processer.before(newvos);
    TimeLog.info("������������BPǰִ��ҵ�����"); /*-=notranslate=-*/
    TimeLog.logStart();
    InsertDeliveryBP action = new InsertDeliveryBP();
    DeliveryVO[] vos = action.insert(newvos);
    TimeLog.info("������������BP�����б���"); /*-=notranslate=-*/
    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("������������BP��ִ��ҵ�����"); /*-=notranslate=-*/
    TimeLog.logStart();
    DeliveryVO[] retvos = transferTool.getBillForToClient(vos);
    TimeLog.info("��֯����ֵ,����������VO"); /*-=notranslate=-*/
    // this.setBusiLog(vos);
    return retvos;
  }

  // private void setBusiLog(DeliveryVO[] vos) {
  // BusinessLogUtil util = new BusinessLogUtil();
  // util.setActiontype(ActionType.SAVE);
  // util.setFuncnode(FuncodeType.DELIVERY);
  // util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006002_0",
  // "04006002-0144")/*����������*/);
  // util.setFuncletInitData(null);
  // try {
  // util.insertBusiLog(vos, true);
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }
}
