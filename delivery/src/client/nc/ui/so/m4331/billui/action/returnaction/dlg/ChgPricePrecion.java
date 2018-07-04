package nc.ui.so.m4331.billui.action.returnaction.dlg;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

public class ChgPricePrecion {

  public static final int BODY_POS = 1;

  public static final String BODY_TABLECODE = "cdeliverycid";

  // ����
  private static String[] astnumkeys = new String[] {
    DeliveryCheckVO.NASTNUM
  };

  // �����ۿۡ���Ʒ�ۿ�
  private static String[] bdiscountkeys = new String[] {
    DeliveryCheckVO.NDISCOUNTRATE, DeliveryCheckVO.NITEMDISCOUNTRATE
  };

  // ������
  private static String[] hslkeys = new String[] {
    DeliveryCheckVO.VCHANGERATE, DeliveryCheckVO.VQTUNITRATE
  };

  // ���ҽ��
  private static String[] mnykeys = new String[] {
    DeliveryCheckVO.NTAX, DeliveryCheckVO.NMNY, DeliveryCheckVO.NTAXMNY,
    DeliveryCheckVO.NDISCOUNT
  };

  // ������
  private static String[] numkeys = new String[] {
    DeliveryCheckVO.NNUM
  };

  // ԭ�ҽ��
  private static String[] origmnykeys = new String[] {
    DeliveryCheckVO.NORIGMNY, DeliveryCheckVO.NORIGTAXMNY,
    DeliveryCheckVO.NORIGDISCOUNT
  };

  // ����
  private static String[] pricekeys = new String[] {
    // ����λԭ�Һ�˰���ۡ�����λԭ����˰����
    DeliveryCheckVO.NORIGTAXPRICE, DeliveryCheckVO.NORIGPRICE,
    // ����λԭ�Һ�˰���ۡ�����λԭ����˰����
    DeliveryCheckVO.NORIGTAXNETPRICE, DeliveryCheckVO.NORIGNETPRICE,
    // ���۵�λԭ�Һ�˰���ۡ����۵�λԭ����˰����
    DeliveryCheckVO.NQTORIGTAXPRICE, DeliveryCheckVO.NQTORIGPRICE,
    // ���۵�λԭ�Һ�˰���ۡ����۵�λԭ����˰����
    DeliveryCheckVO.NQTORIGTAXNETPRC, DeliveryCheckVO.NQTORIGNETPRICE,

  };

  /**
   * ���ҵ���
   */
  private static String[] netpricekeys = new String[] {
    DeliveryCheckVO.NTAXPRICE, DeliveryCheckVO.NPRICE,
    DeliveryCheckVO.NTAXNETPRICE, DeliveryCheckVO.NNETPRICE,
    DeliveryCheckVO.NQTTAXPRICE, DeliveryCheckVO.NQTPRICE,
    DeliveryCheckVO.NQTTAXNETPRICE, DeliveryCheckVO.NQTNETPRICE
  };

  // ��������
  private static String[] qtnumkeys = new String[] {
    DeliveryCheckVO.NQTUNITNUM
  };

  // ����˰��
  private static String[] taxratekey = new String[] {
    DeliveryCheckVO.NTAXRATE
  };

  // �۱�����
  private FieldInfo exchangeRate = new FieldInfo(DeliveryCheckVO.NEXCHANGERATE,
      ChgPricePrecion.BODY_POS, ChgPricePrecion.BODY_TABLECODE);

  // ԭ��
  private FieldInfo localOrigCurr = new FieldInfo(
      DeliveryCheckVO.CORIGCURRENCYID, ChgPricePrecion.BODY_POS,
      ChgPricePrecion.BODY_TABLECODE);

  // ��֯����
  private FieldInfo orgCurr = new FieldInfo(DeliveryCheckVO.CCURRENCYID,
      ChgPricePrecion.BODY_POS, ChgPricePrecion.BODY_TABLECODE);

  // ������֯
  private FieldInfo settleOrg = new FieldInfo(DeliveryCheckVO.PK_ORG,
      ChgPricePrecion.BODY_POS, ChgPricePrecion.BODY_TABLECODE);

  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    this.setBillPrecision(scaleprocess);
  }

  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    // ������
    scaleprocess.setHslCtlInfo(ChgPricePrecion.hslkeys, PosEnum.body, null);
    // ԭ�ҵ���
    scaleprocess.setPriceCtlInfo(ChgPricePrecion.pricekeys, PosEnum.body,
        BODY_TABLECODE, DeliveryCheckVO.CORIGCURRENCYID, PosEnum.body,
        BODY_TABLECODE);
    // ���ҵ���
    scaleprocess.setPriceCtlInfo(ChgPricePrecion.netpricekeys, PosEnum.body,
        BODY_TABLECODE, DeliveryCheckVO.CCURRENCYID, PosEnum.body,
        BODY_TABLECODE);
    // ����
    scaleprocess.setNumCtlInfo(ChgPricePrecion.astnumkeys, PosEnum.body, null,
        DeliveryCheckVO.CASTUNITID, PosEnum.body, null);
    // ������
    scaleprocess.setNumCtlInfo(ChgPricePrecion.numkeys, PosEnum.body, null,
        DeliveryCheckVO.CUNITID, PosEnum.body, null);
    // �����ۿۡ���Ʒ�ۿ�
    scaleprocess.setSaleDiscountCtlInfo(ChgPricePrecion.bdiscountkeys,
        PosEnum.body, null);
    // ��������
    scaleprocess.setNumCtlInfo(ChgPricePrecion.qtnumkeys, PosEnum.body, null,
        DeliveryCheckVO.CQTUNITID, PosEnum.body, null);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(ChgPricePrecion.mnykeys, PosEnum.body, null,
        DeliveryCheckVO.CCURRENCYID, PosEnum.body, null);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(ChgPricePrecion.origmnykeys, PosEnum.body, null,
        DeliveryCheckVO.CORIGCURRENCYID, PosEnum.body, null);
    // �۱�����
    scaleprocess.setOrgExchangeCtlInfo(this.exchangeRate, this.localOrigCurr,
        this.orgCurr, this.settleOrg);
    // ˰��
    scaleprocess.setTaxRateCtlInfo(ChgPricePrecion.taxratekey, PosEnum.body,
        null);
    scaleprocess.process();

  }
}
