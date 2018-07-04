package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m32.action.ApproveSaleInvoiceAction;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.pubitf.credit.accountcheck.IIgnoreAccountCheck;
import nc.pubitf.credit.creditcheck.IIgnoreCreditCheck;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;

/**
 * ���۷�Ʊ����
 * 
 * @since 6.0
 * @version 2011-9-23 ����09:42:48
 * @author ô��
 */
public class N_32_APPROVE extends AbstractCompiler2 {

  /**
   * N_32_APPROVE ������ע�⡣
   */
  public N_32_APPROVE() {
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
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    super.m_tmpVo = vo;
    ApproveSaleInvoiceAction action = new ApproveSaleInvoiceAction();
    try {
      return action.approve(this);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
