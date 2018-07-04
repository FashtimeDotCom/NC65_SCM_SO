package nc.ui.so.m38.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;

/**
 * ��˰���༭ǰ�¼�
 * 
 * @since 6.1
 * @version 2012-2-20 ����10:03:21
 */
public class CalTaxMnyEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOBuysellTriaRule sbtr = new SOBuysellTriaRule(keyValue);
    // �������� -������༭;���ҵ��-����༭
    e.setReturnValue(sbtr.isBuysellFlagOut(e.getRow()));
  }
}
