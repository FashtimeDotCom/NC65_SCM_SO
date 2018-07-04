package nc.ui.so.report.tbb.m32;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

public class InvoiceDetailPrecion {

  public static final int BODY_POS = 1;

  public static final String BODY_TABLECODE = "saleinvoice_b";

  public static final int HEAD_POS = 0;

  public static final String HEAD_TABLECODE = "saleinvoice_h";

  // ����
  private static final String[] ASTNUMKEY = new String[] {
    SaleInvoiceBVO.NASTNUM
  };

  // ����
  private static final String[] DISCOUNTRATEKEY = new String[] {
    SaleInvoiceBVO.NDISCOUNTRATE, SaleInvoiceBVO.NITEMDISCOUNTRATE,
    SaleInvoiceBVO.NINVOICEDISRATE
  };

  // ȫ�ֱ��ҽ��
  private static final String[] GLOBALMNYKEY = new String[] {
    SaleInvoiceBVO.NGLOBALTAXMNY, SaleInvoiceBVO.NGLOBALMNY
  };

  // ���ű��ҽ��
  private static final String[] GROUPMNYKEY = new String[] {
    SaleInvoiceBVO.NGROUPTAXMNY, SaleInvoiceBVO.NGROUPMNY
  };

  // ��ͷ�ۿ�
  private static final String[] HEAD_DISRATEKEYS = new String[] {
    SaleInvoiceHVO.NHVOICEDISRATE
  };

  // �����ʾ���
  private static final String[] HSLKEY = new String[] {
    SaleInvoiceBVO.VCHANGERATE, SaleInvoiceBVO.VQTUNITRATE
  };

  private static InvoiceDetailPrecion instance = new InvoiceDetailPrecion();

  // ���ҽ��
  private static final String[] MNYKEY = new String[] {
    SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NMNY, SaleInvoiceBVO.NTAXMNY,
    SaleInvoiceBVO.NDISCOUNT
  };

  // ������
  private static final String[] NUMKEY = new String[] {
    SaleInvoiceBVO.NNUM
  };

  // ԭ�ҽ��
  private static final String[] ORIGMNYKEY = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NORIGDISCOUNT, SaleInvoiceBVO.NORIGSUBMNY
  };

  // ��ͷԭ�ҽ��
  private static final String[] ORIGMNYKEYHEAD = new String[] {
    SaleInvoiceHVO.NTOTALORIGMNY, SaleInvoiceHVO.NTOTALORIGSUBMNY
  };

  // ����
  private static final String[] PRICEKEY = new String[] {
    SaleInvoiceBVO.NORIGTAXPRICE, SaleInvoiceBVO.NORIGPRICE,
    SaleInvoiceBVO.NORIGTAXNETPRICE, SaleInvoiceBVO.NORIGNETPRICE,
    SaleInvoiceBVO.NQTORIGTAXPRICE, SaleInvoiceBVO.NQTORIGPRICE,
    SaleInvoiceBVO.NQTORIGTAXNETPRC, SaleInvoiceBVO.NQTORIGNETPRICE,
  };

  private static final String[] NETPRICEKEYS = new String[] {
    SaleInvoiceBVO.NTAXPRICE, SaleInvoiceBVO.NPRICE,
    SaleInvoiceBVO.NTAXNETPRICE, SaleInvoiceBVO.NNETPRICE,
    SaleInvoiceBVO.NQTTAXPRICE, SaleInvoiceBVO.NQTPRICE,
    SaleInvoiceBVO.NQTTAXNETPRICE, SaleInvoiceBVO.NQTNETPRICE
  };

  // ��������
  private static final String[] QTNUMKEY = new String[] {
    SaleInvoiceBVO.NQTUNITNUM
  };

  // ����˰��
  private static final String[] TAXRATEKEY = new String[] {
    SaleInvoiceBVO.NTAXRATE
  };

  public static InvoiceDetailPrecion getInstance() {
    return InvoiceDetailPrecion.instance;
  }

  /**
   * ���������������O�ÿ�Ƭ���澫�ȡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   *          ����ID
   * @param cardpanel
   *          ��Ƭpanel
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-1 ����02:14:33
   */
  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    this.setBillPrecision(scaleprocess);
  }

  /**
   * �����������������õ��ݾ��ȡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param scaleprocess
   *          <p>
   * @author fengjb
   * @time 2010-8-17 ����08:19:49
   */
  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(InvoiceDetailPrecion.GROUPMNYKEY,
        PosEnum.body, null);
    // ���ҽ��
    scaleprocess.setOrgLocMnyCtlInfo(InvoiceDetailPrecion.MNYKEY, PosEnum.body,
        null);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(InvoiceDetailPrecion.GLOBALMNYKEY,
        PosEnum.body, null);
    // �����ʾ���
    scaleprocess.setHslCtlInfo(InvoiceDetailPrecion.HSLKEY, PosEnum.body, null);
    // ����
    scaleprocess.setPriceCtlInfo(InvoiceDetailPrecion.PRICEKEY, PosEnum.body,
        null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);

    // ���ҵ���
    scaleprocess.setPriceCtlInfo(InvoiceDetailPrecion.NETPRICEKEYS,
        PosEnum.body, null, SaleInvoiceHVO.CCURRENCYID, PosEnum.head, null);

    // ����
    scaleprocess.setNumCtlInfo(InvoiceDetailPrecion.ASTNUMKEY, PosEnum.body,
        null, SaleInvoiceBVO.CASTUNITID, PosEnum.body, null);
    // ������
    scaleprocess.setNumCtlInfo(InvoiceDetailPrecion.NUMKEY, PosEnum.body, null,
        SaleInvoiceBVO.CUNITID, PosEnum.body, null);
    // ��������
    scaleprocess.setNumCtlInfo(InvoiceDetailPrecion.QTNUMKEY, PosEnum.body,
        null, SaleInvoiceBVO.CQTUNITID, PosEnum.body, null);
    // ��ͷ�ۿ�
    scaleprocess.setSaleDiscountCtlInfo(InvoiceDetailPrecion.HEAD_DISRATEKEYS,
        PosEnum.body, null);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(InvoiceDetailPrecion.ORIGMNYKEY, PosEnum.body,
        null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);

    // ԭ�ҽ���ͷ��
    scaleprocess.setMnyCtlInfo(InvoiceDetailPrecion.ORIGMNYKEYHEAD,
        PosEnum.body, null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);
    // ����
    scaleprocess.setSaleDiscountCtlInfo(InvoiceDetailPrecion.DISCOUNTRATEKEY,
        PosEnum.body, null);
    // ˰��
    scaleprocess.setTaxRateCtlInfo(InvoiceDetailPrecion.TAXRATEKEY,
        PosEnum.body, null);
    scaleprocess.process();

  }
}
