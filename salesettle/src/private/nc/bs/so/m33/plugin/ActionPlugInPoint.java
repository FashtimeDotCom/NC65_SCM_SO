package nc.bs.so.m33.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * ���۽�������߼��Ķ��ο�������㶨��
 * 
 * @author zc
 * 
 *         2009-12-19 ����02:47:34
 */
public enum ActionPlugInPoint implements IPluginPoint {

  /**
   * Ӧ�ս���
   */
  ARIncomeFor32("nc.bs.so.m33.biz.m32.action.ar.ARIncomeFor32Action"),

  /**
   * ��Ӧ�ս���
   */
  ADIncomeFor32("nc.bs.so.m33.biz.m32.action.ar.ADIncomeFor32Action"),

  /**
   * ���۳��ⵥ�������
   */
  ARIncomeFor4C("nc.bs.so.m33.biz.action.ARIncomeFor4CAction"),

  /**
   * ���۳��ⵥ�ݹ�Ӧ�ս���
   */
  ETIncomeFor4C("nc.bs.so.m33.biz.m4c.action.ar.ETIncomeFor4CAction"),

  /**
   * ���۳�������㵥����Գ�
   */
  OutRushFor4CAction("nc.bs.so.m33.biz.m4c.action.outrush.OutRushFor4CAction");

  // �����
  private String point;

  private ActionPlugInPoint(String point1) {
    this.point = point1;
  }

  @Override
  public String getComponent() {
    return "m33";
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
