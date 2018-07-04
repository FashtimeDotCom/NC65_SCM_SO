package nc.ui.so.m38.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.trade.checkrule.VOChecker;

public class SendStockOrgEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    PreOrderCalculator calculator = new PreOrderCalculator(cardPanel);

    // ��ղֿ�
    for (int row : rows) {
      keyValue.setBodyValue(row, PreOrderBVO.CSENDSTORDOCID, null);
    }

    SaleOrgRelationRule relarule = new SaleOrgRelationRule(keyValue);
    // ����ɵ���֯����
    int ilength = rows.length;
    String[] oldcurrs = new String[ilength];
    for (int i = 0; i < ilength; i++) {
      oldcurrs[i] =
          keyValue.getBodyStringValue(rows[i], PreOrderBVO.CCURRENCYID);
    }
    // ���ý��������֯��Ӧ����֯����������
    relarule.setFinanceOrg(rows);

    // ������������֯��Ӧ�ı���
    SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
    currencyrule.setCurrency(rows);

    // ����������֯
    relarule.setTrafficOrg(rows);

    SOCountryInfoRule conutryinforule = new SOCountryInfoRule(keyValue);
    // ���÷������ҡ���˰����
    conutryinforule.setSendCountry(rows);
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
    calculator.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);
    // �ж���֯�����Ƿ�ı�
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

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String cmaterialid =
        keyValue.getBodyStringValue(e.getRow(), PreOrderBVO.CMATERIALID);
    // ���������֯VIDs
    String[] csendstockorgvids = null;
    if (PubAppTool.isNull(cmaterialid)) {
      e.setReturnValue(false);
      return;
    }
    try {
      String pk_org = keyValue.getHeadStringValue(PreOrderHVO.PK_ORG);
      String[] csendstockorgids =
          SaleOrgPubService.getStockOrgIDSBySaleOrgIDAndMaterialID(pk_org,
              cmaterialid);
      csendstockorgvids = this.getSendStockOrgVIDs(csendstockorgids);
    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }

    if (!VOChecker.isEmpty(csendstockorgvids)) {
      BillItem sendStockOrgItem =
          cardPanel.getBodyItem(PreOrderBVO.CSENDSTOCKORGVID);
      UIRefPane sendStockOrgRefPane =
          (UIRefPane) sendStockOrgItem.getComponent();
      AbstractRefModel model = sendStockOrgRefPane.getRefModel();
      model.setFilterPks(csendstockorgvids);
    }

  }

  private String[] getSendStockOrgVIDs(String[] csendstockorgids)
      throws BusinessException {
    String[] csendstockorgvids = null;
    if (null == csendstockorgids || csendstockorgids.length == 0) {
      return csendstockorgvids;
    }

    // תVID
    Map<String, String> hmSendStockOrgIDs =
        OrgUnitPubService.getNewVIDSByOrgIDS(csendstockorgids);

    if (hmSendStockOrgIDs != null) {
      List<String> alSendStockOrgVIDs = new ArrayList<String>();
      for (String value : hmSendStockOrgIDs.values()) {
        if (value != null && value.length() > 0) {
          alSendStockOrgVIDs.add(value);
        }
      }
      if (alSendStockOrgVIDs.size() > 0) {
        csendstockorgvids = alSendStockOrgVIDs.toArray(new String[0]);
      }
    }
    return csendstockorgvids;
  }

  private boolean isCurrChange(String oldcurr, String newcurr) {
    String oldvalue = null == oldcurr ? "" : oldcurr;
    String newvalue = null == newcurr ? "" : newcurr;

    return !oldvalue.equals(newvalue);
  }
}
