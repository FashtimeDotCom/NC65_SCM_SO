package nc.ui.so.m30.billref.m4331;

import java.awt.Container;

import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class SaleOrderBillReferQuery extends DefaultBillReferQuery {

  public SaleOrderBillReferQuery(Container c, TemplateInfo info) {
    super(c, info);
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // ��ʼ�γ�����Լ��
    this.initFilterRef(dlgDelegator);
    // �����ӱ������ֶ�
    this.processBodyItem(dlgDelegator);

    // ����Ȩ��
    dlgDelegator.setPowerEnable(true);

    // ����֯Ȩ��
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleOrderBVO.METAPATH + SaleOrderBVO.CTRAFFICORGID
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {

    // �����������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Order.getCode());
    trantype.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, SaleOrderHVO.PK_ORG);

    String sendstordocorgkey = "so_saleorder_b.csendstockorgid";
    // �ֿⰴ�տ����֯����
    filterutil.addFilterMaps(new String[] {
      "so_saleorder_b.csendstordocid"
    }, sendstordocorgkey);

    String[] removekeys =
        new String[] {
          sendstordocorgkey, "so_saleorder_b.csettleorgid",
          "so_saleorder_b.ctrafficorgid",
          "so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"
        };
    // �Ƴ������ֶβ��չ���
    filterutil.removeFilterMaps(removekeys);

    filterutil.addFilterMapsListeners();
    
    
    //�������۲���
    QDeptFilter deptFilter = QDeptFilter.createDeptFilterOfSO(
    condDLGDelegator, SaleOrderHVO.CDEPTID);
    deptFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    deptFilter.addEditorListener();

    // ����ҵ��Ա
    QPsndocFilter psnFilter = QPsndocFilter.createQPsndocFilterOfSO(
    condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    psnFilter.addEditorListener();  
    
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();

    // // �ͻ�
    // QCustomerFilter cust =
    // new QCustomerFilter(condDLGDelegator, SaleOrderHVO.CCUSTOMERID);
    // cust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // cust.addEditorListener();
    //
    // // �ͻ���������
    // QCustBaseClassFilter custbas =
    // new QCustBaseClassFilter(condDLGDelegator, "ccustomerid.pk_custclass");
    // custbas.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // custbas.addEditorListener();
    //
    // // �ͻ����۷���
    // QCustSaleClassFilter custsale =
    // new QCustSaleClassFilter(condDLGDelegator,
    // "ccustomerid.sales.pk_custsaleclass");
    // custsale.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // custsale.addEditorListener();
    //
    // // ���۲���
    // QDeptFilter dept = new QDeptFilter(condDLGDelegator,
    // SaleOrderHVO.CDEPTID);
    // dept.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // dept.addEditorListener();
    //
    // // ���ϻ�������
    // QMarbasclassFilter marclass =
    // new QMarbasclassFilter(condDLGDelegator,
    // "so_saleorder_b.cmaterialid.pk_marbasclass");
    // marclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // marclass.addEditorListener();
    //
    // // �������۷���
    // QMarSaleClassFilter marSaleClass =
    // new QMarSaleClassFilter(condDLGDelegator,
    // "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass");
    // marSaleClass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // marSaleClass.addEditorListener();
    //
    // // ���ϱ���
    // QMarterialFilter marteral =
    // new QMarterialFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
    // "so_saleorder_b.cmaterialid");
    // marteral.addEditorListener();
    //
    // // ����ҵ��Ա
    // QPsndocFilter employee =
    // new QPsndocFilter(condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
    // employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // employee.addEditorListener();
    //
    // // ���������֯
    // QStockOrgFilter stockOrg =
    // new QStockOrgFilter(condDLGDelegator, "so_saleorder_b.csendstockorgid");
    // stockOrg.filter();
    //
    // // �����ֿ⣺Ĭ��Ϊ�գ����������֯�ǿ���Ψһ�����������淢�������֯���ϵĲֿ�
    // QWareHouseFilter sendstordoc =
    // new QWareHouseFilter(condDLGDelegator,
    // "so_saleorder_b.csendstockorgid", "so_saleorder_b.csendstordocid");
    // sendstordoc.addEditorListener();
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
