/**
 * 
 */
package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * @author ����ܲ
 * @version 2018��6��22�� ����13:44:11 
 * ����Բɹ���˰���Ϊ��׼��ҵ���߼�
 */

public class CgjgbhsBodyAfterEditHandler {

	private SaleOrderBillForm billform;

	// �۸�ȡ���ı༭���¼�
	public void afterEdit(CardBodyAfterEditEvent e) {

		int row = e.getRow();
		// ����Ĳ���˰�Ĳɹ��۸�
		UFDouble cgjgbhs = (UFDouble) e.getValue();
		// ԭ�ɹ��۸�
		UFDouble oldcgjg = (UFDouble) e.getBillCardPanel().getBodyValueAt(row,
				"cgjg");
		// ˰��
		UFDouble ntaxrate = e.getBillCardPanel()
				.getBodyValueAt(row, "ntaxrate") == null ? UFDouble.ZERO_DBL
				: ((UFDouble) e.getBillCardPanel().getBodyValueAt(row,
						"ntaxrate")).div(new UFDouble(100));
		// ����
		UFDouble nastnum = e.getBillCardPanel().getBodyValueAt(row, "nastnum") == null ? UFDouble.ZERO_DBL
				: (UFDouble) e.getBillCardPanel()
						.getBodyValueAt(row, "nastnum");
		// ����ָʾ�����ݲɹ���˰�۸���ɹ���˰�۸� 2018-06-22
		UFDouble cgjg = cgjgbhs.add(cgjgbhs.multiply(ntaxrate));
		e.getBillCardPanel().setBodyValueAt(cgjg, row, "cgjg");
		// �ɹ���˰�ϼ� cgjshj->cgjg*nastnum;
		e.getBillCardPanel().setBodyValueAt(nastnum.multiply(cgjg), row,
				"cgjshj");
		// �ɹ�˰�� cgse->cgjg*nastnum-cgjg/(1+ntaxrate/100)*nastnum;
		e.getBillCardPanel().setBodyValueAt(
				nastnum.multiply(cgjg).sub(cgjgbhs.multiply(nastnum)), row,
				"cgse");

		// ��һ�²ɹ��۸�ı༭���¼�
		CgjgBodyAfterEditHandler handler = new CgjgBodyAfterEditHandler();
		handler.setBillform(billform);
		handler.afterEdit(new CardBodyAfterEditEvent(e.getBillCardPanel(),
				"bodytable1", row, "cgjg", ntaxrate, oldcgjg));

	}

	/**
	 * @return billform
	 */
	public SaleOrderBillForm getBillform() {
		return billform;
	}

	/**
	 * @param billform
	 *            Ҫ���õ� billform
	 */
	public void setBillform(SaleOrderBillForm billform) {
		this.billform = billform;
	}

}
