package nc.ui.so.m32.billui.action.link;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.dlg.CashArsubDetailFor32Dlg;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * ���۷�Ʊ���ó�������ѯ12
 * 
 * @since 6.50
 * @version 2015-08-14 11:03:00
 * @author liylr
 */
public class QueryArsubFor32Action extends NCAction {

  private static final long serialVersionUID = 2110541669625985035L;

  /**
   * ���۷�Ʊ��Ƭ����
   */
  private SaleInvoiceEditor editor;

  /**
   * ģ�͹�����
   */
  private AbstractAppModel model;

  /**
   * ���캯��
   */
  public QueryArsubFor32Action() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_OFFSETINFO);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleInvoiceVO vo = (SaleInvoiceVO) this.getModel().getSelectedData();
    if (null == vo) {
      return;
    }
    SaleInvoiceHVO hVO = vo.getParentVO();
    if (hVO != null) {
      String[] saleBillIDs = new String[] {
          hVO.getCsaleinvoiceid()
      };
      CashArsubDetailFor32Dlg dlg =
          new CashArsubDetailFor32Dlg(this.getEditor().getBillCardPanel(),
              saleBillIDs, true);
      dlg.showModal();
    }
  }

  @Override
  protected boolean isActionEnable() {
    SaleInvoiceVO vo = (SaleInvoiceVO) this.getModel().getSelectedData();
    if (vo != null && vo.getParentVO() != null
        &&!PubAppTool.isNull(vo.getParentVO().getVbillcode())) {
      return true;
    }
    return false;
  }

  /**
   * ��ȡ���۷�Ʊ��Ƭ
   * 
   * @return SaleInvoiceEditor
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * �������۷�Ʊ��Ƭ
   * 
   * @param editor
   */
  public void setEditor(SaleInvoiceEditor editor) {
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
