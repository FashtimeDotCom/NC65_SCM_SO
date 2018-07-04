package nc.vo.so.mreturnreason.entity;

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
public class ReturnReasonType extends MDEnum {
  // �²�Ʒ�˻�
  public static final ReturnReasonType RETURN_NEWPRODUCT = MDEnum.valueOf(
      ReturnReasonType.class, Integer.valueOf(2));

  // �����˻�
  public static final ReturnReasonType RETURN_OTHER = MDEnum.valueOf(
      ReturnReasonType.class, Integer.valueOf(4));

  // �����˻�
  public static final ReturnReasonType RETURN_QUALITY = MDEnum.valueOf(
      ReturnReasonType.class, Integer.valueOf(1));

  // ����������˻�
  public static final ReturnReasonType RETURN_QUOTA = MDEnum.valueOf(
      ReturnReasonType.class, Integer.valueOf(3));

  public ReturnReasonType(IEnumValue enumvalue) {
    super(enumvalue);
  }

}
