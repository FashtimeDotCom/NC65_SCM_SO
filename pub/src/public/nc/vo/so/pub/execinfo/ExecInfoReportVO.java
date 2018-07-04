package nc.vo.so.pub.execinfo;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * ִ�����
 * 
 * @since 6.0
 * @version 2010-12-31 ����10:15:24
 * @author ף����
 */
public class ExecInfoReportVO extends SuperVO {
  /** ���ϱ��� */
  public static final String CMATERIALID = "cmaterialid";

  /** ������λ */
  public static final String CUNITID = "cunitid";

  /** dr */
  public static final String DR = "dr";

  /** δ��Ʊ��� */
  public static final String NEEDINVOICEMONEY = "needinvoicemoney";

  /** δ��Ʊ���� */
  public static final String NEEDINVOICENUM = "needinvoicenum";

  /** δ�������� */
  public static final String NEEDOUTNUM = "needoutnum";

  /** δ������ */
  public static final String NEEDPAYMONEY = "needpaymoney";

  /** δ�������� */
  public static final String NEEDSENDNUM = "needsendnum";

  /** ���� */
  public static final String NNUM = "nnum";

  /** �ѿ�Ʊ���� */
  public static final String NTOTALINVOICENUM = "ntotalinvoicenum";

  /** �ѳ������� */
  public static final String NTOTALOUTNUM = "ntotaloutnum";

  /** �ѷ������� */
  public static final String NTOTALSENDNUM = "ntotalsendnum";

  /** Ӧ������ */
  public static final String SHOULDSENDNUM = "shouldsendnum";

  /** �ѿ�Ʊ��� */
  public static final String TOTALINVOICEMONEY = "totalinvoicemoney";

  /** �Ѹ����� */
  public static final String TOTALPAYMONEY = "totalpaymoney";

  /** ʱ��� */
  public static final String TS = "ts";

  public static final String CCURRENCYID = "ccurrencyid";

  private static final long serialVersionUID = 1L;

  public String getCcurrencyid() {
    return (String) this.getAttributeValue(ExecInfoReportVO.CCURRENCYID);
  }

  public String getCmaterialid() {
    return (String) this.getAttributeValue(ExecInfoReportVO.CMATERIALID);
  }

  public String getCunitid() {
    return (String) this.getAttributeValue(ExecInfoReportVO.CUNITID);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(ExecInfoReportVO.DR);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.execinfo");
    return meta;
  }

  public UFDouble getNeedinvoicemoney() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NEEDINVOICEMONEY);
  }

  public UFDouble getNeedinvoicenum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NEEDINVOICENUM);
  }

  public UFDouble getNeedoutnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NEEDOUTNUM);
  }

  public UFDouble getNeedpaymoney() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NEEDPAYMONEY);
  }

  public UFDouble getNeedsendnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NEEDSENDNUM);
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NNUM);
  }

  public UFDouble getNtotalinvoicenum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NTOTALINVOICENUM);
  }

  public UFDouble getNtotaloutnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NTOTALOUTNUM);
  }

  public UFDouble getNtotalsendnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NTOTALSENDNUM);
  }

  public UFDouble getShouldsendnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.SHOULDSENDNUM);
  }

  public UFDouble getTotalinvoicemoney() {
    return (UFDouble) this
        .getAttributeValue(ExecInfoReportVO.TOTALINVOICEMONEY);
  }

  public UFDouble getTotalpaymoney() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.TOTALPAYMONEY);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(ExecInfoReportVO.TS);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(ExecInfoReportVO.CCURRENCYID, ccurrencyid);
  }

  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(ExecInfoReportVO.CMATERIALID, cmaterialid);
  }

  public void setCunitid(String cunitid) {
    this.setAttributeValue(ExecInfoReportVO.CUNITID, cunitid);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(ExecInfoReportVO.DR, dr);
  }

  public void setNeedinvoicemoney(UFDouble needinvoicemoney) {
    this.setAttributeValue(ExecInfoReportVO.NEEDINVOICEMONEY, needinvoicemoney);
  }

  public void setNeedinvoicenum(UFDouble needinvoicenum) {
    this.setAttributeValue(ExecInfoReportVO.NEEDINVOICENUM, needinvoicenum);
  }

  public void setNeedoutnum(UFDouble needoutnum) {
    this.setAttributeValue(ExecInfoReportVO.NEEDOUTNUM, needoutnum);
  }

  /**
   * δ��������ߴ��ս��
   * 
   * @param needpaymoney
   */
  public void setNeedpaymoney(UFDouble needpaymoney) {
    this.setAttributeValue(ExecInfoReportVO.NEEDPAYMONEY, needpaymoney);
  }

  public void setNeedsendnum(UFDouble needsendnum) {
    this.setAttributeValue(ExecInfoReportVO.NEEDSENDNUM, needsendnum);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(ExecInfoReportVO.NNUM, nnum);
  }

  /**
   * ��Ʊ���������ǽ�������
   * 
   * @param ntotalinvoicenum
   */
  public void setNtotalinvoicenum(UFDouble ntotalinvoicenum) {
    this.setAttributeValue(ExecInfoReportVO.NTOTALINVOICENUM, ntotalinvoicenum);
  }

  public void setNtotaloutnum(UFDouble ntotaloutnum) {
    this.setAttributeValue(ExecInfoReportVO.NTOTALOUTNUM, ntotaloutnum);
  }

  public void setNtotalsendnum(UFDouble ntotalsendnum) {
    this.setAttributeValue(ExecInfoReportVO.NTOTALSENDNUM, ntotalsendnum);
  }

  public void setShouldsendnum(UFDouble shouldsendnum) {
    this.setAttributeValue(ExecInfoReportVO.SHOULDSENDNUM, shouldsendnum);
  }

  /**
   * ��Ʊ�������ǽ�����
   * 
   * @param totalinvoicemoney
   */
  public void setTotalinvoicemoney(UFDouble totalinvoicemoney) {
    this.setAttributeValue(ExecInfoReportVO.TOTALINVOICEMONEY,
        totalinvoicemoney);
  }

  /**
   * �Ѹ���������տ���
   * 
   * @param totalpaymoney
   */
  public void setTotalpaymoney(UFDouble totalpaymoney) {
    this.setAttributeValue(ExecInfoReportVO.TOTALPAYMONEY, totalpaymoney);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(ExecInfoReportVO.TS, ts);
  }
}
