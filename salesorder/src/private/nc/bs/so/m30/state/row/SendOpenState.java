package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPBeforeRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m30.rule.rewrite.price.RewriteProPirceWhenSendOpen;
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
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ���۶���������
 * 
 * @author ��־ΰ
 * @time 2010-01-28 ����13:49:07
 */
public class SendOpenState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public SendOpenState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBSENDENDFLAG, UFBoolean.FALSE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isAutoTransitSendOpen(vo);
  }

  /**
   * �������Ƿ������ֹ������򿪵�����
   * 
   * @param view
   * @return
   */
  public boolean isManualSendOpen(SaleOrderViewVO view) {
    return this.getStateCalculateUtil().isManualSendOpen(view);
  }

  @Override
  public boolean isPrevStateValid(SaleOrderViewVO view) {
    // ֻ������̬������̬�������ر�̬���Դ�
    Integer status = view.getHead().getFstatusflag();
    boolean flag =
        BillStatus.AUDIT.equalsValue(status)
            || BillStatus.FREE.equalsValue(status)
            || BillStatus.AUDITING.equalsValue(status)
            || BillStatus.CLOSED.equalsValue(status);
    return flag;
  }

  /**
   * �Ƿ���Է�����
   * 
   * @param vo
   * @return boolean
   */
  public boolean isSendOpen(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isSendOpen(vo, materrialmaps);
  }

  /**
   * ;��ʱ�Ƿ���Դ�
   * 
   * @param vo
   * @return boolean
   */
  public boolean isSendOpenFor4453(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isSendOpenFor4453(vo, materrialmaps);
  }

  /**
   * �޶�ʱ�Ƿ���Դ�
   * 
   * @param vo
   * @return boolean
   */
  public boolean isSendOpenForRevise(SaleOrderViewVO vo,
      SaleOrderViewVO originVo, Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isSendOpenForRevise(vo, originVo,
        materrialmaps);
  }

  @Override
  public List<IState<SaleOrderViewVO>> next() {
    List<IState<SaleOrderViewVO>> list =
        new ArrayList<IState<SaleOrderViewVO>>();
    list.add(new OutOpenState());
    list.add(new InvoiceOpenState());
    list.add(new RowOpenState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.SendOpenState);
    this.addRule(processer);

    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0159")/*@res "������ǰִ��ҵ�����"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0160")/*@res "�޸ı���״̬Ϊ������"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0161")/*@res "�����򿪺�ִ��ҵ�����"*/);
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {
    if (SysInitGroupQuery.isCREDITEnabled()) {
      // �������õ���ǰ
      processer.addBeforeRule(new RenovateARByBidsBeginRule(
          M30EngrossAction.M30SendOpen));
    }
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ���������ǰ
      processer.addBeforeRule(new SaleOrderViewATPBeforeRule());

      // �����������
      processer.addAfterRule(new SaleOrderViewATPAfterRule());
    }

    // --------------------------------------
    if (SysInitGroupQuery.isCREDITEnabled()) {
      // �������õ��ú�
      processer.addAfterRule(new RenovateARByBidsEndRule(
          M30EngrossAction.M30SendOpen));
    }
    
    // ��д�����۸������
    if (SysInitGroupQuery.isPRICEEnabled()) {
      processer.addBeforeRule(new RewriteProPirceWhenSendOpen());
    }

  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
