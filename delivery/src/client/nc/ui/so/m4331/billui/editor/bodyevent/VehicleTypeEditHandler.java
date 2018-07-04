package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * ���ͱ༭�¼�
 * 
 * @since 6.0
 * @version 2011-3-24 ����02:45:08
 * @author ף����
 */
public class VehicleTypeEditHandler {
  /**
   * ���ͱ༭ǰ�¼�
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(DeliveryHVO.PK_ORG);
    BillItem item = cardPanel.getBodyItem(DeliveryBVO.CVEHICLETYPEID);
    UIRefPane pane = (UIRefPane) item.getComponent();
    pane.setPk_org(pk_org);
  }
}
