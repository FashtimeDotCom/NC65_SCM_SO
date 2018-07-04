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
 * �������ر�
 * 
 * @since 6.3
 * @version 2013-1-8 ����09:02:11
 * @author yaogj
 */
public class ArSettleCloseState extends AbstractRowState<DeliveryViewVO> {

  /**
   * �������ر�
   */
  public ArSettleCloseState() {
    super(DeliveryBVO.class, DeliveryBVO.BBARSETTLEFLAG, UFBoolean.TRUE);
  }

  @Override
  public boolean isAutoTransitable(DeliveryViewVO vo) {
    boolean isAuto = true;
    if (this.isThisState(vo)) {
      isAuto = false;
    }
    else if (!this.isPrevStateValid(vo)) {
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
        new AroundProcesser<DeliveryViewVO>(StatePlugInPoint.ArSettleCloseState);
    this.addRule(processer);
    TimeLog.logStart();
    DeliveryViewVO[] vos = processer.before(views);

    TimeLog.info("�йر�ǰִ��ҵ�����");/*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);

    TimeLog.info("�޸ı���״̬Ϊ�йر�");/*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("�йرպ�ִ��ҵ�����");/*-=notranslate=-*/
  }

  private void addRule(AroundProcesser<DeliveryViewVO> processer) {

    // �ر�ǰ����ռ��
    IRule<DeliveryViewVO> rule =
        new RenovateARByBidsBeginRule(M4331EngrossAction.M4331RowClose);
    processer.addBeforeRule(rule);
    // �رպ�����ռ��
    rule = new RenovateARByBidsEndRule(M4331EngrossAction.M4331RowClose);
    processer.addAfterRule(rule);

  }

}
