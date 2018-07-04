package nc.ui.so.salequotation.handler.body;

import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.so.salequotation.keyrela.SalequoKeyRela;
import nc.vo.uapbd.custmaterial.CustMaterialVO;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.salequotation.rule.BodyDefaultRule;
import nc.ui.so.salequotation.rule.SaleQuotationUnitChangeRateRule;
import nc.ui.so.salequotation.rule.SaleQuotationUnitDefaultRule;

/**
 * ���۱��۵��ͻ�������༭�¼�
 * 
 * @since 6.3
 * @version 2012-12-11 13:16:31
 * @author liangjm
 */
public class CustMaterialEditHandler {

  /**
   * �ͻ�������༭��
   * 
   * @param e
   */

  public void afterEdit(CardBodyAfterEditEvent e) {
    // --���ն�ѡ����
    RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
    int[] rows = utils.refMoreSelected(e.getRow(), e.getKey(), true);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    IKeyRela keyRela = new SalequoKeyRela();

    // --1.ͨ������id�������Ĭ�ϵ�λ,
    SaleQuotationUnitDefaultRule unitdef = new SaleQuotationUnitDefaultRule();
    unitdef.setDefaultSaleUnit(keyValue, rows);

    // �����ջ���������������˰����Ϣ
    SOCountryInfoRule conutry = new SOCountryInfoRule(keyValue);
    conutry.setCountryInfoByPk_Org(rows);
    // ���ù������͡�����ó��
    SOBuysellTriaRule buysellTria = new SOBuysellTriaRule(keyValue);
    buysellTria.setBuysellAndTriaFlag(rows);
    // ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue, keyRela);
    taxInfo.setTaxInfoByBodyPos(rows);

    // ���ò����㻻����
    SaleQuotationUnitChangeRateRule unitrate =
        new SaleQuotationUnitChangeRateRule(keyValue);
    for (int row : rows) {
      unitrate.calcAstChangeRate(row);
      unitrate.calcQtChangeRate(row);
    }
    // ����Ĭ��ֵ����
    BodyDefaultRule defaultValue = new BodyDefaultRule(keyValue);
    defaultValue.setBodyDefaultRule(rows);

    // �༭�ͻ������������������̡���Ӧ��
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.set4310Materials(rows);
  }

  /**
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    // ���ÿ��ö�ѡ
    BillCardPanel cardPanel = e.getBillCardPanel();
    BillItem item = cardPanel.getBodyItem(SOItemKey.CCUSTMATERIALID);
    UIRefPane uirefpane = (UIRefPane) item.getComponent();
    uirefpane.setMultiSelectedEnabled(true);

    // ���տͻ�����֯����
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String customer = keyValue.getHeadStringValue(SalequotationHVO.PK_CUSTOMER);
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String pk_group = keyValue.getHeadStringValue(SOItemKey.PK_GROUP);

    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(CustMaterialVO.getDefaultTableName() + "."
        + CustMaterialVO.PK_CUSTOMER, customer);
    wheresql.append(" and ");
    wheresql.append(CustMaterialVO.getDefaultTableName() + "."
        + CustMaterialVO.PK_ORG, new String[] {
      pk_org, pk_group
    });
    uirefpane.setWhereString(wheresql.toString());
  }
}
