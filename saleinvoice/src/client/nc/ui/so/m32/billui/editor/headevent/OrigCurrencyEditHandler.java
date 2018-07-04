package nc.ui.so.m32.billui.editor.headevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.ExchangeRateRule;
import nc.vo.so.m32.rule.PriceMnyClearRule;
import nc.vo.so.m32.util.BizTypeUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ��ͷ���ֱ༭�¼�����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-3-1 ����03:41:32
 */
public class OrigCurrencyEditHandler {

  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // ����Ƿ�����޸�
    String bizType = keyValue.getHeadStringValue(SaleInvoiceHVO.CBIZTYPEID);
    String vtrantypecode =
        keyValue.getHeadStringValue(SaleInvoiceHVO.VTRANTYPECODE);
    if (BizTypeUtil.getInstance().isAdjustIncome(bizType, vtrantypecode)) {
      keyValue.setHeadValue(SaleInvoiceHVO.CORIGCURRENCYID, e.getOldValue());
      // fengjb 2012.03.05 UE��ʾ�淶
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0008")/*@res "���۷�Ʊ���ò�Ӧ�գ�������༭����"*/);
    }

    ExchangeRateRule exchghandler = new ExchangeRateRule(keyValue);
    exchghandler.calcExchangeRate();

    String ccurrencyid =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CCURRENCYID);
    String corigcurrencyid =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID);

    // 20140409 �����ӱ� ���ԭ�Һͱ���һ�£�������ȡ������˰������۽���㷨���������ա�
    if (PubAppTool.isEqual(ccurrencyid, corigcurrencyid)) {
      CardPanelCalculator calculator =
          new CardPanelCalculator(e.getBillCardPanel());
      calculator.calculateAll(SaleInvoiceBVO.NTAX);
    }
    else {
      // ��ձ��嵥�۽���ֶ�
      PriceMnyClearRule clearrule = new PriceMnyClearRule(keyValue);
      clearrule.clearAllBodyItem();
    }
    //����ѡ�����֮��ֱ����տͻ������˺�, add by liylr 2015-07-11
    keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKACCID, null);
    keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKID, null);
  }
}
