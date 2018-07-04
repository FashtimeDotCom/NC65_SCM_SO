package nc.ui.so.m32.billui.editor.headevent;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.BodyUpdateByHead;
import nc.vo.so.m32.rule.ExchangeRateRule;
import nc.vo.trade.checkrule.VOChecker;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ڱ༭�¼�����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-5-6 ����08:03:23
 */
public class BillDateEditHandler {

  /**
   * ���������������������ڱ༭���¼���
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-5-6 ����08:29:55
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);

    // ���±��嵥������
    BodyUpdateByHead updateRule = new BodyUpdateByHead(keyValue);
    updateRule.updateAllBodyValueByHead(SaleInvoiceBVO.DBILLDATE,
        SaleInvoiceHVO.DBILLDATE);

    // ȡ�����۱�����
    UFDouble oldExchgrate =
        keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NEXCHANGERATE);
    if (null == oldExchgrate) {
      oldExchgrate = UFDouble.ZERO_DBL;
    }

    ExchangeRateRule exchgRule = new ExchangeRateRule(keyValue);
    exchgRule.calcExchangeRate();

    UFDouble newExchgrate =
        keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NEXCHANGERATE);
    // �۱����ʱ仯�����¼���
    if (!VOChecker.isEmpty(newExchgrate)
        && newExchgrate.compareTo(oldExchgrate) != 0) {
      CardPanelCalculator calc = new CardPanelCalculator(cardPanel);
      calc.calculateAll(SaleInvoiceHVO.NEXCHANGERATE);
      // ���¼��������е�VAT��Ϣ
      CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
      vatcal.calVatAll();
    }

  }
}
