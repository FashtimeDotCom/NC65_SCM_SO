package nc.ui.so.m32.billui.editor.orgevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.so.m32.billui.pub.CardPanelClearUtil;
import nc.ui.so.m32.billui.pub.SaleInvoicePrecision;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.BodyDefaultValue;
import nc.vo.so.m32.rule.DefaultOrgCurrRule;
import nc.vo.so.m32.rule.ExchangeRateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOPubParaUtil;
import nc.vo.uif2.LoginContext;

/**
 * 
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>���۷�Ʊ��Ʊ��֯�༭�¼�
 * </ul>
 * 
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-8-10 ����10:24:22
 */
public class OrgEditHandler implements IAppEventHandler<OrgChangedEvent> {

  private BillCardPanel billCardPanel;

  private BillForm billfrom;

  private LoginContext context;

  public OrgEditHandler(BillForm bill, LoginContext context) {
    this.billfrom = bill;
    this.billCardPanel = bill.getBillCardPanel();
    this.context = context;
  }

  @Override
  public void handleAppEvent(OrgChangedEvent e) {
    // ���ý��澫��
    SaleInvoicePrecision.getInstance().setCardPrecision(
        this.context.getPk_group(), this.billCardPanel);
    // ����ʱ�л�����֯����
    if (this.billfrom.getModel().getUiState().equals(UIState.ADD)) {
      // ��յ��ݽ�������ֵ
      CardPanelClearUtil clearutil = new CardPanelClearUtil(this.billCardPanel);
      clearutil.clearValue();

      if (PubAppTool.isNull(e.getNewPkOrg())) {
        return;
      }
      // ����Ĭ��ֵ
      this.setDefValue(e);
      this.setCardEditor();
    }
    BillPanelUtils.setOrgForAllRef(this.billfrom.getBillCardPanel(),
        this.billfrom.getModel().getContext());

  }

  /**
   * ���ü��ű�λ�һ��ʺ�ȫ�ֱ�λ����ر༭�Ժ��ֶ�ֵ
   */
  private void setCardEditor() {
    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    BillItem ortheritem =
        this.billCardPanel.getHeadItem(SaleInvoiceHVO.NGLOBALEXCHGRATE);
    // ����ȫ�ֱ�λ��

    if (null != ortheritem) {
      if (SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()) {
        ortheritem.setEdit(true);
      }
      else {
        ortheritem.setEdit(false);
        keyValue.setHeadValue(SaleInvoiceHVO.NGLOBALEXCHGRATE, null);
      }
    }
    ortheritem = this.billCardPanel.getHeadItem(SaleInvoiceHVO.NGROUPEXCHGRATE);

    String pk_group = keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP);
    // ���ü��ű�λ��
    if (null != ortheritem) {
      if (SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(pk_group)) {
        ortheritem.setEdit(true);
      }
      else {
        ortheritem.setEdit(false);
        keyValue.setHeadValue(SaleInvoiceHVO.NGROUPEXCHGRATE, null);
      }
    }
  }

  /**
   * 
   * ���������������л�����֯ʱ���õ���Ĭ��ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author fengjb
   * @time 2010-8-10 ����10:25:38
   */
  private void setDefValue(OrgChangedEvent e) {
    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    // ���ñ�ͷ��Ʊ��֯
    keyValue.setHeadValue(SaleInvoiceHVO.PK_ORG, e.getNewPkOrg());

    keyValue.setHeadValue(SaleInvoiceHVO.PK_GROUP, AppContext.getInstance()
        .getPkGroup());

    keyValue.setHeadValue(SaleInvoiceHVO.DBILLDATE, AppContext.getInstance()
        .getBusiDate());
    // ���ñ���Ĭ��ֵ
    BodyDefaultValue bodydefault = new BodyDefaultValue(keyValue);
    bodydefault.setAllDefautValue();

    // ���ñ���Ĭ��ֵ
    DefaultOrgCurrRule orgDefault = new DefaultOrgCurrRule(keyValue);
    orgDefault.setDefautOrgCurrByPk();

    // �����۱�����
    ExchangeRateRule exchangerule = new ExchangeRateRule(keyValue);
    exchangerule.calcExchangeRate();
  }
}
