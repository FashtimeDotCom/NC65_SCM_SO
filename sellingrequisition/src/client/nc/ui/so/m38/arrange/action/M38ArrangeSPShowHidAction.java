package nc.ui.so.m38.arrange.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

import nc.pubitf.ic.onhand.IOnhandPanelFacade;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.style.Style;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.pubapp.billref.push.TabBillInfo;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.uif2.LoginContext;

public class M38ArrangeSPShowHidAction extends NCAction implements IBillPush {

  /**
   * 
   */
  private static final long serialVersionUID = -5533751514059031205L;

  private BillContext context;

  // �ִ�����ʾ���
  private IOnhandPanelFacade onhandFacade;

  // �Ƿ���ʾ��
  private boolean show;

  // ��Ƭ����
  private TabBillInfo tabBillInfo;

  private LoginContext logincontext;

  public M38ArrangeSPShowHidAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDINFO);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (null != this.getOnhandFacade()) {
      // ��ʾ����
      this.getOnhandFacade().getOnhandInfoPanel()
          .setPreferredSize(new Dimension(300, 180));
      this.getOnhandFacade()
          .getOnhandInfoPanel()
          .setBorder(
              BorderFactory.createMatteBorder(1, 0, 0, 0,
                  Style.getColor("ϵͳ��ť��.����")));/* -=notranslate=- */
      this.show = !this.show;
      this.getBillContext()
          .getBillTabPanel()
          .showExtendedPanel(
              (UIPanel) this.getOnhandFacade().getOnhandInfoPanel(), this.show);

    }
  }

  @Override
  public BillContext getBillContext() {
    return this.context;
  }

  public IOnhandPanelFacade getOnhandFacade() {
    if (null == this.onhandFacade) {
      this.onhandFacade =
          NCUILocator.getInstance().lookup(IOnhandPanelFacade.class,
              NCModule.IC.getName());
      this.onhandFacade.freshData(null);
    }
    return this.onhandFacade;
  }

  public TabBillInfo getTabBillInfo() {
    return this.tabBillInfo;
  }

  public LoginContext getLogincontext() {
    return this.logincontext;
  }

  public void setLogincontext(LoginContext logincontext) {
    this.logincontext = logincontext;
  }

  @Override
  public void setBillContext(BillContext billcontext) {
    this.context = billcontext;
  }

  public void setTabBillInfo(TabBillInfo table) {
    this.tabBillInfo = table;
  }

}
