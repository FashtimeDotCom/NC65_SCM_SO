package nc.ui.so.m30.sobalance.view;

import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QPaytermFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.pub.SOItemKey;

public class SobalanceQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // ���ø��Ի�����Ĭ������֯
    this.setDefaultPk_org(condDLGDelegator);
    // ��ʼ�γ�����Լ��
    this.initFilterRef(condDLGDelegator);
    // �����ӱ������ֶ�
    // this.processBodyItem(condDLGDelegator);

    // ����Ȩ��
    condDLGDelegator.setPowerEnable(true);

    // ����֯Ȩ��
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });

    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {

    // �ͻ�:�����ѯһ��������֯�����ݣ���ɲ��ո�������֯�ɼ��Ŀͻ���������ռ��ŷ�Χ�ͻ�������
    QCustomerFilter cust =
        new QCustomerFilter(condDLGDelegator, SoBalanceHVO.CCUSTOMERID);
    cust.setPk_orgCode(SoBalanceHVO.PK_ORG);
    cust.addEditorListener();

    // ��Ʊ�ͻ�:�����ѯһ��������֯�����ݣ���ɲ��ո�������֯�ɼ��Ŀͻ���������ռ��ŷ�Χ�ͻ�������
    QCustomerFilter invoiceCust =
        new QCustomerFilter(condDLGDelegator, SoBalanceHVO.CINVOICECUSTID);
    invoiceCust.setPk_orgCode(SoBalanceHVO.PK_ORG);
    invoiceCust.addEditorListener();

    // ����ҵ��Ա������������֯�ɼ�����Ա��������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QPsndocFilter employee =QPsndocFilter.createQPsndocFilterOfSO(
        condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode(SoBalanceHVO.PK_ORG);
    employee.addEditorListener();

    // ���۲���: ����������֯�ɼ��Ĳ��ţ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QDeptFilter dept = QDeptFilter.createDeptFilterOfSO(
        condDLGDelegator, SaleOrderHVO.CDEPTID);
    dept.setPk_orgCode(SoBalanceHVO.PK_ORG);
    dept.addEditorListener();
    

    // �ո���Э�飺����������֯�ɼ��Ŀͻ���������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QPaytermFilter payterm =
        new QPaytermFilter(condDLGDelegator, SoBalanceHVO.CPAYTERMID);
    payterm.setPk_orgCode(SoBalanceHVO.PK_ORG);
    payterm.addEditorListener();
  }

  // private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
  // // ������֯
  // condDLGDelegator.addRedundancyInfo(SoBalanceHVO.PK_ORG,
  // "so_balance_b.pk_org");
  // }

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
