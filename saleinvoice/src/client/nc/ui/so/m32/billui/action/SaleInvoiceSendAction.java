package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.util.ApproveFlowUtil;
import nc.md.data.access.NCObject;
import nc.pubitf.so.saleinvoice.api.ISaleinvoiceReceive;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveStatus;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class SaleInvoiceSendAction extends AbstractReferenceAction {
	
	private AbstractAppModel model;
	
	public AbstractAppModel getModel() {
		return this.model;
	}

	public void setModel(AbstractAppModel model) {
	    this.model = model;
	    model.addAppEventListener(this);
	}
	
	@Override
	protected boolean isActionEnable() {
		// TODO �Զ����ɵķ������

		Object selectedData = this.model.getSelectedData();
		int status = ApproveStatus.FREE;
		if (selectedData != null) {
			NCObject obj = NCObject.newInstance(selectedData);
			if (obj != null) {
				status = ApproveFlowUtil.getBillStatus(obj).intValue();
			}
		}

		// �����л�����ͨ��ʱ�����Ͱ�ť����
		boolean isEnable = this.model.getAppUiState() == AppUiState.NOT_EDIT
				&& selectedData != null
				&& (status == ApproveStatus.APPROVED || status == ApproveStatus.NOPASS);

		return isEnable;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		//��ӿڷ�������
	    ISaleinvoiceReceive is = NCLocator.getInstance().lookup(
	    		ISaleinvoiceReceive.class);
	    Object obj =  getModel().getSelectedData();//��ȡ��ѡ
	    SaleInvoiceVO saleInvoiceVO = new SaleInvoiceVO();
	    saleInvoiceVO = (SaleInvoiceVO)NCObject.newInstance(obj).getContainmentObject();
	    SaleInvoiceHVO saleInvoiceHVO = saleInvoiceVO.getParentVO();
	  //�Ƿ��͹�
	    String isSend = saleInvoiceHVO.getVdef18();
	    if ("1".equals(isSend)) {
	    	ExceptionUtils.wrappBusinessException("���������Ѿ����͹�");
		}
	    String vgoldtaxcode = saleInvoiceHVO.getVgoldtaxcode();
	    if (vgoldtaxcode==null&&saleInvoiceHVO.getVdef2()==null) {
	    	ExceptionUtils.wrappBusinessException("��Ʊ��Ϊ��");
		}
	    //��ӿڷ�������
	    boolean b = is.sendMsgAGCG000003(saleInvoiceVO);
	    
	    // �ӿ����ݷ��ͳɹ���д���м��
	    if (b) {
	    	ShowStatusBarMsgUtil.showStatusBarMsg("���ݷ��ͳɹ�!",model.getContext());

			is.sendZjbInsert(saleInvoiceVO);
		}else {
			try {
				nc.vo.pubapp.pattern.exception.ExceptionUtils.marsh(null);
			} catch (Exception ex) {
				// TODO �Զ����ɵ� catch ��
				ExceptionUtils.wrappBusinessException("���ݷ��Ͳ��ɹ�");
			}
		}
	}

}
