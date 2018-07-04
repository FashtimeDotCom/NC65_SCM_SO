package nc.bs.so.m38.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m38.plugin.StatePlugInPoint;
import nc.bs.so.m38.state.BillStateUtil;
import nc.bs.so.m38.state.StateCalculateUtil;
import nc.bs.so.m38.state.bill.BillCloseState;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;

/**
 * Ԥ�����йر�״̬
 * 
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-04-08 ����09:27:07
 */
public class RowCloseState extends AbstractRowState<PreOrderViewVO> implements
    ITransitionState<PreOrderViewVO, PreOrderVO> {

  private StateCalculateUtil stateCalculateUtil;

  public RowCloseState() {
    super(PreOrderBVO.class, PreOrderBVO.BLINECLOSE, UFBoolean.TRUE);
  }

  /**
   * Ԥ�����йرպ��������ر�
   */
  @Override
  public IState<PreOrderVO> getTransitTargetState() {
    return new BillCloseState();
  }

  /**
   * ִ��next/TransitTargetStateʱ�ж�
   * 
   * @param vo
   * @return boolean
   */
  @Override
  public boolean isAutoTransitable(PreOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isAutoTransitRowClose(vo);
  }

  @Override
  public boolean isPrevStateValid(PreOrderViewVO vo) {
    // ֻ��������״̬�Լ�֮�������״̬�ſ�������״̬�Ĳ���
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  /**
   * �Ƿ�����йر�
   * 
   * @param vo
   * @return boolean
   */
  public boolean isRowClose(PreOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isRowClose(vo);
  }

  @Override
  public List<IState<PreOrderViewVO>> next() {
    List<IState<PreOrderViewVO>> list = new ArrayList<IState<PreOrderViewVO>>();
    return list;
  }

  @Override
  public void setState(PreOrderViewVO[] views) {
    AroundProcesser<PreOrderViewVO> processer =
        new AroundProcesser<PreOrderViewVO>(StatePlugInPoint.RowCloseState);

    TimeLog.logStart();
    PreOrderViewVO[] vos = processer.before(views);
    TimeLog.info("�йر�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("�޸ı���״̬Ϊ�йر�"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("�йرպ�ִ��ҵ�����"); /*-=notranslate=-*/
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
