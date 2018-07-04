package nc.bs.so.buylargess.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum BPMblargessPlugInPoint implements IPluginPoint {
  /* �������õĶ��ο�������� */
  /**
   * ����������������
   */
  InsertMblargessInBP("nc.bs.so.maintain.InsertMblargessInBP"),

  /**
   * ���������޸ı���
   */
  UpdateMblargessInBP("nc.bs.so.maintain.UpdateMblargessInBP"),
  
  /**
   * ��������ɾ��
   */
  DeleteMblargessInBP("nc.bs.so.maintain.DeleteMblargessInBP");

  // �����
  private String point;

  private BPMblargessPlugInPoint(String point) {
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
