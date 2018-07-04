package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class QtUnitRateEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    IKeyValue keyvalue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    String material =
        keyvalue.getBodyStringValue(row, PreOrderBVO.CMATERIALVID);
    if (!PubAppTool.isNull(material)) {
      // ���ϲ�Ϊ�գ�ֻ�ܲ������϶�Ӧ�ļ�����λ
      BillItem qtunititem = listPanel.getBodyItem(PreOrderBVO.CQTUNITID);
      FilterMeasdocRefUtils qtunitFilter =
          new FilterMeasdocRefUtils(qtunititem);
      qtunitFilter.setMaterialPk(material);
      e.setEditable(Boolean.TRUE);
    }
    else {
      e.setEditable(Boolean.FALSE);
    }
  }

}
