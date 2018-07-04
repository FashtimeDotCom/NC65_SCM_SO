package nc.ui.so.m30.billui.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;

/**
 * @description
 *  ���۶����������ֶ��Ƿ��ܱ༭������
 * @scene
 *  ���۶���������֧���޸� �༭ǰ����
 * @param
 * 
 *
 * @since 6.3
 * @version 2015-1-9 ����3:45:49
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
    if (row > -1) {
      isEditable = cardPanel.getBodyItem(itemKey).isM_bReviseFlag();
    }
    else {
      isEditable = cardPanel.getHeadTailItem(itemKey).isM_bReviseFlag();
    }
    return isEditable;
  }
}
