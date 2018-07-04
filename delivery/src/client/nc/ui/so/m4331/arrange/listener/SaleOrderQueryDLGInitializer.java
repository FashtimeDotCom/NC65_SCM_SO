package nc.ui.so.m4331.arrange.listener;

import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOFunc;

import nc.ui.pubapp.uif2app.query2.DefaultQueryConditionDLG;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QStockOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;

/**
 * ���۶�����ѯ�Ի����ʼ��
 * 
 * @since 6.0
 * @version 2011-1-10 ����04:38:40
 * @author ��־ΰ
 */
public class SaleOrderQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // ��ʼ�γ�����Լ��
    this.initFilterRef(dlgDelegator);
    // �����ӱ������ֶ�
    this.processBodyItem(dlgDelegator);

    // ����֯Ȩ��
    TemplateInfo tempalteinfo =
        ((DefaultQueryConditionDLG) dlgDelegator.getQueryConditionDLG())
            .getTempInfo();
    tempalteinfo.setFunNode(SOFunc.N40060401.getCode());
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleOrderBVO.METAPATH + SaleOrderBVO.CTRAFFICORGID
    });
    tempalteinfo.setFunNode(SOFunc.N40060301.getCode());

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());

    dlgDelegator.setPowerEnable(true);
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // ���ε�������֯Ȩ�޹���
    // String[] pks = condDLGDelegator.getLogincontext();

    // �����������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Order.getCode());
    trantype.filter();

    // �ͻ�
    QCustomerFilter cust =
        new QCustomerFilter(condDLGDelegator, SaleOrderHVO.CCUSTOMERID);
    cust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    cust.addEditorListener();

    // ���۲���
    QDeptFilter dept = new QDeptFilter(condDLGDelegator, SaleOrderHVO.CDEPTID);
    dept.setPk_orgCode(SaleOrderHVO.PK_ORG);
    dept.addEditorListener();

    // �������۷���
    QMarSaleClassFilter marSaleClass =
        new QMarSaleClassFilter(condDLGDelegator,
            "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass");
    marSaleClass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marSaleClass.addEditorListener();

    // ���Ϸ���
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid.pk_marbasclass");
    marbasclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marbasclass.addEditorListener();

    // ���ϱ���
    QMarterialoidFilter marteral =
        new QMarterialoidFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid");
    marteral.addEditorListener();
    
    // ���ϱ���
    QMarterialoidFilter marteralcode =
        new QMarterialoidFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid.code");
    marteralcode.addEditorListener();

    // ����ҵ��Ա
    QPsndocFilter employee =
        new QPsndocFilter(condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
    employee.addEditorListener();

    // ���������֯
    QStockOrgFilter stockOrg =
        new QStockOrgFilter(condDLGDelegator, "so_saleorder_b.csendstockorgid");
    stockOrg.filter();

    // �����ֿ⣺Ĭ��Ϊ�գ����������֯�ǿ���Ψһ�����������淢�������֯���ϵĲֿ�
    QWareHouseFilter sendstordoc =
        new QWareHouseFilter(condDLGDelegator,
            "so_saleorder_b.csendstockorgid", "so_saleorder_b.csendstordocid");
    sendstordoc.addEditorListener();
    
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // ������֯
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.PK_ORG,
        SaleOrderBVO.METAPATH + "pk_org");
    // ��������
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        SaleOrderBVO.METAPATH + "dbilldate");
  }
}
