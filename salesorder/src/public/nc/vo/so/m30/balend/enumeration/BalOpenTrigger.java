package nc.vo.so.m30.balend.enumeration;

public enum BalOpenTrigger {
  // ���ⵥȡ��Ӧ�ս���
  OUT_NOINCOME("4C_NOINCOME"),
  // ���ⵥȡ���ɱ�����
  OUT_NOCOST("4C_NOCOST"),
  // ȡ����������Գ�
  OUT_NORUSH("4C_NORUSH"),
  // ���������
  OUT_OPEN("4C_OPEN"),
  // ��Ʊȡ��Ӧ�ս���
  VOICE_NOINCOME("32_NOINCOME"),
  // ��Ʊȡ���ɱ�����
  VOICE_NOCOST("32_NOCOST"),
  // ������Ʊ��
  VOICE_OPEN("32_OPEN"),
  // ;������
  WAST_UNAUDIT("4453_UNAUDIT"),
  // ;������
  WAST_ADD("4453_ADD");

  private String code;

  /**
   * BalEndTrigger �Ĺ�����
   * 
   * @param trigger
   * @param name
   */
  private BalOpenTrigger(
      String code) {
    this.code = code;
  }

  /**
   * �����������������ض�������رմ����㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-14 ����10:59:08
   */
  public String getCode() {
    return this.code;
  }
}
