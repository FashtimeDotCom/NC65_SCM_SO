package nc.vo.so.report.invoicesummary;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * ���۷�Ʊִ�л�����ͼVO
 * 
 * @since 6.3
 * @version 2012-09-10 09:21:11
 * @author ������
 */
public class InvSummaryViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -7793492081245563347L;

  @Override
  public IDataViewMeta getMetaData() {
    // IDataViewMeta��Ҫ������ǰ��ͼ�����ʵ��
    IDataViewMeta viewMeta =
    // ��ͼ�����ഴ���࣬��������ȡԪ����
        DataViewMetaFactory.getInstance().getDataViewMeta(
            InvSummaryViewMeta.class);
    return viewMeta;
  }

  /**
   * ��Ʊ��ʵ��_����
   */
  public static final String CSALEINVOICEID = "csaleinvoiceid";

  /**
   * ��Ʊ��ʵ��_����
   */
  public static final String CSALEINVOICEBID = "csaleinvoicebid";

  /**
   * ��Ʊ��֯
   */
  public static final String PK_ORG = "pk_org";

  /**
   * ��Ʊ����
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * �����ͻ�
   */
  public static final String CORDERCUSTID = "cordercustid";

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
   * ��Ʊ�ͻ�
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";

  /**
   * ������֯
   */
  public static final String CSALEORGID = "csaleorgid";

  /**
   * ���۲���
   */
  public static final String CDEPTID = "cdeptid";

  /**
   * ����ҵ��Ա
   */
  public static final String CEMPLOYEEID = "cemployeeid";

  /**
   * �����֯
   */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

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
   * ����
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * ͳ�Ʒ���ͼ۸��ۿ�
   */
  /**
   * ��Ʊ����
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * ��������(������������)
   */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  /**
   * ��Ʒ
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * ������
   */
  public static final String NNUM = "nnum";

  /**
   * ����˰����(������)
   * 
   */
 
  public static final String NORIGTAXNETPRICE="norigtaxnetprice";
  /**
   * ��˰���
   */
  public static final String NORIGMNY = "norigmny";

  /**
   * ��˰�ϼ�
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /**
   * ˰��(����)
   */
  public static final String NTAX = "ntax";

  /**
   * �ۿ۶�
   */
  public static final String NORIGDISCOUNT = "norigdiscount";

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
  public static final String RECEIVMNY="receivmny";
  
  /**
   * Ӧ�����
   */
  public static final String NTOTALRECEIVMNY = "ntotalreceivmny";

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
   * @return ��ͷid
   */
  public String getCsaleinvoiceid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CSALEINVOICEID);
  }

  /**
   * 
   * @param csaleinvoiceid
   */
  public void setCsaleinvoiceid(String csaleinvoiceid) {
    this.setAttributeValue(InvSummaryViewVO.CSALEINVOICEID, csaleinvoiceid);
  }

  /**
   * 
   * @return ����id
   */
  public String getCsaleinvoicebid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CSALEINVOICEBID);
  }

  /**
   * 
   * @param csaleinvoicebid
   */
  public void setCsaleinvoicebid(String csaleinvoicebid) {
    this.setAttributeValue(InvSummaryViewVO.CSALEINVOICEBID, csaleinvoicebid);
  }

  /**
   * 
   * @return ��Ʊ��֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(InvSummaryViewVO.PK_ORG);
  }

  /**
   * 
   * @param pk_org
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InvSummaryViewVO.PK_ORG, pk_org);
  }

  /**
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) super.getAttributeValue(InvSummaryViewVO.CUNITID);
  }

  /**
   * ��������λ
   * 
   * @param cunitid
   */
  public void setCunitid(String cunitid) {
    super.setAttributeValue(InvSummaryViewVO.CUNITID, cunitid);
  }

  /**
   * 
   * @return ��Ʊ����
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvSummaryViewVO.DBILLDATE);
  }

  /**
   * 
   * @param dbilldate
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InvSummaryViewVO.DBILLDATE, dbilldate);
  }

  /**
   * 
   * @return �����ͻ�
   */
  public String getCordercustid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CORDERCUSTID);
  }

  /**
   * 
   * @param cordercustid
   */
  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(InvSummaryViewVO.CORDERCUSTID, cordercustid);
  }

  /**
   * 
   * @return ������������
   */
  public String getChanneltypeid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CCHANNELTYPEID);
  }

  /**
   * 
   * @param channeltypeid
   */
  public void setChanneltypeid(String channeltypeid) {
    this.setAttributeValue(InvSummaryViewVO.CCHANNELTYPEID, channeltypeid);
  }

  /**
   * 
   * @return ��������
   */
  public String getPk_areacl() {
    return (String) super.getAttributeValue(InvSummaryViewVO.PK_AREACL);
  }

  /**
   * 
   * @return �ͻ���������
   */
  public String getPk_custclass() {
    return (String) super.getAttributeValue(InvSummaryViewVO.PK_CUSTCLASS);
  }

  /**
   * 
   * @return �ͻ����۷���
   */
  public String getPk_custsaleclass() {
    return (String) super.getAttributeValue(InvSummaryViewVO.PK_CUSTSALECLASS);
  }

  /**
   * 
   * @return ���ϻ�������
   */
  public String getPk_marbasclass() {
    return (String) super.getAttributeValue(InvSummaryViewVO.PK_MARBASCLASS);
  }

  /**
   * 
   * @return �������۷���
   */
  public String getPk_marsaleclass() {
    return (String) super.getAttributeValue(InvSummaryViewVO.PK_MARSALECLASS);
  }

  /**
   * ���õ�������
   * 
   * @param pk_areacl
   */
  public void setPk_areacl(String pk_areacl) {
    super.setAttributeValue(InvSummaryViewVO.PK_AREACL, pk_areacl);
  }

  /**
   * ���ÿͻ���������
   * 
   * @param pk_custclass
   */
  public void setPk_custclass(String pk_custclass) {
    super.setAttributeValue(InvSummaryViewVO.PK_CUSTCLASS, pk_custclass);
  }

  /**
   * ���ÿͻ����۷���
   * 
   * @param pk_custsaleclass
   */
  public void setPk_custsaleclass(String pk_custsaleclass) {
    super
        .setAttributeValue(InvSummaryViewVO.PK_CUSTSALECLASS, pk_custsaleclass);
  }

  /**
   * �������ϻ�������
   * 
   * @param pk_marbasclass
   */
  public void setPk_marbasclass(String pk_marbasclass) {
    super.setAttributeValue(InvSummaryViewVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /**
   * �����������۷���
   * 
   * @param pk_marsaleclass
   */
  public void setPk_marsaleclass(String pk_marsaleclass) {
    super.setAttributeValue(InvSummaryViewVO.PK_MARSALECLASS, pk_marsaleclass);
  }

  /**
   * 
   * @return ��Ʊ�ͻ�
   */
  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CINVOICECUSTID);
  }

  /**
   * 
   * @param cinvoicecustid
   */
  public void setCinvoicecustid(String cinvoicecustid) {
    this.setAttributeValue(InvSummaryViewVO.CINVOICECUSTID, cinvoicecustid);
  }

  /**
   * 
   * @return ����
   */
  public String getCdeptid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CDEPTID);
  }

  /**
   * 
   * @param cdeptvid
   */
  public void setCdeptid(String cdeptvid) {
    this.setAttributeValue(InvSummaryViewVO.CDEPTID, cdeptvid);
  }

  /**
   * 
   * @return ҵ��Ա
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CEMPLOYEEID);
  }

  /**
   * 
   * @param cemployeeid
   */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(InvSummaryViewVO.CEMPLOYEEID, cemployeeid);
  }

  /**
   * 
   * @return ������֯
   * 
   */
  public String getCsaleorgid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CSALEORGID);
  }

  /**
   * 
   * @param csaleorgid
   */
  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(InvSummaryViewVO.CSALEORGID, csaleorgid);
  }

  /**
   * 
   * @return �����֯
   */
  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CSENDSTOCKORGID);
  }

  /**
   * 
   * @param csendstockorgid
   */
  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(InvSummaryViewVO.CSENDSTOCKORGID, csendstockorgid);
  }

  /**
   * 
   * @return ����
   */
  public String getCmaterialid() {
    return (String) super.getAttributeValue(InvSummaryViewVO.CMATERIALID);
  }

  /**
   * ��������
   * 
   * @param cmaterialid
   */
  public void setCmaterialid(String cmaterialid) {
    super.setAttributeValue(InvSummaryViewVO.CMATERIALID, cmaterialid);
  }

  /**
   * 
   * @return ���κ�
   */
  public String getVbatchcode() {
    return (String) super.getAttributeValue(InvSummaryViewVO.VBATCHCODE);
  }

  /**
   * �������κ�
   * 
   * @param vbatchcode
   */
  public void setVbatchcode(String vbatchcode) {
    super.setAttributeValue(InvSummaryViewVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 
   * @return ����
   */
  public String getCorigcurrencyid() {
    return (String) super.getAttributeValue(InvSummaryViewVO.CORIGCURRENCYID);
  }

  /**
   * ���ñ���
   * 
   * @param corigcurrencyid
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    super.setAttributeValue(InvSummaryViewVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 
   * @return ��Ʊ����
   */
  public String getCtrantypeid() {
    return (String) super.getAttributeValue(InvSummaryViewVO.CTRANTYPEID);
  }

  /**
   * ��Ʊ����
   * 
   * @param ctrantypeid
   */
  public void setCtrantypeid(String ctrantypeid) {
    super.setAttributeValue(InvSummaryViewVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * 
   * @return ��������
   */
  public String getVfirsttrantype() {
    return (String) super.getAttributeValue(InvSummaryViewVO.VFIRSTTRANTYPE);
  }

  /**
   * ��������
   * 
   * @param vfirsttrantype
   */
  public void setVfirsttrantypee(String vfirsttrantype) {
    super.setAttributeValue(InvSummaryViewVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * 
   * @return ��Ʒ
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(InvSummaryViewVO.BLARGESSFLAG);
  }

  /**
   * 
   * @param blargessflag
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(InvSummaryViewVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * 
   * @return ������
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(InvSummaryViewVO.NNUM);
  }

  /**
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(InvSummaryViewVO.NNUM, nnum);
  }

  /**
   * 
   * @return ��˰���
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(InvSummaryViewVO.NORIGMNY);
  }

  /**
   * 
   * @param norigmny
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(InvSummaryViewVO.NORIGMNY, norigmny);
  }

  /**
   * 
   * @return ��˰�ϼ�
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(InvSummaryViewVO.NORIGTAXMNY);
  }

  /**
   * 
   * @param norigtaxmny
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(InvSummaryViewVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 
   * @return ˰��
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(InvSummaryViewVO.NTAX);
  }

  /**
   * 
   * @param ntax
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(InvSummaryViewVO.NTAX, ntax);
  }

  /**
   * 
   * @return �ۿ۶�
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(InvSummaryViewVO.NORIGDISCOUNT);
  }

  /**
   * 
   * @param norigdiscount
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(InvSummaryViewVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * 
   * @return Ӧ��������
   */
  public UFDouble getNshouldreceivnum() {
    return (UFDouble) super
        .getAttributeValue(InvSummaryViewVO.NSHOULDRECEIVNUM);
  }

  /**
   * ����Ӧ��������
   * 
   * @param nshouldreceivnum
   * 
   */
  public void setNshouldreceivnum(UFDouble nshouldreceivnum) {
    super
        .setAttributeValue(InvSummaryViewVO.NSHOULDRECEIVNUM, nshouldreceivnum);
  }

  /**
   * ����Ӧ�����
   * 
   * @param nshouldreceivmny
   */
  public void setNshouldreceivmny(UFDouble nshouldreceivmny) {
    super
        .setAttributeValue(InvSummaryViewVO.NSHOULDRECEIVMNY, nshouldreceivmny);
  }

  /**
   * 
   * @return Ӧ�����
   */
  public UFDouble getNshouldreceivmny() {
    return (UFDouble) super
        .getAttributeValue(InvSummaryViewVO.NSHOULDRECEIVMNY);
  }

  /**
   * ����Ӧ�ս��
   * 
   * @param ntotalreceivmny
   */
  public void setNtotalreceivmny(UFDouble ntotalreceivmny) {
    super.setAttributeValue(InvSummaryViewVO.NTOTALRECEIVMNY, ntotalreceivmny);
  }

  /**
   * 
   * @return Ӧ�ս��
   */
  public UFDouble getNtotalreceivmny() {
    return (UFDouble) super.getAttributeValue(InvSummaryViewVO.NTOTALRECEIVMNY);
  }

  /**
   * 
   * @return ������
   */
  public UFBoolean getBlaborflag() {
    return (UFBoolean) this.getAttributeValue(InvSummaryViewVO.BLABORFLAG);
  }

  /**
   * 
   * @param blaborflag
   */
  public void setBlaborflag(UFBoolean blaborflag) {
    this.setAttributeValue(InvSummaryViewVO.BLABORFLAG, blaborflag);
  }

  /**
   * 
   * @return �ۿ���
   */
  public UFBoolean getBdiscountflag() {
    return (UFBoolean) this.getAttributeValue(InvSummaryViewVO.BDISCOUNTFLAG);
  }

  /**
   * 
   * @param bdiscountflag
   */
  public void setBdiscountflag(UFBoolean bdiscountflag) {
    this.setAttributeValue(InvSummaryViewVO.BDISCOUNTFLAG, bdiscountflag);
  }

}
