package nc.pubimpl.so.m30.so.m4331.rule;

import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.row.OutOpenState;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 
 * @description
 * ��������д���۶���
 * @scene
 * ��������д���۶���ִ�к�򿪳���ر�
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-12-13 ����02:41:06
 * @author ������
 */
public class RewriteOutCloseRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {
    OutOpenState state = new OutOpenState();

    // ֻ�г���ر�ΪY�ǲ��ܽ�����رմ�
    if (null != vos && vos.length > 0
        && vos[0].getBody().getBboutendflag().booleanValue()) {
      SaleOrderStateMachine bo = new SaleOrderStateMachine();
      bo.setState(state, vos);
    }
  }

}
