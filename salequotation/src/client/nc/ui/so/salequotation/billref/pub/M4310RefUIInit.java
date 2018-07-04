package nc.ui.so.salequotation.billref.pub;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.billref.src.IRefPanelInit;
import nc.ui.so.salequotation.scale.SalequoScaleProcessor;

public class M4310RefUIInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // ���ӱ��ȴ���
    SalequoScaleProcessor.getInstance().setListPrecision(
        ClientContext.getInstance().getPk_group(), masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    String pk_group = ClientContext.getInstance().getPk_group();
    // �����ȴ���
    SalequoScaleProcessor.getInstance().setSingleTableScale(pk_group,
        singlePanel);
  }
}
