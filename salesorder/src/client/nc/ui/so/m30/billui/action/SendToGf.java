/**
 * 
 */
package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.so.m30.msg.ISend2Gf;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.query2.model.ModelDataRefresher;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.bd.meta.IBDObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @author wangzym
 * @version 2017��7��26�� ����10:54:19
 */
public class SendToGf extends NCAction {

	/*
	 * ���� Javadoc��
	 * 
	 * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
	 */
	private nc.ui.pubapp.uif2app.model.BillManageModel model;
	private nc.ui.so.m30.billui.view.SaleOrderBillForm editor;

	public SendToGf() {
		this.setBtnName("���ɷ�");
		this.setCode("sendtogf");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO ����֮ǰ����������ť���ɷݣ�Ϊ��Ӱ��֮ǰ���߼����ڴ�ֱ��Զ�̵���
		this.checkOverPurchase((SaleOrderVO) editor.getValue());

		ISend2Gf sendData = NCLocator.getInstance().lookup(ISend2Gf.class);
		sendData.process(new SaleOrderVO[] { (SaleOrderVO) this.getModel()
				.getSelectedData() });
		// ˢ��һ�½���
		// new ModelDataRefresher(this.model).refreshData();
		// �޸�ˢ�·���
		this.reFreshDate(model);

	}

	/**
	 * У����ڳ��ɸ�����ʾ
	 */
	private void checkOverPurchase(SaleOrderVO saleOrderVO) {
		// ��״̬�����ʻ���������ʽ���ʣ�״̬�����ᱻˢ�� ���¿��ܿ�����������Ϣ
		String msg = "��";
		SaleOrderBVO[] bvos = saleOrderVO.getChildrenVO();
		for (SaleOrderBVO saleOrderBVO : bvos) {
			String rowNo = saleOrderBVO.getCrowno();
			UFDouble nastNum = saleOrderBVO.getNastnum();
			Integer planNum = (int) saleOrderBVO.getAttributeValue("plan_num");
			int nastnum = nastNum.intValue();
			if (planNum != null && nastnum != planNum.intValue()) {
				msg += "[" + rowNo + "] ";
			}
		}
		if (!msg.equals("��")) {

			msg += "�� ���ڳ��ɻ��ٲɣ�";
			MessageDialog.showWarningDlg(WorkbenchEnvironment.getInstance()
					.getWorkbench().getParent(), "��ʾ", msg);
		}
	}

	/**
	 * @return model
	 */
	public nc.ui.pubapp.uif2app.model.BillManageModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            Ҫ���õ� model
	 */
	public void setModel(nc.ui.pubapp.uif2app.model.BillManageModel model) {
		this.model = model;
	}

	/**
	 * @return editor
	 */
	public nc.ui.so.m30.billui.view.SaleOrderBillForm getEditor() {
		return editor;
	}

	/**
	 * @param editor
	 *            Ҫ���õ� editor
	 */
	public void setEditor(nc.ui.so.m30.billui.view.SaleOrderBillForm editor) {
		this.editor = editor;
	}
	public static void reFreshDate(BillManageModel bmModel) throws Exception {
		Object data = bmModel.getSelectedData();
		if (data == null) {
			return;
		}
		String pk = "";
		int i = 0;
		Class<AbstractBill> clazz = null;
		IBDObject target = bmModel.getBusinessObjectAdapterFactory()
				.createBDObject(data);
		pk = (String) target.getId();
		clazz = (Class<AbstractBill>) data.getClass();
		if (clazz == null) {
			return;
		}

		// ע�⣺�����д��ֻ����ʱ��д����Ϊ����ʱ���CQ���⣬��δ����Ժ�϶�Ҫ�޸ĵģ��ڵȴ�����Ľӿ�
		// �����Ӱ��Ч�ʣ������ܶ�������
		IBillQueryService billQuery = NCLocator.getInstance().lookup(
				IBillQueryService.class);
		AbstractBill bills = billQuery.querySingleBillByPk(clazz, pk);

		if (bills == null) {
			return;
		}
		bmModel.directlyUpdate(bills);
	}

}
