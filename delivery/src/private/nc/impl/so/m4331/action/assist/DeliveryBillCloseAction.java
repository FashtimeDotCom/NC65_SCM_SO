package nc.impl.so.m4331.action.assist;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m4331.assist.state.DeliveryStateMachine;
import nc.bs.so.m4331.assist.state.bill.BillCloseState;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPAfterRule;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPBeforeRule;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m4331.action.assist.rule.BussiCheckRule;
import nc.impl.so.m4331.action.assist.rule.RenovateQualityStateRule;
import nc.impl.so.m4331.action.assist.rule.RewriteSrcRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOParameterVO;
import nc.vo.so.pub.enumeration.ActionType;
import nc.vo.so.pub.enumeration.FuncodeType;
import nc.vo.so.pub.util.BusinessLogUtil;

/**
 * �����������ر�
 * 
 * @since 6.0
 * @version 2011-2-28 ����01:43:49
 * @author ף����
 */
public class DeliveryBillCloseAction {
  public DeliveryVO[] closeBill(DeliveryVO[] bills) {
    try {
      TimeLog.logStart();

      AroundProcesser<DeliveryVO> atpprocesser =
          new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.BillCloseAction);
      this.addBeforeATPRule(atpprocesser);
      this.addAfterATPRule(atpprocesser);

      atpprocesser.before(bills);

      BillTransferTool<DeliveryVO> transferTool =
          new BillTransferTool<DeliveryVO>(bills);
      DeliveryVO[] newbills = transferTool.getClientFullInfoBill();
      TimeLog.info("��ȫǰ̨VO�����������ʱ���"); /*-=notranslate=-*/
      this.addRule(bills);
      BillCloseState state = new BillCloseState();
      DeliveryStateMachine bo = new DeliveryStateMachine();
      // Ӧ���з���ֵ��Ϊ״̬�ı���VO
      bo.setState(state, newbills);
      this.setBusiLog(newbills);

      atpprocesser.after(newbills);

      return transferTool.getBillForToClient(newbills);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return null;
  }

  public DeliveryVO[] closeBill(SOParameterVO paravo) {
    try {
      TimeLog.logStart();

      AroundProcesser<DeliveryVO> atpprocesser =
          new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.BillCloseActionByPara);
      this.addBeforeATPRule(atpprocesser);
      this.addAfterATPRule(atpprocesser);
      DeliveryVO[] bills = (DeliveryVO[]) paravo.getVos();
      atpprocesser.before(bills);

      BillTransferTool<DeliveryVO> transferTool =
          new BillTransferTool<DeliveryVO>(bills);
      DeliveryVO[] newbills = transferTool.getClientFullInfoBill();
      TimeLog.info("��ȫǰ̨VO�����������ʱ���"); /*-=notranslate=-*/
      BussiCheckRule busicheck = new BussiCheckRule();
      busicheck.setBusiCheckFlag(paravo.getBusinessCheckMap());
      this.addRule(bills);
      BillCloseState state = new BillCloseState();
      DeliveryStateMachine bo = new DeliveryStateMachine();
      // Ӧ���з���ֵ��Ϊ״̬�ı���VO
      bo.setState(state, newbills);
      this.setBusiLog(newbills);

      atpprocesser.after(newbills);

      return transferTool.getBillForToClient(newbills);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return null;
  }

  /**
   * ����������������ӿ���������
   */
  private void addAfterATPRule(AroundProcesser<DeliveryVO> processer) {
    IRule<DeliveryVO> rule = null;
    rule = new DeliveryVOATPAfterRule();
    processer.addAfterRule(rule);
  }

  /**
   * ����������������ӿ���������
   */
  private void addBeforeATPRule(AroundProcesser<DeliveryVO> processer) {
    IRule<DeliveryVO> rule = null;
    // ������
    rule = new DeliveryVOATPBeforeRule();
    processer.addBeforeRule(rule);
  }

  private void addRule(DeliveryVO[] bills) {
    // ��д��Դ����
    RewriteSrcRule rewrite = new RewriteSrcRule();
    rewrite.rewriteSrc(bills, UFBoolean.TRUE);
    // ������ʼ���Ϣ �����ʼ���Ϣ�ĳ���״̬
    RenovateQualityStateRule renovate = new RenovateQualityStateRule();
    renovate.renovateState(bills, UFBoolean.TRUE);
  }

  private void setBusiLog(DeliveryVO[] vos) {
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.BILLCLOSE);
    util.setFuncnode(FuncodeType.DELIVERY);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006002_0",
        "04006002-0130")/*�����������ر�*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
