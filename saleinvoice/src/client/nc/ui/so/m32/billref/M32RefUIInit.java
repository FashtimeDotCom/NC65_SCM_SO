package nc.ui.so.m32.billref;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.src.IRefPanelInit;
import nc.ui.so.m32.billui.pub.SaleInvoicePrecision;
import nc.vo.pubapp.AppContext;

/**
 * ���۷�Ʊת�������ʼ����
 * 
 * @since 6.0
 * @version 2011-6-1 ����08:33:10
 * @author ô��
 */
public class M32RefUIInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // ���ӱ��ȴ���
    SaleInvoicePrecision.getInstance().setListPrecision(
        AppContext.getInstance().getPkGroup(), masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    String pk_group = AppContext.getInstance().getPkGroup();
    // �����ȴ���
    SaleInvoicePrecision.getInstance().setSingleTableScale(pk_group,
        singlePanel);
  }

}
