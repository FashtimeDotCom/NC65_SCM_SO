package nc.bs.so.m4331.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum ServicePlugInPoint implements IPluginPoint {
  /**
   * ���۶����޸� �޸ķ������ļ۸�
   */
  Renovate4331PriceBP("nc.pubimpl.so.m4331.so.m30.bp.Renovate4331PriceBP"),
 
  /**
   * ;���д������
   */
  rewrite4331For4435("nc.pubimpl.so.m4331.ic.m4435."
      + "Rewrite4331For4435Impl.rewrite"),

  /**
   * ���۳��ⵥ��д������
   */
  rewrite4331outNumFor4C("nc.pubimpl.so.m4331.ic.m4c.Rewrite4331For4CImpl"),
  
  /**
   * ��������д������
   */
  rewrite4331OutNumFor4Y(
      "nc.pubimpl.so.m4331.ic.m4y.Rewrite4331For4YImpl.rewrite4331OutNumFor4Y"),
  /**
   * ��д�������Գ�����
   */
  rewriteRushnumFor33("nc.pubimpl.so.m4331.so.m33.bp.RewriteRushnumFor33"),
      
  /**
   * �����дȷ��Ӧ������
   */
  RewriteArnumFor33("nc.pubimpl.so.m4331.so.m33.bp.RewriteArnumFor33"),

  /**
   * �����д�ݹ�Ӧ������
   */
  RewriteEstArnumFor33("nc.pubimpl.so.m4331.so.m33.bp.RewriteEstArnumFor33");

  // �����
  private String point;

  private ServicePlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "m4331";
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
