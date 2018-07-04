package nc.ui.so.pub.precision;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.so.entry.RecPlanVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * �տ�ƻ����ȴ���
 * 
 * @since 6.0
 * @version 2011-7-1 ����03:36:37
 * @author ô��
 */
public class RecPlanPrecision {

  private static RecPlanPrecision instance = new RecPlanPrecision();

  // ԭ�ҽ��
  private static final String[] ORIGMNYKEY = new String[] {
    RecPlanVO.NORIGMNY, RecPlanVO.NTOTALORIGMNY
  };

  /**
   * ProfitPrecision �Ĺ�����
   */
  private RecPlanPrecision() {
    // ȱʡ���췽��
  }

  public static RecPlanPrecision getInstance() {
    return RecPlanPrecision.instance;
  }

  /**
   * �O�ÿ�Ƭ���澫��
   * 
   * @param pk_group
   * @param cardpanel
   */
  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    this.setBillPrecision(scaleprocess);
  }

  /**
   * �����б���澫��
   * 
   * @param pk_group
   * @param listpanel
   */
  public void setListPrecision(String pk_group, BillListPanel listpanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listpanel);
    this.setBillPrecision(scaleprocess);
  }

  /**
   * ������ƽ����ľ���
   * 
   * @param pk_group
   * @param model
   */
  public void setModelPrecision(String pk_group, BillModel model) {
    TableScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  /**
   * ���õ��ݾ���
   * 
   * @param scaleprocess
   */
  private void setBillPrecision(BillScaleProcessor scaleprocess) {

    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(RecPlanPrecision.ORIGMNYKEY, PosEnum.body, null,
        SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);

    scaleprocess.process();

  }

  /**
   * ���ñ�񾫶�
   * 
   * @param scaleprocess
   */
  private void setTablePrecision(TableScaleProcessor scaleprocess) {
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(RecPlanPrecision.ORIGMNYKEY,
        RecPlanVO.CORIGCURRENCYID);

    scaleprocess.process();
  }
}
