package nc.ui.so.pub.execinfo;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.so.pub.execinfo.ExecInfoReportVO;

/**
 * ����ִ��������ȴ���
 * 
 * @since 6.0
 * @version 2011-7-26 ����07:55:44
 * @author ô��
 */
public class ExecInfoPrecision {

  /** ���� */

  // ���ҽ��
  private static final String[] MNYKEYS = new String[] {
    // δ��Ʊ���\δ�տ���
    ExecInfoReportVO.NEEDINVOICEMONEY, ExecInfoReportVO.NEEDPAYMONEY,
    // �ѿ�Ʊ���\���տ���
    ExecInfoReportVO.TOTALINVOICEMONEY, ExecInfoReportVO.TOTALPAYMONEY
  };

  // ������
  private static final String[] NUMKEYS = new String[] {
    // ������δ��Ʊ����
    ExecInfoReportVO.NNUM, ExecInfoReportVO.NEEDINVOICENUM,
    // δ��������\δ��������
    ExecInfoReportVO.NEEDOUTNUM, ExecInfoReportVO.NEEDSENDNUM,
    // �ѿ�Ʊ����\�ѳ�������
    ExecInfoReportVO.NTOTALINVOICENUM, ExecInfoReportVO.NTOTALOUTNUM,
    // �ѷ�������\Ӧ������
    ExecInfoReportVO.NTOTALSENDNUM, ExecInfoReportVO.SHOULDSENDNUM
  };

  private static ExecInfoPrecision precision = new ExecInfoPrecision();

  /**
   * 
   * PreOrderPrecision �Ĺ�����
   */
  private ExecInfoPrecision() {
    //
  }

  public static ExecInfoPrecision getInstance() {
    return ExecInfoPrecision.precision;
  }

  /**
   * ���ü��а��Ž��澫��
   * 
   * @param pk_group
   * @param model
   */
  public void setModelPrecision(String pk_group, BillModel model) {
    BillModelScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  /**
   * �����ȴ���
   * 
   * @param panel
   */
  public void setSingleTableScale(String pk_group, BillListPanel panel) {
    this.setModelPrecision(pk_group, panel.getHeadBillModel());
  }

  /**
   * ���ñ�񾫶�
   * 
   * @param scaleprocess
   */
  private void setTablePrecision(TableScaleProcessor scaleprocess) {

    // ������
    scaleprocess.setNumCtlInfo(ExecInfoPrecision.NUMKEYS,
        ExecInfoReportVO.CUNITID);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(ExecInfoPrecision.MNYKEYS,
        ExecInfoReportVO.CCURRENCYID);

    scaleprocess.process();
  }

}
