package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.util.CalculatorUtil;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>���۷�Ʊ����༭���¼�����
 * </ul>
 * 
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-8-10 ����10:10:25
 */
public class BodyAfterEditHandler implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    // �༭�ֶ�
    String editKey = e.getKey();
    // ����
    if (SaleInvoiceBVO.CMATERIALVID.equals(editKey)) {
      MaterialEditHandler handler = new MaterialEditHandler();
      handler.afterEdit(e);
    }
    // ҵ��λ
    else if (SaleInvoiceBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.afterEdit(e);
    }
    // ���۵�λ
    else if (SaleInvoiceBVO.CQTUNITID.equals(editKey)) {
      QtUnitEditHandler handler = new QtUnitEditHandler();
      handler.afterEdit(e);
    }
    // ˰��
    else if (SaleInvoiceBVO.CTAXCODEID.equals(editKey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.afterEdit(e);
    }
    // ��˰���
    else if (SaleInvoiceBVO.FTAXTYPEFLAG.equals(editKey)) {
      TaxTypeFlagEditHandler handler = new TaxTypeFlagEditHandler();
      handler.afterEdit(e);
    }
    // ˰��
    else if (SaleInvoiceBVO.NTAXRATE.equals(editKey)) {
      TaxRateEditHandler handler = new TaxRateEditHandler();
      handler.afterEdit(e);
    }
    // ��Ʒ��־λ
    else if (SaleInvoiceBVO.BLARGESSFLAG.equals(editKey)) {
      LargessFlagEditHandler handler = new LargessFlagEditHandler();
      handler.afterEdit(e);
    }
    // �����ֿ�
    else if (SaleInvoiceBVO.CSENDSTORDOCID.equals(editKey)) {
      CsendstockOrgEditHandler handler = new CsendstockOrgEditHandler();
      handler.afterEdit(e);
    }
    // ����༭�ֶβ���Ӱ�쵽�������۽������м���
    else if (CalculatorUtil.getInstance().getNeedCalKey().contains(editKey)) {
      int row = e.getRow();
      BillCardPanel cardpanel = e.getBillCardPanel();
      // �����������۽��Ļ���
      CardPanelCalculator calculator = new CardPanelCalculator(cardpanel);
      calculator.calculate(row, editKey);
      // ����VAT
      CardVATCalculator vatcal = new CardVATCalculator(cardpanel);
      vatcal.calculateVatWhenEditNoVat(row, editKey);
      IKeyValue keyValue = new CardKeyValue(cardpanel);
      HeadTotalCalcUtil.getInstance().calcHeadTotalValue(keyValue);
    }
  }

}
