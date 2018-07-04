package nc.ui.so.m38.arrange.editor.eidthandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.m38.arrange.pub.M38ArrangeModelCalculator;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.pub.enumeration.ListTemplateType;
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

/**
 * ���������֯����
 * <p>��������֯ID������ID,�õ�����ҵ��ί�й�ϵ����ķ����Ŀ����֯ID[]", </p>
 * 
 * @since 6.0
 * @version 2011-3-26 ����02:23:14
 * @author ��־ΰ
 */
public class SendStockOrgEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {
    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String cmaterialid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALID);
    // ���������֯VIDs
    String[] csendstockorgvids = null;
    if (cmaterialid == null || cmaterialid.trim().length() == 0) {
      return;
    }
    try {
      String[] csendstockorgids =
          SaleOrgPubService.getStockOrgIDSBySaleOrgIDAndMaterialID(pk_org,
              cmaterialid);
      csendstockorgvids = this.getSendStockOrgVIDs(csendstockorgids);
    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }

    if (!VOChecker.isEmpty(csendstockorgvids)) {
      BillItem billItem = listPanel.getBodyItem(SaleOrderBVO.CSENDSTOCKORGVID);
      UIRefPane sendStockOrgRefPane = (UIRefPane) billItem.getComponent();
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
        if ((value != null) && (value.length() > 0)) {
          alSendStockOrgVIDs.add(value);
        }
      }
      if (alSendStockOrgVIDs.size() > 0) {
        csendstockorgvids = alSendStockOrgVIDs.toArray(new String[0]);
      }
    }
    return csendstockorgvids;
  }

  public void afterEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    ListKeyValue keyValue =
        new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    // 1.����ɵ���֯����
    String oldcurr = keyValue.getBodyStringValue(row, SaleOrderBVO.CCURRENCYID);

    int[] rows = new int[] {
      row
    };
    // ����ֱ�˲�
    DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
    dirstorerule.setSendStordoc(rows);
    // 2.���ý��������֯��Ӧ����֯����������
    SaleOrgRelationRule relarule = new SaleOrgRelationRule(keyValue);
    relarule.setFinanceOrg(rows);
    // ������������֯��Ӧ�ı���
    SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
    currencyrule.setCurrency(rows);
    // 3.����������֯
    relarule.setTrafficOrg(rows);

    // ���ù���
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(rows);

    // ���ù������ͺ�����ó��
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();

    M38ArrangeModelCalculator calculator =
        new M38ArrangeModelCalculator(listPanel);
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());

    // ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] ratechgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(ratechgrows, SaleOrderBVO.NTAXRATE);

    // 4.�ж���֯�����Ƿ�ı�
    String newcurr = keyValue.getBodyStringValue(row, SaleOrderBVO.CCURRENCYID);
    if (!PubAppTool.isEqual(oldcurr, newcurr)) {

      // ������֯��λ�����¼����۱�����
      SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
      changeraterule.calcBodyExchangeRates(rows);
      // ���ű�λ�һ���
      SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
      if (groupraterule.isGroupExchgRateChange(SaleOrderBVO.CCURRENCYID)) {
        groupraterule.calcGroupExchangeRate(rows);
      }
      // ȫ�ֱ�λ�һ���
      SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
      if (globalerate.isGlobalExchgRateChange(SaleOrderBVO.CCURRENCYID)) {
        globalerate.calcGlobalExchangeRate(rows);
      }
      calculator.calculate(rows, SaleOrderBVO.NEXCHANGERATE);
    }
  }
}
