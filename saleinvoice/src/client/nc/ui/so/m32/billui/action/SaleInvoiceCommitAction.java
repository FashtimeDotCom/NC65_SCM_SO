package nc.ui.so.m32.billui.action;

import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.ui.pubapp.pub.common.context.PFlowContext;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;

/**
 * ���۷�Ʊ�ύ��ť
 * 
 * @since 6.3
 * @version 2012-12-21 ����11:17:46
 * @author yaogj
 */
public class SaleInvoiceCommitAction extends CommitScriptAction {

  /**
     * 
     */
  private static final long serialVersionUID = 1145902021606500549L;

  private SaleInvoiceVO[] oldcombinvo;

  @Override
  protected void fillUpContext(PFlowContext context) {
    super.fillUpContext(context);
    // ����Ʊ�ϲ���ʾ
    this.processCombinShow();
  }

  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getAppUiState() == AppUiState.EDIT
        || this.getModel().getAppUiState() == AppUiState.ADD
        || this.getModel().getAppUiState() == AppUiState.TRANSFERBILL_ADD) {
      return true;
    }
    boolean isEnable =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            && null != this.getModel().getSelectedData();

    if (isEnable) {
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      SaleInvoiceVO selectedData =
          (SaleInvoiceVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();
      if (null == selectedRows) {
        isEnable = BillStatus.FREE.equalsValue(billstatus);
      }
      else {
        // modify by wangshu6 for 636 ���۷�Ʊ��������������ͨ�������ٴ��ύ 20150408
        isEnable =
            selectedRows.length > 1 || BillStatus.FREE.equalsValue(billstatus)
                || BillStatus.NOPASS.equalsValue(billstatus);
      }

    }
    return isEnable;
  }

  @Override
  protected void processReturnObj(Object[] pretObj) throws Exception {

    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    // �ϲ���ʾ
    if (null != cachevo && cachevo.getBcombinflag()) {
      SaleInvoiceVO[] oldconbinvos =
          (SaleInvoiceVO[]) super.getFullOldVOs().clone();
      SaleInvoiceCombin combinuitl = new SaleInvoiceCombin();
      SaleInvoiceVO[] olddetailvos =
          combinuitl.getOldDetailVOs(oldconbinvos, cachevo.getCombinRela());
      SaleInvoiceVO[] newcombinvos =
          combinuitl.getNewCombinUIVOS(cachevo, oldconbinvos, olddetailvos,
              pretObj);
      super.setFullOldVOs(this.oldcombinvo);
      super.processReturnObj(newcombinvos);
    }
    else {
      super.processReturnObj(pretObj);
    }
  }

  private void processCombinShow() {
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    // �ϲ���ʾ
    if (null != cachevo && cachevo.getBcombinflag()) {
      Object[] uiobj = this.model.getSelectedOperaDatas();
      int length = uiobj.length;
      SaleInvoiceVO[] uicomvos = new SaleInvoiceVO[length];
      for (int i = 0; i < length; i++) {
        uicomvos[i] = (SaleInvoiceVO) uiobj[i];
      }

      this.oldcombinvo = uicomvos;

      SaleInvoiceCombin combin = new SaleInvoiceCombin();
      SaleInvoiceVO[] detainvo =
          combin.splitNoEditSaleInvoice(uicomvos, cachevo.getCombinRela());
      this.getFlowContext().setBillVos(detainvo);
    }

  }
}
