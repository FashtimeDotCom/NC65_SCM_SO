package nc.bs.so.m38.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m38.plugin.StatePlugInPoint;
import nc.bs.so.m38.state.BillStateUtil;
import nc.bs.so.m38.state.StateCalculateUtil;
import nc.bs.so.m38.state.bill.BillOpenState;
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
 * Ԥ�����д�״̬
 * 
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-04-08 ����09:27:07
 */
public class RowOpenState extends AbstractRowState<PreOrderViewVO> implements
    ITransitionState<PreOrderViewVO, PreOrderVO> {

  private StateCalculateUtil stateCalculateUtil;

  public RowOpenState() {
    super(PreOrderBVO.class, PreOrderBVO.BLINECLOSE, UFBoolean.FALSE);
  }

  /**
   * Ԥ�����д򿪺���������
   */
  @Override
  public IState<PreOrderVO> getTransitTargetState() {
    return new BillOpenState();
  }

  @Override
  public boolean isAutoTransitable(PreOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isAutoTransitRowOpen(vo);
  }

  @Override
  public boolean isPrevStateValid(PreOrderViewVO vo) {
    // ֻ��������״̬�Լ�֮�������״̬�ſ�������״̬�Ĳ���
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  /**
   * �Ƿ�����д�
   */
  public boolean isRowOpen(PreOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isRowOpen(vo);
  }

  @Override
  public List<IState<PreOrderViewVO>> next() {
    List<IState<PreOrderViewVO>> list = new ArrayList<IState<PreOrderViewVO>>();
    // TODO ����Ӻ�����Ҫ�����״̬
    return list;
  }

  @Override
  public void setState(PreOrderViewVO[] views) {
    AroundProcesser<PreOrderViewVO> processer =
        new AroundProcesser<PreOrderViewVO>(StatePlugInPoint.RowOpenState);

    TimeLog.logStart();
    PreOrderViewVO[] vos = processer.before(views);
    TimeLog.info("�д�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("�޸ı���״̬Ϊ�д�"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);

    TimeLog.info("�д򿪺�ִ��ҵ�����"); /*-=notranslate=-*/
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
