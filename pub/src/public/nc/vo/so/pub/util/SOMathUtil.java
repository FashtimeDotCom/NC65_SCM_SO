package nc.vo.so.pub.util;

import nc.vo.pub.lang.UFDouble;

/**
 * 
 * @since 6.0
 * @version 2011-8-31 ����12:58:35
 * @author ô��
 */
public class SOMathUtil {

  /**
   * �ж�UFDouble �Ƿ�Ϊ0 nc.vo.pubapp.pattern.pub.MathTool.isZero(UFDouble d) �жϲ�׼ȷ
   * 
   * @param d
   * @return
   */
  public static boolean isZero(UFDouble d) {
    if (null == d) {
      return true;
    }
    if (d.compareTo(UFDouble.ZERO_DBL) == 0) {
      return true;
    }
    return false;
  }

}
