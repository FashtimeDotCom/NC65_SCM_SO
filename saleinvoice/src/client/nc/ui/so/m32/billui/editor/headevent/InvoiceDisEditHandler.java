package nc.ui.so.m32.billui.editor.headevent;

import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.BodyUpdateByHead;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ��ͷ��Ʊ�ۿ۱༭�¼�
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-5-6 ����08:33:53
 */
public class InvoiceDisEditHandler {

  /**
   * ����������������Ʊ�ۿ۱༭���¼���
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-5-6 ����08:33:38
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // ���±��巢Ʊ�ۿ�
    BodyUpdateByHead updateRule = new BodyUpdateByHead(keyValue);
    updateRule.updateAllBodyValueByHead(SaleInvoiceBVO.NINVOICEDISRATE,
        SaleInvoiceHVO.NHVOICEDISRATE);
    // ��Ʊ�ۿ۱仯�����¼���
    CardPanelCalculator calculator = new CardPanelCalculator(cardPanel);
    calculator.calculateAll(SaleInvoiceBVO.NINVOICEDISRATE);

    // ���¼��������е�VAT��Ϣ
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    vatcal.calVatAll();
  }
}
