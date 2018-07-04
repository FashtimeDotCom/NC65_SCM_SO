package nc.vo.so.m32trantype.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * ������
 * 
 * @since 6.0
 * @version 2012-2-8 ����01:44:33
 * @author ô��
 */
public class Adjuster extends MDEnum {

  /**
   * ���ۿ�
   */
  public static final Adjuster ADJUSTDISCOUNT = MDEnum.valueOf(Adjuster.class,
      Integer.valueOf(0));

  /**
   * ������
   */
  public static final Adjuster ADJUSTPRICE = MDEnum.valueOf(Adjuster.class,
      Integer.valueOf(1));

  /**
   * 
   * Adjuster �Ĺ�����
   * 
   * @param enumvalue
   */
  public Adjuster(IEnumValue enumvalue) {
    super(enumvalue);
  }

}
