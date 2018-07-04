/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��5��30�� ����8:59:56 
 * @version: V6.5   
 */
package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.revise.rule.CheckExistWorkflowRule;
import nc.bs.so.m30.rule.approve.CheckMaxIversionRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m30.action.main.CommitSaleOrderAction;
import nc.impl.so.m30.action.main.SaveBaseSaleOrderReviseAction;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * ���۶����޸ı���
 * 
 * @since 6.5
 * @version 2018-05-30 ����2:49:00
 * @author ����ܲ
 */
public class N_30R_SAVEBASE extends AbstractPfAction<SaleOrderHistoryVO> {
	/**
	 * ���췽��
	 */
	public N_30R_SAVEBASE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<SaleOrderHistoryVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SaleOrderHistoryVO> processor = new CompareAroundProcesser<SaleOrderHistoryVO>(
				BP30PlugInPoint.ReviseSaveBaseBP);
		this.addBeforeRule(processor);
		return processor;
	}

	@Override
	protected SaleOrderHistoryVO[] processBP(Object userObj,
			SaleOrderHistoryVO[] clientFullVOs, SaleOrderHistoryVO[] originBills) {
		SaveBaseSaleOrderReviseAction action = new SaveBaseSaleOrderReviseAction();
		return action.saveBase(clientFullVOs, originBills);
	}

	private void addBeforeRule(
			CompareAroundProcesser<SaleOrderHistoryVO> processor) {
		/*
		 * // У�鵥��״̬�Ƿ�����ύ IRule rule = new CheckExistWorkflowRule();
		 * processor.addBeforeRule(rule);
		 */
		// У���ύ�汾�Ƿ����޶��������°汾
		IRule rule = new CheckMaxIversionRule();
		processor.addBeforeRule(rule);
	}
}
