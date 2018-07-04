package nc.vo.so.report.outsummary;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * ���۳���ִ�л�����ͼVO
 * 
 * @since 6.3
 * @version 2012-10-18 13:36:47
 * @author ������
 */
public class OutSummaryViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -5218796592933427907L;

  /**
   * ���۳��ⵥ��ʵ��
   */
  public static final String CGENERALHID = "cgeneralhid";

  /**
   * ���۳��ⵥ��ʵ��
   */
  public static final String CGENERALBID = "cgeneralbid";

  /**
   * ������֯
   */
  public static final String CSALEORGOID = "csaleorgoid";

  /**
   * �����֯
   */
  public static final String PK_ORG = "pk_org";

  /**
   * �����ͻ�
   */
  public static final String CCUSTOMERID = "ccustomerid";

  /**
   * ������������
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /**
   * ���۲���
   */
  public static final String CDPTID = "cdptid";

  /**
   * ����ҵ��Ա
   */
  public static final String CBIZID = "cbizid";

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
   * �ջ��ͻ�
   */
  public static final String CRECEIEVEID = "creceieveid";

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
  public static final String CMATERIALOID = "cmaterialoid";

  /**
   * ����λ
   */
  public static final String CUNITID = "cunitid";

  /**
   * ���κ�
   */
  public static final String VBATCHCODE = "vbatchcode";

  /**
   * ��Ʒ
   */
  public static final String FLARGESS = "flargess";

  /**
   * ����
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * ����������
   */
  public static final String NNUM = "nnum";

  /**
   * ������
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /**
   * ǩ��������
   */
  public static final String NACCUMOUTSIGNNUM = "naccumoutsignnum";

  /**
   * ;��������
   */
  public static final String NACCUMWASTNUM = "naccumwastnum";

  /**
   * �˻�������
   */
  public static final String NACCUMOUTBACKNUM = "naccumoutbacknum";

  /**
   * ��Ʊ������
   */
  public static final String NSIGNNUM = "nsignnum";

  /**
   * ��Ʊ���
   */
  public static final String NINVOICEMNY = "ninvoicemny";

  /**
   * Ӧ��������
   */
  public static final String NARNUM = "narnum";

  /**
   * Ӧ�ս��
   */
  public static final String NARMNY = "narmny";

  /**
   * Ӧ�����
   */
  public static final String NARREMAINMNY = "narremainmny";

  /**
   * �տ���
   */
  public static final String NPAYMNY = "npaymny";

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewMeta =
        DataViewMetaFactory.getInstance().getDataViewMeta(
            OutSummaryViewMeta.class);
    return viewMeta;

  }

  /**
   * 
   * @return ���۳��ⵥ��ʵ��
   */
  public String getCgeneralhid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CGENERALHID);
  }

  /**
   * �������۳��ⵥ��ʵ��
   * 
   * @param cgeneralhid
   */
  public void setCgeneralhid(String cgeneralhid) {
    super.setAttributeValue(OutSummaryViewVO.CGENERALHID, cgeneralhid);
  }

  /**
   * 
   * @return ���۳��ⵥ��ʵ��
   */
  public String getCgeneralbid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CGENERALBID);
  }

  /**
   * �������۳��ⵥ��ʵ��
   * 
   * @param cgeneralbid
   */
  public void setCgeneralbid(String cgeneralbid) {
    super.setAttributeValue(OutSummaryViewVO.CGENERALBID, cgeneralbid);
  }

  /**
   * 
   * @return ������֯
   */
  public String getCsaleorgoid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CSALEORGOID);
  }

  /**
   * ����������֯
   * 
   * @param csaleorgoid
   */
  public void setCsaleorgoid(String csaleorgoid) {
    super.setAttributeValue(OutSummaryViewVO.CSALEORGOID, csaleorgoid);
  }

  /**
   * 
   * @return �����֯
   */
  public String getPk_org() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_ORG);
  }

  /**
   * ���ÿ����֯
   * 
   * @param pk_org
   */
  public void setPk_org(String pk_org) {
    super.setAttributeValue(OutSummaryViewVO.PK_ORG, pk_org);
  }

  /**
   * 
   * @return ��������
   */
  public String getCchanneltypeid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CCHANNELTYPEID);
  }

  /**
   * ������������
   * 
   * @param cchanneltypeid
   */
  public void setCchanneltypeid(String cchanneltypeid) {
    super.setAttributeValue(OutSummaryViewVO.CCHANNELTYPEID, cchanneltypeid);
  }

  /**
   * 
   * @return ����
   */
  public String getCdptid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CDPTID);
  }

  /**
   * ���ò���
   * 
   * @param cdptid
   */
  public void setCdptid(String cdptid) {
    super.setAttributeValue(OutSummaryViewVO.CDPTID, cdptid);
  }

  /**
   * 
   * @return ҵ��Ա
   */
  public String getCbizid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CBIZID);
  }

  /**
   * ����ҵ��Ա
   * 
   * @param cbizid
   */
  public void setCbizid(String cbizid) {
    super.setAttributeValue(OutSummaryViewVO.CBIZID, cbizid);
  }

  /**
   * 
   * @return �ͻ���������
   */
  public String getPk_custclass() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_CUSTCLASS);
  }

  /**
   * ���ÿͻ���������
   * 
   * @param pk_custclass
   */
  public void setPk_custclass(String pk_custclass) {
    super.setAttributeValue(OutSummaryViewVO.PK_CUSTCLASS, pk_custclass);
  }

  /**
   * 
   * @return �ͻ����۷���
   */
  public String getPk_custsaleclass() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_CUSTSALECLASS);
  }

  /**
   * ���ÿͻ����۷���
   * 
   * @param pk_custsaleclass
   */
  public void setPk_custsaleclass(String pk_custsaleclass) {
    super
        .setAttributeValue(OutSummaryViewVO.PK_CUSTSALECLASS, pk_custsaleclass);
  }

  /**
   * 
   * @return ��������
   */
  public String getPk_areacl() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_AREACL);
  }

  /**
   * ���õ�������
   * 
   * @param pk_areacl
   */
  public void setPk_areacl(String pk_areacl) {
    super.setAttributeValue(OutSummaryViewVO.PK_AREACL, pk_areacl);
  }

  /**
   * 
   * @return �����ͻ�
   */
  public String getCcustomerid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CCUSTOMERID);
  }

  /**
   * ���ö����ͻ�
   * 
   * @param ccustomerid
   */
  public void setCcustomerid(String ccustomerid) {
    super.setAttributeValue(OutSummaryViewVO.CCUSTOMERID, ccustomerid);
  }

  /**
   * 
   * @return ��Ʊ�ͻ�
   */
  public String getCinvoicecustid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CINVOICECUSTID);
  }

  /**
   * ���ÿ�Ʊ�ͻ�
   * 
   * @param cinvoicecustid
   */
  public void setCinvoicecustid(String cinvoicecustid) {
    super.setAttributeValue(OutSummaryViewVO.CINVOICECUSTID, cinvoicecustid);
  }

  /**
   * 
   * @return �ջ��ͻ�
   */
  public String getCreceieveid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CRECEIEVEID);
  }

  /**
   * �����ջ��ͻ�
   * 
   * @param creceieveid
   */
  public void setCreceieveid(String creceieveid) {
    super.setAttributeValue(OutSummaryViewVO.CRECEIEVEID, creceieveid);
  }

  /**
   * 
   * @return ���ϻ�������
   */
  public String getPk_marbasclass() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_MARBASCLASS);
  }

  /**
   * �������ϻ�������
   * 
   * @param pk_marbasclass
   */
  public void setPk_marbasclass(String pk_marbasclass) {
    super.setAttributeValue(OutSummaryViewVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /**
   * 
   * @return �������۷���
   */
  public String getPk_marsaleclass() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_MARSALECLASS);
  }

  /**
   * �����������۷���
   * 
   * @param pk_marsaleclass
   */
  public void setPk_marsaleclass(String pk_marsaleclass) {
    super.setAttributeValue(OutSummaryViewVO.PK_MARSALECLASS, pk_marsaleclass);
  }

  /**
   * 
   * @return ����
   */
  public String getCmaterialoid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CMATERIALOID);
  }

  /**
   * ��������
   * 
   * @param cmaterialoid
   */
  public void setCmaterialoid(String cmaterialoid) {
    super.setAttributeValue(OutSummaryViewVO.CMATERIALOID, cmaterialoid);
  }

  /**
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CUNITID);
  }

  /**
   * ��������λ
   * 
   * @param cunitid
   */
  public void setCunitid(String cunitid) {
    super.setAttributeValue(OutSummaryViewVO.CUNITID, cunitid);
  }

  /**
   * 
   * @return ���κ�
   */
  public String getVbatchcode() {
    return (String) super.getAttributeValue(OutSummaryViewVO.VBATCHCODE);
  }

  /**
   * �������κ�
   * 
   * @param vbatchcode
   */
  public void setVbatchcode(String vbatchcode) {
    super.setAttributeValue(OutSummaryViewVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 
   * @return ��Ʒ
   */
  public UFBoolean getflargess() {
    return (UFBoolean) super.getAttributeValue(OutSummaryViewVO.FLARGESS);
  }

  /**
   * ������Ʒ
   * 
   * @param flargess
   */
  public void setflargess(UFBoolean flargess) {
    super.setAttributeValue(OutSummaryViewVO.FLARGESS, flargess);
  }

  /**
   * 
   * @return ����
   */
  public String getCorigcurrencyid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CORIGCURRENCYID);
  }

  /**
   * ���ñ���
   * 
   * @param corigcurrencyid
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    super.setAttributeValue(OutSummaryViewVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 
   * @return ����������
   */
  public UFDouble getNnum() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NNUM);
  }

  /**
   * ���ó���������
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    super.setAttributeValue(OutSummaryViewVO.NNUM, nnum);
  }

  /**
   * 
   * @return ������
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NORIGTAXMNY);
  }

  /**
   * ���ó�����
   * 
   * @param norigtaxmny
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    super.setAttributeValue(OutSummaryViewVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 
   * @return ǩ��������
   */
  public UFDouble getNaccumoutsignnum() {
    return (UFDouble) super
        .getAttributeValue(OutSummaryViewVO.NACCUMOUTSIGNNUM);
  }

  /**
   * ����ǩ��������
   * 
   * @param naccumoutsignnum
   */
  public void setNaccumoutsignnum(UFDouble naccumoutsignnum) {
    super
        .setAttributeValue(OutSummaryViewVO.NACCUMOUTSIGNNUM, naccumoutsignnum);
  }

  /**
   * 
   * @return ;��������
   */
  public UFDouble getNaccumwastnum() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NACCUMWASTNUM);
  }

  /**
   * ����;��������
   * 
   * @param naccumwastnum
   */
  public void setNaccumwastnum(UFDouble naccumwastnum) {
    super.setAttributeValue(OutSummaryViewVO.NACCUMWASTNUM, naccumwastnum);
  }

  /**
   * 
   * @return �˻�������
   */
  public UFDouble getNaccumoutbacknum() {
    return (UFDouble) super
        .getAttributeValue(OutSummaryViewVO.NACCUMOUTBACKNUM);
  }

  /**
   * �����˻�������
   * 
   * @param naccumoutbacknum
   */
  public void setNaccumoutbacknum(UFDouble naccumoutbacknum) {
    super
        .setAttributeValue(OutSummaryViewVO.NACCUMOUTBACKNUM, naccumoutbacknum);
  }

  /**
   * 
   * @return ��Ʊ������
   */
  public UFDouble getNsignnum() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NSIGNNUM);
  }

  /**
   * ���ÿ�Ʊ������
   * 
   * @param nsignnum
   */
  public void setNsignnum(UFDouble nsignnum) {
    super.setAttributeValue(OutSummaryViewVO.NSIGNNUM, nsignnum);
  }

  /**
   * 
   * @return ��Ʊ���
   */
  public UFDouble getNinvoicemny() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NINVOICEMNY);
  }

  /**
   * ���ÿ�Ʊ���
   * 
   * @param ninvoicemny
   */
  public void setNinvoicemny(UFDouble ninvoicemny) {
    super.setAttributeValue(OutSummaryViewVO.NINVOICEMNY, ninvoicemny);
  }

  /**
   * 
   * @return Ӧ��������
   */
  public UFDouble getNarnum() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NARNUM);
  }

  /**
   * ����Ӧ��������
   * 
   * @param narnum
   */
  public void setNarnum(UFDouble narnum) {
    super.setAttributeValue(OutSummaryViewVO.NARNUM, narnum);
  }

  /**
   * 
   * @return Ӧ�ս��
   */
  public UFDouble getNarmny() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NARMNY);
  }

  /**
   * ����Ӧ�ս��
   * 
   * @param narmny
   */
  public void setNarmny(UFDouble narmny) {
    super.setAttributeValue(OutSummaryViewVO.NARMNY, narmny);
  }

  /**
   * 
   * @return Ӧ�����
   */
  public UFDouble getNarremainmny() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NARREMAINMNY);
  }

  /**
   * ����Ӧ�����
   * 
   * @param narremainmny
   */
  public void setNarremainmny(UFDouble narremainmny) {
    super.setAttributeValue(OutSummaryViewVO.NARREMAINMNY, narremainmny);
  }

  /**
   * 
   * @return �տ���
   */
  public UFDouble getNpaymny() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NPAYMNY);
  }

  /**
   * �����տ���
   * 
   * @param npaymny
   */
  public void setNpaymny(UFDouble npaymny) {
    super.setAttributeValue(OutSummaryViewVO.NPAYMNY, npaymny);
  }
}
