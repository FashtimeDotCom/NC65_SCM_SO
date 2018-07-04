package nc.impl.so.m32.action;

import nc.bs.so.m32.plugin.Action32PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m32.action.rule.commit.CheckCommitEnableRule;
import nc.impl.so.m32.action.rule.commit.SetCommitStatusRule;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ�ύ���ܺ�̨ʵ��
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-5-21 ����03:00:56
 */
public class CommitSaleInvoiceAction {
  /**
   * �����������������۷�Ʊ�ύ��������ʵ�֡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoices
   * @return
   *         <p>
   * @author fengjb
   * @time 2010-5-21 ����03:01:36
   */
  public SaleInvoiceVO[] commit(SaleInvoiceVO[] voInvoices) {

    TimeLog.logStart();
    BillTransferTool<SaleInvoiceVO> transferTool =
        new BillTransferTool<SaleInvoiceVO>(voInvoices);

    TimeLog.info("����ǰ̨VO����֯����ֵʱʹ��"); /*-=notranslate=-*/

    AroundProcesser<SaleInvoiceVO> processer =
        new AroundProcesser<SaleInvoiceVO>(Action32PlugInPoint.SendAppAction);

    this.addRule(processer);
    TimeLog.logStart();
    processer.before(voInvoices);
    TimeLog.info("��������ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    this.updateBillStatus(voInvoices);

    TimeLog.info("�������ݿ��¼"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(voInvoices);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0040")/*@res "���������ִ��ҵ�����"*/); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleInvoiceVO[] vos = transferTool.getBillForToClient(voInvoices);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0041")/*@res "��֯����ֵ,����������VO"*/); /*-=notranslate=-*/

    TimeLog.logStart();
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0042")/*@res "ҵ����־"*/); /*-=notranslate=-*/
    return vos;

  }

  /**
   * �����������������ҵ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author fengjb
   * @time 2010-5-21 ����03:50:56
   */
  private void addRule(AroundProcesser<SaleInvoiceVO> processer) {
    // У�����۷�Ʊ�Ƿ���������
    IRule<SaleInvoiceVO> rule = new CheckCommitEnableRule();
    processer.addBeforeRule(rule);
    // ���������Ʊ״̬
    rule = new SetCommitStatusRule();
    processer.addBeforeRule(rule);

  }

  /**
   * ���������������������ݿ��м�¼�ĵ���״̬��
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoices
   *          <p>
   * @author fengjb
   * @time 2010-5-21 ����03:50:37
   */
  private void updateBillStatus(SaleInvoiceVO[] voInvoices) {
    int ilength = voInvoices.length;
    SaleInvoiceHVO[] voHeads = new SaleInvoiceHVO[ilength];
    for (int i = 0; i < ilength; i++) {
      voHeads[i] = voInvoices[i].getParentVO();
    }
    VOUpdate<SaleInvoiceHVO> updatesrv = new VOUpdate<SaleInvoiceHVO>();
    String[] updateKeys = new String[] {
      SaleInvoiceHVO.FSTATUSFLAG
    };
    updatesrv.update(voHeads, updateKeys);

  }
}
