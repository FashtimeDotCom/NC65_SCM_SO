package nc.vo.so.m30.util;

import java.util.HashMap;
import java.util.Map;

import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.rule.EditableAndRewiteItems;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ��������ѡ�乤����
 * @author zhangby5
 *
 */
public class FeatureSelectUtil {

  /**
   * ȡ���������е��ݴ�����ѡ��VO
   * @param keyValue ȡֵ����
   * @return <�кţ�ֵ>
   */
  public static Map<String, AggFFileVO> getAllRowAggFFileVO(IKeyValue keyValue) {
    int count = keyValue.getBodyCount();
    Map<String, AggFFileVO> aggffilevomap = new HashMap<>();
    for (int index = 0; index < count; index++) {
      if (null == keyValue.getBodyValue(index, SOConstant.AGGFFILEVO)) {
        continue;
      }
      aggffilevomap.put(
          keyValue.getBodyStringValue(index, SaleOrderBVO.CROWNO),
          (AggFFileVO) keyValue.getBodyValue(index, SOConstant.AGGFFILEVO));
    }
    return aggffilevomap;
  }
  
  /**
   * ��ձ�������ָ���ֶε�ֵ
   * @param keyValue ȡֵ����
   */
  public static void clearAllRowValue(IKeyValue keyValue,String sItemKey) {
    setAllRowsValue(keyValue, sItemKey, null);
  }
  
  /**
   * ���ָ���е�ָ���ֶε�ֵ
   * @param keyValue ȡֵ����
   * @param rows ָ����
   * @param sItemKey ָ���ֶ�
   */
  public static void clearRowsValue(IKeyValue keyValue,int[] rows,String sItemKey){
    setRowsValue(keyValue, rows, sItemKey, null);
  }
  
  /**
   * ���������е�ָ���ֶε�ֵ
   * @param keyValue
   * @param sItemKey
   * @param value
   */
  public static void setAllRowsValue(IKeyValue keyValue,String sItemKey,Object value){
    int count = keyValue.getBodyCount();
    for (int index = 0; index < count; index++) {
      keyValue.setBodyValue(index, sItemKey, null);
    }
  }
  
  /**
   * ����ָ���е�ָ���ֶε�ֵ
   * @param keyValue ȡֵ����
   * @param rows ָ����
   * @param sItemKey ָ���ֶ�
   * @param value Ҫ����ֵ
   */
  public static void setRowsValue(IKeyValue keyValue,int[] rows,String sItemKey,Object value){
    int length = rows.length; 
    for (int index = 0; index < length; index++) {
      keyValue.setBodyValue(rows[index], sItemKey, value);
    }
  }
  
  /**
   * �ж��Ƿ���������
   * ����ֻ�з�������������������ѡ�䣬����ĵ���(�ۼư���������������)����Ҫ�ж�
   * 
   * @param keyValue
   * @param row
   * @return
   */
  public static boolean isOut(IKeyValue keyValue,int row) {
    UFDouble value = null;
    int reviseForOutlength = EditableAndRewiteItems.TOTALNUMKEY.length;
    for (int i = 0; i < reviseForOutlength; i++) {
      String key = EditableAndRewiteItems.TOTALNUMKEY[i];
      value = keyValue.getBodyUFDoubleValue(row, key);
      if (MathTool.absCompareTo(value, UFDouble.ZERO_DBL) > 0) {
        if(key.equalsIgnoreCase(SaleOrderHistoryBVO.NARRANGEMONUM)){
          continue;
        }
        if(key.equalsIgnoreCase(SaleOrderHistoryBVO.NTOTALSENDNUM)){
          continue;
        }
        // �Ѿ��������ε���
        return true;
      }
    }
    return false;
  }
  
  /**
   * �ж��Ƿ���������
   * ����ֻ�з�������������������ѡ�䣬����ĵ���(�ۼư���������������)����Ҫ�ж�
   * 
   * @param keyValue
   * @param row
   * @return
   */
  public static boolean isOut(SaleOrderBVO bvo) {
    UFDouble value = null;
    int reviseForOutlength = EditableAndRewiteItems.TOTALNUMKEY.length;
    for (int i = 0; i < reviseForOutlength; i++) {
      String key = EditableAndRewiteItems.TOTALNUMKEY[i];
      value = (UFDouble) bvo.getAttributeValue(key);
      if (MathTool.absCompareTo(value, UFDouble.ZERO_DBL) > 0) {
        if(key.equalsIgnoreCase(SaleOrderHistoryBVO.NARRANGEMONUM)){
          continue;
        }
        if(key.equalsIgnoreCase(SaleOrderHistoryBVO.NTOTALSENDNUM)){
          continue;
        }
        // �Ѿ��������ε���
        return true;
      }
    }
    return false;
  }
  
  /**
   * ����ѡ��Ĵ�����̨��ȡǰ̨��������������ѡ���ݴ�VO
   * 
   * @param inCurVOs
   * @param userObj
   */
  public static void fillAggffileVO(SaleOrderVO[] inCurVOs, PfUserObject userObj) {
    if (userObj == null) {
      return;
    }
    Object saleUserObj = userObj.getUserObject();
    if (saleUserObj == null) {
      return;
    }
    Map<String, AggFFileVO> aggffilemapvo =
        ((SaleOrderUserObject) saleUserObj).getAggffilevomap();
    if (aggffilemapvo == null || aggffilemapvo.size() == 0) {
      return;
    }
    SaleOrderBVO[] bvos = inCurVOs[0].getChildrenVO();
    for (SaleOrderBVO bvo : bvos) {
      AggFFileVO aggffilevo = aggffilemapvo.get(bvo.getCrowno());
      if (aggffilevo == null) {
        continue;
      }
      bvo.setAggffilevo(aggffilevo);
    }
  }
  
}
