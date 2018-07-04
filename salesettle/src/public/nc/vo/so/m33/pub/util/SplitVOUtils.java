package nc.vo.so.m33.pub.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SplitVOUtils<E extends CircularlyAccessibleValueObject> {

  /**
   * ���ݷֵ�������һ��CircularlyAccessibleValueObject���зֵ�
   * 
   * @param voaSource ��ֵ���VO����
   * @param saKey �ֵ�����KEY���飬��new String[]{"hid","bid"}
   * @return �����ֵ���VO����
   */
  @SuppressWarnings("unchecked")
  public E[][] getSplitVOs(E[] voaSource, String[] saKey) {
    if (saKey == null || saKey.length == 0) {
      return null;
    }

    // �ṹ��KEY �ֵ�������KEYֵ���
    // VALUE ArrayList ��[i]
    Map<String, List<E>> hmapRetVO = new HashMap<String, List<E>>();

    // �ֵ���KEY�ĸ���
    int iKeyNum = saKey.length;

    // ���ֵ�ֵ��ͬ�ķ���ͬһ��HASH��
    int iLen = voaSource.length;
    String sTempValue = null;
    for (int i = 0; i < iLen; i++) {
      if (voaSource[i] == null) {
        continue;
      }
      // ��ͷ��KEY
      StringBuilder sKey = new StringBuilder("");
      for (int j = 0; j < iKeyNum; j++) {
        sTempValue =
            ValueUtils.getString(voaSource[i].getAttributeValue(saKey[j]));
        sKey.append(sTempValue == null ? "NULL" : sTempValue);
      }

      // ���뵽HASH��
      if (!hmapRetVO.containsKey(sKey.toString())) {
        List<E> listItem = new ArrayList<E>();
        listItem.add(voaSource[i]);
        hmapRetVO.put(sKey.toString(), listItem);
      }
      else {
        List<E> listItem = hmapRetVO.get(sKey.toString());
        listItem.add(voaSource[i]);
      }
    }
    if (hmapRetVO.size() == 0) {
      return null;
    }

    // ��HASH��ȡ������VO
    E[][] voaRet = null;
    try {
      voaRet =
          (E[][]) Array.newInstance(voaSource.getClass(), hmapRetVO.size());

      Iterator<List<E>> iteror = hmapRetVO.values().iterator();
      int i = 0;
      while (iteror.hasNext()) {
        // ȡ��VEC
        List<E> listItem = iteror.next();
        // ����һ����VO
        voaRet[i] =
            (E[]) Array.newInstance(voaSource.getClass(), listItem.size());
        listItem.toArray(voaRet[i]);
        i++;
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return voaRet;
  }

}
