package nc.ui.so.report.invoicesummary;

import java.awt.Container;

import nc.bs.framework.common.NCLocator;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
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
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.ui.so.pub.query.refregion.QBatchCodeFilter;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.QryTempletVOWithInfo;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.querytemplate.queryscheme.QuerySchemeVO;
import nc.vo.scmpub.res.billtype.SOBillType;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreeReportContextKey;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * ���۷�Ʊִ�л��ܱ�������������
 * 
 * @since 6.3
 * @version 2012-10-18 13:44:03
 * @author ������
 */
public class InvSummarySubscribeAction extends DefaultSubscribeAction {

  private QueryConditionDLGDelegator m_delegator;

  private QueryConditionDLG m_queryDlg;

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
      this.m_queryDlg = this.m_delegator.getQueryConditionDLG();
      
      this.m_delegator.registerNeedPermissionOrgFieldCodes(new String[] {
          "pk_org"
         });
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

    // ���۷�Ʊ�������Ͳ���
    QTransTypeFilter trantype =
        new QTransTypeFilter(this.m_delegator, SOBillType.Invoice.getCode());
    trantype.filter();

    // ���۶�����������
    QTransTypeFilter ordertran =
        new QTransTypeFilter(this.m_delegator,
            "csaleinvoicebid.vfirsttrantype", SOBillType.Order.getCode());
    ordertran.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(this.m_delegator, "pk_org");
    filterutil.removeFilterMaps(new String[] {
      "csaleinvoicebid.csaleorgid", "csaleinvoicebid.csendstockorgid",
      "csaleinvoicebid.cmffileid","csaleinvoicebid.cmffileid.vskucode"
    });

    filterutil.addFilterMapsListeners();

    // �������ε���
    QBatchCodeFilter batch = new QBatchCodeFilter();
    batch.filter(this.m_delegator, "csaleinvoicebid.vbatchcode");

    new QFfileFilterByMaterCode(this.m_delegator,
        "csaleinvoicebid.cmaterialid.code", "csaleinvoicebid.cmffileid")
        .addEditorListener();
    new QFfileFilterByMaterCode(this.m_delegator,
        "csaleinvoicebid.cmaterialid.code",
        "csaleinvoicebid.cmffileid.vskucode").addEditorListener();

    // ����ҵ��Ա������������֯�ɼ�����Ա��������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QPsndocFilter employee =
        QPsndocFilter.createQPsndocFilterOfSO(this.m_delegator,
            "csaleinvoicebid.cemployeeid");
    employee.setPk_orgCode("csaleinvoicebid.csaleorgid");
    employee.addEditorListener();

    // ���۲���: ����������֯�ɼ��Ĳ��ţ�������֯�ǿ���Ψһ��,������ռ��ſɼ������Ͻ���¼��
    QDeptFilter dept =
        QDeptFilter.createDeptFilterOfSO(this.m_delegator,
            "csaleinvoicebid.cdeptid");
    dept.setPk_orgCode("csaleinvoicebid.csaleorgid");
    dept.addEditorListener();
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
