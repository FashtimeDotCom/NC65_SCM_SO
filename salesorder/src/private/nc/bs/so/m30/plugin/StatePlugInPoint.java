package nc.bs.so.m30.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.pub.enumeration.SOComponent;

/**
 * ����״̬�Ĳ���㶨��
 */
public enum StatePlugInPoint implements IPluginPoint {
  /**
   * ���۶�����������
   */
  ArSettleCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.ArSettleClose"),

  /**
   * ���۶�����������
   */
  ArSettleOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.ArSettleOpen"),

  /**
   * ������������
   */
  BillAuditState("nc.vo.so.m30.plugin.StatePlugInPoint.BillAuditState"),

  /**
   * ���������ر�
   */
  BillCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.BillClose"),

  /**
   * ������������
   */
  BillFreezeState("nc.vo.so.m30.plugin.StatePlugInPoint.BillFreeze"),

  /**
   * ����������
   */
  BillOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.BillOpen"),

  /**
   * ���������ⶳ
   */
  BillUnFreezeState("nc.vo.so.m30.plugin.StatePlugInPoint.BillUnFreeze"),

  /**
   * ���۶����ɱ�����ر�
   */
  CostSettleCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.CostSettleClose"),

  /**
   * ���۶����ɱ������
   */
  CostSettleOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.CostSettleOpen"),

  /**
   * ���۶�����Ʊ�ر�
   */
  InvoiceCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.InvoiceClose"),

  /**
   * ���۶�����Ʊ��
   */
  InvoiceOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.InvoiceOpen"),

  /**
   * ���۶�������ر�
   */
  OutCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.OutClose"),

  // -- ====================== ���۶�����״̬ ===========================
  /**
   * ���۶��������
   */
  OutOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.OutOpen"),

  /**
   * ���۶����йر�
   */
  RowCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.RowClose"),

  /**
   * ���۶����д�
   */
  RowOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.RowOpen"),

  /**
   * ���۶��������ر�
   */
  SendCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.SendClose"),

  /**
   * ���۶��������ر�
   */
  SendOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.SendOpen");

  // �����
  private String point;

  private StatePlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return SOComponent.Order.getComponent();
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
