package nc.ui.so.m30.billui.action.line;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction;
import nc.ui.so.m30.billui.rule.CmffilePasteRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SpecialBusiUtil;


public class SaleOrderPasteToTailAction extends BodyPasteToTailAction{
  
  /**
   * 
   */
  private static final long serialVersionUID = -7322247558607162166L;

  @Override
  public void doAction() {
    super.doAction();
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
