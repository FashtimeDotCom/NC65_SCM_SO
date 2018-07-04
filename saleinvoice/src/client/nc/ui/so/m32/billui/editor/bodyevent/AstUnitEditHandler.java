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
 * <li>���۷�Ʊ����λ�༭�¼�
 * </ul>
 * 
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-9-10 ����10:19:06
 */
public class AstUnitEditHandler {

  /**
   * �����������������۷�Ʊ���嵥λ�༭���¼�����ȡ�����ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-9 ����10:42:34
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
    chgraterule.calcAstChangeRate(row);

    // �����������۽��Ļ���
    CardPanelCalculator calculator = new CardPanelCalculator(cardPanel);
    calculator.calculate(rows, SaleInvoiceBVO.VCHANGERATE);

    // ����VAT
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    vatcal.calculateVatWhenEditNoVat(row, SaleInvoiceBVO.VCHANGERATE);
  }

  /**
   * �����������������۷�Ʊ���嵥λ�༭ǰ�¼��������������ñ༭�ԺͲ���Լ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-3-12 ����03:44:34
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // ����Ϊ�գ����ɱ༭
    String material =
        keyValue.getBodyStringValue(e.getRow(), SaleInvoiceBVO.CMATERIALID);

    if (PubAppTool.isNull(material)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // ���ϲ�Ϊ�գ�ֻ�ܲ������϶�Ӧ�ļ�����λ
    BillItem astunititem = cardPanel.getBodyItem(SaleInvoiceBVO.CASTUNITID);
    FilterMeasdocRefUtils astunitFilter =
        new FilterMeasdocRefUtils(astunititem);
    astunitFilter.setMaterialPk(material);
  }

}
