package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalOpenPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.balend.enumeration.BalOpenTrigger;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class InvoiceOpenState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public InvoiceOpenState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBINVOICENDFLAG, UFBoolean.FALSE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitInvoiceOpen(vo);
  }

  /**
   * �Ƿ���Կ�Ʊ��
   * 
   * @param vo
   * @return boolean
   */
  public boolean isInvoiceOpen(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isInvoiceOpen(vo);
  }

  
  /**
   * �޶��Ƿ���Կ�Ʊ��
   * 
   * @param vo
   * @return boolean
   */
  public boolean isReviseInvoiceOpen(SaleOrderViewVO vo,SaleOrderViewVO originView) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isReviseInvoiceOpen(vo,originView);
  }

  
  /**
   * �Ƿ���Կ�Ʊ��4;��
   * 
   * @param vo
   * @return boolean
   */
  public boolean isInvoiceOpenFor4453(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isInvoiceOpenFor4453(vo);
  }

  /**
   * ����Գ��Ƿ���Կ�Ʊ��
   * 
   * @param vo
   * @return boolean
   */
  public boolean isInvoiceOpenForOutRush(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isInvoiceOpenForOutRush(vo);
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

  @Override
  public List<IState<SaleOrderViewVO>> next() {
    List<IState<SaleOrderViewVO>> list =
        new ArrayList<IState<SaleOrderViewVO>>();
    list.add(new RowOpenState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.InvoiceOpenState);
    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0147")/*@res "��Ʊ��ǰִ��ҵ�����"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0148")/*@res "�޸ı���״̬Ϊ��Ʊ��"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0149")/*@res "��Ʊ�򿪺�ִ��ҵ�����"*/);

    /**
     * �������򿪣�������ʷ����ԭ��û�и���״̬������ԽǨ��ʽ���б�д
     * ��ȷ��д��Ӧ����next()�����к�ArSettleOpenState��isAutoTransitable����д�߼���
     */
    this.processOrderSquareOpen(views);

  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

  /**
   * ��������
   * 
   * @param views
   */
  private void processOrderSquareOpen(SaleOrderViewVO[] views) {
    // ���۶�������id
    int len = views.length;
    String[] orderbids = new String[len];
    for (int i = 0; i < len; i++) {
      orderbids[i] = views[i].getBody().getCsaleorderbid();
    }
    BalOpenTrigger trigger = BalOpenTrigger.VOICE_OPEN;
    BalOpenPara para = new BalOpenPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalOpen(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    /*** 2012-04-28 ��ӱ� �۹� ֻ���ֹ�������Ҫˢ��ǰ̨TS�����Ǩ�Ƶ������ȡ���� ***/
    // // ����ر�ˢ��ts������رձ�־
    // new SaleOrderVOUtil().refreshViewForSettleClose(views);
  }

}
