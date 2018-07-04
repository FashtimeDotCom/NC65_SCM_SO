package nc.ui.so.m30.billui.editor.headevent;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.json.UFDataTypeDeserializer;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * @author wangzym
 * @version 2017��3��3�� ����1:44:54
 */
public class JhqHeadAndBodyEditHandler {

	private SaleOrderBillForm billform;
	// ��ͷ������
	private int jhq;
	// ��׼����
	private UFDate jzrq;
	// ���彻����
	private int jhqb;
	private UFDate zcjhq = new UFDate("2000-01-01 00:00:00");

	public JhqHeadAndBodyEditHandler() {
		// TODO �Զ����ɵĹ��캯�����
	}

	// Ϊ��ͷ�༭�¼��ṩ����
	public void afterEdit(CardHeadTailAfterEditEvent e) {

		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		this.headAndBodyEditHandler(keyValue);
	}

	// Ϊ����༭�¼��ṩ����
	public void afterEdit(CardBodyAfterEditEvent e) {
		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		this.headAndBodyEditHandler(keyValue);
	}

	// ɾ�к�Ҫ���¼�����ٽ�����
	public void calculateForDelLine(IKeyValue keyValue) {
		this.headAndBodyEditHandler(keyValue);
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

	private void headAndBodyEditHandler(IKeyValue keyValue) {
		int blens = keyValue.getBodyCount();
		UFDate[] bdates = new UFDate[blens];
		for (int i = 0; i < blens; i++) {
			if (this.enAble(keyValue)) {
				jzrq = UFDate.getDate(keyValue.getHeadStringValue("jzrq")
						.substring(0, 10));
				jhq = keyValue.getHeadIntegerValue("jhq");

				if (keyValue.getBodyIntegerValue(i, "csjhq") != null) {
					jhqb = keyValue.getBodyIntegerValue(i, "csjhq");
				} else {
					jhqb = 0;
				}

				UFDate jiaohuodate = jzrq.getDateAfter(jhq).getDateAfter(jhqb);
				keyValue.setBodyValue(i, "jiaohuodate", jiaohuodate);
				bdates[i] = jiaohuodate;
			} else {
				return;
			}
		}

		// ������ٽ����ڣ����Ҹ�ֵ����ͷ,���������1������Ϊ-1

		for (int i = 0; i < bdates.length; i++) {

			UFDate ufDate = bdates[i];
			for (int j = 1; j < bdates.length; j++) {
				UFDate date = bdates[j];
				int compareO = date.compareTo(ufDate);
				if (compareO == -1) {
					int compare = zcjhq.compareTo(ufDate);
					if (compare == -1 || compare == 0) {
						zcjhq = ufDate;
					}
				} else if (compareO == 1 || compareO == 0) {
					int compare1 = zcjhq.compareTo(date);
					if (compare1 == -1 || compare1 == 0) {
						zcjhq = date;
					}
				}
			}
		}

		// ð����һ�£�û��ʹ��
		/*
		 * for(int i = 0; i < bdates.length; i++){ for(int j = bdates.length-1;
		 * j <i ; j--){ UFDate temp=new UFDate();
		 * if(bdates[j].compareTo(bdates[j-1])==1){ temp=bdates[j-1];
		 * bdates[j]=bdates[j-1]; bdates[j]=temp; } } }zcjhq=bdates[0];
		 */

		if (bdates.length == 1) {
			keyValue.setHeadValue("zcjhq", bdates[0]);
		} else {
			keyValue.setHeadValue("zcjhq", zcjhq);
		}
	}

	/**
	 * 
	 * @param keyValue
	 * @return �ж��Ƿ��м��������
	 */
	private boolean enAble(IKeyValue keyValue) {
		if (keyValue.getHeadStringValue("jzrq") == null) {
			return false;
		}
		if ((UFDate.getDate(keyValue.getHeadStringValue("jzrq"))) == null) {
			return false;
		} else if ((keyValue.getHeadIntegerValue("jhq")) == null) {
			return false;
		} else {
			return true;
		}

	}
}
