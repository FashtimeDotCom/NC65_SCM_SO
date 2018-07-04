package nc.ui.so.m38.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;

public class SettleOrgEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    // ���ҵ�����
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    PreOrderCalculator calculator = new PreOrderCalculator(cardPanel);

    // ���ñ�˰����/����
    SOCountryInfoRule conutryinforule = new SOCountryInfoRule(keyValue);
    conutryinforule.setTaxCountry(rows);
    // ���ù������ͺ�����ó��
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);

    int[] buysellrows = buyflgrule.getBuysellChgRow();
    calculator.calculate(buysellrows, SOCalConditionRule.getCalPriceKey());
    // ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] taxchgrows = taxInfo.getTaxChangeRows();
    // ��˰�ʴ����������۽������
    calculator.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);
    // ����ԭ����
    int ilength = rows.length;
    String[] oldcurrs = new String[ilength];
    for (int i = 0; i < ilength; i++) {
      oldcurrs[i] =
          keyValue.getBodyStringValue(rows[i], PreOrderBVO.CCURRENCYID);
    }
    // 1.������������֯��Ӧ�ı���
    SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
    currencyrule.setCurrency(rows);
    // 2.�ж���֯�����Ƿ�ı�
    List<Integer> listchgrow = new ArrayList<Integer>();
    for (int i = 0; i < ilength; i++) {
      String newcurr =
          keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CCURRENCYID);
      if (this.isCurrChange(oldcurrs[i], newcurr)) {
        listchgrow.add(rows[i]);
      }
    }
    int chgsize = listchgrow.size();
    if (chgsize > 0) {
      int[] chgrows = new int[chgsize];
      for (int i = 0; i < chgsize; i++) {
        chgrows[i] = listchgrow.get(i);
      }
      // ������֯��λ�����¼����۱�����
      SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
      changeraterule.calcBodyExchangeRates(chgrows);
      // ���ű�λ�һ���
      SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
      if (groupraterule.isGroupExchgRateChange(PreOrderBVO.CCURRENCYID)) {
        groupraterule.calcGroupExchangeRate(chgrows);
      }
      // ȫ�ֱ�λ�һ���
      SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
      if (globalerate.isGlobalExchgRateChange(PreOrderBVO.CCURRENCYID)) {
        globalerate.calcGlobalExchangeRate(chgrows);
      }
      calculator.calculate(chgrows, PreOrderBVO.NEXCHANGERATE);
    }
  }

  private boolean isCurrChange(String oldcurr, String newcurr) {
    String oldvalue = null == oldcurr ? "" : oldcurr;
    String newvalue = null == newcurr ? "" : newcurr;

    return !oldvalue.equals(newvalue);
  }

}
