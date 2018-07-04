package nc.impl.so.m30.action.main;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pub.action.N_30R_UNAPPROVE;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m30.rule.me.SaleOrderVOUnApproveAfterRule;
import nc.bs.so.m30.rule.unapprove.CheckReviseUnApprovableRule;
import nc.bs.so.m30.rule.unapprove.UNApproveStateRule;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * ���۶����޶�������
 * 
 * @since 6.3
 * @version 2014-12-5 ����2:17:26
 * @author wangshu6
 */
public class UnApproveSaleOrderReviseAction {

  public SaleOrderVO[] unApprove(SaleOrderVO[] bills, N_30R_UNAPPROVE script) {
    SaleOrderVO[] ret = null;
    try {

      // ע���
      AroundProcesser<SaleOrderVO> processer =
          new AroundProcesser<SaleOrderVO>(Action30PlugInPoint.UnApproveAction);

      TimeLog.logStart();
//      modify by wangshu6 for ���۶����޶�ȡ������ ����ҪУ�� ֻ��״̬�ĸı� 2014-12-8
      this.addBeforeRule(processer);
      processer.before(bills);
      TimeLog.info("��������ǰ���������"); /* -=notranslate=- */

      /************* �����Ϊ�������������������ܽ����޸� *********************/
      script.procUnApproveFlow(script.getPfParameterVO());
      /************** ���ؽ�� *************************************************/

      // ת������ƽ̨������״̬��ҵ�񵥾�״̬�����־û�
      SaleOrderVO[] newbills = script.getVos();

      this.updateNewBillStatus(newbills);
      Integer newbillstatus = newbills[0].getParentVO().getFstatusflag();

      TimeLog.logStart();
//      this.addAfterRule(processer, newbillstatus);
      processer.after(newbills);
      TimeLog.info("������������������"); /* -=notranslate=- */

      ret = newbills;
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return ret;
  }

  private void updateNewBillStatus(SaleOrderVO[] newbills) {

    SOPfStatusChgRule statuschgrule = new SOPfStatusChgRule();
    SaleOrderHVO[] updateheads = new SaleOrderHVO[newbills.length];
    List<SaleOrderBVO> listbody = new ArrayList<SaleOrderBVO>();
    int i = 0;
    for (SaleOrderVO ordervo : newbills) {
      statuschgrule.changePfToBillStatus(ordervo);
      updateheads[i] = ordervo.getParentVO();
      for (SaleOrderBVO bvo : ordervo.getChildrenVO()) {
        listbody.add(bvo);
      }
    }
    String[] headupname = new String[] {
      SaleOrderHVO.FSTATUSFLAG
    };
    VOUpdate<SaleOrderHVO> headupsrv = new VOUpdate<SaleOrderHVO>();
    headupsrv.update(updateheads, headupname);

    String[] bodyupname = new String[] {
      SaleOrderBVO.FROWSTATUS
    };
    VOUpdate<SaleOrderBVO> bodyupsrv = new VOUpdate<SaleOrderBVO>();
    SaleOrderBVO[] updatebodys =
        listbody.toArray(new SaleOrderBVO[listbody.size()]);
    bodyupsrv.update(updatebodys, bodyupname);

  }

  private void addAfterRule(AroundProcesser<SaleOrderVO> processer,
      Integer newbillstatus) {
    IRule<SaleOrderVO> rule = null;
    // ���ü��
    if (SysInitGroupQuery.isCREDITEnabled()) {
      rule = new RenovateARByHidsEndRule(M30EngrossAction.M30UnApprove);
      processer.addAfterRule(rule);
    }
    if (BillStatus.FREE.equalsValue(newbillstatus)
        || BillStatus.NOPASS.equalsValue(newbillstatus)
        || BillStatus.AUDITING.equalsValue(newbillstatus)) {
      // ����ʱ����״̬�Ĵ���
      rule = new UNApproveStateRule();
      processer.addAfterRule(rule);
    }

    // ����� ɾ����Ӧ�Ķ������������
    rule = new SaleOrderVOUnApproveAfterRule();
    processer.addAfterRule(rule);

  }

  private void addBeforeRule(AroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;

    // ��鵥���Ƿ��������
    rule = new CheckReviseUnApprovableRule();
    processer.addBeforeRule(rule);

  }

}
