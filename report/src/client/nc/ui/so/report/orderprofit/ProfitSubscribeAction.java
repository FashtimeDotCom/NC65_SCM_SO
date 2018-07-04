package nc.ui.so.report.orderprofit;

import java.awt.Container;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.qrytemplate.IQueryTemplateQry;
import nc.ui.iufo.ClientEnv;
import nc.ui.iufo.freereport.extend.DefaultSubscribeAction;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QCustSaleClassFilter;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.so.pub.query.refregion.QBatchCodeFilter;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.QryTempletVOWithInfo;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.querytemplate.queryscheme.QuerySchemeVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.report.paravo.ProfitContext;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.model.AbsAnaReportModel;
import com.ufida.zior.exception.MessageException;

/**
 * ��������������
 * 
 * @since 6.0
 * @version 2011-8-3 ����03:04:17
 * @author ô��
 */
@SuppressWarnings("restriction")
public class ProfitSubscribeAction extends DefaultSubscribeAction {

  private QueryConditionDLGDelegator delegator;

  private QueryConditionDLG m_queryDlg;
  
  
  @Override
  public ISubscribeQueryCondition doSubscribeAction(Container parent,
      IContext context, AbsAnaReportModel reportModel,
      ISubscribeQueryCondition oldCondition) {
    if (!SysInitGroupQuery.isAREnabled()) {
      throw new MessageException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006005_0", "04006005-0025")/* ����ģ��δ���ã��޷�ִ�в�ѯ�� */);
    }

    if (!SysInitGroupQuery.isICEnabled()) {
      throw new MessageException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006005_0", "04006005-0026")/* ���ģ��δ���ã��޷�ִ�в�ѯ�� */);
    }
    if (!SysInitGroupQuery.isIAEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006005_0", "04006005-0029")/*�������ģ��δ���ã��޷�ִ�в�ѯ��*/);
    }
    return super.doSubscribeAction(parent, context, reportModel, oldCondition);
  }

  @Override
  public QueryConditionDLG getQueryConditionDlg(Container parent,
      com.ufida.dataset.IContext context,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel,
      ISubscribeQueryCondition cond) {
    TemplateInfo tempinfo = this.getTempletInfo(context);
    if (this.hasQueryTemplet(tempinfo)) {
      if (null == this.delegator) {
        this.delegator = new QueryConditionDLGDelegator(parent, tempinfo);
      }
      // �����ֶι���
      this.initListener();
      // �����ӱ������ֶ�
      this.processBodyItem();

      // ����Ȩ���ֶι���
      this.processPowerFilter();
      this.m_queryDlg = this.delegator.getQueryConditionDLG();
      this.m_queryDlg.getQryCondEditor().getQueryContext().setMultiTB(true);

      // ��ʾ�ϴ����õ�����
      if (cond instanceof com.ufida.report.anareport.base.BaseSubscribeCondition) {
        com.ufida.report.anareport.base.BaseSubscribeCondition baseCond =
            (com.ufida.report.anareport.base.BaseSubscribeCondition) cond;
        // ��IQueryScheme���õ�������
        IQueryScheme scheme = baseCond.getScheme();
        if (scheme != null) {
          String qsName = scheme.getName();
          this.m_queryDlg.getQryCondEditor().setQsSelectedByName(qsName);
        }

      }
    }

    return this.m_queryDlg;
  }

  @Override
  protected ISubscribeQueryCondition getConditionFromDlg(
      ISubscribeQueryCondition oldCondition, QueryConditionDLG queryDlg,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel,
      com.ufida.dataset.IContext context) {
    IQueryScheme scheme = queryDlg.getQueryScheme();
    if (scheme == null) {
      return null;
    }
    QuerySchemeVO schemeVO =
        this.m_queryDlg.getQryCondEditor().getSelectedQuerySchemeVO();
    if (schemeVO != null) {
      scheme.put(IQueryScheme.KEY_NAME, schemeVO.getName());
    }
    ConditionVO[] conds =
        (ConditionVO[]) scheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);

    boolean issummaryconditionsnull = true;
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ProfitContext.SUMMARYCONDITIONS)) {
        if (null != cond.getValue()) {
          issummaryconditionsnull = false;

        }
      }
    }
    if (issummaryconditionsnull) {
      // ExceptionUtils.wrappBusinessException("������������Ϊ��");
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006005_0", "04006005-0024")/*������������Ϊ��*/);
    }
    com.ufida.report.anareport.base.BaseSubscribeCondition result =
        new com.ufida.report.anareport.base.BaseSubscribeCondition(scheme);
    return result;
  }

  private TemplateInfo getTempletInfo(com.ufida.dataset.IContext context) {
    TemplateInfo tempinfo = new TemplateInfo();
    tempinfo.setPk_Org(ClientEnv.getInstance().getGroupID());
    String key = com.ufida.report.anareport.FreeReportContextKey.REPORT_FUNCODE;
    tempinfo.setFunNode((String) context.getAttribute(key));
    tempinfo.setUserid(ClientEnv.getInstance().getLoginUserID());
    return tempinfo;
  }

  private boolean hasQueryTemplet(TemplateInfo ti) {
    IQueryTemplateQry qry =
        NCLocator.getInstance().lookup(IQueryTemplateQry.class);
    try {
      QryTempletVOWithInfo temp = qry.findAndGetTemplateVO(ti);
      return temp != null;
    }
    catch (BusinessException ex) {
      com.ufida.iufo.pub.tools.AppDebug.debug(ex);
    }
    return false;
  }

  /**
   * UAPĬ�ϼ����¼���ע��ĺ�ִ�У���˽�������֯�ı��¼������ŵ����������������ִ�У��Ӷ����Խ������ݺϷ���У��
   * 
   * @param delegator
   */
  private void initListener() {
    // ��Ʊ�ͻ�:�����ѯһ��������֯�����ݣ���ɲ��ո�������֯�ɼ��Ŀͻ���������ռ��ŷ�Χ�ͻ�������
    QCustomerFilter invoicecust =
        new QCustomerFilter(this.delegator, "cinvoicecustid");
    invoicecust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    invoicecust.addEditorListener();

    // �ͻ�:�����ѯһ��������֯�����ݣ���ɲ��ո�������֯�ɼ��Ŀͻ���������ռ��ŷ�Χ�ͻ�������
    QCustomerFilter cust = new QCustomerFilter(this.delegator, "ccustomerid");
    cust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    cust.addEditorListener();

    // �ͻ���������
    QMarSaleClassFilter marSaleClass =
        new QMarSaleClassFilter(this.delegator, "ccustomerid.pk_custclass");
    marSaleClass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marSaleClass.addEditorListener();

    // �ͻ����۷���
    QCustSaleClassFilter custsaleclass =
        new QCustSaleClassFilter(this.delegator,
            "ccustomerid.sales.pk_custsaleclass");
    custsaleclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    custsaleclass.addEditorListener();

    // ����ҵ��Ա������������֯�ɼ�����Ա��������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QPsndocFilter employee =
        QPsndocFilter.createQPsndocFilterOfSO(this.delegator, SaleOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
    employee.addEditorListener();

    // ���۲���: ����������֯�ɼ��Ĳ��ţ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QDeptFilter dept =  QDeptFilter.createDeptFilterOfSO(this.delegator, SaleOrderHVO.CDEPTID);
    dept.setPk_orgCode(SaleOrderHVO.PK_ORG);
    dept.addEditorListener();

    // ���ϱ���:����������֯�ɼ������ϣ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QMarterialFilter marteral =
        new QMarterialFilter(this.delegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid");
    marteral.addEditorListener();

    // ���ϻ������ࣺ���ռ������ϻ�������
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(this.delegator,
            "so_saleorder_b.cmaterialid.pk_marbasclass");
    marbasclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marbasclass.addEditorListener();

    // �������۷���
    QMarSaleClassFilter marsaleclass =
        new QMarSaleClassFilter(this.delegator,
            "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass");
    marsaleclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marsaleclass.addEditorListener();
    
    //�������ε���
    QBatchCodeFilter batch = new QBatchCodeFilter();
    batch.filter(this.delegator,"so_saleorder_b.vbatchcode"); 
  }

  private void processBodyItem() {
    // ������֯
    this.delegator.addRedundancyInfo(SaleOrderHVO.PK_ORG,
        "so_saleorder_b.pk_org");
    // ��������
    this.delegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        "so_saleorder_b.dbilldate");
  }

  private void processPowerFilter() {
    this.delegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleOrderHVO.PK_ORG
    });

  }

}
