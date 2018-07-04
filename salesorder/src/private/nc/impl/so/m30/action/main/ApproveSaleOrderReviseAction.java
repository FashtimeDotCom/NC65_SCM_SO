package nc.impl.so.m30.action.main;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pub.action.N_30R_APPROVE;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.rule.approve.CheckApprovableRule;
import nc.bs.so.m30.rule.approve.CheckMaxIversionRule;
import nc.bs.so.m30.rule.approve.CheckSaleOrderStatusRule;
import nc.bs.so.m30.rule.approve.SaleOrderReviseApproveAfterRule;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * ���۶����޶���������
 * 
 * @since 6.3
 * @version 2014-12-5 ����2:17:26
 * @author wangshu6
 */
public class ApproveSaleOrderReviseAction {

  /**
   * ��������
   * 
   * @param bills
   * @param script
   * @return ����������
   */
  public Object approve(SaleOrderHistoryVO[] bills, N_30R_APPROVE script) {

    Object ret = null;
    try {

      // ��ȫ
      BillTransferTool<SaleOrderHistoryVO> transferTool =
          new BillTransferTool<SaleOrderHistoryVO>(bills);
      bills = transferTool.getClientFullInfoBill();
      // ע���
      AroundProcesser<SaleOrderVO> processer =
          new AroundProcesser<SaleOrderVO>(
              Action30PlugInPoint.ApproveAction);

      TimeLog.logStart();
      this.addBeforeRule(processer);
      processer.before(bills);
      TimeLog.info("��������ǰ���������"); /* -=notranslate=- */

      /************* �����Ϊ�������������������ܽ����޸� *********************/
      ret = script.procActionFlow(script.getPfParameterVO());
      /************** ���ؽ�� *************************************************/

      // ת������ƽ̨������״̬��ҵ�񵥾�״̬�����־û�
      SaleOrderHistoryVO[] newbills = script.getVos();
      this.updateNewBillStatus(newbills);
      TimeLog.logStart();

      Integer newbillstatus = newbills[0].getParentVO().getFstatusflag();
      this.addAfterRule(processer, newbillstatus);
      processer.after(newbills);
      TimeLog.info("������������������"); /* -=notranslate=- */

      // ����ͨ��ʱ������ƽ̨���صĲ���Ϊnull,��ʱ��Ҫ�������µ����ݣ����������(������)ֻ�ܷ���null,���������������
      if (null == ret) {
        ret = newbills;
      }
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
      updateheads[i++] = ordervo.getParentVO();
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
    // ����������д�����۶����� add by wangshu6
    @SuppressWarnings("unchecked")
    IRule<SaleOrderVO> rule = new SaleOrderReviseApproveAfterRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SaleOrderVO> processer) {
    // ��鵥���Ƿ��������
    IRule<SaleOrderVO> rule = new CheckApprovableRule();
    processer.addBeforeRule(rule);

    // У�������汾�Ƿ����޶��������°汾
    rule = new CheckMaxIversionRule();
    processer.addBeforeRule(rule);

    // ������۶����޶����޶�ǰ�汾״̬����ֹ�Ѿ�������
    rule = new CheckSaleOrderStatusRule();
    processer.addBeforeRule(rule);

  }

}
