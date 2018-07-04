package nc.ui.so.m4331.billui.view.dlg;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.pub.DeliveryVoUtil;
import nc.vo.so.pub.SOItemKey;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.EnumRefRegisterInfo;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;

/**
 * 
 * 
 * @since 6.1
 * @version 2013-01-18 12:12:16
 * @author liangjm
 */
public class DeliveryQueryDlg implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initRefCondition(condDLGDelegator);
    this.processBodyItem(condDLGDelegator);
    this.setDefaulValue(condDLGDelegator);
    // ����Ȩ��
    condDLGDelegator.setPowerEnable(true);
    // ����֯Ȩ��
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });
    // ������״̬��ö��
    this.processBillStatusCond(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  /**
   * ������״̬��ö��
   * 
   * @param condDLGDelegator
   */
  private void processBillStatusCond(QueryConditionDLGDelegator condDLGDelegator) {
    DeliveryVoUtil voutil = new DeliveryVoUtil();
    EnumRefRegisterInfo info =
        new EnumRefRegisterInfo(voutil.getBillStatusName(),
            voutil.getBillStatusValue());
    condDLGDelegator.setComboxItem(null, DeliveryHVO.FSTATUSFLAG, info);
  }

  private void initRefCondition(QueryConditionDLGDelegator condDLGDelegator) {
    // �������������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Delivery.getCode());
    trantype.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, DeliveryHVO.PK_ORG);

    String saleorgkey = DeliveryBVO.MAINMETAPATH + DeliveryBVO.CSALEORGID;
    String sendstockorgkey =
        DeliveryBVO.MAINMETAPATH + DeliveryBVO.CSENDSTOCKORGID;
    String instockorgkey = DeliveryBVO.MAINMETAPATH + DeliveryBVO.CINSTOCKORGID;
    // �ͻ�,�ͻ��������࣬�ͻ����۷��� ����������֯����
    filterutil.addFilterMaps(new String[] {
      DeliveryBVO.MAINMETAPATH + DeliveryBVO.CORDERCUSTID,
      "cdeliverybid.cordercustid.pk_custclass",
      "cdeliverybid.cordercustid.sales.pk_custsaleclass"
    }, saleorgkey);
    // ���ϣ����ϻ������࣬�������۷���,�����ֿ� ���շ��������֯����
    filterutil.addFilterMaps(new String[] {
      "cdeliverybid.cmaterialid", "cdeliverybid.cmaterialid.pk_marbasclass",
      "cdeliverybid.cmaterialvid.materialsale.pk_marsaleclass",
      "cdeliverybid.csendstordocid"
    }, sendstockorgkey);
    // �ջ��ֿ� �����ջ������֯����
    filterutil.addFilterMaps(new String[] {
      "cdeliverybid.cinstordocid"
    }, instockorgkey);
    filterutil.removeFilterMaps(new String[] {
      "cdeliverybid.csendstockorgid", "cdeliverybid.cinstockorgid",
      "cdeliverybid.csaleorgid","cdeliverybid.cmffileid","cdeliverybid.cmffileid.vskucode"
    });
   
    
    //�������۲���
    QDeptFilter deptFilter = QDeptFilter.createDeptFilterOfTR(
    condDLGDelegator,DeliveryHVO.CSENDDEPTID);
    deptFilter.setPk_orgCode(DeliveryHVO.PK_ORG);
    deptFilter.addEditorListener();

    // ����ҵ��Ա
    QPsndocFilter psnFilter = QPsndocFilter.createQPsndocFilterOfTR(
    condDLGDelegator, DeliveryHVO.CSENDEMPLOYEEID);
    psnFilter.setPk_orgCode(DeliveryHVO.PK_ORG);
    psnFilter.addEditorListener();
    filterutil.addFilterMapsListeners();
    
    // ����ҵ��Ա
    QPsndocFilter csupercargoidFilter = QPsndocFilter.createQPsndocFilterOfTR(
    condDLGDelegator, "cdeliverybid.csupercargoid");
    csupercargoidFilter.setPk_orgCode(DeliveryHVO.PK_ORG);
    csupercargoidFilter.addEditorListener();
    filterutil.addFilterMapsListeners();
    
    new QFfileFilterByMaterCode(condDLGDelegator, "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid.vskucode").addEditorListener();


    // // ���������֯����
    // QStockOrgFilter csendstockorgid =
    // new QStockOrgFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSENDSTOCKORGID);
    // csendstockorgid.filter();
    // // �ͻ�����
    // QCustomerFilter cordercustid =
    // new QCustomerFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CORDERCUSTID);
    // cordercustid.setPk_orgCode(DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSALEORGID);
    // cordercustid.addEditorListener();

    // // ������
    // QCarrierFilter carrier =
    // new QCarrierFilter(condDLGDelegator, "cdeliverybid.ctranscustid");
    // carrier.addEditorListener();
    //
    // // �ͻ��������ࣺ���ռ������ϻ�������
    // QCustBaseClassFilter custbasclass =
    // new QCustBaseClassFilter(condDLGDelegator,
    // "cdeliverybid.cordercustid.pk_custclass");
    // cordercustid.setPk_orgCode(DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSALEORGID);
    // custbasclass.addEditorListener();
    //
    // // �ͻ����۷��ࣺ���ռ������ϻ�������
    // QCustSaleClassFilter custsaleclass =
    // new QCustSaleClassFilter(condDLGDelegator,
    // "cdeliverybid.cordercustid.sales.pk_custsaleclass");
    // cordercustid.setPk_orgCode(DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSALEORGID);
    // custsaleclass.addEditorListener();

    // // �ջ��ͻ�����
    // QCustomerFilter creceivecustid =
    // new QCustomerFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CRECEIVECUSTID);
    // creceivecustid.addEditorListener();
    // // �ջ������֯����
    // QStockOrgFilter cinstockorgid =
    // new QStockOrgFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CINSTOCKORGID);
    // cinstockorgid.filter();

    // // Ѻ��Ա
    // QPsndocFilter supercargoid =
    // new QPsndocFilter(condDLGDelegator, "cdeliverybid.csupercargoid");
    // supercargoid.addEditorListener();

    // // ���ϱ���:���շ��������֯�ɼ������ϣ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    // QMarterialFilter marteral =
    // new QMarterialFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSENDSTOCKORGID, "cdeliverybid.cmaterialid");
    // marteral.addEditorListener();
    //
    // // ���ϻ������ࣺ���ռ������ϻ�������
    // QMarbasclassFilter marbasclass =
    // new QMarbasclassFilter(condDLGDelegator,
    // "cdeliverybid.cmaterialid.pk_marbasclass");
    // marbasclass.setPk_orgCode(DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSENDSTOCKORGID);
    // marbasclass.addEditorListener();
    //
    // // �������۷��ࣺ���ռ������ϻ�������
    // QMarSaleClassFilter marsaleclass =
    // new QMarSaleClassFilter(condDLGDelegator,
    // "cdeliverybid.cmaterialvid.materialsale.pk_marsaleclass");
    // marsaleclass.setPk_orgCode(DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSENDSTOCKORGID);
    // marsaleclass.addEditorListener();
    //
    // // ����·��
    // QRouteFilter routerfilter =
    // new QRouteFilter(condDLGDelegator, DeliveryHVO.CTRANSPORTROUTEID);
    // routerfilter.addEditorListener();

    condDLGDelegator.setPowerEnable(true);
  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // ��Ʊ��֯
    condDLGDelegator.addRedundancyInfo(DeliveryHVO.PK_ORG,
        "cdeliverybid.pk_org");
    // ��������
    condDLGDelegator.addRedundancyInfo(DeliveryHVO.DBILLDATE,
        "cdeliverybid.dbilldate");
  }

  private void setDefaulValue(QueryConditionDLGDelegator condDLGDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (defaultOrg != null && defaultOrg.trim().length() > 0) {
      condDLGDelegator.setDefaultValue(DeliveryHVO.PK_ORG, defaultOrg);
    }
  }
}
