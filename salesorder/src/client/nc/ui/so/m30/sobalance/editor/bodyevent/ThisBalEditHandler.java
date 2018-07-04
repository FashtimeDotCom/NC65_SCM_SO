package nc.ui.so.m30.sobalance.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class ThisBalEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // ֻ�ж��������п��Ա༭
    Integer fibaltype =
        keyValue.getBodyIntegerValue(row, SoBalanceBVO.FIBALTYPE);
    if (SoBalanceType.SOBALANCE_FINBAL.equalsValue(fibaltype)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
  }
}
