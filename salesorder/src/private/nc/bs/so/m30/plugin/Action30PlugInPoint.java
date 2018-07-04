package nc.bs.so.m30.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.pub.enumeration.SOComponent;

/**
 * ���������Ĳ���㶨��
 */
public enum Action30PlugInPoint implements IPluginPoint {

  
  /**
   * ��������
   */
  SaveAction("nc.vo.so.m30.plugin.ActionPlugInPoint.Save"),

  /**
   * ������������
   */
  InsertAction("nc.vo.so.m30.plugin.ActionPlugInPoint.Insert"),

  /**
   * ����ɾ��
   */
  DeleteAction("nc.vo.so.m30.plugin.ActionPlugInPoint.Delete"),

  /**
   * �����޸ı���
   */
  UpdateAction("nc.vo.so.m30.plugin.ActionPlugInPoint.Update"),

  /**
   * ��������
   */
  SendApproveAction("nc.vo.so.m30.plugin.ActionPlugInPoint.SendApprove"),

  /**
   * ��������
   */
  ApproveAction("nc.vo.so.m30.plugin.ActionPlugInPoint.Approve"),

  /**
   * ��������
   */
  UnApproveAction("nc.vo.so.m30.plugin.ActionPlugInPoint.UnApprove"),

  /**
   * ����ת����(�������롢�빺��)
   */
  Push5Aor20Action("nc.bs.so.m30.action.Push5Aor20Action"),

  /**
   * Ԥ����������ʽ�������۶���
   */
  PushSave30For38ArrangeAction(
      "nc.impl.so.m30.action.main.PushSave30For38ArrangeAction"),

  /**
   * ���۶����޶�����
   */
  ReviseSaveAction("nc.impl.so.m30.revise.action.ReviseSaveSaleOrderAction");

  // �����
  private String point;

  private Action30PlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getModule() {
    return NCModule.SO.getName();
  }

  @Override
  public String getComponent() {
    return SOComponent.Order.getComponent();
  }

  @Override
  public String getPoint() {
    return this.point;
  }
}
