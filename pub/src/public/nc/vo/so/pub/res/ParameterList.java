package nc.vo.so.pub.res;

/**
 * ���۹���Ĳ����б�
 * 
 * @author ף����
 */
public enum ParameterList {
  SO01("SO01", "��������"), /* -=notranslate=- */
  SO02("SO02", "���������Ƿ�ͳһ����"), /* -=notranslate=- */
  SO03("SO03", "������ͷ��¼Ԥ�տ������Ԥ�տ�������ȹ���"), /* -=notranslate=- */
  SO04("SO04", "�����۶��������ݲ���Ʒ�ʽ"), /* -=notranslate=- */
  SO05("SO05", "�����������ݲ���Ʒ�ʽ"), /* -=notranslate=- */
  SO06("SO06", "�������������ݲ���Ʒ�ʽ"), /* -=notranslate=- */
  SO07("SO07", "���۶�����Ʊ���Ʒ�ʽ"), /* -=notranslate=- */
  SO08("SO08", "���۳��ⵥ��Ʊ���Ʒ�ʽ"), /* -=notranslate=- */
  SO10("SO10", "�����ر�ʱ�ö������������޶����ۺ�ִͬ������"), /* -=notranslate=- */
  SO11("SO11", "�����տ��������"), /* -=notranslate=- */
  SO12("SO12", "���۳��ⵥӦ�ս��������޸Ŀͻ��͵���"), /* -=notranslate=- */
  SO13("SO13", "����/ֱ�˰����ݲ����"), /* -=notranslate=- */
  SO14("SO14", "��Ʊ���Ӧ�յ��Զ����ó��"), /* -=notranslate=- */
  SO15("SO15", "��Ӧ�յ����ó��ʱ���Գ����Ʊ���ı���"), /* -=notranslate=- */
  SO16("SO16", "����ǩ��������Ʊ��ҵ������"), /* -=notranslate=- */
  SO17("SO17", "��Ʊ����"), /* -=notranslate=- */
  SO18("SO18", "���۶����ֵ���ӡ����"), /* -=notranslate=- */
  SO19("SO19", "�������ֵ���ӡ����"), /* -=notranslate=- */
  SO20("SO20", "��Ʒ�Ƿ�Ʊ"), /* -=notranslate=- */
  SO21("SO21", "����ѯ�۴�������"), /* -=notranslate=- */
  SO22("SO22", "�洢ѯ�۹�����ϸ"), /* -=notranslate=- */
  SO26("SO26", "��Ԥ�������Ŷ������Ʒ�ʽ"), /* -=notranslate=- */
  SO27("SO27", "��Ʊ����Ĭ����ʾ��ʽ"), /* -=notranslate=- */
  SO28("SO28", "��Ʊ���ܹ���"), /* -=notranslate=- */
  SO29("SO29", "��������"), /* -=notranslate=- */
  SO30("SO30", "������������ϸ��������"), /* -=notranslate=- */
  SO31("SO31", "���۷�Ʊ�кϲ�˰�����"),/* -=notranslate=- */
  SO32("SO32", "���۶����Ƿ�֧���������޸�"),/* -=notranslate=- */
  SO33("SO33", "���۷�Ʊ�Ƿ�֧���������޸�");/* -=notranslate=- */

  /**
   * #�ű�Ƿ�
   */
  public static final String BIGSPLITKEY = "#";

  /**
   * ���ű�Ƿ�
   */
  public static final String SPLITKEY = ",";

  /**
   * _V��Ƿ�
   */
  public static final String SUFFIX = "_V";

  /**
   * ��Ԫ����
   */
  public static final String DOLLER = "$";

  // ��������
  private String code;

  // ��������
  private String name;

  private ParameterList(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }
}
