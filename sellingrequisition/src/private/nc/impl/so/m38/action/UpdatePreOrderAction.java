package nc.impl.so.m38.action;

import nc.bs.so.m38.maintain.UpdatePreOrderBP;
import nc.bs.so.m38.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * Ԥ�����޸ı��涯��
 * 
 * @author ��־ΰ
 */
public class UpdatePreOrderAction {

  public PreOrderVO[] update(PreOrderVO[] bills, PreOrderVO[] originBills) {

    PreOrderVO[] newBills = bills;

    TimeLog.logStart();
    TimeLog.info("������38��ʱ���������Դ"); /*-=notranslate=-*/

    CompareAroundProcesser<PreOrderVO> compareProcesser =
        new CompareAroundProcesser<PreOrderVO>(ActionPlugInPoint.UpdateAction);

    TimeLog.logStart();
    compareProcesser.before(newBills, originBills);
    TimeLog.info("�����޸ı���ǰ���������"); /*-=notranslate=-*/

    UpdatePreOrderBP action = new UpdatePreOrderBP();
    PreOrderVO[] ret = action.update(newBills, originBills);

    TimeLog.logStart();
    compareProcesser.after(ret, originBills);
    TimeLog.info("�����޸ı������������"); /*-=notranslate=-*/

    return ret;
  }
}
