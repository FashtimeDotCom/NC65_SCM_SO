package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.fixblob.ReQuery2FixBlob;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ���۶����޶��ڵ��ջذ�ť
 * 
 * @since 6.36
 * @version 2014-12-26 ����1:52:04
 * @author wangshu6
 */
public class M30ReviseUnSendApproveAction extends UnCommitScriptAction {

	/** Version */
	private static final long serialVersionUID = 4549098173080532064L;

	@Override
	protected boolean isActionEnable() {
		boolean isEnable = this.getModel().getAppUiState() == AppUiState.NOT_EDIT
				&& null != this.getModel().getSelectedData();

		if (isEnable) {
			Object[] selectedRows = this.getModel().getSelectedOperaDatas();
			SaleOrderHistoryVO selectedData = (SaleOrderHistoryVO) this
					.getModel().getSelectedData();
			Integer billstatus = selectedData.getParentVO().getFstatusflag();
			String approver = selectedData.getParentVO().getApprover();
			String saleorderid = selectedData.getParentVO().getCsaleorderid();
			String orderhistoryid = selectedData.getParentVO()
					.getCorderhistoryid();
			isEnable = (null != selectedRows && selectedRows.length > 0)
					|| null == approver
					&& BillStatus.AUDITING.equalsValue(billstatus);
			// ������۶����޶����������۶���������ͬ��˵����ǰ���ݾ������۶������°汾���ջذ�ť���ɵ�
			isEnable = (selectedData != null)
					&& (!saleorderid.equals(orderhistoryid));

		}
		return isEnable;
	}

	@Override
	protected Object[] processBefore(Object[] vos) {
		return ReviseVOFiltrateUtls.getIsPFlowActionBillVO(vos);
	}

	/**
	 * @author wangzy
	 * @date:2018-05-18
	 * @Description: ��һ�����²�ѯ
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		super.doAction(e);
		ReQuery2FixBlob.reFreshDate(model);
	}
}
