package nc.vo.so.report.ordersummary;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * ���۶���ִ�л�����ͼVO
 * 
 * @since 6.3
 * @version 2012-10-18 13:26:57
 * @author ������
 */
public class OrderSummaryViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -5952394906291813369L;

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewMeta =
    // ��ͼ�����ഴ���࣬��������ȡԪ����
        DataViewMetaFactory.getInstance().getDataViewMeta(
            OrderSummaryViewMeta.class);
    return viewMeta;
  }

  /**
   * ������ʵ��_����
   */
  public static final String CSALEORDERID = "csaleorderid";

  /**
   * ������ʵ��_����
   */
  public static final String CSALEORDERBID = "csaleorderbid";

  /**
   * ����ر�
   */
  public static final String BBOUTENDFLAG = "bboutendflag";

  /**
   * ������֯
   */
  public static final String PK_ORG = "pk_org";

  /**
   * �����֯
   */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  /**
   * ������
   */
  public static final String VBILLCODE = "vbillcode";

  /**
   * �����ͻ�
   */
  public static final String CCUSTOMERID = "ccustomerid";

  /**
   * ������������
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /**
   * �ͻ���������
   */
  public static final String PK_CUSTCLASS = "pk_custclass";

  /**
   * �ͻ����۷���
   */
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";

  /**
   * ��������
   */
  public static final String PK_AREACL = "pk_areacl";

  /**
   * ���۲���
   */
  public static final String CDEPTID = "cdeptid";

  /**
   * ����ҵ��Ա
   */
  public static final String CEMPLOYEEID = "cemployeeid";

  /**
   * ����
   */
  public static final String CMATERIALID = "cmaterialid";

  /**
   * ����λ
   */
  public static final String CUNITID = "cunitid";

  /**
   * ���κ�
   */
  public static final String VBATCHCODE = "vbatchcode";

  /**
   * ���ϻ�������
   */
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  /**
   * �������۷���
   */
  public static final String PK_MARSALECLASS = "pk_marsaleclass";

  /**
   * ͳ�Ʒ���ͼ۸��ۿ�
   */
  /**
   * ��������
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * ��Ʒ
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * ����
   */
  public static final String CORIGCURRENCYID = "ccurrencyid";

  /**
   * ������
   */
  public static final String NNUM = "nnum";

  /**
   * ����˰����(������)
   * 
   */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";
  
  /**
   * ��˰���
   */
  public static final String NORIGMNY = "nmny";

  /**
   * ��˰�ϼ�
   */
  public static final String NORIGTAXMNY = "ntaxmny";

  /**
   * ˰��(����)
   */
  public static final String NTAX = "ntax";

  /**
   * �ۿ۶�
   */
  public static final String NORIGDISCOUNT = "ndiscount";

  /**
   * ����������
   */
  public static final String NOUTNUM = "ntotaloutnum";

  /**
   * ǩ��������
   */
  public static final String NOUTSIGNNUM = "ntotalsignnum";

  /**
   * ;��������
   */
  public static final String NNORWASTNUM = "ntranslossnum";

  /**
   * ������������
   */
  public static final String NWAITOUTNUM = "nwaitoutnum";

  /**
   * ��Ʊ������
   */
  public static final String NINVOICENUM = "ntotalinvoicenum";

  /**
   * ��Ʊ���
   */
  public static final String NINVOICEORIGTAXMNY = "ninvoiceorigtaxmny";

  /**
   * Ӧ��������
   */
  public static final String NSHOULDRECEIVNUM = "nshouldreceivnum";

  /**
   * Ӧ�ս��
   */
  public static final String NSHOULDRECEIVMNY = "nshouldreceivmny";

  /**
   * �տ��������
   */
  public static final String NPAYMNY = "npaymny";
  /**
   * Ӧ�����
   */
  public static final String NTOTALRECEIVMNY = "ntotalreceivmny";

  /**
   * �ɱ����
   */
  public static final String NTOTALCOSTMNY = "ntotalcostmny";

  /**
   * �ɱ�����
   */
  public static final String NCOSTPRICE = "ncostprice";

  /**
   * ������
   */
  public static final String BLABORFLAG = "blaborflag";

  /**
   * �ۿ���
   */
  public static final String BDISCOUNTFLAG = "bdiscountflag";

  /**
   * 
   * @return ������ͷid
   */
  public String getCsaleorderid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CSALEORDERID);
  }

  /**
   * 
   * @param csaleinvoiceid
   */
  public void setCsaleorderid(String csaleinvoiceid) {
    this.setAttributeValue(OrderSummaryViewVO.CSALEORDERID, csaleinvoiceid);
  }

  /**
   * 
   * @return ��������id
   */
  public String getCsaleorderbid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CSALEORDERBID);
  }

  /**
   * 
   * @param csaleinvoicebid
   */
  public void setCsaleorderbid(String csaleinvoicebid) {
    this.setAttributeValue(OrderSummaryViewVO.CSALEORDERBID, csaleinvoicebid);
  }

  /**
   * 
   * @return ������֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.PK_ORG);
  }

  /**
   * 
   * @param pk_org
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(OrderSummaryViewVO.PK_ORG, pk_org);
  }

  /**
   * 
   * @return ������
   */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.VBILLCODE);
  }

  /**
   * 
   * @param vbillcode
   */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(OrderSummaryViewVO.VBILLCODE, vbillcode);
  }

  /**
   * 
   * @return �����ͻ�
   */
  public String getCordercustid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CCUSTOMERID);
  }

  /**
   * 
   * @param cordercustid
   */
  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(OrderSummaryViewVO.CCUSTOMERID, cordercustid);
  }

  /**
   * 
   * @return ������������
   */
  public String getChanneltypeid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CCHANNELTYPEID);
  }

  /**
   * 
   * @param channeltypeid
   */
  public void setChanneltypeid(String channeltypeid) {
    this.setAttributeValue(OrderSummaryViewVO.CCHANNELTYPEID, channeltypeid);
  }

  /**
   * 
   * @return ��������
   */
  public String getPk_areacl() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.PK_AREACL);
  }

  /**
   * 
   * @return �ͻ���������
   */
  public String getPk_custclass() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.PK_CUSTCLASS);
  }

  /**
   * 
   * @return �ͻ����۷���
   */
  public String getPk_custsaleclass() {
    return (String) super
        .getAttributeValue(OrderSummaryViewVO.PK_CUSTSALECLASS);
  }

  /**
   * 
   * @return ���ϻ�������
   */
  public String getPk_marbasclass() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.PK_MARBASCLASS);
  }

  /**
   * 
   * @return �������۷���
   */
  public String getPk_marsaleclass() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.PK_MARSALECLASS);
  }

  /**
   * ���õ�������
   * 
   * @param pk_areacl
   */
  public void setPk_areacl(String pk_areacl) {
    super.setAttributeValue(OrderSummaryViewVO.PK_AREACL, pk_areacl);
  }

  /**
   * ���ÿͻ���������
   * 
   * @param pk_custclass
   */
  public void setPk_custclass(String pk_custclass) {
    super.setAttributeValue(OrderSummaryViewVO.PK_CUSTCLASS, pk_custclass);
  }

  /**
   * ���ÿͻ����۷���
   * 
   * @param pk_custsaleclass
   */
  public void setPk_custsaleclass(String pk_custsaleclass) {
    super.setAttributeValue(OrderSummaryViewVO.PK_CUSTSALECLASS,
        pk_custsaleclass);
  }

  /**
   * �������ϻ�������
   * 
   * @param pk_marbasclass
   */
  public void setPk_marbasclass(String pk_marbasclass) {
    super.setAttributeValue(OrderSummaryViewVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /**
   * �����������۷���
   * 
   * @param pk_marsaleclass
   */
  public void setPk_marsaleclass(String pk_marsaleclass) {
    super
        .setAttributeValue(OrderSummaryViewVO.PK_MARSALECLASS, pk_marsaleclass);
  }

  /**
   * 
   * @return ����
   */
  public String getCdeptid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CDEPTID);
  }

  /**
   * 
   * @param cdeptvid
   */
  public void setCdeptid(String cdeptvid) {
    this.setAttributeValue(OrderSummaryViewVO.CDEPTID, cdeptvid);
  }

  /**
   * 
   * @return ҵ��Ա
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CEMPLOYEEID);
  }

  /**
   * 
   * @param cemployeeid
   */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(OrderSummaryViewVO.CEMPLOYEEID, cemployeeid);
  }

  /**
   * 
   * @return ����
   */
  public String getCmaterialid() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.CMATERIALID);
  }

  /**
   * ��������
   * 
   * @param cmaterialid
   */
  public void setCmaterialid(String cmaterialid) {
    super.setAttributeValue(OrderSummaryViewVO.CMATERIALID, cmaterialid);
  }

  /**
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.CUNITID);
  }

  /**
   * ��������λ
   * 
   * @param cunitid
   */
  public void setCunitid(String cunitid) {
    super.setAttributeValue(OrderSummaryViewVO.CUNITID, cunitid);
  }

  /**
   * 
   * @return ���κ�
   */
  public String getVbatchcode() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.VBATCHCODE);
  }

  /**
   * �������κ�
   * 
   * @param vbatchcode
   */
  public void setVbatchcode(String vbatchcode) {
    super.setAttributeValue(OrderSummaryViewVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 
   * @return ����
   */
  public String getCorigcurrencyid() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.CORIGCURRENCYID);
  }

  /**
   * ���ñ���
   * 
   * @param corigcurrencyid
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    super
        .setAttributeValue(OrderSummaryViewVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 
   * @return ����
   */
  public String getCtrantypeid() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.CTRANTYPEID);
  }

  /**
   * ����
   * 
   * @param ctrantypeid
   */
  public void setCtrantypeid(String ctrantypeid) {
    super.setAttributeValue(OrderSummaryViewVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * 
   * @return ��Ʒ
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(OrderSummaryViewVO.BLARGESSFLAG);
  }

  /**
   * 
   * @param blargessflag
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(OrderSummaryViewVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * 
   * @return ����ر�
   */
  public UFBoolean getBboutendflag() {
    return (UFBoolean) this.getAttributeValue(OrderSummaryViewVO.BBOUTENDFLAG);
  }

  /**
   * 
   * @param bboutendflag
   */
  public void setBboutendflag(UFBoolean bboutendflag) {
    this.setAttributeValue(OrderSummaryViewVO.BBOUTENDFLAG, bboutendflag);
  }

  /**
   * 
   * @return �ɱ����
   */
  public UFDouble getNtotalcostmny() {
    return (UFDouble) super.getAttributeValue(OrderSummaryViewVO.NTOTALCOSTMNY);
  }

  /**
   * ���óɱ����
   * 
   * @param ntotalcostmny
   */
  public void setNtotalcostmny(UFDouble ntotalcostmny) {
    super.setAttributeValue(OrderSummaryViewVO.NTOTALCOSTMNY, ntotalcostmny);
  }

  /**
   * 
   * @return ������
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NNUM);
  }

  /**
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(OrderSummaryViewVO.NNUM, nnum);
  }

  /**
   * 
   * @return ����������
   */
  public UFDouble getNoutnum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NOUTNUM);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNoutnum(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NOUTNUM, noutnum);
  }

  /**
   * 
   * @return ǩ��������
   */
  public UFDouble getNoutsignnum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NOUTSIGNNUM);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNoutsignnum(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NOUTSIGNNUM, noutnum);
  }

  /**
   * @return ;��������
   */
  public UFDouble getNnorwastnum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NNORWASTNUM);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNnorwastnum(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NNORWASTNUM, noutnum);
  }

  /**
   * @return ������������
   */
  public UFDouble getNwaitoutnum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NWAITOUTNUM);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNwaitoutnum(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NWAITOUTNUM, noutnum);
  }

  /**
   * @return ��Ʊ������
   */
  public UFDouble getNinvoicenum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NINVOICENUM);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNinvoicenum(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NINVOICENUM, noutnum);
  }

  /**
   * @return ��Ʊ���
   */
  public UFDouble getNinvoiceorigtaxmny() {
    return (UFDouble) this
        .getAttributeValue(OrderSummaryViewVO.NINVOICEORIGTAXMNY);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNinvoiceorigtaxmny(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NINVOICEORIGTAXMNY, noutnum);
  }

  /**
   * 
   * @return ��˰���
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NORIGMNY);
  }

  /**
   * 
   * @param norigmny
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(OrderSummaryViewVO.NORIGMNY, norigmny);
  }

  /**
   * 
   * @return ��˰�ϼ�
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NORIGTAXMNY);
  }

  /**
   * 
   * @param norigtaxmny
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(OrderSummaryViewVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 
   * @return ˰��
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NTAX);
  }

  /**
   * 
   * @param ntax
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(OrderSummaryViewVO.NTAX, ntax);
  }

  /**
   * 
   * @return �ۿ۶�
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NORIGDISCOUNT);
  }

  /**
   * 
   * @param norigdiscount
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(OrderSummaryViewVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * 
   * @return Ӧ��������
   */
  public UFDouble getNshouldreceivnum() {
    return (UFDouble) super
        .getAttributeValue(OrderSummaryViewVO.NSHOULDRECEIVNUM);
  }

  /**
   * ����Ӧ��������
   * 
   * @param nshouldreceivnum
   * 
   */
  public void setNshouldreceivnum(UFDouble nshouldreceivnum) {
    super.setAttributeValue(OrderSummaryViewVO.NSHOULDRECEIVNUM,
        nshouldreceivnum);
  }

  /**
   * ����Ӧ�ս��
   * 
   * @param nshouldreceivmny
   */
  public void setNshouldreceivmny(UFDouble nshouldreceivmny) {
    super.setAttributeValue(OrderSummaryViewVO.NSHOULDRECEIVMNY,
        nshouldreceivmny);
  }

  /**
   * 
   * @return Ӧ�ս��
   */
  public UFDouble getNshouldreceivmny() {
    return (UFDouble) super
        .getAttributeValue(OrderSummaryViewVO.NSHOULDRECEIVMNY);
  }

  /**
   * ����Ӧ�����
   * 
   * @param ntotalreceivmny
   */
  public void setNtotalreceivmny(UFDouble ntotalreceivmny) {
    super
        .setAttributeValue(OrderSummaryViewVO.NTOTALRECEIVMNY, ntotalreceivmny);
  }

  /**
   * 
   * @return Ӧ�����
   */
  public UFDouble getNtotalreceivmny() {
    return (UFDouble) super
        .getAttributeValue(OrderSummaryViewVO.NTOTALRECEIVMNY);
  }

  /**
   * 
   * @return ������
   */
  public UFBoolean getBlaborflag() {
    return (UFBoolean) this.getAttributeValue(OrderSummaryViewVO.BLABORFLAG);
  }

  /**
   * 
   * @param blaborflag
   */
  public void setBlaborflag(UFBoolean blaborflag) {
    this.setAttributeValue(OrderSummaryViewVO.BLABORFLAG, blaborflag);
  }

  /**
   * 
   * @return �ۿ���
   */
  public UFBoolean getBdiscountflag() {
    return (UFBoolean) this.getAttributeValue(OrderSummaryViewVO.BDISCOUNTFLAG);
  }

  /**
   * 
   * @param bdiscountflag
   */
  public void setBdiscountflag(UFBoolean bdiscountflag) {
    this.setAttributeValue(OrderSummaryViewVO.BDISCOUNTFLAG, bdiscountflag);
  }

  /**
   * 
   * @return �ɱ�����
   */
  public UFDouble getNcostprice() {
    return (UFDouble) super.getAttributeValue(OrderSummaryViewVO.NCOSTPRICE);
  }

  /**
   * ���óɱ�����
   * 
   * @param ncostprice
   */
  public void setNcostprice(UFDouble ncostprice) {
    super.setAttributeValue(OrderSummaryViewVO.NCOSTPRICE, ncostprice);
  }
}
