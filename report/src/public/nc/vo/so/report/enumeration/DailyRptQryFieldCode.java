package nc.vo.so.report.enumeration;

/**
 * �ۺ��ձ���ѯ�����ֶ�ö����
 * 
 * @since 6.3
 * @version 2013-4-26 ����04:40:39
 * @author tianft
 */
public enum DailyRptQryFieldCode {
  saleOrderStatus("saleorderfstatusflag"), // ���۶���״̬
  generalStatus("generalstatusflag"), // ���ⵥ����״̬
  saleInvoiceStatus("saleinvoicefstatusflag"), // ���۷�Ʊ����״̬
  deliveryStatus("deliveryfstatusflag");// ����������״̬

  private String code;

  private DailyRptQryFieldCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }

}
