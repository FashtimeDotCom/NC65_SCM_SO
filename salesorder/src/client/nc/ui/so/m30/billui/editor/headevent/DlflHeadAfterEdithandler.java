/**
 * 
 */
package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;

/**
 * @author wangzym
 * @version 2017��3��2�� ����3:57:03
 */
public class DlflHeadAfterEdithandler {

	private SaleOrderBillForm billform;

	public DlflHeadAfterEdithandler() {
		// TODO �Զ����ɵĹ��캯�����
	}

	public void afterEdit(CardHeadTailAfterEditEvent e) {

		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		//����Ϊ�յ��в��������
		BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
		int[] rows = countutil.getMarNotNullRows();
		// ����ɹ��۸�
		UFDouble cgjg;
		// �������
		UFDouble exchange_rate = keyValue.getHeadUFDoubleValue("exchange_rate");
		// ��ͷ�������
		UFDouble dlfl = keyValue.getHeadUFDoubleValue("dlfl");
		// ����˰��
		UFDouble ntaxrate;
		//�ɹ�����
		String buyccurrencyid = keyValue.getHeadStringValue("buyccurrencyid");
		if (exchange_rate == null) {
			ExceptionUtils.wrappBusinessException("���ʲ���Ϊ�գ���������ʣ�");
		}
		if ( buyccurrencyid == null ) {
			ExceptionUtils.wrappBusinessException("�ɹ����ֲ���Ϊ�գ�������ɹ����֣�");
		}
		if ( dlfl == null ) {
			ExceptionUtils.wrappBusinessException("������ʲ���Ϊ�գ������������ʣ�");
		}
		//������ʣ�����ɹ�����Ϊ����ң����ʰ��ٷֱȼ���
		if (buyccurrencyid.equals("1002Z0100000000001K1")) {
			dlfl = dlfl.div(100.00).add(new UFDouble(1).doubleValue());
		} else {
			dlfl = keyValue.getHeadUFDoubleValue("dlfl");
		}

		UFDouble nqtorigprice = UFDouble.ZERO_DBL;
		int djqz = keyValue.getHeadIntegerValue("djqz") == null ? 2 : keyValue
				.getHeadIntegerValue("djqz");
		for (int i = 0; i < rows.length; i++) {
			//�ɹ��۸�
			cgjg = keyValue.getBodyUFDoubleValue(rows[i], "cgjg");
			//˰��
			ntaxrate = keyValue.getBodyUFDoubleValue(rows[i], "ntaxrate");
			
			// �������,���ܻ���һ�����ݣ��������ݲ�����
/*			if (cgjg != null && exchange_rate != null && ntaxrate != null && dlfl != null) {
				nqtorigprice = (cgjg.multiply(exchange_rate).multiply(dlfl))
						.div(ntaxrate.div(100.00).add(new Double(1).doubleValue()));
				//keyValue.setBodyValue(i, "nqtorigprice", nqtorigprice);
				// �˴��鿴�Ƿ���Ҫȡ��
				if (djqz == 1) {
					// ��Ҫȡ����ֱ�ӵ�ȡ����
					JgqzHeadAfterEditHandler handler = new JgqzHeadAfterEditHandler();
					handler.setBillform(this.billform);
					handler.afterEdit(e);
				}
			}
*/		
		 /**
		 * add by lyw 2017-6-9
		 * ���ݴ�����ʣ�������˰����
		 */
			if (cgjg != null ) {
				nqtorigprice = cgjg;
			} else {
				ExceptionUtils.wrappBusinessException ("�ɹ����۲���Ϊ�գ���������ɹ�����");
			}
		if (dlfl != null) {
			if (buyccurrencyid.equals("1002Z0100000000001K1")) {
				nqtorigprice = nqtorigprice.multiply(dlfl);
				nqtorigprice = nqtorigprice.div(ntaxrate.div(100).add(new UFDouble(1).doubleValue()));
			} else {
				nqtorigprice = nqtorigprice.multiply(dlfl).multiply(exchange_rate).div(ntaxrate.div(100).add(new UFDouble(1).doubleValue()));
			}
			keyValue.setBodyValue(rows[i], "nqtorigprice", nqtorigprice);
			keyValue.setBodyValue(rows[i], "nqtorignetprice", nqtorigprice);
		    SaleOrderCalculator calculator = new SaleOrderCalculator(panel);
		    calculator.calculate(rows[i], SaleOrderBVO.NQTORIGNETPRICE);
			if (djqz == 1) {
				// ��Ҫȡ����ֱ�ӵ�ȡ����
				JgqzHeadAfterEditHandler handler = new JgqzHeadAfterEditHandler();
				handler.setBillform(this.billform);
				handler.afterEdit(e);
			}
		}
	}

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
