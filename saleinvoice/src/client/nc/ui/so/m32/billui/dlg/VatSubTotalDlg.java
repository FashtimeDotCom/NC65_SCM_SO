package nc.ui.so.m32.billui.dlg;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.WindowConstants;

import nc.vo.pubapp.AppContext;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.so.pub.enumeration.ListTemplateType;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillListData;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.so.m32.billui.pub.VatSubTotalPrecison;
import nc.ui.so.pub.keyvalue.ListPanelValueUtils;
import nc.ui.so.pub.listener.SOListPanelTotalListener;

/**
 * ���۷�Ʊ˰��С�ƶԻ���
 * 
 * @since 6.1
 * @version 2012-11-22 18:35:00
 * @author ��ӱ�
 */
public class VatSubTotalDlg extends UIDialog {

	/**
   * 
   */
	private static final long serialVersionUID = -8892895125644165865L;

	/** Ĭ�Ͽ�� */
	private static final int DEFAULTWITH = 950;

	/** Ĭ�ϸ߶� */
	private static final int DEFAUTHIGH = 700;

	/** �仯���� */
	private static final int DENOMINATOR = 8;

	/** �仯���� */
	private static final int NOLECULE = 7;

	/**
	 * �б�ģ��
	 */
	private BillListPanel vatPanel;

	/**
	 * ������
	 * 
	 * @param parent
	 * @param views
	 */
	public VatSubTotalDlg(Container parent, SaleInvoiceViewVO[] views) {
		super(parent);
		this.initialize(views);
	}

	public VatSubTotalDlg(Container parent, SaleInvoiceViewVO[] views, boolean reset) {
		super(parent, reset);
		this.initialize(views);
	}

	private void initialize(SaleInvoiceViewVO[] views) {
		this.setName("VatSubTotalDlg");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Dimension dd = Toolkit.getDefaultToolkit().getScreenSize();
		int w = VatSubTotalDlg.DEFAULTWITH;
		int h = VatSubTotalDlg.DEFAUTHIGH;
		if (dd != null) {
			w = (int) dd.getWidth() * VatSubTotalDlg.NOLECULE
					/ VatSubTotalDlg.DENOMINATOR;
			h = (int) dd.getHeight() * VatSubTotalDlg.NOLECULE
					/ VatSubTotalDlg.DENOMINATOR;
		}
		w = w > VatSubTotalDlg.DEFAULTWITH || w <= 0 ? VatSubTotalDlg.DEFAULTWITH
				: w;
		h = h > VatSubTotalDlg.DEFAUTHIGH || h <= 0 ? VatSubTotalDlg.DEFAUTHIGH
				: h;
		this.setSize(w, h);
		this.setResizable(true);
		this.setTitle(NCLangRes.getInstance().getStrByID("4006008_0",
				"04006008-0144")/* ˰��С�� */);
		this.setContentPane(this.getVatPanel());
		this.initData(views);
	}

	private void initData(SaleInvoiceViewVO[] views) {
		// ���ý��澫��
		String pk_group = AppContext.getInstance().getPkGroup();
		// ����ģ�����ݿ���
		BillListData billlistdata = this.getVatPanel().getBillListData();
		VatSubTotalPrecison.getInstance().setModelPrecision(pk_group,
				billlistdata.getBodyBillModel());
		billlistdata.setBodyValueVO(views);
		billlistdata.getBodyBillModel().loadLoadRelationItemValue();
		billlistdata.getBodyBillModel().execLoadFormula();

	}

	private BillListPanel getVatPanel() {
		if (null == this.vatPanel) {
			this.vatPanel = new BillListPanel();
			this.vatPanel.setName("vatpanel");
			this.vatPanel.setToolTipText(NCLangRes.getInstance().getStrByID(
					"4006008_0", "04006008-0144")/* ˰��С�� */);
			String operator = AppContext.getInstance().getPkUser();
			String pk_group = AppContext.getInstance().getPkGroup();
			this.vatPanel.loadTemplet("40060501", null, operator, pk_group,
					"32vat");

			BillScrollPane panle = this.vatPanel.getChildListPanel();
			ListPanelValueUtils listutils = new ListPanelValueUtils(
					this.vatPanel, ListTemplateType.SUB);
			SOListPanelTotalListener totallis = new SOListPanelTotalListener(
					listutils);
			this.vatPanel.getBodyBillModel().addTotalListener(totallis);
			panle.setTotalRowShow(true);
			this.vatPanel.setEnabled(false);
		}
		return this.vatPanel;
	}
}
