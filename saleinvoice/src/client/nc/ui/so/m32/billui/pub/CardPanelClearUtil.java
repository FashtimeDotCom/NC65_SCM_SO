package nc.ui.so.m32.billui.pub;

import nc.vo.so.m32.entity.SaleInvoiceBVO;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * ��Ƭ�������
 * 
 * @since 6.3
 * @version 2012-12-21 ����01:01:34
 * @author yaogj
 */
public class CardPanelClearUtil {

  private BillCardPanel cardPanel;

  /**
   * CardPanelClearUtil �Ĺ�����
   * 
   * @param cardPanel
   */
  public CardPanelClearUtil(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  /**
   * �������������������Ƭ�����ͷ�������ݡ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author ��ӱ�
   * @time 2010-4-26 ����04:52:41
   */
  public void clearValue() {

    CardKeyValue keyValue = new CardKeyValue(this.cardPanel);
    // ��ձ�ͷ�ֶ�ֵ
    BillItem[] headItems = this.cardPanel.getHeadItems();
    for (BillItem item : headItems) {
      item.setValue(item.getDefaultValue());
    }

    // ��ձ����ֶ�ֵ
    BillItem[] bodyItems = this.cardPanel.getBodyItems();
    for (BillItem bodyitem : bodyItems) {
      // �����кŲ�Ӧ���
      if (SaleInvoiceBVO.CROWNO.equals(bodyitem.getKey())) {
        continue;
      }
      int rowcount = this.cardPanel.getRowCount();
      for (int i = 0; i < rowcount; i++) {

        keyValue.setBodyValue(i, bodyitem.getKey(), bodyitem.getDefaultValue());

      }
    }

    BillItem[] tailItems = this.cardPanel.getTailItems();
    for (BillItem item : tailItems) {

      item.setValue(item.getDefaultValue());

    }

  }
}
