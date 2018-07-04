package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m38.action.ApprovePreOrderAction;
import nc.vo.pub.compiler.PfParameterVO;

public class N_38_APPROVE extends AbstractCompiler2 {

  /**
   * N_38_APPROVE ������ע�⡣
   */
  public N_38_APPROVE() {
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

    ApprovePreOrderAction action = new ApprovePreOrderAction();
    return action.approve(this);
  }
}
