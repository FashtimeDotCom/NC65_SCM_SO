package nc.bs.so.m38.state.bill;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m38.plugin.StatePlugInPoint;
import nc.bs.so.m38.state.BillStateUtil;
import nc.bs.so.m38.state.StateCalculateUtil;
import nc.bs.so.m38.state.row.RowCloseState;
import nc.impl.pubapp.bill.state.AbstractBillState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * Ԥ���������ر�״̬
 * 
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-04-08 ����09:27:07
 */
public class BillCloseState extends AbstractBillState<PreOrderVO> implements
    ITransitionState<PreOrderVO, PreOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public BillCloseState() {
    super(PreOrderHVO.FSTATUSFLAG, BillStatus.CLOSED.value());
  }

  /**
   * Ԥ���������رպ����йر�
   */
  @Override
  public IState<PreOrderViewVO> getTransitTargetState() {
    return new RowCloseState();
  }

  @Override
  public boolean isAutoTransitable(PreOrderVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitBillClose(vo);
  }

  @Override
  public boolean isPrevStateValid(PreOrderVO vo) {
    // ֻ��������״̬֮�������״̬�ſ���
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  @Override
  public List<IState<PreOrderVO>> next() {
    List<IState<PreOrderVO>> list = new ArrayList<IState<PreOrderVO>>();
    return list;
  }

  @Override
  public void setState(PreOrderVO[] bills) {
    AroundProcesser<PreOrderVO> processer =
        new AroundProcesser<PreOrderVO>(StatePlugInPoint.BillCloseState);

    TimeLog.logStart();
    PreOrderVO[] vos = processer.before(bills);
    TimeLog.info("�����ر�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("�����ر�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("�����رպ�ִ��ҵ�����"); /*-=notranslate=-*/
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }
}
