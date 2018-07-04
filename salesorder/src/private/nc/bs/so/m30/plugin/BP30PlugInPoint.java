package nc.bs.so.m30.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.pub.enumeration.SOComponent;

/**
 * ����BP�Ĳ���㶨��
 */
public enum BP30PlugInPoint implements IPluginPoint {

	/**
	 * ����ɾ��
	 */
	DeleteBP("nc.vo.so.m30.plugin.BPPlugInPoint.Delete"),

	/**
	 * ������������
	 */
	InsertBP("nc.vo.so.m30.plugin.BPPlugInPoint.Insert"),

	/**
	 * �����޶�������ʷ�汾
	 */
	ReviseInsertBP("nc.bs.so.m30.revise.InsertSaleOrderHistoryBP"),

	/**
	 * �����޶��������۶���
	 */
	ReviseUpdateSOBP("nc.bs.so.m30.revise.SaleOrderHistoryUpdateSaleOrderBP"),

	/**
	 * ���۶����޶�����ͨ�����¶���
	 */
	ReviseUpdateBP("nc.bs.so.m30.revise.UpdateSaleOrderBP"),

	/**
	 * �����޶�����
	 */
	ReviseSaveBP("nc.bs.so.m30.revise.SaveReviseSaleOrderBP"),
	/**
	 * @author ����ܲ
	 * @date 2018-05-30 Ϊ���۶����޶������޸ı��湦�� �����޸ı���
	 */
	ReviseSaveBaseBP("nc.bs.so.m30.revise.SaveBaseReviseSaleOrderBP"),
	/**
	 * @author ����ܲ
	 * @date 2018-06-06 Ϊ���۶����޶�����ɾ������
	 */
	ReviseDeleteBP("nc.bs.so.m30.revise.ReviseSaleOrderDeleteBP"),

	/**
	 * ���۶����޶��ύ����
	 */
	ReviseSendBP("nc.vo.so.m30.plugin.BPPlugInPoint.ReviseSendBP"),

	/**
	 * �ύ����
	 */
	SendBP("nc.vo.so.m30.plugin.BPPlugInPoint.Send"),

	/**
	 * �����տ����ɾ��
	 */
	SOBalanceDeleteBP("nc.vo.so.sobalance.plugin.BPPlugInPoint.Delete"),

	/**
	 * �����տ������������
	 */
	SOBalanceInsertBP("nc.vo.so.sobalance.plugin.BPPlugInPoint.Insert"),

	/**
	 * �����տ�����޸ı���
	 */
	SOBalanceUpdateBP("nc.vo.so.sobalance.plugin.BPPlugInPoint.Update"),

	/**
	 * �����ջ�
	 */
	UnSendBP("nc.vo.so.m30.plugin.BPPlugInPoint.UnSend"),

	/**
	 * �����޸ı���
	 */
	UpdateBP("nc.vo.so.m30.plugin.BPPlugInPoint.Update");

	// �����
	private String point;

	private BP30PlugInPoint(String point) {
		this.point = point;
	}

	@Override
	public String getComponent() {
		return SOComponent.Order.getComponent();
	}

	@Override
	public String getModule() {
		return NCModule.SO.getName();
	}

	@Override
	public String getPoint() {
		return this.point;
	}
}
