package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pub.bill.fixblob.ReQuery2FixBlob;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ���۶����޶��ڵ�������ť
 * 
 * @since 6.36
 * @version 2014-12-26 ����1:52:04
 * @author wangshu6
 */
public class M30ReviseApproveAction extends ApproveScriptAction {

	/**
   * 
   */
	private static final long serialVersionUID = -2914924453009547525L;

	@Override
	public void doBeforAction() {
		// ��������ʱ���������
		this.getFlowContext().getEParam()
				.put(PfUtilBaseTools.PARAM_NO_LOCK, UFBoolean.TRUE);
		super.doBeforAction();
	}

	@Override
	protected Object[] processBefore(Object[] vos) {
		return ReviseVOFiltrateUtls.getIsPFlowActionBillVO(vos);
	}

	@Override
	protected boolean isActionEnable() {

		Object[] seldatas = this.model.getSelectedOperaDatas();

		if (this.model.getAppUiState() == AppUiState.NOT_EDIT
				&& null != seldatas && seldatas.length > 1) {
			return true;
		}
		Object selectedData = this.model.getSelectedData();
		Integer status = null;
		if (null != selectedData && selectedData instanceof SaleOrderHistoryVO) {
			SaleOrderHistoryVO selorder = (SaleOrderHistoryVO) selectedData;
			status = selorder.getParentVO().getFstatusflag();
		}
		boolean isEnable = this.model.getAppUiState() == AppUiState.NOT_EDIT
				&& selectedData != null
				&& (BillStatus.FREE.equalsValue(status) || BillStatus.AUDITING
						.equalsValue(status));

		return isEnable;

	}

	@Override
	protected boolean isResume(IResumeException resumeInfo) {
		return ResumeExceptionUIProcessUtils.isResume(resumeInfo,
				getFlowContext());
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
