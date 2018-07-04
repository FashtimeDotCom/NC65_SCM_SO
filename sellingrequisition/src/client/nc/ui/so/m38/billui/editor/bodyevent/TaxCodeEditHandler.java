package nc.ui.so.m38.billui.editor.bodyevent;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTaxCodeRefUtils;
import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOTaxInfoRule;

/**
 * Ԥ����˰��༭�¼�
 * 
 * @since 6.0
 * @version 2012-2-9 ����10:22:21
 * @author ����
 */
public class TaxCodeEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // ����˰�ʡ���˰���
    SOTaxInfoRule taxinfo = new SOTaxInfoRule(keyValue);
    taxinfo.setTaxTypeAndRate(rows);
    // ��˰�ʴ������۽���㷨
    PreOrderCalculator calculator = new PreOrderCalculator(cardPanel);
    calculator.calculate(rows, PreOrderBVO.NTAXRATE);
  }

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    // ��˰��Ϊ�գ����ɱ༭
    String ctaxcountryid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CTAXCOUNTRYID);
    if (PubAppTool.isNull(ctaxcountryid)) {
      e.setReturnValue(false);
      return;
    }
    Integer fbuysellflag =
        keyValue.getBodyIntegerValue(row, PreOrderBVO.FBUYSELLFLAG);
    BillItem item = cardPanel.getBodyItem(PreOrderBVO.CTAXCODEID);
    FilterTaxCodeRefUtils filter =
        new FilterTaxCodeRefUtils((UIRefPane) item.getComponent());
    filter.filterItemRefWithCompatible(ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellflag));
  }

}
