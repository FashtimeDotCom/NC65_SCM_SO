package nc.vo.so.report.multipleprofit;

import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;

/**
 * �ۺ�ë������ViewMeta
 * 
 * @since 6.3
 * @version 2012-10-18 14:59:56
 * @author zhangkai4
 */
public class MultipleProfitViewMeta extends DataViewMeta {

  /**
   * String�ֶ�
   * ��ʱ���ص�String�ֶ�
   */
  public static final String[] MULPROFIT_STRKEYS = new String[] {
    MultipleProfitViewVO.CBIZID, MultipleProfitViewVO.CCUSTOMERID,
    MultipleProfitViewVO.CDPTID, MultipleProfitViewVO.CMATERIALID,
    MultipleProfitViewVO.CORIGCURRENCYID, MultipleProfitViewVO.CPRODLINEID,
    MultipleProfitViewVO.CSALEORGID, MultipleProfitViewVO.PK_AREACL,
    MultipleProfitViewVO.PK_CUSTCLASS, MultipleProfitViewVO.PK_CUSTSALECLASS,
    MultipleProfitViewVO.PK_MARBASCLASS, MultipleProfitViewVO.PK_MARSALECLASS,
    MultipleProfitViewVO.PK_ORG, MultipleProfitViewVO.VBATCHCODE,
    MultipleProfitViewVO.CUNITID, MultipleProfitViewVO.SOURCESYSTEM

  };

  /**
   * UFDouble�ֶ�
   * ��ʱ���ص�UFDouble�ֶ�
   */
  public static final String[] MULPROFIT_UFDKEYS = new String[] {
    MultipleProfitViewVO.NCOSTPRICE, MultipleProfitViewVO.NMAINNUM,
    MultipleProfitViewVO.NPROFITMNY, MultipleProfitViewVO.NPROFITRATE,
    MultipleProfitViewVO.NSHOULDRECEIVNUM, MultipleProfitViewVO.NTOTALCOSTMNY,
    MultipleProfitViewVO.NTOTALRECEIVEPRICE,
    MultipleProfitViewVO.NTOTALRECEIVMNY
  };

  /**
   * ���л����ֶ�
   */
  public static final String[] AGGKEYS = new String[] {
    MultipleProfitViewVO.NMAINNUM, MultipleProfitViewVO.NSHOULDRECEIVNUM,
    MultipleProfitViewVO.NTOTALCOSTMNY, MultipleProfitViewVO.NTOTALRECEIVMNY,
    MultipleProfitViewVO.NPROFITMNY
  };

  // /**
  // * ��Ҫ����Ľ���ֶ�
  // */
  // public static final String[] CALCULATEKEYS = new String[] {
  // MultipleProfitViewVO.NCOSTPRICE, MultipleProfitViewVO.NPROFITMNY,
  // MultipleProfitViewVO.NTOTALRECEIVEPRICE, MultipleProfitViewVO.NPROFITRATE
  // };

  /**
   * �����ֶ�
   */
  public static final String[] GROUPKEYS = new String[] {
    MultipleProfitViewVO.CBIZID,
    MultipleProfitViewVO.CCUSTOMERID,
    MultipleProfitViewVO.CDPTID,
    MultipleProfitViewVO.CMATERIALID,
    // MultipleProfitViewVO.CORIGCURRENCYID,
    MultipleProfitViewVO.CPRODLINEID, MultipleProfitViewVO.CSALEORGID,
    MultipleProfitViewVO.PK_AREACL, MultipleProfitViewVO.PK_CUSTCLASS,
    MultipleProfitViewVO.PK_CUSTSALECLASS, MultipleProfitViewVO.PK_MARBASCLASS,
    MultipleProfitViewVO.PK_MARSALECLASS, MultipleProfitViewVO.PK_ORG,
    MultipleProfitViewVO.VBATCHCODE, MultipleProfitViewVO.SOURCESYSTEM
  };

  /**
   * ����ֶ�
   */
  public static final String[] MNYKEYS = new String[] {
    MultipleProfitViewVO.NTOTALCOSTMNY, MultipleProfitViewVO.NTOTALRECEIVMNY,
    MultipleProfitViewVO.NPROFITMNY,MultipleProfitViewVO.NPROFITRATE
  };

  /**
   * �����ֶ�
   */
  public static final String[] NUMKEY = new String[] {
    MultipleProfitViewVO.NMAINNUM, MultipleProfitViewVO.NSHOULDRECEIVNUM
  };

  /**
   * �����ֶ�
   */
  public static final String[] PRICEKEY = new String[] {
    MultipleProfitViewVO.NCOSTPRICE, MultipleProfitViewVO.NTOTALRECEIVEPRICE,

  };

  /**
   * ������
   */
  public MultipleProfitViewMeta() {
    // �����ֶ�����
    this.addExtAttributes();
  }

  /**
   * �����ֶ�����
   */
  private void addExtAttributes() {
    // String�ֶ����͵�����
    for (String field : MultipleProfitViewMeta.MULPROFIT_STRKEYS) {
      this.addAttribute(field, JavaType.String);
    }
    // UFDouble�ֶ����͵�����
    for (String field : MultipleProfitViewMeta.MULPROFIT_UFDKEYS) {
      this.addAttribute(field, JavaType.UFDouble);
    }
  }

  /**
   * �����ֶ�����
   * 
   * @param itemkey ���õ��ֶ�
   * @param type �ֶε�����
   */
  private void addAttribute(String itemkey, JavaType type) {
    Attribute attribute = new Attribute(itemkey, null, null);
    attribute.setJavaType(type);
    attribute.setCustom(false);
    attribute.setStatic(false);
    attribute.setPersistence(false);
    attribute.setSerializable(true);
    this.add(attribute);
  }
}
