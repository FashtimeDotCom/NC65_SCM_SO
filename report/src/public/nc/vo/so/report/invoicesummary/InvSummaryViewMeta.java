package nc.vo.so.report.invoicesummary;

import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * ���۷�Ʊִ�л�����ͼMETA
 * 
 * @since 6.3
 * @version 2012-09-10 09:21:11
 * @author ������
 */
public class InvSummaryViewMeta extends DataViewMeta {

  /**
   * ��ʵ�������ֶ���
   */
  public static final String[] SALEINV_HKEYS = new String[] {
    InvSummaryViewVO.CSALEINVOICEID, InvSummaryViewVO.PK_ORG,
    InvSummaryViewVO.DBILLDATE, InvSummaryViewVO.CINVOICECUSTID,
    InvSummaryViewVO.CORIGCURRENCYID, InvSummaryViewVO.CTRANTYPEID
  };

  /**
   * ��ʵ�������ֶ���
   */
  public static final String[] SALEINV_BKEYS = {
    InvSummaryViewVO.CSALEINVOICEBID, InvSummaryViewVO.CORDERCUSTID,
    InvSummaryViewVO.CSALEORGID, InvSummaryViewVO.CDEPTID,
    InvSummaryViewVO.CEMPLOYEEID, InvSummaryViewVO.CSENDSTOCKORGID,
    InvSummaryViewVO.CMATERIALID, InvSummaryViewVO.CUNITID,
    InvSummaryViewVO.VBATCHCODE, InvSummaryViewVO.VFIRSTTRANTYPE,
    InvSummaryViewVO.NNUM, InvSummaryViewVO.BLARGESSFLAG,
    InvSummaryViewVO.NORIGMNY, InvSummaryViewVO.NORIGTAXMNY,
    InvSummaryViewVO.NTAX, InvSummaryViewVO.NORIGDISCOUNT,
    InvSummaryViewVO.BLABORFLAG, InvSummaryViewVO.BDISCOUNTFLAG
  };

  /**
   * ��չString�ֶ�
   * ��ʱ���ص�String�ֶ�
   */
  public static final String[] EXTEND_STRKEYS = {
    InvSummaryViewVO.PK_CUSTCLASS, InvSummaryViewVO.PK_CUSTSALECLASS,
    InvSummaryViewVO.PK_AREACL, InvSummaryViewVO.PK_MARBASCLASS,
    InvSummaryViewVO.PK_MARSALECLASS, InvSummaryViewVO.CCHANNELTYPEID
  };

  /**
   * ��չUFDouble�ֶ�
   * ��ʱ���ص�UFDouble�ֶ�
   */
  public static final String[] EXTEND_UFKEYS = {
    InvSummaryViewVO.NSHOULDRECEIVNUM, InvSummaryViewVO.NSHOULDRECEIVMNY,
    InvSummaryViewVO.NTOTALRECEIVMNY
  };

  /**
   * ��ʱ���ص����۷�Ʊ�ֶ�
   */
  public static final String[] TMPTABLE_INVKEYS = new String[] {
    InvSummaryViewVO.PK_ORG, InvSummaryViewVO.CINVOICECUSTID,
    InvSummaryViewVO.CORIGCURRENCYID, InvSummaryViewVO.CTRANTYPEID,
    InvSummaryViewVO.CORDERCUSTID, InvSummaryViewVO.CSALEORGID,
    InvSummaryViewVO.CDEPTID, InvSummaryViewVO.CEMPLOYEEID,
    InvSummaryViewVO.CSENDSTOCKORGID, InvSummaryViewVO.CMATERIALID,
    InvSummaryViewVO.CUNITID, InvSummaryViewVO.VBATCHCODE,
    InvSummaryViewVO.BLARGESSFLAG, InvSummaryViewVO.VFIRSTTRANTYPE,
    InvSummaryViewVO.NNUM, InvSummaryViewVO.NORIGMNY,
    InvSummaryViewVO.NORIGTAXMNY, InvSummaryViewVO.NTAX,
    InvSummaryViewVO.NORIGDISCOUNT, InvSummaryViewVO.BLABORFLAG,
    InvSummaryViewVO.BDISCOUNTFLAG
  };

  /**
   * ���췽��
   */
  public InvSummaryViewMeta() {
    super(SaleInvoiceHVO.class, InvSummaryViewMeta.SALEINV_HKEYS);
    this.add(SaleInvoiceBVO.class, InvSummaryViewMeta.SALEINV_BKEYS);
    this.addRelation(SaleInvoiceHVO.class, InvSummaryViewVO.CSALEINVOICEID,
        SaleInvoiceBVO.class, InvSummaryViewVO.CSALEINVOICEID);
    this.addExtAttributes();
  }

  private void addExtAttributes() {
    for (String field : InvSummaryViewMeta.EXTEND_STRKEYS) {
      this.addAttribute(field, JavaType.String);
    }
    for (String field : InvSummaryViewMeta.EXTEND_UFKEYS) {
      this.addAttribute(field, JavaType.UFDouble);
    }
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
}
