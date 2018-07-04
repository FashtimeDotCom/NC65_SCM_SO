package nc.vo.so.pub;

/**
 * ���۹��ܽڵ�ö��
 * 
 * @since 6.1
 * @version 2012-11-29 11:15:49
 * @author ��ӱ�
 */
public enum SOFunc {

  /**
   * �ƶ�Ӧ��-���۶���
   */
  A03002("A03002", "���۶���")/* -=notranslate=- */,
  /**
   * �ƶ�Ӧ��-���۲�ѯ
   */
  A03006("A03006", "���۲�ѯ")/* -=notranslate=- */,
  /**
   * �ƶ�Ӧ��-���۷���
   */
  A04002("A04002", "���۷���")/* -=notranslate=- */,
  /**
   * ���۶���
   */
  N40060301("40060301", "���۶���")/* -=notranslate=- */,
  /**
   * ������
   */
  N40060402("40060402", "������")/* -=notranslate=- */,

  /**
   * ���۷�Ʊ
   */
  N40060501("40060501", "���۷�Ʊ")/* -=notranslate=- */,

  /**
   * Ԥ��������
   */
  N40060202("40060202", "Ԥ��������")/* -=notranslate=- */,
  /**
   * ��������
   */
  N40060401("40060401", "��������")/* -=notranslate=- */,
  /**
   * ��������
   */
  N40093010("40093010", "��������")/* -=notranslate=- */;

  private String funccode;

  private String funcname;

  private SOFunc(String funccode, String funcname) {
    this.funccode = funccode;
    this.funcname = funcname;
  }

  /**
   * ��ýڵ����
   * 
   * @return ���ܽڵ��
   */
  public String getCode() {
    return this.funccode;
  }

  /**
   * ��ýڵ�����
   * 
   * @return ���ܽڵ���������
   */
  public String getName() {
    return this.funcname;
  }
}
