package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.vattax.vo.CalVatFieldValues;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;

/**
 * ���۷�Ʊ����˰�ʱ༭�¼�����
 * 
 * @since 6.1
 * @version 2012-11-21 12:48:23
 * @author ��ӱ�
 */
public class TaxRateEditHandler {

  /**
   * ˰�ʱ༭���¼�
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    int row = e.getRow();
    BillCardPanel cardpanel = e.getBillCardPanel();

    // ����ɵ�����
    CardVATCalculator vatcal = new CardVATCalculator(cardpanel);
    CalVatFieldValues oldvat = vatcal.getVatFieldValues(row);
    UFDouble oldtaxrate = (UFDouble) e.getOldValue();
    oldvat.setNtaxrate(oldtaxrate);
    // �����������۽��Ļ���
    CardPanelCalculator calculator = new CardPanelCalculator(cardpanel);
    calculator.calculate(row, SaleInvoiceBVO.NTAXRATE);

    // ����VAT�ϼ�
    vatcal.calculateVatWhenEditVat(row, oldvat);
  }

}
