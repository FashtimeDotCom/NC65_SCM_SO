package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pub.pf.PfUtilTools;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.md.data.access.NCObject;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.dm.pub.parapanel.TransferViewProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.org.OrgVO;
import nc.vo.pp.m28.entity.PriceAuditHeaderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.model.AbstractAppModel;

public class SaleInvoiceSendysdAction extends AbstractReferenceAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2599372460525675314L;

	private ShowUpableBillForm editor;
	
	private AbstractAppModel model;

	//ת����transfer
	private TransferViewProcessor transferProcessor;
	
	public TransferViewProcessor getTransferProcessor() {
		return transferProcessor;
	}
	public void setTransferProcessor(TransferViewProcessor transferProcessor) {
		this.transferProcessor = transferProcessor;
	}
	public ShowUpableBillForm getEditor() {
		return editor;
	}
	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}
	
	public AbstractAppModel getModel() {
		return this.model;
	}

	public void setModel(AbstractAppModel model) {
	    this.model = model;
	    model.addAppEventListener(this);
	}
	
	@Override
	protected boolean isActionEnable() {
		//��ѡ��ť������
		Object[] selectedRows = ((BillManageModel)model).getSelectedOperaDatas();
		if(selectedRows != null && selectedRows.length > 1){
			return false;
		}
		
		
		SaleInvoiceVO selectedData = (SaleInvoiceVO) this.model.getSelectedData();
		if(selectedData == null){
			return false;
		}
		
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		//��Ʊ��֯ 
		String pk_org = selectedData.getParentVO().getPk_org();
		//��������
		String ctrantypeid = selectedData.getParentVO().getCtrantypeid();
		
		try {
			//��֯����Ϊ 3006 ���ֹ�ó���ʻ������޹�˾
			OrgVO orgvo = (OrgVO)query.retrieveByPK(OrgVO.class, pk_org);
			if(orgvo == null || !"3006".equals(orgvo.getCode())){
				return false;
			}
			
			//�������ͱ���Ϊ 32-Cxx-03	�ֲĳ��ں��˷�
			BilltypeVO billtype = (BilltypeVO)query.retrieveByPK(BilltypeVO.class, ctrantypeid);
			if(billtype == null || !"32-Cxx-03".equals(billtype.getPk_billtypecode())){
				return false;
			}
			
		} catch (BusinessException e) {
			Logger.error(e.getMessage(), e);
		}
		
		return true;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// ��֧�����б��£�ѡ�ж��м�¼��Ҫ��getModel().getSelectedOperaDatas()ѡ�ж��vo
		SaleInvoiceVO selectedVO = (SaleInvoiceVO) this.getModel().getSelectedData();
		if (null == selectedVO ||null==selectedVO.getChildren(SaleInvoiceBVO.class)) {
			return;
		}
		// �����Ƶ�������£������޷�ˢ�£���Ҫ�����ݿ��в�ѯ���ݣ������Ƶ�,���򶩵�����Ტ��
	    String id = (String) selectedVO.getParentVO().getAttributeValue("csaleinvoiceid");
	    NCObject queryBillOfVOByPK = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByPKWithDR(SaleInvoiceHVO.class, id,true);
	    SaleInvoiceVO vo = (SaleInvoiceVO) queryBillOfVOByPK.getContainmentObject();
	    
	    //����Ӧ�յ�
		String sql="select pk_recbill from ar_recitem where top_billid	='"+id+"' and dr=0";
		IUAPQueryBS uapQuery = (IUAPQueryBS) NCLocator.getInstance().lookup(nc.itf.uap.IUAPQueryBS.class);
		List<?> resultList = (List<?>) uapQuery.executeQuery(sql, new ArrayListProcessor());
		if(resultList != null && resultList.size() > 0){
			MessageDialog.showHintDlg(null, "��ʾ", "����Ӧ�յ��������ظ��Ƶ���");
			return;
		}
	    /*
		String vdef8 = vo.getParentVO().getVdef8();
		if(vdef8 != null && !"0".equals(vdef8)){
			MessageDialog.showHintDlg(null, "��ʾ", "����Ӧ�յ��������ظ��Ƶ���");
			return;
		}
		*/
		// ������ѡ��ťȷ���Ƶ�
		this.openSaleOrderDlg(vo);
	  }

	private void openSaleOrderDlg(SaleInvoiceVO selectedVO) {

		SaleInvoiceBVO[] children = (SaleInvoiceBVO[]) selectedVO.getChildren(SaleInvoiceBVO.class);
		List<SaleInvoiceBVO> newChildren = new ArrayList<SaleInvoiceBVO>();
		for (SaleInvoiceBVO child : children) {
			 newChildren.add(child);
		}
	    selectedVO.setChildrenVO(newChildren
	        .toArray(new SaleInvoiceBVO[newChildren.size()]));
	    AggregatedValueObject[] retSrcVos = new AggregatedValueObject[] {
	      selectedVO
	    };
	    AggregatedValueObject[] srcVosAfterFilter =  retSrcVos;
	    
	    AggReceivableBillVO destVo = null;
	    try {
			destVo = (AggReceivableBillVO)PfUtilTools.runChangeData("32-Cxx-03", "F0", srcVosAfterFilter[0]);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
	   
	    if(destVo == null){
	    	return;
	    }
	    
	    this.fillDestVOs(new AggReceivableBillVO[]{destVo});	
	    FuncletInitData initData = null;
	    initData = new FuncletInitData();
	    initData.setInitType(20);
	    initData.setInitData(new AggReceivableBillVO[]{destVo});
	    FuncRegisterVO funvo = WorkbenchEnvironment.getInstance().getFuncRegisterVO("20060RBR");
		if (null == funvo) {
			  ExceptionUtils.wrappBusinessException("����ע�ᴦ�Ҳ���code=20060RBR��");
		}
		FuncletWindowLauncher.openFuncNodeDialog(WorkbenchEnvironment.getInstance().getWorkbench(), funvo, initData, null, true, false);
	}
	private void fillDestVOs(AggReceivableBillVO[] destVos) {
		// ���Ŀ��vo�������۶���vo
		for (AggReceivableBillVO destVo : destVos) {
		        Object pk_orgObj =
		          destVo.getParentVO().getAttributeValue(PriceAuditHeaderVO.PK_ORG);
				if (null == pk_orgObj || StringUtils.isEmpty(pk_orgObj.toString())) {
					break;
				}
		        String pk_org = pk_orgObj.toString();
		        String pk_org_v = null;
				if (!StringUtils.isEmpty(pk_org)) {
				        pk_org_v = OrgUnitPubService.getOrgVid(pk_org);
				      }
				boolean blen = OrgUnitPubService.isTypeOf(pk_org, IOrgConst.FINANCEORGTYPE);
	
				if(destVo.getChildrenVO() != null){
					for (CircularlyAccessibleValueObject itemvo : destVo.getChildrenVO()) {
						if (blen) {
							itemvo.setAttributeValue("pk_financeorg", pk_org);
							itemvo.setAttributeValue("pk_financeorg_v", pk_org_v);
						}else {
							OrgVO vo = OrgUnitPubService.getOrg(pk_org);
							String pk_corp = vo.getPk_corp();
							String pk_corp_v = OrgUnitPubService.getOrgVid(pk_corp);
							itemvo.setAttributeValue("pk_financeorg", pk_corp);
							itemvo.setAttributeValue("pk_financeorg_v", pk_corp_v);
						}
					}
				}
		}
	}

}
