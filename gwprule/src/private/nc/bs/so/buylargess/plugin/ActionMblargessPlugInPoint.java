package nc.bs.so.buylargess.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum ActionMblargessPlugInPoint implements IPluginPoint {
  /* ������������Ķ��ο�������� */

  /**
   * ����������������
   */
  InsertBuyLargessAction("nc.impl.so.mbuylargess.InsertBuyLargessAction"),

  /**
   * ���������޸ı���
   */
  UpdateBuyLargessAction("nc.impl.so.mbuylargess.UpdateBuyLargessAction");
  // �����
  private String point;

  private ActionMblargessPlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "so";
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
