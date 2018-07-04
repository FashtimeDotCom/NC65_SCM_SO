package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m30.billui.rule.AssociateConstractRule;
import nc.ui.so.m30.billui.rule.ClearBodyValueRule;
import nc.ui.so.m30.billui.rule.MatchLargessRule;
import nc.ui.so.m30.billui.rule.SrcTypeRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class OrigCurrencyEditHandler {

	private SaleOrderBillForm billform;

	public void setBillform(SaleOrderBillForm billform) {
		this.billform = billform;
	}

	public SaleOrderBillForm getBillform() {
		return this.billform;
	}

	public void beforeEdit(CardHeadTailBeforeEditEvent e) {

		// ��Դ�Ǻ�ͬ�ģ����ֲ����Ա༭
		BillCardPanel cardPanel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(cardPanel);

		SrcTypeRule srcrule = new SrcTypeRule(keyValue);
		e.setReturnValue(Boolean.valueOf(!srcrule.isBillSrcCT()));

	}

	public void afterEdit(CardHeadTailAfterEditEvent e) {

		BillCardPanel cardPanel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(cardPanel);
		BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
		int[] rows = countutil.getMarNotNullRows();

		// 1.����������۱�����
		SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
		exraterule.calcBodyExchangeRates(rows);
		// 2.ȫ�ֱ�λ�һ���
		SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
		if (globalraterule
				.isGlobalExchgRateChange(SaleOrderHVO.CORIGCURRENCYID)) {
			globalraterule.calcGlobalExchangeRate(rows);
		}

		// 3.���ű�λ�һ���
		SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
		if (groupraterule.isGroupExchgRateChange(SaleOrderHVO.CORIGCURRENCYID)) {
			groupraterule.calcGroupExchangeRate(rows);
		}
		// 4.�������۽������
		SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
	  int[] changerows = exraterule.getRateChangeRow();
		calculator.calculate(changerows, SaleOrderBVO.NEXCHANGERATE);

		// 5.ѯ��
		String tranTypeCode = keyValue
				.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
		String pk_group = AppUiContext.getInstance().getPkGroup();
		SaleOrderClientContext cache = this.getBillform().getM30ClientContext();
		M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
		SaleOrderFindPriceConfig config = new SaleOrderFindPriceConfig(
				cardPanel, m30transvo);
		ClearBodyValueRule clearrule = new ClearBodyValueRule(keyValue, null);
		
		// add 2015.6.26 by zhangby5 20150127 �¶���������޸ı��֣���յ��۽�
		// ������ȷ�ϣ��޸ı��֣���Ҫ����ѯ�������ж��Ƿ�Ҫѯ�ۣ���ѯ������յ��۽��κ������Ҫ�������������ֶ�
		// ���������������ֶΣ������ۻ�Ӱ��ѯ���ļ۸�
		clearrule.clearAllFfileKey(rows);
		if (isFindPrice(config, keyValue)) {
			FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
			findPrice.findPriceAfterEdit(rows, SaleOrderHVO.CORIGCURRENCYID);
		} else {
			clearrule.clearAllPriceKey(rows);
		}
		// end
		
		// 6.������ͬ
		String trantypecode = keyValue
				.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
		SaleOrderClientContext clientcontex = this.billform
				.getM30ClientContext();
		M30TranTypeVO trantypevo = clientcontex.getTransType(trantypecode,
				pk_group);
		AssociateConstractRule asctrule = new AssociateConstractRule(cardPanel,
				trantypevo);
		asctrule.associateCT(rows);

		// 7.��������ƥ������
		MatchLargessRule matchlarrule = new MatchLargessRule(cardPanel,
				trantypevo);
		matchlarrule.matchLargess(rows);
		
		for(int row : rows){
	    	// ������ǰ���
	        UFDouble norigtaxmny =
	            keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
	        UFDouble norigsubmny =
	            keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGSUBMNY);
	        keyValue.setBodyValue(row, SaleOrderBVO.NBFORIGSUBMNY,
	            MathTool.add(norigtaxmny, norigsubmny));
	    }

		// 8.��ͷ��˰�ϼ�
		HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
		totalrule.calculateHeadTotal();
		
  	//9.����ѡ�����֮��ֱ����տͻ������˺�, add by liylr 2015-07-11
    keyValue.setHeadValue(SaleOrderHVO.CCUSTBANKACCID, null);
    keyValue.setHeadValue(SaleOrderHVO.CCUSTBANKID, null);

	}

	private boolean isFindPrice(SaleOrderFindPriceConfig config,
			IKeyValue keyValue) {

		// 1.ѯ�۲���,�ж��Ƿ�ѯ��
		Integer askrule = config.getAskPriceRule();
		if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
			return false;
		}
		// 2.�༭�ֶ��Ƿ񴥷�ѯ��
		if (!this.isTrigFindPrice(SaleOrderHVO.CORIGCURRENCYID, keyValue)) {
			return false;
		}

		return true;
	}

	private boolean isTrigFindPrice(String editkey, IKeyValue keyValue) {
		// �ж�����Ǽ۸��� �ʹ���ѯ��
		if (editkey.equals(SOItemKey.CPRICEITEMID)) {
			return true;
		}
		// ����ѯ�۴����������ж��Ƿ�ѯ��
		String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
		String[] so21 = null;

		so21 = SOSysParaInitUtil.getSO21(pk_org);

		// ѯ�۴�������Ϊ��
		if (null == so21 || so21.length == 0) {
			return false;
		}
		for (String condition : so21) {
			if (editkey.equals(condition)) {
				return true;
			}
		}
		return false;
	}
}
