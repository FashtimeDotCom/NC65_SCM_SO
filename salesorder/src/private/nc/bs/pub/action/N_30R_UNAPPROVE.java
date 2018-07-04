package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m30.action.main.UnApproveSaleOrderReviseAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 *  ���۶����޶�����
 *
 * @since 6.36
 * @version 2014-12-26 ����2:49:00
 * @author wangshu6
 */
public class N_30R_UNAPPROVE extends AbstractCompiler2 {
  /**
   * N_5X_UNAPPROVE ������ע�⡣
   */
  public N_30R_UNAPPROVE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;

      SaleOrderVO[] inCurVOs = this.getVos();
      UnApproveSaleOrderReviseAction action = new UnApproveSaleOrderReviseAction();
      return action.unApprove(inCurVOs, this);
    }
    catch (Exception ex) {

      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderVO[] getVos() {
    return (SaleOrderVO[]) super.getVos();
  }
}
