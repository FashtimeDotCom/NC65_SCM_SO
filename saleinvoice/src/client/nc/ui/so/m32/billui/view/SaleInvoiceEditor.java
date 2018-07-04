package nc.ui.so.m32.billui.view;

import java.util.List;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.BillOrgPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.CardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.listener.SOBillTotalListener;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.HeadDefaultValue;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOPubParaUtil;

public class SaleInvoiceEditor extends ShowUpableBillForm {

  // ���岻����༭�ֶ�:���ϻ������ࡢ����λ�����ű�����˰�����ű��Ҽ�˰�ϼ�
  // ȫ�ֱ�����˰��ȫ�ֱ��Ҽ�˰�ϼơ�������֯�������֯���ֿ�
  // ���ǰ��� �����ó�ֽ������Һ�˰���ۡ���������˰����
  // �����Һ�˰���ۡ���������˰���ۡ����Һ�˰����
  // ������˰���ۡ����Һ�˰���ۡ�������˰����
  // ����˰�������˰�����Ҽ�˰�ϼơ������ۿ۶�
  // �ɱ����ۼ�Ӧ��δ�����������ۼƳ�������
  // �ۼ�ȷ��Ӧ���������ۼ�ȷ��Ӧ�ս��ۼƳɱ���������
  // �ۼ��տ���Ĵ湩Ӧ�̡����Ļ��ܡ���Ʊ����
  // ���Ļ�����������Դ����������Դ�����ӱ�
  // ���۷�Ʊ���������۷�Ʊ�ӱ�����
  // Դͷ��������Դͷ�����ӱ�
  private static final String[] NOEDITBODYKEY = new String[] {
    // �������OID
    SaleInvoiceBVO.CARORGID, SaleInvoiceBVO.CARORGVID,
    SaleInvoiceBVO.CPROFITCENTERID, SaleInvoiceBVO.CSENDSTOCKORGID,
    SaleInvoiceBVO.CDEPTID,

    SaleInvoiceBVO.CMARBASCALSSID, SaleInvoiceBVO.CUNITID,
    SaleInvoiceBVO.NGROUPMNY, SaleInvoiceBVO.NGROUPTAXMNY,
    SaleInvoiceBVO.NGLOBALMNY, SaleInvoiceBVO.NGLOBALTAXMNY,
    SaleInvoiceBVO.CSALEORGID, SaleInvoiceBVO.CSENDSTOCKORGID,
    SaleInvoiceBVO.CSENDSTORDOCID, "nbforigsubmny", SaleInvoiceBVO.NORIGSUBMNY,
    SaleInvoiceBVO.NTAXPRICE, SaleInvoiceBVO.NPRICE,
    SaleInvoiceBVO.NTAXNETPRICE, SaleInvoiceBVO.NNETPRICE,
    SaleInvoiceBVO.NQTTAXPRICE, SaleInvoiceBVO.NQTPRICE,
    SaleInvoiceBVO.NQTTAXNETPRICE, SaleInvoiceBVO.NQTNETPRICE,
    SaleInvoiceBVO.NDISCOUNT, SaleInvoiceBVO.NORIGDISCOUNT,
    SaleInvoiceBVO.NSHOULDOUTNUM, SaleInvoiceBVO.NTOTALOUTNUM,
    SaleInvoiceBVO.NTOTALINCOMENUM, SaleInvoiceBVO.NTOTALINCOMEMNY,
    SaleInvoiceBVO.NTOTALCOSTNUM, SaleInvoiceBVO.NTOTALPAYMNY,
    SaleInvoiceBVO.CVMIVENDERID, SaleInvoiceBVO.CSUMID,
    SaleInvoiceBVO.DBILLDATE, SaleInvoiceBVO.CSRCID, SaleInvoiceBVO.CSUMID,
    SaleInvoiceBVO.CSRCBID, SaleInvoiceBVO.CSALEINVOICEID,
    SaleInvoiceBVO.CSALEINVOICEBID, SaleInvoiceBVO.CFIRSTBID,
    SaleInvoiceBVO.CFIRSTID, SaleInvoiceBVO.SRCBTS, SaleInvoiceBVO.SRCTS,
    SaleInvoiceBVO.TS, SaleInvoiceBVO.VFIRSTCODE, SaleInvoiceBVO.VFIRSTROWNO,
    SaleInvoiceBVO.VSRCCODE, SaleInvoiceBVO.VSRCROWNO,
    SaleInvoiceBVO.NITEMDISCOUNTRATE, SaleInvoiceBVO.NDISCOUNTRATE
  };

  // ���۷�Ʊ��ͷ������༭�ֶ�:���ҡ�����������˰�ϼơ�
  // �Ƿ񴫽�˰������˰ʱ�䡢�Գ��־�� �Գ巢ƱHID���Գ巢Ʊ�š�����״̬,��ַ�Ʊ��־
  private static final String[] NOEDITHEADKEY = new String[] {
    SaleInvoiceHVO.CBIZTYPEID, SaleInvoiceHVO.CCURRENCYID,
    SaleInvoiceHVO.CCUSTBANKID, SaleInvoiceHVO.NTOTALASTNUM,
    SaleInvoiceHVO.NTOTALORIGMNY, SaleInvoiceHVO.VGOLDTAXCODE,
    SaleInvoiceHVO.BTOGOLDTAXFLAG, SaleInvoiceHVO.TGOLDTAXTIME,
    SaleInvoiceHVO.FOPPOSEFLAG, SaleInvoiceHVO.COPPOSESRCID,
    SaleInvoiceHVO.VOPPOSESRCCODE, SaleInvoiceHVO.FSTATUSFLAG,
    SaleInvoiceHVO.FOPPOSEFLAG,

    SaleInvoiceHVO.DMAKEDATE, SaleInvoiceHVO.APPROVER,
    SaleInvoiceHVO.TAUDITTIME, SaleInvoiceHVO.CREATOR, SaleInvoiceHVO.MODIFIER,
    SaleInvoiceHVO.MODIFIEDTIME, SaleInvoiceHVO.CREATIONTIME,
    SaleInvoiceHVO.BILLMAKER

  };

  /**
   * 
   */
  private static final long serialVersionUID = -4898097866857994181L;

  List<String> clearHyperlink;

  // ��Ʊ�༭��������
  private CardEditSetter editsetter = new CardEditSetter();

  // �������۷��õ�������۶�����������
  private OffsetTempVO tempvo;

  // ���۷�Ʊ����֯������Ҫ��������ʾ����
  private BillOrgPanel billOrgPanel;

  public List<String> getClearHyperlink() {
    return this.clearHyperlink;
  }

  public OffsetTempVO getTempvo() {
    return this.tempvo;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.view.ShowUpableBillForm#initUI()
   */
  @Override
  public void initUI() {
    super.initUI();
    BillCardPanel billcard = this.getBillCardPanel();
    billcard.getBodyPanel().setTotalRowShow(true);
    IKeyValue keyValue = new CardKeyValue(billcard);

    SOBillTotalListener totallis = new SOBillTotalListener(keyValue);
    billcard.getBillModel().addTotalListener(totallis);
    this.initEditorData();
    // �����Ƭ������
    this.clearHyperlink();

    this.initFillEnabled(billcard);
  }

  @Override
  public BillOrgPanel getBillOrgPanel() {
    if (null == this.billOrgPanel && this.isShowOrgPanel()) {
      this.billOrgPanel = this.createDefaultBillOrgPanel();
      super.setBillOrgPanel(this.billOrgPanel);
    }
    return this.billOrgPanel;

  }

  private BillOrgPanel createDefaultBillOrgPanel() {
    BillOrgPanel orgPanel = new BillOrgPanel();
    orgPanel.setLabelName(NCLangRes.getInstance().getStrByID("4006008_0",
        "04006008-0085")/* ��Ʊ��֯ */);
    orgPanel.setModel(this.getModel());
    orgPanel.initUI();
    return orgPanel;
  }

  /**
   * ���ÿ�Ƭ�༭��
   */
  public void setCardEditEnable() {
    SaleInvoiceManageModel model = (SaleInvoiceManageModel) this.getModel();
    this.editsetter.setEditEnable(this.getBillCardPanel(),
        model.getCombinCacheVO());
  }

  public void setClearHyperlink(List<String> clearHyperlink) {
    this.clearHyperlink = clearHyperlink;
  }

  public void setTempvo(OffsetTempVO tempvo) {
    this.tempvo = tempvo;
  }

  /**
   * ���෽����д ��ֹ�༭��Ʊ��֯
   */
  @Override
  public void showMeUp() {
    super.showMeUp();
    this.getBillOrgPanel().setEnabled(false);

  }

  @Override
  protected void onAdd() {
    super.onAdd();
    this.setTempvo(null);
    this.setCardEditEnable();
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.editor.BillForm#onEdit()
   */
  @Override
  protected void onEdit() {
    super.onEdit();
    this.setTempvo(null);
  }

  /**
   * ���෽����д ����ʱ�������Ĭ�Ͽ�Ʊ��֯��Ҫ����Ĭ��ֵ
   */
  @Override
  protected void setDefaultValue() {

    CardKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    // ���ñ�ͷĬ��ֵ
    HeadDefaultValue headdefault =
        new HeadDefaultValue(keyValue, this.getModel().getContext());
    headdefault.setDefaultValue();
    // ��������
    keyValue.setHeadValue(SaleInvoiceHVO.DBILLDATE, AppContext.getInstance()
        .getBusiDate());

    String pk_org = keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    if (StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }

    // // ���ñ���Ĭ��ֵ(��������֯�ı��¼�����)
    // BodyDefaultValue bodydefault = new BodyDefaultValue(keyValue);
    // bodydefault.setAllDefautValue();
    // // ���ñ���Ĭ��ֵ(��������֯�ı��¼�����)
    // DefaultOrgCurrRule orgDefault = new DefaultOrgCurrRule(keyValue);
    // orgDefault.setDefautOrgCurrByPk();
    //
    // // �����۱�����
    // ExchangeRateRule exchangerule = new ExchangeRateRule(keyValue);
    // exchangerule.calcExchangeRate();
  }

  private void clearHyperlink() {
    for (String key : this.getClearHyperlink()) {
      BillItem item = this.getBillCardPanel().getBillData().getHeadItem(key);
      // item.setHyperlink(false);
      item.getCaptionLabel().setHyperlinkLabel(false);
    }
  }

  /**
   * ���۷�Ʊ��Ƭ�����ʼ���ܷ�༭����
   */
  private void initEditEnable() {

    // ��ͷ���ɱ༭��
    for (String key : SaleInvoiceEditor.NOEDITHEADKEY) {
      BillItem headitem = this.getBillCardPanel().getHeadTailItem(key);
      if (null != headitem) {
        headitem.setEdit(false);
      }
    }
    // ���岻�ɱ༭��
    for (String key : SaleInvoiceEditor.NOEDITBODYKEY) {
      BillItem bodyitem = this.getBillCardPanel().getBodyItem(key);
      if (null != bodyitem) {
        bodyitem.setEdit(false);
      }
    }
    BillItem headitem =
        this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.NGLOBALEXCHGRATE);
    if (null != headitem) {
      if (SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()) {
        headitem.setEdit(true);
      }
      else {
        headitem.setEdit(false);
      }
    }
    headitem =
        this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.NGROUPEXCHGRATE);
    if (null != headitem) {
      if (SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(
          AppContext.getInstance().getPkGroup())) {
        headitem.setEdit(true);
      }
      else {
        headitem.setEdit(false);
      }
    }
  }

  private void initEditorData() {
    // ��ʼ���༭��
    this.initEditEnable();
    // ����༭��
    // this.editsetter = new CardEditSetter();
    this.editsetter.cacheEditEnable(this.getBillCardPanel());
  }

  /**
   * ��ʼ������ı༭��
   * 
   * @param cardPanel
   */
  private void initFillEnabled(BillCardPanel cardPanel) {
    BillCardPanelUtils util = new BillCardPanelUtils(cardPanel);
    util.disableItemsFill();
    util.enableItemsFill(SOConstant.SALEINVOICEFILLENABLEDKEY);
    // �Զ�����������༭
    for (int i = 1; i < 21; i++) {
      BillItem bodyitem =
          this.getBillCardPanel().getBodyItem(SOConstant.VBDEF + i);
      bodyitem.setFillEnabled(true);
    }

  }
}
