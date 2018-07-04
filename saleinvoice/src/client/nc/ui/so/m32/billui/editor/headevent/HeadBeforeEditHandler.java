package nc.ui.so.m32.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m32.billui.rule.IsEditableCheckRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>���۷�Ʊ��ͷ�༭ǰ�¼������ò���Լ��������
 * </ul>
 * 
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-8-10 ����10:01:14
 */
public class HeadBeforeEditHandler implements
    IAppEventHandler<CardHeadTailBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    
    String editKey = e.getKey();
    // add by wangshu6 for 636 2015-01-09 ���۷�Ʊ�޶�֧���޸� 
    // �ж�״̬���������޸�
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    Integer status = keyValue.getHeadIntegerValue(SaleInvoiceHVO.FSTATUSFLAG);
    boolean isEditable = Boolean.FALSE;
    if (BillStatus.AUDITING.equalsValue(status)) {
      // У�鵱ǰ�༭�ֶ��Ƿ���޸ģ�
      isEditable =
          new IsEditableCheckRule().check(e.getBillCardPanel(), -1,
              e.getKey());
      if (!isEditable) {
        e.setReturnValue(Boolean.valueOf(Boolean.FALSE));
      }
    }
    // end 
    
    // Ĭ����Ϊ�ɱ༭
    e.setReturnValue(Boolean.TRUE);
    // �۱����ʡ�ȫ���۱����ʡ������۱�����
    if (SaleInvoiceHVO.NEXCHANGERATE.equals(editKey)
        || SaleInvoiceHVO.NGLOBALEXCHGRATE.equals(editKey)
        || SaleInvoiceHVO.NGROUPEXCHGRATE.equals(editKey)) {
      ExchgRateEditHandler handler = new ExchgRateEditHandler();
      handler.beforeEdit(e);
    }
    // ��������
    else if (SaleInvoiceHVO.CTRANTYPEID.equals(editKey)) {
      TrantypeEditHandler handler = new TrantypeEditHandler();
      handler.beforeEdit(e);
    }
    // �ͻ���������
    else if (SaleInvoiceHVO.CCUSTBANKID.equals(editKey)) {
      CustBankEditHandler handler = new CustBankEditHandler();
      handler.beforeEdit(e);
    }
    // ���������ʺ�
    else if (SaleInvoiceHVO.CCUSTBANKACCID.equals(editKey)) {
      CustBankaccEditHandler handler = new CustBankaccEditHandler();
      handler.beforeEdit(e);
    }
    // ��Ʊ�ۿ�
    else if (SaleInvoiceHVO.NHVOICEDISRATE.equals(editKey)) {
      NhvoicedisrateEditHandler handler = new NhvoicedisrateEditHandler();
      handler.beforeEdit(e);
    }
    // ����
    else if (SaleInvoiceHVO.CORIGCURRENCYID.equals(editKey)) {
      CorigcurrencyidEditHandler handler = new CorigcurrencyidEditHandler();
      handler.beforeEdit(e);
    }
    else if(SaleInvoiceHVO.CINVOICECUSTID.equals(editKey)){
      InvoiceCustEditHandler handler=new InvoiceCustEditHandler();
      handler.beforeEdit(e);
    }

  }
}
