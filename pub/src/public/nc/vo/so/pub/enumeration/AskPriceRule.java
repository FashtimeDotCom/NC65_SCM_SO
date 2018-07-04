package nc.vo.so.pub.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * ѯ�۹���
 * ����ѯ��/����ѯ��/ѯ�Ǵ����ۣ�
 * 
 * @version 6.0
 * @author ��־ΰ
 */
public class AskPriceRule extends MDEnum {

  /** ��ѯ�� */
  public static final AskPriceRule ASKPRICE_NO = MDEnum.valueOf(
      AskPriceRule.class, Integer.valueOf(1));

  /** ����ѯ�� */
  public static final AskPriceRule ASKPRICE_NORMAL = MDEnum.valueOf(
      AskPriceRule.class, Integer.valueOf(2));

  /** ѯ�Ǵ����� */
  public static final AskPriceRule ASKPRICE_UNSPROMOTION = MDEnum.valueOf(
      AskPriceRule.class, Integer.valueOf(3));

  public AskPriceRule(IEnumValue enumValue) {
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
