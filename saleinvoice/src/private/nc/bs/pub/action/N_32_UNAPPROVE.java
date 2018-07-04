package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m32.action.UnApproveSaleInvoiceAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ��Ʊ�������
 * 
 * @since 6.0
 * @version 2011-10-28 ����09:19:05
 * @author ô��
 */
public class N_32_UNAPPROVE extends AbstractCompiler2 {

  /**
   * N_32_UNAPPROVE ������ע�⡣
   */
  public N_32_UNAPPROVE() {
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
    try {
      UnApproveSaleInvoiceAction action = new UnApproveSaleInvoiceAction();
      return action.unapprove(this);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
