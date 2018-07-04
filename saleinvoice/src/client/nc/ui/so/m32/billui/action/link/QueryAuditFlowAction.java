package nc.ui.so.m32.billui.action.link;

import nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * �����������ѯ
 * 
 * @since 6.0
 * @version 2011-2-28 ����10:33:28
 * @author ô��
 */
public class QueryAuditFlowAction extends PFApproveStatusInfoAction {

  /** Version */
  private static final long serialVersionUID = -4490586267001887381L;

  // private AbstractAppModel model;

  /**
   * ���췽��
   */
  public QueryAuditFlowAction() {
    super();
    this.initializeAction();
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.SCM_VIEWAPPROVEFLOW);
  }
}
