package nc.ui.so.m32.billui.pub;

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
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * ���۷�Ʊ���ȴ�����
 * 
 * @since 6.3
 * @version 2012-12-21 ����11:03:37
 * @author yaogj
 */
public class SaleInvoicePrecision {

  /**
   * ��ͷҳǩ����
   */
  public static final String BODY_TABLECODE = "saleinvoice_b";

  /**
   * ��ͷ
   */
  public static final int HEAD_POS = 0;

  /**
   * ��ͷҳǩ����
   */
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

  private static SaleInvoicePrecision instance = new SaleInvoicePrecision();

  // ���ҽ��
  private static final String[] MNYKEY = new String[] {
    SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NMNY, SaleInvoiceBVO.NTAXMNY,
    SaleInvoiceBVO.NDISCOUNT, SaleInvoiceBVO.NCALTAXMNY
  };

  // ������\�ۼ�Ӧ��δ��������\�ۼƳɱ���������\�ۼ�ȷ��Ӧ������\�ۼƳ�������
  private static final String[] NUMKEY = new String[] {
    SaleInvoiceBVO.NNUM, SaleInvoiceBVO.NSHOULDOUTNUM,
    SaleInvoiceBVO.NTOTALCOSTNUM, SaleInvoiceBVO.NTOTALINCOMENUM,
    SaleInvoiceBVO.NTOTALOUTNUM
  };

  // ԭ�ҽ��
  private static final String[] ORIGMNYKEY = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NORIGDISCOUNT, SaleInvoiceBVO.NORIGSUBMNY,
    SaleInvoiceBVO.NTOTALINCOMEMNY, SaleInvoiceBVO.NTOTALPAYMNY,
    "nbforigsubmny"
  };

  // ��ͷԭ�ҽ��
  private static final String[] ORIGMNYKEYHEAD = new String[] {
    SaleInvoiceHVO.NTOTALORIGMNY, SaleInvoiceHVO.NTOTALORIGSUBMNY,"ntotalmny"
  };

  // ����
  private static final String[] PRICEKEY = new String[] {
    SaleInvoiceBVO.NORIGTAXPRICE, SaleInvoiceBVO.NORIGPRICE,
    SaleInvoiceBVO.NORIGTAXNETPRICE, SaleInvoiceBVO.NORIGNETPRICE,
    SaleInvoiceBVO.NQTORIGTAXPRICE, SaleInvoiceBVO.NQTORIGPRICE,
    SaleInvoiceBVO.NQTORIGTAXNETPRC, SaleInvoiceBVO.NQTORIGNETPRICE,
  };

  private static final String[] NETPRICEKEYS=new String[]{
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

  // �۱�����
  private FieldInfo exchangeRate = new FieldInfo(SaleInvoiceHVO.NEXCHANGERATE,
      SaleInvoicePrecision.HEAD_POS, SaleInvoicePrecision.HEAD_TABLECODE);

  // ȫ���۱�����
  private FieldInfo globalExchgRate = new FieldInfo(
      SaleInvoiceHVO.NGLOBALEXCHGRATE, SaleInvoicePrecision.HEAD_POS,
      SaleInvoicePrecision.HEAD_TABLECODE);

  // �����۱�����
  private FieldInfo groupExchgRate = new FieldInfo(
      SaleInvoiceHVO.NGROUPEXCHGRATE, SaleInvoicePrecision.HEAD_POS,
      SaleInvoicePrecision.HEAD_TABLECODE);

  // ԭ��
  private FieldInfo localOrigCurr = new FieldInfo(
      SaleInvoiceHVO.CORIGCURRENCYID, SaleInvoicePrecision.HEAD_POS, null);

  // ��֯����
  private FieldInfo orgCurr = new FieldInfo(SaleInvoiceHVO.CCURRENCYID,
      SaleInvoicePrecision.HEAD_POS, null);

  // ������֯
  private FieldInfo settleOrg = new FieldInfo(SaleInvoiceHVO.PK_ORG,
      SaleInvoicePrecision.HEAD_POS, null);

  /**
   * SaleInvoicePrecision �Ĺ�����
   */
  private SaleInvoicePrecision() {
    // ȱʡ���췽��
  }

  /**
   * 
   * @return ��ʼ����
   */
  public static SaleInvoicePrecision getInstance() {
    return SaleInvoicePrecision.instance;
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
   * @author ��ӱ�
   * @time 2010-4-1 ����02:17:10
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
        listpanel.getHeadBillModel(), new String[] {
          SaleInvoiceHVO.NTOTALASTNUM
        });
  }

  /**
   * ��������������������ƽ����ľ��ȡ�
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
   * 
   * @param scale �����ȴ�����
   */
  public void setScaleForSingleTable(BillScaleProcessor scale) {
    if (scale != null) {
      this.setBillPrecision(scale);
    }
  }

  /**
   * �����ȴ���
   * 
   * @param pk_group ����
   * @param panel ����
   */
  public void setSingleTableScale(String pk_group, BillListPanel panel) {
    this.setModelPrecision(pk_group, panel.getHeadBillModel());
    // this.setScaleForSingleTable(new ListPaneScaleProcessor(pk_group, panel));
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
   * @time 2010-8-17 ����08:19:49
   */
  private void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(SaleInvoicePrecision.GROUPMNYKEY,
        PosEnum.body, null);
    // ���ҽ��
    scaleprocess.setOrgLocMnyCtlInfo(SaleInvoicePrecision.MNYKEY, PosEnum.body,
        null);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(SaleInvoicePrecision.GLOBALMNYKEY,
        PosEnum.body, null);
    // �����ʾ���
    scaleprocess.setHslCtlInfo(SaleInvoicePrecision.HSLKEY, PosEnum.body, null);
    // ����
    scaleprocess.setPriceCtlInfo(SaleInvoicePrecision.PRICEKEY, PosEnum.body,
        null,SaleInvoiceHVO.CORIGCURRENCYID,PosEnum.head,null);
    
    // ����
    scaleprocess.setPriceCtlInfo(SaleInvoicePrecision.NETPRICEKEYS, PosEnum.body,
        null,SaleInvoiceHVO.CCURRENCYID,PosEnum.head,null);
    
    // ����
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.ASTNUMKEY, PosEnum.body,
        null, SaleInvoiceBVO.CASTUNITID, PosEnum.body, null);
    // ������
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.NUMKEY, PosEnum.body, null,
        SaleInvoiceBVO.CUNITID, PosEnum.body, null);
    // ��������
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.QTNUMKEY, PosEnum.body,
        null, SaleInvoiceBVO.CQTUNITID, PosEnum.body, null);
    // ��ͷ�ۿ�
    scaleprocess.setSaleDiscountCtlInfo(SaleInvoicePrecision.HEAD_DISRATEKEYS,
        PosEnum.head, null);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(SaleInvoicePrecision.ORIGMNYKEY, PosEnum.body,
        null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);

    // ԭ�ҽ���ͷ��
    scaleprocess.setMnyCtlInfo(SaleInvoicePrecision.ORIGMNYKEYHEAD,
        PosEnum.head, null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);
    // ����
    scaleprocess.setSaleDiscountCtlInfo(SaleInvoicePrecision.DISCOUNTRATEKEY,
        PosEnum.body, null);

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
    scaleprocess.setTaxRateCtlInfo(SaleInvoicePrecision.TAXRATEKEY,
        PosEnum.body, null);
    // ��ͷ�����ϼ�
    if (totalscale != null) {
      totalscale.setHeadTailKeys(new String[] {
        SaleInvoiceHVO.NTOTALASTNUM
      });
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
  private void setTablePrecision(BillModelScaleProcessor scaleprocess) {
    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(SaleInvoicePrecision.GROUPMNYKEY);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(SaleInvoicePrecision.MNYKEY,
        SaleInvoiceHVO.CCURRENCYID);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(SaleInvoicePrecision.GLOBALMNYKEY);
    // ������
    scaleprocess.setHslCtlInfo(SaleInvoicePrecision.HSLKEY);
    // ����
    scaleprocess.setPriceCtlInfo(SaleInvoicePrecision.PRICEKEY,SaleInvoiceHVO.CORIGCURRENCYID);
    
    // ���ҵ���
    scaleprocess.setPriceCtlInfo(SaleInvoicePrecision.NETPRICEKEYS,SaleInvoiceHVO.CCURRENCYID);
    
    
    // ����
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.ASTNUMKEY,
        SaleInvoiceBVO.CASTUNITID);
    // ������
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.NUMKEY,
        SaleInvoiceBVO.CUNITID);
    // ��������
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.QTNUMKEY,
        SaleInvoiceBVO.CQTUNITID);

    // ��ͷ�ۿ�
    scaleprocess.setSaleDiscountCtlInfo(SaleInvoicePrecision.HEAD_DISRATEKEYS);

    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(SaleInvoicePrecision.ORIGMNYKEY,
        SaleInvoiceHVO.CORIGCURRENCYID);

    // ԭ�ҽ���ͷ��
    scaleprocess.setMnyCtlInfo(SaleInvoicePrecision.ORIGMNYKEYHEAD,
        SaleInvoiceHVO.CORIGCURRENCYID);
    // �ۿ���
    scaleprocess.setSaleDiscountCtlInfo(SaleInvoicePrecision.DISCOUNTRATEKEY);

    // // �۱�����
    // scaleprocess.setOrgExchangeCtlInfo(this.exchangeRate, this.localOrigCurr,
    // this.orgCurr, this.settleOrg);
    // // ȫ�ֱ�λ�һ���
    // scaleprocess.setGlobalExchangeCtlInfo(this.globalExchgRate,
    // this.localOrigCurr, this.orgCurr);
    // // ���ű�λ�һ���
    // scaleprocess.setGroupExchangeCtlInfo(this.groupExchgRate,
    // this.localOrigCurr, this.orgCurr);

    // ˰��
    scaleprocess.setTaxRateCtlInfo(SaleInvoicePrecision.TAXRATEKEY);

    scaleprocess.process();
  }
}
