package nc.impl.so.mbuylargess.action;

import nc.bs.so.buylargess.maintain.UpdateMblargessInBP;
import nc.bs.so.buylargess.plugin.ActionMblargessPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

public class UpdateBuyLargessAction {

  public BuyLargessVO[] updateBuylargess(BuyLargessVO[] toupdateVO) {
    TimeLog.logStart();
    BillTransferTool<BuyLargessVO> transferTool =
        new BillTransferTool<BuyLargessVO>(toupdateVO);
    BuyLargessVO[] newtoupdateVO = transferTool.getClientFullInfoBill();
    BuyLargessVO[] originBills = transferTool.getOriginBills();
    TimeLog.info("��ȫǰ̨VO������޸�ǰVO�����������ʱ���"); /*-=notranslate=-*/
    CompareAroundProcesser<BuyLargessVO> compareProcesser =
        new CompareAroundProcesser<BuyLargessVO>(
            ActionMblargessPlugInPoint.UpdateBuyLargessAction);
    TimeLog.logStart();

    compareProcesser.before(newtoupdateVO, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006003_0","04006003-0011")/*@res "���ø��±���BPǰִ��ҵ�����"*/);
    UpdateMblargessInBP action = new UpdateMblargessInBP();
    BuyLargessVO[] ret = action.update(newtoupdateVO, originBills);
    TimeLog.logStart();
    ret = transferTool.getBillForToClient(ret);
    TimeLog.info("��֯����ֵ,����������VO"); /*-=notranslate=-*/
    TimeLog.logStart();
    TimeLog.info("ҵ����־"); /*-=notranslate=-*/
    return ret;
  }

}