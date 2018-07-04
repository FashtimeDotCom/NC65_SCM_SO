package nc.ui.so.m32.billui.action.add;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.CombinResultVO;
import nc.vo.so.m32.util.OpposeConvertUtil;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.pubitf.so.m33.so.m32.ISquare33For32Rush;

import nc.bs.framework.common.NCLocator;

import nc.ui.scmpub.action.ReferenceAction;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * ���ɶԳ巢Ʊ������
 * 
 * @since 6.0
 * @version 2011-9-14 ����09:32:41
 * @author ô��
 */
public class OpposeAddAction extends ReferenceAction {

  /**
   * Version
   */
  private static final long serialVersionUID = 4935922265324881330L;

  /**
   * ��Ƭ
   */
  private SaleInvoiceEditor editor;

  /**
   * ģ�͹�����
   */
  private AbstractAppModel model;

  /**
   * ������
   */
  public OpposeAddAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // ��ȡ��Ʊ�Գ�VO
    SaleInvoiceVO selectVO = (SaleInvoiceVO) this.model.getSelectedData();
    // �ϲ�״̬��
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    SaleInvoiceCombin combinutil = new SaleInvoiceCombin();

    List<String> idset = new ArrayList<String>();
    idset.add(selectVO.getPrimaryKey());
    ISquare33For32Rush srv =
        NCLocator.getInstance().lookup(ISquare33For32Rush.class);
    srv.checkIFCanInvoiceRush(idset.toArray(new String[0]));
    UFDate busidate = AppContext.getInstance().getBusiDate();
    if (null != cachevo && cachevo.getBcombinflag()) {
      SaleInvoiceVO[] detailvos =
          combinutil.getOldDetailVOs(new SaleInvoiceVO[] {
            selectVO
          }, cachevo.getCombinRela());
      selectVO = detailvos[0];
    }
    SaleInvoiceVO oppInvoice =
        OpposeConvertUtil.getInstance().convertToOpposeVO(selectVO,
            this.getModel().getContext(), busidate);

    if (null != cachevo && cachevo.getBcombinflag()) {

      combinutil.processVOBids(new SaleInvoiceVO[] {
        oppInvoice
      });
      CombinResultVO resultvo =
          combinutil.combinSaleInvoices(new SaleInvoiceVO[] {
            oppInvoice
          }, cachevo);
      oppInvoice = resultvo.getCombinvos()[0];
    }
    this.model.setUiState(UIState.ADD);
    // �������ݵ���Ƭ����
    this.editor.setValue(oppInvoice);
    // ���ñ༭��
    this.editor.setCardEditEnable();
  }

  /**
   * ������۷�ƱbillForm
   * 
   * @return SaleInvoiceEditor
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * ���model
   * 
   * @return AbstractAppModel
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * �������۷�ƱbillForm
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

  @Override
  protected boolean isActionEnable() {

    SaleInvoiceVO selectVO = (SaleInvoiceVO) this.model.getSelectedData();
    Integer status = (Integer) BillStatus.FREE.value();
    Integer hedgeflag = (Integer) OpposeFlag.NORMAL.value();
    if (null != selectVO && null != selectVO.getParent()) {
      status = selectVO.getParentVO().getFstatusflag();
      hedgeflag = selectVO.getParentVO().getFopposeflag();
    }
    boolean isEnable =
        this.model.getUiState() == UIState.NOT_EDIT
            && BillStatus.AUDIT.equalsValue(status)
            && OpposeFlag.NORMAL.equalsValue(hedgeflag);

    return isEnable;
  }

  @Override
  protected boolean isManual() {
    return true;
  }

}
