package nc.ui.so.m30.pub;

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
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>���۶������ȴ�������
 * </ul>
 * 
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-5-27 ����10:14:55
 */
public class SaleOrderPrecision {

  /**
   * ����λ�ó���
   */
  public static final int BODY_POS = 1;

  /**
   * ����ҳǩ
   */
  public static final String BODY_TABLECODE = "bodytable1";

  /**
   * ��ͷλ�ó���
   */
  public static final int HEAD_POS = 0;

  /**
   * ��ͷҳǩ
   */
  public static final String HEAD_TABLECODE = "head";

  /** ���� */
  // ����(nunvoicenumΪ���۶�������Ʊת�������ϼ����ֶΣ��ɿ�Ʊ������)
  private static final String[] ASTNUMKEYS = new String[] {
    SaleOrderBVO.NASTNUM,"nunvoicenum"
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

  /** ��ͷ */
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
    SaleOrderBVO.NDISCOUNT, SaleOrderBVO.NCALTAXMNY,
  };

  // ������
  private static final String[] NUMKEYS = new String[] {
    SaleOrderBVO.NNUM, SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM,
    SaleOrderBVO.NTOTALESTARNUM, SaleOrderBVO.NTOTALINVOICENUM,
    SaleOrderBVO.NTOTALNOTOUTNUM, SaleOrderBVO.NTOTALOUTNUM,
    SaleOrderBVO.NTOTALRETURNNUM, SaleOrderBVO.NTOTALRUSHNUM,
    SaleOrderBVO.NTOTALSENDNUM, SaleOrderBVO.NTOTALSIGNNUM,
    SaleOrderBVO.NTOTALTRADENUM, SaleOrderBVO.NREQRSNUM,
  };

  // �ۼ�***������
  private static final String[] GATHERNUMKEYS = new String[] {
    SaleOrderBVO.NTOTALSENDNUM, SaleOrderBVO.NTOTALINVOICENUM,
    SaleOrderBVO.NTOTALOUTNUM, SaleOrderBVO.NTOTALNOTOUTNUM,
    SaleOrderBVO.NTOTALSIGNNUM, SaleOrderBVO.NTRANSLOSSNUM,
    SaleOrderBVO.NTOTALRUSHNUM, SaleOrderBVO.NTOTALESTARNUM,
    SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM,
    SaleOrderBVO.NARRANGESCORNUM, SaleOrderBVO.NARRANGEPOAPPNUM,
    SaleOrderBVO.NARRANGETOORNUM, SaleOrderBVO.NARRANGETOAPPNUM,
    SaleOrderBVO.NARRANGEMONUM, SaleOrderBVO.NARRANGEPONUM,
    SaleOrderBVO.NTOTALPLONUM, SaleOrderBVO.NTOTALRETURNNUM,
    SaleOrderBVO.NTOTALTRADENUM
  };

  // ���۶����رչ�������
  private static final String[] CLOSEMAGENUMKEYS = new String[] {
    // ����������
    SaleOrderBVO.NSENDUNFINISEDNUM, SaleOrderBVO.NSENDAUDITNUM,
    // ��������
    SaleOrderBVO.NOUTUNFINISEDNUM, SaleOrderBVO.NOUTAUDITNUM,
    // ��Ʊ����
    SaleOrderBVO.NINVUNFINISEDNUM, SaleOrderBVO.NINVOICEAUDITNUM,
    // ��������
    SaleOrderBVO.NOUTNOTAUDITNUM, SaleOrderBVO.NNOTARNUM,
    SaleOrderBVO.NLOSSNOTAUDITNUM, SaleOrderBVO.NNOTCOSTNUM
  };

  // ԭ����������ͷ��
  private static final String[] NUMHEAD = new String[] {
    SaleOrderHVO.NTOTALNUM, SaleOrderHVO.NTOTALVOLUME,
    SaleOrderHVO.NTOTALWEIGHT,
  };

  // ԭ�ҽ��(nuninvoicemny,�ɿ�Ʊ�����۶�������Ʊת�������ϼ����ֶ�)
  private static final String[] ORIGMNYKEYS = new String[] {
    SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NBFORIGSUBMNY,
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALESTARMNY,
    SaleOrderBVO.NTOTALPAYMNY,SaleOrderBVO.NORIGSUBMNY,"nuninvoicemny"
  };

  // ԭ�ҽ���ͷ��
  private static final String[] ORIGMNYKEYSHEAD = new String[] {
    SaleOrderHVO.NTOTALMNY, SaleOrderHVO.NTOTALORIGMNY,
    SaleOrderHVO.NTOTALORIGSUBMNY, SaleOrderHVO.NPRECEIVEMNY,
    SaleOrderHVO.NPRECEIVEQUOTA, SaleOrderHVO.NRECEIVEDMNY,
    SaleOrderHVO.NTHISRECEIVEMNY, SaleOrderHVO.NLRGTOTALORIGMNY
  };

  // ����
  private static final String[] PIECEKEY = new String[] {
    SaleOrderBVO.NPIECE
  };

  private static SaleOrderPrecision precision = new SaleOrderPrecision();

  // ����
  private static final String[] PRICEKEYS = new String[] {
    SaleOrderBVO.NORIGTAXPRICE, SaleOrderBVO.NORIGPRICE,
    SaleOrderBVO.NORIGTAXNETPRICE, SaleOrderBVO.NORIGNETPRICE,
    SaleOrderBVO.NQTORIGTAXPRICE, SaleOrderBVO.NQTORIGPRICE,
    SaleOrderBVO.NQTORIGTAXNETPRC, SaleOrderBVO.NQTORIGNETPRICE,
    SaleOrderBVO.NACCPRICE, SaleOrderBVO.NMFFILEPRICE
  
  };
  
  private static final String[] netpricekeys=new String[]{  SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXNETPRICE,
    SaleOrderBVO.NNETPRICE, SaleOrderBVO.NQTTAXPRICE, SaleOrderBVO.NQTPRICE,
    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
    SaleOrderBVO.NASKQTORIGNETPRICE, SaleOrderBVO.NASKQTORIGPRICE,
    SaleOrderBVO.NASKQTORIGTAXPRC, SaleOrderBVO.NASKQTORIGTXNTPRC};

  // ��������
  private static final String[] QTNUMKEYS = new String[] {
    SaleOrderBVO.NQTUNITNUM
  };

  // ����˰��
  private static final String[] TAXRATEKEY = new String[] {
    SaleOrderBVO.NTAXRATE
  };

  // ���
  private static final String[] VOLUMEKEY = new String[] {
    SaleOrderBVO.NVOLUME
  };

  // ����
  private static final String[] WEIGHTKEY = new String[] {
    SaleOrderBVO.NWEIGHT
  };

  /** ���ʾ��� */
  // �۱�����
  private FieldInfo exchangeRate = new FieldInfo(SaleOrderBVO.NEXCHANGERATE,
      SaleOrderPrecision.BODY_POS, SaleOrderPrecision.BODY_TABLECODE);

  // ȫ���۱�����
  private FieldInfo globalExchgRate = new FieldInfo(
      SaleOrderBVO.NGLOBALEXCHGRATE, SaleOrderPrecision.BODY_POS,
      SaleOrderPrecision.BODY_TABLECODE);

  // �����۱�����
  private FieldInfo groupExchgRate = new FieldInfo(
      SaleOrderBVO.NGROUPEXCHGRATE, SaleOrderPrecision.BODY_POS,
      SaleOrderPrecision.BODY_TABLECODE);

  // ԭ��
  private FieldInfo localOrigCurr = new FieldInfo(SaleOrderHVO.CORIGCURRENCYID,
      SaleOrderPrecision.HEAD_POS, SaleOrderPrecision.HEAD_TABLECODE);

  // ��֯����
  private FieldInfo orgCurr = new FieldInfo(SaleOrderBVO.CCURRENCYID,
      SaleOrderPrecision.BODY_POS, SaleOrderPrecision.BODY_TABLECODE);

  // ������֯
  private FieldInfo settleOrg = new FieldInfo(SaleOrderBVO.CSETTLEORGID,
      SaleOrderPrecision.BODY_POS, SaleOrderPrecision.BODY_TABLECODE);

  /**
   * 
   * PreOrderPrecision �Ĺ�����
   */
  private SaleOrderPrecision() {
    //
  }

  /**
   * ��ʼ��
   * 
   * @return SaleOrderPrecision
   */
  public static SaleOrderPrecision getInstance() {
    return SaleOrderPrecision.precision;
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
    TotalValueScale totalscale = new TotalValueScaleProcessor(cardpanel);
    this.setBillPrecision(scaleprocess, totalscale);
  }

  /**
   * 
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
        listpanel.getHeadBillModel(), SaleOrderPrecision.NUMHEAD);
  }

  /**
   * 
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
    BillModelScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  /**
   * �����ȴ���
   * 
   * @param pk_group
   * 
   * @param panel
   */
  public void setSingleTableScale(String pk_group, BillListPanel panel) {
    this.setModelPrecision(pk_group, panel.getHeadBillModel());
    // �����б����ĺϼ���������
    this.setListHeadNumTrimZeroPrecison(panel);
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
  private void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(SaleOrderPrecision.GROUPMNYKEYS,
        PosEnum.body, null);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(SaleOrderPrecision.GLOBALMNYKEYS,
        PosEnum.body, null);
    // ������
    scaleprocess.setHslCtlInfo(SaleOrderPrecision.HSLKEYS, PosEnum.body, null);
    // ����
    scaleprocess.setPriceCtlInfo(SaleOrderPrecision.PRICEKEYS, PosEnum.body,
        null,SaleOrderHVO.CORIGCURRENCYID, PosEnum.head,null);
    
    scaleprocess.setPriceCtlInfo(SaleOrderPrecision.netpricekeys, PosEnum.body,
        null,SaleOrderBVO.CCURRENCYID, PosEnum.body,null);
    
    // ����
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.ASTNUMKEYS, PosEnum.body,
        null, SaleOrderBVO.CASTUNITID, PosEnum.body, null);
    // ������
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.NUMKEYS, PosEnum.body, null,
        SaleOrderBVO.CUNITID, PosEnum.body, null);
    // �ۼ�***������
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.GATHERNUMKEYS, PosEnum.body,
        null, SaleOrderBVO.CUNITID, PosEnum.body, null);
    // ��������
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.QTNUMKEYS, PosEnum.body,
        null, SaleOrderBVO.CQTUNITID, PosEnum.body, null);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.MNYKEYS, PosEnum.body, null,
        SaleOrderBVO.CCURRENCYID, PosEnum.body, null);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.ORIGMNYKEYS, PosEnum.body,
        null, SaleOrderHVO.CORIGCURRENCYID, PosEnum.head, null);

    // ԭ�ҽ���ͷ��
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.ORIGMNYKEYSHEAD,
        PosEnum.head, null, SaleOrderHVO.CORIGCURRENCYID, PosEnum.head, null);
    // ��ͷ�ۿ�
    scaleprocess.setSaleDiscountCtlInfo(SaleOrderPrecision.HEAD_DISRATEKEYS,
        PosEnum.head, null);
    // �����ۿ�
    scaleprocess.setSaleDiscountCtlInfo(SaleOrderPrecision.BODY_DISRATEKEYS,
        PosEnum.body, null);

    // ��ͷ�ϼ�����
    totalscale.setHeadTailKeys(SaleOrderPrecision.NUMHEAD);

    // �۱�����
    scaleprocess.setOrgExchangeCtlInfo(this.exchangeRate, this.localOrigCurr,
        this.orgCurr, this.settleOrg);
    // ȫ�ֱ�λ�һ���
    scaleprocess.setGlobalExchangeCtlInfo(this.globalExchgRate,
        this.localOrigCurr, this.orgCurr);
    // ���ű�λ�һ���
    scaleprocess.setGroupExchangeCtlInfo(this.groupExchgRate,
        this.localOrigCurr, this.orgCurr);

    // ˰��
    scaleprocess.setTaxRateCtlInfo(SaleOrderPrecision.TAXRATEKEY, PosEnum.body,
        null);
    // ����
    scaleprocess.setWeightCtlInfo(SaleOrderPrecision.WEIGHTKEY, PosEnum.body,
        null);
    // ���
    scaleprocess.setVolumnCtlInfo(SaleOrderPrecision.VOLUMEKEY, PosEnum.body,
        null);
    // ����
    scaleprocess.setUnitCtlInfo(SaleOrderPrecision.PIECEKEY, PosEnum.body,
        null, SaleOrderBVO.CMATERIALID, PosEnum.body, null);

    scaleprocess.process();
  }

  /**
   * 
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
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.ASTNUMKEYS,
        SaleOrderBVO.CASTUNITID);
    // ������
    scaleprocess
        .setNumCtlInfo(SaleOrderPrecision.NUMKEYS, SaleOrderBVO.CUNITID);
    // �ۼ�***������
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.GATHERNUMKEYS,
        SaleOrderBVO.CUNITID);

    // �����رչ�������
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.CLOSEMAGENUMKEYS,
        SaleOrderBVO.CUNITID);

    // ��������
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.QTNUMKEYS,
        SaleOrderBVO.CQTUNITID);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.MNYKEYS,
        SaleOrderBVO.CCURRENCYID);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.ORIGMNYKEYS,
        SaleOrderHVO.CORIGCURRENCYID);

    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(SaleOrderPrecision.GROUPMNYKEYS);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(SaleOrderPrecision.GLOBALMNYKEYS);
    // ������
    scaleprocess.setHslCtlInfo(SaleOrderPrecision.HSLKEYS);
    // ����
    scaleprocess.setPriceCtlInfo(SaleOrderPrecision.PRICEKEYS,SaleOrderHVO.CORIGCURRENCYID);
    
    // ���ҵ���
    scaleprocess.setPriceCtlInfo(SaleOrderPrecision.netpricekeys,SaleOrderBVO.CCURRENCYID);
    
    // ˰��
    scaleprocess.setTaxRateCtlInfo(SaleOrderPrecision.TAXRATEKEY);
    // �ۿ�
    scaleprocess.setSaleDiscountCtlInfo(SaleOrderPrecision.BODY_DISRATEKEYS);
    // ���
    scaleprocess.setVolumnCtlInfo(SaleOrderPrecision.VOLUMEKEY);
    // ����
    scaleprocess.setWeightCtlInfo(SaleOrderPrecision.WEIGHTKEY);
    // ����
    scaleprocess.setUnitCtlInfo(SaleOrderPrecision.PIECEKEY,
        SaleOrderBVO.CMATERIALID);
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.ORIGMNYKEYSHEAD,
        SaleOrderHVO.CORIGCURRENCYID);
    scaleprocess.process();
  }
}
