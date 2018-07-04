package nc.vo.so.m30.scale;

import org.apache.commons.lang.ArrayUtils;

import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ���۶������ȴ����ࣨǰ̨�Ӵ˼̳У�
 * 
 * @since 6.3
 * @version 2013-6-14 ����10:38:43
 * @author tianft
 */
public class SaleOrderScaleProcessor {

  /** ���� */
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
    SaleOrderBVO.NDISCOUNT, SaleOrderBVO.NCALTAXMNY
  };

  // ������
  private static final String[] NUMKEYS = new String[] {
    SaleOrderBVO.NNUM, SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM,
    SaleOrderBVO.NTOTALESTARNUM, SaleOrderBVO.NTOTALINVOICENUM,
    SaleOrderBVO.NTOTALNOTOUTNUM, SaleOrderBVO.NTOTALOUTNUM,
    SaleOrderBVO.NTOTALRETURNNUM, SaleOrderBVO.NTOTALRUSHNUM,
    SaleOrderBVO.NTOTALSENDNUM, SaleOrderBVO.NTOTALSIGNNUM,
    SaleOrderBVO.NTOTALTRADENUM, SaleOrderBVO.NREQRSNUM
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
    SaleOrderBVO.NTOTALTRADENUM,
  };

  // ԭ����������ͷ��
  private static final String[] NUMHEAD = new String[] {
    SaleOrderHVO.NTOTALNUM, SaleOrderHVO.NTOTALVOLUME,
    SaleOrderHVO.NTOTALWEIGHT,
  };

  // ԭ�ҽ��
  private static final String[] ORIGMNYKEYS = new String[] {
    SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NBFORIGSUBMNY,
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALESTARMNY,
    SaleOrderBVO.NTOTALPAYMNY,SaleOrderBVO.NORIGSUBMNY
  };

  // ԭ�ҽ���ͷ��
  private static final String[] ORIGMNYKEYSHEAD = new String[] {
    SaleOrderHVO.NTOTALMNY, SaleOrderHVO.NTOTALORIGMNY,
    SaleOrderHVO.NTOTALORIGSUBMNY, SaleOrderHVO.NPRECEIVEMNY,
    SaleOrderHVO.NPRECEIVEQUOTA, SaleOrderHVO.NRECEIVEDMNY,
    SaleOrderHVO.NTHISRECEIVEMNY
  };

  // ����
  private static final String[] PIECEKEY = new String[] {
    SaleOrderBVO.NPIECE
  };

  // ����
  private static final String[] PRICEKEYS = new String[] {
    SaleOrderBVO.NORIGTAXPRICE, SaleOrderBVO.NORIGPRICE,
    SaleOrderBVO.NORIGTAXNETPRICE, SaleOrderBVO.NORIGNETPRICE,
    SaleOrderBVO.NQTORIGTAXPRICE, SaleOrderBVO.NQTORIGPRICE,
    SaleOrderBVO.NQTORIGTAXNETPRC, SaleOrderBVO.NQTORIGNETPRICE,

  };

  private static final String[] netpricekeys = new String[] {
    SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXNETPRICE,
    SaleOrderBVO.NNETPRICE, SaleOrderBVO.NQTTAXPRICE, SaleOrderBVO.NQTPRICE,
    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
    SaleOrderBVO.NASKQTORIGNETPRICE, SaleOrderBVO.NASKQTORIGPRICE,
    SaleOrderBVO.NASKQTORIGTAXPRC, SaleOrderBVO.NASKQTORIGTXNTPRC
  };

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

  public void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    this.setBillPrecision(scaleprocess, totalscale, null, false);
  }

  /**
   * ���ȼ��
   * 
   * @param vos
   */
  public void checkBillPrecision(SaleOrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_group = vos[0].getParentVO().getPk_group();
    BillVOScaleCheckProcessor scaleChecker =
        new BillVOScaleCheckProcessor(pk_group, vos);
    this.setBillPrecision(scaleChecker, null, null, true);
  }

  protected void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale, PosEnum singlePos, boolean forScaleCheck) {
    PosEnum head = PosEnum.head;
    PosEnum body = PosEnum.body;
    if (singlePos != null) {
      head = singlePos;
      body = singlePos;
    }
    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(SaleOrderScaleProcessor.GROUPMNYKEYS,
        body, null);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(SaleOrderScaleProcessor.GLOBALMNYKEYS,
        body, null);
    // ���ȼ�鲻��Ҫ�����
    if (!forScaleCheck) {
      // ������
      scaleprocess.setHslCtlInfo(SaleOrderScaleProcessor.HSLKEYS, body, null);
      // ����
      scaleprocess.setWeightCtlInfo(SaleOrderScaleProcessor.WEIGHTKEY, body,
          null);
      // ���
      scaleprocess.setVolumnCtlInfo(SaleOrderScaleProcessor.VOLUMEKEY, body,
          null);
      // ����
      scaleprocess.setUnitCtlInfo(SaleOrderScaleProcessor.PIECEKEY, body, null,
          SaleOrderBVO.CMATERIALID, body, null);
    }
    // ����
    scaleprocess.setPriceCtlInfo(SaleOrderScaleProcessor.PRICEKEYS, body, null,
        SaleOrderHVO.CORIGCURRENCYID, head, null);

    // ���ҵ���
    scaleprocess.setPriceCtlInfo(SaleOrderScaleProcessor.netpricekeys, body,
        null, SaleOrderBVO.CCURRENCYID, body, null);

    // ����
    scaleprocess.setNumCtlInfo(SaleOrderScaleProcessor.ASTNUMKEYS, body, null,
        SaleOrderBVO.CASTUNITID, body, null);
    // ������
    scaleprocess.setNumCtlInfo(SaleOrderScaleProcessor.NUMKEYS, body, null,
        SaleOrderBVO.CUNITID, body, null);
    // �ۼ�***������
    scaleprocess.setNumCtlInfo(SaleOrderScaleProcessor.GATHERNUMKEYS, body,
        null, SaleOrderBVO.CUNITID, body, null);
    // ��������
    scaleprocess.setNumCtlInfo(SaleOrderScaleProcessor.QTNUMKEYS, body, null,
        SaleOrderBVO.CQTUNITID, body, null);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(SaleOrderScaleProcessor.MNYKEYS, body, null,
        SaleOrderBVO.CCURRENCYID, body, null);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(SaleOrderScaleProcessor.ORIGMNYKEYS, body, null,
        SaleOrderHVO.CORIGCURRENCYID, head, null);

    // ԭ�ҽ���ͷ��
    scaleprocess.setMnyCtlInfo(SaleOrderScaleProcessor.ORIGMNYKEYSHEAD, head,
        null, SaleOrderHVO.CORIGCURRENCYID, head, null);
    // ��ͷ�ۿ�
    scaleprocess.setSaleDiscountCtlInfo(
        SaleOrderScaleProcessor.HEAD_DISRATEKEYS, head, null);
    // �����ۿ�
    scaleprocess.setSaleDiscountCtlInfo(
        SaleOrderScaleProcessor.BODY_DISRATEKEYS, body, null);
    if (totalscale != null) {
      // ��ͷ�ϼ�����
      totalscale.setHeadTailKeys(SaleOrderScaleProcessor.NUMHEAD);
    }

    // �۱�����
    this.setOrgExchangeCtlInfo(scaleprocess, singlePos);
    // ȫ�ֱ�λ�һ���
    this.setGlobalExchangeCtlInfo(scaleprocess, singlePos);
    // ���ű�λ�һ���
    this.setGroupExchangeCtlInfo(scaleprocess, singlePos);

    // ˰��
    scaleprocess.setTaxRateCtlInfo(SaleOrderScaleProcessor.TAXRATEKEY, body,
        null);

    scaleprocess.process();
  }

  private void setGroupExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // ԭ��
    FieldInfo localOrigCurr =
        new FieldInfo(SaleOrderHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // ��֯����
    FieldInfo orgCurr =
        new FieldInfo(SaleOrderBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // �����۱�����
    FieldInfo groupExchgRate =
        new FieldInfo(SaleOrderBVO.NGROUPEXCHGRATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);

    scaleprocess
        .setGroupExchangeCtlInfo(groupExchgRate, localOrigCurr, orgCurr);
  }

  private void setGlobalExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // ȫ���۱�����
    FieldInfo globalExchgRate =
        new FieldInfo(SaleOrderBVO.NGLOBALEXCHGRATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // ԭ��
    FieldInfo localOrigCurr =
        new FieldInfo(SaleOrderHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // ��֯����
    FieldInfo orgCurr =
        new FieldInfo(SaleOrderBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);

    scaleprocess.setGlobalExchangeCtlInfo(globalExchgRate, localOrigCurr,
        orgCurr);
  }

  private void setOrgExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // ԭ��
    FieldInfo localOrigCurr =
        new FieldInfo(SaleOrderHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // ��֯����
    FieldInfo orgCurr =
        new FieldInfo(SaleOrderBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // ������֯
    FieldInfo settleOrg =
        new FieldInfo(SaleOrderBVO.CSETTLEORGID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    /** ���ʾ��� */
    // �۱�����
    FieldInfo exchangeRate =
        new FieldInfo(SaleOrderBVO.NEXCHANGERATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    scaleprocess.setOrgExchangeCtlInfo(exchangeRate, localOrigCurr, orgCurr,
        settleOrg);
  }

}
