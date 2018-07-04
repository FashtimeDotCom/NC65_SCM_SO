/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��5��30�� ����9:09:08 
 * @version: V6.5   
 */
package nc.impl.so.m30.action.main;

import nc.bs.so.m30.revise.SaveBaseReviseSaleOrderBP;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * @Description: TODO
 * @author: wangzy
 * @date: 2018��5��30�� ����9:09:08
 */
public class SaveBaseSaleOrderReviseAction {
	public SaleOrderHistoryVO[] saveBase(SaleOrderHistoryVO[] clientBills,
			SaleOrderHistoryVO[] originBills) {
		// �����ڴ˴���һЩ�������ݸ��µĴ�������Ŀǰû������ֱ�Ӿ͵�bp
		// ����ͷ�ı�־����Ϊnull
		before(clientBills);
		SaveBaseReviseSaleOrderBP bp = new SaveBaseReviseSaleOrderBP();
		SaleOrderHistoryVO[] ret = null;
		try {
			ret = bp.update(clientBills, originBills);
		} catch (BusinessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * @Description: һ�ε�����ɣ�����־����Ϊnull
	 */
	private void before(SaleOrderHistoryVO[] clientBills) {
		// TODO �Զ����ɵķ������
		for (SaleOrderHistoryVO saleOrderHistoryVO : clientBills) {
			SaleOrderHistoryHVO parentVO = saleOrderHistoryVO.getParentVO();
			parentVO.setAttributeValue("agdef1", null);
		}

	}

}
