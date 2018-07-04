package nc.ui.so.m4310.report;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.report.SCMReportQueryConUtil;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

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
import nc.ui.scmpub.query.refregion.QCustSaleClassFilter;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;

/**
 * ����ִ�в�ѯ
 * 
 * @since 6.0
 * @version 2011-3-14 ����03:04:27
 * @author ô��
 */
public class ReportQueryAction extends DefaultQueryAction {

  /**
   * �ۿ�
   */
  private static final String[] DISCOUNTRATE = {
    // �����ۿۡ���Ʒ�ۿ�
    SalequotationBVO.NDISCOUNTRATE, SalequotationBVO.NITEMDISCOUNTRATE,
  };

  /**
   * ����ֶ�
   */
  private static final String[] MNYKEYS = {
    // ��˰�ϼơ���˰���
    SalequotationBVO.NORIGTAXMNY, SalequotationBVO.NORIGMNY,
    // �ۿ۶�
    SalequotationBVO.NORIGDISCOUNT
  };

  /**
   * �����ֶ�
   */
  private static final String[] NUMKEYS = {
    // ����
      SalequotationBVO.NASSISTNUM
    };

  /**
   * �����ֶ�
   */
  private static final String[] PRICEMNYKEYS = {
    // ��˰���ۡ���˰����
    SalequotationBVO.NQTORIGNETPRICE, SalequotationBVO.NQTORIGTAXNETPRC,
    SalequotationBVO.NQTORIGPRICE, SalequotationBVO.NQTORIGTAXPRICE
  };

  private static final String[] TOTALKEYS = {
    // ��˰�ϼơ���˰���
    SalequotationBVO.NORIGTAXMNY, SalequotationBVO.NORIGMNY,
    // �ۿ۶�
    SalequotationBVO.NORIGDISCOUNT, SalequotationBVO.NASSISTNUM
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
    conutil.addRedundancyInfo(SalequotationHVO.PK_ORG, "body_pk_org");
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  @Override
  protected IQueryCondition createQueryCondition(IContext context) {
    // Ȩ��
    ReportPermissionUtils utils = new ReportPermissionUtils(context);
    utils.setMainBeanClass(SalequotationHVO.class);
    ReportQueryCondition condition = new ReportQueryCondition(true);

    ReportScaleProcess process = new ReportScaleProcess();
    // ����
    process.setMnyDigits(SalequotationHVO.PK_CURRTYPE,
        ReportQueryAction.MNYKEYS);
    // ��������
    process
        .setNumDigits(SalequotationBVO.CASTUNITID, ReportQueryAction.NUMKEYS);
    // ˰�ʾ���
    process.setTaxRateDigits(new String[] {
      SalequotationBVO.NTAXRATE
    });
    // �ۿ۾���
    process.setSaleDiscountDigits(new String[] {
      SalequotationBVO.NDISCOUNTRATE, SalequotationBVO.NITEMDISCOUNTRATE
    });

    // ���۾���
    process.setPriceDigits(ReportQueryAction.PRICEMNYKEYS,SalequotationHVO.PK_CURRTYPE);

    // ˰�ʾ���
    process.setTaxRateDigits(new String[] {
      SalequotationBVO.NTAXRATE
    });

    // �ۿ۾���
    process.setSaleDiscountDigits(ReportQueryAction.DISCOUNTRATE);

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
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {

    IQueryCondition bascon =
        super.setQueryResult(condition, queryDlg, reportModel, context);
    // �����ӱ������ֶ�
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(SalequotationHVO.PK_ORG, "body_pk_org");

    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();
    conutil.changeCustSaleClassCon(condition, conds,
        "this.pk_customer.sales.pk_custsaleclass", "pk_customer");

    conutil.changeMarSaleClassCon(condition, conds,
        "this.pk_material_v.materialsale.pk_marsaleclass", "pk_material_v");
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

  private void processBodyItem() {
    // ������֯
    this.delegator.addRedundancyInfo(SalequotationHVO.PK_ORG,
        "salequotationdetail.pk_org");
    // // ��������
    // this.delegator.addRedundancyInfo(SalequotationHVO.DBILLDATE,
    // "salequotationdetail.dbilldate");
  }

  private void processPowerFilter() {
    this.delegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SalequotationHVO.PK_ORG
    });

    Map<String, String> columnMapping = new HashMap<String, String>();

    columnMapping.put("this.pk_customer.code", SalequotationHVO.PK_CUSTOMER);
    columnMapping.put(SalequotationHVO.PK_DEPT, SalequotationHVO.PK_DEPT);
    columnMapping.put(SalequotationHVO.CEMPLOYEEID,
        SalequotationHVO.CEMPLOYEEID);
    this.delegator.registerRefPowerFilterInfo(SalequotationHVO.class,
        columnMapping);
    Map<String, String> BcolumnMapping = new HashMap<String, String>();
    BcolumnMapping.put("this.pk_material_v.code", SalequotationBVO.PK_MATERIAL);
    this.delegator.registerRefPowerFilterInfo(SalequotationBVO.class,
        BcolumnMapping);

  }

  private void setFilter() {

    // �������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(this.delegator, SOBillType.SaleQuotation.getCode());
    trantype.filter();

    //�������۲���
    QDeptFilter deptFilter = QDeptFilter.createDeptFilterOfSO(
        delegator, SalequotationHVO.PK_DEPT);
    deptFilter.setPk_orgCode(SalequotationHVO.PK_ORG);
    deptFilter.addEditorListener();

    // ����ҵ��Ա
    QPsndocFilter psnFilter = QPsndocFilter.createQPsndocFilterOfSO(
        delegator, SalequotationHVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(SalequotationHVO.PK_ORG);
    psnFilter.addEditorListener();
    
    // �ͻ�:�����ѯһ��������֯�����ݣ���ɲ��ո�������֯�ɼ��Ŀͻ���������ռ��ŷ�Χ�ͻ�������
    QCustomerFilter cust =
        new QCustomerFilter(this.delegator, "this.pk_customer.code");
    cust.setPk_orgCode(SalequotationHVO.PK_ORG);
    cust.addEditorListener();

    // �ͻ���������
    QCustBaseClassFilter marSaleClass =
        new QCustBaseClassFilter(this.delegator,
            "this.pk_customer.pk_custclass");
    marSaleClass.setPk_orgCode(SalequotationHVO.PK_ORG);
    marSaleClass.addEditorListener();
    // �ͻ����۷���
    QCustSaleClassFilter cusSaleClass =
        new QCustSaleClassFilter(this.delegator,
            "this.pk_customer.sales.pk_custsaleclass");
    cusSaleClass.setPk_orgCode(SalequotationHVO.PK_ORG);
    cusSaleClass.addEditorListener();

    // ���ϱ���:����������֯�ɼ������ϣ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QMarterialFilter marteral =
        new QMarterialFilter(this.delegator, SalequotationHVO.PK_ORG,
            "this.pk_material_v.code");
    marteral.addEditorListener();

    // ���ϻ������ࣺ���ռ������ϻ�������
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(this.delegator,
            "this.pk_material_v.pk_marbasclass");
    marbasclass.setPk_orgCode(SalequotationHVO.PK_ORG);
    marbasclass.addEditorListener();

    // �������۷���
    QMarSaleClassFilter marsaleclass =
        new QMarSaleClassFilter(this.delegator,
            "this.pk_material_v.materialsale.pk_marsaleclass");
    marsaleclass.setPk_orgCode(SalequotationHVO.PK_ORG);
    marsaleclass.addEditorListener();

  }
}
