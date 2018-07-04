package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.ui.pubapp.bill.BillListPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import com.ufida.iufo.table.drill.ReportDrillInfo;
import com.ufida.iufo.table.drill.ReportDrillItem;
import com.ufida.report.anareport.base.FreeReportDrillParam;
import com.ufida.report.free.publish.util.FreeReportFuncletUtil;

/**
 */
public class InterNNProAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6145316257087100164L;

	private SaleOrderBillForm editor;
	private BillListView listView;
	private BillManageModel model;

	public InterNNProAction() {
		super();
		this.setBtnName("���鱨��");
		this.setCode("InterNNProAction");
	}
	public void setBtnName(String btnName) {
		putValue(NAME,"���鱨��");
	}
	@Override
	public void doAction(ActionEvent e) throws Exception {
		BillListPanel listPanel = (BillListPanel) listView.getBillListPanel();
		BillCardPanel cardPanel = (BillCardPanel) editor.getBillCardPanel();
		Object[] objs = getModel().getSelectedOperaDatas();
		Object obj = getModel().getSelectedData();
		if(cardPanel.isShowing() && obj == null){
			ExceptionUtils.wrappBusinessException("û��ѡ�����ݣ�");
		}
		if(listPanel.isShowing()){
			if(objs == null || objs.length>1){
				ExceptionUtils.wrappBusinessException("��ѡ��һ�����ݣ�");
			}
		}
		AggregatedValueObject aggVO = (AggregatedValueObject) objs[0];
		String projectid = (String) aggVO.getParentVO().getAttributeValue("vdef8");
		if(projectid == null){
			ExceptionUtils.wrappBusinessException("��ĿΪ�գ�");
		}
		linkReport(projectid);
	}
	
	public void linkReport(String projectid){
		//���ɱ���͸��Ϣ��
		ReportDrillItem[] drillItems = new ReportDrillItem[1];
		//ҳά�ȿͻ�����
		drillItems[0] = new ReportDrillItem();
		drillItems[0].setConditionType(ReportDrillItem.TYPE_FRQUERYITEM);
		drillItems[0].setConditionName("��Ŀ����");//ע�⣺������������Ƹ���ѡ������ģ����ͻ����Ƶ�λ���й�
		drillItems[0].setValue(projectid);
		ReportDrillInfo drillRule = new ReportDrillInfo();
		drillRule.setDrillItem(drillItems);
		
		FreeReportDrillParam drillParam = new FreeReportDrillParam();
		drillParam.setDrillRule(drillRule);
		
		FuncletInitData initData = new FuncletInitData();
		initData.setInitData(drillParam);
		FreeReportFuncletUtil.openReportNode("4105report01", initData);
	}
	public SaleOrderBillForm getEditor() {
		return editor;
	}
	public void setEditor(SaleOrderBillForm editor) {
		this.editor = editor;
	}
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
	}
	public BillListView getListView() {
		return listView;
	}

	public void setListView(BillListView listView) {
		this.listView = listView;
	}

}
