package nc.ui.so.m38.report;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.iufo.freereport.extend.DefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QCustBaseClassFilter;
import nc.ui.scmpub.query.refregion.QCustSaleClassFilter;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.report.SCMReportQueryConUtil;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.SOConstant;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;
import com.ufida.report.free.plugin.param.ReportVariables;

/**
 * Ԥ����ִ�в�ѯ��ť��Ӧ��
 * 
 * @since 6.0
 * @version 2010-12-30 ����09:44:03
 * @author ô��
 */
@SuppressWarnings("restriction")
public class ReportQueryAction extends DefaultQueryAction {

  /**
   * ����ֶ�
   */
  private static final String[] MNYKEYS = {
    // ��˰�ϼ�
      PreOrderBVO.NORIGTAXMNY
    };

  /**
   * �����ֶ�
   */
  private static final String[] NUMKEYS = {
    // ������\�ۼư���������
    PreOrderBVO.NNUM, PreOrderBVO.NARRNUM
  };

  /**
   * �����ֶ�
   */
  private static final String[] PRICEMNYKEYS = {
    // ����˰����
      PreOrderBVO.NORIGTAXNETPRICE
    };

  /**
   * �����ֶ�
   */
  private static final String[] TOTALKEYS = {
    // ������\�ۼư���������
    PreOrderBVO.NNUM, PreOrderBVO.NARRNUM,
    // ��˰�ϼ�
    PreOrderBVO.NORIGTAXMNY
  };

  // ��ѯ�Ի������
  private QueryConditionDLGDelegator delegator;

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {

    IQueryCondition bascon =
        super.doQueryByScheme(parent, context, reportModel, queryScheme);
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(PreOrderHVO.DBILLDATE, "body_dbilldate");
    conutil.addRedundancyInfo(PreOrderHVO.PK_ORG, "body_pk_org");
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  @Override
  protected IQueryCondition createQueryCondition(IContext context) {

    // Ȩ��
    ReportPermissionUtils utils = new ReportPermissionUtils(context);
    utils.setMainBeanClass(PreOrderHVO.class);

    ReportQueryCondition condition = new ReportQueryCondition(true);

    ReportScaleProcess process = new ReportScaleProcess();
    // ����
    process
        .setMnyDigits(PreOrderBVO.CORIGCURRENCYID, ReportQueryAction.MNYKEYS);
    // ��������
    process.setNumDigits(PreOrderBVO.CUNITID, ReportQueryAction.NUMKEYS);
    // ���۾���
    process.setPriceDigits(ReportQueryAction.PRICEMNYKEYS,PreOrderBVO.CORIGCURRENCYID);

    process.setTotalFields(ReportQueryAction.TOTALKEYS);

    condition.setBusiFormat(process);
    return condition;
  }

  @Override
  protected QueryConditionDLG createQueryDlg(Container parent, TemplateInfo ti,
      IContext context, IQueryCondition oldCondition) {
    if (this.delegator == null) {
      this.delegator = new QueryConditionDLGDelegator(parent, ti);
    }
    // Ϊ��ѯģ����ֶβ��յȹ���
    this.setFilter();
    // ���ø��Ի�����Ĭ������֯
    this.setDefaultPk_org();
    // �����ӱ������ֶ�
    this.processBodyItem();
    // Ȩ�޹���
    this.processPowerFilter();
    return this.delegator.getQueryConditionDLG();

  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {

    // ���ò�ѯ����

    IQueryCondition bascon =
        super.setQueryResult(condition, queryDlg, reportModel, context);
    // �����ӱ������ֶ�
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(PreOrderHVO.DBILLDATE, "body_dbilldate");
    conutil.addRedundancyInfo(PreOrderHVO.PK_ORG, "body_pk_org");

    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();
    conutil.changeCustSaleClassCon(condition, conds,
        "this.ccustomerid.sales.pk_custsaleclass", "ccustomerid");

    IQueryScheme queryScheme = queryDlg.getQueryScheme();
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);

  }

  @Override
  protected IQueryCondition showQueryDialog(Container parent, IContext context,
      AbsAnaReportModel reportModel, TemplateInfo tempinfo,
      IQueryCondition oldCondition) {
    QueryConditionDLG queryDlg =
        this.getQueryConditionDlg(parent, context, reportModel, oldCondition);
    QueryConditionDLGDelegator dlgDelegator = this.getDLGDelegator(queryDlg);
    if (dlgDelegator.showModal() == UIDialog.ID_OK) {
      IQueryCondition condition = this.createQueryCondition(context);
      condition =
          this.setQueryResult(condition, queryDlg, reportModel, context);
      return condition;
    }
    return new BaseQueryCondition(false);
  }

  private QueryConditionDLGDelegator getDLGDelegator(QueryConditionDLG queryDlg) {
    QueryConditionDLGDelegator condDLGDelegator =
        new QueryConditionDLGDelegator(queryDlg);

    return condDLGDelegator;
  }

  private void processBodyItem() {
    // ������֯
    this.delegator
        .addRedundancyInfo(PreOrderHVO.PK_ORG, "so_preorder_b.pk_org");
    // ��������
    this.delegator.addRedundancyInfo(PreOrderHVO.DBILLDATE,
        "so_preorder_b.dbilldate");
  }

  private void processPowerFilter() {
    this.delegator.registerNeedPermissionOrgFieldCodes(new String[] {
      PreOrderHVO.PK_ORG
    });
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put("this.ccustomerid.code", PreOrderHVO.CCUSTOMERID);
    columnMapping.put(PreOrderHVO.CDEPTID, PreOrderHVO.CDEPTID);
    columnMapping.put(PreOrderHVO.CEMPLOYEEID, PreOrderHVO.CEMPLOYEEID);
    this.delegator.registerRefPowerFilterInfo(PreOrderHVO.class, columnMapping);

    Map<String, String> bcolumnMapping = new HashMap<String, String>();
    bcolumnMapping.put("this.cmaterialvid.code", PreOrderBVO.CMATERIALID);
    this.delegator
        .registerRefPowerFilterInfo(PreOrderBVO.class, bcolumnMapping);
  }

  private void setDefaultPk_org() {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (defaultOrg != null && defaultOrg.trim().length() > 0) {
      this.delegator.setDefaultValue(SaleOrderHVO.PK_ORG, defaultOrg);
    }
  }

  private void setFilter() {

    // Ԥ�����������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(this.delegator, SOBillType.PreOrder.getCode());
    trantype.filter();

    // �ͻ�
    QCustomerFilter cust =
        new QCustomerFilter(this.delegator, "this.ccustomerid.code");
    cust.addEditorListener();

    // ���۲���
    QDeptFilter dept = QDeptFilter.createDeptFilterOfSO(this.delegator, PreOrderHVO.CDEPTID);
    dept.setPk_orgCode(PreOrderHVO.PK_ORG);
    dept.addEditorListener();

    // �ͻ���������
    QCustBaseClassFilter marSaleClass =
        new QCustBaseClassFilter(this.delegator,
            "this.ccustomerid.pk_custclass");
    marSaleClass.setPk_orgCode(PreOrderHVO.PK_ORG);
    marSaleClass.addEditorListener();

    // �ͻ����۷���
    QCustSaleClassFilter custsaleclass =
        new QCustSaleClassFilter(this.delegator,
            "this.ccustomerid.sales.pk_custsaleclass");
    custsaleclass.setPk_orgCode(PreOrderHVO.PK_ORG);
    custsaleclass.addEditorListener();

    // ����ҵ��Ա������������֯�ɼ�����Ա��������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QPsndocFilter employee =
    		QPsndocFilter.createQPsndocFilterOfSO(this.delegator, PreOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode(PreOrderHVO.PK_ORG);
    employee.addEditorListener();

    // ���ϱ���:����������֯�ɼ������ϣ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QMarterialFilter marteral =
        new QMarterialFilter(this.delegator, PreOrderHVO.PK_ORG,
            "this.cmaterialvid.code");
    marteral.addEditorListener();

    // ���ϻ������ࣺ���ռ������ϻ�������
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(this.delegator,
            "this.cmaterialvid.pk_marbasclass");
    marbasclass.setPk_orgCode(PreOrderHVO.PK_ORG);
    marbasclass.addEditorListener();

    // �������۷���
    QMarSaleClassFilter marsaleclass =
        new QMarSaleClassFilter(this.delegator,
            "this.cmaterialvid.materialsale.pk_marsaleclass");
    marsaleclass.setPk_orgCode(PreOrderHVO.PK_ORG);
    marsaleclass.addEditorListener();
  }
}
