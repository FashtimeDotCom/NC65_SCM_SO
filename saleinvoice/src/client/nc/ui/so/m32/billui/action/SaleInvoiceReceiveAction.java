package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.saleinvoice.api.ISaleinvoiceReceive;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;

public class SaleInvoiceReceiveAction extends AbstractReferenceAction {

//	@Override
//	protected boolean isActionEnable() {
//		// TODO �Զ����ɵķ������
//		return true;
//	}
	private AbstractAppModel model;
	
	public AbstractAppModel getModel() {
		return this.model;
	}

	public void setModel(AbstractAppModel model) {
	    this.model = model;
	    model.addAppEventListener(this);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		ISaleinvoiceReceive is = NCLocator.getInstance().lookup(
	    		ISaleinvoiceReceive.class);
		 //��ӿڷ�������
	    String b = is.receiveMsgCGAG000003();
	    
	    // �ӿ����ݷ��ͳɹ���д���м��
	    if ("".equals(b)) {
	    	ShowStatusBarMsgUtil.showStatusBarMsg("���ݽ��ճɹ�!",model.getContext());

		}else {
			try {
				nc.vo.pubapp.pattern.exception.ExceptionUtils.marsh(null);
			} catch (Exception ex) {
				// TODO �Զ����ɵ� catch ��
				ExceptionUtils.wrappBusinessException(b);
			}
		}

	}

}
