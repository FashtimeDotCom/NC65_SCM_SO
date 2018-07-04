package nc.ui.so.m32.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ��ͷ�۱����ʣ������۱����ʡ����ű�λ�һ��ʡ�ȫ�ֱ�λ�һ��ʣ��༭�¼�����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-3-1 ����03:48:12
 */
public class ExchgRateEditHandler {

  /**
   * ���������������۱����ʱ༭���¼���
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-27 ����03:09:36
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardpanel = e.getBillCardPanel();
    CardPanelCalculator calculator = new CardPanelCalculator(cardpanel);
    calculator.calculateAll(e.getKey());

    // ���¼��������е�VAT��Ϣ
    CardVATCalculator vatcal = new CardVATCalculator(cardpanel);
    vatcal.calVatAll();
  }

  /**
   * ���������������۱����ʱ༭ǰ�¼���
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-27 ����03:09:02
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String orgcur = keyValue.getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID);
    String currency = keyValue.getHeadStringValue(SaleInvoiceHVO.CCURRENCYID);
    UFDouble nglobalexchgrate = keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NGLOBALEXCHGRATE);
    UFDouble ngroupexchgrate = keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NGROUPEXCHGRATE);
    
    String editKey = e.getKey();
    if (SaleInvoiceHVO.NEXCHANGERATE.equals(editKey)
        && !StringUtil.isEmpty(orgcur) && !StringUtil.isEmpty(currency)
        && orgcur.equals(currency)) {
      e.setReturnValue(Boolean.FALSE);
    }
    else if (SaleInvoiceHVO.NGLOBALEXCHGRATE.equals(editKey)
        && MathTool.equals(nglobalexchgrate, UFDouble.ONE_DBL)) {
      e.setReturnValue(Boolean.FALSE);
    }
    else if (SaleInvoiceHVO.NGROUPEXCHGRATE.equals(editKey)
        && MathTool.equals(ngroupexchgrate, UFDouble.ONE_DBL)) {
      e.setReturnValue(Boolean.FALSE);
    }
    
  }
}
