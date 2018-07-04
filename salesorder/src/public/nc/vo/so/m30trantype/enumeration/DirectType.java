package nc.vo.so.m30trantype.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * ֱ�����ͱ��
 * ����ֱ��/ֱ�˲ɹ�/ֱ�˵�����
 * 
 * @version 6.0
 * @author ��־ΰ
 */
public class DirectType extends MDEnum {

  /** ��ֱ�� */
  public static final DirectType DIRECTTRAN_NO = MDEnum.valueOf(
      DirectType.class, Integer.valueOf(1));

  /** ֱ�˲ɹ� */
  public static final DirectType DIRECTTRAN_PO = MDEnum.valueOf(
      DirectType.class, Integer.valueOf(2));

  /** ֱ�˵��� */
  public static final DirectType DIRECTTRAN_TO = MDEnum.valueOf(
      DirectType.class, Integer.valueOf(3));

  public DirectType(IEnumValue enumValue) {
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
