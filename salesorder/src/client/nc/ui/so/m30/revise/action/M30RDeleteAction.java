/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��6��6�� ����10:01:42 
 * @version: V6.5   
 */
package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @Description: ���۶����޶���ɾ����ť��
 * @author: wangzy
 * @date: 2018��6��6�� ����10:01:42
 */
public class M30RDeleteAction extends DeleteScriptAction {
	public M30RDeleteAction() {
		super();
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������

		super.doAction(e);
	}

	/**
	 * �����Կ��ƣ����������޸ľ���ɾ�������Ը����޸ĵ��߼�
	 */
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
