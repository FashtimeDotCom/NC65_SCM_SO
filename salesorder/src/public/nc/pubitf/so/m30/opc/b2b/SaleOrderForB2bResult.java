package nc.pubitf.so.m30.opc.b2b;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * B2BԤ�������ؽ��������
 * 
 * @since 6.5
 * @version 2014-04-02 14:56:14
 * @author zhangyfr
 */
public class SaleOrderForB2bResult implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 116991112972746850L;

  /**
   * ����������ID
   */
  private String cpromotpriceid;

  /**
   * �ͻ�
   */
  private String ccustomerid;

  /**
   * ����ر�
   */
  private UFBoolean bboutendflag;

  /**
   * �����ر�
   */
  private UFBoolean bbsendendflag;

  /**
   * ���۵�λ����
   */
  private UFDouble nqtunitnum;

  /**
   * �ۼƷ�����λ����
   */
  private UFDouble ntotalsendnum;

  /**
   * �ۼƳ��ⵥλ����
   */
  private UFDouble ntotaloutnum;

  /**
   * ��Դ������ID
   */
  private String csrcbid;

  /**
   * �����Դ������ID
   * 
   * @return csrcbid
   */
  public String getCsrcbid() {
    return this.csrcbid;
  }

  /**
   * ������Դ������ID
   * 
   * @param csrcbid
   */
  public void setCsrcbid(String csrcbid) {
    this.csrcbid = csrcbid;
  }

  /**
   * ��ô���������ID
   * 
   * @return cpromotpriceid
   */
  public String getCpromotpriceid() {
    return this.cpromotpriceid;
  }

  /**
   * ���ô���������ID
   * 
   * @param cpromotpriceid
   */
  public void setCpromotpriceid(String cpromotpriceid) {
    this.cpromotpriceid = cpromotpriceid;
  }

  /**
   * ��ÿͻ�
   * 
   * @return ccustomerid
   */
  public String getCcustomerid() {
    return this.ccustomerid;
  }

  /**
   * ���ÿͻ�
   * 
   * @param ccustomerid
   */
  public void setCcustomerid(String ccustomerid) {
    this.ccustomerid = ccustomerid;
  }

  /**
   * ��ó���ر�
   * 
   * @return bboutendflag
   */
  public UFBoolean getBboutendflag() {
    return this.bboutendflag;
  }

  /**
   * ���ó���ر�
   * 
   * @param bboutendflag
   */
  public void setBboutendflag(UFBoolean bboutendflag) {
    this.bboutendflag = bboutendflag;
  }

  /**
   * ��÷����ر�
   * 
   * @return bbsendendflag
   */
  public UFBoolean getBbsendendflag() {
    return this.bbsendendflag;
  }

  /**
   * ���÷����ر�
   * 
   * @param bbsendendflag
   */
  public void setBbsendendflag(UFBoolean bbsendendflag) {
    this.bbsendendflag = bbsendendflag;
  }

  /**
   * ��ñ��۵�λ����
   * 
   * @return nqtunitnum
   */
  public UFDouble getNqtunitnum() {
    return this.nqtunitnum;
  }

  /**
   * ���ñ��۵�λ����
   * 
   * @param nqtunitnum
   */
  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.nqtunitnum = nqtunitnum;
  }

  /**
   * ����ۼƷ�������
   * 
   * @return ntotalsendnums
   */
  public UFDouble getNtotalsendnum() {
    return this.ntotalsendnum;
  }

  /**
   * �����ۼƳ�������
   * 
   * @param ntotalsendnum
   */
  public void setNtotalsendnum(UFDouble ntotalsendnum) {
    this.ntotalsendnum = ntotalsendnum;
  }

  /**
   * ����ۼƳ�������
   * 
   * @return ntotalsendnum
   */
  public UFDouble getNtotaloutnum() {
    return this.ntotaloutnum;
  }

  /**
   * �����ۼƳ�������
   * 
   * @param ntotaloutnum
   */
  public void setNtotaloutnum(UFDouble ntotaloutnum) {
    this.ntotaloutnum = ntotaloutnum;
  }

}
