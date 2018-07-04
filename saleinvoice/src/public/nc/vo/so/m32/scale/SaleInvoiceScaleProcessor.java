package nc.vo.so.m32.scale;

import org.apache.commons.lang.ArrayUtils;

import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * ���۷�Ʊ���ȴ�����
 * 
 * @since 6.3
 * @version 2013-6-18 ����07:35:03
 * @author tianft
 */
public class SaleInvoiceScaleProcessor {

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
    SaleInvoiceBVO.NTOTALINCOMEMNY, SaleInvoiceBVO.NTOTALPAYMNY
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
  
  private static final String[] netpricekeys=new String[]{
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

  /**
   * SaleInvoiceScaleProcessor �Ĺ�����
   */
  public SaleInvoiceScaleProcessor() {
    // ȱʡ���췽��
  }

  public void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    this.setBillPrecision(scaleprocess, totalscale, null, false);
  }

  /**
   * ���ȼ��
   * 
   * @param vos
   */
  public void checkBillPrecision(SaleInvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_group = vos[0].getParentVO().getPk_group();
    BillVOScaleCheckProcessor scaleChecker =
        new BillVOScaleCheckProcessor(pk_group, vos);
    this.setBillPrecision(scaleChecker, null, null, true);
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
  protected void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale, PosEnum singlePos, boolean forScaleCheck) {
    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(SaleInvoiceScaleProcessor.GROUPMNYKEY,
        PosEnum.body, null);
    // ���ҽ��
    scaleprocess.setOrgLocMnyCtlInfo(SaleInvoiceScaleProcessor.MNYKEY,
        PosEnum.body, null);
    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(SaleInvoiceScaleProcessor.GLOBALMNYKEY,
        PosEnum.body, null);
    if (!forScaleCheck) {
      // �����ʾ���
      scaleprocess.setHslCtlInfo(SaleInvoiceScaleProcessor.HSLKEY,
          PosEnum.body, null);
    }
    // ����
    scaleprocess.setPriceCtlInfo(SaleInvoiceScaleProcessor.PRICEKEY,
        PosEnum.body, null,SaleInvoiceHVO.CORIGCURRENCYID,PosEnum.head,null);
    
    // ���ҵ���
    scaleprocess.setPriceCtlInfo(SaleInvoiceScaleProcessor.netpricekeys,
        PosEnum.body, null,SaleInvoiceHVO.CCURRENCYID,PosEnum.head,null);
    
    
    // ����
    scaleprocess.setNumCtlInfo(SaleInvoiceScaleProcessor.ASTNUMKEY,
        PosEnum.body, null, SaleInvoiceBVO.CASTUNITID, PosEnum.body, null);
    // ������
    scaleprocess.setNumCtlInfo(SaleInvoiceScaleProcessor.NUMKEY, PosEnum.body,
        null, SaleInvoiceBVO.CUNITID, PosEnum.body, null);
    // ��������
    scaleprocess.setNumCtlInfo(SaleInvoiceScaleProcessor.QTNUMKEY,
        PosEnum.body, null, SaleInvoiceBVO.CQTUNITID, PosEnum.body, null);
    // ��ͷ�ۿ�
    scaleprocess.setSaleDiscountCtlInfo(
        SaleInvoiceScaleProcessor.HEAD_DISRATEKEYS, PosEnum.head, null);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(SaleInvoiceScaleProcessor.ORIGMNYKEY,
        PosEnum.body, null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);

    // ԭ�ҽ���ͷ��
    scaleprocess.setMnyCtlInfo(SaleInvoiceScaleProcessor.ORIGMNYKEYHEAD,
        PosEnum.head, null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);
    // ����
    scaleprocess.setSaleDiscountCtlInfo(
        SaleInvoiceScaleProcessor.DISCOUNTRATEKEY, PosEnum.body, null);

    // �۱�����
    this.setOrgExchangeCtlInfo(scaleprocess, singlePos);
    // ȫ�ֱ�λ�һ���
    this.setGlobalExchangeCtlInfo(scaleprocess, singlePos);
    // ���ű�λ�һ���
    this.setGroupExchangeCtlInfo(scaleprocess, singlePos);

    // ˰��
    scaleprocess.setTaxRateCtlInfo(SaleInvoiceScaleProcessor.TAXRATEKEY,
        PosEnum.body, null);
    // ��ͷ�����ϼ�
    if (totalscale != null) {
      totalscale.setHeadTailKeys(new String[] {
        SaleInvoiceHVO.NTOTALASTNUM
      });
    }
    scaleprocess.process();

  }

  private void setGroupExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // �����۱�����
    FieldInfo groupExchgRate =
        new FieldInfo(SaleInvoiceHVO.NGROUPEXCHGRATE,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // ԭ��
    FieldInfo localOrigCurr =
        new FieldInfo(SaleInvoiceHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // ��֯����
    FieldInfo orgCurr =
        new FieldInfo(SaleInvoiceHVO.CCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    scaleprocess
        .setGroupExchangeCtlInfo(groupExchgRate, localOrigCurr, orgCurr);
  }

  private void setGlobalExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // ȫ���۱�����
    FieldInfo globalExchgRate =
        new FieldInfo(SaleInvoiceHVO.NGLOBALEXCHGRATE,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // ԭ��
    FieldInfo localOrigCurr =
        new FieldInfo(SaleInvoiceHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // ��֯����
    FieldInfo orgCurr =
        new FieldInfo(SaleInvoiceHVO.CCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);

    scaleprocess.setGlobalExchangeCtlInfo(globalExchgRate, localOrigCurr,
        orgCurr);
  }

  private void setOrgExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {

    // �۱�����
    FieldInfo exchangeRate =
        new FieldInfo(SaleInvoiceHVO.NEXCHANGERATE,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // ԭ��
    FieldInfo localOrigCurr =
        new FieldInfo(SaleInvoiceHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // ��֯����
    FieldInfo orgCurr =
        new FieldInfo(SaleInvoiceHVO.CCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);

    // ������֯
    FieldInfo settleOrg =
        new FieldInfo(SaleInvoiceHVO.PK_ORG,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);

    scaleprocess.setOrgExchangeCtlInfo(exchangeRate, localOrigCurr, orgCurr,
        settleOrg);
  }

}
