package nc.ui.so.m4331.report;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.report.SCMReportQueryConUtil;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;

import nc.itf.iufo.freereport.extend.IQueryCondition;

import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess;

import nc.ui.iufo.freereport.extend.DefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QCustBaseClassFilter;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;

/**
 * ������ִ�в�ѯ
 *
 * @since 6.0
 * @version 2011-3-14 ����03:04:27
 * @author ô��
 */
/**
 * 
 * @since 6.0
 * @version 2011-8-29 ����12:23:16
 * @author ô��
 */
@SuppressWarnings("restriction")
public class ReportQueryAction extends DefaultQueryAction {

  /**
   * �����ֶ�
   */
  private static final String[] NUMKEYS = {
    // ������\Ӧ��������
    DeliveryBVO.NNUM, DeliveryBVO.NTOTALOUTNUM, DeliveryBVO.NTRANSLOSSNUM,
    DeliveryBVO.NTOTALTRANSNUM
  };

  /**
   * �ϼ��ֶ�
   */
  private static final String[] TOTALKEYS = {
    // ������\Ӧ��������
    DeliveryBVO.NNUM, DeliveryBVO.NTOTALOUTNUM, DeliveryBVO.NTRANSLOSSNUM,
    DeliveryBVO.NTOTALTRANSNUM, DeliveryBVO.NASTNUM, DeliveryBVO.NWEIGHT,
    DeliveryBVO.NVOLUME, DeliveryBVO.NPIECE
  };

  // ��ѯ�Ի������
  private QueryConditionDLGDelegator delegator;

  @Override
  protected IQueryCondition createQueryCondition(IContext context) {

    // Ȩ��
    ReportPermissionUtils utils = new ReportPermissionUtils(context);
    utils.setMainBeanClass(DeliveryHVO.class);

    ReportQueryCondition condition = new ReportQueryCondition(true);

    ReportScaleProcess process = new ReportScaleProcess();

    // ����������
    process.setNumDigits(DeliveryBVO.CUNITID, ReportQueryAction.NUMKEYS);

    // ����������
    process.setNumDigits(DeliveryBVO.CASTUNITID, new String[] {
      DeliveryBVO.NASTNUM
    });
    // ��������
    process.setStandWeightDigits(new String[] {
      DeliveryBVO.NWEIGHT
    });
    // �������
    process.setStandardVolumnDigits(new String[] {
      DeliveryBVO.NVOLUME
    });
    // ��������
    process.setUnitDigits(DeliveryBVO.CMATERIALVID, new String[] {
      DeliveryBVO.NPIECE
    });

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

    // �����ӱ������ֶ�
    this.processBodyItem();
    // Ȩ�޹���
    this.processPowerFilter();
    return this.delegator.getQueryConditionDLG();
  }

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    IQueryCondition bascon =
        super.doQueryByScheme(parent, context, reportModel, queryScheme);
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(DeliveryHVO.DBILLDATE, "body_dbilldate");
    conutil.addRedundancyInfo(DeliveryHVO.PK_ORG, "body_pk_org");
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  private void processBodyItem() {
    // ������֯
    this.delegator
        .addRedundancyInfo(DeliveryHVO.PK_ORG, "so_delivery_b.pk_org");
    // ��������
    this.delegator.addRedundancyInfo(DeliveryHVO.DBILLDATE,
        "so_delivery_b.dbilldate");
  }

  private void processPowerFilter() {
    this.delegator.registerNeedPermissionOrgFieldCodes(new String[] {
      DeliveryHVO.PK_ORG
    });

    Map<String, String> columnMapping = new HashMap<String, String>();

    columnMapping.put("this.cordercustid.code", DeliveryBVO.CORDERCUSTID);
    columnMapping.put("this.creceivecustid.code", DeliveryBVO.CRECEIVECUSTID);
    columnMapping.put(DeliveryBVO.CEMPLOYEEID, DeliveryBVO.CEMPLOYEEID);
    columnMapping.put(DeliveryBVO.CDEPTID, DeliveryBVO.CDEPTID);

    columnMapping.put("this.cmaterialvid.code", DeliveryBVO.CMATERIALID);
    this.delegator.registerRefPowerFilterInfo(DeliveryBVO.class, columnMapping);
  }

  private void setFilter() {
    // �������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(this.delegator, SOBillType.Delivery.getCode());
    trantype.filter();

    // �ͻ�:�����ѯһ��������֯�����ݣ���ɲ��ո�������֯�ɼ��Ŀͻ���������ռ��ŷ�Χ�ͻ�������
    QCustomerFilter cust =
        new QCustomerFilter(this.delegator, "this.cordercustid.code");
    cust.setPk_orgCode(DeliveryHVO.PK_ORG);
    cust.addEditorListener();

    // �ͻ���������
    QCustBaseClassFilter marSaleClass =
        new QCustBaseClassFilter(this.delegator,
            "this.cordercustid.pk_custclass");
    marSaleClass.setPk_orgCode(DeliveryHVO.PK_ORG);
    marSaleClass.addEditorListener();

    // �ջ��ͻ�
    QCustomerFilter reccust =
        new QCustomerFilter(this.delegator, "this.creceivecustid.code");
    reccust.setPk_orgCode(DeliveryHVO.PK_ORG);
    reccust.addEditorListener();

    // �������۲���
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfTR(this.delegator,
            DeliveryHVO.CSENDDEPTID);
    deptFilter.setPk_orgCode(DeliveryHVO.PK_ORG);
    deptFilter.addEditorListener();

    // ����ҵ��Ա
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfTR(this.delegator,
            DeliveryHVO.CSENDEMPLOYEEID);
    psnFilter.setPk_orgCode(DeliveryHVO.PK_ORG);
    psnFilter.addEditorListener();

    // ���ϱ���:����������֯�ɼ������ϣ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QMarterialFilter marteral =
        new QMarterialFilter(this.delegator, DeliveryHVO.PK_ORG,
            "this.cmaterialvid.code");
    marteral.addEditorListener();

    // ���ϻ������ࣺ���ռ������ϻ�������
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(this.delegator,
            "this.cmaterialvid.pk_marbasclass");
    marbasclass.setPk_orgCode(DeliveryHVO.PK_ORG);
    marbasclass.addEditorListener();

    // �������۷���
    QMarSaleClassFilter marsaleclass =
        new QMarSaleClassFilter(this.delegator,
            "this.cmaterialvid.materialsale.pk_marsaleclass");
    marsaleclass.setPk_orgCode(DeliveryHVO.PK_ORG);
    marsaleclass.addEditorListener();

    new QFfileFilterByMaterCode(this.delegator,
        "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid")
        .addEditorListener();
    new QFfileFilterByMaterCode(this.delegator,
        "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid.vskucode")
        .addEditorListener();
    
    
    //��Ŀ�������� NCdp205265254 �����Ӳֿ��ѯ�����޷�����
    // �����ֿⰴ��������֯����
    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(this.delegator, DeliveryHVO.PK_ORG);
    String sendstockorgkey =
        DeliveryBVO.MAINMETAPATH + DeliveryBVO.CSENDSTOCKORGID;
    filterutil.addFilterMaps(new String[] {
      "this.csendstordocid"
    }, sendstockorgkey);
    filterutil.addFilterMapsListeners();

  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
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
  protected IQueryCondition showQueryDialog(Container parent, IContext context,
      AbsAnaReportModel reportModel, TemplateInfo tempinfo,
      IQueryCondition oldCondition) {
    QueryConditionDLG queryDlg =
        this.getQueryConditionDlg(parent, context, reportModel, oldCondition);

    if (this.delegator.showModal() == UIDialog.ID_OK) {
      IQueryCondition condition = this.createQueryCondition(context);
      condition =
          this.setQueryResult(condition, queryDlg, reportModel, context);
      return condition;
    }
    return new BaseQueryCondition(false);
  }
}
