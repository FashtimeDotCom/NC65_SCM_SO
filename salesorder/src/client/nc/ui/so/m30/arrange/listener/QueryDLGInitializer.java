package nc.ui.so.m30.arrange.listener;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * ���۶�����ѯ�Ի����ʼ��
 * 
 * @since 6.0
 * @version 2011-1-10 ����04:38:40
 * @author ��־ΰ
 */
public class QueryDLGInitializer implements IQueryConditionDLGInitializer {
  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {

    // ��ʼ�γ�����Լ��
    this.initFilterRef(dlgDelegator);
    // �����ӱ������ֶ�
    this.processBodyItem(dlgDelegator);

    // ����֯Ȩ��
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleOrderHVO.PK_ORG
    });
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void processBodyItem(QueryConditionDLGDelegator dlgDelegator) {
    // ������֯
    dlgDelegator.addRedundancyInfo(SaleOrderHVO.PK_ORG, SaleOrderBVO.METAPATH
        + "pk_org");
    // ��������
    dlgDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        SaleOrderBVO.METAPATH + "dbilldate");
  }

  private void initFilterRef(QueryConditionDLGDelegator dlgDelegator) {

    // �����������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(dlgDelegator, SOBillType.Order.getCode());
    trantype.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(dlgDelegator, SaleOrderHVO.PK_ORG);

    String sendstordocorgkey = "so_saleorder_b.csendstockorgid";
    // �ֿⰴ�տ����֯����
    filterutil.addFilterMaps(new String[] {
      "so_saleorder_b.csendstordocid"
    }, sendstordocorgkey);

    String[] removekeys =
        new String[] {
          sendstordocorgkey, "so_saleorder_b.csettleorgid",
          "so_saleorder_b.ctrafficorgid","so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"
        };
    // �Ƴ������ֶβ��չ���
    filterutil.removeFilterMaps(removekeys);

    filterutil.addFilterMapsListeners();
    
    new QFfileFilterByMaterCode(dlgDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(dlgDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();

    // // �ͻ�
    // QCustomerFilter cust =
    // new QCustomerFilter(dlgDelegator, SaleOrderHVO.CCUSTOMERID);
    // cust.addEditorListener();
    //
    // // ���ϱ���
    // QMarterialFilter marteral =
    // new QMarterialFilter(dlgDelegator, SaleOrderHVO.PK_ORG,
    // "so_saleorder_b.cmaterialid");
    // marteral.addEditorListener();
    //
    // // ���������֯
    // QStockOrgFilter stockOrg =
    // new QStockOrgFilter(dlgDelegator, "so_saleorder_b.csendstockorgid");
    // stockOrg.filter();
    //
    // // �����ֿ⣺Ĭ��Ϊ�գ����������֯�ǿ���Ψһ�����������淢�������֯���ϵĲֿ�
    // QWareHouseFilter sendstordoc =
    // new QWareHouseFilter(dlgDelegator, "so_saleorder_b.csendstockorgid",
    // "so_saleorder_b.csendstordocid");
    // sendstordoc.addEditorListener();
  }
}
