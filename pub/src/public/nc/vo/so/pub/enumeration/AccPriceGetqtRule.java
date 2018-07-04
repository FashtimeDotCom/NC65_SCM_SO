package nc.vo.so.pub.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * �����˵���ȡ�۹���
 * ���۸�0/ѯ���ۼ�/�ο��ۼ�/����ۼ�/�ο��ɱ���
 * 
 * @version 6.0
 * @author ����
 */
public class AccPriceGetqtRule extends MDEnum {

  /** ѯ���ۼ� :�������������ı��۹������ȡ�� */
  public static final AccPriceGetqtRule ASK_SALEQT = MDEnum.valueOf(
      AccPriceGetqtRule.class, Integer.valueOf(2));

  /** �ο��ɱ� :ȡ���ϵ���������֯ҳǩ�ϵĲο��ɱ� */
  public static final AccPriceGetqtRule MARSSORG_COSETQT = MDEnum.valueOf(
      AccPriceGetqtRule.class, Integer.valueOf(5));

  /** ����ۼ� :ȡ���ϵ���������֯ҳǩ������ۼ� */
  public static final AccPriceGetqtRule MARSSORG_LOWQT = MDEnum.valueOf(
      AccPriceGetqtRule.class, Integer.valueOf(4));

  /** �ο��ۼ�:���ϵ���������֯ҳǩ�ϵĲο��ۼ� */
  public static final AccPriceGetqtRule MARSSORG_REQT = MDEnum.valueOf(
      AccPriceGetqtRule.class, Integer.valueOf(3));

  /** �۸�0 :��������Ʒ�����ϵļ۸�Ϊ0 */
  public static final AccPriceGetqtRule ZERO_QT = MDEnum.valueOf(
      AccPriceGetqtRule.class, Integer.valueOf(1));

  public AccPriceGetqtRule(IEnumValue enumValue) {
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
