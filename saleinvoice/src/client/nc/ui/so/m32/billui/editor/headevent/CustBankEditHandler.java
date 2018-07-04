package nc.ui.so.m32.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterBankRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ͻ��������б༭�¼�
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-8-6 ����03:13:22
 */
public class CustBankEditHandler {
  /**
   * 
   * ���������������ͻ��������б༭���¼�����տͻ������ʺš�
   * <p>
   * <b>����˵��</b>
   * @param e
   * <p>
   * @author fengjb
   * @time 2010-9-10 ����10:08:19
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKACCID, null);
  }

  /**
   * 
   * ���������������ͻ��������б༭ǰ�¼������ݿͻ�����Լ��������
   * <p>
   * <b>����˵��</b>
   * @param e
   * <p>
   * @author fengjb
   * @time 2010-9-10 ����10:07:48
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // ��Ʊ�ͻ�Ϊ��ʱ���ͻ����������ʺŲ��ղ�����
    String invoicecust =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID);

    BillItem bankitem = cardPanel.getHeadItem(SaleInvoiceHVO.CCUSTBANKID);

    FilterBankRefUtils refUtil = new FilterBankRefUtils(bankitem);
    refUtil.filterItemRefByCust(invoicecust);
  }
}
