package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>���۵�λ�༭�¼�����
 * </ul>
 * 
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-9-10 ����10:23:48
 */
public class QtUnitEditHandler {

  /**
   * �����������������۷�Ʊ���屨�۵�λ�༭���¼������ñ��ۻ����ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author fengjb
   * @time 2010-6-29 ����11:23:30
   */
  public void afterEdit(CardBodyAfterEditEvent e) {

    int row = e.getRow();
    int[] rows = new int[] {
      row
    };
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // �����µĻ�����
    SOUnitChangeRateRule chgraterule = new SOUnitChangeRateRule(keyValue);
    chgraterule.calcQtChangeRate(row);

    // �����������۽��Ļ���
    CardPanelCalculator calculator = new CardPanelCalculator(cardPanel);
    calculator.calculate(rows, SaleInvoiceBVO.VQTUNITRATE);

    // ����VAT�ϼ�
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    vatcal.calculateVatWhenEditNoVat(row, SaleInvoiceBVO.VQTUNITRATE);
  }

  /**
   * �����������������۷�Ʊ���屨�۵�λ�༭ǰ�¼���
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author fengjb
   * @time 2010-6-29 ����11:21:12
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // ����Ϊ�գ����ɱ༭
    String material =
        keyValue.getBodyStringValue(e.getRow(), SaleInvoiceBVO.CMATERIALID);

    if (PubAppTool.isNull(material)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // ���ϲ�Ϊ�գ�ֻ�ܲ������϶�Ӧ�ļ�����λ
    BillItem qtunititem = cardPanel.getBodyItem(SaleInvoiceBVO.CQTUNITID);
    FilterMeasdocRefUtils qtunitFilter = new FilterMeasdocRefUtils(qtunititem);
    qtunitFilter.setMaterialPk(material);
  }

}
