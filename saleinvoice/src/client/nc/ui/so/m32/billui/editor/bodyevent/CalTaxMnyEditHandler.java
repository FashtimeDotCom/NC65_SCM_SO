package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;

/**
 * ������༭�¼�
 * 
 * @since 6.0
 * @version 2012-2-17 ����04:02:28
 * @author ô��
 */
public class CalTaxMnyEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    SOBuysellTriaRule rule = new SOBuysellTriaRule(keyValue);
    boolean out = rule.isHeadBuysellFlagOut();

    if (out) {
      e.setReturnValue(Boolean.TRUE);
    }
    else {
      e.setReturnValue(Boolean.FALSE);
    }

  }

}
