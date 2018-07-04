package nc.ui.so.m38.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class ExchangerateEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    int editrow = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // ԭ�ұ���
    String localcurid =
        keyValue.getHeadStringValue(PreOrderHVO.CORIGCURRENCYID);
    // ��֯��λ��
    String orgcurid =
        keyValue.getBodyStringValue(editrow, PreOrderBVO.CCURRENCYID);

    // û��ԭ�һ�����֯��λ��
    if (PubAppTool.isNull(localcurid) || PubAppTool.isNull(orgcurid)
        || localcurid.equals(orgcurid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    e.setReturnValue(Boolean.TRUE);
  }

}
