package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.rule.UnitChangeRateRule;

public class QtUnitEditHandler {
  /**
   * �����������������������۵�λ�༭���ñ��ۻ�����
   * 
   * @author ף����
   * @time 2010-6-7 ����03:47:39
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    UnitChangeRateRule chgraterule = new UnitChangeRateRule(keyValue);
    chgraterule.calcQTChangeRate(e.getRow());
  }

  /**
   * �����������������������屨�۵�λ�༭ǰ�¼�
   * 
   * @author ף����
   * @time 2010-6-7 ����03:42:00
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // ����Ϊ�գ����ɱ༭
    String material =
        keyValue.getBodyStringValue(e.getRow(), DeliveryBVO.CMATERIALID);

    
    if (PubAppTool.isNull(material)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // ���ϲ�Ϊ�գ�ֻ�ܲ������϶�Ӧ�ļ�����λ
    BillItem item = cardPanel.getBodyItem(DeliveryBVO.CQTUNITID);
    FilterMeasdocRefUtils astunitFilter = new FilterMeasdocRefUtils(item);
    astunitFilter.setMaterialPk(material);
  }

}
