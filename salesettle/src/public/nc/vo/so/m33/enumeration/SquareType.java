package nc.vo.so.m33.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class SquareType extends MDEnum {

  /**
   * ȷ��Ӧ��
   */
  public static final SquareType SQUARETYPE_AR = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(1));

  /**
   * �س�Ӧ��
   */
  public static final SquareType SQUARETYPE_ARRUSH = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(3));

  /**
   * ��Ӧ��
   */
  public static final SquareType SQUARETYPE_BALANCEAR = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(7));

  /**
   * �ݹ�Ӧ��
   */
  public static final SquareType SQUARETYPE_ET = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(2));

  /**
   * �ɱ�����
   */
  public static final SquareType SQUARETYPE_IA = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(4));

  /**
   * ��
   */
  public static final SquareType SQUARETYPE_NULL = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(0));

  /**
   * ����Գ�
   */
  public static final SquareType SQUARETYPE_OUTRUSH = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(6));

  /**
   * ���뷢����Ʒ����
   */
  public static final SquareType SQUARETYPE_REG_CREDIT = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(8));

  /**
   * ���뷢����Ʒ�跽
   */
  public static final SquareType SQUARETYPE_REG_DEBIT = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(5));

  public SquareType(IEnumValue enumValue) {
    super(enumValue);
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
