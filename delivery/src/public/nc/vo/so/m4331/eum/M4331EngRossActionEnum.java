package nc.vo.so.m4331.eum;

/**
 * 
 * @since 6.0
 * @version 2011-2-25 ����01:49:13
 * @author ף����
 */
public enum M4331EngRossActionEnum {

  // ���������涯��
  M4331Write("WRITE");
  private String actionvalue;

  private M4331EngRossActionEnum(String actionvalue1) {
    this.actionvalue = actionvalue1;
  }

  public String getValue() {
    return this.actionvalue;
  }
}
