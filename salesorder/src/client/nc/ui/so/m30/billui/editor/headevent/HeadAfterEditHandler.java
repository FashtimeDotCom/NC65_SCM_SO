package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class HeadAfterEditHandler implements
		IAppEventHandler<CardHeadTailAfterEditEvent> {

	private SaleOrderBillForm billform;

	public SaleOrderBillForm getBillform() {
		return this.billform;
	}

	public void setBillform(SaleOrderBillForm billform) {
		this.billform = billform;
	}

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		// Ч���Ż������ٺϼƴ���
		BillCardPanel cardPanel = e.getBillCardPanel();
		cardPanel.getBillModel().setNeedCalculate(false);

		String editkey = e.getKey();
		// ��������
		if (SaleOrderHVO.CTRANTYPEID.equals(editkey)) {
			TranTypeEditHandler handler = new TranTypeEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// �ͻ�
		else if (SaleOrderHVO.CCUSTOMERID.equals(editkey)) {
			CustomerEditHandler handler = new CustomerEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}

		// ��ͷ�ջ��ͻ�
		else if (SaleOrderHVO.CHRECEIVECUSTID.equals(editkey)) {
			HeadReceiveCustEditHandler handler = new HeadReceiveCustEditHandler();
			handler.afterEdit(e);
		}

		// ��ͷ�ջ���ַ
		else if (SaleOrderHVO.CHRECEIVEADDID.equals(editkey)) {
			HeadReceiveaddrEditHandler handler = new HeadReceiveaddrEditHandler();
			handler.afterEdit(e);
		}

		// ��Ʊ�ͻ�
		else if (SaleOrderHVO.CINVOICECUSTID.equals(editkey)) {
			InvoiceCustEditHandler handler = new InvoiceCustEditHandler();
			handler.afterEdit(e);
		}

		// ���㷽ʽ
		else if (SaleOrderHVO.CBALANCETYPEID.equals(editkey)) {
			BalanceTypeEditHandler handler = new BalanceTypeEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// ������������
		else if (SaleOrderHVO.CCHANNELTYPEID.equals(editkey)) {
			ChannelTypeEditHandler handler = new ChannelTypeEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// ���䷽ʽ
		else if (SaleOrderHVO.CTRANSPORTTYPEID.equals(editkey)) {
			TransportTypeEditHandler handler = new TransportTypeEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// ó������
		else if (SaleOrderHVO.CTRADEWORDID.equals(editkey)) {
			TradewordEditHandler handler = new TradewordEditHandler();
			handler.afterEdit(e);
		}
		// ����
		else if (SaleOrderHVO.CORIGCURRENCYID.equals(editkey)) {
			OrigCurrencyEditHandler handler = new OrigCurrencyEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// ��������
		else if (SaleOrderHVO.DBILLDATE.equals(editkey)) {
			BillDateEditHandler handler = new BillDateEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// �����ۿ�
		else if (SaleOrderHVO.NDISCOUNTRATE.equals(editkey)) {
			DiscountRateEditHandler handler = new DiscountRateEditHandler();
			handler.afterEdit(e);
		}
		// ��ͷ��˰�ϼ�
		else if (SaleOrderHVO.NTOTALORIGMNY.equals(editkey)) {
			TotalOrigMnyEditHandler handler = new TotalOrigMnyEditHandler();
			handler.afterEdit(e);
		}
		// ��ͷ������ϼ�
		else if (SaleOrderHVO.NTOTALORIGSUBMNY.equals(editkey)) {
			TotalOrigSubMnyEditHandler handler = new TotalOrigSubMnyEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// �ո���Э��
		else if (SaleOrderHVO.CPAYTERMID.equals(editkey)) {
			PayTermEditHandler handler = new PayTermEditHandler();
			handler.afterEdit(e);
		}
		// Ԥ�տ����
		else if (SaleOrderHVO.NPRECEIVERATE.equals(editkey)) {
			PreceiveRateEditHandler handler = new PreceiveRateEditHandler();
			handler.afterEdit(e);
		}
		// �տ��޶�
		else if (SaleOrderHVO.NPRECEIVEQUOTA.equals(editkey)) {
			PreceiveQuotaEditHandler handler = new PreceiveQuotaEditHandler();
			handler.afterEdit(e);
		}
		// �����տ���
		else if (SaleOrderHVO.NTHISRECEIVEMNY.equals(editkey)) {
			ThisReceiveMnyEditHandler handler = new ThisReceiveMnyEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		/**
		 * add by lyw 2017-06-09 
		 * ������ͷ���ʱ༭���¼�
		 */
		else if ("exchange_rate".equals(editkey)) {
			DlflHeadAfterEdithandler handler = new DlflHeadAfterEdithandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		/**
		 * add by lyw 2017-06-12 
		 * ������ͷ�ɹ����ֱ༭���¼�
		 */
		else if ("buyccurrencyid".equals(editkey)) {
			DlflHeadAfterEdithandler handler = new DlflHeadAfterEdithandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}

		/**
		 * @author wangzym
		 * @version 2017-03-02 ������ͷ������ʵı༭���¼��ļ��� û��ͨ����̬��������ȡString�ַ���
		 */
		// �������ר����
		else if ("dlfl".equals(editkey)) {
			DlflHeadAfterEdithandler handler = new DlflHeadAfterEdithandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);

		}
		// ����ȡ��ר����
		else if ("djqz".equals(editkey)) {
			JgqzHeadAfterEditHandler handler = new JgqzHeadAfterEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);

		}
		// ��׼���ڱ�ͷ���干����
		else if ("jzrq".equals(editkey)) {
			JhqHeadAndBodyEditHandler handler = new JhqHeadAndBodyEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);

		}
		// �����ڱ�ͷ���干����
		else if ("jhq".equals(editkey)) {
			JhqHeadAndBodyEditHandler handler = new JhqHeadAndBodyEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);

		}
		/****************** wangzym modify End**************************/
		// Ч���Ż������ٺϼƴ���
		cardPanel.getBillModel().setNeedCalculate(true);

	}
}
