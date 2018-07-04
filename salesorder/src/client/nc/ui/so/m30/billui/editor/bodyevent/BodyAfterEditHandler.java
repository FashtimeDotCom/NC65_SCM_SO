package nc.ui.so.m30.billui.editor.bodyevent;

import java.util.Arrays;
import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.editor.headevent.JhqHeadAndBodyEditHandler;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOFreeUtil;

/**
 * ��������༭���ɷ���
 * 
 * @since 6.0
 * @version 2011-6-9 ����01:28:27
 * @author fengjb
 */
public class BodyAfterEditHandler implements
		IAppEventHandler<CardBodyAfterEditEvent> {

	private SaleOrderBillForm billform;

	public SaleOrderBillForm getBillform() {
		return this.billform;
	}

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {

		int[] editrows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
		if (null == editrows) {
			return;
		}

		BillCardPanel cardPanel = e.getBillCardPanel();
		// boolean istotalshow = cardPanel.getBodyPanel().isTatolRow();
		BillScrollPane bodypanl = cardPanel.getBodyPanel();
		bodypanl.setTotalRowShow(false);

		String editKey = e.getKey();
		// ����
		if (SaleOrderBVO.CMATERIALVID.equals(editKey)) {
			MaterialEditHandler handler = new MaterialEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// �ͻ�������(V63�¼�)
		else if (SaleOrderBVO.CCUSTMATERIALID.equals(editKey)) {
			CustMaterialEditHandler handler = new CustMaterialEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// ����
		else if (SaleOrderBVO.NASTNUM.equals(editKey)) {
			AstNumEditHandler handler = new AstNumEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// ������
		else if (SaleOrderBVO.NNUM.equals(editKey)) {
			NumEditHandler handler = new NumEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// ���۵�λ����
		else if (SaleOrderBVO.NQTUNITNUM.equals(editKey)) {
			QtUnitNumEditHandler handler = new QtUnitNumEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// ��λ
		else if (SaleOrderBVO.CASTUNITID.equals(editKey)) {
			AstUnitEditHandler handler = new AstUnitEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// ���ۼ�����λ
		else if (SaleOrderBVO.CQTUNITID.equals(editKey)) {
			QtUnitEditHandler handler = new QtUnitEditHandler();
			handler.afterEdit(e);
		}
		// ��Ʒ��־
		else if (SaleOrderBVO.BLARGESSFLAG.equals(editKey)) {
			LargessFlagEditHandler handler = new LargessFlagEditHandler();
			handler.afterEdit(e);
		}
		// ���κ�
		else if (SaleOrderBVO.VBATCHCODE.equals(editKey)) {
			BatchCodeEditHandler handler = new BatchCodeEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// �����ȼ�
		else if (SaleOrderBVO.CQUALITYLEVELID.equals(editKey)) {
			QualitylevelEditHandler handler = new QualitylevelEditHandler();
			handler.afterEdit(e);
		}
		// ���������֯
		else if (SaleOrderBVO.CSENDSTOCKORGVID.equals(editKey)) {
			SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// �����ֿ�
		else if (SaleOrderBVO.CSENDSTORDOCID.equals(editKey)) {
			SendStordocEditHandler handler = new SendStordocEditHandler();
			handler.afterEdit(e);
		}
		// ���������֯
		else if (SaleOrderBVO.CSETTLEORGVID.equals(editKey)) {
			SettleOrgEditHandler handler = new SettleOrgEditHandler();
			handler.afterEdit(e);
		}
		// �ջ�����
		else if (SaleOrderBVO.CRECEIVEAREAID.equals(editKey)) {
			ReceiveAreaEditHandler handler = new ReceiveAreaEditHandler();
			handler.afterEdit(e);
		}

		// �۸���
		else if (SaleOrderBVO.CPRICEITEMID.equals(editKey)) {
			PriceItemEditHandler handler = new PriceItemEditHandler();
			handler.afterEdit(e);
		}
		// ˰��
		else if (SaleOrderBVO.CTAXCODEID.equals(editKey)) {
			TaxCodeEditHandler handler = new TaxCodeEditHandler();
			handler.afterEdit(e);
		}
		// ��˰���
		else if (SaleOrderBVO.FTAXTYPEFLAG.equals(editKey)) {
			TaxTypeFlagEditHandler handler = new TaxTypeFlagEditHandler();
			handler.afterEdit(e);
		}
		// ��˰���
		else if (SaleOrderBVO.NCALTAXMNY.equals(editKey)) {
			CaltaxmnyEditHandler handler = new CaltaxmnyEditHandler();
			handler.afterEdit(e);
		}
		// �ջ��ͻ�
		else if (SaleOrderBVO.CRECEIVECUSTID.equals(editKey)) {
			ReceiveCustEditHandler handler = new ReceiveCustEditHandler();
			handler.afterEdit(e);
		}
		// �ջ���ַ
		else if (SaleOrderBVO.CRECEIVEADDRID.equals(editKey)) {
			ReceiveaddrEditHandler handler = new ReceiveaddrEditHandler();
			handler.afterEdit(e);
		}
		// ��������
		else if (SaleOrderBVO.CSENDCOUNTRYID.equals(editKey)) {
			SendCountryEditHandler handler = new SendCountryEditHandler();
			handler.afterEdit(e);
		}
		// �ջ�����
		else if (SaleOrderBVO.CRECECOUNTRYID.equals(editKey)) {
			ReceCountryEditHandler handler = new ReceCountryEditHandler();
			handler.afterEdit(e);
		}
		// ��˰����
		else if (SaleOrderBVO.CTAXCOUNTRYID.equals(editKey)) {
			TaxCountryEditHandler handler = new TaxCountryEditHandler();
			handler.afterEdit(e);
		}// ��������(V63�¼�)
		else if (SaleOrderBVO.CPRODUCTORID.equals(editKey)) {
			ProductorEditHandler handler = new ProductorEditHandler();
			handler.afterEdit(e);
		}// ��Ӧ��(V63�¼�)
		else if (SaleOrderBVO.CVENDORID.equals(editKey)) {
			VendorEditHandler handler = new VendorEditHandler();
			handler.afterEdit(e);
		}// ������������(ԭ�������� V65�¼�)
		else if (SaleOrderBVO.CPROFITCENTERVID.equals(editKey)) {
			ProfitCenterEditHandler handler = new ProfitCenterEditHandler();
			handler.afterEdit(e);
		}// ������༭(636�¼�)
		else if (SaleOrderBVO.CMFFILEID.equals(editKey)) {
			CmffileidEditHandle handler = new CmffileidEditHandle();
			handler.afterEdit(e, billform);
		}// ������������(65�¼�)
		else if (SaleOrderBVO.CSPROFITCENTERVID.equals(editKey)) {
			CsprofitcenteridEditHandle handler = new CsprofitcenteridEditHandle();
			handler.afterEdit(e, billform);
		}
		// ���ɸ�������
		else if (SOFreeUtil.isFreeKey(editKey)) {
			FreeEditHandler handler = new FreeEditHandler();
			handler.afterEdit(e);
		} else {
			// �༭����ִ���������۽�����(calculator�ڻ���˵�����Ҫ������ֶ�)
			IKeyValue keyValue = new CardKeyValue(cardPanel);
			String trantypecode = keyValue
					.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
			String pk_group = AppUiContext.getInstance().getPkGroup();
			SaleOrderClientContext clientcontex = this.billform
					.getM30ClientContext();
			M30TranTypeVO trantypevo = clientcontex.getTransType(trantypecode,
					pk_group);
			SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
			calculator.setTranTypeVO(trantypevo);
			calculator.calculate(editrows, editKey);
		}
		// jilu for �㰲
		if (this.getPirceMnyList().contains(editKey)) {
			IKeyValue keyValue = new CardKeyValue(cardPanel);
			keyValue.setBodyValue(e.getRow(), SaleOrderBVO.CPROMOTPRICEID, null);
		}

		/**
		 * @author wzy
		 * @since 2017-03-03
		 */
		if ("csjhq".equals(editKey)) {
			JhqHeadAndBodyEditHandler handler = new JhqHeadAndBodyEditHandler();
			handler.setBillform(billform);
			handler.afterEdit(e);

		} else if ("desnddate".equals(editKey)) {

		} else if("cgjg".equals(editKey)){
			CgjgBodyAfterEditHandler handler = new CgjgBodyAfterEditHandler();
			handler.setBillform(billform);
			handler.afterEdit(e);
		}else if("cgjgbhs".equals(editKey)){
			
			CgjgbhsBodyAfterEditHandler handler = new CgjgbhsBodyAfterEditHandler();
			handler.setBillform(billform);
			handler.afterEdit(e);
		}

		// ���ﲻ��ʹ��istotalshow ��Ϊ ����ֵ�����м䱻����Ĺ�
		bodypanl.setTotalRowShow(true);
	}

	public void setBillform(SaleOrderBillForm billform) {
		this.billform = billform;
	}

	/**
	 * ȡ���۽���ֶ�List����ѯ�ۺ��ֶα༭�ԣ�
	 * 
	 * @return priceAndMny
	 */
	private List<String> getPirceMnyList() {
		// ѯ����ص��۽��
		String[] priceAndMnys = new String[] { SaleOrderBVO.NORIGTAXPRICE,
				SaleOrderBVO.NORIGPRICE, SaleOrderBVO.NORIGTAXNETPRICE,
				SaleOrderBVO.NORIGNETPRICE, SaleOrderBVO.NQTORIGTAXPRICE,
				SaleOrderBVO.NQTORIGPRICE, SaleOrderBVO.NQTORIGTAXNETPRC,
				SaleOrderBVO.NQTORIGNETPRICE, SaleOrderBVO.NTAXPRICE,
				SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXNETPRICE,
				SaleOrderBVO.NNETPRICE, SaleOrderBVO.NQTTAXPRICE,
				SaleOrderBVO.NQTPRICE, SaleOrderBVO.NQTTAXNETPRICE,
				SaleOrderBVO.NQTNETPRICE, SaleOrderBVO.NORIGTAXMNY,
				SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGDISCOUNT,
				SaleOrderBVO.NTAXMNY, SaleOrderBVO.NMNY, SaleOrderBVO.NTAX,
				SaleOrderBVO.NDISCOUNT };
		List<String> priceAndMny = Arrays.asList(priceAndMnys);
		return priceAndMny;
	}

}
