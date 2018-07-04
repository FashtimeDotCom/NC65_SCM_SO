package nc.ui.so.m38.billui.action.assit;

import java.awt.event.ActionEvent;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.itf.so.m38.IPreOrderAssitFunc;

import nc.bs.framework.common.NCLocator;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.m38.billui.view.PreOrderListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * Ԥ�����йرհ�ť
 * 
 * @since 6.1
 * @version 2013-03-18 15:37:41
 * @author liujingn
 */
public class PreOrderRowCloseAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 5154115102907511740L;

  private PreOrderEditor editor;

  private PreOrderListView listView;

  private AbstractAppModel model;

  /**
   * �����б����
   * 
   * @param listView
   */
  public void setListView(PreOrderListView listView) {
    this.listView = listView;
  }

  /**
   * ���췽��
   */
  public PreOrderRowCloseAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_LINECLOSE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // �����Ƭ���б�ʱ��û��ѡ���У���ť������
    int cardselRow =
        this.editor.getBillCardPanel().getBillTable().getSelectedRow();
    int listselRow =
        this.listView.getBillListPanel().getBodyTable().getSelectedRow();
    if (cardselRow == -1 && listselRow == -1) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0",
              "04006012-0007")/*@res "����ѡ����"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    // ��ȡ����ѡ�����
    int[] newrows = this.getBodySelRows();
    PreOrderVO selectedData = (PreOrderVO) this.getModel().getSelectedData();
    PreOrderVO[] ret = null;
    IPreOrderAssitFunc service =
        NCLocator.getInstance().lookup(IPreOrderAssitFunc.class);
    try {
      ret = service.closePreOrderRows(selectedData, newrows);
      // ��̨�仯VO��ǰ̨�ϲ�
      ClientBillCombinServer<PreOrderVO> util =
          new ClientBillCombinServer<PreOrderVO>();
      util.combine(new PreOrderVO[] {
        selectedData
      }, ret);
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }
    this.model.directlyUpdate(new PreOrderVO[] {
      selectedData
    });
    this.showQueryInfo();
  }

  /**
   * 
   */
  protected void showQueryInfo() {
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0",
            "04006012-0008")/*@res "��ǰ�йرճɹ���"*/, this.getModel().getContext());
  }

  /**
   * 
   * @return PreOrderEditor
   */
  public PreOrderEditor getEditor() {
    return this.editor;
  }

  /**
   * 
   * @return AbstractAppModel
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * 
   * @param editor
   */
  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  /**
   * 
   * @param model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    // ��Ӱ�ť״̬���ü���(isActionEnable)
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    // �����Ƭ���б�ʱ��û��ѡ���У���ť������
    int cardselRow =
        this.editor.getBillCardPanel().getBillTable().getSelectedRow();
    int listselRow =
        this.listView.getBillListPanel().getBodyTable().getSelectedRow();
    if (cardselRow == -1 && listselRow == -1) {
      return false;
    }
    if (this.model.getUiState() == UIState.NOT_EDIT) {

      PreOrderVO selectedData = (PreOrderVO) this.getModel().getSelectedData();
      if (selectedData == null) {
        return false;
      }
      // �����ݲ������״̬�������йرն���
      if (!BillStatus.AUDIT.equalsValue(selectedData.getParentVO()
          .getFstatusflag())) {
        return false;
      }
      // ��ȡ����ѡ�����
      int[] rows = this.getBodySelRows();
      // ����Ƕ�ѡ����ť����
      if (rows.length > 1) {
        return true;
      }
      else if (rows.length == 1) {
        int row = rows[0];
        // �����޸Ľ��棬������n��Ȼ����ȡ������ʱ�����ڿ���ѡ�еĽ���ͽ��������ݵ����������ϣ��򲻿��Խ����в���
        PreOrderBVO[] items = selectedData.getChildrenVO();
        if (row >= items.length) {
          return false;
        }
        PreOrderBVO item = items[rows[0]];
        // ������йر��ѹر�s��ť�û�
        if (UFBoolean.TRUE.equals(item.getBlineclose())) {
          return false;
        }
      }
    }
    return true;
  }

  private int[] getBodySelRows() {
    int[] rows = null;
    // ��Ƭ����
    if (((ShowUpableBillForm) this.editor).isComponentVisible()) {
      rows = this.editor.getBillCardPanel().getBillTable().getSelectedRows();
    }
    else {// �б����
      rows = this.listView.getBillListPanel().getBodyTable().getSelectedRows();
    }
    return rows;
  }

}
