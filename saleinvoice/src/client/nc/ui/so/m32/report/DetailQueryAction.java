package nc.ui.so.m32.report;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.report.SCMReportQueryConUtil;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.report.DetailQryConditionVO;
import nc.vo.so.m4331.entity.DeliveryHVO;

import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.bs.pubapp.report.ReportPermissionUtils;
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
 * ���۷�Ʊִ�в�ѯ ��ѯǰ̨������
 * 
 * @since 6.3
 * @version 2012-12-21 ����01:05:48
 * @author yaogj
 */
public class DetailQueryAction extends DefaultQueryAction {

  /**
   * ����ֶ�
   */
  private static final String[] MNYKEYS = {
    // ��˰�ϼ�
    SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NTOTALINCOMEMNY,
    SaleInvoiceBVO.NTOTALPAYMNY, "nremainmny"
  };

  /**
   * �����ֶ�
   */
  private static final String[] NUMKEYS = {
    // ������\Ӧ��������
    SaleInvoiceBVO.NNUM, SaleInvoiceBVO.NTOTALINCOMENUM
  };

  /**
   * �����ֶ�
   */
  private static final String[] PRICEMNYKEYS = {
    SaleInvoiceBVO.NORIGTAXNETPRICE
  };

  /**
   * �ϼ��ֶ�
   */
  private static final String[] TOTALKEYS = {
    // ��˰�ϼ�
    SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NTOTALINCOMEMNY,
    SaleInvoiceBVO.NTOTALPAYMNY, "nremainmny",
    // ������\Ӧ��������
    SaleInvoiceBVO.NNUM, SaleInvoiceBVO.NTOTALINCOMENUM
  };

  // ��ѯ�Ի������
  private QueryConditionDLGDelegator delegator;

  /**
   * ��ѯ����
   */
  @Override
  public IQueryCondition doQueryByScheme(Container parent,
      com.ufida.dataset.IContext context,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel,
      IQueryScheme queryScheme) {

    IQueryCondition bascon =
        super.doQueryByScheme(parent, context, reportModel, queryScheme);
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(SaleInvoiceHVO.DBILLDATE, "body_dbilldate");
    conutil.addRedundancyInfo(SaleInvoiceHVO.PK_ORG, "body_pk_org");
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  private QueryConditionDLGDelegator getDLGDelegator(QueryConditionDLG queryDlg) {
    QueryConditionDLGDelegator condDLGDelegator =
        new QueryConditionDLGDelegator(queryDlg);

    return condDLGDelegator;
  }

  private void processPowerFilter() {
    this.delegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleInvoiceHVO.PK_ORG
    });
    Map<String, String> columnMapping = new HashMap<String, String>();
    // ��Ʊ�ͻ�
    columnMapping
        .put("this.cinvoicecustid.code", SaleInvoiceHVO.CINVOICECUSTID);

    this.delegator.registerRefPowerFilterInfo(SaleInvoiceHVO.class,
        columnMapping);

    Map<String, String> bcolumnMapping = new HashMap<String, String>();
    // �����ͻ�
    bcolumnMapping.put("this.cordercustid.code", SaleInvoiceBVO.CORDERCUSTID);
    bcolumnMapping.put("this.cmaterialvid.code", SaleInvoiceBVO.CMATERIALID);
    bcolumnMapping.put("so_saleinvoice_b.cdeptid", SaleInvoiceBVO.CDEPTID);
    bcolumnMapping.put("so_saleinvoice_b.cemployeeid",
        SaleInvoiceBVO.CEMPLOYEEID);
    this.delegator.registerRefPowerFilterInfo(SaleInvoiceBVO.class,
        bcolumnMapping);
  }

  private void setFilter() {

    // Ԥ�����������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(this.delegator, SOBillType.Invoice.getCode());
    trantype.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(this.delegator, SaleInvoiceHVO.PK_ORG);

    String saleorgkey = "so_saleinvoice_b.csaleorgid";
    filterutil.removeFilterMaps(new String[] {
      saleorgkey
    });
    // ���ϱ��� (��������֯����)\���ϻ������� (��������֯����)\�������۷��� (��������֯����)
    filterutil.addFilterMaps(new String[] {
      "this.cmaterialvid.code", "this.cmaterialvid.pk_marbasclass",
      "this.cmaterialvid.materialsale.pk_marsaleclass"
    }, saleorgkey);
    filterutil.addFilterMapsListeners();
    
    // ����ҵ��Ա������������֯�ɼ�����Ա��������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QPsndocFilter employee =
        QPsndocFilter.createQPsndocFilterOfSO(this.delegator,
            "so_saleinvoice_b.cemployeeid");
    employee.setPk_orgCode("so_saleinvoice_b.csaleorgid");
    employee.addEditorListener();

    // ���۲���: ����������֯�ɼ��Ĳ��ţ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QDeptFilter dept =
        QDeptFilter.createDeptFilterOfSO(this.delegator,
            "so_saleinvoice_b.cdeptid");
    dept.setPk_orgCode("so_saleinvoice_b.csaleorgid");
    dept.addEditorListener();

  }

  @Override
  protected IQueryCondition createQueryCondition(
      com.ufida.dataset.IContext context) {

    // Ȩ��
    ReportPermissionUtils utils = new ReportPermissionUtils(context);
    utils.setMainBeanClass(SaleInvoiceHVO.class);

    DetailQryConditionVO condition = new DetailQryConditionVO(true);

    ReportScaleProcess process = new ReportScaleProcess();
    // ����
    process.setMnyDigits(SaleInvoiceHVO.CORIGCURRENCYID,
        DetailQueryAction.MNYKEYS);
    // ��������
    process.setNumDigits(SaleInvoiceBVO.CUNITID, DetailQueryAction.NUMKEYS);
    // ���۾���
    process.setPriceDigits(DetailQueryAction.PRICEMNYKEYS,SaleInvoiceHVO.CORIGCURRENCYID);

    process.setTotalFields(DetailQueryAction.TOTALKEYS);
    condition.setBusiFormat(process);
    return condition;
  }

  @Override
  protected QueryConditionDLG createQueryDlg(Container parent, TemplateInfo ti,
      com.ufida.dataset.IContext context, IQueryCondition oldCondition) {
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
      QueryConditionDLG queryDlg,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel,
      com.ufida.dataset.IContext context) {
    IQueryCondition bascon =
        super.setQueryResult(condition, queryDlg, reportModel, context);
    // �����ӱ������ֶ�
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(DeliveryHVO.DBILLDATE, "body_dbilldate");
    conutil.addRedundancyInfo(DeliveryHVO.PK_ORG, "body_pk_org");

    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();
    conutil.changeCustSaleClassCon(condition, conds,
        "this.cordercustid.sales.pk_custsaleclass", "cordercustid");

    conutil.changeMarSaleClassCon(condition, conds,
        "this.cmaterialvid.materialsale.pk_marsaleclass", "cmaterialvid");
    IQueryScheme queryScheme = queryDlg.getQueryScheme();
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);

  }

  @Override
  protected IQueryCondition showQueryDialog(Container parent,
      com.ufida.dataset.IContext context,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel,
      TemplateInfo tempinfo, IQueryCondition oldCondition) {
    if (!SysInitGroupQuery.isICEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006008_0", "04006008-0152")/* ���ģ��δ���ã����������ֶ��޷���ѯ�� */);
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
    return new com.ufida.report.anareport.base.BaseQueryCondition(false);

  }
}
