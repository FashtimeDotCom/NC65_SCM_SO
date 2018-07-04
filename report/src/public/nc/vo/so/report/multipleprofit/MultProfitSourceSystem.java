package nc.vo.so.report.multipleprofit;

/**
 * �ۺ�ë��������Դϵͳ
 * 
 * @since 6.3
 * @version 2012-12-10 13:50:10
 * @author liangjm
 */
public enum MultProfitSourceSystem {
  /**
   * ��Դ����
   */
  RESOURCE_SALE(Integer.valueOf(0)),
  /**
   * ��Դ�ڲ�����
   */
  RESOURCE_INNER(Integer.valueOf(1)),
  /**
   * ��Դ�ڲ����׺�����
   */
  RESOURCE_SALE_INNER(Integer.valueOf(2));

  private Integer level;

  private MultProfitSourceSystem(Integer level) {
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

  /**
   * 
   * 
   * @return level
   */
  public Integer getResourceSystem() {
    return this.level;
  }

}
