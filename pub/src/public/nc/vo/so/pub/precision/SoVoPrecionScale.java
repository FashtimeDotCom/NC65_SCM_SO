package nc.vo.so.pub.precision;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;
import nc.vo.so.pub.SOItemKey;

/**
 * ��ӡvo���ȴ���
 * 
 * @since 6.0
 * @version 2010-12-31 ����10:16:36
 * @author ף����
 */
public class SoVoPrecionScale {

  private BillScaleProcessor scale;

  private TotalValueScale totalScale;

  /**
   * ���ش���vo����ľ���
   * 
   * @return
   */
  public SoVoPrecionScale(String pk_group, AggregatedValueObject[] bills) {
    this.scale = new BillVOScaleProcessor(pk_group, bills);
    this.totalScale = new TotalValueVOScaleProcessor(bills);
  }

  /**
   * ����vo����
   */
  public void setScale() {
    this.setPriceScale();
    this.setNumScale();
    this.setLocalScale();
    this.setMoneyScale();
    this.setRateScale();
    this.setDiscountScale();
    // ���м���
    this.scale.process();
    this.setHeadDataScale();
  }

  protected BillScaleProcessor getScale() {
    return this.scale;
  }

  /**
   * ���ر�ͷ����
   * 
   * @return
   */
  protected TotalValueScale getTotalScale() {
    return this.totalScale;
  }

  /**
   * �����ۿ۾���
   * 
   * @return
   */
  protected void setDiscountScale() {
    /** ��ͷ */
    // �����ۿ�
    String[] headDiscountKeys = new String[] {
      SOItemKey.NDISCOUNTRATE,"nhvoicedisrate"
    };

    // �����ۿۡ���Ʒ�ۿ�
    String[] bodyDiscountKeys = new String[] {
      SOItemKey.NDISCOUNTRATE, SOItemKey.NITEMDISCOUNTRATE
    };

    // �����ۿ�
    this.scale.setSaleDiscountCtlInfo(headDiscountKeys, PosEnum.head, null);
    // �����ۿۡ���Ʒ�ۿ�
    this.scale.setSaleDiscountCtlInfo(bodyDiscountKeys, PosEnum.body, null);
  }

  protected void setHeadDataScale() {
    // ��ͷ�ϼ�
    String[] totalKeys = new String[] {
      SOItemKey.NTOTALNUM/*, SOItemKey.NTOTALORIGMNY, SOItemKey.NTOTALORIGSUBMNY*/
      ,"ntotalastnum","ntotalpiece","ntotalweight","ntotalvolume"
    };
    // ���������ò������ã����ñ�ͷ���ȴ����Ѿ�ע��
    this.totalScale.setHeadTailKeys(totalKeys);
  }

  /**
   * ���ü��ű�λ�Һ���֯��λ�Ҿ���
   */
  protected void setLocalScale() {
    // ȫ�ֱ�λ�ҽ��
    String[] globalmnykeys = new String[] {
      SOItemKey.NGLOBALMNY, SOItemKey.NGLOBALTAXMNY
    };
    // ���ű�λ�ҽ��
    String[] groupmnykeys = new String[] {
      SOItemKey.NGROUPMNY, SOItemKey.NGROUPTAXMNY
    };
    // ȫ�ֱ�λ�ҽ���
    this.scale.setGlobalLocMnyCtlInfo(globalmnykeys, PosEnum.body, null);
    // ���ű�λ�ҽ���
    this.scale.setGroupLocMnyCtlInfo(groupmnykeys, PosEnum.body, null);
  }

  /**
   * ���ý���
   */
  protected void setMoneyScale() {
    // ԭ�ҽ��
    String[] orgmnykeys =
        new String[] {
          SOItemKey.NORIGMNY, SOItemKey.NORIGTAX, SOItemKey.NORIGTAXMNY,
          SOItemKey.NORIGDISCOUNT, SOItemKey.NORIGSUBMNY,
          // ���۷��õ���������������ֽ���������Ӧ�ս�������Ʊ��ֽ�� ��������
          "norigarsubmny", "nordersubmny", "nredarsubmny", "ninvoicesubmny",
          "nremainmny"
        };

    // ��ӡģ���ͷԭ�ҽ��
    String[] headOrgMnyKeys = new String[] {
      SOItemKey.NTOTALORIGMNY, SOItemKey.NTOTALORIGSUBMNY,
      "ntotalmny","npreceivequota","nreceivedmny","npreceivemny"
    };

    // ����ԭ�ҽ��
    String[] bodyorgmnykeys =
        new String[] {
          SOItemKey.NORIGTAXMNY, SOItemKey.NORIGSUBMNY, SOItemKey.NCALTAXMNY,
          SOItemKey.NTAX
        };
    // ���ҽ��
    String[] mnykeys = new String[] {
      SOItemKey.NMNY, SOItemKey.NTAXMNY, SOItemKey.NTAX, SOItemKey.NDISCOUNT
    };
    // ���ҽ���
    this.scale.setMnyCtlInfo(mnykeys, PosEnum.body, null,
        SOItemKey.CCURRENCYID, PosEnum.body, null);
    // ԭ�ҽ���
    this.scale.setMnyCtlInfo(orgmnykeys, PosEnum.body, null,
        SOItemKey.CORIGCURRENCYID, PosEnum.head, null);

    // ԭ�ҽ��� --��ӡģ���ͷԭ�ҽ�� ---yixl 2013-03-07���
    this.scale.setMnyCtlInfo(headOrgMnyKeys, PosEnum.head, null,
        SOItemKey.CORIGCURRENCYID, PosEnum.head, null);
    // ����ԭ�ҽ���
    this.scale.setMnyCtlInfo(bodyorgmnykeys, PosEnum.body, null,
        SOItemKey.CORIGCURRENCYID, PosEnum.head, null);
  }

  /**
   * ������������
   */
  protected void setNumScale() {

    // ҵ��λ����
    String[] assistNumkeys = new String[] {
      SOItemKey.NASTNUM
    };
    // ������
    String[] numkeys = new String[] {
      SOItemKey.NNUM,"noutnotauditnum","nnotarnum","nlossnotauditnum","nnotcostnum",
      "ninvunfinisednum","ninvoiceauditnum","noutunfinisednum","noutauditnum","nsendunfinisednum",
      "nsendunfinisednum","nsendauditnum"
    };
    String[] qtnumkeys = new String[] {
      SOItemKey.NQTUNITNUM
    };
    // ����λ��������
    this.scale.setNumCtlInfo(numkeys, PosEnum.body, null, SOItemKey.CUNITID,
        PosEnum.body, null);
    // ҵ��λ��������
    this.scale.setNumCtlInfo(assistNumkeys, PosEnum.body, null,
        SOItemKey.CASTUNITID, PosEnum.body, null);
    // ���۵�λ����
    this.scale.setNumCtlInfo(qtnumkeys, PosEnum.body, null,
        SOItemKey.CQTUNITID, PosEnum.body, null);
  }

  /**
   * ���ü۸񾫶�
   */
  protected void setPriceScale() {
    // ����
    String[] pricekeys =
        new String[] {
          SOItemKey.NORIGNETPRICE, SOItemKey.NORIGTAXNETPRICE,
          SOItemKey.NORIGPRICE, SOItemKey.NORIGTAXPRICE,
          SOItemKey.NQTORIGNETPRICE, SOItemKey.NQTORIGTAXNETPRC,
          SOItemKey.NQTORIGPRICE, SOItemKey.NQTORIGTAXPRICE,
        };

    String[] netpricekeys =
        new String[] {
          SOItemKey.NQTNETPRICE, SOItemKey.NQTPRICE, SOItemKey.NQTTAXPRICE,
          SOItemKey.NQTTAXNETPRICE, SOItemKey.NNETPRICE, SOItemKey.NPRICE,
          SOItemKey.NTAXNETPRICE, SOItemKey.NTAXPRICE
        };
    this.scale.setPriceCtlInfo(pricekeys, PosEnum.body, null,
        SOItemKey.CORIGCURRENCYID, PosEnum.head, null);

    this.scale.setPriceCtlInfo(netpricekeys, PosEnum.body, null,
        SOItemKey.CCURRENCYID, PosEnum.body, null);
  }
  
  

  /**
   * ����˰�� �۱����ʾ���
   */
  protected void setRateScale() {
    // ����˰��
    String[] taxRateKey_B = new String[] {
      SOItemKey.NTAXRATE
    };
    // ����˰��
    this.scale.setTaxRateCtlInfo(taxRateKey_B, PosEnum.body, null);
    
    FieldInfo exchangeRate = new FieldInfo(SOItemKey.NEXCHANGERATE,
        PosEnum.head.getCode(), null);
    
    // ԭ��
    FieldInfo localOrigCurr = new FieldInfo(
        SOItemKey.CORIGCURRENCYID, PosEnum.head.getCode(), null);

    // ��֯����
    FieldInfo orgCurr = new FieldInfo(SOItemKey.CCURRENCYID,
        PosEnum.head.getCode(), null);

    // ������֯
    FieldInfo settleOrg = new FieldInfo(SOItemKey.PK_ORG,
        PosEnum.head.getCode(), null);
    
    // �۱�����
    scale.setOrgExchangeCtlInfo(exchangeRate, localOrigCurr,
        orgCurr, settleOrg);
  }
}
