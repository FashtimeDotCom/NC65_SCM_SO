/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��5��29�� ����3:29:25 
 * @version: V6.5   
 */
package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.FeatureSelectUtil;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.util.StringUtil;

/**
 * @Description: Ϊ���۶��������ݴ��������޸İ�ť
 * @author: wangzy
 * @date: 2018��5��29�� ����3:29:25
 */
public class M30REditAction extends EditAction {
	private BillForm editor;

	private AbstractAppModel model;

	public M30REditAction() {
		this.setBtnName("�޸�");
		this.setCode("reviseEdit");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		this.model.setUiState(UIState.EDIT);
		// ����Ϊ�޸�״̬
		this.getEditor().getBillCardPanel().getHeadItem("agdef1")
				.setValue("�޸�");
		this.getEditor().getBillCardPanel().getHeadItem("agdef1")
				.setEdit(false);
		this.model.fireEvent(new AppEvent("ˢһ�´�ҵİ�ť״̬"));
	}

	public BillForm getEditor() {
		return editor;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		boolean iseidtable = (this.model.getUiState() == UIState.NOT_EDIT)
				&& (this.model.getSelectedData() != null);
		if (iseidtable) {
			SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
			// δ�ύ�Ŀ��޸�,��Ӧ��ֻ���޸İ汾�����µ����۶���
			Integer fstatusflag = vo.getParentVO().getFstatusflag();
			Integer iVersion = vo.getParentVO().getIversion();
			boolean lastestVersion = isLastestVersion(vo.getParentVO()
					.getCsaleorderid(), iVersion);
			// ��������ģ��淶��1 Ϊ����̬��7Ϊ���������� ��2Ϊ����ͨ��״̬
			if (lastestVersion
					&& nc.vo.so.pub.enumeration.BillStatus.FREE
							.equalsValue(fstatusflag)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Title: isLastestVersion
	 * @Description: TODO
	 * @param primaryKey
	 * @param iVersion
	 * @return
	 * @return: boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean isLastestVersion(String primaryKey, Integer iVersion) {
		// TODO �Զ����ɵķ������

		String sql = "select max(iversion) from so_orderhistory where csaleorderid ='"
				+ primaryKey + "' and dr =0";
		IUAPQueryBS service = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		ArrayList<Object[]> rs = new ArrayList<Object[]>();
		try {
			rs = (ArrayList<Object[]>) service.executeQuery(sql,
					new ArrayListProcessor());
		} catch (BusinessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		if (rs == null || rs.get(0) == null) {
			return false;
		}
		int version = rs.get(0)[0] == null ? 0 : (Integer) rs.get(0)[0];
		// �������°汾���߾�û�޶����������޸�
		if (Integer.valueOf(version) == 0
				|| Integer.valueOf(version) != iVersion) {
			return false;
		}
		return true;
	}

}
