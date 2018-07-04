package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderTranTypeUtil;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBatcheEditRule;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;

public class SendStordocEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardpanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardpanel);
    BillItem sendStordoc = cardpanel.getBodyItem(SaleOrderBVO.CSENDSTORDOCID);
    FilterWareHouseRefUtils stordocFilter =
        new FilterWareHouseRefUtils((UIRefPane) sendStordoc.getComponent());

    // ����
    String pk_group = AppUiContext.getInstance().getPkGroup();
    // �����֯
    String sendStockOrg =
        keyValue.getBodyStringValue(e.getRow(), SaleOrderBVO.CSENDSTOCKORGID);
    // ��������
    String ctrantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    String csprofitcenterid = keyValue.getBodyStringValue(e.getRow(), SaleOrderBVO.CSPROFITCENTERID);
    if(!PubAppTool.isNull(csprofitcenterid)){
      // ���ݷ����������Ĺ���
      stordocFilter.filterByLiabcenter(csprofitcenterid);
    }
    stordocFilter.filterItemRefByGroup(pk_group);
    stordocFilter.filterWasteStorc();
    stordocFilter.filterUnableState();

    if (!PubAppTool.isNull(sendStockOrg)) {
      stordocFilter.filterItemRefByOrg(sendStockOrg);
    }
    else {
      stordocFilter.filterItemRefByOrg(null);
    }

    if (!PubAppTool.isNull(ctrantypeid)) {
      int flag = new SaleOrderTranTypeUtil().getDirectType(ctrantypeid);
      if (DirectType.DIRECTTRAN_NO.getIntValue() == flag) {
        // 1.��ֱ�ˣ����˵�ֱ�˲�
        stordocFilter.filterDirectStorc();
      }
      else if (DirectType.DIRECTTRAN_TO.getIntValue() == flag) {
        // 2.ֱ�˵�����ֱ�˲�
        stordocFilter.onlyDirectStorc();
      }
      else {
        // 3.ֱ�˲ɹ�����ͨ��+ֱ�˲�
        stordocFilter.filterDirectAndNotDirectStorc();
      }
    }
  }

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    SOBatcheEditRule batchedit = new SOBatcheEditRule(keyValue, new String[] {
        SaleOrderBVO.CSENDSTOCKORGID
    });
    batchedit.clearRows(rows, SaleOrderBVO.CSENDSTORDOCID);

    SaleOrgRelationRule relarule = new SaleOrgRelationRule(keyValue);
    // ����������֯
    relarule.setTrafficOrg(rows);
    
    /*
     * �༭�ֿ⣺���������������Ϊ�գ����ݹ�����뷢���������ģ����򷢻��������Ĳ��䡣
     * ��ղֿ�:��Ӱ�췢���������ġ�
     */
    String csprofitcenterid =
        keyValue.getBodyStringValue(e.getRow(), SaleOrderBVO.CSPROFITCENTERID);
    String csendstordocid =
        keyValue.getBodyStringValue(e.getRow(), SaleOrderBVO.CSENDSTORDOCID);
    if (PubAppTool.isNull(csprofitcenterid)
        && !PubAppTool.isNull(csendstordocid)) {
      // add by zhangby5 for ��������ȡֵ����
      SOProfitCenterValueRule profitRule =
          new SOProfitCenterValueRule(keyValue);
      profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
          SaleOrderBVO.CSPROFITCENTERID, rows);
    }
  }
}
