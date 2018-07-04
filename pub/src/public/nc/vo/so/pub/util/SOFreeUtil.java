package nc.vo.so.pub.util;

import nc.vo.pubapp.pattern.pub.PubAppTool;

public class SOFreeUtil {
  // ���ϸ�����������
  private static final String VFREE = "vfree";

  public static boolean isFreeKey(String key) {

    if (PubAppTool.isNull(key) || key.length() < 5) {
      return false;
    }
    return VFREE.equals(key.substring(0, 5));
  }
}
