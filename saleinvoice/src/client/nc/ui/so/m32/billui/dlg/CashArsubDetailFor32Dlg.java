package nc.ui.so.m32.billui.dlg;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m35.so.m32.IArsubToSaleInvoice;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListData;
import nc.ui.pub.bill.BillListPanel;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m35.entity.ArsubDetailVO;

/**
 * 
 * ���۷�Ʊ���öҸ���ϸ��Ϣ�Ի���
 * 
 * @since 6.5
 * @version 2014-08-14 15:35:09
 * @author liylr
 */
public class CashArsubDetailFor32Dlg extends UIDialog implements BillEditListener {

  private static final long serialVersionUID = -283873732522189276L;

  /**
   * �¼�������
   * 
   * @since 6.35
   * @version 2013-12-11 14:04:47
   * @author ������
   */
  class ActionHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == CashArsubDetailFor32Dlg.this.getBtnCancel()) {
        // �رհ�ťִ���¼�
        CashArsubDetailFor32Dlg.this.doCancelAction();
      }
    }
  }

  /** Ĭ�Ͽ�� */
  private static final int DEFAULTWITH = 600;

  /** Ĭ�ϸ߶� */
  private static final int DEFAUTHIGH = 450;

  /** �仯���� */
  private static final int DENOMINATOR = 8;

  /** �仯���� */
  private static final int NOLECULE = 5;

  /** ��ť��Ӧ�¼� */
  private ActionHandler actionHandler = new ActionHandler();

  /** ���۷�Ʊ��ID�� */
  private String[] invoiceBillIDs;

  /** �б�ģ�� */
  private BillListPanel arsubPanel;

  /** �رհ�ť */
  private UIButton btnCancel;

  /** ��ťpanel */
  private UIPanel btnUIPanel;

  private int decimaldigit;

  /** UI JPanel */
  private JPanel uiContentPane;

  /**
   * ���췽��
   * 
   * @param parent
   *            ��Ƭ
   * @param saleBillIDs
   *            ���۷�ƱID��
   */
  public CashArsubDetailFor32Dlg(BillCardPanel parent, String[] saleBillIDs) {
    super(parent);
    this.decimaldigit = parent.getBodyItem(SaleInvoiceBVO.NORIGSUBMNY)
        .getDecimalDigits();
    this.invoiceBillIDs = saleBillIDs;
    this.initialize();
  }

  public CashArsubDetailFor32Dlg(BillCardPanel parent, String[] saleBillIDs, boolean reset) {
    super(parent, reset);
    this.decimaldigit = parent.getBodyItem(SaleInvoiceBVO.NORIGSUBMNY)
        .getDecimalDigits();
    this.invoiceBillIDs = saleBillIDs;
    this.initialize();
  }

  @Override
  public void afterEdit(BillEditEvent e) {
    //
  }

  @Override
  public void bodyRowChange(BillEditEvent e) {
    //
  }

  /**
   * ȡ����ť����
   */
  public void doCancelAction() {
    this.closeCancel();
  }

  /**
   * �رհ�ť
   * 
   * @return ��ť
   */
  public UIButton getBtnCancel() {
    if (null == this.btnCancel) {
      this.btnCancel = new UIButton();
      this.btnCancel.setName("btnCancel");

      this.btnCancel.setText(NCLangRes.getInstance().getStrByID("40060501", "1400605010000")/*�ر�*/);
    }
    return this.btnCancel;
  }

  /**
   * ��ť����
   * 
   * @return panel
   */
  public UIPanel getBtnUIPanel() {
    if (this.btnUIPanel == null) {
      this.btnUIPanel = new nc.ui.pub.beans.UIPanel();
      this.btnUIPanel.setName("BtnUIPanel");
      this.btnUIPanel.add(this.getBtnCancel(), this.getBtnCancel()
          .getName());
    }
    return this.btnUIPanel;
  }

  /**
   * JPanel
   * 
   * @return JPanel
   */
  public JPanel getUiContentPane() {
    return this.uiContentPane;
  }

  /**
   * �رհ�ťsetter
   * 
   * @param btnCancel
   *            �رհ�ť
   */
  public void setBtnCancel(UIButton btnCancel) {
    this.btnCancel = btnCancel;
  }

  /**
   * ��ť����
   * 
   * @param btnUIPanel
   *            ����
   */
  public void setBtnUIPanel(UIPanel btnUIPanel) {
    this.btnUIPanel = btnUIPanel;
  }

  /**
   * UiContent
   * 
   * @param uiContentPane
   *            UiContent
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
   * �������۷��õ��б�Panel
   * 
   * @return �б�ģ��
   */
  private BillListPanel getArSubPanel() {
    if (null == this.arsubPanel) {
      this.arsubPanel = new BillListPanel();
      this.arsubPanel.setName("ArSubPanel");
      this.arsubPanel.setToolTipText(NCLangRes.getInstance().getStrByID("40060501", "1400605010037")/*���öҸ���ϸ*/);
      String operator = AppContext.getInstance().getPkUser();
      String pk_group = AppContext.getInstance().getPkGroup();
      this.arsubPanel.loadTemplet("40060501", null, operator, pk_group,
          "32cash35");

      this.arsubPanel.getHeadTable().setSelectionMode(
          javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      this.arsubPanel.getHeadTable().setEnabled(true);
      this.arsubPanel.getChildListPanel().setTotalRowShow(true);
      this.arsubPanel.setEnabled(false);
    }
    return this.arsubPanel;
  }

  private Container getUIContentPane() {
    if (null == this.uiContentPane) {
      this.uiContentPane = new JPanel();
      this.uiContentPane.setName("UIDialogContentPane");
      this.uiContentPane.setLayout(new BorderLayout());

      this.getUIContentPane().add(this.getArSubPanel(), "Center");
      this.getUIContentPane().add(this.getBtnUIPanel(), "South");

    }
    return this.uiContentPane;
  }

  /**
   * ��ʼ������
   */
  private void initialize() {
    this.setName("ArsubDlg");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    Dimension dd = Toolkit.getDefaultToolkit().getScreenSize();
    int w = CashArsubDetailFor32Dlg.DEFAULTWITH;
    int h = CashArsubDetailFor32Dlg.DEFAUTHIGH;
    if (dd != null) {
      w = (int) dd.getWidth() * CashArsubDetailFor32Dlg.NOLECULE
          / CashArsubDetailFor32Dlg.DENOMINATOR;
      h = (int) dd.getHeight() * CashArsubDetailFor32Dlg.NOLECULE
          / CashArsubDetailFor32Dlg.DENOMINATOR;
    }
    w = w > CashArsubDetailFor32Dlg.DEFAULTWITH || w <= 0 ? CashArsubDetailFor32Dlg.DEFAULTWITH
        : w;
    h = h > CashArsubDetailFor32Dlg.DEFAUTHIGH || h <= 0 ? CashArsubDetailFor32Dlg.DEFAUTHIGH
        : h;
    this.setSize(w, h);
    this.setResizable(true);
    this.setTitle(NCLangRes.getInstance().getStrByID("40060501", "1400605010037")/*���öҸ���ϸ*/);
    this.setContentPane(this.getUIContentPane());
    // ��Ӽ���
    this.addActionListener();
    // ��ʼ������
    this.queryAndLoadArsub();
  }

  private void queryAndLoadArsub() {

    // ��ѯ���۷��õ�
    ArsubDetailVO[] bills = null;
    IArsubToSaleInvoice service = NCLocator.getInstance().lookup(IArsubToSaleInvoice.class);
    try {
      bills = service.queryCashArsubDetailVO(this.invoiceBillIDs);
      
    } catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    // ����ģ�����ݿ���
    BillListData billlistdata = this.getArSubPanel().getBillListData();
    BillItem item = billlistdata.getBodyBillModel().getItemByKey(
        ArsubDetailVO.NDETAILSUBMNY);
    item.setDecimalDigits(this.decimaldigit);
    billlistdata.setBodyValueVO(bills);
    billlistdata.getBodyBillModel().loadLoadRelationItemValue();
    billlistdata.getBodyBillModel().execLoadFormula();
  }

}

