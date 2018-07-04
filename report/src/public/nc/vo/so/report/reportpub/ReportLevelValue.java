package nc.vo.so.report.reportpub;

/**
 * ������ö��
 * 
 * @since 6.3
 * @version 2013-03-01 16:05:17
 * @author ������
 */
public enum ReportLevelValue {

  /**
   * ĩ��
   */
  END(Integer.valueOf(0)),
  /**
   * һ��
   */
  FIRST(Integer.valueOf(1)),
  /**
   * ����
   */
  SECOND(Integer.valueOf(2)),
  /**
   * ����
   */
  THIRD(Integer.valueOf(3)),
  /**
   * �ļ�
   */
  FOUTH(Integer.valueOf(4)),
  /**
   * �弶
   */
  FIFTH(Integer.valueOf(5)),
  /**
   * ����
   */
  SIXTH(Integer.valueOf(6))

  ;

  private Integer level;

  private ReportLevelValue(Integer level) {
    this.level = level;
  }

  /**
   * 
   * @param otherlevel
   * @return b
   */
  public boolean equalsValue(Integer otherlevel) {
    return this.level.equals(otherlevel);
  }
}
