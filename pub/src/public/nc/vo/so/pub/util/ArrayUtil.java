package nc.vo.so.pub.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>���һЩ����ʵ�ô���
 * <li>�п�
 * <li>
 * </ul>
 * 
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-29 ����09:59:31
 */
public class ArrayUtil {

  private ArrayUtil() {
    super();
  }

  /**
   * 
   * �������������������ͬ��������ϲ�Ϊһ�������顣
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param ObjArrays
   * @return �����飬����Ϊnull���㳤
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-29 ����03:38:41
   */
  @SuppressWarnings("unchecked")
  public static <E> E[] combinArrays(E[]... objArrays) {
    if (objArrays.length == 0) {
      return null;
    }

    List<E> alCombinResult = new ArrayList<E>();
    E[] firstNotNull = null;
    for (int i = 0; i < objArrays.length; i++) {
      if (ArrayUtils.isEmpty(objArrays[i])) {
        continue;
      }
      if (null == firstNotNull) {
        firstNotNull = objArrays[i];
      }
      for (int j = 0; j < objArrays[i].length; j++) {
        alCombinResult.add(objArrays[i][j]);
      }
    }

    if (null != firstNotNull) {
      firstNotNull =
          alCombinResult.toArray((E[]) Array.newInstance(firstNotNull
              .getClass().getComponentType(), alCombinResult.size()));
    }
    return firstNotNull;

  }

  /**
   * ��һ����������ڶ��������ֵ
   * 
   * @param all
   * @param sub
   * @return
   */
  public static int[] subArrays(int[] all, int[] sub) {
    Set<Integer> subs = new HashSet<Integer>();
    Set<Integer> rets = new HashSet<Integer>();

    for (int i = 0; i < sub.length; i++) {
      subs.add(Integer.valueOf(sub[i]));
    }
    for (int i = 0; i < all.length; i++) {
      Integer r = Integer.valueOf(all[i]);
      if (subs.contains(r)) {
        continue;
      }
      rets.add(r);
    }

    int[] retrows = new int[rets.size()];
    int i = 0;
    for (Integer row : rets) {
      retrows[i] = row.intValue();
      i++;
    }
    return retrows;

  }

  /**
   * �ϲ�int������
   * 
   * @param objArrays ����
   * @return
   */
  public static int[] combinArrays(int[]... objArrays) {

    Set<Integer> needchangecountyrows = new HashSet<Integer>();

    for (int i = 0; i < objArrays.length; i++) {
      if (ArrayUtils.isEmpty(objArrays[i])) {
        continue;
      }
      for (int j = 0; j < objArrays[i].length; j++) {
        needchangecountyrows.add(Integer.valueOf(objArrays[i][j]));
      }
    }

    int[] needchangecountyrow = new int[needchangecountyrows.size()];
    int i = 0;
    for (Integer row : needchangecountyrows) {
      needchangecountyrow[i] = row.intValue();
      i++;
    }
    return needchangecountyrow;
  }

  /**
   * 
   * ���������������ж�һ�����������Ƿ�Ϊ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param array
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-9 ����07:42:19
   */
  public static boolean isEmpty(int[] array) {
    if (null == array || 0 == array.length) {
      return true;
    }

    return false;
  }

  /**
   * 
   * ���������������ж�һ�������Ƿ�Ϊ�ա�
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param array
   * @return ���null==array || array.length==0 ���ؿ�
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-29 ����10:02:31
   */
  public static boolean isEmpty(Object[] array) {
    return ArrayUtils.isEmpty(array);
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param values
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-14 ����11:32:58
   */
  public static int[] toPrimitive(Integer[] values) {
    if (ArrayUtil.isEmpty(values)) {
      return null;
    }
    return ArrayUtils.toPrimitive(values);
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param list
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-14 ����11:32:50
   */
  public static int[] toPrimitive(List<Integer> list) {
    if (ListUtil.isEmpty(list)) {
      return null;
    }
    Integer[] value = ListUtil.toArray(list);
    return ArrayUtil.toPrimitive(value);
  }
  
  public static boolean isEmptyOrNull(Object[] array) {
    if (isEmpty(array) || array[0] == null) {
      return true;
    }
    return false;
  }

}
