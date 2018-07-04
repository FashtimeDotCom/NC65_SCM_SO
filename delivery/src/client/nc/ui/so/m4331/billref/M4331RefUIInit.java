package nc.ui.so.m4331.billref;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.src.IRefPanelInit;
import nc.ui.so.m4331.billui.util.DeliveryPrecision;
import nc.vo.pubapp.AppContext;

public class M4331RefUIInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // ���ӱ��ȴ���
    DeliveryPrecision.getInstance().setListPrecision(
        AppContext.getInstance().getPkGroup(), masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    String pk_group = AppContext.getInstance().getPkGroup();
    // �����ȴ���
    DeliveryPrecision.getInstance().setSingleTableScale(pk_group, singlePanel);
  }

}
