package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class InstordocHandler {

  /**
   * �������������������������ջ��ֿ�༭ǰ�¼�
   * 
   * @author ף����
   * @time 2010-6-7 ����03:42:00
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String instock =
        keyValue.getBodyStringValue(e.getRow(), DeliveryBVO.CINSTOCKORGID);

    if (PubAppTool.isNull(instock)) {
      e.setReturnValue(Boolean.FALSE);
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
              "04006002-0016")
      /*@res "����¼���ջ������֯"*/;
      e.getBillCardPanel().transferFocus();
      ExceptionUtils.wrappBusinessException(message);
    }

    String crprofitcenterid =
        keyValue.getBodyStringValue(e.getRow(), DeliveryBVO.CRPROFITCENTERID);

    // �ջ��ֿ�����ջ������֯�µ��ջ��ֿ�
    BillItem warehouse = cardPanel.getBodyItem(DeliveryBVO.CINSTORDOCID);
    FilterWareHouseRefUtils filter =
        new FilterWareHouseRefUtils((UIRefPane) warehouse.getComponent());
    if (!PubAppTool.isNull(crprofitcenterid)) {
      // ���ݷ����������Ĺ���
      filter.filterByLiabcenter(crprofitcenterid);
    }
    filter.filterItemRefByOrg(instock);
    filter.filterDirectStorc();
  }
}
