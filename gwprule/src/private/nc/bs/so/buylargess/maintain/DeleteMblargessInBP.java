package nc.bs.so.buylargess.maintain;

import nc.bs.so.buylargess.plugin.BPMblargessPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

public class DeleteMblargessInBP {

	public void delete(BuyLargessVO[] bills) {
		AroundProcesser<BuyLargessVO> processer = new AroundProcesser<BuyLargessVO>(
				BPMblargessPlugInPoint.DeleteMblargessInBP);

		// ����ִ��ǰҵ�����
		this.addBeforeRule(processer);
		TimeLog.logStart();
		processer.before(bills);
		TimeLog.info("ɾ��ǰִ��ҵ�����"); /* -=notranslate=- */

		TimeLog.logStart();
		BillDelete<BuyLargessVO> deleteaction = new BillDelete<BuyLargessVO>();
		deleteaction.delete(bills);
		TimeLog.info("ɾ��"); /* -=notranslate=- */

		TimeLog.logStart();
		processer.after(bills);
		TimeLog.info("ɾ����ִ��ҵ�����"); /* -=notranslate=- */
	}

	private void addBeforeRule(AroundProcesser<BuyLargessVO> processer) {
	}
}
