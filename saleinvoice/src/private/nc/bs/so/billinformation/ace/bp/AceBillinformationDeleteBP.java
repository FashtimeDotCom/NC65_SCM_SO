package nc.bs.so.billinformation.ace.bp;

import nc.bs.so.billinformation.plugin.bpplugin.BillinformationPluginPoint;
import nc.vo.so.billinformation.AggBillInforMationVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceBillinformationDeleteBP {

	public void delete(AggBillInforMationVO[] bills) {

		DeleteBPTemplate<AggBillInforMationVO> bp = new DeleteBPTemplate<AggBillInforMationVO>(
				BillinformationPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggBillInforMationVO> processer) {
		// TODO ǰ����
		/*IRule<AggBillInforMationVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);*/
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBillInforMationVO> processer) {
		// TODO �����

	}
}
