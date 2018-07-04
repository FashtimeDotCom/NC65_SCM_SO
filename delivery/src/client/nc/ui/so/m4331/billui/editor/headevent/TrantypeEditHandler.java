package nc.ui.so.m4331.billui.editor.headevent;

import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * �������ͱ༭ǰ�¼�
 * 
 * @since 6.0
 * @version 2011-3-24 ����11:42:02
 * @author ף����
 */
public class TrantypeEditHandler {
  /**
   * �������ͱ༭ǰ�¼�
   * 
   * @param e
   */
  public void beforeTrantypeEdit(CardHeadTailBeforeEditEvent e) {
    CardKeyValue keyValue = new CardKeyValue(e.getBillCardPanel());
    String pk_org = keyValue.getHeadStringValue(DeliveryHVO.PK_ORG);
    BillItem trantypeItem =
        e.getBillCardPanel().getHeadItem(DeliveryHVO.CTRANTYPEID);
    FilterTransTypeRefUtils filter =
        new FilterTransTypeRefUtils(trantypeItem, pk_org);
    filter.filterTranType(new String[] {
      SOBillType.Delivery.getCode()
    });
  }
}
