package nc.bs.so.m4331.assist.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m4331.assist.state.BillStateUtil;
import nc.bs.so.m4331.assist.state.StateCalculateUtil;
import nc.bs.so.m4331.assist.state.bill.BillOpenState;
import nc.bs.so.m4331.assist.state.row.rule.DeliveryViewATPAfterRule;
import nc.bs.so.m4331.assist.state.row.rule.DeliveryViewATPBeforeRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m4331.plugin.StatePlugInPoint;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * �������д�״̬
 *
 * @author ף����
 * @since 6.0
 * @time 2010-04-08 ����09:27:07
 */
public class RowOpenState implements
    ITransitionState<DeliveryViewVO, DeliveryVO> {

  private Class<? extends ISuperVO> clazz;

  private String stateKey;

  private Object stateValue;

  public RowOpenState() {
    this.initRowOpenState();
  }

  /**
   * �������д򿪺���������
   */
  @Override
  public IState<DeliveryVO> getTransitTargetState() {
    return new BillOpenState();
  }

  @Override
  public boolean isAutoTransitable(DeliveryViewVO vo) {
    boolean isAuto = true;
    if (this.isThisState(vo)) {
      isAuto = false;
    }
    if (!this.isPrevStateValid(vo)) {
      isAuto = false;
    }
    else {
      StateCalculateUtil util = new StateCalculateUtil();
      isAuto = util.isAutoRowsOpenByBillState(vo);
    }
    return isAuto;
  }

  @Override
  public boolean isPrevStateValid(DeliveryViewVO vo) {
    // ֻ��������״̬�Լ�֮�������״̬�ſ�������״̬�Ĳ���
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  @Override
  public boolean isThisState(DeliveryViewVO vo) {
    Object value2 = vo.getAttributeValue(this.stateKey);
    if (value2 == null) {
      return false;
    }
    return this.stateValue.equals(value2);
  }

  @Override
  public List<IState<DeliveryViewVO>> next() {
    List<IState<DeliveryViewVO>> list = new ArrayList<IState<DeliveryViewVO>>();
    return list;
  }

  @Override
  public void setState(DeliveryViewVO[] views) {
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(StatePlugInPoint.RowOpenState);
    this.addRule(processer);
    TimeLog.logStart();
    DeliveryViewVO[] vos = processer.before(views);
    TimeLog.info("�д�ǰִ��ҵ�����"); /*-=notranslate=-*/
    TimeLog.logStart();
    this.changeState(views);
    TimeLog.info("�޸ı���״̬Ϊ�д�"); /*-=notranslate=-*/
    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("�д򿪺�ִ��ҵ�����"); /*-=notranslate=-*/
  }

  private void addRule(AroundProcesser<DeliveryViewVO> processer) {
    // ���������ǰ����
    processer.addBeforeRule(new DeliveryViewATPBeforeRule());
    // ��ǰ����ռ��
    IRule<DeliveryViewVO> rule =
        new RenovateARByBidsBeginRule(M4331EngrossAction.M4331RowOpen);
    processer.addBeforeRule(rule);
    // �򿪺�����ռ��
    rule = new RenovateARByBidsEndRule(M4331EngrossAction.M4331RowOpen);
    processer.addAfterRule(rule);
    // ��������������
    processer.addAfterRule(new DeliveryViewATPAfterRule());
  }

  private void changeState(DeliveryViewVO[] views) {
    for (DeliveryViewVO view : views) {
      if (!this.isPrevStateValid(view)) {
        String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0034")/*@res "��ǰ����������״̬�������õ�ǰ״̬"*/;
        ExceptionUtils.wrappBusinessException(message);
      }
      view.setAttributeValue(this.stateKey, this.stateValue);
    }
    String[] names = new String[] {
      this.stateKey
    };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, this.clazz, names);
  }

  private void initRowOpenState() {
    this.clazz = DeliveryBVO.class;
    this.stateKey = DeliveryBVO.BOUTENDFLAG;
    this.stateValue = UFBoolean.FALSE;
  }
}