package nc.vo.so.m30trantype.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class LargessDisType extends MDEnum {

  /** ͬ���Ϸ�̯ */
  public static final LargessDisType DISPARTBYINV = MDEnum.valueOf(
      LargessDisType.class, Integer.valueOf(2));

  /** ���������÷�̯ */
  public static final LargessDisType DISPARTBYLARGESS = MDEnum.valueOf(
      LargessDisType.class, Integer.valueOf(1));

  /** ������̯ */
  public static final LargessDisType DISPARTONE = MDEnum.valueOf(
      LargessDisType.class, Integer.valueOf(3));

  /** ����̯ */
  public static final LargessDisType NODISPART = MDEnum.valueOf(
      LargessDisType.class, Integer.valueOf(0));

  public LargessDisType(IEnumValue enumValue) {
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
