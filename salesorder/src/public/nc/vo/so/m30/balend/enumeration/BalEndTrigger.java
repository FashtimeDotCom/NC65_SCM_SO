package nc.vo.so.m30.balend.enumeration;

public enum BalEndTrigger {
  // ���ⵥȷ��Ӧ��
  OUT_INCOME("4C_INCOME"),
  // ���ⵥ�ݹ�Ӧ��
  OUT_ESTAR("4C_EASTAR"),
  // ���ⵥ�ɱ�����
  OUT_COST("4C_COST"),
  // ��������Գ�
  OUT_RUSH("4C_RUSH"),
  // ���ⵥ������Ʒ
  OUT_REGIST("4C_REGIST"),
  // ��������ر�
  OUT_CLOSE("4C_CLOSE"),
  // ���ⵥɾ��
  OUT_DELETE("4C_DELETE"),
  // ��Ʊȷ��Ӧ��
  VOICE_INCOME("32_INCOME"),
  // ��Ʊ�ɱ�����
  VOICE_COST("32_COST"),
  // ��Ʊ�س�Ӧ��
  VOICE_RUSH("32_RUSH"),
  // ��Ʊ��Ӧ��
  VOICE_ADJUST("32_ADJUST"),
  // ������Ʊ�ر�
  VOICE_CLOSE("32_CLOSE"),
  // ��Ʊɾ��
  VOICE_DELETE("32_DELETE"),
  // ;��ǩ��
  WAST_AUDIT("4453_AUDIT"),
  // ;��ɾ��
  WAST_DELETE("4453_DELETE");

  private String code;

  /**
   * BalEndTrigger �Ĺ�����
   * 
   * @param trigger
   * @param name
   */
  private BalEndTrigger(
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
