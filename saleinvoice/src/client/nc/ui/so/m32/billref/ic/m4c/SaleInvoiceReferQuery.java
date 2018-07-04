package nc.ui.so.m32.billref.ic.m4c;

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
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.SOItemKey;

public class SaleInvoiceReferQuery extends DefaultBillReferQuery {

  public SaleInvoiceReferQuery(Container c, TemplateInfo info) {
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

    // ����֯Ȩ��
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleInvoiceBVO.MAINMETAPATH + SaleInvoiceBVO.CSENDSTOCKORGID
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());

    dlgDelegator.setPowerEnable(true);
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // ������֯�ֶ�
    String saleorgkey = "csaleinvoicebid.csaleorgid";

    // �������Ͳ��չ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Invoice.getCode());
    trantype.filter();
    
    RefCommonFilterListener filterUtil = 
    	new RefCommonFilterListener(condDLGDelegator,SaleInvoiceHVO.PK_ORG);
    // ����ҵ��Ա
    // ���۲���
    filterUtil.addFilterMaps(new String[] {
    		"csaleinvoicebid.cemployeeid","csaleinvoicebid.cdeptid"
    }, saleorgkey);
    filterUtil.addFilterMapsListeners();
    
    new QFfileFilterByMaterCode(condDLGDelegator, "csaleinvoicebid.cmaterialid.code", "csaleinvoicebid.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "csaleinvoicebid.cmaterialid.code", "csaleinvoicebid.cmffileid.vskucode").addEditorListener();
    filterUtil.removeFilterMaps(new String[] {"csaleinvoicebid.cmffileid","csaleinvoicebid.cmffileid.vskucode"});
//    // ��Ʊ�ͻ�
//    QCustomerFilter invoicecust =
//        new QCustomerFilter(condDLGDelegator, SaleInvoiceHVO.CINVOICECUSTID);
//    invoicecust.setPk_orgCode(SaleInvoiceHVO.PK_ORG);
//    invoicecust.addEditorListener();
//
//    // ����ҵ��Ա
//    QPsndocFilter employee =
//        new QPsndocFilter(condDLGDelegator, "csaleinvoicebid.cemployeeid");
//    employee.setPk_orgCode(saleorgkey);
//    employee.addEditorListener();
//
//    // ���۲���
//    QDeptFilter dept =
//        new QDeptFilter(condDLGDelegator, "csaleinvoicebid.cdeptid");
//    dept.setPk_orgCode(saleorgkey);
//    dept.addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // ����֯
    condDLGDelegator.addRedundancyInfo(SaleInvoiceHVO.PK_ORG,
        "csaleinvoicebid.pk_org");
    // ��������
    condDLGDelegator.addRedundancyInfo(SaleInvoiceHVO.DBILLDATE,
        "csaleinvoicebid.dbilldate");
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
