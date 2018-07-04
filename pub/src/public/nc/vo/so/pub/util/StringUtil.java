package nc.vo.so.pub.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>�ַ�����ʵ�ò���
 * <li>
 * <li>
 * </ul>
 * 
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-31 ����10:54:29
 */
public class StringUtil {

  private StringUtil() {
    super();
  }
  
  /**
   * 
   * ��������������ȥ���ִ������е��ظ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param ss
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-13 ����06:32:45
   */
  public static String[] getDistinct(String[] ss) {
    if ((null == ss) || (0 == ss.length)) {
      return ss;
    }
    List<String> ssList = Arrays.asList(ss);
    Set<String> ssSet = new HashSet<String>(ssList);
    return ssSet.toArray(new String[ssSet.size()]);
  }

  /**
   * 
   * ���������������ж�һ������toStringȥ����ո���Ƿ�Ϊ�ա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param o
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-31 ����10:59:14
   */
  public static boolean isEmptyTrimSpace(Object o) {
    if (null == o) {
      return true;
    }
    return nc.vo.jcom.lang.StringUtil.isEmptyWithTrim(o.toString());
  }

}
