package nc.vo.so.mbuylargess.entity;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b> �ڴ˴���Ҫ��������Ĺ��� </b>
 * <p>
 * �ڴ˴���Ӵ����������Ϣ
 * </p>
 * ��������:${vmObject.createdDate}
 * 
 * @author
 * @version NCPrj ??
 */
public class Toplimittype extends MDEnum {
  // ����
  public static final Toplimittype LIMIT_NUM = MDEnum.valueOf(
      Toplimittype.class, Integer.valueOf(0));

  // ������
  public static final Toplimittype LIMIT_NO = MDEnum.valueOf(
      Toplimittype.class, Integer.valueOf(2));

  // ���
  public static final Toplimittype LIMIT_MNY = MDEnum.valueOf(
      Toplimittype.class, Integer.valueOf(1));

  public Toplimittype(
      IEnumValue enumvalue) {
    super(enumvalue);
  }

}
