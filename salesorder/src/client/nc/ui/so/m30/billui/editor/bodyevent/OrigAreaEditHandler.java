package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCountryAreaRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ԭ�������༭�¼�
 * 1��ԭ������û������Ļ� ������༭
 * 2������ԭ����������������
 * 
 * @since 6.0
 * @version 2012-3-8 ����09:07:55
 * @author ô��
 */
public class OrigAreaEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    // ԭ����
    String origcountryid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CORIGCOUNTRYID);
    if (PubAppTool.isNull(origcountryid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    FilterCountryAreaRefUtils filter =
        new FilterCountryAreaRefUtils((UIRefPane) cardPanel.getBodyItem(
            SaleOrderBVO.CORIGAREAID).getComponent());
    filter.filterItemRefBy(origcountryid);

  }
}
