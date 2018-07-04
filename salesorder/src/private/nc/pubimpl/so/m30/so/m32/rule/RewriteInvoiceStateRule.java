package nc.pubimpl.so.m30.so.m32.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.row.InvoiceCloseState;
import nc.bs.so.m30.state.row.InvoiceOpenState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * ���۷�Ʊ��д���۶���ִ�к������(after):
 * @scene
 * <p>��д����Ʊ״̬��/�ر�
 * @param
 * ��
 *
 * @since 6.0
 * @version 2010-01-28 ����13:49:07
 * @author ��־ΰ
 */
public class RewriteInvoiceStateRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {
    InvoiceOpenState openState = new InvoiceOpenState();
    InvoiceCloseState closeState = new InvoiceCloseState();

    List<SaleOrderViewVO> closeList = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> openList = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO vo : vos) {
      if (openState.isInvoiceOpen(vo)) {
        openList.add(vo);
      }
      else if (closeState.isInvoiceClose(vo)) {
        closeList.add(vo);
      }
    }
    this.setState(openList, openState);
    this.setState(closeList, closeState);
  }

  private void setState(List<SaleOrderViewVO> list,
      IState<SaleOrderViewVO> state) {
    int size = list.size();
    if (size <= 0) {
      return;
    }
    SaleOrderViewVO[] views = new SaleOrderViewVO[size];
    views = list.toArray(views);
    SaleOrderStateMachine bo = new SaleOrderStateMachine();
    bo.setState(state, views);
  }

}
