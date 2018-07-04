package nc.vo.so.pub.util;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.parameter.SCMParameterUtils;

/**
 * �������� ���� ���۷����ж�������
 * 
 * @since 6.0
 * @version 2011-3-30 ����04:09:43
 * @author fengjb1
 */
public class BaseSaleClassUtil {

  public static boolean isCustBaseClass(String pk_group) {
    String scm12 = null;

    scm12 = SCMParameterUtils.getSCM12(pk_group);

    return PubAppTool.isNull(scm12) || "�ͻ���������".equals(scm12);/*-=notranslate=-*/
  }

  public static boolean isMarBaseClass(String pk_group) {
    String scm11 = null;
    scm11 = SCMParameterUtils.getSCM11(pk_group);

    return PubAppTool.isNull(scm11) || "���ϻ�������".equals(scm11);/*-=notranslate=-*/
  }
}
