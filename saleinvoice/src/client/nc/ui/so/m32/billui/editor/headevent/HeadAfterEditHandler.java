package nc.ui.so.m32.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ��ͷ�༭���¼�����ɷ�Ʊ��ͷ�ֶα༭��������
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-3-1 ����03:01:17
 */
public class HeadAfterEditHandler implements
    IAppEventHandler<CardHeadTailAfterEditEvent> {

  private SaleInvoiceEditor editor;

  private BillManageModel model;

  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(CardHeadTailAfterEditEvent e) {
    // Ч���Ż������ٺϼƴ���
    BillCardPanel cardPanel = e.getBillCardPanel();
    cardPanel.getBillModel().setNeedCalculate(false);

    // ��ͷ�༭�ֶ�
    String editkey = e.getKey();
    // ��������
    if (SaleInvoiceHVO.DBILLDATE.equals(editkey)) {
      BillDateEditHandler handler = new BillDateEditHandler();
      handler.afterEdit(e);
    }
    // ��Ʊ�ۿ�
    else if (SaleInvoiceHVO.NHVOICEDISRATE.equals(editkey)) {
      InvoiceDisEditHandler handler = new InvoiceDisEditHandler();
      handler.afterEdit(e);
    }
    // ��Ʊ�ͻ�
    else if (SaleInvoiceHVO.CINVOICECUSTID.equals(editkey)) {
      InvoiceCustEditHandler handler = new InvoiceCustEditHandler();
      handler.afterEdit(e);

    }
    // �ͻ���������
    else if (SaleInvoiceHVO.CCUSTBANKID.equals(editkey)) {
      CustBankEditHandler handler = new CustBankEditHandler();
      handler.afterEdit(e);
    }
    // �ͻ������ʺ�
    else if (SaleInvoiceHVO.CCUSTBANKACCID.equals(editkey)) {
      CustBankaccEditHandler handler = new CustBankaccEditHandler();
      handler.afterEdit(e);
    }
    // ԭ�ұ���
    else if (SaleInvoiceHVO.CORIGCURRENCYID.equals(editkey)) {
      OrigCurrencyEditHandler handler = new OrigCurrencyEditHandler();
      handler.afterEdit(e);
    }
    // �۱����ʡ����ű�λ�һ��ʡ�ȫ�ֱ�λ�һ���
    else if (SaleInvoiceHVO.NEXCHANGERATE.equals(editkey)
        || SaleInvoiceHVO.NGLOBALEXCHGRATE.equals(editkey)
        || SaleInvoiceHVO.NGROUPEXCHGRATE.equals(editkey)) {
      ExchgRateEditHandler handler = new ExchgRateEditHandler();
      handler.afterEdit(e);
    }
    // ��ֽ��
    else if (SaleInvoiceHVO.NTOTALORIGSUBMNY.equals(editkey)) {
      TotalOrigSubMnyEditHandler handler = new TotalOrigSubMnyEditHandler();
      handler.afterEdit(e, this.getEditor(), this.getModel());
    }
    // Ч���Ż������ٺϼƴ���
    cardPanel.getBillModel().setNeedCalculate(true);
  }

  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
