package nc.ui.so.m30.arrange.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;

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
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.uif2.LoginContext;

@SuppressWarnings("restriction")
public class SaleorderATPShowHiddenAction extends NCAction implements IBillPush {
  private static final long serialVersionUID = 80830283833718816L;

  private BillContext context;

  // �ִ�����ʾ���
  private IOnhandPanelFacade onhandFacade;

  // �Ƿ���ʾ��
  private boolean show;

  // ��Ƭ����
  private TabBillInfo tabBillInfo;

  private LoginContext logincontext;

  public SaleorderATPShowHiddenAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDINFO);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (this.getOnhandFacade() != null) {
      // ��ʾ����
      this.getOnhandFacade().getOnhandInfoPanel()
          .setPreferredSize(new Dimension(300, 180));
      this.getOnhandFacade()
          .getOnhandInfoPanel()
          .setBorder(
              BorderFactory.createMatteBorder(1, 0, 0, 0,
                  Style.getColor("ϵͳ��ť��.����")));/* -=notranslate=- */
      // ���û��ѡ������ Ҫ���
      this.tabBillInfo.setViewShow(true);
      if (this.tabBillInfo.getBillTabPanel() != null) {
        if (this.tabBillInfo.getBillTabPanel().getBodySelectVOs() == null
            || this.tabBillInfo.getBillTabPanel().getBodySelectVOs().length == 0) {
          Method clearMethod =
              this.getOnhandFacade().getOnhandInfoPanel().getClass()
                  .getMethod("clearData");
          clearMethod.invoke(this.onhandFacade.getOnhandInfoPanel());
        }
      }
      this.show = !this.show;
      this.getBillContext()
          .getBillTabPanel()
          .showExtendedPanel(
              (UIPanel) this.getOnhandFacade().getOnhandInfoPanel(), this.show);
      // ��������viewshowΪfalse���������²�ѯ����
      this.tabBillInfo.setViewShow(false);
    }
  }

  @Override
  public BillContext getBillContext() {
    return this.context;
  }

  public LoginContext getLogincontext() {
    return this.logincontext;
  }

  public void setLogincontext(LoginContext logincontext) {
    this.logincontext = logincontext;
  }

  /**
   * �����ִ������档
   * 
   * @return
   * @author liutao
   * @throws BusinessException
   * @time 2011-1-10 ����04:57:16
   */
  public IOnhandPanelFacade getOnhandFacade() throws BusinessException {
    // ���ģ��û������
    if (this.onhandFacade == null) {
      this.onhandFacade =
          NCUILocator.getInstance().lookup(IOnhandPanelFacade.class,
              NCModule.IC.getName());
      this.onhandFacade.freshData(null);
      try {
        Method setContextMethod =
            this.onhandFacade.getOnhandInfoPanel().getClass()
                .getMethod("setLogincontext", new Class[] {
                  this.getLogincontext().getClass()
                });
        setContextMethod.invoke(this.onhandFacade.getOnhandInfoPanel(),
            this.getLogincontext());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return this.onhandFacade;
  }

  public TabBillInfo getTabBillInfo() {
    return this.tabBillInfo;
  }

  @Override
  public void setBillContext(BillContext billcontext) {
    this.context = billcontext;
  }

  public void setTabBillInfo(TabBillInfo table) {
    this.tabBillInfo = table;
  }
}
