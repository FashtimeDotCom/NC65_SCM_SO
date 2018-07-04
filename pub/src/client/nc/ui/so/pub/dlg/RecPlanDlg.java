package nc.ui.so.pub.dlg;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillListData;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.so.pub.precision.RecPlanPrecision;
import nc.vo.so.entry.RecPlanVO;

/**
 * �տ�ƻ��Ի���
 * 
 * @since 6.0
 * @version 2011-7-1 ����03:34:12
 * @author ô��
 */
public class RecPlanDlg extends UIDialog implements BillEditListener {

	/**
	 * 
	 * �¼�����
	 * 
	 * @since 6.0
	 * @version 2010-12-10 ����12:35:14
	 * @author ô��
	 */
	class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == RecPlanDlg.this.getBtnCancel()) {
				// ȡ����ťִ���¼�
				RecPlanDlg.this.doCancelAction();
			}
		}
	}

	/** Ĭ�Ͽ�� */
	private static final int DEFAULTWITH = 900;

	/** Ĭ�ϸ߶� */
	private static final int DEFAUTHIGH = 700;

	/** �仯���� */
	private static final int DENOMINATOR = 8;

	/** �仯���� */
	private static final int NOLECULE = 7;

	private static final long serialVersionUID = 2421981094802478580L;

	/** ��ť��Ӧ�¼� */
	private ActionHandler actionHandler = new ActionHandler();

	/** �رհ�ť */
	private UIButton btnCancel;

	/** ��ťpanel */
	private UIPanel btnUIPanel;

	/** �б�ģ�� */
	private BillListPanel recplanPanel;

	/** UI JPanel */
	private JPanel uiContentPane;

	/** ����ID */
	private RecPlanVO[] viewvo;

	/**
	 * ���췽��
	 * 
	 * @param parent
	 *            ����
	 * @param viewvo
	 *            �տ�ƻ�vo
	 */
	public RecPlanDlg(BillCardPanel parent, RecPlanVO[] viewvo) {
		super(parent);
		this.viewvo = viewvo;
		this.initialize();
	}

	public RecPlanDlg(BillCardPanel parent, RecPlanVO[] viewvo, boolean reset) {
		super(parent, reset);
		this.viewvo = viewvo;
		this.initialize();
	}

	@Override
	public void afterEdit(BillEditEvent e) {
		return;
	}

	@Override
	public void bodyRowChange(BillEditEvent e) {
		return;
	}

	/**
	 * ȡ����ť����
	 */
	public void doCancelAction() {
		this.closeCancel();
	}

	/**
	 * ȡ����ťgetter
	 * 
	 * @return ȡ����ť
	 */
	public UIButton getBtnCancel() {
		if (null == this.btnCancel) {
			this.btnCancel = new UIButton();
			this.btnCancel.setName("btnCancel");
			this.btnCancel.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("4006004_0", "04006004-0000")/* @res "�ر�" */);
		}
		return this.btnCancel;
	}

	/**
	 * ��ťpannelgetter
	 * 
	 * @return ��ťpannel
	 */
	public UIPanel getBtnUIPanel() {
		if (this.btnUIPanel == null) {
			this.btnUIPanel = new nc.ui.pub.beans.UIPanel();
			this.btnUIPanel.setName("BtnUIPanel");
			// this.btnUIPanel.add(this.getBtnCancel(),
			// this.getBtnCancel().getName());
		}
		return this.btnUIPanel;
	}

	/**
	 * ����UIģ��
	 * 
	 * @return JPanel
	 */
	public JPanel getUiContentPane() {
		if (null == this.uiContentPane) {
			this.uiContentPane = new JPanel();
			this.uiContentPane.setName("UIDialogContentPane");
			this.uiContentPane.setLayout(new BorderLayout());

			this.getUIContentPane().add(this.getProfitPanel(), "Center");
			this.getUIContentPane().add(this.getBtnUIPanel(), "South");

		}
		return this.uiContentPane;
	}

	/**
	 * ȡ����ťsetter
	 * 
	 * @param btnCancel
	 *            ȡ����ť
	 */
	public void setBtnCancel(UIButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	/**
	 * ��ťpanel setter
	 * 
	 * @param btnUIPanel
	 *            ��ťpanel
	 */
	public void setBtnUIPanel(UIPanel btnUIPanel) {
		this.btnUIPanel = btnUIPanel;
	}

	/**
	 * BillListPanel setter
	 * 
	 * @param recplanPanel
	 *            �б�ģ��
	 */
	public void setProfitPanel(BillListPanel recplanPanel) {
		this.recplanPanel = recplanPanel;
	}

	/**
	 * UI content
	 * 
	 * @param uiContentPane
	 *            JPanel
	 */
	public void setUiContentPane(JPanel uiContentPane) {
		this.uiContentPane = uiContentPane;
	}

	/**
	 * ����¼�����
	 */
	private void addActionListener() {
		this.getBtnCancel().addActionListener(this.actionHandler);
	}

	/**
	 * ����ë�������б�Panel
	 * 
	 * @return �б�ģ��
	 */
	private BillListPanel getProfitPanel() {
		if (null == this.recplanPanel) {
			this.recplanPanel = new BillListPanel();
			this.recplanPanel.setName("profitPanel");
			this.recplanPanel
					.setToolTipText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
							.getStrByID("4006004_0", "04006004-0002")/*
																	 * @res
																	 * "�տ�ƻ�"
																	 */);
			String operator = WorkbenchEnvironment.getInstance().getLoginUser()
					.getPrimaryKey();
			String pk_group = WorkbenchEnvironment.getInstance().getGroupVO()
					.getPk_group();
			this.recplanPanel.loadTemplet("40060301", null, operator, pk_group,
					"recplan");

			this.recplanPanel.addBodyEditListener(this);
			this.recplanPanel.getHeadTable().setSelectionMode(
					javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			this.recplanPanel.getHeadTable().setEnabled(true);
			this.recplanPanel.setMultiSelect(false);
			this.recplanPanel.setEnabled(true);
			recplanPanel.getBodyScrollPane("recvplan").setBBodyMenuShow(false);
		}
		return this.recplanPanel;
	}

	private javax.swing.JPanel getUIContentPane() {
		if (null == this.uiContentPane) {
			this.uiContentPane = new JPanel();
			this.uiContentPane.setName("UIDialogContentPane");
			this.uiContentPane.setLayout(new BorderLayout());

			this.getUIContentPane().add(this.getProfitPanel(), "Center");
			this.getUIContentPane().add(this.getBtnUIPanel(), "South");
		}
		return this.uiContentPane;
	}

	/**
	 * ��ʼ������
	 */
	private void initialize() {
		this.setName("RecPlanDlg");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Dimension dd = Toolkit.getDefaultToolkit().getScreenSize();
		int w = RecPlanDlg.DEFAULTWITH;
		int h = RecPlanDlg.DEFAUTHIGH;
		if (dd != null) {
			w = (int) dd.getWidth() * RecPlanDlg.NOLECULE
					/ RecPlanDlg.DENOMINATOR;
			h = (int) dd.getHeight() * RecPlanDlg.NOLECULE
					/ RecPlanDlg.DENOMINATOR;
		}
		w = w > RecPlanDlg.DEFAULTWITH || w <= 0 ? RecPlanDlg.DEFAULTWITH : w;
		h = h > RecPlanDlg.DEFAUTHIGH || h <= 0 ? RecPlanDlg.DEFAUTHIGH : h;
		this.setSize(w, h);
		this.setResizable(true);
		this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4006004_0", "04006004-0002")/* @res "�տ�ƻ�" */);
		this.setContentPane(this.getUIContentPane());
		// ��Ӽ���
		this.addActionListener();
		// ��ʼ������
		this.queryAndLoadProfit();
	}

	/**
	 * ��ѯ����ë������
	 */
	private void queryAndLoadProfit() {
		// ���ý��澫��
		String pk_group = WorkbenchEnvironment.getInstance().getGroupVO()
				.getPk_group();

		// ����ģ�����ݿ���
		BillListData billlistdata = this.getProfitPanel().getBillListData();
		RecPlanPrecision.getInstance().setModelPrecision(pk_group,
				billlistdata.getBodyBillModel());
		billlistdata.setBodyValueVO(this.viewvo);
		billlistdata.getBodyBillModel().loadLoadRelationItemValue();
		billlistdata.getBodyBillModel().execLoadFormula();
	}
}
