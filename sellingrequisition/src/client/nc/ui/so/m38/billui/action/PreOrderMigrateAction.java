package nc.ui.so.m38.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.so.m38.IPreOrderMigrate;
import nc.ui.ls.MessageBox;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @author liylr
 */
@SuppressWarnings("restriction")
public class PreOrderMigrateAction extends NCAction {

  public PreOrderMigrateAction() {
    super();
    this.setBtnName(NCLangRes.getInstance().getStrByID("4006012_0",
        "04006012-0102")/* Ǩ�� */);
    this.setCode("preorderMigdata");
  }

  private static final long serialVersionUID = -7952608831277926346L;

  @Override
  public synchronized void doAction(ActionEvent arg0) throws Exception {
    try {
      int dlgResult =
          MessageDialog.showYesNoDlg(WorkbenchEnvironment.getInstance()
              .getWorkbench().getParent(), NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0004")/*��ʾ*/,
              NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0122")/*��ȷ���Ƿ��������Ԥ��������������Ԥ����Ǩ�ƣ�\r\nǨ�ƹ����п��ܺ�ʱ�ϳ���*/);
      if (UIDialog.ID_YES == dlgResult) {
        NCLocator.getInstance().lookup(IPreOrderMigrate.class)
            .migratePreOrder();
        MessageBox
        .showMessageDialog(
            NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0004")/* ��ʾ */,
            NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0103")/* �����ɹ��� */);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
