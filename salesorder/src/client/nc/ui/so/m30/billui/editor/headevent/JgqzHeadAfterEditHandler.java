/**
 * 
 */
package nc.ui.so.m30.billui.editor.headevent;

import nc.bs.ra.common.UFDoubleUtil;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.arap.verify.UFDoubleTool;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * @author wangzym
 * @version 2017��3��2�� ����7:39:18
 */
public class JgqzHeadAfterEditHandler {

	private SaleOrderBillForm billform;

	public JgqzHeadAfterEditHandler() {
		// TODO �Զ����ɵĹ��캯�����
	}

	// �۸�ȡ���ı༭���¼�
	public void afterEdit(CardHeadTailAfterEditEvent e) {

		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		int blens = keyValue.getBodyCount();
		// �ж��Ƿ�۸�ȡ��
		int yon = (int) (keyValue.getHeadValue("djqz") == null ? 2 : keyValue
				.getHeadValue("djqz"));
		// �������˰����
		UFDouble oldnqtorigprice;
		UFDouble nqtorigprice;
		if (!MathTool.isZero(keyValue.getHeadUFDoubleValue("dlfl"))
				&& keyValue.getHeadUFDoubleValue("dlfl") != null) {
			if (yon == 1) {
				for (int i = 0; i < blens; i++) {
					// ����ȡֵ�����⣬���������ʱ仯���������þɵ�ֵҪ���¼�����ɵı��嵥�ۣ�������ȡ����
					oldnqtorigprice = keyValue.getBodyUFDoubleValue(i,
							"nqtorigprice") == null ? UFDouble.ZERO_DBL
							: keyValue.getBodyUFDoubleValue(i, "nqtorigprice");
					// ���oldnqtorigpriceΪ�տ���������������ߵ�һ��ѡ��Ϊ��Ҫȡ��Ҫ���¼���һ���ٽ���ȡ��
					if (oldnqtorigprice == UFDouble.ZERO_DBL
							|| oldnqtorigprice.toString().equals("0.00")) {

						this.reCal(e, i);
					}
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
				}
			} else if (yon == 2) {
				DlflHeadAfterEdithandler handler = new DlflHeadAfterEdithandler();
				handler.setBillform(this.billform);
				handler.afterEdit(e);

			}
		}

	}

	public void reCal(CardHeadTailAfterEditEvent e, int i) {

		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		int blens = keyValue.getBodyCount();
		// ����ɹ��۸�
		UFDouble cgjg;
		// �������
		UFDouble exchange_rate;
		// ��ͷ�������
		UFDouble dlfl;
		// ����˰��
		UFDouble ntaxrate;

		UFDouble nqtorigprice = UFDouble.ZERO_DBL;
		int djqz = keyValue.getHeadIntegerValue("djqz") == null ? 2 : keyValue
				.getHeadIntegerValue("djqz");
		cgjg = keyValue.getBodyUFDoubleValue(i, "cgjg");
		exchange_rate = keyValue.getBodyUFDoubleValue(i, "exchange_rate");
		ntaxrate = keyValue.getBodyUFDoubleValue(i, "ntaxrate");
		dlfl = keyValue.getHeadUFDoubleValue("dlfl");
		// �������,���ܻ���һ�����ݣ��������ݲ�����
		if (cgjg != null && exchange_rate != null && ntaxrate != null
				&& dlfl != null) {

			nqtorigprice = (cgjg.multiply(exchange_rate).multiply(dlfl))
					.div(ntaxrate.div(100.00).add(new Double(1).doubleValue()));
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
