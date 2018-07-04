package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.scmpub.vattax.vo.CalVatFieldValues;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;

/**
 * ��˰�����ֶα༭�¼�
 * 
 * @since 6.0
 * @version 2011-12-31 ����02:22:54
 * @author ô��
 */
public class TaxTypeFlagEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    int row = e.getRow();
    int[] rows = new int[] {
      row
    };
    BillCardPanel cardPanel = e.getBillCardPanel();
    // ����ɵ�����
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    CalVatFieldValues oldvat = vatcal.getVatFieldValues(row);
    Integer oldtaxtype = (Integer) e.getOldValue();
    oldvat.setFtaxtypeflag(oldtaxtype);
    // ���õ��۽���㷨����˰�ʴ�����
    CardPanelCalculator calculator = new CardPanelCalculator(cardPanel);
    calculator.calculate(rows, SaleInvoiceBVO.NTAXRATE);
    // ����VAT�ϼ�
    vatcal.calculateVatWhenEditVat(row, oldvat);
  }

}
