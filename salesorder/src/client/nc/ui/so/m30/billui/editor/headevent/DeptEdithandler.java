package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;


/**
 * ���ű༭�¼�
 * 
 * @since 6.36
 * @version 2015-2-6 ����2:18:12
 * @author ����
 */
public class DeptEdithandler {
  
  /**
   * ���ű༭ǰ�¼�
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);

    BillItem item = cardPanel.getHeadItem(SaleOrderHVO.CDEPTVID);
    UIRefPane refPane = (UIRefPane) item.getComponent();
    FilterDeptRefUtils  filter = FilterDeptRefUtils
    .createFilterDeptRefUtilsOfSO(refPane);
    filter.filterItemRefByOrg(pk_org);
  }

}
