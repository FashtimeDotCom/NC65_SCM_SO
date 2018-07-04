package nc.itf.so.m33.ref.so.m4331;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m4331.so.m33.IRewrite4331For33;
import nc.pubitf.so.m4331.so.m33.RewriteArnumPara;
import nc.pubitf.so.m4331.so.m33.RewriteEstarnumPara;
import nc.pubitf.so.m4331.so.m33.RewriteRushNumPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SODeliveryServicesUtil {

  /**
   * ��д���������ۼ�ȷ��Ӧ������
   * 
   * @param paras
   */
  public static void rewrite4331Arnum(RewriteArnumPara[] paras) {
    IRewrite4331For33 bo =
        NCLocator.getInstance().lookup(IRewrite4331For33.class);
    try {
      bo.rewrite4331Arnum(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��д���������ۼ��ݹ�Ӧ������+�س�Ӧ������
   * 
   * @param paras
   */
  public static void rewrite4331Estarnum(RewriteEstarnumPara[] paras) {
    IRewrite4331For33 bo =
        NCLocator.getInstance().lookup(IRewrite4331For33.class);
    try {
      bo.rewrite4331Estarnum(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��д���������ۼƳ���Գ�����
   * 
   * @param paras
   */
  public static void rewrite4331RushNum(RewriteRushNumPara[] paras) {
    IRewrite4331For33 bo =
        NCLocator.getInstance().lookup(IRewrite4331For33.class);
    try {
      bo.rewrite4331RushNum(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
