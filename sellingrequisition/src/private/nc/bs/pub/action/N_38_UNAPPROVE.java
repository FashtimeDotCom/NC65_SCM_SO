package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m38.action.UnApprovePreOrderAction;
import nc.vo.pub.compiler.PfParameterVO;

public class N_38_UNAPPROVE extends AbstractCompiler2 {

  public N_38_UNAPPROVE() {
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

    UnApprovePreOrderAction action = new UnApprovePreOrderAction();
    return action.unApprove(this);
  }
}
