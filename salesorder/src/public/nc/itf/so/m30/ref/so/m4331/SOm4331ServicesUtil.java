package nc.itf.so.m30.ref.so.m4331;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m4331.so.m30.IDeliveryFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * SO��������������
 * 
 * @since 6.0
 * @version 2011-8-4 ����08:22:42
 * @author ��־ΰ
 */
public class SOm4331ServicesUtil {

  /**
   * ���ݶ���id����ѯ��˷�������˵�����
   */
  public static Map<String, UFDouble> queryAppNum(String[] srcBids)
      throws BusinessException {
    IDeliveryFor30 m4331Service =
        NCLocator.getInstance().lookup(IDeliveryFor30.class);
    return m4331Service.queryAppNum(srcBids);
  }
}
