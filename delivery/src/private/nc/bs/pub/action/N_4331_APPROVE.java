package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m4331.action.maintain.DeliveryApproveAction;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.credit.accountcheck.IIgnoreAccountCheck;
import nc.pubitf.credit.creditcheck.IAuditFlowFuncFlag;
import nc.pubitf.credit.creditcheck.IIgnoreCreditCheck;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;

public class N_4331_APPROVE extends AbstractCompiler2 {

  /**
   * N_32_APPROVE ������ע�⡣
   */
  public N_4331_APPROVE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  \n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) {
    super.m_tmpVo = vo;
    Object rets = null;
    try {
      if (SysInitGroupQuery.isCREDITEnabled()) {
        IAuditFlowFuncFlag service =
            NCLocator.getInstance().lookup(IAuditFlowFuncFlag.class);
        service.setAuditFlowFuncFlag();
        DeliveryApproveAction action = new DeliveryApproveAction();
        rets = action.approve(this);
        service.removeAuditFlowFuncFlag();
      }
      else {
        DeliveryApproveAction action = new DeliveryApproveAction();
        rets = action.approve(this);
      }

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return rets;

  }
}
