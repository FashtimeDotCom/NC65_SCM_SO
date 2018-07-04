package nc.bs.so.m33.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * ���۽����̨����Ķ��ο�������㶨��
 * 
 * @author zc
 * 
 *         2009-12-19 ����02:47:08
 */
public enum ServicePlugInPoint implements IPluginPoint {

  /**
   * ���۷�Ʊ��ʽ�������۽��㵥
   */
  Push33For32("nc.pubitf.so.m33.so.m32.ISquare33For32.pushSquareSrv"),

  /**
   * ;����ʽ�������۽��㵥
   */
  Push33For4453("nc.pubitf.so.m33.ic.m4453.ISquareAcionFor4453"),

  /**
   * ���۳��ⵥ��ʽ�������۽��㵥
   */
  Push33For4C("nc.pubitf.so.m33.so.m32.ISquare33For4C.pushSquareSrv");

  // �����
  private String point;

  private ServicePlugInPoint(String point1) {
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
