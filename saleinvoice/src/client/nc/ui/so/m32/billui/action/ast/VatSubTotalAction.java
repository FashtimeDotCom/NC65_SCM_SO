package nc.ui.so.m32.billui.action.ast;

import java.awt.event.ActionEvent;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.DefaultVOMerger;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.dlg.VatSubTotalDlg;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * ˰��С��ǰ̨action��
 * 
 * @since 6.1
 * @version 2012-11-22 13:38:50
 * @author ��ӱ�
 */
public class VatSubTotalAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = 2643979139774754038L;

  /**
   * ������Ƭ�ؼ�
   */
  private SaleInvoiceEditor editor;

  private AbstractAppModel model;

  /**
   * ������
   */
  public VatSubTotalAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_VATSUBTOTAL);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleInvoiceVO vo = null;
    if (this.getModel().getUiState() == UIState.EDIT
        || this.getModel().getUiState() == UIState.ADD) {
      vo = (SaleInvoiceVO) this.editor.getValue();
    }
    else {
      vo = (SaleInvoiceVO) this.model.getSelectedData();
    }
    if(vo.getChildrenVO() == null || vo.getChildrenVO().length == 0){
      ExceptionUtils
      .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006008_0", "04006008-0162")/*@res "˰��С���쳣�����������ݲ���Ϊ�գ�"*/);
    }
    SaleInvoiceBVO[] mergebodys = this.getMergeBody(vo);
    SaleInvoiceViewVO[] views = new SaleInvoiceViewVO[mergebodys.length]; 
    int i = 0;
    for (SaleInvoiceBVO bvo : mergebodys) {
      views[i] = new SaleInvoiceViewVO();
      views[i].setHead(vo.getParentVO());
      views[i].setItem(bvo);
      i++;
    }
    VatSubTotalDlg dlg =
        new VatSubTotalDlg(WorkbenchEnvironment.getInstance().getWorkbench(),
            views, true);
    dlg.showModal();
  }

  private SaleInvoiceBVO[] getMergeBody(SaleInvoiceVO vo) {
    SaleInvoiceBVO[] bodys = vo.getChildrenVO();
    // �����ֶ�
    String[] groupkeys =
        new String[] {
          SaleInvoiceBVO.CTAXCODEID, SaleInvoiceBVO.NTAXRATE,
          SaleInvoiceBVO.FTAXTYPEFLAG, SaleInvoiceBVO.BLARGESSFLAG
        };
    // �����ֶ�
    String[] sumkeys =
        new String[] {
          SaleInvoiceBVO.NMNY, SaleInvoiceBVO.NCALTAXMNY, SaleInvoiceBVO.NTAX,
          SaleInvoiceBVO.NTAXMNY
        };
    DefaultVOMerger merger = new DefaultVOMerger();
    merger.setGroupingAttr(groupkeys);
    merger.setSummingAttr(sumkeys);
    SaleInvoiceBVO[] newbodys = null;
    try {
      newbodys = (SaleInvoiceBVO[]) merger.mergeByGroup(bodys);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return newbodys;
  }

  @Override
  protected boolean isActionEnable() {
    boolean broenable =
        this.model.getUiState() == UIState.NOT_EDIT
            && this.model.getSelectedData() != null;

    return broenable || this.model.getUiState() == UIState.EDIT
        || this.model.getUiState() == UIState.ADD;
  }

  /**
   * ��ÿ�Ƭ�ؼ�
   * 
   * @return ��Ƭ�ؼ�
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * ���model
   * 
   * @return modele
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * ���ÿ�Ƭ�ؼ�
   * 
   * @param editor
   */
  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  /**
   * ����model
   * 
   * @param model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

}
