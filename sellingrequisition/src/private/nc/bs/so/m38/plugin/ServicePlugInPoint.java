package nc.bs.so.m38.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * Ԥ�����ĺ�̨����Ķ��ο�������㶨��
 * 
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-04-08 ����09:27:07
 */
public enum ServicePlugInPoint implements IPluginPoint {

  /**
   * ���۶�����дԤ������������
   */
  rewrite38NarrnumFor30(
      "nc.pubitf.so.m38.so.m30.IRewrite38For30.rewrite38NarrnumFor30");

  // �����
  private String point;

  private ServicePlugInPoint(String point) {
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
