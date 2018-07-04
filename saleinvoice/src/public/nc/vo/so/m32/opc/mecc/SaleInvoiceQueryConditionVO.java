package nc.vo.so.m32.opc.mecc;

import java.io.Serializable;

/**
 * ��ѯ����VO
 * 
 * @since 6.3
 * @version 2012-10-23 ����12:57:42
 * @author ������
 */
public class SaleInvoiceQueryConditionVO implements Serializable {

  private static final long serialVersionUID = -872100620100984383L;

  // �����ͻ�
  private String[] customer;

  // ��ʼ����
  private String begindate;

  // ��ֹ����
  private String enddate;

  // ����״̬
  private String[] billstatus;

  public String[] getCustomer() {
    return this.customer;
  }

  public void setCustomer(String[] customer) {
    this.customer = customer;
  }

  public String getBegindate() {
    return this.begindate;
  }

  public void setBegindate(String begindate) {
    this.begindate = begindate;
  }

  public String getEnddate() {
    return this.enddate;
  }

  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }

  public String[] getBillstatus() {
    return this.billstatus;
  }

  public void setBillstatus(String[] billstatus) {
    this.billstatus = billstatus;
  }

}
