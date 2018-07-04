package nc.ui.so.m30.billui.editor.bodyevent;

import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * ���۶������������ֶα༭�¼�
 * 
 * @since 6.3
 * @version 2012-12-20 13:29:04
 * @author liangjm
 */
public class ProductorEditHandler {

  /**
   * 
   * 
   * @param e
   * 
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int editrow = e.getRow();
    // �༭�������̺����ÿͻ�������(V63�¼�)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setCustMaterial(editrow);
  }
}
