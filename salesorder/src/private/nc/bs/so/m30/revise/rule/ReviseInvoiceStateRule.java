package nc.bs.so.m30.revise.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.row.InvoiceCloseState;
import nc.bs.so.m30.state.row.InvoiceOpenState;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 *              ���۶����޶�ִ�к������
 * @scene
 *        <p>
 *        ��д����Ʊ״̬��/�ر�
 * @param ��
 * 
 * @since 6.0
 * @version 2010-01-28 ����13:49:07
 * @author ��־ΰ
 */
public class ReviseInvoiceStateRule implements ICompareRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
    // ת��view
    BillToViewConvertor<SaleOrderVO, SaleOrderViewVO> convertor =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = convertor.convert(vos);
    SaleOrderViewVO[] originViews = convertor.convert(originVOs);
    Map<String, SaleOrderViewVO> originViewMap =
        new HashMap<String, SaleOrderViewVO>();
    for (SaleOrderViewVO originView : originViews) {
      originViewMap.put(originView.getBody().getCsaleorderbid(), originView);
    }
    InvoiceOpenState openState = new InvoiceOpenState();
    InvoiceCloseState closeState = new InvoiceCloseState();
    List<SaleOrderViewVO> closeList = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> openList = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO vo : views) {
      SaleOrderViewVO originView =
          originViewMap.get(vo.getBody().getCsaleorderbid());

      if (openState.isReviseInvoiceOpen(vo,originView) ) {
        openList.add(vo);
      }
      else if (closeState.isReviseInvoiceClose(vo,originView) ) {
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
