package nc.ui.so.m38.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.keyrela.PreOrderKeyrela;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.rule.SOUnitDefaultRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;

import nc.itf.so.m38.IQueryRelationOrg;

import nc.bs.framework.common.NCLocator;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.so.m38.billui.pub.BodyDefaultRule;
import nc.ui.so.m38.billui.pub.ClearBodyValueRule;
import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 
 * @since 6.3
 * @version 2012-12-24 09:50:07
 * @author liangjm
 */
public class MaterialEditHandler {

  /**
   * ���� �༭��
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    // --���ն�ѡ����
    RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
    int[] rows = utils.refMoreSelected(e.getRow(), e.getKey(), true);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // ��ձ����ֶ�
    ClearBodyValueRule cbvr = new ClearBodyValueRule(keyValue);
    cbvr.clearBodyValue(rows);

    // --1.ͨ������id�������Ĭ�ϵ�λ,�����㻻����
    SOUnitDefaultRule unitdef = new SOUnitDefaultRule(keyValue);
    unitdef.setDefaultSaleUnit(rows);

    SOUnitChangeRateRule unitrate = new SOUnitChangeRateRule(keyValue);
    for (int row : rows) {
      unitrate.calcAstChangeRate(row);
      unitrate.calcQtChangeRate(row);
    }
    // ����Ĭ��ֵ
    BodyDefaultRule defrule = new BodyDefaultRule(keyValue);
    defrule.setBodyDefaultValue(rows);

    // ����Ĭ���ջ��ͻ�
    SOCustRelaDefValueRule custrefrule = new SOCustRelaDefValueRule(keyValue);
    custrefrule.setRelaReceiveCust(rows);

    IKeyRela keyRela = new PreOrderKeyrela();
    ReceiveCustDefAddrRule defaddrule =
        new ReceiveCustDefAddrRule(keyValue, keyRela);
    defaddrule.setCustDefaultAddress(rows);
    // ������֯ί�й�ϵ����Ĭ����֯
    SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(keyValue);
    orgrelarule.setFinanceStockOrg(rows,
        this.GetRelationOrg(keyValue, rows));
    
    orgrelarule.setTrafficOrg(rows);

    // ������������֯��Ӧ�ı���
    SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
    currencyrule.setCurrency(rows);
    // ������֯��λ�����¼����۱�����
    SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
    changeraterule.calcBodyExchangeRates(rows);
    // ���ű�λ�һ���
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    groupraterule.calcGroupExchangeRate(rows);
    // ȫ�ֱ�λ�һ���
    SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
    globalerate.calcGlobalExchangeRate(rows);

    // ���ù���
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(rows);

    // ���ù������ͺ�����ó��
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    // ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);

    // ѯ��
    PreOrderFindPriceConfig config = new PreOrderFindPriceConfig(cardPanel);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.findPriceAfterEdit(rows, SaleOrderBVO.CMATERIALVID);

    // �༭���Ϻ����ÿͻ�������(V63�¼�)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setCustMaterial(rows);
  }

  /**
   * ���� �༭ǰ
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // �޶�ʱ�Ѿ������ε��ݵĲ������޸�
    Integer billstatus = keyValue.getHeadIntegerValue(PreOrderHVO.FSTATUSFLAG);
    int row = e.getRow();
    UFDouble narrnum = keyValue.getBodyUFDoubleValue(row, PreOrderBVO.NARRNUM);

    if (BillStatus.AUDIT.equalsValue(billstatus) && !MathTool.isZero(narrnum)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // �Ѿ��йرյĲ������޸�
    UFBoolean bclose =
        keyValue.getBodyUFBooleanValue(row, PreOrderBVO.BLINECLOSE);
    if (null != bclose && bclose.booleanValue()) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // �������Ͽ��Զ�ѡ
    UIRefPane refPane =
        (UIRefPane) cardPanel.getBodyItem(PreOrderBVO.CMATERIALVID)
            .getComponent();
    refPane.setMultiSelectedEnabled(true);
    // ��������Լ������
    BillItem cmaterialvid = cardPanel.getBodyItem(PreOrderBVO.CMATERIALVID);
    FilterMaterialRefUtils utils =
        new FilterMaterialRefUtils((UIRefPane) cmaterialvid.getComponent());
    String pk_org = keyValue.getHeadStringValue(PreOrderHVO.PK_ORG);
    utils.filterItemRefByOrg(pk_org);
    e.setReturnValue(Boolean.TRUE);
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

    // ��ѯ���������֯ID��Ӧ����֯ID����������ID�ͽ��������֯VID��Ӧ����֯VID����������VID
    try {

      IQueryRelationOrg service =
          NCLocator.getInstance().lookup(IQueryRelationOrg.class);
      hmRelationOrgid =
          service.querySaleRelationOrg(ccustomerid, pk_org, cmaterialids);

    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    return hmRelationOrgid;
  }
}
