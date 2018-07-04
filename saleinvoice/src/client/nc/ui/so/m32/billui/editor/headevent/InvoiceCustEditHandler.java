package nc.ui.so.m32.billui.editor.headevent;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.m32.billui.rule.CustBankAccRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.pub.SaleInvoiceKeyRela;
import nc.vo.so.m32.rule.ExchangeRateRule;
import nc.vo.so.m32.rule.PriceMnyClearRule;
import nc.vo.so.m32.rule.VATDefaultRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ��ͷ��Ʊ�ͻ��༭�¼�����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-3-1 ����03:34:43
 */
public class InvoiceCustEditHandler {
  
  
  
  /**
   * �༭ǰ�¼�
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e){
    IKeyValue keyValue = new CardKeyValue( e.getBillCardPanel());
    // ���ÿ�Ʊ�ͻ�Լ������
    BillItem invoicecust =
        e.getBillCardPanel().getHeadItem(SaleInvoiceHVO.CINVOICECUSTID);
    FilterCustomerRefUtils custfilter = new FilterCustomerRefUtils(invoicecust);
    custfilter.filterRefByFrozenFlag(UFBoolean.FALSE);
    custfilter.filterItemRefByOrg(keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG));
  }

  /**
   * �����������������۷�Ʊ��ͷ��Ʊ�ͻ��༭���¼���
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-27 ����01:59:43
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();

    String invoicecust =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID);
    String pk_org = keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    String oldcurrency =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID);

    // --�ͻ�Ĭ�Ͽ�������
    CustBankAccRule bankaccrule = new CustBankAccRule(cardPanel);
    bankaccrule.setDefCustBankAcc();

    String currency =
        CustomerPubService.getDefaultCurrtype(invoicecust, pk_org);
    CardPanelCalculator calc = new CardPanelCalculator(cardPanel);
    // ���ԭ����ԭ�Һ����»�ȡ����Ĭ�Ͻ��ױ��ֲ�ͬ�������»�ȡ�۱�����
    if (null != currency && !currency.equals(oldcurrency)) {
      // ����ԭ������
      UFDouble oldExchgrate =
          keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NEXCHANGERATE);
      if (null == oldExchgrate) {
        oldExchgrate = UFDouble.ZERO_DBL;
      }
      // �����±���
      keyValue.setHeadValue(SaleInvoiceHVO.CORIGCURRENCYID, currency);
      
      String ccurrencyid =
          keyValue.getHeadStringValue(SaleInvoiceHVO.CCURRENCYID);
      
      if (!PubAppTool.isEqual(ccurrencyid, currency)) {
        // ��ձ��嵥�۽���ֶ�
        PriceMnyClearRule clearrule = new PriceMnyClearRule(keyValue);
        clearrule.clearAllBodyItem();
      }
      
      // ��ȡ�µ��۱�����
      ExchangeRateRule chgraterule = new ExchangeRateRule(keyValue);
      chgraterule.calcExchangeRate();
      UFDouble newExchgrate =
          keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NEXCHANGERATE);
      // �۱����ʱ仯�����¼���
      if (!VOChecker.isEmpty(newExchgrate)
          && newExchgrate.compareTo(oldExchgrate) != 0) {
        calc.calculateAll(SaleInvoiceHVO.NEXCHANGERATE);
      }

    }

    // ��. ѯ˰
    SaleInvoiceKeyRela keyrala = new SaleInvoiceKeyRela();
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue, keyrala);
    taxInfo.setTaxInfoByHeadPos(rows);
    int[] ratechgrows = taxInfo.getTaxChangeRows();
    calc.calculate(ratechgrows, SaleInvoiceBVO.NTAXRATE);

    VATDefaultRule vatrule = new VATDefaultRule(keyValue);
    vatrule.setCustvatCode();

    // 3.���¼��������е�VAT��Ϣ
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    vatcal.calVatAll();

  }

}
