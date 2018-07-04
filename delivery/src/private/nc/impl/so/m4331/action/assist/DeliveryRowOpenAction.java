package nc.impl.so.m4331.action.assist;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m4331.assist.state.DeliveryStateMachine;
import nc.bs.so.m4331.assist.state.row.RowOpenState;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPAfterRule;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPBeforeRule;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.bill.convertor.ViewToBillConvertor;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.data.view.tool.ViewTransferTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m4331.action.assist.rule.BussiCheckRule;
import nc.impl.so.m4331.action.assist.rule.RenovateQualityStateRule;
import nc.impl.so.m4331.action.assist.rule.RewriteSrcRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.enumeration.ActionType;
import nc.vo.so.pub.enumeration.FuncodeType;
import nc.vo.so.pub.util.BusinessLogUtil;

/**
 * �������д�
 * 
 * @since 6.0
 * @version 2011-3-1 ����04:22:20
 * @author ף����
 */
public class DeliveryRowOpenAction {
  public DeliveryVO[] openRow(DeliveryVO[] bills, Map<String, Boolean> map) {
    try {
      AroundProcesser<DeliveryVO> atpprocesser =
        new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.RowOpenAction);
      this.addBeforeATPRule(atpprocesser);
      this.addAfterATPRule(atpprocesser);
      
      atpprocesser.before(bills);
      this.addRule(bills, map);
      IObjectConvert<DeliveryVO, DeliveryViewVO> convert =
          new BillToViewConvertor<DeliveryVO, DeliveryViewVO>(
              DeliveryViewVO.class);
      DeliveryViewVO[] views = convert.convert(bills);
      IState<DeliveryViewVO> state = new RowOpenState();
      views = this.setRowStatus(state, views);
      IObjectConvert<DeliveryViewVO, DeliveryVO> billconvert =
          new ViewToBillConvertor<DeliveryViewVO, DeliveryVO>(DeliveryVO.class);
      DeliveryVO[] newbills = billconvert.convert(views);
      this.setBusiLog(newbills);
      atpprocesser.after(newbills);
      return newbills;
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
    BussiCheckRule checkRule = new BussiCheckRule();
    checkRule.setBusiCheckFlag(map);
    // ��д��Դ����
    RewriteSrcRule rewrite = new RewriteSrcRule();
    rewrite.rewriteSrc(bills, UFBoolean.FALSE);
    // ������ʼ���Ϣ �����ʼ���Ϣ�ĳ���״̬
    RenovateQualityStateRule renovate = new RenovateQualityStateRule();
    renovate.renovateState(bills, UFBoolean.FALSE);
  }

  private void setBusiLog(DeliveryVO[] vos) {
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.OUTOPEN);
    util.setFuncnode(FuncodeType.DELIVERY);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0133")/*�������г����*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLogForBody(vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private DeliveryViewVO[] setRowStatus(IState<DeliveryViewVO> state,
      DeliveryViewVO[] originviews) {
    ViewTransferTool<DeliveryViewVO> tool =
        new ViewTransferTool<DeliveryViewVO>(originviews);
    DeliveryViewVO[] views = tool.getOriginViews();
    DeliveryStateMachine machine = new DeliveryStateMachine();
    machine.setState(state, views);
    // ����������VO
    views = tool.getViewForToClient(views);
    return views;
  }
}
