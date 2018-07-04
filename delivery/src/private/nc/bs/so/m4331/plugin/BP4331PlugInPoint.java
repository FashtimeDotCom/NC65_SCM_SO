package nc.bs.so.m4331.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum BP4331PlugInPoint implements IPluginPoint {
  /**
   * ����������
   */
  ApproveAction("nc.bs.so.m4331.maintain.ApproveDeliveryBP"),

  /**
   * ������ɾ��
   */
  DeleteAction("nc.bs.so.m4331.maintain.DeleteDeliveryBP"),

  /**
   * �������ʼ�ɾ��
   */
  DeleteDeliverycheck("nc.bs.so.m4331.maintain.DeleteDeliverycheckBP.delete"),

  /**
   * ��������������
   */
  InsertAction("nc.bs.so.m4331.maintain.InsertSaleInvoiceBP"),

  /**
   * ����������
   */
  UnApproveAction("nc.bs.so.m4331.maintain.UnAppDeliveryBP"),

  /**
   * �������ջ�
   */
  UnSendBP("nc.bs.so.m4331.maintain.DeliveryUnSendApproveBP"),

  /**
   * �������޸ı���
   */
  UpdateAction("nc.bs.so.m4331.maintain.UpdateDeliveryBP"),

  /**
   * �������޸ı���
   */
  UpdateActionForATP("nc.bs.so.m4331.maintain.UpdateDeliveryBP.forAtP"),

  /**
   * �������ʼ��޸�
   */
  updateDeliverycheck("nc.bs.so.m4331.maintain.UpdateDeliveryCheckBP.update");

  // �����
  private String point;

  private BP4331PlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "m4331";
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
