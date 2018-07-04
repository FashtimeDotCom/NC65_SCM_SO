package nc.bs.so.m32.plugin;

import nc.vo.pubapp.res.NCModule;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ�����Ķ��ο��������
 * </ul>
 * 
 * @version ���汾��6.0
 * @author fengjb
 * @time 2010-1-19 ����10:59:05
 */
public enum Action32PlugInPoint implements IPluginPoint {
  
  /**
   * ���۷�Ʊ����
   */
  ApproveAction("nc.impl.so.m32.action.ApproveSaleInvoiceAction"),

  /**
   * ���۷�Ʊɾ��
   */
  DeleteAction("nc.impl.so.m32.action.DeleteSaleInvoiceAction"),

  /**
   * ���۷�Ʊ����
   */
  InsertAction("nc.impl.so.m32.action.InsertSaleInvoiceAction"),

  /**
   * ���۷�Ʊ����
   */
  SendAppAction("nc.impl.so.m32.action.SendAppSaleInvoiceAction"),

  /**
   * ���۷�Ʊȡ�����
   */
  UnApproveAction("nc.impl.so.m32.action.UnAppSaleInvoiceAction"),

  /**
   * ���۷�Ʊ�ջ�
   */
  UnSendAppAction("nc.impl.so.m32.action.UnCommitSaleInvoiceAction"),

  /**
   * ���۷�Ʊ�޸�
   */
  UpdateAction("nc.impl.so.m32.action.UpdateSaleInvoiceAction");

  // �����
  private String point;

  private Action32PlugInPoint(String point) {
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
