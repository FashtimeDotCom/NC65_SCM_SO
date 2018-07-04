package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * 
 * ���֮������ֶα༭�Ա༭������
 * 
 * @since 6.0
 * @version 2011-5-20 ����11:06:45
 * @author ô��
 */
public class OffsetBodyEditHandler {

  /**
   * �����ֶεı༭������
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    UFBoolean bsubunitflag =
        keyValue.getHeadUFBooleanValue(SaleInvoiceHVO.BSUBUNITFLAG);
    if (bsubunitflag.booleanValue()) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

  }
}
