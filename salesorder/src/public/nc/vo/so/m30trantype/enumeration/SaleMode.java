package nc.vo.so.m30trantype.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * ����ģʽ
 * ����ͨ/�˻�/�˻���/��ͨ+�˻�/��ͨ+�˻�����
 * 
 * @version 6.0
 * @author ��־ΰ
 */
public class SaleMode extends MDEnum {

  /** ��ͨ */
  public static final SaleMode MODE_COMMON = MDEnum.valueOf(SaleMode.class,
      Integer.valueOf(1));

  /** ��ͨ+�˻� */
  public static final SaleMode MODE_COMMONRETURN = MDEnum.valueOf(
      SaleMode.class, Integer.valueOf(4));

  /** ��ͨ+�˻��� */
  public static final SaleMode MODE_COMMONRETURNCHANGE = MDEnum.valueOf(
      SaleMode.class, Integer.valueOf(5));

  /** �˻� */
  public static final SaleMode MODE_RETURN = MDEnum.valueOf(SaleMode.class,
      Integer.valueOf(2));

  /** �˻��� */
  public static final SaleMode MODE_RETURNCHANGE = MDEnum.valueOf(
      SaleMode.class, Integer.valueOf(3));

  public SaleMode(IEnumValue enumValue) {
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
