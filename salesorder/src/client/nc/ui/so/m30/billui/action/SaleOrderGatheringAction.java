/**
 * $�ļ�˵��$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-30 ����03:50:51
 */
package nc.ui.so.m30.billui.action;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.m30.self.ISaleOrderBusi;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.funclink.SaleOrderGatheringLinkListener;
import nc.ui.uif2.NCAction;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author gdsjw
 * @time 2010-6-30 ����03:50:51
 */
@SuppressWarnings("serial")
public class SaleOrderGatheringAction extends NCAction {

  private BillForm editor;

  private BillManageModel model;

  public SaleOrderGatheringAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_ORDERPAY);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isAREnabled()) {
      ExceptionUtils
      .wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0518")/*����ģ��δ���ã�*/);
    }
    try {
      SaleOrderVO ordvo = (SaleOrderVO) this.model.getSelectedData();
      ISaleOrderBusi service =
          NCLocator.getInstance().lookup(ISaleOrderBusi.class);
      AggregatedValueObject[] destVos = service.prepareOrderGathering(ordvo);

      FuncletInitData initData = new FuncletInitData();
      initData.setInitType(ILinkType.LINK_TYPE_ADD);
      initData.setInitData(destVos);

      // �տ�ڵ��
      FuncRegisterVO funvo =
          WorkbenchEnvironment.getInstance().getFuncRegisterVO("20060GBR");
      if (null == funvo) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes.getInstance().getStrByID(
                "4006011_0", "04006011-0448")/*��ǰ�û�û�д򿪶����տ�ڵ��Ȩ�ޣ�����*/);
      }
      SaleOrderGatheringLinkListener linkListener =
          new SaleOrderGatheringLinkListener();
      linkListener.setModel(this.getModel());
      
      int screenWidth =
          Toolkit.getDefaultToolkit().getScreenSize().width;
      int screenHeight =
          Toolkit.getDefaultToolkit().getScreenSize().height -1;
      FuncletWindowLauncher.openFuncNodeForceModalDialog(this.getModel().getContext()
          .getEntranceUI(), funvo, initData, null, true, new Dimension(
          screenWidth, screenHeight),true);
    }
    catch (BusinessException be) {
      ExceptionUtils.wrappException(be);
    }
  }

  public BillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    Object selectedData = this.getModel().getSelectedData();
    boolean isEnable =
        this.model.getAppUiState() == AppUiState.NOT_EDIT
            && selectedData != null;

    return isEnable;
  }
}
