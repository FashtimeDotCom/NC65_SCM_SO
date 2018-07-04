package nc.ui.so.m38.billui.query;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.SOItemKey;

import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;

/**
 * ��ѯ�����Ի���
 * 
 * @since 6.1
 * @version 2013-01-17 14:54:25
 * @author liangjm
 */
public class PreOrderQryCondDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // ���ø��Ի�����Ĭ������֯
    this.setDefaultPk_org(dlgDelegator);
    // ��ʼ������Լ������
    this.initRefCondition(dlgDelegator);
    // ��ʼ�����������ֶ�
    this.initBodyRedundancyItem(dlgDelegator);

    // ����Ȩ��
    dlgDelegator.setPowerEnable(true);

    // ����֯Ȩ��
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initBodyRedundancyItem(QueryConditionDLGDelegator dlgDelegator) {
    // ������֯
    dlgDelegator.addRedundancyInfo(PreOrderHVO.PK_ORG, "so_preorder_b.pk_org");
    // ��������
    dlgDelegator.addRedundancyInfo(PreOrderHVO.DBILLDATE,
        "so_preorder_b.dbilldate");
  }

  private void initRefCondition(QueryConditionDLGDelegator dlgDelegator) {
    // Ԥ�����������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(dlgDelegator, SOBillType.PreOrder.getCode());
    trantype.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(dlgDelegator, PreOrderHVO.PK_ORG);
    filterutil.removeFilterMaps(new String[] {
      "so_preorder_b.csettleorgid", "so_preorder_b.csendstockorgid"
    });

    filterutil.addFilterMapsListeners();

    // // �ͻ�
    // QCustomerFilter cust =
    // new QCustomerFilter(dlgDelegator, PreOrderHVO.CCUSTOMERID);
    // cust.addEditorListener();
    //
    // // ���۲���
    // QDeptFilter dept = new QDeptFilter(dlgDelegator, PreOrderHVO.CDEPTID);
    // dept.setPk_orgCode(PreOrderHVO.PK_ORG);
    // dept.addEditorListener();
    //
    // // ���ϻ�������
    // QMarbasclassFilter marclass =
    // new QMarbasclassFilter(dlgDelegator,
    // "so_preorder_b.cmaterialid.pk_marbasclass");
    // marclass.addEditorListener();
    // marclass.setPk_orgCode(PreOrderHVO.PK_ORG);
    // marclass.filterByGroup();
    //
    // // �������۷���
    // QMarSaleClassFilter marSaleClass =
    // new QMarSaleClassFilter(dlgDelegator,
    // "so_preorder_b.cmaterialvid.materialsale.pk_marsaleclass");
    // marSaleClass.setPk_orgCode(PreOrderHVO.PK_ORG);
    // marSaleClass.addEditorListener();
    //
    // // ���ϱ���
    // QMarterialFilter marteral =
    // new QMarterialFilter(dlgDelegator, PreOrderHVO.PK_ORG,
    // "so_preorder_b.cmaterialid");
    // marteral.addEditorListener();
    //
    // // ����ҵ��Ա
    // QPsndocFilter employee =
    // new QPsndocFilter(dlgDelegator, PreOrderHVO.CEMPLOYEEID);
    // employee.setPk_orgCode(PreOrderHVO.PK_ORG);
    // employee.addEditorListener();
    //
    // // ���������֯
    // QStockOrgFilter stockOrg =
    // new QStockOrgFilter(dlgDelegator, "so_preorder_b.csendstockorgid");
    // stockOrg.filter();
  }

  private void setDefaultPk_org(QueryConditionDLGDelegator dlgDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (defaultOrg != null && defaultOrg.trim().length() > 0) {
      dlgDelegator.setDefaultValue(SaleOrderHVO.PK_ORG, defaultOrg);
    }
  }

}
