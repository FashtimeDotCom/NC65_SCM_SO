package nc.ui.so.m4331.billui.action.returnaction.dlg;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4331.IDeliverycheckMaintain;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIDialogEvent;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.pub.DeliveryVOCalculator;

/**
 * �۸����
 * 
 * @since 6.0
 * @version 2011-1-4 ����02:21:12
 * @author ף����
 */
public class ChgPriceDialog extends UIDialog implements BillEditListener {

	/**
	 * �����¼�
	 * 
	 * @since 6.0
	 * @version 2011-1-4 ����02:21:23
	 * @author ף����
	 */
	class IvjEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ChgPriceDialog.this.getUIButtonOk()) {
				ChgPriceDialog.this.connEtoC1();
			}
			if (e.getSource() == ChgPriceDialog.this.getUIButtonCancel()) {
				ChgPriceDialog.this.connEtoC4();
			}
		}
	}

	private static int iButton_Height = 30;

	private static int iButton_Width = 700;

	private static int iButton_X = 10;

	private static int iButton_Y = 435;

	private static int iMainFrame_Height = 500;

	private static int iMainFrame_Width = 720;

	private static int iTable_Height = 425;

	private static int iTable_Width = 693;

	private static int iTable_X = 10;

	private static int iTable_Y = 10;

	private static final long serialVersionUID = 1L;

	IvjEventHandler ivjEventHandler = new IvjEventHandler();

	private BillCardPanel ivjBillCardPanel;

	private nc.ui.pub.beans.UIPanel ivjPnlButtons;

	private UIButton ivjUIButtonCancel;

	private UIButton ivjUIButtonOk;

	private JPanel ivjUIDialogContentPane;

	/**
	 * StatbDlg ������ע�⡣
	 * 
	 * @param parent
	 *            java.awt.Container
	 */
	public ChgPriceDialog(Container parent) {
		super(parent);
		this.initialize();
	}

	public ChgPriceDialog(Container parent, boolean reset) {
		super(parent, reset);
		this.initialize();
	}

	@Override
	public void afterEdit(BillEditEvent e) {
		String voname = DeliveryCheckVO.class.getName();
		BillModel model = this.getBillCardPanel().getBillModel();
		DeliveryCheckVO vo = (DeliveryCheckVO) model.getBodyValueRowVO(
				e.getRow(), voname);

		// û�������޷����㱾�ҵļ۸����ʵ������û�й�ϵ�������۱����ʼ��㣬���Ը�ֵһ�����ڼ��ɡ�
		vo.setDbilldate(AppContext.getInstance().getBusiDate());
		DeliveryVOCalculator cal = new DeliveryVOCalculator(
				new DeliveryCheckVO[] { vo });
		int[] rows = new int[1];
		rows[0] = 0;
		cal.calculate(rows, e.getKey());
		this.getBillCardPanel().getBillModel().setBodyRowVO(vo, e.getRow());
	}

	@Override
	public void bodyRowChange(BillEditEvent e) {
		return;
	}

	public void loadData(DeliveryBVO bvo) {
		String bid = bvo.getCdeliverybid();
		DeliveryCheckVO[] vos = this.getCheckVOs(bid);
		for (DeliveryCheckVO checkvo : vos) {
			checkvo.setDbilldate(bvo.getDbilldate());
		}
		this.getBillCardPanel().getBillModel().setBodyDataVO(vos);
		this.getBillCardPanel().getBillModel().updateValue();
		this.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
	}

	public void onCancel() {
		this.setResult(UIDialog.ID_CANCEL);
		this.close();
		this.fireUIDialogClosed(new UIDialogEvent(this,
				UIDialogEvent.WINDOW_CANCEL));
		return;
	}

	/**
	 * Comment
	 */
	public void onOk() {
		DeliveryCheckVO[] vos = (DeliveryCheckVO[]) this.getBillCardPanel()
				.getBillModel()
				.getBodyValueChangeVOs(DeliveryCheckVO.class.getName());
		IDeliverycheckMaintain service = NCLocator.getInstance().lookup(
				IDeliverycheckMaintain.class);
		service.updateDeliverycheck(vos);
		this.closeOK();
	}

	/**
	 * ���� BillCardPanel1 ����ֵ��
	 * 
	 * @return nc.ui.pub.bill.BillCardPanel
	 */
	protected BillCardPanel getBillCardPanel() {
		if (this.ivjBillCardPanel == null) {
			this.ivjBillCardPanel = new BillCardPanel();
			this.ivjBillCardPanel.setName("ChgPriceDlg");
			this.ivjBillCardPanel.setBounds(ChgPriceDialog.iTable_X,
					ChgPriceDialog.iTable_Y, ChgPriceDialog.iTable_Width,
					ChgPriceDialog.iTable_Height);
			this.ivjBillCardPanel.loadTemplet("1001Z81000000000MIYI");
			this.ivjBillCardPanel.setBodyMenuShow(false);
			this.ivjBillCardPanel.addEditListener(this);
		}
		ChgPricePrecion precion = new ChgPricePrecion();
		precion.setCardPrecision(AppContext.getInstance().getPkGroup(),
				this.ivjBillCardPanel);
		return this.ivjBillCardPanel;
	}

	/**
	 * ���� UIDialogContentPane ����ֵ��
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getUIDialogContentPane() {
		if (this.ivjUIDialogContentPane == null) {
			this.ivjUIDialogContentPane = new JPanel();
			this.ivjUIDialogContentPane.setName("UIDialogContentPane");
			this.ivjUIDialogContentPane.setLayout(null);
			this.getUIDialogContentPane().add(this.getPnlButtons(), "South");
			this.getUIDialogContentPane()
					.add(this.getBillCardPanel(), "Center");
		}
		return this.ivjUIDialogContentPane;
	}

	void connEtoC1() {
		this.onOk();
	}

	void connEtoC4() {
		this.onCancel();
	}

	/**
	 * ���� UIButtonCancel ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	UIButton getUIButtonCancel() {
		if (this.ivjUIButtonCancel == null) {
			this.ivjUIButtonCancel = new nc.ui.pub.beans.UIButton();
			this.ivjUIButtonCancel.setName("UIButtonCancel");
			this.ivjUIButtonCancel.setText(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("common", "UC001-0000008")/*
																		 * @res
																		 * "ȡ��"
																		 */);
		}
		return this.ivjUIButtonCancel;
	}

	/**
	 * ���� UIButtonOk ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	UIButton getUIButtonOk() {
		if (this.ivjUIButtonOk == null) {
			this.ivjUIButtonOk = new nc.ui.pub.beans.UIButton();
			this.ivjUIButtonOk.setName("UIButtonOk");
			this.ivjUIButtonOk.setText(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("common", "UC001-0000044")/*
																		 * @res
																		 * "ȷ��"
																		 */);
		}
		return this.ivjUIButtonOk;
	}

	private DeliveryCheckVO[] getCheckVOs(String bid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct(");
		sql.append(DeliveryCheckVO.CDELIVERYCID);
		sql.append(") from so_delivery_check where dr =0 and ");
		sql.append(DeliveryCheckVO.CDELIVERYBID + "='" + bid + "'");
		IDeliverycheckMaintain service = NCLocator.getInstance().lookup(
				IDeliverycheckMaintain.class);
		return service.queryDeliveryCheckVO(sql.toString());
	}

	/**
	 * ���� UIPanel1 ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIPanel
	 */
	private nc.ui.pub.beans.UIPanel getPnlButtons() {
		if (this.ivjPnlButtons == null) {
			this.ivjPnlButtons = new nc.ui.pub.beans.UIPanel();
			this.ivjPnlButtons.setName("PnlButtons");
			this.ivjPnlButtons.setLayout(this.getPnlButtonsFlowLayout());
			this.ivjPnlButtons.setBounds(ChgPriceDialog.iButton_X,
					ChgPriceDialog.iButton_Y, ChgPriceDialog.iButton_Width,
					ChgPriceDialog.iButton_Height);

			this.ivjPnlButtons.setMinimumSize(new java.awt.Dimension(100, 100));
			this.ivjPnlButtons.add(this.getUIButtonOk(), this.getUIButtonOk()
					.getName());
			this.ivjPnlButtons.add(this.getUIButtonCancel(), this
					.getUIButtonCancel().getName());
		}
		return this.ivjPnlButtons;
	}

	private java.awt.FlowLayout getPnlButtonsFlowLayout() {
		FlowLayout ivjPnlButtonsFlowLayout = null;
		/* �������� */
		ivjPnlButtonsFlowLayout = new java.awt.FlowLayout();
		ivjPnlButtonsFlowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
		ivjPnlButtonsFlowLayout.setVgap(5);
		ivjPnlButtonsFlowLayout.setHgap(8);
		return ivjPnlButtonsFlowLayout;
	}

	/*
	 * ��ʼ������
	 */
	private void initConnections() {
		this.getUIButtonOk().addActionListener(this.ivjEventHandler);
		this.getUIButtonCancel().addActionListener(this.ivjEventHandler);
	}

	/*
	 * ��ʼ���۸���н���
	 */
	private void initialize() {
		this.setName("ChgPriceDlg");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(ChgPriceDialog.iMainFrame_Width,
				ChgPriceDialog.iMainFrame_Height);
		this.setResizable(false);
		this.setModal(true);
		this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4006002_0", "04006002-0007")/* @res "�۸����" */);
		this.setContentPane(this.getUIDialogContentPane());
		this.initConnections();
	}
}
