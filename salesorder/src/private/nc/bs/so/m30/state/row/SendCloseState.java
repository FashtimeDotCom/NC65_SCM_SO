package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPBeforeRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m30.rule.rewrite.price.RewriteProPirceWhenSendClose;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.bd.material.MaterialVO;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ���۶��������ر�
 * 
 * @author ��־ΰ
 * @time 2010-01-28 ����13:49:07
 */
public class SendCloseState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public SendCloseState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBSENDENDFLAG, UFBoolean.TRUE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitSendClose(vo);
  }

  @Override
  public boolean isPrevStateValid(SaleOrderViewVO vo) {
    // ֻ��������״̬�Լ�֮�������״̬�ſ�������״̬�Ĳ���
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  /**
   * �޶�ʱ�Ƿ���Թر�
   * 
   * @param vo
   * @return boolean
   */
  public boolean isSendColseForRevise(SaleOrderViewVO vo,
      SaleOrderViewVO originVo, Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isSendColseForRevise(vo, originVo,
        materrialmaps);
  }
  
  /**
   * �Ƿ�����йر�
   * 
   * @param vo
   * @return boolean
   */
  public boolean isSendClose(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isSendClose(vo, materrialmaps);
  }

  /**
   * ;�𲻻ᵼ�·����ر�
   * 
   * @param vo
   * @return
   */
  public boolean isSendCloseFor4453(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return false;
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
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.SendCloseState);
    this.addRule(processer);

    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0156")/*@res "�����ر�ǰִ��ҵ�����"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0157")/*@res "�޸ı���״̬Ϊ�����ر�"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0158")/*@res "�����رպ�ִ��ҵ�����"*/);
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {
    // �������õ���ǰ
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M30EngrossAction.M30SendClose));
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ���������ǰ
      processer.addBeforeRule(new SaleOrderViewATPBeforeRule());
      // �����������
      processer.addAfterRule(new SaleOrderViewATPAfterRule());
    }

    // --------------------------------------

    // �������õ���ǰ
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M30EngrossAction.M30SendClose));
    
    // ��д�����۸��
    if (SysInitGroupQuery.isPRICEEnabled()) {
      processer.addAfterRule(new RewriteProPirceWhenSendClose());
    }

  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
