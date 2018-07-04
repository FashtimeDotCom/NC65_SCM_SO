package nc.bs.so.m38.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * Ԥ����BP�Ĳ���㶨��
 * 
 * @author ��־ΰ
 */
public enum BPPlugInPoint implements IPluginPoint {
  /**
   * Ԥ����ɾ��
   */
  DeleteBP("nc.bs.so.m38.maintain.DeletePreOrderBP"),

  /**
   * Ԥ������������
   */
  InsertBP("nc.bs.so.m38.maintain.InsertPreOrderBP"),

  /**
   * Ԥ�����޸ı���
   */
  UpdateBP("nc.bs.so.m38.maintain.UpdatePreOrderBP");

  // �����
  private String point;

  private BPPlugInPoint(String point) {
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
