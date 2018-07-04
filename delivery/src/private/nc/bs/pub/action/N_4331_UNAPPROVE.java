package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m4331.action.maintain.DeliveryUnApproveAction;
import nc.vo.pub.compiler.PfParameterVO;

public class N_4331_UNAPPROVE extends AbstractCompiler2 {
  public N_4331_UNAPPROVE() {
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

    DeliveryUnApproveAction action = new DeliveryUnApproveAction();
    return action.unapprove(this);
  }

}
