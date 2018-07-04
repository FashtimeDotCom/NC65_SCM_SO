package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * Ѻ��Ա�༭�¼�
 * 
 * @since 6.0
 * @version 2011-3-24 ����02:53:41
 * @author ף����
 */
public class SupercargoEditHandler {
  /**
   * Ѻ��Ա�༭ǰ�¼�
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(DeliveryHVO.PK_ORG);
    BillItem item = cardPanel.getBodyItem(DeliveryBVO.CSUPERCARGOID);
    FilterPsndocRefUtils utils = FilterPsndocRefUtils
        .createFilterPsndocRefUtilsOfTR((UIRefPane) item.getComponent());
    utils.filterItemRefByOrg(pk_org);
  }
}
