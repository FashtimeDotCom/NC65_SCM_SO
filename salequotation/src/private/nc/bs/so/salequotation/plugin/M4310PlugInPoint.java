package nc.bs.so.salequotation.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.pub.enumeration.SOComponent;

public enum M4310PlugInPoint implements IPluginPoint {

  /**
   * ���۵�ɾ��
   */
  DeleteBP("nc.vo.so.m4310.plugin.BPPlugInPoint.Delete"),

  /**
   * ���۵���������
   */
  InsertBP("nc.vo.so.m4310.plugin.BPPlugInPoint.Insert"),

  /**
   * ���۵��޸ı���
   */
  UpdateBP("nc.vo.so.m4310.plugin.BPPlugInPoint.Update");

  // �����
  private String point;

  private M4310PlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return SOComponent.SaleQuotation.getComponent();
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
