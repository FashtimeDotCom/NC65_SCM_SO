package nc.ui.so.pub.listener;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.ui.uif2.editor.BillListView;
import nc.vo.so.pub.enumeration.ListTemplateType;

/**
 * Ϊ�б�������úϼ��еĹ�����
 * 
 * @author jilu 2014-05-04 for v65
 * 
 */
public class SOListPanelTotalRowUtil {

	/**
	 * �����б����ı�ͷ�ͱ���ĺϼ���
	 * 
	 * @param listView
	 *            ������
	 */
	public static void setListViewTotalHeadAndBodyRow(BillListView listView) {
		BillListPanel listPanel = listView.getBillListPanel();
		setListViewTotalHeadRow(listPanel);
		setListViewTotalBodyRow(listPanel);
	}

	/**
	 * �����б����ı�ͷ�ͱ���ĺϼ���
	 * 
	 * @param listPanel
	 *            ShowUpableBillListView�п���ʹ��this.getBillListPanel()������ȡ
	 */
	public static void setListViewTotalHeadAndBodyRow(BillListPanel listPanel) {
		setListViewTotalHeadRow(listPanel);
		setListViewTotalBodyRow(listPanel);
	}

	/**
	 * �����б����ı�ͷ�ĺϼ���
	 * 
	 * @param listPanel
	 */
	public static void setListViewTotalHeadRow(BillListPanel listPanel) {
		// �ϼƱ�ͷ
		listPanel.getParentListPanel().setTotalRowShow(true);
		ListKeyValue keyValue1 = new ListKeyValue(listPanel,
				ListTemplateType.MAIN);
		SOListPanelTotalListener totallis1 = new SOListPanelTotalListener(
				keyValue1);
		listPanel.getHeadBillModel().addTotalListener(totallis1);
	}

	/**
	 * �����б����ı���ĺϼ���
	 * 
	 * @param listPanel
	 */
	public static void setListViewTotalBodyRow(BillListPanel listPanel) {
		// �ϼƱ���
		listPanel.getChildListPanel().setTotalRowShow(true);
		ListKeyValue keyValue2 = new ListKeyValue(listPanel,
				ListTemplateType.SUB);
		SOListPanelTotalListener totallis2 = new SOListPanelTotalListener(
				keyValue2);
		listPanel.getBodyBillModel().addTotalListener(totallis2);
	}
}
