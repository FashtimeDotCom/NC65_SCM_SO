package nc.vo.so.m32.paravo;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

public class InvoiceReturnToReportVO {
  /** �Ƿ���Ʒ */
  private UFBoolean blargessflag;

  /** �����ͻ� */
  private String ccustomerid;

  /** ��Ʊ�ͻ� */
  private String cinvoicecustid;

  /** ����ID */
  private String cmaterialvid;

  /** ��λID */
  private String cunitid;

  /** ���� */
  private UFDouble nnum;

  /** ��˰��� */
  private UFDouble norigmny;

  /** ��˰�ϼ� */
  private UFDouble norigtaxmny;

  /** ������֯ */
  private String saleorgid;

  /** �����֯ */
  private String stockorgvid;

  public UFBoolean getBlargessflag() {
    return this.blargessflag;
  }

  public String getCcustomerid() {
    return this.ccustomerid;
  }

  public String getCinvoicecustid() {
    return this.cinvoicecustid;
  }

  public String getCmaterialvid() {
    return this.cmaterialvid;
  }

  public String getCunitid() {
    return this.cunitid;
  }

  public UFDouble getNnum() {
    return this.nnum;
  }

  public UFDouble getNorigmny() {
    return this.norigmny;
  }

  public UFDouble getNorigtaxmny() {
    return this.norigtaxmny;
  }

  public String getSaleorgid() {
    return this.saleorgid;
  }

  public String getStockorgvid() {
    return this.stockorgvid;
  }

  public void setBlargessflag(UFBoolean blargessflag) {
    this.blargessflag = blargessflag;
  }

  public void setCcustomerid(String ccustomerid) {
    this.ccustomerid = ccustomerid;
  }

  public void setCinvoicecustid(String cinvoicecustid) {
    this.cinvoicecustid = cinvoicecustid;
  }

  public void setCmaterialvid(String cmaterialvid) {
    this.cmaterialvid = cmaterialvid;
  }

  public void setCunitid(String cunitid) {
    this.cunitid = cunitid;
  }

  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  public void setNorigmny(UFDouble norigmny) {
    this.norigmny = norigmny;
  }

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.norigtaxmny = norigtaxmny;
  }

  public void setSaleorgid(String saleorgid) {
    this.saleorgid = saleorgid;
  }

  public void setStockorgvid(String stockorgvid) {
    this.stockorgvid = stockorgvid;
  }
}
