package nc.bs.so.m38.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum ActionPlugInPoint implements IPluginPoint {

  /**
   * Ԥ��������
   */
  ApproveAction("nc.impl.so.m38.action.ApprovePreOrderAction"),

  /**
   * Ԥ����ɾ��
   */
  DeleteAction("nc.impl.so.m38.action.DeletePreOrderAction"),

  /**
   * Ԥ������������
   */
  InsertAction("nc.impl.so.m38.action.InsertPreOrderAction"),

  /**
   * Ԥ����ȡ������
   */
  UnApproveAction("nc.impl.so.m38.action.UnApprovePreOrderAction"),

  /**
   * Ԥ�����޸ı���
   */
  UpdateAction("nc.impl.so.m38.action.UpdatePreOrderAction");

  // �����
  private String point;

  private ActionPlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "m38";
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
