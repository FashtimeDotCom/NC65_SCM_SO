package nc.bs.so.m4331.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum Action4331PlugInPoint implements IPluginPoint {
  /**
   * ����������
   */
  ApproveAction("nc.impl.so.m4331.action.DeliveryApproveAction"),

  /**
   * ������״̬���
   */
  CheckStatusByCredit("nc.impl.so.m4331.pf.DeliveryCheckStatus.byCredit"),

  /**
   * ������״̬���
   */
  CheckStatusByATP("nc.impl.so.m4331.pf.DeliveryCheckStatus.byATP"),

  /**
   * ������ɾ��
   */
  DeleteAction("nc.impl.so.m4331.action.DeliveryDeleteAction"),

  /**
   * ��������������
   */
  InsertAction("nc.impl.so.m4331.action.DeliveryInsertAction"),

  /**
   * �������ʼ챣��
   */
  InsertDeliverycheck(
      "nc.impl.so.m4331.action.DeliverycheckInsertAction.insert"),

      /**
       * ����������
       */
      SendApproveAction(
          "nc.impl.so.m4331.action.DeliverySendApproveAction.sendApprove"),

          /**
           * ����������
           */
          UnApproveAction("nc.impl.so.m4331.action.DeliveryUnApproveAction"),

          /**
           * �������޸ı���
           */
          UpdateAction("nc.impl.so.m4331.action.DeliveryUpdateAction"),

          /**
           * ��������
           */
          BillOpenAction("nc.impl.so.m4331.action.assist.DeliveryBillOpenAction"),


          /**
           * �������ر�
           */
          BillCloseAction("nc.impl.so.m4331.action.assist.DeliveryBillCloseAction"),

          /**
           * �������ر�
           */
          BillCloseActionByPara("nc.impl.so.m4331.action.assist.DeliveryBillCloseAction.SOParameterVO"),

          /**
           * �������д�
           */
          RowOpenAction("nc.impl.so.m4331.action.assist.DeliveryRowOpenAction"),

          /**
           * �������йر�
           */
          RowCloseAction("nc.impl.so.m4331.action.assist.DeliveryRowCloseAction");
  // �����
  private String point;

  private Action4331PlugInPoint(String point) {
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
