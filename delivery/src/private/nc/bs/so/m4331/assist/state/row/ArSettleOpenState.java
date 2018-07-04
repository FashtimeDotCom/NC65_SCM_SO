package nc.bs.so.m4331.assist.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m4331.plugin.StatePlugInPoint;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * ��������������
 * 
 * @since 6.3
 * @version 2013-1-8 ����08:51:20
 * @author yaogj
 */
public class ArSettleOpenState extends AbstractRowState<DeliveryViewVO> {

  /**
   * ��������������
   */
  public ArSettleOpenState() {
    super(DeliveryBVO.class, DeliveryBVO.BBARSETTLEFLAG, UFBoolean.FALSE);
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

    return isAuto;
  }

  @Override
  public boolean isPrevStateValid(DeliveryViewVO vo) {
    return true;
  }

  @Override
  public List<IState<DeliveryViewVO>> next() {
    List<IState<DeliveryViewVO>> list = new ArrayList<IState<DeliveryViewVO>>();
    return list;
  }

  @Override
  public void setState(DeliveryViewVO[] views) {
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(StatePlugInPoint.ArSettleOpenState);
    this.addRule(processer);
    TimeLog.logStart();
    DeliveryViewVO[] vos = processer.before(views);
    TimeLog.info("�д�ǰִ��ҵ�����"); /*-=notranslate=-*/
    TimeLog.logStart();
    super.setState(views);
    TimeLog.info("�޸ı���״̬Ϊ�д�"); /*-=notranslate=-*/
    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("�д򿪺�ִ��ҵ�����"); /*-=notranslate=-*/
  }

  private void addRule(AroundProcesser<DeliveryViewVO> processer) {

    // ��ǰ����ռ��
    IRule<DeliveryViewVO> rule =
        new RenovateARByBidsBeginRule(M4331EngrossAction.M4331RowOpen);
    processer.addBeforeRule(rule);
    // �򿪺�����ռ��
    rule = new RenovateARByBidsEndRule(M4331EngrossAction.M4331RowOpen);
    processer.addAfterRule(rule);

  }
}
