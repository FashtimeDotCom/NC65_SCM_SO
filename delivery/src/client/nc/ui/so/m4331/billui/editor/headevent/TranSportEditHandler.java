package nc.ui.so.m4331.billui.editor.headevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * ����·�߱༭�¼�
 * 
 * @since 6.0
 * @version 2011-3-24 ����02:29:34
 * @author ף����
 */
public class TranSportEditHandler {
  /**
   * ����·�߱༭ǰ�����¼�
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    CardKeyValue keyValue = new CardKeyValue(e.getBillCardPanel());
    String pk_org = keyValue.getHeadStringValue(DeliveryHVO.PK_ORG);
    BillItem item =
        e.getBillCardPanel().getHeadItem(DeliveryHVO.CTRANSPORTROUTEID);
    UIRefPane pane = (UIRefPane) item.getComponent();
    pane.setPk_org(pk_org);
  }

}
