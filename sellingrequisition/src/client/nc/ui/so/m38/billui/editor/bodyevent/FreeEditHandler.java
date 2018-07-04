package nc.ui.so.m38.billui.editor.bodyevent;

import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;

/**
 * Ԥ�������ɸ������Ա༭�¼�
 * 
 * @since 6.0
 * @version 2011-6-14 ����10:14:17
 * @author fengjb
 */
public class FreeEditHandler {

  /**
   * 
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    PreOrderFindPriceConfig config = new PreOrderFindPriceConfig(cardPanel);
    FindSalePrice findprice = new FindSalePrice(cardPanel, config);
    findprice.findPriceAfterEdit(rows, e.getKey());
    // �༭���ɸ������Ժ����ÿͻ�������(V63�¼�)
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setCustMaterial(rows);
  }

}
