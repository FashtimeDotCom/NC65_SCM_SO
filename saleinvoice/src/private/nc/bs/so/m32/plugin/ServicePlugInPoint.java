package nc.bs.so.m32.plugin;

import nc.vo.pubapp.res.NCModule;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * ���������ע���
 * 
 * @since 6.3
 * @version 2012-12-21 ����09:18:09
 * @author yaogj
 */
public enum ServicePlugInPoint implements IPluginPoint {
  
  /**
   * �����㵥��д��Ʊ
   */
  rewrite32BalFor33("nc.pubimpl.so.m32.so.m33.Rewrite32For33Impl"),
  
  /**
   * ���۳��ⵥ��д���۷�Ʊ
   */
  rewrite32OutNumFor4C("nc.pubimpl.so.m32.ic.m4c.Rewrite32For4CImpl");

  // �����
  private String point;

  private ServicePlugInPoint(String point) {
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
