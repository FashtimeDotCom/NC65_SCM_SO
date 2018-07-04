package nc.ui.so.m30.billui.action.line;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.BodyPasteLineAction;
import nc.ui.so.m30.billui.rule.CmffilePasteRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SpecialBusiUtil;

/**
 * ������ʱ�ж��Ƿ����ѯ��
 * 
 * @author quyt
 * 
 */
public class SaleOrderPasteLineAction extends BodyPasteLineAction {

  /**
   * 
   */
  private static final long serialVersionUID = -6298734043503954122L;

  private SaleOrderBillForm editor;

  public SaleOrderBillForm getEditor() {
    return editor;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  @Override
  public void doAction() {
    super.doAction();
    // TODO ���� ����ͣ���������۽��ۣ������в�ѯ�ۡ�
    CmffilePasteRule pastRule = new CmffilePasteRule();
    pastRule.process(getCardPanel(), this.lastPastedRow());
    
  }
  
  @Override
  protected boolean doBeforeAction(ActionEvent e) {

	  boolean isdo = super.doBeforeAction(e);
	  if(isdo){
	  	//==== lijj ����Ƿ������ν��ں�ͬ���ж�=====
		SpecialBusiUtil busiUtil = new SpecialBusiUtil();
		SaleOrderVO bill = (SaleOrderVO) this.getModel().getSelectedData();
	
		if(bill != null){
			boolean hasLowerBill = busiUtil.hasLowerBill(bill.getPrimaryKey());
			if(hasLowerBill){
				MessageDialog.showHintDlg(null, "��ʾ", "�����ɽ��ں�ͬ���ܽ��д˲�����");
				return false;
			}
		}
		//==== lijj ����Ƿ������ν��ں�ͬ���ж�=====
	  }
	  
	  return true;
  }
}
