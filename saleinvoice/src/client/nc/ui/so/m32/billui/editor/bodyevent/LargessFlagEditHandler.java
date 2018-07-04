package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.vattax.vo.CalVatFieldValues;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * ��Ʒ��־λ�༭�¼�
 * 
 * @since 6.1
 * @version 2012-11-21 13:35:29
 * @author ��ӱ�
 */
public class LargessFlagEditHandler {

  /**
   * ����������������Ʒ��־�༭ǰ�¼�����Դ�����εķ�Ʊ����Ʒ��־���ɱ༭��
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-9 ����10:45:55
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // ��Դ����ID��Ϊ�ղ��ɱ༭
    String sourceid =
        keyValue.getBodyStringValue(e.getRow(), SaleInvoiceBVO.CSRCID);

    if (!PubAppTool.isNull(sourceid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
  }

  /**
   * ��Ʒ��־�༭���¼�
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    // ����VAT�ϼ�
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    CalVatFieldValues oldvat = vatcal.getVatFieldValues(row);
    UFBoolean oldlarflag = (UFBoolean) e.getOldValue();
    oldvat.setBlargessflag(oldlarflag);
    vatcal.calculateVatWhenEditVat(row, oldvat);
  }

}
