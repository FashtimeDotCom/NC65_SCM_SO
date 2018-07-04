package nc.vo.so.pub.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * 
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>���۶��������۷�Ʊ�����۴����㵥��Ԥ���������۵�����״̬ö����
 * </ul>
 * 
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-1-27 ����11:18:34
 */
public class BillStatus extends MDEnum {

  /**
   * ����ͨ��
   */
  public static final int I_AUDIT = 2;

  public static final BillStatus AUDIT = MDEnum.valueOf(BillStatus.class,
      I_AUDIT);

  /**
   * ������
   */
  public static final int I_AUDITING = 7;

  public static final BillStatus AUDITING = MDEnum.valueOf(BillStatus.class,
      I_AUDITING);

  /**
   * �ر�
   */
  public static final int I_CLOSED = 4;

  public static final BillStatus CLOSED = MDEnum.valueOf(BillStatus.class,
      I_CLOSED);

  /**
   * ����̬
   */
  public static final int I_FREE = 1;

  public static final BillStatus FREE = MDEnum
      .valueOf(BillStatus.class, I_FREE);

  /**
   * ����̬
   */
  public static final int I_FREEZE = 3;

  public static final BillStatus FREEZE = MDEnum.valueOf(BillStatus.class,
      I_FREEZE);

  /**
   * ����ʧЧ
   */
  public static final int I_INVALIDATE = 5;

  public static final BillStatus INVALIDATE = MDEnum.valueOf(BillStatus.class,
      I_INVALIDATE);

  /**
   * ������ͨ��
   */
  public static final int I_NOPASS = 8;

  public static final BillStatus NOPASS = MDEnum.valueOf(BillStatus.class,
      I_NOPASS);

  public BillStatus(IEnumValue enumvalue) {
    super(enumvalue);
  }

  public Integer getIntegerValue() {
    return Integer.valueOf(this.value().toString());
  }

  public int getIntValue() {
    return Integer.parseInt(this.value().toString());
  }

  public String getStringValue() {
    return this.value().toString();
  }

}
