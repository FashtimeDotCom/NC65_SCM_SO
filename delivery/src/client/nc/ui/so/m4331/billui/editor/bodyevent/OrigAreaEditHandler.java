package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCountryAreaRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ԭ�������༭�¼�
 * 1��ԭ������û������Ļ� ������༭
 * 2������ԭ����������������
 * 
 * @since 6.0
 * @version 2012-3-21 ����11:06:20
 * @author ������
 */
public class OrigAreaEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    // ԭ����
    String origcountryid =
        keyValue.getBodyStringValue(row, DeliveryBVO.CORIGCOUNTRYID);
    if (PubAppTool.isNull(origcountryid)) {
      e.setReturnValue(false);
      return;
    }

    FilterCountryAreaRefUtils filter =
        new FilterCountryAreaRefUtils((UIRefPane) cardPanel.getBodyItem(
            DeliveryBVO.CORIGAREAID).getComponent());
    filter.filterItemRefBy(origcountryid);

  }
}
