package nc.ui.so.pub;

import nc.md.MDBaseQueryFacade;
import nc.md.model.IBean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.util.VisibleUtil;

public class ReturnBaseDocUtils {
  /**
   * 
   * ��û����������տɼ���
   * 
   * @param pk_group ����pk
   * @param pk_org ��֯pk
   * @param clazz VO class
   * @return �ɼ���
   */
  public static String getVisibleForRef(String pk_group, String pk_org,
      Class<?> clazz) {
    String visible = "";
    try {
      IBean ibean =
          MDBaseQueryFacade.getInstance().getBeanByFullClassName(
              clazz.getName());
      visible =
          VisibleUtil.getRefVisibleCondition(pk_group, pk_org, ibean.getID());
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return visible;
  }
}
