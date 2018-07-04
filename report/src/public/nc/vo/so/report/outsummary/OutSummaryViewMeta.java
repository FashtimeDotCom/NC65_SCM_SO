package nc.vo.so.report.outsummary;

import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;

/**
 * ���۳���ִ�л��ܲ�ѯ��ͼMETA
 * 
 * @since 6.3
 * @version 2012-10-14 ����6:00:12
 * @author ������
 */
public class OutSummaryViewMeta extends DataViewMeta {

  /**
   * ���۳��ⵥ��ʵ���ֶ�
   */
  public static final String[] SALEOUT_HKEYS = new String[] {
    OutSummaryViewVO.CGENERALHID, OutSummaryViewVO.CSALEORGOID,
    OutSummaryViewVO.PK_ORG, OutSummaryViewVO.CDPTID, OutSummaryViewVO.CBIZID,
    OutSummaryViewVO.CCUSTOMERID
  };

  /**
   * ���ⵥ��ʵ�������ֶ���
   */
  public static final String[] SALEOUT_BKEYS = {
    OutSummaryViewVO.CGENERALBID, OutSummaryViewVO.CINVOICECUSTID,
    OutSummaryViewVO.CRECEIEVEID, OutSummaryViewVO.CMATERIALOID,
    OutSummaryViewVO.CUNITID, OutSummaryViewVO.VBATCHCODE,
    OutSummaryViewVO.FLARGESS, OutSummaryViewVO.CORIGCURRENCYID,
    OutSummaryViewVO.NNUM, OutSummaryViewVO.NORIGTAXMNY
  };

  /**
   * ���ⵥִ�б������ֶ���
   */
  public static final String[] SALEOUT_EXEKEYS = {
    OutSummaryViewVO.NACCUMOUTSIGNNUM, OutSummaryViewVO.NACCUMOUTBACKNUM,
    OutSummaryViewVO.NACCUMWASTNUM, OutSummaryViewVO.NSIGNNUM
  };

  /**
   * ��չString�ֶ�
   * ��ʱ���ص�String�ֶ�
   */
  public static final String[] EXTEND_STRKEYS = {
    OutSummaryViewVO.PK_CUSTCLASS, OutSummaryViewVO.PK_CUSTSALECLASS,
    OutSummaryViewVO.PK_MARBASCLASS, OutSummaryViewVO.PK_MARSALECLASS,
    OutSummaryViewVO.PK_AREACL, OutSummaryViewVO.CCHANNELTYPEID
  };

  /**
   * ��չUFDouble�ֶ�
   * ��ʱ���ص�UFDouble�ֶ�
   */
  public static final String[] EXTEND_UFKEYS = {
    OutSummaryViewVO.NACCUMOUTSIGNNUM, OutSummaryViewVO.NACCUMWASTNUM,
    OutSummaryViewVO.NACCUMOUTBACKNUM, OutSummaryViewVO.NSIGNNUM,
    OutSummaryViewVO.NINVOICEMNY, OutSummaryViewVO.NARNUM,
    OutSummaryViewVO.NARMNY, OutSummaryViewVO.NARREMAINMNY,
    OutSummaryViewVO.NPAYMNY
  };

  /**
   * ��ʱ���صĳ��ⵥ�ֶ�
   */
  public static final String[] TMPTABLE_OUTKEYS = new String[] {
    OutSummaryViewVO.CSALEORGOID, OutSummaryViewVO.PK_ORG,
    OutSummaryViewVO.CDPTID, OutSummaryViewVO.CBIZID,
    OutSummaryViewVO.CCUSTOMERID, OutSummaryViewVO.CINVOICECUSTID,
    OutSummaryViewVO.CRECEIEVEID, OutSummaryViewVO.CMATERIALOID,
    OutSummaryViewVO.CUNITID, OutSummaryViewVO.VBATCHCODE,
    OutSummaryViewVO.FLARGESS, OutSummaryViewVO.CORIGCURRENCYID,
    OutSummaryViewVO.NNUM, OutSummaryViewVO.NORIGTAXMNY
  };

  /**
   * ����
   */
  public OutSummaryViewMeta() {
    super(SaleOutHeadVO.class, OutSummaryViewMeta.SALEOUT_HKEYS);
    this.add(SaleOutBodyVO.class, OutSummaryViewMeta.SALEOUT_BKEYS);
    this.addRelation(SaleOutHeadVO.class, OutSummaryViewVO.CGENERALHID,
        SaleOutBodyVO.class, OutSummaryViewVO.CGENERALHID);
    this.addExtAttributes();
  }

  private void addAttribute(String itemkey, JavaType type) {
    Attribute attribute = new Attribute(itemkey, null, null);
    attribute.setJavaType(type);
    attribute.setCustom(false);
    attribute.setStatic(false);
    attribute.setPersistence(false);
    attribute.setSerializable(true);
    this.add(attribute);
  }

  private void addExtAttributes() {

    for (String field : OutSummaryViewMeta.EXTEND_STRKEYS) {
      this.addAttribute(field, JavaType.String);
    }
    for (String field : OutSummaryViewMeta.EXTEND_UFKEYS) {
      this.addAttribute(field, JavaType.UFDouble);
    }
  }

}
