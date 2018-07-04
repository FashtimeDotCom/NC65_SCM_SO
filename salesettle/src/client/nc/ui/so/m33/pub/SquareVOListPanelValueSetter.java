package nc.ui.so.m33.pub;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.uif2.editor.BillListView.VOBillListPanelValueSetter;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class SquareVOListPanelValueSetter extends VOBillListPanelValueSetter {

  @Override
  public void setHeaderDatas(BillListPanel listPanel, Object[] allDatas) {
    if ((allDatas == null) || (allDatas.length == 0)) {
      listPanel.setHeaderValueVO(null);
    }
    else {

      CircularlyAccessibleValueObject[] vos =
          new CircularlyAccessibleValueObject[allDatas.length];
      for (int i = 0; i < allDatas.length; i++) {
        vos[i] = (CircularlyAccessibleValueObject) allDatas[i];
      }
      listPanel.setHeaderValueVO(vos);
      BillModel headModel = listPanel.getHeadBillModel();
      if (headModel != null
          && listPanel.getBillListData().isMeataDataTemplate()) {
        // ���������Ϣ
        headModel.loadLoadRelationItemValue();
        // ����װ�ع�ʽ
        headModel.execLoadFormula();
      }
    }
    
  }

  @Override
  public void setHeaderRowData(BillListPanel listPanel, Object rowData, int row) {
    listPanel.getHeadBillModel().setBodyRowVO(
        (CircularlyAccessibleValueObject) rowData, row);
    // ���������Ϣ
    if (listPanel.getBillListData().isMeataDataTemplate()) {
      listPanel.getHeadBillModel().loadLoadRelationItemValue();
    }
    // ����װ�ع�ʽ
    listPanel.getHeadBillModel().execLoadFormula();
  }

}
