package nc.vo.so.m4331.scale;

import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * ���������ȴ�����
 * 
 * @since 6.3
 * @version 2013-6-14 ����04:55:39
 * @author tianft
 */
public class DeliveryScaleProcessor {

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
  
  /**
   * ���ҵ���
   */
  private static String[] netpricekeys=new String[]{
    DeliveryBVO.NTAXPRICE, DeliveryBVO.NPRICE, DeliveryBVO.NTAXNETPRICE,
    DeliveryBVO.NNETPRICE, DeliveryBVO.NQTTAXPRICE, DeliveryBVO.NQTPRICE,
    DeliveryBVO.NQTTAXNETPRICE, DeliveryBVO.NQTNETPRICE};

  // ��������
  private static String[] qtnumkeys = new String[] {
    DeliveryBVO.NQTUNITNUM
  };

  // ����˰��
  private static String[] taxratekey = new String[] {
    DeliveryBVO.NTAXRATE
  };

  /**
   * DeliverySacleProcessor �Ĺ�����
   */
  public DeliveryScaleProcessor() {
    //
  }

  protected void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale, PosEnum singlePos, boolean forScaleCheck) {
    PosEnum body = PosEnum.body;
    if (singlePos != null) {
      body = singlePos;
    }
    // ���ȼ�����β���
    if (!forScaleCheck) {
      // ������
      scaleprocess.setHslCtlInfo(DeliveryScaleProcessor.hslkeys, body, null);
    }
    // ԭ�ҵ���
    scaleprocess.setPriceCtlInfo(DeliveryScaleProcessor.pricekeys, body, null,DeliveryBVO.CORIGCURRENCYID,body,null);
    // ���ҵ���
    scaleprocess.setPriceCtlInfo(DeliveryScaleProcessor.netpricekeys, body, null,DeliveryBVO.CCURRENCYID,body,null);
    
    // ����
    scaleprocess.setNumCtlInfo(DeliveryScaleProcessor.astnumkeys, body, null,
        DeliveryBVO.CASTUNITID, body, null);
    // ������
    scaleprocess.setNumCtlInfo(DeliveryScaleProcessor.numkeys, body, null,
        DeliveryBVO.CUNITID, body, null);
    // �����ۿۡ���Ʒ�ۿ�
    scaleprocess.setSaleDiscountCtlInfo(DeliveryScaleProcessor.bdiscountkeys,
        body, null);
    // ��������
    scaleprocess.setNumCtlInfo(DeliveryScaleProcessor.qtnumkeys, body, null,
        DeliveryBVO.CQTUNITID, body, null);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(DeliveryScaleProcessor.mnykeys, body, null,
        DeliveryBVO.CCURRENCYID, body, null);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(DeliveryScaleProcessor.origmnykeys, body, null,
        DeliveryBVO.CORIGCURRENCYID, body, null);
    // ˰��
    scaleprocess.setTaxRateCtlInfo(DeliveryScaleProcessor.taxratekey, body,
        null);
    // �۱�����
    this.setOrgExchangeCtlInfo(scaleprocess, singlePos);
    // ȫ�ֱ�λ�һ���
    this.setGlobalExchangeCtlInfo(scaleprocess, singlePos);
    // ���ű�λ�һ���
    this.setGroupExchangeCtlInfo(scaleprocess, singlePos);
    scaleprocess.setWeightCtlInfo(DeliveryScaleProcessor.bodyweightKey, body,
        null);
    scaleprocess
        .setVolumnCtlInfo(DeliveryScaleProcessor.bodyvolKey, body, null);
    scaleprocess.setUnitCtlInfo(DeliveryScaleProcessor.bodypieceKey, body,
        null, DeliveryBVO.CMATERIALVID, body, null);
    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(DeliveryScaleProcessor.GROUPMNYKEYS,
        body, null);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(DeliveryScaleProcessor.GLOBALMNYKEYS,
        body, null);
    if (totalscale != null) {
      // ��ͷ�ϼ�����
      totalscale.setHeadTailKeys(DeliveryScaleProcessor.NUMHEAD);
    }
    scaleprocess.process();

  }

  /**
   * 
   * 
   * @param scaleprocess
   */
  public void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    this.setBillPrecision(scaleprocess, totalscale, null, false);
  }

  /**
   * ���ھ��ȼ��
   * 
   * @param scale
   * @param totalScale
   */
  public void checkBillPrecision(DeliveryVO[] vos) {
    String pk_group = AppContext.getInstance().getPkGroup();
    BillVOScaleCheckProcessor scaleprocess =
        new BillVOScaleCheckProcessor(pk_group, vos);
    this.setBillPrecision(scaleprocess, null, null, true);
  }

  private void setOrgExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // �۱�����
    FieldInfo exchangeRate =
        new FieldInfo(DeliveryBVO.NEXCHANGERATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // ԭ��
    FieldInfo localOrigCurr =
        new FieldInfo(DeliveryBVO.CORIGCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // ��֯����
    FieldInfo orgCurr =
        new FieldInfo(DeliveryBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // ������֯
    FieldInfo settleOrg =
        new FieldInfo(DeliveryBVO.CSETTLEORGID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);

    scaleprocess.setOrgExchangeCtlInfo(exchangeRate, localOrigCurr, orgCurr,
        settleOrg);
  }

  private void setGroupExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // ԭ��
    FieldInfo localOrigCurr =
        new FieldInfo(DeliveryBVO.CORIGCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // ��֯����
    FieldInfo orgCurr =
        new FieldInfo(DeliveryBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // �����۱�����
    FieldInfo groupExchgRate =
        new FieldInfo(DeliveryBVO.NGROUPEXCHGRATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    scaleprocess
        .setGroupExchangeCtlInfo(groupExchgRate, localOrigCurr, orgCurr);
  }

  private void setGlobalExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // ԭ��
    FieldInfo localOrigCurr =
        new FieldInfo(DeliveryBVO.CORIGCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // ��֯����
    FieldInfo orgCurr =
        new FieldInfo(DeliveryBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // ȫ���۱�����
    FieldInfo globalExchgRate =
        new FieldInfo(DeliveryBVO.NGLOBALEXCHGRATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    scaleprocess.setGlobalExchangeCtlInfo(globalExchgRate, localOrigCurr,
        orgCurr);
  }

}
