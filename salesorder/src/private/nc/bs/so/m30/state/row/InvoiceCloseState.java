package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalEndPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.balend.enumeration.BalEndTrigger;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class InvoiceCloseState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public InvoiceCloseState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBINVOICENDFLAG, UFBoolean.TRUE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitInvoiceClose(vo);
  }

  /**
   * �Ƿ���Կ�Ʊ�ر�
   * 
   * @param vo
   * @return boolean
   */
  public boolean isInvoiceClose(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isInvoiceClose(vo);
  }
  
  
  /**
   * �޶��Ƿ���Կ�Ʊ�ر�
   * 
   * @param vo
   * @return boolean
   */
  public boolean isReviseInvoiceClose(SaleOrderViewVO vo, SaleOrderViewVO originView) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isReviseInvoiceClose(vo,originView);
  }

  /**
   * �Ƿ���Կ�Ʊ�ر�4;��
   * 
   * @param vo
   * @return boolean
   */
  public boolean isInvoiceCloseFor4453(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isInvoiceCloseFor4453(vo);
  }

  /**
   * ����Գ��Ƿ���Կ�Ʊ��
   * 
   * @param vo
   * @return boolean
   */
  public boolean isInvoiceCloseForOutRush(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isInvoiceCloseForOutRush(vo);
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
    list.add(new OutCloseState());
    list.add(new SendCloseState());
    list.add(new RowCloseState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.InvoiceCloseState);

    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0144")/*@res "��Ʊ�ر�ǰִ��ҵ�����"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0145")/*@res "�޸ı���״̬Ϊ��Ʊ�ر�"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0146")/*@res "��Ʊ�رպ�ִ��ҵ�����"*/);

    /**
     * �������رգ�������ʷ����ԭ��û�и���״̬������ԽǨ��ʽ���б�д
     * ��ȷ��д��Ӧ����next()�����к�ArSettleCloseState��isAutoTransitable����д�߼���
     */
    this.processOrderSquareClose(views);
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

  /**
   * �������ر�
   * 
   * @param views
   */
  private void processOrderSquareClose(SaleOrderViewVO[] views) {
    // ���۶�������id
    int len = views.length;
    String[] orderbids = new String[len];
    for (int i = 0; i < len; i++) {
      orderbids[i] = views[i].getBody().getCsaleorderbid();
    }
    BalEndTrigger trigger = BalEndTrigger.VOICE_CLOSE;
    BalEndPara para = new BalEndPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalEnd(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    /*** 2012-04-28 ��ӱ� �۹� ֻ���ֹ�������Ҫˢ��ǰ̨TS�����Ǩ�Ƶ������ȡ���� ***/
    // // ����ر�ˢ��ts������رձ�־
    // new SaleOrderVOUtil().refreshViewForSettleClose(views);
  }

}
