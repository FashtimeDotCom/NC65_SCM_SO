package nc.pubitf.so.m30.opc.mecc;

import nc.vo.pub.lang.UFBoolean;

/**
 * IQuerySaleOrderIDByMecc.query�ӿڷ���ֵ�ṹ
 * ��������������Ԥ����ͷID����������Ԥ������ID�����۶���ͷID�����۶�����ID������ر�״̬
 * 
 * @since 6.0
 * @version 2012-02-14 ����03:00:34
 * @author ����
 */
public class SaleOrderInfoVO {
  /**
   * ����ر�״̬
   */
  private UFBoolean bboutendflag;

  /**
   * ���۶�����ID
   */
  private String csaleorderbid;

  /**
   * ���۶���ͷID
   */
  private String csaleorderid;

  /**
   * ��������Ԥ������ID
   */
  private String csrcbid;

  /**
   * ��������Ԥ����ͷID
   */
  private String csrcid;

  public UFBoolean getBboutendflag() {
    return this.bboutendflag;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public String getCsaleorderid() {
    return this.csaleorderid;
  }

  public String getCsrcbid() {
    return this.csrcbid;
  }

  public String getCsrcid() {
    return this.csrcid;
  }

  public void setBboutendflag(UFBoolean bboutendflag) {
    this.bboutendflag = bboutendflag;
  }

  public void setCsaleorderbid(String csaleorderbid) {
    this.csaleorderbid = csaleorderbid;
  }

  public void setCsaleorderid(String csaleorderid) {
    this.csaleorderid = csaleorderid;
  }

  public void setCsrcbid(String csrcbid) {
    this.csrcbid = csrcbid;
  }

  public void setCsrcid(String csrcid) {
    this.csrcid = csrcid;
  }

}
