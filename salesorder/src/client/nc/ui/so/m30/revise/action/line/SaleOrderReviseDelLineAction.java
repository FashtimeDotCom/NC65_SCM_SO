package nc.ui.so.m30.revise.action.line;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction;
import nc.ui.so.m30.billui.rule.RelateRowDeleteRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SpecialBusiUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class SaleOrderReviseDelLineAction extends SaleOrderDelLineAction {

	private static final long serialVersionUID = -5431730414303648161L;

	@Override
	protected boolean checkCanDelRows(int[] srcrows, IKeyValue keyValue) {
		if (!super.checkCanDelRows(srcrows, keyValue)) {
			// ��ʱ�������Ƿ�ǿ��ɾ������ʾ 2018-04-19 ����ܲ
			// �޸ģ�����������ֺ�Ϊ��ʵ�ַǸֲ��ŵĺ�ͬ�޶�ҵ��ǿ��ɾ��Ҳ���ԣ����ǳ����˿϶����ܸĵ�
			int showOkCancelDlg = MessageDialog.showOkCancelDlg(this.getModel()
					.getContext().getEntranceUI(), "��ʾ", "�Ƿ�Ҫǿ��ɾ�����ں�ͬ�����õ����ݣ�");
			if (showOkCancelDlg == 1) {
				return true;
			}

			return false;
		}
		String[] rowids = new String[srcrows.length];
		Map<String, String> rowNoMap = new HashMap<String, String>();
		for (int i = 0; i < srcrows.length; i++) {
			String rowid = keyValue.getBodyStringValue(srcrows[i],
					SaleOrderBVO.CSALEORDERBID);
			String rowno = keyValue.getBodyStringValue(srcrows[i],
					SaleOrderBVO.CROWNO);
			rowids[i] = rowid;
			rowNoMap.put(rowid, rowno);
		}

		List<String> notdelrows = new ArrayList<String>();
		// ���ýӿڼ���Ƿ�������γ��ⵥ
		try {
			if (SysInitGroupQuery.isICEnabled()) {
				I4CQueryPubService outqrysrv = NCLocator.getInstance().lookup(
						I4CQueryPubService.class);
				Map<String, UFBoolean> map4cexist = outqrysrv.existBill(rowids,
						false, SOBillType.Order.getCode());
				for (Entry<String, UFBoolean> entry : map4cexist.entrySet()) {
					if (entry.getValue().booleanValue()) {
						notdelrows.add(rowNoMap.get(entry.getKey()));
					}
				}
				if (notdelrows.size() == 0) {
					return true;
				}
				ShowStatusBarMsgUtil.showErrorMsg(
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"pubapp_0", "0pubapp-0092")/* @res "����" */,
						NCLangRes.getInstance().getStrByID("4006011_0",
								"04006011-0411", null,
								new String[] { notdelrows.toString() })/*
																		 * �к�Ϊ[{0
																		 * }]
																		 * �Ķ������Ѿ��������ε���
																		 * ��
																		 * ����ɾ����
																		 */,
						this.getModel().getContext());
				return false;
			}
		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return true;
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: doBeforeAction
	 * 
	 * @Description: TODO
	 * 
	 * @param e
	 * 
	 * @return
	 * 
	 * @see
	 * nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction#doBeforeAction
	 * (java.awt.event.ActionEvent)
	 */
	@Override
	public boolean doBeforeAction(ActionEvent e) {
		// ������ʵ�ָ������в���������super
		boolean isdo = true;
		int selectedRow = getCardPanel().getBillTable().getSelectedRow();
		if (selectedRow == -1) {
			String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("pubapp_0", "0pubapp-0111");
			ShowStatusBarMsgUtil.showStatusBarMsg(message, getModel()
					.getContext());
			isdo = false;
		}
		isdo = true;
		if (!isdo) {
			return false;
		}
		BillCardPanel cardPanel = this.getCardPanel();
		IKeyValue keyValue = new CardKeyValue(cardPanel);
		int[] srcrows = cardPanel.getBodyPanel().getTable().getSelectedRows();
		// �ж�ɾ�����Ƿ�����Ʒ�У�����Ʒ��Ҫ����һƥ���еĴ�������ID�Ƿ���Ҫɾ��,����ҵ��û����Ʒ��һ˵
		//this.delProID(srcrows, keyValue);
		// �޶�ʱ��鶩�����Ƿ��ɾ��
		boolean checkpass = this.checkCanDelRows(srcrows, keyValue);
		if (!checkpass) {
			return false;
		}

		RelateRowDeleteRule delrowrule = new RelateRowDeleteRule(keyValue);
		int[] reltodelrows = delrowrule.getRelaDeleteRows(srcrows);

		for (int deleterow : reltodelrows) {
			cardPanel.getBodyPanel().getTable().getSelectionModel()
					.addSelectionInterval(deleterow, deleterow);
		}

		SpecialBusiUtil busiUtil = new SpecialBusiUtil();
		SaleOrderVO bill = (SaleOrderVO) this.getModel().getSelectedData();

		if (bill != null) {
			boolean hasLowerBill = busiUtil.hasLowerBill(bill.getPrimaryKey());
			if (hasLowerBill) {
				int showOkCancelDlg = MessageDialog.showOkCancelDlg(this.getModel()
						.getContext().getEntranceUI(), "��ʾ", "�������ݽ��ں�ͬ�����ɣ�\n�Ƿ�Ҫǿ��ɾ�����ں�ͬ�����õ����ݣ�");
				if (showOkCancelDlg == 1) {
					return true;
				}
				return false;
			}
		}

		return true;
	}

}
