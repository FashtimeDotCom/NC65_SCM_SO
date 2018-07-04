package nc.vo.so.pub.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.logging.Logger;

/**
 * ����ۺ�VO������
 * 
 * @since 6.3
 * @version 2013-11-22 ����03:49:03
 * @author liujingn
 */
public class AggVOUtil {

  private AggVOUtil() {
    super();
  }

  /**
   * 
   * ��������������������VO���飬��ϳ�AGGVO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param <T>
   * @param headers ͷ
   * @param items����
   * @param billClass��aggvo����
   * @param headIdName��ͷ������������
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-17 ����02:24:05
   */
  public static <T extends AggregatedValueObject> T[] createAggVO(
      CircularlyAccessibleValueObject[] headers,
      CircularlyAccessibleValueObject[] items, Class<T> billClass,
      String headIdName) {
    Map<String, T> newBillMap = new HashMap<String, T>();
    Set<String> dealtIds = new HashSet<String>();
    try {
      for (CircularlyAccessibleValueObject head : headers) {
        String hid = head.getPrimaryKey();
        if (newBillMap.containsKey(hid)) {
          continue;
        }

        // ���µ�BILLVO
        T newBill = Constructor.construct(billClass);
        newBill.setParentVO(head);
        newBillMap.put(hid, newBill);
        if (null == items) {
          continue;
        }
        List<CircularlyAccessibleValueObject> children =
            new ArrayList<CircularlyAccessibleValueObject>();
        // �ұ���VO
        for (CircularlyAccessibleValueObject item : items) {
          if (!dealtIds.contains(item.getPrimaryKey())
              && hid.equals(item.getAttributeValue(headIdName))) {
            children.add(item);
            dealtIds.add(item.getPrimaryKey());
          }
        }
        if (0 < children.size()) {
          CircularlyAccessibleValueObject[] childrenAry =
              new ListToArrayTool<CircularlyAccessibleValueObject>()
                  .convertToArray(children);
          newBill.setChildrenVO(childrenAry);
        }
      }
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }

    T[] bills = Constructor.construct(billClass, newBillMap.size());
    return newBillMap.values().toArray(bills);
  }

  /**
   * 
   * ���������������ɷ��ѵı�ͷ�ͱ������ɾۺ�VO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param headers
   * @param items
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-14 ����03:34:48
   */
  public static IBill[] createBillVO(ISuperVO[] headers, ISuperVO[] items,
      Class<? extends IBill> billClass) {
    Map<String, IBill> newBillMap = new HashMap<String, IBill>();
    IVOMeta childMeta = null;
    String headIdName =
        headers[0].getMetaData().getPrimaryAttribute().getName();
    Set<String> deltIds = new HashSet<String>();
    for (ISuperVO head : headers) {
      String hid = head.getPrimaryKey();
      if (newBillMap.containsKey(hid)) {
        continue;
      }

      // ���µ�BILLVO
      IBill newBill = Constructor.construct(billClass);
      newBill.setParent(head);
      List<ISuperVO> children = new ArrayList<ISuperVO>();
      childMeta =
          childMeta == null ? newBill.getMetaData().getChildren()[0]
              : childMeta;
      // �ұ���VO
      for (ISuperVO item : items) {
        if (!deltIds.contains(item.getPrimaryKey())
            && hid.equals(item.getAttributeValue(headIdName))) {
          children.add(item);
          deltIds.add(item.getPrimaryKey());
        }
      }
      if (0 < children.size()) {
        ISuperVO[] childrenAry =
            new ListToArrayTool<ISuperVO>().convertToArray(children);
        newBill.setChildren(childMeta, childrenAry);
      }
      newBillMap.put(hid, newBill);
    }

    IBill[] bills = Constructor.construct(billClass, newBillMap.size());
    return newBillMap.values().toArray(bills);
  }

  /**
   * 
   * �����������������ۺ�VO�ı������ɣ۱�������������ݵ�Map��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-31 ����03:15:17
   */
  @SuppressWarnings("unchecked")
  public static <E extends CircularlyAccessibleValueObject> Map<String, E> createItemMap(
      AggregatedValueObject[] vos) {
    if (ArrayUtil.isEmpty(vos)) {
      return null;
    }
    Map<String, E> retMap = new HashMap<String, E>();
    for (AggregatedValueObject vo : vos) {
      if (null == vo || ArrayUtil.isEmpty(vo.getChildrenVO())) {
        continue;
      }
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        if (null == item) {
          continue;
        }
        String bid = null;
        try {
          bid = item.getPrimaryKey();
        }
        catch (BusinessException e) {
          Logger.error("Find the primary key error:" + e.getMessage());
        }
        if (!StringUtil.isEmptyTrimSpace(bid)) {
          retMap.put(bid, (E) item);
        }
      }
    }
    return retMap;
  }

  /**
   * 
   * �����������������ۺ�VO����[��ͷ������VO]��Map
   * <p>
   * <b>����˵��</b>
   * 
   * @param <E>
   * @param vos
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-6 ����10:02:57
   */
  @SuppressWarnings("unchecked")
  public static <E extends AggregatedValueObject> Map<String, E> createVOMap(
      AggregatedValueObject[] vos) {
    if (ArrayUtil.isEmpty(vos)) {
      return null;
    }

    Map<String, E> retMap = new HashMap<String, E>();
    for (AggregatedValueObject vo : vos) {
      if (null == vo || null == vo.getParentVO()) {
        continue;
      }

      String id = null;
      try {
        id = vo.getParentVO().getPrimaryKey();
      }
      catch (BusinessException e) {
        Logger.error("Find the primary key error:" + e.getMessage());
      }

      if (!StringUtil.isEmptyTrimSpace(id)) {
        retMap.put(id, (E) vo);
      }
    }

    return retMap;
  }

  /**
   * 
   * ����������������ȡȫvo�е�itemvo�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param <T>
   * @param VOs
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-11 ����01:54:44
   */
  @SuppressWarnings("unchecked")
  public static <T extends CircularlyAccessibleValueObject> T[] getCombinItemVOs(
      AggregatedValueObject[] vOs) {
    if (ArrayUtil.isEmpty(vOs)) {
      return null;
    }
    T[] itemVOs = null;
    for (AggregatedValueObject vo : vOs) {
      itemVOs = ArrayUtil.combinArrays(itemVOs, (T[]) vo.getChildrenVO());
    }
    return itemVOs;
  }

  @SuppressWarnings("unchecked")
  public static <T extends CircularlyAccessibleValueObject> T[] getCombinItemVOs(
      AggregatedValueObject[] vOs, T[] itemVOs) {
    if (ArrayUtil.isEmpty(vOs) && ArrayUtil.isEmpty(itemVOs)) {
      return null;
    }
    T[] resutlItemVOs =
        (T[]) ArrayUtil.combinArrays(itemVOs, AggVOUtil.getCombinItemVOs(vOs));

    return resutlItemVOs;
  }

  /**
   * 
   * ���������������õ�VO�����а�ĳ����õ���ֵ����
   * ���ص������в���NULL��մ����������ظ�ֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaPlan VO����
   * @param sField ������
   * @param sFieldClass ������
   * @return
   *         <p>
   * @since 6.0
   * @author ��ӡ��
   * @time 2010-4-13 ����03:39:53
   */
  @SuppressWarnings("unchecked")
  public static <T extends Object> T[] getDistinctFieldArray(
      CircularlyAccessibleValueObject[] voaPlan, String sField,
      Class<T> sFieldClass) {
    if (null == voaPlan || null == sField) {
      return null;
    }

    Set<T> mapValue = new HashSet<T>();
    T oValue = null;
    int iLen = voaPlan.length;
    for (int j = 0; j < iLen; j++) {

      oValue = (T) voaPlan[j].getAttributeValue(sField);

      // �����ռ��ظ���
      if (!VOChecker.isEmpty(oValue) && !mapValue.contains(oValue)) {
        mapValue.add(oValue);
      }
    }

    iLen = mapValue.size();
    if (iLen == 0) {
      return null;
    }

    T[] oaRet = null;
    try {

      oaRet = Constructor.declareArray(sFieldClass, iLen);
      // oaRet = (T[])Array.newInstance( sFieldClass, iLen ) ;
    }
    catch (Exception e) {
      Logger.error("nc.vo.pu.pub.util.AggVOUtil." + "getDistinctFieldArray"
          + "(CircularlyAccessibleValueObject [], String, Class)����ת������");
      return null;
    }

    mapValue.toArray(oaRet);
    return oaRet;
  }

  /**
   * �õ�VO�����ͷ�а�ĳ�����ֵ����
   * ���ص������в���NULL��մ����������ظ�ֵ
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaCheck ���ڼ���VO����.ÿ��VO��ͷ����������޿�ֵ
   * @param sField ��
   * @param sFieldClass �������
   * @param bNullAndDuplicateIncluded �Ƿ������ֵ���ظ�ֵ
   * @return
   *         <p>
   * @author wangyf
   * @time 2009-6-29 ����03:26:59
   */
  public static <T extends Object> T[] getDistinctHeadFieldArray(
      AggregatedValueObject[] voaCheck, String sField, Class<T> sFieldClass) {

    return AggVOUtil.getFieldArray(voaCheck, true, sField, sFieldClass, false);

  }

  /**
   * �õ�VO��������а�ĳ�����ֵ����
   * ���ص������в���NULL��մ����������ظ�ֵ
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaCheck ���ڼ���VO����.ÿ��VO��ͷ����������޿�ֵ
   * @param sField ��
   * @param sFieldClass �������
   * @param bNullAndDuplicateIncluded �Ƿ������ֵ���ظ�ֵ
   * @return
   *         <p>
   * @author wangyf
   * @time 2009-6-29 ����03:26:59
   */
  public static <T extends Object> T[] getDistinctItemFieldArray(
      AggregatedValueObject[] voaCheck, String sField, Class<T> sFieldClass) {

    return AggVOUtil.getFieldArray(voaCheck, false, sField, sFieldClass, false);

  }

  /**
   * 
   * ���������������õ����ݵı�ͷ�������顣
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   * @return bills�ı�ͷ�������� ����Ϊnull
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-29 ����11:23:45
   */
  public static String[] getPrimaryKeys(IBill[] bills) {
    if (ArrayUtil.isEmpty(bills)) {
      return null;
    }
    List<String> ids = new ArrayList<String>();
    for (IBill bill : bills) {
      ids.add(bill.getPrimaryKey());
    }
    return ids.toArray(new String[ids.size()]);
  }

  /**
   * 
   * ��������������ɸѡ���ۺ�VO������Ϊ����״̬��VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   *         <p>
   * @since 6.0
   * @author linsf
   * @time 2010-2-24 ����04:16:20
   */
  public static IBill[] pickupInsertVO(IBill[] vos) {
    List<IBill> newVos = new ArrayList<IBill>();
    for (IBill vo : vos) {
      if (VOStatus.NEW == vo.getParent().getStatus()) {
        newVos.add(vo);
      }
    }
    if (0 == newVos.size()) {
      return null;
    }
    return new ListToArrayTool<IBill>().convertToArray(newVos);
  }

  /**
   * 
   * ��������������ɸѡ���ۺ�VO������Ϊ�޸�״̬��VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   *         <p>
   * @since 6.0
   * @author linsf
   * @time 2010-2-24 ����04:18:14
   */
  public static IBill[] pickupUpdateVO(IBill[] vos) {
    List<IBill> newVos = new ArrayList<IBill>();
    for (IBill vo : vos) {
      if (VOStatus.UPDATED == vo.getParent().getStatus()) {
        newVos.add(vo);
      }
    }
    if (0 == newVos.size()) {
      return null;
    }
    return new ListToArrayTool<IBill>().convertToArray(newVos);
  }

  /**
   * 
   * ������������������ָ��VO���飬���ղ��յ�VO����˳�����С�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos��Ҫ�����VO���� ����VO�����������ͬ���ȣ�����ͬ����
   * @param referVos�����յ�VO����
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-28 ����08:56:46
   */
  public static <T extends AggregatedValueObject> void reSortVO(T[] vos,
      T[] referVos) {
    Map<String, T> vomap = AggVOUtil.createVOMap(vos);
    for (int i = 0; i < referVos.length; i++) {
      String pk = null;
      try {
        pk = referVos[i].getParentVO().getPrimaryKey();
      }
      catch (BusinessException e) {
        Logger.error("Find the primary key error:" + e.getMessage());

      }
      vos[i] = vomap.get(pk);
    }
  }

  /**
   * �õ�VO�����а�ĳ����õ���ֵ����
   * ���ص������в���NULL��մ����������ظ�ֵ
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaCheck ���ڼ���VO����.ÿ��VO��ͷ����������޿�ֵ
   * @param bHead ����Ǵ�ͷVO��ȡ���飬��true��������壬��false.
   * @param sField ��
   * @param sFieldClass �������
   * @param bNullAndDuplicateIncluded �Ƿ������ֵ���ظ�ֵ
   * @return
   *         <p>
   * @author wangyf
   * @time 2009-6-29 ����03:26:59
   */
  @SuppressWarnings("unchecked")
  private static <T extends Object> T[] getFieldArray(
      AggregatedValueObject[] voaCheck, boolean bHead, String sField,
      Class<T> sFieldClass, boolean bNullAndDuplicateIncluded) {
    if (voaCheck == null || sField == null) {
      return null;
    }
    int iLen = voaCheck.length;
    Object oValue = null;
    CircularlyAccessibleValueObject[] voaItem = null;
    int iBodyLen = 0;
    List<Object> listValue = new ArrayList<Object>();
    for (int i = 0; i < iLen; i++) {
      if (voaCheck[i] == null) {
        continue;
      }
      if (bHead) {

        oValue = voaCheck[i].getParentVO().getAttributeValue(sField);

        if (bNullAndDuplicateIncluded) {
          listValue.add(oValue);
        }
        else {
          // ���ռ��ظ���
          if (!StringUtil.isEmptyTrimSpace(oValue)
              && !listValue.contains(oValue)) {
            listValue.add(oValue);
          }
        }
      }
      else {
        voaItem = voaCheck[i].getChildrenVO();
        if (voaItem != null) {
          iBodyLen = voaItem.length;
          for (int j = 0; j < iBodyLen; j++) {
            oValue = voaItem[j].getAttributeValue(sField);
            if (bNullAndDuplicateIncluded) {
              listValue.add(oValue);
            }
            else {
              // ���ռ��ظ���
              if (!StringUtil.isEmptyTrimSpace(oValue)
                  && !listValue.contains(oValue)) {
                listValue.add(oValue);
              }
            }
          }
        }
      }

    }
    iLen = listValue.size();
    if (iLen == 0) {
      return null;
    }

    T[] oaRet = null;
    try {
      oaRet = (T[]) Array.newInstance(sFieldClass, iLen);
    }
    catch (Exception e) {
      Logger.error("nc.vo.po.pub.OrderPubVO.getAllFieldArray(OrderVO [], "
          + "Class, String, Class, boolean)����ת������");
      return null;
    }
    listValue.toArray(oaRet);
    return oaRet;

  }

  /**
   * ���������ͬ���ͣ����ݲ�ͬ��VO���ϲ�����
   * 
   * ��Ŀ��VO��������䵽��ԴVO�У������ԴVO���ֶ�ֵΪ�������,�������
   * 
   * @param srcvos ��ԴVO
   * @param destvos Ŀ��VO
   * @return �ϲ���VO
   */
  public static AggregatedValueObject[] combinBillVO(
      AggregatedValueObject[] srcvos, AggregatedValueObject[] destvos) {
    int j = 0;
    for (AggregatedValueObject srcvo : srcvos) {
      String[] headnames = srcvo.getParentVO().getAttributeNames();
      CircularlyAccessibleValueObject srcheadvo = srcvo.getParentVO();
      CircularlyAccessibleValueObject destheadvo = destvos[j].getParentVO();
      for (String headname : headnames) {
        if (srcheadvo.getAttributeValue(headname) == null) {
          srcheadvo.setAttributeValue(headname,
              destheadvo.getAttributeValue(headname));
        }
      }
      CircularlyAccessibleValueObject[] srcbodyvos = srcvo.getChildrenVO();
      CircularlyAccessibleValueObject[] destbodyvos =
          destvos[j].getChildrenVO();
      if (srcbodyvos == null || srcbodyvos.length == 0) {
        return srcvos;
      }
      int i = 0;
      for (CircularlyAccessibleValueObject srcbodyvo : srcbodyvos) {
        String[] srcbodynames = srcbodyvo.getAttributeNames();
        for (String srcbodyname : srcbodynames) {
          if (srcbodyvo.getAttributeValue(srcbodyname) == null) {
            srcbodyvo.setAttributeValue(srcbodyname,
                destbodyvos[i].getAttributeValue(srcbodyname));
          }
        }
        i++;
      }
      j++;
    }
    return srcvos;
  }

}
