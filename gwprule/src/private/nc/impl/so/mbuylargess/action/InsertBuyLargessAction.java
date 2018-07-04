package nc.impl.so.mbuylargess.action;

import nc.bs.so.buylargess.maintain.InsertMblargessInBP;
import nc.bs.so.buylargess.plugin.ActionMblargessPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

/**
 * Created on 2010-1-12
 * <p>
 * Title: NC56
 * </p>
 * <p>
 * Description: [����������������]
 * </p>
 * <p>
 * Copyright: ufida
 * </p>
 * <p>
 * Company: NC��Ӧ�����۹���
 * </p>
 * <p>
 * Department: ��Ӧ������С��
 * </p>
 * 
 * @author ף����[ף����@ufida.com.cn]
 * @version 1.0
 */
public class InsertBuyLargessAction {
  public BuyLargessVO[] insertBuylargess(BuyLargessVO[] bills) {

    TimeLog.logStart();
    BillTransferTool<BuyLargessVO> transferTool =
        new BillTransferTool<BuyLargessVO>(bills);
    TimeLog.info("����ǰ̨VO����֯����ֵʱʹ��"); /*-=notranslate=-*/

    AroundProcesser<BuyLargessVO> processer =
        new AroundProcesser<BuyLargessVO>(
            ActionMblargessPlugInPoint.InsertBuyLargessAction);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("������������BPǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    InsertMblargessInBP action = new InsertMblargessInBP();
    BuyLargessVO[] vos = action.insert(bills);
    TimeLog.info("������������BP�����б���"); /*-=notranslate=-*/

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);
    TimeLog.info("��֯����ֵ,����������VO"); /*-=notranslate=-*/

    TimeLog.logStart();
    TimeLog.info("ҵ����־"); /*-=notranslate=-*/
    return vos;
  }
}
