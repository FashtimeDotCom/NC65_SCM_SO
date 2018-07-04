package nc.ui.so.m32.billui.view;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.m32.billui.pub.SaleInvoicePrecision;
import nc.ui.so.pub.listener.SOListPanelTotalRowUtil;
import nc.vo.pubapp.AppContext;

/**
 * ���۷�Ʊ�б���ͼ
 * 
 * @since 6.0
 * @version 2011-5-6 ����01:27:34
 * @author ô��
 */
public class SaleInvoiceListView extends ShowUpableBillListView {

  /** Version */
  private static final long serialVersionUID = -4014700571812033159L;

  @Override
  public void initUI() {
    super.initUI();
    // �����б����ĺϼ���
    this.setListViewTotalRow();

    String pk_group = AppContext.getInstance().getPkGroup();
    SaleInvoicePrecision.getInstance().setListPrecision(pk_group,
        this.getBillListPanel());
  }

  /**
   * ���ñ�ͷ������ĺϼ��м���
   */
  private void setListViewTotalRow() {
    SOListPanelTotalRowUtil.setListViewTotalHeadAndBodyRow(this);
  }

}
