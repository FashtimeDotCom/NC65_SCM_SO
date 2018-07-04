package nc.ui.so.report.tbb.m30;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * Ԥ�����鶩����ϸ����
 * 
 * @since 6.0
 * @version 2011-6-13 ����11:11:05
 * @author ף����
 */
public class OrderDetailPrecion {
  public static final int BODY_POS = 1;

  public static final String BODY_TABLECODE = "bodytable1";

  public static final int HEAD_POS = 0;

  public static final String HEAD_TABLECODE = "head";

  // ����
  private static final String[] ASTNUMKEYS = new String[] {
    SaleOrderBVO.NASTNUM
  };

  // �����ۿ�
  private static final String[] BODY_DISRATEKEYS = new String[] {
    SaleOrderBVO.NDISCOUNTRATE, SaleOrderBVO.NITEMDISCOUNTRATE
  };

  // ȫ�ֱ��ҽ��
  private static final String[] GLOBALMNYKEYS = new String[] {
    SaleOrderBVO.NGLOBALTAXMNY, SaleOrderBVO.NGLOBALMNY
  };

  // ���ű��ҽ��
  private static final String[] GROUPMNYKEYS = new String[] {
    SaleOrderBVO.NGROUPTAXMNY, SaleOrderBVO.NGROUPMNY
  };

  // ��ͷ�ۿ�
  private static final String[] HEAD_DISRATEKEYS = new String[] {
    SaleOrderHVO.NDISCOUNTRATE
  };

  // ������
  private static final String[] HSLKEYS = new String[] {
    SaleOrderBVO.VCHANGERATE, SaleOrderBVO.VQTUNITRATE
  };

  // ���ҽ��
  private static final String[] MNYKEYS = new String[] {
    SaleOrderBVO.NTAX, SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY,
    SaleOrderBVO.NDISCOUNT
  };

  // ԭ����������ͷ��
  /* private static final String[] NUMHEAD = new String[] {
   * SaleOrderHVO.NTOTALNUM, SaleOrderHVO.NTOTALVOLUME,
   * SaleOrderHVO.NTOTALWEIGHT,
   * }; */

  // ������
  private static final String[] NUMKEYS = new String[] {
    SaleOrderBVO.NNUM
  };

  // ԭ�ҽ��
  private static final String[] ORIGMNYKEYS = new String[] {
    SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY, SaleOrderBVO.NORIGDISCOUNT
  };

  // ԭ�ҽ���ͷ��
  private static final String[] ORIGMNYKEYSHEAD = new String[] {
    SaleOrderHVO.NTOTALMNY, SaleOrderHVO.NTOTALORIGMNY,
    SaleOrderHVO.NTOTALORIGSUBMNY, SaleOrderHVO.NPRECEIVEMNY,
    SaleOrderHVO.NPRECEIVEQUOTA, SaleOrderHVO.NRECEIVEDMNY,
    SaleOrderHVO.NTHISRECEIVEMNY
  };

  private static OrderDetailPrecion precision = new OrderDetailPrecion();

  // ����
  private static final String[] PRICEKEYS = new String[] {
    SaleOrderBVO.NORIGTAXPRICE, SaleOrderBVO.NORIGPRICE,
    SaleOrderBVO.NORIGTAXNETPRICE, SaleOrderBVO.NORIGNETPRICE,
    SaleOrderBVO.NQTORIGTAXPRICE, SaleOrderBVO.NQTORIGPRICE,
    SaleOrderBVO.NQTORIGTAXNETPRC, SaleOrderBVO.NQTORIGNETPRICE,
  
  };
  
  private static final String[] NETPRICEKEYS=new String[]{
    SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXNETPRICE,
    SaleOrderBVO.NNETPRICE, SaleOrderBVO.NQTTAXPRICE, SaleOrderBVO.NQTPRICE,
    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE
  };

  // ��������
  private static final String[] QTNUMKEYS = new String[] {
    SaleOrderBVO.NQTUNITNUM
  };

  // ����˰��
  private static final String[] TAXRATEKEY = new String[] {
    SaleOrderBVO.NTAXRATE
  };

  public static OrderDetailPrecion getInstance() {
    return OrderDetailPrecion.precision;
  }

  /**
   * 
   * �����������������ÿ�Ƭ���澫�ȡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param cardpanel
   *          <p>
   * @author fengjb
   * @time 2010-5-26 ����04:55:35
   */
  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    this.setBillPrecision(scaleprocess);
  }

  /**
   * 
   * �����������������õ��ݾ��ȡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param scaleprocess
   *          <p>
   * @author fengjb
   * @time 2010-5-27 ����10:13:48
   */
  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(OrderDetailPrecion.GROUPMNYKEYS,
        PosEnum.body, null);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(OrderDetailPrecion.GLOBALMNYKEYS,
        PosEnum.body, null);
    // ������
    scaleprocess.setHslCtlInfo(OrderDetailPrecion.HSLKEYS, PosEnum.body, null);
    // ԭ�ҵ���
    scaleprocess.setPriceCtlInfo(OrderDetailPrecion.PRICEKEYS, PosEnum.body,
        null,SaleOrderHVO.CORIGCURRENCYID,PosEnum.head,null);
    // ��λ�ҵ���
    scaleprocess.setPriceCtlInfo(OrderDetailPrecion.NETPRICEKEYS, PosEnum.body,
        null,SaleOrderBVO.CCURRENCYID,PosEnum.body,null);
    
    // ����
    scaleprocess.setNumCtlInfo(OrderDetailPrecion.ASTNUMKEYS, PosEnum.body,
        null, SaleOrderBVO.CASTUNITID, PosEnum.body, null);
    // ������
    scaleprocess.setNumCtlInfo(OrderDetailPrecion.NUMKEYS, PosEnum.body, null,
        SaleOrderBVO.CUNITID, PosEnum.body, null);
    // ��������
    scaleprocess.setNumCtlInfo(OrderDetailPrecion.QTNUMKEYS, PosEnum.body,
        null, SaleOrderBVO.CQTUNITID, PosEnum.body, null);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(OrderDetailPrecion.MNYKEYS, PosEnum.body, null,
        SaleOrderBVO.CCURRENCYID, PosEnum.body, null);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(OrderDetailPrecion.ORIGMNYKEYS, PosEnum.body,
        null, SaleOrderHVO.CORIGCURRENCYID, PosEnum.body, null);

    // ԭ�ҽ���ͷ��
    scaleprocess.setMnyCtlInfo(OrderDetailPrecion.ORIGMNYKEYSHEAD,
        PosEnum.body, null, SaleOrderHVO.CORIGCURRENCYID, PosEnum.body, null);
    // ��ͷ�ۿ�
    scaleprocess.setSaleDiscountCtlInfo(OrderDetailPrecion.HEAD_DISRATEKEYS,
        PosEnum.body, null);
    // �����ۿ�
    scaleprocess.setSaleDiscountCtlInfo(OrderDetailPrecion.BODY_DISRATEKEYS,
        PosEnum.body, null);
    // ˰��
    scaleprocess.setTaxRateCtlInfo(OrderDetailPrecion.TAXRATEKEY, PosEnum.body,
        null);
    scaleprocess.process();
  }
}
