package nc.bs.so.custmatrel.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum BPPlugInPoint implements IPluginPoint {
  
  /**
   * ��������
   */
  InsertBP("nc.vo.so.custmatrel.plugin.BPPlugInPoint.Insert"),

  /**
   * �޸ı���
   */
  UpdateBP("nc.vo.so.custmatrel.plugin.BPPlugInPoint.Update"),

  /**
   * ɾ��
   */
  DeleteBP("nc.vo.so.custmatrel.plugin.BPPlugInPoint.Delete");

  // �����
  private String point;

  private BPPlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getModule() {
    return NCModule.SO.getName();
  }

  @Override
  public String getComponent() {
    return "salebusirule";
  }

  @Override
  public String getPoint() {
    return this.point;
  }
}
