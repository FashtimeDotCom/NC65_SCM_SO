package nc.ui.so.m30.billref.withdraw;

import java.awt.Container;

import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QFreeCustFilter;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPaytermFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QStockOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOItemKey;

public class SaleOrderBillReferQuery extends DefaultBillReferQuery {

  public SaleOrderBillReferQuery(Container c, TemplateInfo info) {
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
    // �����������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Order.getCode());
    trantype.filter();

    // �ͻ�����
    QCustomerFilter iordercustcode =
        new QCustomerFilter(condDLGDelegator, "ccustomerid.code");
    iordercustcode.setPk_orgCode(SaleOrderHVO.PK_ORG);
    iordercustcode.addEditorListener();

    // �ͻ�����
    QCustomerFilter iordercustname =
        new QCustomerFilter(condDLGDelegator, "ccustomerid.name");
    iordercustname.setPk_orgCode(SaleOrderHVO.PK_ORG);
    iordercustname.addEditorListener();

    // ɢ��
    QFreeCustFilter freecust =
        new QFreeCustFilter(condDLGDelegator, SaleOrderHVO.CFREECUSTID);
    freecust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    freecust.addEditorListener();

    // ��Ʊ�ͻ�
    QCustomerFilter invoicecust =
        new QCustomerFilter(condDLGDelegator, SaleOrderHVO.CINVOICECUSTID);
    invoicecust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    invoicecust.addEditorListener();

    // �ջ��ͻ�����
    QCustomerFilter creceivecustid =
        new QCustomerFilter(condDLGDelegator, "so_saleorder_b.creceivecustid");
    creceivecustid.setPk_orgCode(SaleOrderHVO.PK_ORG);
    creceivecustid.addEditorListener();

    // ���ϱ���
    QMarterialFilter marteralcode =
        new QMarterialFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid.code");
    marteralcode.addEditorListener();

    // ��������
    QMarterialFilter marteralname =
        new QMarterialFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid.name");
    marteralname.addEditorListener();

    // ���ϻ�������
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(condDLGDelegator,
            "so_saleorder_b.cmaterialid.pk_marbasclass");
    marbasclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marbasclass.addEditorListener();

    // �������۷���
    QMarSaleClassFilter marSaleClass =
        new QMarSaleClassFilter(condDLGDelegator,
            "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass");
    marSaleClass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marSaleClass.addEditorListener();

    // �������۲���
    QDeptFilter deptFilter =
        QDeptFilter
            .createDeptFilterOfSO(condDLGDelegator, SaleOrderHVO.CDEPTID);
    deptFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    deptFilter.addEditorListener();

    // ����ҵ��Ա
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfSO(condDLGDelegator,
            SaleOrderHVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    psnFilter.addEditorListener();

    // ���������֯
    QStockOrgFilter stockOrg =
        new QStockOrgFilter(condDLGDelegator, "so_saleorder_b.csendstockorgid");
    stockOrg.filter();

    // ����Э��
    QPaytermFilter paytermfilter =
        new QPaytermFilter(condDLGDelegator, SaleOrderHVO.CPAYTERMID);
    paytermfilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    paytermfilter.addEditorListener();

    new QFfileFilterByMaterCode(condDLGDelegator,
        "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid")
        .addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator,
        "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode")
        .addEditorListener();
  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // ������֯
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.PK_ORG,
        SaleOrderBVO.METAPATH + "pk_org");
    // ��������
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        SaleOrderBVO.METAPATH + "dbilldate");
  }

  private void setDefaultPk_org(QueryConditionDLGDelegator condDLGDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (defaultOrg != null && defaultOrg.trim().length() > 0) {
      condDLGDelegator.setDefaultValue(SaleOrderHVO.PK_ORG, defaultOrg);
    }
  }

}
