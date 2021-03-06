package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

public class ChangeRateEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyvalue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    String cunitid = keyvalue.getBodyStringValue(row, SaleOrderBVO.CUNITID);
    String castunitid =
        keyvalue.getBodyStringValue(row, SaleOrderBVO.CASTUNITID);
    if (PubAppTool.isNull(cunitid) || PubAppTool.isNull(castunitid)) {
      e.setReturnValue(false);
      return;
    }
    SOUnitChangeRateRule changeraterule = new SOUnitChangeRateRule(keyvalue);
    boolean isfix = changeraterule.isAstFixedRate(row);
    e.setReturnValue(!isfix);

  }

}
