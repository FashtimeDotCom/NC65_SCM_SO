package nc.ui.so.m32.billui.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;


/**
 * @description
 * ���۷�Ʊ�޶��ֶ��Ƿ��ܱ༭������
 * 
 * @since 6.36
 * @version 2015-1-23 ����10:36:00
 * @author wangshu6
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
    // 2.�޸���
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
    isEditable = this.checkIsEditable(itemKey);

    // if (isEditable) {
    // // ����2
    // }
    // if (isEditable) {
    // // ����3
    // }
    return isEditable;
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


}
