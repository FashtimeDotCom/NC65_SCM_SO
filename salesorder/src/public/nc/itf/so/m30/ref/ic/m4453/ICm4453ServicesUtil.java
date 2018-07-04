package nc.itf.so.m30.ref.ic.m4453;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.m4453.m30.IWastageServiceFor30;
import nc.pubitf.ic.m4453.m33.IWastageServiceFor33;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * IC����;�𵥷���
 * 
 * @since 6.0
 * @version 2011-8-4 ����08:02:27
 * @author ��־ΰ
 */
public class ICm4453ServicesUtil {

  /**
   * ����Դͷ����������ѯδ�������
   */
  public static Map<String, UFDouble> queryUnApproveNumByFirst(
      String[] firstbids) throws BusinessException {
	if(!SysInitGroupQuery.isICEnabled()) {
	  return new HashMap<String, UFDouble>();
	}
    IWastageServiceFor33 service =
        NCLocator.getInstance().lookup(IWastageServiceFor33.class);
    return service.queryUnApproveNumByFirst(firstbids);
  }

  /**
   * �������ε�;���Ƿ�ȫ������
   * 
   * @param orderbids
   * @return Y������ N||NULL��û������
   */
  public static UFBoolean[] queryWastageSigned(String[] orderbids) {
    if(!SysInitGroupQuery.isICEnabled()) {
    	return new UFBoolean[orderbids.length];
    }
    UFBoolean[] isapprove = null;
    IWastageServiceFor30 querysrv =
        NCLocator.getInstance().lookup(IWastageServiceFor30.class);
    try {
      isapprove = querysrv.queryWastageSigned(orderbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return isapprove;
  }

}
