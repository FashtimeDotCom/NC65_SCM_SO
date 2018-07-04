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
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

public class ProfitPrecision {

  // ����
  private static final String[] ASTNUMKEY = new String[] {
    ProfitVO.NASTNUM
  };

  private static ProfitPrecision instance = new ProfitPrecision();

  // ԭ�ҽ��
  private static final String[] ORIGMNYKEY = new String[] {
    ProfitVO.NTOTALCOST, ProfitVO.NTOTALINCOME, ProfitVO.NTOTALPROFIT
  };

  // ����
  private static final String[] PRICEKEY = new String[] {
    ProfitVO.NQTORIGNETPRICE
  };

  //ë����
  private static final String[] RATEKEY = new String[]{
	  ProfitVO.NTOTALPROFITRATE
  };
  /**
   * ProfitPrecision �Ĺ�����
   */
  private ProfitPrecision() {
    // ȱʡ���췽��
  }

  public static ProfitPrecision getInstance() {
    return ProfitPrecision.instance;
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

    // ����
    scaleprocess.setPriceCtlInfo(ProfitPrecision.PRICEKEY, PosEnum.body, null,ProfitVO.CORIGCURRENCYID,PosEnum.body,null);

    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(ProfitPrecision.ORIGMNYKEY, PosEnum.body, null,
        SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);
    
    //ë����
    scaleprocess.setSaleDiscountCtlInfo(ProfitPrecision.RATEKEY, PosEnum.body, null);

    scaleprocess.process();

  }

  /**
   * ���ñ�񾫶�
   * 
   * @param scaleprocess
   */
  private void setTablePrecision(TableScaleProcessor scaleprocess) {

    // ����
    scaleprocess.setPriceCtlInfo(ProfitPrecision.PRICEKEY,ProfitVO.CORIGCURRENCYID);
    // ����
    scaleprocess.setNumCtlInfo(ProfitPrecision.ASTNUMKEY, ProfitVO.CASTUNITID);

    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(ProfitPrecision.ORIGMNYKEY,
        ProfitVO.CORIGCURRENCYID);
    //ë����
    scaleprocess.setSaleDiscountCtlInfo(ProfitPrecision.RATEKEY);
    scaleprocess.process();
  }
}
