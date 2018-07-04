package nc.ui.so.m30.revise.rule;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.EditableAndRewiteItems;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;

/**
 * ���۶����޶��ֶ��Ƿ��ܱ༭������
 * 
 * @since 6.0
 * @version 2011-3-8 ����10:38:44
 * @author ��־ΰ
 */
public class IsEditableCheckRule {

  private CardKeyValue keyValue;

  /**
   * @param BillCardPanel
   * @param row ��ͷ��-1
   * @param key ��ǰ�༭itemKey
   */
  public boolean check(BillCardPanel cardPanel, int row, String itemKey) {
    this.keyValue = new CardKeyValue(cardPanel);

    // 1.����������
    RowStatus rowStatus = this.keyValue.getRowStatus(row);
    if (RowStatus.NEW == rowStatus) {
      return true;
    }
    // 2.�޶���
    boolean isEditable = false;
    // 3.�����ֶ��Ƿ���޶��ж�
    boolean isRevise = false;
    if (row > -1) {
      isRevise = cardPanel.getBodyItem(itemKey).isM_bReviseFlag();
    }
    else {
      isRevise = cardPanel.getHeadTailItem(itemKey).isM_bReviseFlag();
    }
    if (!isRevise) {
      return isRevise;
    }
    // 4.�����ֶ��Ƿ�����ڿɱ༭�ֶ����ж�
    
    // if (isEditable) {
    // // ����2
    // }
    // if (isEditable) {
    // // ����3
    // }
    //���ݰ�������Ҫ�� �����Ա༭2018-04-02
    return true;
  }

  /**
   * �Ƿ������޶��Ŀ��Ա༭�ֶ�
   * 
   * @param itemKey
   * @param row
   * @return
   */
  private boolean isEditableItem(String itemKey, int row) {
    if (isOut(itemKey, row)) {
      return checkIsEditableForOut(itemKey, row);
    }
    else {
      return checkIsEditable(itemKey);
    }
  }

  /**
   * δ�������η������ͳ��ⵥ�ĵ��ݣ��ж��ֶ��Ƿ���޶�
   * 
   * @param itemKey
   * @return
   */
  private boolean checkIsEditable(String itemKey) {
    int bodylength = EditableAndRewiteItems.BODYEDITABLEITEMKEY.length;
    int headlengh = EditableAndRewiteItems.HEADEDITABLEITEMKEY.length;
    for (int i = 0; i < bodylength; i++) {
      if (i < headlengh) {
        if (EditableAndRewiteItems.HEADEDITABLEITEMKEY[i].equals(itemKey)) {
          return true;
        }
      }
      if (EditableAndRewiteItems.BODYEDITABLEITEMKEY[i].equals(itemKey)) {
        return true;
      }
    }
    return false;
  }

  /**
   * ���������η������ͳ��ⵥ�ĵ��ݣ��ж��ֶ��Ƿ���޶�
   * 
   * @param itemKey
   * @return
   */
  private boolean checkIsEditableForOut(String itemKey, int row) {
    boolean isEditable = false;

    // �޶�����ʱ���������۶����Ľ����������Ծ����Ƿ���Ա༭��
    int priceLength = EditableAndRewiteItems.PRICE.length;
    for (int i = 0; i < priceLength; i++) {
      String key = EditableAndRewiteItems.PRICE[i];
      if (key.equals(itemKey)) {
        return checkPriceIsEditable();
      }
    }
    int reviseForOutLength =
        EditableAndRewiteItems.EDITABLEITEMKEYFOROUT.length;
    for (int i = 0; i < reviseForOutLength; i++) {
      String key = EditableAndRewiteItems.EDITABLEITEMKEYFOROUT[i];
      if (key.equals(itemKey)) {
        isEditable = true;
      }
    }
    return isEditable;
  }

  /**
   * �޶�����ʱ���������۶����Ľ����������Ծ����Ƿ���Ա༭��
   * ����δѯ���۸��Ƿ�ɸġ�ѯ���۸��Ƿ�ɸĽ����ж�
   * 
   * @return
   */
  private boolean checkPriceIsEditable() {
    // ����ȡ��������vo
    M30TranTypeVO vo = queryM30TrantypeVO();
    if (vo != null) {
      // ѯ���۸��Ƿ�ɸ�
      if (UFBoolean.TRUE == vo.getBmodifyaskedqt()) {
        return true;
      }
      // δѯ���۸��Ƿ�ɸ�
      if (UFBoolean.TRUE == vo.getBmodifyunaskedqt()) {
        return true;
      }
    }
    return false;
  }

  /**
   * ��ѯ���۶�����������vo
   * 
   * @return
   */
  private M30TranTypeVO queryM30TrantypeVO() {

    M30TranTypeVO trantype = null;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    String ctrantypeid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    try {
      trantype = service.queryTranTypeVO(ctrantypeid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return trantype;
  }

  /**
   * �Ƿ��Ѿ��������е���
   * 
   * @param itemKey
   * @param row
   * @return
   */
  private boolean isOut(String itemKey, int row) {
    UFDouble value = null;
    int reviseForOutlength = EditableAndRewiteItems.TOTALNUMKEY.length;
    for (int i = 0; i < reviseForOutlength; i++) {
      String key = EditableAndRewiteItems.TOTALNUMKEY[i];
      value = this.keyValue.getBodyUFDoubleValue(row, key);
      if (MathTool.absCompareTo(value, UFDouble.ZERO_DBL) > 0) {
        // �Ѿ��������ε���
        return true;
      }
    }
    return false;
  }

}
