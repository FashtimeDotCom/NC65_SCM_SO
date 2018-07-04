package nc.vo.so.m32.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ�Գ��־ö����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-4-28 ����02:37:01
 */
public class OpposeFlag extends MDEnum {

  /**
   * ���Գ�
   */
  public static final OpposeFlag FINSH = MDEnum.valueOf(OpposeFlag.class,
      Integer.valueOf(1));

  /**
   * �Գ�����
   */
  public static final OpposeFlag GENERAL = MDEnum.valueOf(OpposeFlag.class,
      Integer.valueOf(2));

  /**
   * ����
   */
  public static final OpposeFlag NORMAL = MDEnum.valueOf(OpposeFlag.class,
      Integer.valueOf(0));

  public OpposeFlag(IEnumValue enumvalue) {
    super(enumvalue);
  }

}
