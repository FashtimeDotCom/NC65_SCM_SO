package nc.ui.so.m30.billui.view.sideform;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import nc.vo.credit.billcreditquery.entity.CreditInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.util.SOMilSymbolUtil;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.m30.ref.credit.CreditServicesUtil;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.queryarea.component.QueryAreaHyperlinkButton;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.components.widget.IBesideWidgetlet;
import nc.ui.uif2.components.widget.WidgetInfo;

public class CreditSideForm extends UIPanel implements IBesideWidgetlet {

  private static final String CREDITBALANCE = "CreditBalance";

  private static final String CREDITOCCUPANCY = "CreditOccupancy";

  private static final String CREDITVALUE = "CreditValue";

  private static final long serialVersionUID = 8118632475271990452L;

  // ������Ƭ
  private SaleOrderBillForm billForm;

  private UILabel creditBalanceLabel;

  // �������ֵ
  private UIPanel creditBalancePanel;

  private UILabel creditOccupancyLabel;

  // ����ռ��ֵ
  private UIPanel creditOccupancyPanel;

  private UILabel creditValueLabel;

  // ���ö��ֵ
  private UIPanel creditValuePanel;

  // ����resetUIˢ��ʱ��������ʼ��layout
  private LayoutManager defaultLayout;

  private IExceptionHandler exceptionHandler;

  public CreditSideForm() {
    super();
    this.initUI();
    this.defaultLayout = this.getLayout();
  }

  public void display() throws Exception {
    int selectedRow =
        this.billForm.getBillCardPanel().getBillTable().getSelectedRow();
    if (selectedRow < 0) {
      /* MessageDialog
           .showErrorDlg(null, null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
               .getStrByID("4006011_0", "04006011-0041")@res "��ѡһ�������У�");*/

      String errorMsg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0041")/*@res"��ѡһ�������У�"*/;
      this.exceptionHandler.handlerExeption(new Exception(errorMsg));
      return;
    }
    // this.removeAll();
    this.notifyUpdateData(selectedRow);
  }

  public SaleOrderBillForm getBillForm() {
    return this.billForm;
  }

  @Override
  public Insets getComponentInsets() {
    return null;
  }

  @Override
  public Component getContentComponent() {
    return this;
  }

  public UIPanel getCreditBalancePanel() {
    if (this.creditBalancePanel == null) {
      this.creditBalancePanel = new UIPanel(new GridBagLayout());
      this.creditBalancePanel.setName("creditBalancePanel");
    }
    return this.creditBalancePanel;
  }

  public UIPanel getCreditOccupancyPanel() {
    if (this.creditOccupancyPanel == null) {
      this.creditOccupancyPanel = new UIPanel(new GridBagLayout());
      this.creditOccupancyPanel.setName("creditOccupancyPanel");
    }
    return this.creditOccupancyPanel;
  }

  public UIPanel getCreditValuePanel() {
    if (this.creditValuePanel == null) {
      this.creditValuePanel = new UIPanel(new GridBagLayout());
      this.creditValuePanel.setName("creditValuePanel");
    }
    return this.creditValuePanel;
  }

  public IExceptionHandler getExceptionHandler() {
    return this.exceptionHandler;
  }

  @Override
  public WidgetInfo getWidgetInfo() {
    WidgetInfo info = new WidgetInfo();
    info.setTitle(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0268")/*�ͻ�����*/);
    return info;
  }

  public void notifyUpdateData(int row) throws BusinessException {
    // ȡ������ϢVO
    CreditInfoVO creditVO = this.getCreditVO(row);
    this.removeAll();
    if (null == creditVO) {
      creditVO = new CreditInfoVO();
    }
    this.setLayout(new BorderLayout());
    this.initBorder();

    // ������ʾֵ
    Component[] parent = this.getComponents();
    UIPanel uipanel = (UIPanel) parent[0];
    Component[] cps = uipanel.getComponents();

    String corigcurrencyid = creditVO.getCorigcurrencyid();
    for (Component cp : cps) {
      if ("creditValuePanel".equals(cp.getName())) {
        UILabel label = (UILabel) this.getCreditValueLabel();
        UFDouble creditValue = creditVO.getNlimitmny();
        label.setText(this.getFormatData(corigcurrencyid, creditValue));
      }
      else if ("creditOccupancyPanel".equals(cp.getName())) {
        UILabel label = (UILabel) this.getCreditOccupancyLabel();
        UFDouble creditOccupancy = creditVO.getNengrossmny();
        label.setText(this.getFormatData(corigcurrencyid, creditOccupancy));
      }
      else if ("creditBalancePanel".equals(cp.getName())) {
        UILabel label = (UILabel) this.getCreditBalanceLabel();
        UFDouble creditBalance = creditVO.getNbalancemny();
        label.setText(this.getFormatData(corigcurrencyid, creditBalance));
      }
    }

    this.updateUI();
  }

  public void resetUI() {
    this.removeAll();
    this.setLayout(this.defaultLayout);
    this.initUI();
  }

  public void setBillForm(SaleOrderBillForm billForm) {
    this.billForm = billForm;
  }

  public void setExceptionHandler(IExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  @Override
  public void setWidgetState(int state) {
    //
  }

  private Component getCreditBalanceLabel() {
    if (this.creditBalanceLabel == null) {
      this.creditBalanceLabel = new UILabel();
      this.creditBalanceLabel.setName(CreditSideForm.CREDITBALANCE);
    }
    return this.creditBalanceLabel;
  }

  private Component getCreditOccupancyLabel() {
    if (this.creditOccupancyLabel == null) {
      this.creditOccupancyLabel = new UILabel();
      this.creditOccupancyLabel.setName(CreditSideForm.CREDITOCCUPANCY);
    }
    return this.creditOccupancyLabel;
  }

  private Component getCreditValueLabel() {
    if (this.creditValueLabel == null) {
      this.creditValueLabel = new UILabel();
      this.creditValueLabel.setName(CreditSideForm.CREDITVALUE);
    }
    return this.creditValueLabel;
  }

  private CreditInfoVO getCreditVO(int row) throws BusinessException {
    if (!SysInitGroupQuery.isCREDITEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0450")/*���������ù���ģ��*/);
    }
    CardKeyValue keyValue = new CardKeyValue(this.billForm.getBillCardPanel());
    String customerid = keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID);
    String ctranstypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    String csettleorg =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSETTLEORGID);
    CreditInfoVO creditVO = null;
    creditVO =
        CreditServicesUtil.creditQueryForSoSideHead(csettleorg, customerid,
            ctranstypeid);
    return creditVO;
  }

  private String getFormatData(String corigcurrencyid, UFDouble creditValue) {
    UFDouble newcreditValue = new UFDouble();
    ScaleUtils scaleutil =
        new ScaleUtils(AppContext.getInstance().getPkGroup());
    newcreditValue = scaleutil.adjustMnyScale(creditValue, corigcurrencyid);
    // ���ݸ�ʽ����
    return SOMilSymbolUtil.setNumFormat(newcreditValue.toString());
  }

  private void initBorder() {
    UIPanel panel = new UIPanel();
    panel.setLayout(new GridLayout(3, 1));

    // ����label�����ݵĲ���
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 100;
    constraints.weighty = 100;
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.EAST;
    // ��ʼ���߿����Ϊÿ��panel���ñ߿�
    Border etched = BorderFactory.createEtchedBorder();
    Border creditValueTitled =
        BorderFactory.createTitledBorder(etched, NCLangRes.getInstance()
            .getStrByID("4006011_0", "04006011-0265")/*���ö��*/);
    this.getCreditValuePanel().add(this.getCreditValueLabel(), constraints);
    this.getCreditValuePanel().setBorder(creditValueTitled);

    Border creditOccupancy =
        BorderFactory.createTitledBorder(etched, NCLangRes.getInstance()
            .getStrByID("4006011_0", "04006011-0266")/*����ռ��*/);
    this.getCreditOccupancyPanel().add(this.getCreditOccupancyLabel(),
        constraints);
    this.getCreditOccupancyPanel().setBorder(creditOccupancy);

    Border creditBalanceTitled =
        BorderFactory.createTitledBorder(etched, NCLangRes.getInstance()
            .getStrByID("4006011_0", "04006011-0267")/*�������*/);
    this.getCreditBalancePanel().add(this.getCreditBalanceLabel(), constraints);
    this.getCreditBalancePanel().setBorder(creditBalanceTitled);

    panel.add(this.getCreditValuePanel());
    panel.add(this.getCreditOccupancyPanel());
    panel.add(this.getCreditBalancePanel());
    this.add(panel);
  }

  private void initUI() {
    this.setPreferredSize(new Dimension(80, 120));
    this.add(new QueryAreaHyperlinkButton(new CreditSideFormAction(this,
        this.exceptionHandler)));
    this.updateUI();
  }

}
