package nc.ui.so.m30.billui.editor.headevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.so.m30.IQueryRelationOrg;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.so.m30.billui.rule.AssociateConstractRule;
import nc.ui.so.m30.billui.rule.ClearBodyValueRule;
import nc.ui.so.m30.billui.rule.CustBankAccRule;
import nc.ui.so.m30.billui.rule.MatchLargessRule;
import nc.ui.so.m30.billui.rule.SrcTypeRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.rule.PayTermRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * �����ͻ��༭�¼�
 * 
 * @since 6.0
 * @version 2011-6-8 ����10:57:10
 * @author fengjb
 */
@SuppressWarnings("restriction")
public class CustomerEditHandler {

  private SaleOrderBillForm billform;

  /**
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String oldCorigcurrencyid =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    // ����ǿ���
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();

    // �ͻ���ص�Ĭ��ֵ
    keyValue.setHeadValue(SaleOrderHVO.CHRECEIVECUSTID, e.getValue());
    SOCustRelaDefValueRule defrule = new SOCustRelaDefValueRule(keyValue);
    defrule.setCustRelaDefValue();

    // ��ͷ������ջ���ַ
    String custid = (String) e.getValue();
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String[] defadds = CustomerPubService.getDefaultAddresses(new String[] {
      custid
    }, pk_org);
    String defaddValue = null;
    if (null != defadds && defadds.length > 0) {
      defaddValue = defadds[0];
    }
    keyValue.setHeadValue(SaleOrderHVO.CHRECEIVEADDID, defaddValue);
    for (int row : rows) {
      keyValue.setBodyValue(row, SaleOrderBVO.CRECEIVEADDRID, defaddValue);
    }

    // ����Ĭ���ջ��ͻ���ַ
    String creceiveaddrid =
        keyValue.getHeadStringValue(SaleOrderHVO.CHRECEIVEADDID);
    for (int index : rows) {
      cardPanel.getBillModel().loadLoadRelationItemValue(index,
          SaleOrderBVO.CRECEIVECUSTID);
      keyValue.setBodyValue(index, SaleOrderBVO.CRECEIVEADDRID, creceiveaddrid);
    }

    // ���ñ����ջ��ص㣬�ջ�����
    ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue);
    defaddrule.setCustDefaultAddress(rows);

    // --�����տ�Э����Ϣ
    PayTermRule payTermRule = new PayTermRule(keyValue);
    payTermRule.setPayTermInfo();
    // --�ͻ�Ĭ�Ͽ�������
    CustBankAccRule bankaccrule = new CustBankAccRule(cardPanel);
    bankaccrule.setDefCustBankAcc();

    // ����ֻ�п��в��ü���
    if (rows.length == 0) {
      return;
    }
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    if (defrule.isDiscountRateChg()) {
      calculator.calculate(rows, SaleOrderBVO.NDISCOUNTRATE);
    }
    
    // 3.���ý��������֯��Ӧ����֯����������
    SaleOrgRelationRule relarule = new SaleOrgRelationRule(keyValue);
    relarule.setFinanceOrg(rows);

    // 4.����ÿ����֯��λ��
    SOCurrencyRule currule = new SOCurrencyRule(keyValue);
    currule.setCurrency(rows);
    // 5.����������۱�����
    SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
    exraterule.calcBodyExchangeRates(rows);
    // 6.ȫ�ֱ�λ�һ���
    SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
    globalraterule.calcGlobalExchangeRate(rows);

    // 7.���ű�λ�һ���
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    groupraterule.calcGroupExchangeRate(rows);

    int[] changerows = exraterule.getRateChangeRow();
    calculator.calculate(changerows, SaleOrderBVO.NEXCHANGERATE);

    // 8.������Ϣ
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(rows);

    // 9.�������͡�����ó��
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
    // 10. ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] ratechgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(ratechgrows, SaleOrderBVO.NTAXRATE);

    // 11.ѯ��
    String trantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext clientcontex = this.billform.getM30ClientContext();
    M30TranTypeVO trantypevo =
        clientcontex.getTransType(trantypecode, pk_group);
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, trantypevo);
    ClearBodyValueRule clearrule = new ClearBodyValueRule(keyValue, null);
    String newCorigcurrencyid =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    // ����û�иı�����Ҫ��ռ۸������������ֶ� modify by zhangby5
    //���ݰ�����Ŀ �ι�ǿ Ҫ�� ȥ��ѡ���ͷ�ͻ�ʱ������������յĲ���
/*    boolean isCurrencyChange =
        !PubAppTool.isEqual(newCorigcurrencyid, oldCorigcurrencyid);
    if (isCurrencyChange) {
      clearrule.clearAllFfileKey(rows);
    }
    if (isFindPrice(config, keyValue)) {
      FindSalePrice findprice = new FindSalePrice(cardPanel, config);
      findprice.findPriceAfterEdit(rows, SaleOrderHVO.CCUSTOMERID);
    }
    else {
      if (isCurrencyChange) {
        clearrule.clearAllPriceKey(rows);
      }
    }*/

    // 12.������ͬ
    AssociateConstractRule asctrule =
        new AssociateConstractRule(cardPanel, trantypevo);
    asctrule.associateCT(rows);

    // 13.��������ƥ������
    MatchLargessRule matchlarrule = new MatchLargessRule(cardPanel, trantypevo);
    matchlarrule.matchLargess(rows);

    // ���÷�����������
    this.setCsprofitcenterID(billform, keyValue, rows);

    for (int row : rows) {
      // ������ǰ���
      UFDouble norigtaxmny =
          keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
      UFDouble norigsubmny =
          keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGSUBMNY);
      keyValue.setBodyValue(row, SaleOrderBVO.NBFORIGSUBMNY,
          MathTool.add(norigtaxmny, norigsubmny));
    }

    // 14.��ͷ��˰�ϼ�
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

    // 15.�༭�ͻ������ÿͻ�������(V63�¼�)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setCustMaterial(rows);
  }

  /**
   * 
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SrcTypeRule srcrule = new SrcTypeRule(keyValue);
    e.setReturnValue(Boolean.valueOf(!srcrule.isBillSrcCT()));

    // �����ͻ�
    BillItem customeritem = cardPanel.getHeadTailItem(SaleOrderHVO.CCUSTOMERID);
    FilterCustomerRefUtils filterutils =
        new FilterCustomerRefUtils(customeritem);
    filterutils.filterRefByFrozenFlag(UFBoolean.FALSE);
  }

  /**
   * 
   * 
   * @return billform
   */
  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  /**
   * 
   * 
   * @param billform
   */
  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }

  private boolean isFindPrice(SaleOrderFindPriceConfig config,
      IKeyValue keyValue) {

    // 1.ѯ�۲���,�ж��Ƿ�ѯ��
    Integer askrule = config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
      return false;
    }
    // 2.�༭�ֶ��Ƿ񴥷�ѯ��
    if (!this.isTrigFindPrice(SaleOrderHVO.CORIGCURRENCYID, keyValue)) {
      return false;
    }

    return true;
  }

  private boolean isTrigFindPrice(String editkey, IKeyValue keyValue) {
    // �ж�����Ǽ۸��� �ʹ���ѯ��
    if (editkey.equals(SOItemKey.CPRICEITEMID)) {
      return true;
    }
    // ����ѯ�۴����������ж��Ƿ�ѯ��
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String[] so21 = null;

    so21 = SOSysParaInitUtil.getSO21(pk_org);

    // ѯ�۴�������Ϊ��
    if (null == so21 || so21.length == 0) {
      return false;
    }
    for (String condition : so21) {
      if (editkey.equals(condition)) {
        return true;
      }
    }
    return false;
  }

  /**
   * ��ѯ���������֯�����������֯ID��Ӧ����֯ID����������ID��Ĭ��������֯��ֱ�˲�
   * 
   * @param keyValue
   * @param rows
   * @return
   */
  private Map<String, String[]> GetRelationOrg(IKeyValue keyValue, int[] rows) {

    Map<String, String[]> hmRelationOrgid = null;
    // ��֯���ͻ����������͡����ϲ���׼��
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String ccustomerid = keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);

    List<String> alMaterialid = new ArrayList<String>();

    for (int row : rows) {
      String cmaterialid =
          keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (PubAppTool.isNull(cmaterialid)) {
        continue;
      }
      alMaterialid.add(cmaterialid);
    }
    if (alMaterialid.size() == 0) {
      return null;
    }

    String[] cmaterialids = new String[alMaterialid.size()];
    alMaterialid.toArray(cmaterialids);

    String transtypeID = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    // ��ѯ���������֯ID��Ӧ����֯ID����������ID�ͽ��������֯VID��Ӧ����֯VID����������VID
    try {
      // ����������ͷǿգ����ս������ͻ�ȡֱ�˲�

      IQueryRelationOrg service =
          NCLocator.getInstance().lookup(IQueryRelationOrg.class);
      hmRelationOrgid =
          service.querySaleRelationOrg(transtypeID, ccustomerid, pk_org,
              cmaterialids);

    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    return hmRelationOrgid;
  }

  /**
   * �Ƿ�ֱ��ҵ��
   */
  private boolean isDirecttype(IKeyValue keyValue, SaleOrderBillForm billform) {
    String vtrantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    M30TranTypeVO m30trantypevo =
        billform.getM30ClientContext().getTransType(vtrantypecode,
            AppUiContext.getInstance().getPkGroup());
    // ��ֱ��
    if (DirectType.DIRECTTRAN_NO.equalsValue(m30trantypevo.getFdirecttype())) {
      return false;
    }
    return true;
  }

  /**
   * ִ�����ϱ༭��ʽ dongli2 2013.07.17
   * 
   * @param cardPanel
   * @param rows
   */
  private void execEditFormulas(BillCardPanel cardPanel, String[] key,
      int[] rows) {
    if (rows.length == 1) {
      for (String str : key) {
        // ִ�б༭��ʽ
        cardPanel.getBillModel().execEditFormulaByKey(rows[0], str);
      }
    }
    else {
      cardPanel.getBillModel().execEditFormulas(-1);
    }
  }

  /**
   * ���÷�����������
   * 
   * @param billform
   * @param keyValue
   * @param rows
   */
  private void setCsprofitcenterID(SaleOrderBillForm billform,
      IKeyValue keyValue, int[] rows) {
    // ֱ��ҵ�񣬷�����������=������������
    if (this.isDirecttype(keyValue, billform)) {
      int length = rows.length;
      for (int index = 0; index < length; index++) {
        String cprofitcenterid =
            keyValue.getBodyStringValue(rows[index],
                SaleOrderBVO.CPROFITCENTERID);
        String cprofitcentervid =
            keyValue.getBodyStringValue(rows[index],
                SaleOrderBVO.CPROFITCENTERVID);
        keyValue.setBodyValue(rows[index], SaleOrderBVO.CSPROFITCENTERID,
            cprofitcenterid);
        keyValue.setBodyValue(rows[index], SaleOrderBVO.CSPROFITCENTERVID,
            cprofitcentervid);
      }
    }
    else {
      // ��������ȡֵ���򣬷�ֱ��ҵ�����ȡֵ
      SOProfitCenterValueRule profitRule =
          new SOProfitCenterValueRule(keyValue);
      profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
          SaleOrderBVO.CSPROFITCENTERID, rows);
    }
  }

}
