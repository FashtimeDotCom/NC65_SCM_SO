package nc.impl.so.m32.action;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.bs.pub.action.N_32_APPROVE;
import nc.bs.so.m32.plugin.Action32PlugInPoint;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m32.action.rule.approve.BusiLog;
import nc.impl.so.m32.action.rule.approve.CheckAppEnableRule;
import nc.impl.so.m32.action.rule.approve.PushSquareRule;
import nc.impl.so.m32.action.rule.approve.ReWriteArsubAppRule;

/**
 * ���۷�Ʊ����action
 * 
 * @since 6.3
 * @version 2012-12-10 ����01:31:15
 * @author yaogj
 */
public class ApproveSaleInvoiceAction {

  /**
   * �����������������۷�Ʊ��������ʵ�֡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param script
   * @return <p>
   * @author ��ӱ�
   * @time 2010-1-22 ����11:02:45
   */
  public Object approve(N_32_APPROVE script) {
    Object ret = null;
    try {
      // Object inCurObject = script.getPfParameterVO().m_preValueVo;
      //
      // SaleInvoiceVO[] inCurVOs = new SaleInvoiceVO[] {
      // (SaleInvoiceVO) inCurObject
      // };
      // m_preValueVo ��ͨ��ĳЩ���õ�ʱ��û������
      SaleInvoiceVO[] inCurVOs =
          (SaleInvoiceVO[]) script.getPfParameterVO().m_preValueVos;

      AroundProcesser<SaleInvoiceVO> processer =
          new AroundProcesser<SaleInvoiceVO>(Action32PlugInPoint.ApproveAction);

      this.addBeforeRule(processer);
      TimeLog.logStart();
      processer.before(inCurVOs);
      TimeLog.info("����������ǰִ��ҵ�����"); /*-=notranslate=-*/

      TimeLog.logStart();
      ret = script.procActionFlow(script.getPfParameterVO());
      TimeLog.info("�����������������˴�����������޸�"); /*-=notranslate=-*/
      if (null == ret) {
        ret = this.queryNewVO(inCurVOs);

        this.addAfterRule(processer);
        TimeLog.logStart();
        processer.after((SaleInvoiceVO[]) ret);
        TimeLog.info("������������ִ��ҵ�����"); /*-=notranslate=-*/
      }

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return ret;
  }

  /**
   * ��������������������۷�Ʊ������������ҵ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-20 ����04:35:15
   */
  private void addAfterRule(AroundProcesser<SaleInvoiceVO> processer) {
    // ��д���۷��õ�
    IRule<SaleInvoiceVO> rule = new ReWriteArsubAppRule();
    processer.addAfterRule(rule);
    // ��ʽ����
    rule = new PushSquareRule();
    processer.addAfterRule(rule);

  }

  /**
   * ��������������������۷�Ʊ����������ǰҵ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-22 ����08:57:00
   */
  private void addBeforeRule(AroundProcesser<SaleInvoiceVO> processer) {

    // ������۷�Ʊ�Ƿ�����
    IRule<SaleInvoiceVO> rule = new CheckAppEnableRule();
    processer.addBeforeRule(rule);
    // ҵ����־
    rule = new BusiLog();
    processer.addBeforeRule(rule);

  }

  /**
   * ����������������ѯ��˺�ƱVO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   * @return <p>
   * @author ��ӱ�
   * @time 2010-1-22 ����02:20:47
   */
  private SaleInvoiceVO[] queryNewVO(SaleInvoiceVO[] bills) {
    int ilength = bills.length;
    String[] ids = new String[ilength];
    for (int i = 0; i < ilength; i++) {
      ids[i] = bills[i].getPrimaryKey();
    }
    BillQuery<SaleInvoiceVO> query =
        new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
    return query.query(ids);

  }

}
