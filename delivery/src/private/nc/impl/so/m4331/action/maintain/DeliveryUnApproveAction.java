package nc.impl.so.m4331.action.maintain;

import nc.bs.pub.action.N_4331_UNAPPROVE;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPAfterRule;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPBeforeRule;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m4331.action.maintain.rule.unapprove.CheckEnableUnApproveRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class DeliveryUnApproveAction {

  public DeliveryVO[] unapprove(N_4331_UNAPPROVE script) {
    DeliveryVO[] retvos = null;
    try {
      Object[] inCurObjects = script.getPfParameterVO().m_preValueVos;
      DeliveryVO[] inCurVOs = new DeliveryVO[inCurObjects.length];
      int length = inCurObjects.length;
      for (int i = 0; i < length; i++) {
        inCurVOs[i] = (DeliveryVO) inCurObjects[i];
      }

      DeliveryHVO vo = inCurVOs[0].getParentVO();
      AroundProcesser<DeliveryVO> processer =
          new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.UnApproveAction);
      this.addBeforRule(processer, vo.getFstatusflag());
      addAfterATPRule(processer, vo.getFstatusflag());
      TimeLog.logStart();
      processer.before(inCurVOs);
      TimeLog.info("����������ǰִ��ҵ�����"); /*-=notranslate=-*/
      TimeLog.logStart();
      script.procUnApproveFlow(script.getPfParameterVO());
      TimeLog.info("�����������������˴�����������޸�"); /*-=notranslate=-*/
      TimeLog.logStart();
      TimeLog.info("������������ִ��ҵ�����"); /*-=notranslate=-*/
      TimeLog.logStart();
      retvos = this.queryNewVO(inCurVOs);

      processer.after(retvos);
      TimeLog.info("��֯����ֵ,����������VO"); /*-=notranslate=-*/
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return retvos;
  }

  /**
   * ������������������������
   */
  private void addBeforRule(AroundProcesser<DeliveryVO> processer,
      Integer statusflag) {
    IRule<DeliveryVO> rule = new CheckEnableUnApproveRule();
    processer.addBeforeRule(rule);
    // ������ͨ�����ÿ�����
    if (BillStatus.NOPASS.getIntValue() == statusflag.intValue()) {
      // ������
      rule = new DeliveryVOATPBeforeRule();
      processer.addBeforeRule(rule);
    }
  }

  private void addAfterATPRule(AroundProcesser<DeliveryVO> processer,
      Integer statusflag) {
    if (BillStatus.NOPASS.getIntValue() == statusflag.intValue()) {
      IRule<DeliveryVO> rule = null;
      rule = new DeliveryVOATPAfterRule();
      processer.addAfterRule(rule);
    }
  }

  private DeliveryVO[] queryNewVO(DeliveryVO[] bills) {
    String[] ids = new String[bills.length];
    int length = bills.length;
    for (int i = 0; i < length; i++) {
      ids[i] = bills[i].getPrimaryKey();
    }
    BillQuery<DeliveryVO> query = new BillQuery<DeliveryVO>(DeliveryVO.class);
    return query.query(ids);
  }
}
