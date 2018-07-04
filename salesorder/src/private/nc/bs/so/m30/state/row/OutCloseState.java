package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPBeforeRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m30.rule.reserve.ReserveCloseRule;
import nc.bs.so.m30.rule.rewrite.m35.Rewrite35WhenOutClose;
import nc.bs.so.m30.rule.rewrite.m4331.Rewrite4331WhenOutCloseRule;
import nc.bs.so.m30.rule.rewrite.me.RewriteME35WhenOutClose;
import nc.bs.so.m30.rule.rewrite.opc.RewriteOPCWhenOutClose;
import nc.bs.so.m30.rule.rewrite.price.RewriteProPirceWhenOutClose;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalEndPara;
import nc.vo.bd.material.MaterialVO;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.balend.enumeration.BalEndTrigger;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.SOSysInitGroupQuery;

/**
 * ���۶�������ر�
 * 
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class OutCloseState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  /**
   * ���۶�������رչ�����
   */
  public OutCloseState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBOUTENDFLAG, UFBoolean.TRUE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitOutClose(vo);
  }

  /**
   * �Ƿ���Գ���ر�
   * 
   * @param vo
   * @param materrialmaps
   * @return boolean
   */
  public boolean isOutClose(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isOutClose(vo, materrialmaps);
  }

  
  /**
   * �Ƿ���Գ���ر�4�޶�
   * 
   * @param vo
   * @return boolean
   */
  public boolean isOutColseForRevise(SaleOrderViewVO vo,
      SaleOrderViewVO originVo, Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isOutCloseForRevise(vo, originVo,
        materrialmaps);
  }
  
  /**
   * �Ƿ���Գ���ر�4;��
   * 
   * @param vo
   * @param materrialmaps
   * @return boolean
   */
  public boolean isOutCloseFor4453(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isOutCloseFor4453(vo, materrialmaps);
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
    list.add(new RowCloseState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.OutCloseState);
    this.addRule(processer);

    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0150")/*@res "����ر�ǰִ��ҵ�����"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0151")/*@res "�޸ı���״̬Ϊ����ر�"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0152")/*@res "����رպ�ִ��ҵ�����"*/);

    /**
     * �������رգ�������ʷ����ԭ��û�и���״̬������ԽǨ��ʽ���б�д
     * ��ȷ��д��Ӧ����next()�����к�ArSettleCloseState��isAutoTransitable����д�߼�
     * ��״̬����֧������ģʽ.....
     */
    this.processOrderSquareClose(views);

  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {

    // �������õ���ǰ
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M30EngrossAction.M30OutClose));

    // --------------------------------------

    // �������õ��ú�
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M30EngrossAction.M30OutClose));

    // ����رպ������η�����(Ҫ�󷢻���������ʱ����������,���������۶������ÿ������߼�֮��)
    processer.addAfterRule(new Rewrite4331WhenOutCloseRule());
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ���������ǰ
      processer.addBeforeRule(new SaleOrderViewATPBeforeRule());
      // �����������
      processer.addAfterRule(new SaleOrderViewATPAfterRule());
      // Ԥ������
      processer.addAfterRule(new ReserveCloseRule());
    }

    // ������ֹ�ϵ
    if (SOSysInitGroupQuery.isMeEnabled()) {
      processer.addBeforeRule(new RewriteME35WhenOutClose());
    }
    else {
      processer.addAfterRule(new Rewrite35WhenOutClose());
    }
    if(SysInitGroupQuery.isOPCEnabled()){
      // ��д�������۳���ر�״̬
      processer.addAfterRule(new RewriteOPCWhenOutClose());
    }
    // ��д�����۸��
    if (SysInitGroupQuery.isPRICEEnabled()) {
      processer.addAfterRule(new RewriteProPirceWhenOutClose());
    }
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
    BalEndTrigger trigger = BalEndTrigger.OUT_CLOSE;
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
