package nc.ui.so.mreturnpolicy.ref;

/**
 * ?user> ���ܣ� ���ڣ�(2004-7-1 8:33:35)
 */
public interface IRefReturn {
  /**
   * ?user> ���ܣ� ������ ���أ� ���⣺ ���ڣ�(2004-7-1 13:04:39) �޸����ڣ��޸��ˣ��޸�ԭ��ע�ͱ�־��
   * 
   * @return java.lang.String
   * @param code
   *          java.lang.String
   */
  String getNameByCode(String code);

  /**
   * ?user> ���ܣ� ������ ���أ� ���⣺ ���ڣ�(2004-7-1 8:34:59) �޸����ڣ��޸��ˣ��޸�ԭ��ע�ͱ�־��
   * 
   * @return java.lang.String
   */
  String getRefReturnCode();

  /**
   * ?user> ���ܣ� ������ ���أ� ���⣺ ���ڣ�(2004-7-1 8:34:59) �޸����ڣ��޸��ˣ��޸�ԭ��ע�ͱ�־��
   * 
   * @return java.lang.String
   */
  String getRefReturnName();

  /**
   * ?user> ���ܣ� ������ ���أ� ���⣺ ���ڣ�(2004-7-1 9:07:11) �޸����ڣ��޸��ˣ��޸�ԭ��ע�ͱ�־��
   * 
   * @return int
   */
  int showModal();
}
