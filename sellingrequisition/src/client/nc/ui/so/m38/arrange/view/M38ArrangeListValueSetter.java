package nc.ui.so.m38.arrange.view;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.uif2.editor.BillListView.IBillListPanelValueSetter;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class M38ArrangeListValueSetter implements IBillListPanelValueSetter {

  /**
   * ��viewVO�������ֶ��ڱ����viewģ����
   * 
   * @version 6.0
   * @author ��־ΰ
   * @time 2010-6-30 ����09:44:39
   */
  @Override
  public void setBodyData(BillListPanel listPanel, Object selectedData) {
    listPanel.getBillListData().setBodyValueVO(
        (CircularlyAccessibleValueObject[]) selectedData);
    listPanel.getBillListData().getBodyBillModel().loadLoadRelationItemValue();
    listPanel.getBillListData().getBodyBillModel().execLoadFormula();

  }

  @Override
  public void setHeaderDatas(BillListPanel listPanel, Object[] allDatas) {
    // TODO �Զ����ɷ������

  }

  @Override
  public void setHeaderRowData(BillListPanel listPanel, Object rowData, int row) {
    // TODO �Զ����ɷ������

  }

}
