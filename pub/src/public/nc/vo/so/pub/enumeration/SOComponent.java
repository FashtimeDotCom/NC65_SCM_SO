package nc.vo.so.pub.enumeration;

/**
 * ���۹������ö��
 * 
 * @since 6.0
 * @version 2012-5-17 ����09:55:19
 * @author ô��
 */
public enum SOComponent {
  /**
   * ���۷��õ�
   */
  Arsub("m35"),

  /**
   * ������
   */
  Delivery("m4331"),

  /**
   * ���۷�Ʊ
   */
  Invoice("m32"),

  /**
   * ���۶���
   */
  Order("m30"),

  /**
   * Ԥ����
   */
  PreOrder("m38"),

  /**
   * ���۱��۵�
   */
  SaleQuotation("m4310"),

  /**
   * �˻�����
   */
  Returnpolicy("returnpolicy");

  // �������
  private String component;

  /**
   * ������
   * 
   * @return �����
   */
  public String getComponent() {
    return this.component;
  }

  private SOComponent(String component) {
    this.component = component;
  }
}
