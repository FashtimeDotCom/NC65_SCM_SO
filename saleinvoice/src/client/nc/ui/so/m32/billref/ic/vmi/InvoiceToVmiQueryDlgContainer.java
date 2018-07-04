package nc.ui.so.m32.billref.ic.vmi;

import nc.ui.ic.m50.m32.itf.IVmiSumQueryCondProcessorFor32;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.ui.so.pub.query.refregion.QBatchCodeFilter;
import nc.ui.uif2.editor.QueryTemplateContainer;
import nc.vo.ic.m50.entity.VmiSumGenerateParam;
import nc.vo.ic.m50.entity.VmiSumQueryConditionVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.uif2.LoginContext;

@SuppressWarnings("restriction")
public class InvoiceToVmiQueryDlgContainer {

  private IVmiSumQueryCondProcessorFor32 condProcessor;

  private LoginContext context;

  /** ��ѯ�Ի������ */
  private QueryConditionDLGDelegator qryCondDLGDelegator;

  /**
   *
   */
  private IQueryConditionDLGInitializer qryCondDLGInitializer;

  /** ��ѯģ������ */
  private QueryTemplateContainer templateContainer;

  public InvoiceToVmiQueryDlgContainer(LoginContext context) {
    this.context = context;
  }

  public IVmiSumQueryCondProcessorFor32 getCondProcessor() {
    if (this.condProcessor == null) {
      this.condProcessor =
          NCUILocator.getInstance().lookup(
              IVmiSumQueryCondProcessorFor32.class, NCModule.IC.getName());
    }
    return this.condProcessor;
  }

  public LoginContext getContext() {
    return this.context;
  }

  public QueryConditionDLGDelegator getQryCondDLGDelegator() {
    if (this.qryCondDLGDelegator == null) {
      String operator = AppContext.getInstance().getPkUser();
      String pk_group = AppContext.getInstance().getPkGroup();
      TemplateInfo tempinfo = new TemplateInfo();
      tempinfo.setPk_Org(pk_group);
      tempinfo.setFunNode("40060501");
      tempinfo.setUserid(operator);
      tempinfo.setNodekey("32toVMI_Q");
      tempinfo.setSealedDataShow(true);
      // ���ع�Ӧ���Ĳ�ѯ�Ի���
      this.qryCondDLGDelegator = this.createQryDLGDelegator(tempinfo);
      // ��ʼ������Լ������
      this.initRefCondition(this.qryCondDLGDelegator);
      // �����ӱ������ֶ�
      // this.processBodyItem(this.qryCondDLGDelegator);
      // ���ɸ�������
      this.qryCondDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());

      this.initQueryConditionDLG(this.qryCondDLGDelegator);
    }
    return this.qryCondDLGDelegator;
  }

  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return this.qryCondDLGInitializer;
  }

  public QueryTemplateContainer getTemplateContainer() {
    return this.templateContainer;
  }

  public VmiSumGenerateParam getVmiSumGenerateParam() {
    try {
      return this.getCondProcessor().getVmiSumGenerateParam();
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }
    return null;
  }

  public void setCondProcessor(IVmiSumQueryCondProcessorFor32 condProcessor) {
    this.condProcessor = condProcessor;
  }

  public void setContext(LoginContext context) {
    this.context = context;
  }

  public void setQryCondDLGDelegator(
      QueryConditionDLGDelegator qryCondDLGDelegator) {
    this.qryCondDLGDelegator = qryCondDLGDelegator;
  }

  public void setQryCondDLGInitializer(
      IQueryConditionDLGInitializer qryCondDLGInitializer) {
    this.qryCondDLGInitializer = qryCondDLGInitializer;
  }

  public void setTemplateContainer(QueryTemplateContainer templateContainer) {
    this.templateContainer = templateContainer;
  }

  /**
   * ������ѯ�Ի������
   * 
   * @param ti
   * @return
   */
  private QueryConditionDLGDelegator createQryDLGDelegator(TemplateInfo ti) {
    QueryConditionDLGDelegator dlgDelegator;
    if (this.getTemplateContainer() == null) {
      dlgDelegator = new QueryConditionDLGDelegator(this.context, ti);
    }
    else {
      dlgDelegator =
          new QueryConditionDLGDelegator(this.context, ti, this
              .getTemplateContainer().getQueryTempletLoader());
    }
    this.initQueryConditionDLG(dlgDelegator);
    return dlgDelegator;
  }

  private void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    if (this.getQryCondDLGInitializer() != null) {
      // ����֯Ȩ��
      dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
        SaleInvoiceBVO.MAINMETAPATH + SaleInvoiceBVO.CSENDSTOCKORGID
      });
      this.getQryCondDLGInitializer().initQueryConditionDLG(dlgDelegator);
    }
  }

  private void initRefCondition(QueryConditionDLGDelegator condDLGDelegator) {
    try {
      this.getCondProcessor().addVmiTransTypeFilter(condDLGDelegator,
          VmiSumQueryConditionVO.VMITRANTYPECODE, ICBillType.VmiSum.getCode());
      this.getCondProcessor()
          .addFilterForCalbody(condDLGDelegator,
              VmiSumQueryConditionVO.CVMIRULEHID,
              "csaleinvoicebid.csendstockorgid");
      this.getCondProcessor().addFilterForCwarehouseid(condDLGDelegator,
          VmiSumQueryConditionVO.CVMIRULEHID, "csaleinvoicebid.csendstordocid");
      this.getCondProcessor().addFilterForVtrantypecode(condDLGDelegator,
          VmiSumQueryConditionVO.CVMIRULEHID, SaleInvoiceHVO.CTRANTYPEID);
      this.getCondProcessor().addFilterForCvmicondition(condDLGDelegator,
          VmiSumQueryConditionVO.CVMIRULEHID,
          VmiSumQueryConditionVO.CVMICONDITIOIN);

      // ������֯�ֶ�
      String saleorgkey = "csaleinvoicebid.csaleorgid";
      // ���۷�Ʊ�������Ͳ���
      QTransTypeFilter trantype =
          new QTransTypeFilter(condDLGDelegator, SOBillType.Invoice.getCode());
      trantype.filter();

      // ��Ӧ��
      QSupplierFilter ordertran =
          new QSupplierFilter(condDLGDelegator, "csaleinvoicebid.cvendorid");
      ordertran.setPk_orgCode(SaleInvoiceHVO.PK_ORG);
      ordertran.filter();

      // �Ĵ湩Ӧ��
      QSupplierFilter cvmitran =
          new QSupplierFilter(condDLGDelegator, "csaleinvoicebid.cvmivenderid");
      cvmitran.setPk_orgCode(SaleInvoiceHVO.PK_ORG);
      cvmitran.filter();

      // ���ϱ��� (��������֯����)
      QMarterialFilter marteral =
          new QMarterialFilter(condDLGDelegator, saleorgkey,
              "csaleinvoicebid.cmaterialid.code");
      marteral.addEditorListener();

      // // ���ϻ������� (��������֯����)
      QMarbasclassFilter marbasclass =
          new QMarbasclassFilter(condDLGDelegator,
              "csaleinvoicebid.cmaterialid.pk_marbasclass");
      marbasclass.setPk_orgCode(saleorgkey);
      marbasclass.addEditorListener();

      // ����ҵ��Ա
      QPsndocFilter employee =
          new QPsndocFilter(condDLGDelegator, "csaleinvoicebid.cemployeeid");
      employee.setPk_orgCode(SaleInvoiceHVO.PK_ORG);
      employee.addEditorListener();

      // ���۲���
      QDeptFilter dept =
          new QDeptFilter(condDLGDelegator, "csaleinvoicebid.cdeptid");
      dept.setPk_orgCode(SaleInvoiceHVO.PK_ORG);
      dept.addEditorListener();

      // �ֿ�
      QWareHouseFilter warehouse =
          new QWareHouseFilter(condDLGDelegator,
              "csaleinvoicebid.csendstockorgid",
              "csaleinvoicebid.csendstordocid");
      warehouse.addEditorListener();

      // ��Ŀ
      QProjectFilter project =
          new QProjectFilter(condDLGDelegator, SaleInvoiceHVO.PK_ORG,
              "csaleinvoicebid.cprojectid");
      project.addEditorListener();
      
      
      //�������ε���
      QBatchCodeFilter batch = new QBatchCodeFilter();
      batch.filter(condDLGDelegator,"csaleinvoicebid.vbatchcode");
      
      new QFfileFilterByMaterCode(condDLGDelegator, "csaleinvoicebid.cmaterialid.code", "csaleinvoicebid.cmffileid").addEditorListener();
      new QFfileFilterByMaterCode(condDLGDelegator, "csaleinvoicebid.cmaterialid.code", "csaleinvoicebid.cmffileid.vskucode").addEditorListener();

    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }

  }

  // @SuppressWarnings("unused")
  // private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
  // // ������֯
  // condDLGDelegator.addRedundancyInfo(SaleInvoiceHVO.PK_ORG,
  // "so_saleinvoice_b.pk_org");
  // // ��������
  // condDLGDelegator.addRedundancyInfo(SaleInvoiceHVO.DBILLDATE,
  // "so_saleinvoice_b.dbilldate");
  // }

}
