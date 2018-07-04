package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.bs.so.m30.state.bill.BillCloseState;
import nc.bs.so.m30.state.rule.RowStateReWriteZ3Rule;

import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * ���۶����йر�
 * 
 * @author ��־ΰ
 * @time 2010-01-28 ����13:49:07
 */
public class RowCloseState extends AbstractRowState<SaleOrderViewVO> implements
    ITransitionState<SaleOrderViewVO, SaleOrderVO> {

  private StateCalculateUtil stateCalculateUtil;

  /**
   * ���۶����йرչ�����
   */
  public RowCloseState() {
    super(SaleOrderBVO.class, SaleOrderBVO.FROWSTATUS, BillStatus.CLOSED
        .value());
  }

  @Override
  public IState<SaleOrderVO> getTransitTargetState() {
    return new BillCloseState();
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitRowClose(vo);
  }

  @Override
  public boolean isPrevStateValid(SaleOrderViewVO vo) {
    // ֻ��������״̬�Լ�֮�������״̬�ſ�������״̬�Ĳ���
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  @Override
  public List<IState<SaleOrderViewVO>> next() {
    List<IState<SaleOrderViewVO>> list =
        new ArrayList<IState<SaleOrderViewVO>>();
    list.add(new SendCloseState());
    list.add(new OutCloseState());
    list.add(new InvoiceCloseState());
    list.add(new ArSettleCloseState());
    list.add(new CostSettleCloseState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.RowCloseState);
    this.addRule(processer);
    TimeLog.logStart();

    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info("�йر�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("�޸ı���״̬Ϊ�йر�"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("�йرպ�ִ��ҵ�����"); /*-=notranslate=-*/
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {
    // �����رջ�д��ͬ
    processer.addAfterRule(new RowStateReWriteZ3Rule(true));
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }
}
