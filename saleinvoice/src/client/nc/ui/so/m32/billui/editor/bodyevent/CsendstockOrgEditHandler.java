package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBatcheEditRule;

/**
 * �����ֿ��ֶα༭�¼�
 * 
 * @since 6.0
 * @version 2011-12-31 ����02:22:54
 * @author ������
 */
public class CsendstockOrgEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    SOBatcheEditRule batchedit = new SOBatcheEditRule(keyValue, new String[] {
      SOItemKey.CSENDSTOCKORGID
    });
    batchedit.clearRows(rows, SOItemKey.CSENDSTORDOCID);

  }

}
