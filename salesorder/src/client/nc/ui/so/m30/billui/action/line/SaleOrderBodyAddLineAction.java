package nc.ui.so.m30.billui.action.line;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SpecialBusiUtil;

public class SaleOrderBodyAddLineAction extends BodyAddLineAction {

	  /**
	 * 
	 */
	private static final long serialVersionUID = -6698994471507680470L;

	
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
