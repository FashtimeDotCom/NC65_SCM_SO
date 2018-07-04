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
import nc.itf.so.m30.sobalance.ISOBalanceQuery;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.funclink.SaleOrderGatheringLinkListener;
import nc.ui.so.m30.sobalance.link.SoBalanceLinkMaintainData;
import nc.ui.uif2.NCAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.util.SoBalanceUtil;

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
public class SaleOrderReceiveVerifyAction extends NCAction {

  private BillForm editor;

  private BillManageModel model;

  public SaleOrderReceiveVerifyAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_PAYVERIFY);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleOrderVO ordvo = (SaleOrderVO) this.getModel().getSelectedData();
    try {
      ISOBalanceQuery query =
          NCLocator.getInstance().lookup(ISOBalanceQuery.class);
      // ��ѯ�տ����VO
      SoBalanceVO[] balancevos =
          query.querySoBalanceVOBySaleOrderIDs(new String[] {
            ordvo.getPrimaryKey()
          });
      if (balancevos == null || balancevos.length == 0) {
        SoBalanceHVO head =
            SoBalanceUtil.getInstance().createSoBalanceHVOBySaleOrderVO(ordvo);
        head.setDr(Integer.valueOf(0));
        head.setStatus(VOStatus.NEW);
        balancevos = new SoBalanceVO[] {
          new SoBalanceVO()
        };
        balancevos[0].setParentVO(head);
        balancevos[0].setChildrenVO(new SoBalanceBVO[0]);
      }

      SoBalanceLinkMaintainData data = new SoBalanceLinkMaintainData();
      data.setBillID(balancevos[0].getPrimaryKey());
      data.setUserObject(balancevos);
      FuncletInitData initData = new FuncletInitData();
      initData.setInitType(ILinkType.LINK_TYPE_MAINTAIN);
      initData.setInitData(data);
      SaleOrderGatheringLinkListener listener =
          new SaleOrderGatheringLinkListener();
      FuncRegisterVO funvo =
          WorkbenchEnvironment.getInstance().getFuncRegisterVO("40060304");
      if (null == funvo) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes.getInstance().getStrByID(
                "4006011_0", "04006011-0435")/*��ǰ�û�û�д��տ�����ڵ��Ȩ�ޣ�����*/);
      }
      // ���տ�����ڵ�
      int screenWidth =
          Toolkit.getDefaultToolkit().getScreenSize().width * 9 / 10;
      int screenHeight =
          Toolkit.getDefaultToolkit().getScreenSize().height * 9 / 10;
      FuncletWindowLauncher.openFuncNodeForceModalDialog(this.getModel().getContext()
          .getEntranceUI(), funvo, initData, null, true, new Dimension(
          screenWidth, screenHeight),true);
    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
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
