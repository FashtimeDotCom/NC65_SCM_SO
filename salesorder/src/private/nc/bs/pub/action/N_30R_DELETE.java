/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��6��6�� ����10:09:42 
 * @version: V6.5   
 */
package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.revise.rule.ReWriteSaleOrderForRevise;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m30.action.main.DeleteSaleOrderReviseAction;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @Description: TODO
 * @author: wangzy
 * @date: 2018��6��6�� ����10:09:42
 */
public class N_30R_DELETE extends AbstractPfAction<SaleOrderVO> {

	@Override
	protected CompareAroundProcesser<SaleOrderVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		// TODO �Զ����ɵķ������
		CompareAroundProcesser<SaleOrderVO> processor = new CompareAroundProcesser<SaleOrderVO>(
				BP30PlugInPoint.ReviseDeleteBP);
		// ɾ�����д��һ���汾�ĵ��ݺ�
		processor.addAfterFinalRule(new ReWriteSaleOrderForRevise());
		return processor;
	}

	@Override
	protected SaleOrderVO[] processBP(Object userObj,
			SaleOrderVO[] clientFullVOs, SaleOrderVO[] originBills) {
		// TODO �Զ����ɵķ������
		DeleteSaleOrderReviseAction action = new DeleteSaleOrderReviseAction();
		return action.delete(clientFullVOs, originBills);
	}

}
