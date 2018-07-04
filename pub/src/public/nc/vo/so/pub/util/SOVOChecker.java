package nc.vo.so.pub.util;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;

public class SOVOChecker {

  /**
   * �������Ƿ�Ϊ�ա�
   * 
   * @return boolean ��������ֵΪnull������true��
   *         ���value������ΪString������value.length()Ϊ0������true��
   *         ���value������ΪObject[]������value.lengthΪ0������true��
   *         ���value������ΪCollection������value.size()Ϊ0������true��
   *         ���value������ΪMap,����value.size()Ϊ0������true��
   *         ���value������ΪDictionary������value.size()Ϊ0������true�� ���򷵻�false��
   * @param value
   *          �����ֵ��
   */
  @SuppressWarnings("rawtypes")
  public static boolean isEmpty(Object value) {
    if (value == null) {
      return true;
    }
    if (value instanceof String && ((String) value).trim().length() <= 0) {
      return true;
    }
    if (value instanceof Object[] && ((Object[]) value).length <= 0) {
      return true;
    }
    if (value instanceof Collection && ((Collection) value).size() <= 0) {
      return true;
    }
    if (value instanceof Map && ((Map) value).size() <= 0) {
      return true;
    }
    if (value instanceof Dictionary && ((Dictionary) value).size() <= 0) {
      return true;
    }
    return false;
  }

}
