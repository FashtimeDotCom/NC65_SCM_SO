/**
 * $$�ļ�˵��$$
 * 
 * @author chenyyb
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-07-08 08:46:49
 */
package nc.vo.so.salequotation.entity;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>
 * </ul>
 * 
 * <p>
 * ${tags}
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenyyb
 * @time 2010-07-08 08:46:49
 */
public class BillStatusEnum extends MDEnum {
  /**
   * ����ͨ��
   */
  public static final BillStatusEnum AUDIT = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_AUDIT));

  /**
   * ������
   */
  public static final BillStatusEnum AUDITING = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_AUDITING));

  /**
   * ����ͨ��
   */
  public static final int C_AUDIT = 1;

  /**
   * ������
   */
  public static final int C_AUDITING = 2;

  /**
   * �ѹر�
   */
  public static final int C_CLOSE = 4;

  /**
   * �ύ
   */
  public static final int C_COMMIT = 3;

  /**
   * ����
   */
  public static final int C_FREE = -1;

  /**
   * ��ʧЧ
   */
  public static final int C_INVALIDATE = 5;

  /**
   * ������ͨ��
   */
  public static final int C_NOPASS = 0;

  /**
   * �ѹر�
   */
  public static final BillStatusEnum CLOSE = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_CLOSE));

  /**
   * �ύ
   */
  public static final BillStatusEnum COMMIT = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_COMMIT));

  /**
   * ����
   */
  public static final BillStatusEnum FREE = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_FREE));

  /**
   * ��ʧЧ
   */
  public static final BillStatusEnum INVALIDATE = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_INVALIDATE));

  /**
   * ������ͨ��
   */
  public static final BillStatusEnum NOPASS = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_NOPASS));

  public BillStatusEnum(IEnumValue enumvalue) {
    super(enumvalue);
  }

}
