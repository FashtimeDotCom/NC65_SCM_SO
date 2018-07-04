package nc.pubitf.so.m33.arap;

public enum AccountDateType {

  /**
   * ��������
   */
  OUT_STORE_DATE(0),

  /**
   * ����ǩ������
   */
  OUTSTORE_SIGNATURE_DATE(1),

  /**
   * ���ۺ�ͬ��Ч����
   */
  SALE_CONTRACT_EFFECTIVE_DATE(12),

  /**
   * ���۷�Ʊ�������
   */
  SALE_INVOICE_APPROVE_DATE(3),

  /**
   * ���ۿ�Ʊ����
   */
  SALE_MAKE_BILL_DATE(2),

  /**
   * ���۶�������
   */
  SALE_ORDER_DATE(11);

  private int code;

  private AccountDateType(int code) {
    this.code = code;
  }

  /**
   * 
   * @return ����
   */
  public int getCode() {
    return this.code;
  }

}
