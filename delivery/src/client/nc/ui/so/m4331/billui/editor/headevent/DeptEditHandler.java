package nc.ui.so.m4331.billui.editor.headevent;

import nc.vo.so.m4331.entity.DeliveryHVO;

import nc.ui.bd.ref.RefInitializeCondition;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * ���ű༭�¼�
 * 
 * @since 6.0
 * @version 2011-3-24 ����11:34:44
 * @author ף����
 */
public class DeptEditHandler {

  /**
   * 
   * ���ű༭ǰ
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(DeliveryHVO.PK_ORG);
    BillItem item = cardPanel.getHeadItem(e.getKey());
    UIRefPane panel = (UIRefPane) item.getComponent();
    FilterDeptRefUtils filter = FilterDeptRefUtils
    .createFilterDeptRefUtilsOfTR(panel);
    filter.filterItemRefByOrg(pk_org);
  }

}
