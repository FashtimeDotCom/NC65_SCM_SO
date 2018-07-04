package nc.bs.so.m38.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum StatePlugInPoint implements IPluginPoint {

  /**
   * Ԥ���������ر�
   */
  BillCloseState("nc.bs.so.m38.state.bill.BillCloseState"),

  /**
   * Ԥ����������
   */
  BillOpenState("nc.bs.so.m38.state.bill.BillOpenState"),

  /**
   * Ԥ�����йر�
   */
  RowCloseState("nc.bs.so.m38.state.row.RowCloseState"),

  /**
   * Ԥ�����д�
   */
  RowOpenState("nc.bs.so.m38.state.row.RowOpenState");

  // �����
  private String point;

  private StatePlugInPoint(String point) {
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
