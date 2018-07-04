package nc.vo.so.pub.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * ��Ʒȡ�۹���
 * ���۸�0/ѯ���ۼ�/�ο��ۼ�/����ۼ�/�ο��ɱ���
 * 
 * @version 6.0
 * @author ��־ΰ
 */
public class LargessGetqtRule extends MDEnum {

  /** ѯ���ۼ� :�������������ı��۹������ȡ�� */
  public static final LargessGetqtRule ASK_SALEQT = MDEnum.valueOf(
      LargessGetqtRule.class, Integer.valueOf(2));

  /** �ο��ɱ� :ȡ���ϵ���������֯ҳǩ�ϵĲο��ɱ� */
  public static final LargessGetqtRule MARSSORG_COSETQT = MDEnum.valueOf(
      LargessGetqtRule.class, Integer.valueOf(5));

  /** ����ۼ� :ȡ���ϵ���������֯ҳǩ������ۼ� */
  public static final LargessGetqtRule MARSSORG_LOWQT = MDEnum.valueOf(
      LargessGetqtRule.class, Integer.valueOf(4));

  /** �ο��ۼ�:���ϵ���������֯ҳǩ�ϵĲο��ۼ� */
  public static final LargessGetqtRule MARSSORG_REQT = MDEnum.valueOf(
      LargessGetqtRule.class, Integer.valueOf(3));

  /** �۸�0 :��������Ʒ�����ϵļ۸�Ϊ0 */
  public static final LargessGetqtRule ZERO_QT = MDEnum.valueOf(
      LargessGetqtRule.class, Integer.valueOf(1));

  public LargessGetqtRule(IEnumValue enumValue) {
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
