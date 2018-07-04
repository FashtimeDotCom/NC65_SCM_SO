package nc.ui.so.m38.billui.editor.headevent;

import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.keyrela.PreOrderKeyrela;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyUpdateByHead;
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
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 
 * @since 6.3
 * @version 2012-12-24 10:00:12
 * @author liangjm
 */
public class CustomerEditHandler {

  /**
   * 
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    PreOrderCalculator calculator = new PreOrderCalculator(cardPanel);
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();
    // ���ÿͻ�������Ϣ
    SOCustRelaDefValueRule defrule = new SOCustRelaDefValueRule(keyValue);
    defrule.setCustRelaDefValue();
    // ����ֻ�п��в��ü���
    if (rows.length == 0) {
      return;
    }
    if (defrule.isDiscountRateChg()) {
      calculator.calculate(rows, PreOrderBVO.NDISCOUNTRATE);
    }
    // ���ý��������֯��Ӧ����֯����������
    SaleOrgRelationRule relarule = new SaleOrgRelationRule(keyValue);
    relarule.setFinanceOrg(rows);
    // ����ÿ����֯��λ��
    SOCurrencyRule currule = new SOCurrencyRule(keyValue);
    currule.setCurrency(rows);
    // ����������۱�����
    SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
    exraterule.calcBodyExchangeRates(rows);
    // ȫ�ֱ�λ�һ���
    SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
    globalraterule.calcGlobalExchangeRate(rows);
    // ���ű�λ�һ���
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    groupraterule.calcGroupExchangeRate(rows);
    // �������۽������
    calculator.calculate(rows, PreOrderBVO.NEXCHANGERATE);
    // ���ñ����ջ��ͻ�
    BodyUpdateByHead updaterule = new BodyUpdateByHead(keyValue);
    updaterule.updateAllBodyValueByHead(PreOrderBVO.CRECEIVECUSTID,
        PreOrderHVO.CCUSTOMERID);
    // Ĭ���ջ���ַ
    IKeyRela keyRela = new PreOrderKeyrela();
    ReceiveCustDefAddrRule defaddrule =
        new ReceiveCustDefAddrRule(keyValue, keyRela);
    defaddrule.setCustDefaultAddress(rows);
    // �ջ�����/����
    SOCountryInfoRule conutryinforule = new SOCountryInfoRule(keyValue);
    conutryinforule.setReceiveCountry(rows);
    // ���ù������ͺ�����ó��
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
    // ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] ratechgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(ratechgrows, SaleOrderBVO.NTAXRATE);
    // ѯ��
    PreOrderFindPriceConfig config = new PreOrderFindPriceConfig(cardPanel);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.findPriceAfterEdit(rows, PreOrderHVO.CCUSTOMERID);

    // �༭�ͻ������ÿͻ�������(V63�¼�)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setCustMaterial(rows);
  }
}
