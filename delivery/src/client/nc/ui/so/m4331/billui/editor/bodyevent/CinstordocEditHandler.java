package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBatcheEditRule;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;

/**
 * �����ֿ��ֶα༭�¼�
 * 
 * @since 6.0
 * @version 2011-12-31 ����02:22:54
 * @author ������
 */
public class CinstordocEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    SOBatcheEditRule batchedit = new SOBatcheEditRule(keyValue, new String[] {
        SOItemKey.CSENDSTOCKORGID
    });
    batchedit.clearRows(rows, SOItemKey.CSENDSTORDOCID);
    String cprofitcenterid =
        keyValue.getBodyStringValue(e.getRow(), DeliveryBVO.CRPROFITCENTERID);
    String cstordocid =
        keyValue.getBodyStringValue(e.getRow(), DeliveryBVO.CINSTORDOCID);
    if (PubAppTool.isNull(cprofitcenterid)
        && !PubAppTool.isNull(cstordocid)) {
    // add by zhangby5 for ��������ȡֵ����
    SOProfitCenterValueRule profitRule =
        new SOProfitCenterValueRule(keyValue, DeliveryBVO.CINSTORDOCID,
            DeliveryBVO.CINSTOCKORGID);
    profitRule.setProfitCenterValue(DeliveryBVO.CRPROFITCENTERVID,
        DeliveryBVO.CRPROFITCENTERID, rows);
    }
  }

}
