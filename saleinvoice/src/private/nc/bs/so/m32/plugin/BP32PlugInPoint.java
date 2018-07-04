package nc.bs.so.m32.plugin;

import nc.vo.pubapp.res.NCModule;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * BP��������ע��
 * 
 * @since 6.3
 * @version 2012-12-21 ����09:19:02
 * @author yaogj
 */
public enum BP32PlugInPoint implements IPluginPoint {
  
  /**
   * ���۷�Ʊ����
   */
  ApproveAction("nc.bs.so.m32.maintain.ApproveSaleInvoiceBP"),

  /**
   * ���۷�Ʊ��ƾ֤
   */
  CreateVoucherAction("nc.bs.so.m32.maintain.CreateVoucherSaleInvoiceBP"),

  /**
   * ���۷�Ʊɾ��
   */
  DeleteAction("nc.bs.so.m32.maintain.DeleteSaleInvoiceBP"),

  /**
   * ���۷�Ʊ��������
   */
  InsertAction("nc.bs.so.m32.maintain.InsertSaleInvoiceBP"),

  /**
   * ���۷�Ʊ����
   */
  SendAction("nc.bs.so.m32.maintain.CommitSaleInvoiceBP"),
  
  /**
   * ���۷�Ʊ����
   */
  UnApproveAction("nc.bs.so.m32.maintain.UnAppSaleInvoiceBP"),

  /**
   * ���۷�Ʊ�޸ı���
   */
  UpdateAction("nc.bs.so.m32.maintain.UpdateSaleInvoiceBP");

  // �����
  private String point;

  private BP32PlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "m32";
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
