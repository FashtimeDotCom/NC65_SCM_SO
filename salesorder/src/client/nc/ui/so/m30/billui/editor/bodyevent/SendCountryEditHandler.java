package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;

/**
 * �������༭�¼�������
 * 
 * @since 6.0
 * @version 2012-2-7 ����03:52:49
 * @author ô��
 */
public class SendCountryEditHandler {

  /**
   * �༭����
   * 
   * @param e
   * @param rows
   */
  public void afterEdit(CardBodyAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    // 1.���ù������͡�����ó��
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
    // 2.ѯ˰
    SOTaxInfoRule taxInforule = new SOTaxInfoRule(keyValue);
    taxInforule.setTaxInfoByBodyPos(rows);
    int[] taxchgrows = taxInforule.getTaxChangeRows();
    calculator.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);

  }

}
