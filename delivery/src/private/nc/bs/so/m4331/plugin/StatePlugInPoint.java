package nc.bs.so.m4331.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum StatePlugInPoint implements IPluginPoint {

  /**
   * �����������ر�
   */
  BillCloseState("nc.bs.so.m4331.state.bill.BillCloseState"),

  /**
   * ������������
   */
  BillOpenState("nc.bs.so.m4331.state.bill.BillOpenState"),

  /**
   * ����������ر�
   */
  OutCloseState("nc.bs.so.m4331.state.OutCloseState"),

  /**
   * �����������
   */
  OutOpenState("nc.bs.so.m4331.state.OutOpenState"),

  /**
   * �������йر�
   */
  RowCloseState("nc.bs.so.m4331.state.row.RowCloseState"),

  /**
   * �������ر�
   */
  ArSettleCloseState("nc.bs.so.m4331.assist.state.row.ArSettleCloseState"),

  /**
   * �������д�
   */
  RowOpenState("nc.bs.so.m4331.state.row.RowOpenState"),

  /**
   * ��������������
   */
  ArSettleOpenState("nc.bs.so.m4331.assist.state.row.ArSettleOpenState");

  // �����
  private String point;

  private StatePlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "4331";
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
