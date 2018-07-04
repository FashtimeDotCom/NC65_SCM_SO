package nc.ui.so.m38.billui.pub;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;

/**
 * Ԥ�������ȿ����࣬������Ƭ���б���ƽ����ľ��ȿ���
 * 
 * @since 6.0
 * @version 2011-7-11 ����10:12:33
 * @author fengjb
 */
public class PreOrderPrecision {

  public static final int BODY_POS = 1;

  public static final String BODY_TABLECODE = "body";

  public static final int HEAD_POS = 0;

  public static final String HEAD_TABLECODE = "head";

  // ҵ������
  private static final String[] ASTNUMKEYS = new String[] {
    PreOrderBVO.NASTNUM
  };

  // �����ۿۡ���Ʒ�ۿ�
  private static final String[] BDISCOUNTKEYS = new String[] {
    PreOrderBVO.NDISCOUNTRATE, PreOrderBVO.NITEMDISCOUNTRATE
  };

  // ȫ�ֱ��ҽ��
  private static final String[] GLOBALMNYKEYS = new String[] {
    PreOrderBVO.NGLOBALTAXMNY, PreOrderBVO.NGLOBALMNY
  };

  // ���ű��ҽ��
  private static final String[] GROUPMNYKEYS = new String[] {
    PreOrderBVO.NGROUPTAXMNY, PreOrderBVO.NGROUPMNY
  };

  /** ��ͷ */
  // �����ۿ�
  private static final String[] HDISCOUNTKEYS = new String[] {
    PreOrderHVO.NDISCOUNTRATE
  };

  // ������
  private static final String[] HSLKEYS = new String[] {
    PreOrderBVO.VCHANGERATE, PreOrderBVO.VQTUNITRATE
  };

  // ���ҽ��
  private static final String[] MNYKEYS = new String[] {
    PreOrderBVO.NTAX, PreOrderBVO.NMNY, PreOrderBVO.NTAXMNY,
    PreOrderBVO.NDISCOUNT, PreOrderBVO.NCALTAXMNY
  };

  // ԭ�ҽ���ͷ��
  private static final String[] NNYHEAD = new String[] {
    PreOrderHVO.NHEADSUMMNY
  };

  // �ϼ���������ͷ��
  private static final String[] NUMHEAD = new String[] {
    PreOrderHVO.NTOTALNUM
  };

  /** ���� */

  // ������ ���ɰ���������ncanarrnum��
  private static final String[] NUMKEYS = new String[] {
    PreOrderBVO.NNUM, PreOrderBVO.NARRNUM, "ncanarrnum"
  };

  // ԭ�ҽ�� norigmny
  private static final String[] ORIGMNYKEYS = new String[] {
    // TODO ���� 2012-2-17 ɾ��ԭ��˰���ֶ�
    // PreOrderBVO.NORIGTAX,

    PreOrderBVO.NORIGMNY, PreOrderBVO.NORIGTAXMNY, PreOrderBVO.NORIGDISCOUNT
  };

  private static PreOrderPrecision precision = new PreOrderPrecision();

  // ����
  private static final String[] PRICEKEYS = new String[] {
    PreOrderBVO.NORIGTAXPRICE, PreOrderBVO.NORIGPRICE,
    PreOrderBVO.NORIGTAXNETPRICE, PreOrderBVO.NORIGNETPRICE,
    PreOrderBVO.NQTORIGTAXPRICE, PreOrderBVO.NQTORIGPRICE,
    PreOrderBVO.NQTORIGTAXNETPRC, PreOrderBVO.NQTORIGNETPRICE,

    PreOrderBVO.NASKQTORIGNETPRICE, PreOrderBVO.NASKQTORIGPRICE,
    PreOrderBVO.NASKQTORIGTAXPRC, PreOrderBVO.NASKQTORIGTXNTPRC

  };

  private static final String[] netpricekeys = new String[] {
    PreOrderBVO.NTAXPRICE, PreOrderBVO.NPRICE, PreOrderBVO.NTAXNETPRICE,
    PreOrderBVO.NNETPRICE, PreOrderBVO.NQTTAXPRICE, PreOrderBVO.NQTPRICE,
    PreOrderBVO.NQTTAXNETPRICE, PreOrderBVO.NQTNETPRICE,
  };

  // ��������
  private static final String[] QTNUMKEYS = new String[] {
    PreOrderBVO.NQTUNITNUM
  };

  // ����˰��
  private static final String[] TAXRATEKEY = new String[] {
    PreOrderBVO.NTAXRATE
  };

  // �۱�����
  private FieldInfo exchangeRate = new FieldInfo(PreOrderBVO.NEXCHANGERATE,
      PreOrderPrecision.BODY_POS, PreOrderPrecision.BODY_TABLECODE);

  // ȫ���۱�����
  private FieldInfo globalExchgRate = new FieldInfo(
      PreOrderBVO.NGLOBALEXCHGRATE, PreOrderPrecision.BODY_POS,
      PreOrderPrecision.BODY_TABLECODE);

  // �����۱�����
  private FieldInfo groupExchgRate = new FieldInfo(PreOrderBVO.NGROUPEXCHGRATE,
      PreOrderPrecision.BODY_POS, PreOrderPrecision.BODY_TABLECODE);

  // ԭ��
  private FieldInfo localOrigCurr = new FieldInfo(PreOrderHVO.CORIGCURRENCYID,
      PreOrderPrecision.HEAD_POS, PreOrderPrecision.HEAD_TABLECODE);

  // ��֯����
  private FieldInfo orgCurr = new FieldInfo(PreOrderBVO.CCURRENCYID,
      PreOrderPrecision.BODY_POS, PreOrderPrecision.BODY_TABLECODE);

  // ������֯
  private FieldInfo settleOrg = new FieldInfo(PreOrderBVO.CSETTLEORGID,
      PreOrderPrecision.BODY_POS, PreOrderPrecision.BODY_TABLECODE);

  public static PreOrderPrecision getInstance() {
    return PreOrderPrecision.precision;
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
   * @author ��־ΰ
   * @time 2010-7-2 ����04:30:23
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
   * @author ��־ΰ
   * @time 2010-7-2 ����04:30:23
   */
  public void setListPrecision(String pk_group, BillListPanel listpanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listpanel);
    TotalValueScale totalscale = new TotalValueScaleProcessor(listpanel);
    this.setBillPrecision(scaleprocess, totalscale);
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
   * @author ��־ΰ
   * @time 2010-5-27 ����09:59:48
   */
  public void setModelPrecision(String pk_group, BillModel model) {
    TableScaleProcessor scaleprocess =
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

  private void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {

    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(PreOrderPrecision.GROUPMNYKEYS,
        PosEnum.body, null);
    // ���ҽ��
    scaleprocess.setOrgLocMnyCtlInfo(PreOrderPrecision.MNYKEYS, PosEnum.body,
        null);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(PreOrderPrecision.GLOBALMNYKEYS,
        PosEnum.body, null);
    // �����ʾ���
    scaleprocess.setHslCtlInfo(PreOrderPrecision.HSLKEYS, PosEnum.body, null);
    // �����ۿ�
    scaleprocess.setSaleDiscountCtlInfo(PreOrderPrecision.HDISCOUNTKEYS,
        PosEnum.head, null);
    // �����ۿۡ���Ʒ�ۿ�
    scaleprocess.setSaleDiscountCtlInfo(PreOrderPrecision.BDISCOUNTKEYS,
        PosEnum.body, null);
    // ����
    scaleprocess.setPriceCtlInfo(PreOrderPrecision.PRICEKEYS, PosEnum.body,
        null, PreOrderBVO.CORIGCURRENCYID, PosEnum.body, null);

    // ����
    scaleprocess.setPriceCtlInfo(PreOrderPrecision.netpricekeys, PosEnum.body,
        null, PreOrderBVO.CCURRENCYID, PosEnum.body, null);

    // ����
    scaleprocess.setNumCtlInfo(PreOrderPrecision.ASTNUMKEYS, PosEnum.body,
        null, PreOrderBVO.CASTUNITID, PosEnum.body, null);
    // ������
    scaleprocess.setNumCtlInfo(PreOrderPrecision.NUMKEYS, PosEnum.body, null,
        PreOrderBVO.CUNITID, PosEnum.body, null);
    // ��������
    scaleprocess.setNumCtlInfo(PreOrderPrecision.QTNUMKEYS, PosEnum.body, null,
        PreOrderBVO.CQTUNITID, PosEnum.body, null);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(PreOrderPrecision.ORIGMNYKEYS, PosEnum.body,
        null, PreOrderBVO.CORIGCURRENCYID, PosEnum.body, null);

    // ԭ�ҽ���ͷ��
    scaleprocess.setMnyCtlInfo(PreOrderPrecision.NNYHEAD, PosEnum.head, null,
        PreOrderHVO.CORIGCURRENCYID, PosEnum.head, null);

    // ��ͷ�ϼ�����
    if (totalscale != null) {
      totalscale.setHeadTailKeys(PreOrderPrecision.NUMHEAD);
    }
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
    scaleprocess.setTaxRateCtlInfo(PreOrderPrecision.TAXRATEKEY, PosEnum.body,
        null);

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
   * @author ��־ΰ
   * @time 2010-5-27 ����10:13:35
   */
  private void setTablePrecision(TableScaleProcessor scaleprocess) {

    // ҵ������
    scaleprocess.setNumCtlInfo(PreOrderPrecision.ASTNUMKEYS,
        PreOrderBVO.CASTUNITID);
    // ������
    scaleprocess.setNumCtlInfo(PreOrderPrecision.NUMKEYS, PreOrderBVO.CUNITID);
    // ��������
    scaleprocess.setNumCtlInfo(PreOrderPrecision.QTNUMKEYS,
        PreOrderBVO.CQTUNITID);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(PreOrderPrecision.MNYKEYS,
        PreOrderBVO.CCURRENCYID);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(PreOrderPrecision.ORIGMNYKEYS,
        PreOrderBVO.CORIGCURRENCYID);

    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(PreOrderPrecision.GROUPMNYKEYS);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(PreOrderPrecision.GLOBALMNYKEYS);
    // ������
    scaleprocess.setHslCtlInfo(PreOrderPrecision.HSLKEYS);
    // ����
    scaleprocess.setPriceCtlInfo(PreOrderPrecision.PRICEKEYS,
        PreOrderBVO.CORIGCURRENCYID);

    // ���ҵ���
    scaleprocess.setPriceCtlInfo(PreOrderPrecision.netpricekeys,
        PreOrderBVO.CCURRENCYID);

    // ˰��
    scaleprocess.setTaxRateCtlInfo(PreOrderPrecision.TAXRATEKEY);
    // �ۿ�
    scaleprocess.setSaleDiscountCtlInfo(PreOrderPrecision.BDISCOUNTKEYS);

    scaleprocess.process();
  }
}
