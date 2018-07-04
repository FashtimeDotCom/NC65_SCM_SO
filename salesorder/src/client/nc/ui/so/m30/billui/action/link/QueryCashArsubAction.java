package nc.ui.so.m30.billui.action.link;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.dlg.CashArsubDetailDlg;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ���öҸ���ϸ��ѯ
 * 
 * @since 6.35
 * @version 2013-12-11 10:43:02
 * @author ������
 */
public class QueryCashArsubAction extends NCAction {

  private static final long serialVersionUID = 2110541669625985035L;

  /**
   * ���۶�����Ƭ����
   */
  private SaleOrderBillForm editor;

  /**
   * ģ�͹�����
   */
  private AbstractAppModel model;

  /**
   * ���캯��
   */
  public QueryCashArsubAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_CASHARSUBDETAIL);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
    if (null == vo) {
      return;
    }
    SaleOrderHVO hVO = vo.getParentVO();
    if (hVO != null) {
      String[] saleBillIDs = new String[] {
          hVO.getCsaleorderid()
      };
      CashArsubDetailDlg dlg =
          new CashArsubDetailDlg(this.getEditor().getBillCardPanel(),
              saleBillIDs, true);
      dlg.showModal();
    }
  }

  @Override
  protected boolean isActionEnable() {
    SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
    if (vo != null && vo.getParentVO() != null
        &&!PubAppTool.isNull(vo.getParentVO().getVbillcode())) {
      return true;
    }
    return false;
  }

  /**
   * ��ȡ���۶�����Ƭ
   * 
   * @return SaleOrderBillForm
   */
  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  /**
   * �������۶�����Ƭ
   * 
   * @param editor
   */
  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  /**
   * ��ȡģ��
   * 
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * ����ģ��
   * 
   * @param model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

}
