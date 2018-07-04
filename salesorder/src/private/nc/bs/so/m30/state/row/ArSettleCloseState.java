package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.bs.so.m30.state.rule.ARCloseProcessRule;

import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * �������ر�
 * 
 * @since 6.1
 * @version 2013-05-28 14:18:32
 * @author yixl
 */
public class ArSettleCloseState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  /**
   * �������رչ�����
   */
  public ArSettleCloseState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBARSETTLEFLAG, UFBoolean.TRUE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitArSettleClose(vo);
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
    list.add(new RowCloseState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            StatePlugInPoint.ArSettleCloseState);
    this.addRule(processer);
    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info("�������ر�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("�޸ı���״̬Ϊ�������ر�"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("�������رպ�ִ��ҵ�����"); /*-=notranslate=-*/
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {

    if (SysInitGroupQuery.isCREDITEnabled()) {
      // �������õ���ǰ
      processer.addBeforeRule(new RenovateARByBidsBeginRule(
          M30EngrossAction.M30SettleClose));
    }

    // --------------------------------------
    if (SysInitGroupQuery.isCREDITEnabled()) {
      // �������õ��ú�
      processer.addAfterRule(new RenovateARByBidsEndRule(
          M30EngrossAction.M30SettleClose));
    }

    // Ӧ�ս���رմ���س�Ӧ��
    processer.addAfterRule(new ARCloseProcessRule());
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
