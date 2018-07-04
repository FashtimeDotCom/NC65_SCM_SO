package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.m38.arrange.pub.M38ArrangeModelCalculator;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.uap.rbac.profile.FunctionPermProfileManager;

/**
 * Ԥ�������а��Ž��涩��������֯�༭�¼�
 * 
 * @since 6.0
 * @version 2012-3-28 ����03:13:13
 * @author fengjb
 */
public class SaleOrgEditHandler {

  public void afterEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    // ���ò���Ϊ��
    keyValue.setHeadValue(SaleOrderHVO.CDEPTVID, null);
    keyValue.setHeadValue(SaleOrderHVO.CDEPTID, null);
    // ����ҵ��ԱΪ��
    keyValue.setHeadValue(SaleOrderHVO.CEMPLOYEEID, null);

    String csaleorgid = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    // תVID
    String csaleorgvid = OrgUnitPubService.getNewVIDByOrgID(csaleorgid);
    keyValue.setHeadValue(SaleOrderHVO.PK_ORG_V, csaleorgvid);

    // ����ƥ��ҵ������
    String trantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String userid = AppUiContext.getInstance().getPkUser();
    String cbiztypeid = null;
    try {
      cbiztypeid =
          PfUtilClient.retBusitypeCanStart(SOBillType.Order.getCode(),
              trantypecode, csaleorgid, userid);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    // ����ҵ������
    keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, cbiztypeid);

    int[] rows = new int[] {
      row
    };
    // ����ԭʼ���������֯�ͱ���
    String old_sendstockorg =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSENDSTOCKORGVID);
    String old_settleorg =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSETTLEORGVID);
    String old_currency =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CCURRENCYID);

    // ����ҵ��ί�й�ϵ����ƥ���µĽ��������֯�������֯��������֯
    SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(keyValue);
    orgrelarule.setFinanceOrg(rows);
    orgrelarule.setSendStockOrg(rows);
    orgrelarule.setTrafficOrg(rows);
    // ��������֯�����仯
    String new_sendstockorg =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSENDSTOCKORGVID);
    if (!PubAppTool.isEqual(old_sendstockorg, new_sendstockorg)) {
      keyValue.setBodyValue(row, SaleOrderBVO.CSENDSTORDOCID, null);
    }
    // ���������֯�����仯
    String new_settleorg =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSETTLEORGVID);
    if (!PubAppTool.isEqual(old_settleorg, new_settleorg)) {
      SOCurrencyRule currule = new SOCurrencyRule(keyValue);
      currule.setCurrency(rows);
      // ������ұ��ַ����仯
      String new_currency =
          keyValue.getBodyStringValue(row, SaleOrderBVO.CCURRENCYID);
      if (!PubAppTool.isEqual(old_currency, new_currency)) {
        SOExchangeRateRule exchangerule = new SOExchangeRateRule(keyValue);
        exchangerule.calcBodyExchangeRates(rows);
        // �������۽�����
        M38ArrangeModelCalculator calculator =
            new M38ArrangeModelCalculator(listPanel);
        calculator.calculate(rows, SaleOrderBVO.NEXCHANGERATE);
      }
    }

  }

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {
    BillItem orgitem = listPanel.getBodyItem(SaleOrderHVO.PK_ORG);
    String usercode =
        WorkbenchEnvironment.getInstance().getLoginUser().getUser_code();
    String[] orgpks =
        FunctionPermProfileManager.getInstance().getProfile(usercode)
            .getPermPkorgs();
    UIRefPane orgRefPane = (UIRefPane) orgitem.getComponent();
    AbstractRefModel model = orgRefPane.getRefModel();
    model.setFilterPks(orgpks);

  }
}
