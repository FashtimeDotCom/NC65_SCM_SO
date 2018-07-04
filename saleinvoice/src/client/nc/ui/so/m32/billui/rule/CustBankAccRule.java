package nc.ui.so.m32.billui.rule;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ���۷�Ʊ����Ĭ���ʻ�
 * 
 * @since 6.0
 * @version 2011-11-17 ����08:49:59
 * @author ô��
 */
public class CustBankAccRule {

  /**
   * ��Ƭ
   */
  private BillCardPanel cardPanel;

  /**
   * ���췽��
   * 
   * @param cardPanel ��Ƭ
   */
  public CustBankAccRule(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  /**
   * ����Ĭ�ϵ������ʺźͿ�������
   */
  public void setDefCustBankAcc() {

    IKeyValue keyValue = new CardKeyValue(this.cardPanel);

    String invoicecust =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID);
    if (PubAppTool.isNull(invoicecust)) {
      keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKID, null);
      keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKACCID, null);
      return;
    }

    String defacc = CustomerPubService.getDefaultBankAcc(invoicecust);
    keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKACCID, defacc);

    // ִ�й�����ʽ��������������
    this.cardPanel.getBillData().loadEditHeadRelation(
        SaleInvoiceHVO.CCUSTBANKACCID);

  }
}
