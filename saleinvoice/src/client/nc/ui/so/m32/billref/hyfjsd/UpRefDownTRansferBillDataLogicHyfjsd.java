package nc.ui.so.m32.billref.hyfjsd;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;

public class UpRefDownTRansferBillDataLogicHyfjsd extends DefaultBillDataLogic{

	@Override
	public void doTransferAddLogic(Object selectedData) {
		// �����������ڽ�����
		super.doTransferAddLogic(selectedData);
		BillCardPanel cardPanel = this.getBillForm().getBillCardPanel();
		cardPanel.getBillData().loadLoadHeadRelation();
		cardPanel.getBillModel().loadLoadRelationItemValue();
//		BillCardPanel cardPanel = this.getBillForm().getBillCardPanel();
//		IKeyValue keyValue = new CardKeyValue(cardPanel);
//
//		// 1.��ͷ�ϼ�
//		HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
//		totalrule.calculateHeadTotal();
	}
}
