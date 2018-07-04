package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * ˰��༭�¼�
 * 
 * @since 6.1
 * @version 2013-04-12 14:48:06
 * @author yixl
 */
public class NtaxEditHandler {

  /**
   * �༭ǰ�¼�
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // �Ƿ���ó��
    UFBoolean bsubunitflag =
        keyValue.getHeadUFBooleanValue(SaleInvoiceHVO.BSUBUNITFLAG);

    if (bsubunitflag.booleanValue()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006008_0", "04006008-0151")/*���۷�Ʊ�������ó�֣��������޸ģ�*/);
    }
  }
}
