package nc.ui.so.m38.billui.editor.bodyevent;

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
 * Ԥ�����ջ�����/�����༭�¼�
 * 
 * @since 6.0
 * @version 2012-2-7 ����13:58:21
 * @author ����
 */
public class ReceiveCountryEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);

    // 1.���ù������ͺ�����ó��
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());

    // 2.ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] taxchgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);

  }
}
