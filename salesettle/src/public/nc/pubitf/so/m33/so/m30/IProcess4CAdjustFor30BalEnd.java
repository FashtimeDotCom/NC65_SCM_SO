package nc.pubitf.so.m33.so.m30;

public interface IProcess4CAdjustFor30BalEnd {

  /**
   * ����������������������رպ���ⵥ�����ݹ�Ӧ����Ҫ���ɶ�Ӧ�Ļس�Ӧ�յ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids -- ���γ��ⵥ�����ݹ�Ӧ�յĶ�����ID����
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 ����03:22:04
   */
  void process4CAdjust(String[] ordBids);

  /**
   * ����������������������رպ���ⵥ����������Ʒ��Ҫ���ɴ���������Ʒ
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids -- ���γ��ⵥ����������Ʒ�Ķ�����ID����
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 ����03:22:04
   */
  void process4CReg(String[] ordBids);

  /**
   * ����������������������򿪺���ⵥ�����ݹ�Ӧ�գ������ɶ�Ӧ�Ļس�Ӧ�յ�ȡ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids -- ���γ��ⵥ�����ݹ�Ӧ�յĶ�����ID����
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 ����03:22:04
   */
  void unProcess4CAdjust(String[] ordBids);

  /**
   * ����������������������򿪺���ⵥ����������Ʒ�������ɶ�Ӧ�Ĵ���������Ʒȡ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids -- ���γ��ⵥ����������Ʒ�Ķ�����ID����
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 ����03:22:04
   */
  void unProcess4CReg(String[] ordBids);

}
