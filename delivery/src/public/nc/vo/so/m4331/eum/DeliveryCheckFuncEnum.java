package nc.vo.so.m4331.eum;

/**
 * 
 * @since 6.0
 * @version 2011-2-25 ����01:48:35
 * @author ף����
 */
public enum DeliveryCheckFuncEnum {

  // ���������棬�������μ��
  DeliverySave("examBatchInv");
  private String func;

  private DeliveryCheckFuncEnum(String func1) {
    this.func = func1;
  }

  public String getValue() {
    return this.func;
  }

}
