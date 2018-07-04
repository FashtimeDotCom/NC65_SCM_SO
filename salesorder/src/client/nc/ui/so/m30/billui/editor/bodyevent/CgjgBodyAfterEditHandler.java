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
 * @author wangzym
 * @version 2017��3��2�� ����7:39:18
 */

/**
 *  ԭ�����ڱ�ͷ������ȡ�����༭���¼� ����ɡ��ɹ��۸� �ı༭���¼� Ҫ�����䡰����ȡ�����༭���¼�һ��
 * @author Daniel
 *
 */
public class CgjgBodyAfterEditHandler {

	private SaleOrderBillForm billform;

	public CgjgBodyAfterEditHandler() {
		// TODO �Զ����ɵĹ��캯�����
	}

	// �۸�ȡ���ı༭���¼�
	public void afterEdit(CardBodyAfterEditEvent e) {

		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		int i = e.getRow();
		// �ж��Ƿ�۸�ȡ��
		int yon = (int) (keyValue.getHeadValue("djqz") == null ? 2 : keyValue
				.getHeadValue("djqz"));
		// �������˰����
		UFDouble oldnqtorigprice;
		UFDouble nqtorigprice;
		if (!MathTool.isZero(keyValue.getHeadUFDoubleValue("dlfl"))
				&& keyValue.getHeadUFDoubleValue("dlfl") != null) {
			if (yon == 1) {
				// ����ȡֵ�����⣬���������ʱ仯���������þɵ�ֵҪ���¼�����ɵı��嵥�ۣ�������ȡ����
				oldnqtorigprice = keyValue.getBodyUFDoubleValue(i,
						"nqtorigprice") == null ? UFDouble.ZERO_DBL
						: keyValue.getBodyUFDoubleValue(i, "nqtorigprice");
				

				// ��ͷ����
				UFDouble exchange_rate = keyValue.getHeadUFDoubleValue("exchange_rate");
				this.reCal(e, i,exchange_rate);
				
				oldnqtorigprice = keyValue.getBodyUFDoubleValue(i,
						"nqtorigprice") == null ? UFDouble.ZERO_DBL
						: keyValue.getBodyUFDoubleValue(i, "nqtorigprice");
				
				//====lijj ȡ���߼��޸� �Ȱ���λ���Ƚ��д��� ��ȡ��  ====
				nqtorigprice = oldnqtorigprice.setScale(2, UFDouble.ROUND_HALF_UP);
				nqtorigprice = nqtorigprice.setScale(0, UFDouble.ROUND_HALF_UP);
				//====lijj ȡ���߼��޸� �Ȱ���λ���Ƚ��д��� ��ȡ��  ====
				/*
				double getInt = Math.round(oldnqtorigprice.doubleValue());
				nqtorigprice = new UFDouble(getInt); // (oldnqtorigprice);
				*/
				
				keyValue.setBodyValue(i, "nqtorigprice", nqtorigprice);
				/**
				 * add by lyw ������� 2017-6-9
				 */
				keyValue.setBodyValue(i, "nqtorignetprice", nqtorigprice);
			    SaleOrderCalculator calculator = new SaleOrderCalculator(panel);
			    calculator.calculate(i, SaleOrderBVO.NQTORIGNETPRICE);
			} else if (yon == 2) {
				// ����ɹ��۸�
				UFDouble cgjg = keyValue.getBodyUFDoubleValue(i, "cgjg");
				// �������
				UFDouble exchange_rate = keyValue.getHeadUFDoubleValue("exchange_rate");
				// ��ͷ�������
				UFDouble dlfl = keyValue.getHeadUFDoubleValue("dlfl");
				// ����˰��
				UFDouble ntaxrate = keyValue.getBodyUFDoubleValue(i, "ntaxrate");
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

				nqtorigprice = UFDouble.ZERO_DBL;

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
					keyValue.setBodyValue(i, "nqtorigprice", nqtorigprice);
					keyValue.setBodyValue(i, "nqtorignetprice", nqtorigprice);
				    SaleOrderCalculator calculator = new SaleOrderCalculator(panel);
				    calculator.calculate(i, SaleOrderBVO.NQTORIGNETPRICE);
				}
			}
		}

	}

	public void reCal(CardBodyAfterEditEvent e, int i, UFDouble exchange_rate) {

		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		// ����ɹ��۸�
		UFDouble cgjg;

		
		// ��ͷ�������
		UFDouble dlfl;
		// ����˰��
		UFDouble ntaxrate;

		UFDouble nqtorigprice = UFDouble.ZERO_DBL;
		cgjg = keyValue.getBodyUFDoubleValue(i, "cgjg");

		ntaxrate = keyValue.getBodyUFDoubleValue(i, "ntaxrate");
		dlfl = keyValue.getHeadUFDoubleValue("dlfl");
		//�ɹ�����
		String buyccurrencyid = keyValue.getHeadStringValue("buyccurrencyid");
		//������ʣ�����ɹ�����Ϊ����ң����ʰ��ٷֱȼ���
		if (buyccurrencyid.equals("1002Z0100000000001K1")) {
			dlfl = dlfl.div(100.00).add(new UFDouble(1).doubleValue());
		} 
		
		// �������,���ܻ���һ�����ݣ��������ݲ�����
		if (cgjg != null && exchange_rate != null && ntaxrate != null
				&& dlfl != null) {

			
			if (buyccurrencyid.equals("1002Z0100000000001K1")) {
				nqtorigprice = cgjg.multiply(dlfl)
						.div(ntaxrate.div(100.00).add(new Double(1).doubleValue()));
			}else{
				nqtorigprice = cgjg.multiply(dlfl).multiply(exchange_rate)
					.div(ntaxrate.div(100.00).add(new Double(1).doubleValue()));
			}
			keyValue.setBodyValue(i, "nqtorigprice", nqtorigprice);

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
