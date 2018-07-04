package nc.ui.so.m30.billui.editor.bodyevent;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTaxCodeRefUtils;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOTaxInfoRule;

/**
 * ˰��༭��
 * 1��ѯ˰
 * 2���������۽���㷨
 * 
 * @since 6.0
 * @version 2012-3-13 ����08:21:49
 * @author ô��
 */
public class TaxCodeEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 1.����˰�ʡ���˰���
    SOTaxInfoRule taxinfo = new SOTaxInfoRule(keyValue);
    taxinfo.setTaxTypeAndRate(rows);
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    calculator.calculate(rows, SaleOrderBVO.NTAXRATE);
  }

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // ��˰��Ϊ�գ����ɱ༭
    String ctaxcountryid =
        keyValue.getBodyStringValue(e.getRow(), SaleOrderBVO.CTAXCOUNTRYID);
    if (PubAppTool.isNull(ctaxcountryid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    Integer fbuysellflag =
        keyValue.getBodyIntegerValue(e.getRow(), SaleOrderBVO.FBUYSELLFLAG);
    BillItem item = cardPanel.getBodyItem(SaleOrderBVO.CTAXCODEID);

    FilterTaxCodeRefUtils filter =
        new FilterTaxCodeRefUtils((UIRefPane) item.getComponent());
    filter.filterItemRefWithCompatible(ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellflag));
  }

}
