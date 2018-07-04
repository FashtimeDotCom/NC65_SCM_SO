package nc.ui.so.m4331.billui.util;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.ui.so.pub.listener.ListHeadNumTrimZeroPrecisonUtil;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���������ȹ�����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-7-6 ����11:24:14
 */
public class DeliveryPrecision {

  public static final int BODY_POS = 1;

  public static final String BODY_TABLECODE = "cdeliverybid";

  public static final int HEAD_POS = 0;

  public static final String HEAD_TABLECODE = "delivery";

  // ����
  private static String[] astnumkeys = new String[] {
    DeliveryBVO.NASTNUM
  };

  // �����ۿۡ���Ʒ�ۿ�
  private static String[] bdiscountkeys = new String[] {
    DeliveryBVO.NDISCOUNTRATE, DeliveryBVO.NITEMDISCOUNTRATE
  };

  // �������
  private static String[] bodypieceKey = new String[] {
    DeliveryBVO.NPIECE
  };

  // �������
  private static String[] bodyvolKey = new String[] {
    DeliveryBVO.NVOLUME
  };

  // ��������
  private static String[] bodyweightKey = new String[] {
    DeliveryBVO.NWEIGHT
  };

  // ȫ�ֱ��ҽ��
  private static final String[] GLOBALMNYKEYS = new String[] {
    DeliveryBVO.NGLOBALTAXMNY, DeliveryBVO.NGLOBALMNY
  };

  // ���ű��ҽ��
  private static final String[] GROUPMNYKEYS = new String[] {
    DeliveryBVO.NGROUPTAXMNY, DeliveryBVO.NGROUPMNY
  };

  // ������
  private static String[] hslkeys = new String[] {
    DeliveryBVO.VCHANGERATE, DeliveryBVO.VQTUNITRATE
  };

  // ���ҽ��
  private static String[] mnykeys = new String[] {
    DeliveryBVO.NTAX, DeliveryBVO.NMNY, DeliveryBVO.NTAXMNY,
    DeliveryBVO.NDISCOUNT, DeliveryBVO.NCALTAXMNY
  };

  // ������
  private static String[] numkeys = new String[] {
    DeliveryBVO.NNUM, DeliveryBVO.NREQRSNUM, DeliveryBVO.NTOTALREPORTNUM,
    DeliveryBVO.NTOTALUNELIGNUM, DeliveryBVO.NTOTALELIGNUM
  };

  // ��������ͷ��
  private static final String[] NUMHEAD = new String[] {
    // ���������ܼ������������������
    DeliveryHVO.NTOTALASTNUM, DeliveryHVO.NTOTALPIECE,
    DeliveryHVO.NTOTALWEIGHT, DeliveryHVO.NTOTALVOLUME
  };

  // ԭ�ҽ��
  private static String[] origmnykeys = new String[] {
    DeliveryBVO.NORIGMNY, DeliveryBVO.NORIGTAXMNY, DeliveryBVO.NORIGDISCOUNT
  };

  private static DeliveryPrecision precision = new DeliveryPrecision();

  // ����
  private static String[] pricekeys = new String[] {
    // ����λԭ�Һ�˰���ۡ�����λԭ����˰����
    DeliveryBVO.NORIGTAXPRICE,
    DeliveryBVO.NORIGPRICE,
    // ����λԭ�Һ�˰���ۡ�����λԭ����˰����
    DeliveryBVO.NORIGTAXNETPRICE,
    DeliveryBVO.NORIGNETPRICE,
    // ���۵�λԭ�Һ�˰���ۡ����۵�λԭ����˰����
    DeliveryBVO.NQTORIGTAXPRICE,
    DeliveryBVO.NQTORIGPRICE,
    // ���۵�λԭ�Һ�˰���ۡ����۵�λԭ����˰����
    DeliveryBVO.NQTORIGTAXNETPRC, DeliveryBVO.NQTORIGNETPRICE,

  
  };
  
  private static String[] netpricekeys=new String[]{
    DeliveryBVO.NTAXPRICE, DeliveryBVO.NPRICE, DeliveryBVO.NTAXNETPRICE,
    DeliveryBVO.NNETPRICE, DeliveryBVO.NQTTAXPRICE, DeliveryBVO.NQTPRICE,
    DeliveryBVO.NQTTAXNETPRICE, DeliveryBVO.NQTNETPRICE
  };

  // ��������
  private static String[] qtnumkeys = new String[] {
    DeliveryBVO.NQTUNITNUM
  };

  // ����˰��
  private static String[] taxratekey = new String[] {
    DeliveryBVO.NTAXRATE
  };

  // �۱�����
  private FieldInfo exchangeRate = new FieldInfo(DeliveryBVO.NEXCHANGERATE,
      DeliveryPrecision.BODY_POS, DeliveryPrecision.BODY_TABLECODE);

  // ȫ���۱�����
  private FieldInfo globalExchgRate = new FieldInfo(
      DeliveryBVO.NGLOBALEXCHGRATE, DeliveryPrecision.BODY_POS,
      DeliveryPrecision.BODY_TABLECODE);

  // �����۱�����
  private FieldInfo groupExchgRate = new FieldInfo(DeliveryBVO.NGROUPEXCHGRATE,
      DeliveryPrecision.BODY_POS, DeliveryPrecision.BODY_TABLECODE);

  // ԭ��
  private FieldInfo localOrigCurr = new FieldInfo(DeliveryBVO.CORIGCURRENCYID,
      DeliveryPrecision.BODY_POS, DeliveryPrecision.BODY_TABLECODE);

  // ��֯����
  private FieldInfo orgCurr = new FieldInfo(DeliveryBVO.CCURRENCYID,
      DeliveryPrecision.BODY_POS, DeliveryPrecision.BODY_TABLECODE);

  // ������֯
  private FieldInfo settleOrg = new FieldInfo(DeliveryBVO.CSETTLEORGID,
      DeliveryPrecision.BODY_POS, DeliveryPrecision.BODY_TABLECODE);

  /**
   * DeliveryPrecision �Ĺ�����
   */
  private DeliveryPrecision() {
    // TODS
  }

  public static DeliveryPrecision getInstance() {
    return DeliveryPrecision.precision;
  }

  /**
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
    TotalValueScale totalscale = new TotalValueScaleProcessor(cardpanel);
    this.setBillPrecision(scaleprocess, totalscale);

  }

  /**
   * �������������������б���澫�ȡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param listpanel
   *          <p>
   * @author fengjb
   * @time 2010-5-26 ����04:55:55
   */
  public void setListPrecision(String pk_group, BillListPanel listpanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listpanel);
    TotalValueScale totalscale = new TotalValueScaleProcessor(listpanel);
    this.setBillPrecision(scaleprocess, totalscale);

    // �����б����ĺϼ���������
    this.setListHeadNumTrimZeroPrecison(listpanel);
  }

  private void setListHeadNumTrimZeroPrecison(BillListPanel listpanel) {
    ListHeadNumTrimZeroPrecisonUtil.addHeadNumTrimZeroPrecisonListener(
        listpanel.getHeadBillModel(), DeliveryPrecision.NUMHEAD);
  }

  /**
   * �����������������ü��а��Ž���ľ��ȡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param model
   *          <p>
   * @author fengjb
   * @time 2010-5-27 ����09:59:48
   */
  public void setModelPrecision(String pk_group, BillModel model) {
    TableScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  public void setScaleForSingleTable(BillScaleProcessor scale) {
    if (scale != null) {
      this.setBillPrecision(scale);
    }
  }

  /**
   * �����ȴ���
   * 
   * @param panel
   */
  public void setSingleTableScale(String pk_group, BillListPanel panel) {
    this.setModelPrecision(pk_group, panel.getHeadBillModel());
  }

  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    this.setBillPrecision(scaleprocess, null);
  }

  /**
   * �����������������õ��ݾ��ȡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param scaleprocess
   *          <p>
   * @author fengjb
   * @time 2010-5-27 ����10:13:48
   */
  private void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {

    // ������
    scaleprocess.setHslCtlInfo(DeliveryPrecision.hslkeys, PosEnum.body, null);
    // ԭ�ҵ���
    scaleprocess.setPriceCtlInfo(DeliveryPrecision.pricekeys, PosEnum.body,
        BODY_TABLECODE,DeliveryBVO.CORIGCURRENCYID,
        PosEnum.body, DeliveryPrecision.BODY_TABLECODE);
    
    //��λ�ҵ���
    scaleprocess.setPriceCtlInfo(DeliveryPrecision.netpricekeys, PosEnum.body,
        BODY_TABLECODE,DeliveryBVO.CCURRENCYID,
        PosEnum.body, DeliveryPrecision.BODY_TABLECODE);
    
    
    // ����
    scaleprocess.setNumCtlInfo(DeliveryPrecision.astnumkeys, PosEnum.body,
        null, DeliveryBVO.CASTUNITID, PosEnum.body, null);
    // ������
    scaleprocess.setNumCtlInfo(DeliveryPrecision.numkeys, PosEnum.body, null,
        DeliveryBVO.CUNITID, PosEnum.body, null);
    // �����ۿۡ���Ʒ�ۿ�
    scaleprocess.setSaleDiscountCtlInfo(DeliveryPrecision.bdiscountkeys,
        PosEnum.body, null);
    // ��������
    scaleprocess.setNumCtlInfo(DeliveryPrecision.qtnumkeys, PosEnum.body, null,
        DeliveryBVO.CQTUNITID, PosEnum.body, null);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(DeliveryPrecision.mnykeys, PosEnum.body, null,
        DeliveryBVO.CCURRENCYID, PosEnum.body, null);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(DeliveryPrecision.origmnykeys, PosEnum.body,
        null, DeliveryBVO.CORIGCURRENCYID, PosEnum.body, null);
    // �۱�����
    scaleprocess.setOrgExchangeCtlInfo(this.exchangeRate, this.localOrigCurr,
        this.orgCurr, this.settleOrg);
    // ˰��
    scaleprocess.setTaxRateCtlInfo(DeliveryPrecision.taxratekey, PosEnum.body,
        null);
    // ȫ�ֱ�λ�һ���
    scaleprocess.setGlobalExchangeCtlInfo(this.globalExchgRate,
        this.localOrigCurr, this.orgCurr);
    // ���ű�λ�һ���
    scaleprocess.setGroupExchangeCtlInfo(this.groupExchgRate,
        this.localOrigCurr, this.orgCurr);
    scaleprocess.setWeightCtlInfo(DeliveryPrecision.bodyweightKey,
        PosEnum.body, null);
    scaleprocess.setVolumnCtlInfo(DeliveryPrecision.bodyvolKey, PosEnum.body,
        null);
    scaleprocess.setUnitCtlInfo(DeliveryPrecision.bodypieceKey, PosEnum.body,
        null, DeliveryBVO.CMATERIALVID, PosEnum.body, null);
    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(DeliveryPrecision.GROUPMNYKEYS,
        PosEnum.body, null);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(DeliveryPrecision.GLOBALMNYKEYS,
        PosEnum.body, null);
    if (totalscale != null) {
      // ��ͷ�ϼ�����
      totalscale.setHeadTailKeys(DeliveryPrecision.NUMHEAD);
    }

    scaleprocess.process();

  }

  /**
   * �����������������ñ�񾫶ȡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param scaleprocess
   *          <p>
   * @author fengjb
   * @time 2010-5-27 ����10:13:35
   */
  private void setTablePrecision(TableScaleProcessor scaleprocess) {
    // ����
    scaleprocess.setNumCtlInfo(DeliveryPrecision.astnumkeys,
        DeliveryBVO.CASTUNITID);
    // ������
    scaleprocess.setNumCtlInfo(DeliveryPrecision.numkeys, DeliveryBVO.CUNITID);
    // ��������
    scaleprocess.setNumCtlInfo(DeliveryPrecision.qtnumkeys,
        DeliveryBVO.CQTUNITID);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(DeliveryPrecision.mnykeys,
        DeliveryBVO.CCURRENCYID);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(DeliveryPrecision.origmnykeys,
        DeliveryBVO.CORIGCURRENCYID);
    scaleprocess.setHslCtlInfo(DeliveryPrecision.hslkeys);
    //ԭ�ҵ���    
    scaleprocess.setPriceCtlInfo(DeliveryPrecision.pricekeys,DeliveryBVO.CORIGCURRENCYID);
    //���ҵ���
    scaleprocess.setPriceCtlInfo(DeliveryPrecision.netpricekeys,DeliveryBVO.CCURRENCYID);
    scaleprocess.setTaxRateCtlInfo(DeliveryPrecision.taxratekey);
    scaleprocess.setGlobalLocMnyCtlInfo(DeliveryPrecision.GLOBALMNYKEYS);
    scaleprocess.setGroupLocMnyCtlInfo(DeliveryPrecision.GROUPMNYKEYS);
    
    // add by quyt Ϊ�˴��������ż�������
    scaleprocess.setUnitCtlInfo(new String[] {
      DeliveryBVO.NPIECE
    }, DeliveryBVO.CMATERIALVID);
    
    scaleprocess.process();
  }

}
