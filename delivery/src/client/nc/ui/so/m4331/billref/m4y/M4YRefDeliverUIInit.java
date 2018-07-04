package nc.ui.so.m4331.billref.m4y;

import nc.ui.pubapp.billref.src.RefUIInit;
import nc.ui.pubapp.billref.src.view.RefListPanel;
import nc.ui.so.m4331.billui.util.DeliveryPrecision;
import nc.vo.pubapp.AppContext;

public class M4YRefDeliverUIInit extends RefUIInit {

  @Override
  public void initBillListPanel(RefListPanel listpane) {
    // ������
    DeliveryPrecision.getInstance().setListPrecision(
        AppContext.getInstance().getPkGroup(), listpane);
  }
}
