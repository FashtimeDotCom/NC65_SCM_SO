package nc.vo.so.m30.balend.enumeration;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۶�������ر�������㵥������
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-7-13 ����06:58:41
 */
public enum BalBillType {

  // ���ⵥ����Ʊ������ɱ�����
  BOTHCOST("bothcost"),

  // ���ⵥ����Ʊ������Ӧ�ս���
  BOTHINCOME("bothincome"),

  // ���۳��ⵥ��Ʊ�����������
  NONEBAL("nonebal"),

  // ֻ�г��ⵥ����ɱ�����
  OUTCOST("outcost"),

  // ֻ�г��ⵥ����Ӧ�ս���
  OUTINCOME("outincome"),

  // ֻ�з�Ʊ�����óɱ�����
  VOICECOST("voicecost"),

  // ֻ�з�Ʊ������Ӧ�ս���
  VOICEINCOME("voiceincome"),
  // ���ⵥ�ֹ�����ʱ��ֻ���ķ�Ʊ����ɱ�����

  OnlyVOICECOST("onlyvoicecost"),
  // ���ⵥ�ֹ�����ʱ��ֻ���ķ�Ʊ�����������

  OnlyVOICEINCOME("onlyvoiceincome");

  private String code;

  private BalBillType(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }

  public boolean equalsValue(BalBillType otherbaltype) {
    if (null == otherbaltype) {
      return false;
    }
    return this.getCode().equals(otherbaltype.getCode());
  }
}
