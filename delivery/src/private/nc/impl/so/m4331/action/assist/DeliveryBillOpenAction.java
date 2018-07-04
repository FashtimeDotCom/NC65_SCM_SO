package nc.impl.so.m4331.action.assist;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m4331.assist.state.DeliveryStateMachine;
import nc.bs.so.m4331.assist.state.bill.BillOpenState;
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
 * ������������
 * 
 * @since 6.0
 * @version 2011-3-1 ����04:22:03
 * @author ף����
 */
public class DeliveryBillOpenAction {
  public DeliveryVO[] openBill(SOParameterVO paravo) {
    try {
      this.setCheck(paravo.getBusinessCheckMap());
      DeliveryVO[] bills = (DeliveryVO[]) paravo.getVos();
      TimeLog.logStart();
      AroundProcesser<DeliveryVO> atpprocesser =
        new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.BillOpenAction);
      this.addBeforeATPRule(atpprocesser);
      this.addAfterATPRule(atpprocesser);

      atpprocesser.before(bills);
      BillTransferTool<DeliveryVO> transferTool =
          new BillTransferTool<DeliveryVO>(bills);
      DeliveryVO[] newbills = transferTool.getClientFullInfoBill();
      TimeLog.info("��ȫǰ̨VO�����������ʱ���"); /*-=notranslate=-*/
      this.addRule(bills, paravo.getBusinessCheckMap());
      BillOpenState state = new BillOpenState();
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
  
  private void addRule(DeliveryVO[] bills, Map<String, Boolean> map) {
    BussiCheckRule busicheck = new BussiCheckRule();
    busicheck.setBusiCheckFlag(map);
    // ��д��Դ����
    RewriteSrcRule rewrite = new RewriteSrcRule();
    rewrite.rewriteSrc(bills, UFBoolean.FALSE);
    // ������ʼ���Ϣ �����ʼ���Ϣ�ĳ���״̬
    RenovateQualityStateRule renovate = new RenovateQualityStateRule();
    renovate.renovateState(bills, UFBoolean.FALSE);
  }

  private void setBusiLog(DeliveryVO[] vos) {
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.BILLOPEN);
    util.setFuncnode(FuncodeType.DELIVERY);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0131")/*������������*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void setCheck(Map<String, Boolean> map) {
    if (null == map || map.size() == 0) {
      return;
    }
  }
}
