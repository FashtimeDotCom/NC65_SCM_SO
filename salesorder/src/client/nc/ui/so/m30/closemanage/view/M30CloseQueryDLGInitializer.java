package nc.ui.so.m30.closemanage.view;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QFreeCustFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QStockOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class M30CloseQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // ��ʼ�γ�����Լ��
    this.initFilterRef(condDLGDelegator);
    // �����ӱ������ֶ�
    this.processBodyItem(condDLGDelegator);

    // ����֯Ȩ��
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // �����������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Order.getCode());
    trantype.filter();
    
    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, SaleOrderHVO.PK_ORG);

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
    filterutil.removeFilterMaps(new String[] {"so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"});
//    // �ͻ�
//    QCustomerFilter cust =
//        new QCustomerFilter(condDLGDelegator, SaleOrderHVO.CCUSTOMERID);
//    cust.addEditorListener();
//
//    // ���ϱ���
//    QMarterialFilter marteral =
//        new QMarterialFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
//            "so_saleorder_b.cmaterialvid");
//    marteral.addEditorListener();
//
//    // ���������֯
//    QStockOrgFilter stockOrg =
//        new QStockOrgFilter(condDLGDelegator, "so_saleorder_b.csendstockorgid");
//    stockOrg.filter();
//
//    // ɢ��
//    QFreeCustFilter freecust =
//        new QFreeCustFilter(condDLGDelegator, SaleOrderHVO.CFREECUSTID);
//    freecust.setPk_orgCode(SaleOrderHVO.PK_ORG);
//    freecust.addEditorListener();
//
//    // ����ҵ��Ա������������֯�ɼ�����Ա��������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
//    QPsndocFilter employee =
//        new QPsndocFilter(condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
//    employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
//    employee.addEditorListener();
//
//    // ���۲���: ����������֯�ɼ��Ĳ��ţ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
//    QDeptFilter dept = new QDeptFilter(condDLGDelegator, SaleOrderHVO.CDEPTID);
//    dept.setPk_orgCode(SaleOrderHVO.PK_ORG);
//    dept.addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // ������֯
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.PK_ORG,
        "so_saleorder_b.pk_org");
    // ��������
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        "so_saleorder_b.dbilldate");
  }

}
