package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JDialog;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.ml.NCLangRes;
import nc.ui.uif2.NCAction;

/**
 * �޶���ʷ�ڵ㷵�ذ�ť
 * 
 * @since 6.0
 * @version 2011-6-10 ����01:55:10
 * @author ��־ΰ
 */
public class M30ReviseReturnAction extends NCAction {

  private static final long serialVersionUID = -5738936479886531979L;

  public M30ReviseReturnAction() {
    this.setBtnName(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0387")/*����*/);
    this.setCode("ReviseHisReturn");
    this.putValue(Action.SHORT_DESCRIPTION, NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0387")/*����*/);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    JDialog dialog =
        (JDialog) WorkbenchEnvironment.getInstance().findOpenedFuncletWindow(
            M30ReviseHistoryAction.HISTORY_FUNCODE);
    dialog.setVisible(false);
    dialog.dispose();
  }
}
