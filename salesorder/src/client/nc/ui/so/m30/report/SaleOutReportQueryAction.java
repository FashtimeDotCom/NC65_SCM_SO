package nc.ui.so.m30.report;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.report.SCMReportQueryConUtil;
import nc.vo.scmpub.res.billtype.ICBillType;

import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess;

import nc.ui.iufo.freereport.extend.DefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;

/**
 * ���۳���ִ�в�ѯ
 * 
 * @since 6.0
 * @version 2011-3-14 ����03:04:27
 * @author ô��
 */
public class SaleOutReportQueryAction extends DefaultQueryAction {

  /**
   * ����ֶ�
   */
  private static final String[] MNYKEYS = {
    "ntotalpaymny", "ntotalsquarearmny", "nbalancenmny"
  };

  /**
   * �����ֶ�
   */
  private static final String[] NUMKEYS = {
    // ������\ǩ��������
    ICPubMetaNameConst.NNUM, SaleOutBodyVO.NACCUMOUTSIGNNUM,
    // ;��������\�ۼ��˻�����
    SaleOutBodyVO.NACCUMWASTNUM, SaleOutBodyVO.NREPLENISHEDNUM,
    // �ۼƳ����˻�����
    SaleOutBodyVO.NACCUMOUTBACKNUM, SaleOutBodyVO.ReWriteShouldNum,
    SaleOutBodyVO.NSIGNNUM, "nreceinnum"
  };

  private static final String[] TOTALKEYS = {
    // ������\ǩ��������
    ICPubMetaNameConst.NNUM,
    SaleOutBodyVO.NACCUMOUTSIGNNUM,
    // ;��������\�ۼ��˻�����
    SaleOutBodyVO.NACCUMWASTNUM,
    SaleOutBodyVO.NREPLENISHEDNUM,
    // �ۼƳ����˻�����
    SaleOutBodyVO.NACCUMOUTBACKNUM, SaleOutBodyVO.ReWriteShouldNum,
    SaleOutBodyVO.NSIGNNUM, "nreceinnum", "ntotalpaymny", "ntotalsquarearmny",
    "nbalancenmny"
  };

  // ��ѯ�Ի������
  private QueryConditionDLGDelegator delegator;

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {

    IQueryCondition bascon =
        super.doQueryByScheme(parent, context, reportModel, queryScheme);
    // �����ӱ������ֶ�
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    // conutil.addRedundancyInfo(ICPubMetaNameConst.DBILLDATE, "body_dbizdate");
    conutil.addRedundancyInfo(ICPubMetaNameConst.PK_ORG, "body_pk_org");
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  /**
   * 
   * 
   * @return ff
   */
  public QueryConditionDLGDelegator getDelegator() {
    return this.delegator;
  }

  /**
   * 
   * 
   * @param delegator
   */
  public void setDelegator(QueryConditionDLGDelegator delegator) {
    this.delegator = delegator;
  }

  @Override
  protected IQueryCondition createQueryCondition(IContext context) {
    // Ȩ��
    ReportPermissionUtils utils = new ReportPermissionUtils(context);
    utils.setMainBeanClass(SaleOutHeadVO.class);
    ReportQueryCondition condition = new ReportQueryCondition(true);

    ReportScaleProcess process = new ReportScaleProcess();
    // ����
    process.setMnyDigits(SaleOutBodyVO.CORIGCURRENCYID,
        SaleOutReportQueryAction.MNYKEYS);
    // ��������
    process.setNumDigits(ICPubMetaNameConst.CUNITID,
        SaleOutReportQueryAction.NUMKEYS);

    process.setTotalFields(SaleOutReportQueryAction.TOTALKEYS);

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
    // Ȩ�޹���
    this.processPowerFilter();
    return this.delegator.getQueryConditionDLG();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {

    IQueryCondition bascon =
        super.setQueryResult(condition, queryDlg, reportModel, context);
    // �����ӱ������ֶ�
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    // conutil.addRedundancyInfo(ICPubMetaNameConst.DBILLDATE, "body_dbizdate");
    conutil.addRedundancyInfo(ICPubMetaNameConst.PK_ORG, "body_pk_org");

    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();
    conutil.changeCustSaleClassCon(condition, conds,
        "this.ccustomerid.sales.pk_custsaleclass", "ccustomerid");

    conutil.changeMarSaleClassCon(condition, conds,
        "this.cmaterialvid.materialsale.pk_marsaleclass", "cmaterialvid");
    IQueryScheme queryScheme = queryDlg.getQueryScheme();
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  @Override
  protected IQueryCondition showQueryDialog(Container parent, IContext context,
      AbsAnaReportModel reportModel, TemplateInfo tempinfo,
      IQueryCondition oldCondition) {
    if (!SysInitGroupQuery.isICEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0470")/* ���ģ��δ���ã��޷�ִ�в�ѯ�� */);
    }
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

  private void processPowerFilter() {
    this.delegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleOutHeadVO.CSALEORGOID
    });
    Map<String, String> columnMapping = new HashMap<String, String>();

    columnMapping.put("this.ccustomerid.code", MetaNameConst.CCUSTOMERID);
    this.delegator.registerRefPowerFilterInfo(SaleOutHeadVO.class,
        columnMapping);

    Map<String, String> bcolumnMapping = new HashMap<String, String>();
    bcolumnMapping.put("this.cmaterialvid.code",
        ICPubMetaNameConst.CMATERIALOID);
    this.delegator.registerRefPowerFilterInfo(SaleOutBodyVO.class,
        bcolumnMapping);

  }

  private void setFilter() {
    String basekey = "csaleorgoid";

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(this.delegator, basekey);
    filterutil.removeFilterMaps(new String[] {
      "pk_org",
      "so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"
    });
    filterutil.addFilterMapsListeners();
    // �������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(this.delegator, ICBillType.SaleOut.getCode());
    trantype.filter();
    
    // ����ҵ��Ա������������֯�ɼ�����Ա��������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QPsndocFilter employee =
        QPsndocFilter.createQPsndocFilterOfSO(this.delegator, "cbizid");
    employee.setPk_orgCode("csaleorgoid");
    employee.addEditorListener();

    // ���۲���: ����������֯�ɼ��Ĳ��ţ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QDeptFilter dept =  QDeptFilter.createDeptFilterOfSO(this.delegator, "cdptid");
    dept.setPk_orgCode("csaleorgoid");
    dept.addEditorListener();
    
    new QFfileFilterByMaterCode(this.delegator, "cgeneralbid.cmaterialoid.code", "cgeneralbid.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(this.delegator, "cgeneralbid.cmaterialoid.code", "cgeneralbid.cffileid.vskucode").addEditorListener();
  }
}
