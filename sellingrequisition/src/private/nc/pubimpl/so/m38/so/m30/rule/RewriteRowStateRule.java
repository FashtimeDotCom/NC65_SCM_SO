package nc.pubimpl.so.m38.so.m30.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m38.state.PreOrderStateMachine;
import nc.bs.so.m38.state.row.RowCloseState;
import nc.bs.so.m38.state.row.RowOpenState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m38.entity.PreOrderViewVO;

/**
 * @description
 * ���۶�����дԤ����ִ�к������(after):
 * <p>��д�����д�/�ر�
 * @scene
 * ���۶�����дԤ����ִ�к������
 * @param
 * ��
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-04-08 ����10:37:16
 */
public class RewriteRowStateRule implements IRule<PreOrderViewVO> {

  @Override
  public void process(PreOrderViewVO[] vos) {
    RowOpenState openState = new RowOpenState();
    RowCloseState closeState = new RowCloseState();

    List<PreOrderViewVO> closeList = new ArrayList<PreOrderViewVO>();
    List<PreOrderViewVO> openList = new ArrayList<PreOrderViewVO>();
    for (PreOrderViewVO vo : vos) {
      if (openState.isRowOpen(vo)) {
        openList.add(vo);
      }
      if (closeState.isRowClose(vo)) {
        closeList.add(vo);
      }
    }
    this.setState(openList, openState);
    this.setState(closeList, closeState);
  }

  private void setState(List<PreOrderViewVO> list, IState<PreOrderViewVO> state) {
    int size = list.size();
    if (size <= 0) {
      return;
    }
    PreOrderViewVO[] views = new PreOrderViewVO[size];
    views = list.toArray(views);
    PreOrderStateMachine bo = new PreOrderStateMachine();
    bo.setState(state, views);
  }
}
