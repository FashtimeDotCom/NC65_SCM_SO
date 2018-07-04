package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.m32.billui.view.SaleInvoiceListView;
import nc.ui.uif2.NCAction;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;

/**
 * ��ʾ���ܺ���ʾ��ϸ�л���ť
 * 
 * @since 6.3
 * @version 2012-12-21 ����11:19:49
 * @author yaogj
 */
public class SaleInvoiceShowChgAction extends NCAction {

  private static final long serialVersionUID = -8092390829238861201L;

  private SaleInvoiceEditor editor;

  private SaleInvoiceManageModel model;

  private SaleInvoiceListView listView;

  private LazilyLoadManager lasilyLoder;

  /**
   * ���췽��
   */
  public SaleInvoiceShowChgAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    CombinCacheVO cachevo = this.getModel().getCombinCacheVO();
    List<SaleInvoiceVO> alldata = this.getModel().getData();
    SaleInvoiceVO[] alldatas =
        alldata.toArray(new SaleInvoiceVO[alldata.size()]);

    SaleInvoiceCombin combinutil = new SaleInvoiceCombin();

    int selectedRow = this.getModel().getSelectedRow();
    // jilu for 633 ��ʾ����ʱ����ģ��ȫ����
    int rowCount = this.getModel().getRowCount();
    if (rowCount > 0) {
      this.lasilyLoder.changeChildren(this.getAllChildrenClass(),
          (IBill[]) this.getModel().getData().toArray(new IBill[0]));
    }
    // end

    SaleInvoiceVO[] combinvos = null;
    // ��ʾ����->��ʾ��ϸ
    if (cachevo.getBcombinflag()) {
      cachevo.setBcombinflag(false);
      cachevo.setMapGroupKeys(null);
      try {
        combinvos =
            combinutil
            .splitNoEditSaleInvoice(alldatas, cachevo.getCombinRela());
      }
      catch (Exception ex) {
        // TODO: handle exception
        // ��׽���쳣����ǰ״̬������Ϊ�ϲ� zhangby5 2014.7.31
        cachevo.setBcombinflag(true);
        ExceptionUtils.wrappException(ex);
      }
      this.setBtnName(NCLangRes.getInstance().getStrByID("4006008_0",
          "04006008-0081")/* ��ʾ���� */);
      cachevo.setCombinrela(new MapList<String, SaleInvoiceBVO>());

    }
    // ��ʾ��ϸ->��ʾ����
    else {
      cachevo.setBcombinflag(true);
      cachevo.setCombinrela(null);
      this.getModel().setCombinCacheVO(cachevo);
      try {
        combinvos = this.getModel().getCombinreVO(alldatas);
      }
      catch (Exception ex) {
        // TODO: handle exception
        // ��׽���쳣����ǰ״̬������Ϊ��ϸ zhangby5 2014.7.31
        cachevo.setBcombinflag(false);
        ExceptionUtils.wrappException(ex);

      }
      this.setBtnName(NCLangRes.getInstance().getStrByID("4006008_0",
          "04006008-0082")/* ��ʾ��ϸ */);
    }
    this.getModel().setProcesscombin(true);
    this.getModel().initModel(combinvos);
    this.getModel().setSelectedRow(selectedRow);
  }

  /**
   * 
   * @return ��Ʊ��Ƭ
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * ������������������model���ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-6-21 ����09:46:05
   */
  public SaleInvoiceManageModel getModel() {
    return this.model;
  }

  /**
   * ���ؼ�
   * 
   * @param editor
   *          ���ؼ�
   */
  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  /**
   * ������������������model���ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param model
   *          <p>
   * @author fengjb
   * @time 2010-6-21 ����09:46:19
   */
  public void setModel(SaleInvoiceManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  public SaleInvoiceListView getListView() {
    return this.listView;
  }

  public void setListView(SaleInvoiceListView listView) {
    this.listView = listView;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_GATHERDISPLAY);
    SaleInvoiceCombin combin = new SaleInvoiceCombin();
    if (combin.getSO27()) {
      this.setBtnName(NCLangRes.getInstance().getStrByID("4006008_0",
          "04006008-0082")/* ��ʾ��ϸ */);
    }
    else {
      this.setBtnName(NCLangRes.getInstance().getStrByID("4006008_0",
          "04006008-0081")/* ��ʾ���� */);
    }
  }

  public List<Class<? extends ISuperVO>> getAllChildrenClass() {
    List<Class<? extends ISuperVO>> classList =
        new ArrayList<Class<? extends ISuperVO>>();
    IBill aggVo = (IBill) this.getModel().getSelectedData();
    if (aggVo != null) {
      IVOMeta[] voMeta = aggVo.getMetaData().getChildren();
      for (IVOMeta childMeta : voMeta) {
        Class<? extends ISuperVO> childClass =
            aggVo.getMetaData().getVOClass(childMeta);
        classList.add(childClass);
      }
    }
    return classList;
  }

  public LazilyLoadManager getLasilyLoder() {
    return this.lasilyLoder;
  }

  public void setLasilyLoder(LazilyLoadManager lasilyLoder) {
    this.lasilyLoder = lasilyLoder;
  }
}
