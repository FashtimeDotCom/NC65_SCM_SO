package nc.itf.so.m30.ref.ic.m4c;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.pubitf.ic.m4c.m30.ICSaleOutNumInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * IC�������۳��ⵥ����
 * 
 * @since 6.0
 * @version 2011-7-5 ����09:41:12
 * @author ��־ΰ
 */
public class ICm4CServicesUtil {

  /**
   * �ṩ�����۶����������Ĳ����ӿڣ�������ѯ���ⵥ��ǩ�ֺ�δǩ������
   */
  public static Map<String, ICSaleOutNumInfoVO> query4CNumInfoFor30CloseMng(
      String[] orderbids) throws BusinessException {
	if(!SysInitGroupQuery.isICEnabled()) {
		return new HashMap<String, ICSaleOutNumInfoVO>();
	}
    I4CQueryPubService m4CService =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    return m4CService.query4CNumInfoFor30CloseMng(orderbids);
  }

  /**
   * �������εĳ��ⵥ�Ƿ�ȫ������
   * 
   * @param orderbids
   * @return Y������ N||NULL��û������
   */
  public static UFBoolean[] queryIsAllSigned(String[] orderbids) {
	if(!SysInitGroupQuery.isICEnabled()) {
	  return new UFBoolean[orderbids.length];
	}
    UFBoolean[] isapprove = null;
    I4CQueryPubService querysrv =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    try {
      isapprove = querysrv.queryIsAllSigned(orderbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return isapprove;
  }
}
