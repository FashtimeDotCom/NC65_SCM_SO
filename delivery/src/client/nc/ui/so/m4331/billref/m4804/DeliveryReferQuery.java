package nc.ui.so.m4331.billref.m4804;

import java.awt.Container;

import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.SOItemKey;

public class DeliveryReferQuery extends DefaultBillReferQuery {

  public DeliveryReferQuery(Container c, TemplateInfo info) {
    super(c, info);
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // ���ø��Ի�����Ĭ������֯
    this.setDefaultPk_org(dlgDelegator);
    // ��ʼ�γ�����Լ��
    this.initFilterRef(dlgDelegator);
    // �����ӱ������ֶ�
    this.processBodyItem(dlgDelegator);
    // ����Ȩ��
    dlgDelegator.setPowerEnable(true);
    // ����֯Ȩ��
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // �������Ͳ��չ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOItemKey.VTRANTYPECODE);
    trantype.filter();
    
    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, DeliveryHVO.PK_ORG);
    
    String[] removekeys =
        new String[] {
    		DeliveryBVO.MAINMETAPATH+ DeliveryBVO.CINSTOCKORGID, 
    		DeliveryBVO.MAINMETAPATH+ DeliveryBVO.CSENDSTOCKORGID,
    		"cdeliverybid.cmffileid","cdeliverybid.cmffileid.vskucode"
        };
    // �Ƴ������ֶβ��չ���
    filterutil.removeFilterMaps(removekeys);
    filterutil.addFilterMapsListeners();
    
    new QFfileFilterByMaterCode(condDLGDelegator, "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid.vskucode").addEditorListener();


//    // ���������֯����
//    QStockOrgFilter csendstockorgid =
//        new QStockOrgFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
//            + DeliveryBVO.CSENDSTOCKORGID);
//    csendstockorgid.filter();
//
//    // �ջ��ͻ�����
//    QCustomerFilter creceivecustid =
//        new QCustomerFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
//            + DeliveryBVO.CRECEIVECUSTID);
//    creceivecustid.addEditorListener();
//    // �ջ������֯����
//    QStockOrgFilter cinstockorgid =
//        new QStockOrgFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
//            + DeliveryBVO.CINSTOCKORGID);
//    cinstockorgid.filter();
//
//    // ������
//    QCarrierFilter carrier =
//        new QCarrierFilter(condDLGDelegator, "cdeliverybid.ctranscustid");
//    carrier.addEditorListener();
//
//    // ���ϱ���:���շ��������֯�ɼ������ϣ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
//    QMarterialFilter marteral =
//        new QMarterialFilter(condDLGDelegator, DeliveryHVO.PK_ORG,
//            "cdeliverybid.cmaterialid");
//    marteral.addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // ����֯
    condDLGDelegator.addRedundancyInfo(DeliveryHVO.PK_ORG,
        "cdeliverybid.pk_org");
    // ��������
    condDLGDelegator.addRedundancyInfo(DeliveryHVO.DBILLDATE,
        "cdeliverybid.dbilldate");
  }

  private void setDefaultPk_org(QueryConditionDLGDelegator condDLGDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (!PubAppTool.isNull(defaultOrg)) {
      condDLGDelegator.setDefaultValue(SOItemKey.PK_ORG, defaultOrg);
    }
  }

}
