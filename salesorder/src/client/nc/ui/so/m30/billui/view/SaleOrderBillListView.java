package nc.ui.so.m30.billui.view;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.m30.pub.SaleOrderPrecision;
import nc.ui.so.pub.listener.SOListPanelTotalRowUtil;

public class SaleOrderBillListView extends ShowUpableBillListView {

  /**
   * 
   */
  private static final long serialVersionUID = -3171792172183638055L;

  @Override
  public void initUI() {
    super.initUI();
    // �����б����ĺϼ���
    this.setListViewTotalRow();
    String pk_group = this.getModel().getContext().getPk_group();
    // t AppContext.getInstance().getPkGroup();
    SaleOrderPrecision.getInstance().setListPrecision(pk_group,
        this.getBillListPanel());
  }

  /**
   * ���ñ�ͷ������ĺϼ��м���
   */
  private void setListViewTotalRow() {
    SOListPanelTotalRowUtil.setListViewTotalHeadAndBodyRow(this);
  }
}
