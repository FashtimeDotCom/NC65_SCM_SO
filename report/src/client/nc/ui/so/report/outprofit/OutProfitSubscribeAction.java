package nc.ui.so.report.outprofit;

import java.awt.Container;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.qrytemplate.IQueryTemplateQry;
import nc.scmmm.pub.scmpub.report.adapter.SCMRptBaseSubscribeCondition;
import nc.ui.iufo.ClientEnv;
import nc.ui.iufo.freereport.extend.DefaultSubscribeAction;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.ui.so.pub.query.refregion.QBatchCodeFilter;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.QryTempletVOWithInfo;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.querytemplate.queryscheme.QuerySchemeVO;
import nc.vo.so.report.outprofit.OutProfitViewVO;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreeReportContextKey;
import com.ufida.report.anareport.model.AbsAnaReportModel;
import com.ufida.zior.exception.MessageException;

/**
 * ���۳���ë��������������������
 * 
 * @since 6.3
 * @version 2012-08-28 19:52:27
 * @author ������
 */
public class OutProfitSubscribeAction extends DefaultSubscribeAction {

  private QueryConditionDLGDelegator m_delegator;

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

  /**
   * ��Ҫ���л�ȡ��ѯ�������
   * 
   * 
   * @param parent
   * @param context
   * @param reportModel
   * @param cond
   * @return m_queryDlg
   */
  @Override
  public QueryConditionDLG getQueryConditionDlg(Container parent,
      IContext context, AbsAnaReportModel reportModel,
      ISubscribeQueryCondition cond) {

    TemplateInfo tempinfo = this.getTempletInfo(context);
    if (this.hasQueryTemplet(tempinfo)) {
      if (null == this.m_delegator) {
        this.m_delegator = new QueryConditionDLGDelegator(parent, tempinfo);
      }
      // 1.�����ֶι���
      this.processFilter();
      
      //��֯����Ȩ��
      this.m_delegator.registerNeedPermissionOrgFieldCodes(new String[] {
          OutProfitViewVO.CSALEORGOID
        });
      
      this.m_queryDlg = this.m_delegator.getQueryConditionDLG();
      return this.m_queryDlg;
    }

    return this.m_queryDlg;
  }

  @Override
  protected ISubscribeQueryCondition getConditionFromDlg(
      ISubscribeQueryCondition oldCondition, QueryConditionDLG queryDlg,
      AbsAnaReportModel reportModel, IContext context) {
    IQueryScheme scheme = queryDlg.getQueryScheme();
    if (scheme == null) {
      return null;
    }
    QuerySchemeVO schemeVO =
        this.m_queryDlg.getQryCondEditor().getSelectedQuerySchemeVO();
    if (schemeVO != null) {
      scheme.put(IQueryScheme.KEY_NAME, schemeVO.getName());
    }
    SCMRptBaseSubscribeCondition result =
        new SCMRptBaseSubscribeCondition(scheme);
    return result;
  }

  private void processFilter() {
    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(this.m_delegator,
            OutProfitViewVO.CSALEORGOID);
    filterutil.addFilterMapsListeners();
    
    //�������ε���
    QBatchCodeFilter batch = new QBatchCodeFilter();
    batch.filter(this.m_delegator,"cgeneralbid.vbatchcode");
    
    // ����ҵ��Ա������������֯�ɼ�����Ա��������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QPsndocFilter employee =
        QPsndocFilter.createQPsndocFilterOfSO(this.m_delegator, "cbizid");
    employee.setPk_orgCode("csaleorgoid");
    employee.addEditorListener();

    // ���۲���: ����������֯�ɼ��Ĳ��ţ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QDeptFilter dept =  QDeptFilter.createDeptFilterOfSO(this.m_delegator, "cdptid");
    dept.setPk_orgCode("csaleorgoid");
    dept.addEditorListener();
    
    
    new QFfileFilterByMaterCode(this.m_delegator, "cgeneralbid.cmaterialoid.code", "cgeneralbid.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(this.m_delegator, "cgeneralbid.cmaterialoid.code", "cgeneralbid.cffileid.vskucode").addEditorListener();
    filterutil.removeFilterMaps(new String[] {
        "cgeneralbid.cffileid", "cgeneralbid.cffileid.vskucode"
      });
  }

  private TemplateInfo getTempletInfo(IContext context) {
    TemplateInfo tempinfo = new TemplateInfo();
    tempinfo.setPk_Org(ClientEnv.getInstance().getGroupID());
    tempinfo.setFunNode((String) context
        .getAttribute(FreeReportContextKey.REPORT_FUNCODE));
    tempinfo.setUserid(ClientEnv.getInstance().getLoginUserID());
    return tempinfo;
  }

  private boolean hasQueryTemplet(TemplateInfo ti) {
    IQueryTemplateQry qry =
        NCLocator.getInstance().lookup(IQueryTemplateQry.class);
    QryTempletVOWithInfo temp;
    try {
      temp = qry.findAndGetTemplateVO(ti);
      return temp != null;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return false;
  }
}
