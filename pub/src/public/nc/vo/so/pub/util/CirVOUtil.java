package nc.vo.so.pub.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;

public class CirVOUtil {

  private CirVOUtil() {
    super();
  }

  public static <E extends CircularlyAccessibleValueObject> List<E> combineBill(
      E[] voa, E[] vob) {
    List<E> list = new ArrayList<E>();
    for (E vo : voa) {
      list.add(vo);
    }
    for (E vo : vob) {
      list.add(vo);
    }
    return list;
  }

  /**
   * ����������������ǰheadvo����Դ�ۺ�vo��headvo�Ƚϣ��жϷǿ��ֶ��Ƿ�һ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param VOs - �ۺ�vo
   * @param curHeadVO - ��ͷvo
   * @param itemKeys - �ǿ��ֶ�key
   * @param includeDate - �Ƿ�����������ֶ�
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-8 ����09:03:17
   */
  public static boolean existDifferNotNullItems(AggregatedValueObject[] vOs,
      CircularlyAccessibleValueObject curHeadVO, String[] itemKeys,
      boolean includeDate) {
    if (ArrayUtil.isEmpty(vOs) || (curHeadVO == null)
        || ArrayUtil.isEmpty(itemKeys)) {
      return false;
    }
    boolean exist = false;
    for (AggregatedValueObject vo : vOs) {
      exist =
          CirVOUtil.existDifferNotNullItems(vo.getParentVO(), curHeadVO,
              itemKeys, includeDate);
      if (exist) {
        exist = true;
      }
    }
    return exist;
  }

  /**
   * ����������������ǰheadvo����Դheadvo�Ƚϣ��жϷǿ��ֶ��Ƿ�һ��
   * Ŀǰ���ڷ�Ʊ��������ʱ��ı�ͷvo�Ƚ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param srcHeadVO ��Դheadervo
   * @param curHeadVO ��ǰheadervo
   * @param itemKeys - �ǿ��ֶ�key
   * @param includeDate - �Ƿ���������
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-8 ����01:26:40
   */
  public static boolean existDifferNotNullItems(
      CircularlyAccessibleValueObject srcHeadVO,
      CircularlyAccessibleValueObject curHeadVO, String[] itemKeys,
      boolean includeDate) {
    if ((srcHeadVO == null) || (curHeadVO == null)
        || ArrayUtil.isEmpty(itemKeys)) {
      return false;
    }

    boolean exist = false;
    for (String key : itemKeys) {
      if ((srcHeadVO.getAttributeValue(key) != null)
          && !srcHeadVO.getAttributeValue(key).equals(
              curHeadVO.getAttributeValue(key))) {
        if (!includeDate
            && (srcHeadVO.getAttributeValue(key) instanceof UFDate)) {
          continue;
        }
        exist = true;
      }
    }
    return exist;
  }

  /**
   * �õ�VO�����а�ĳ����õ���MAP,ȡMAP������KEY�õ����д�KEY��ֵ
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaPlan
   *          VO����
   * @param sField
   *          ������
   * @param T
   *          ������
   * @return ��ĳ����õ���MAP��HashMap<T,T>��TΪ���ֶε����͡�ʹ��ʱֻȡ��MAP��KEY���ɡ�
   *         <p>
   * @author wangyf
   * @param <E>
   * @time 2009-6-29 ����03:09:18
   */
  @SuppressWarnings("unchecked")
  public static <E> Set<E> getDistinctFieldSet(
      CircularlyAccessibleValueObject[] voaPlan, String sField) {

    if (voaPlan == null) {
      return null;
    }

    Set<E> hsetValue = new HashSet<E>();
    E oValue = null;
    int iLen = voaPlan.length;
    for (int j = 0; j < iLen; j++) {

      oValue = (E) voaPlan[j].getAttributeValue(sField);

      // �����ռ��ظ���
      if (!StringUtil.isEmptyTrimSpace(oValue) && !hsetValue.contains(oValue)) {
        hsetValue.add(oValue);
      }
    }

    iLen = hsetValue.size();
    if (iLen == 0) {
      hsetValue = null;
    }

    return hsetValue;

  }
}
