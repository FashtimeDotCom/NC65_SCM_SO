package nc.pubitf.so.m4331.ic.m4c;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * �������ṩ�����ⵥ�Ľӿ�
 * 
 * @since 6.0
 * @version 2011-9-26 
 * @author ������
 */
public interface IDeliveryFor4C {
  /**
   * ���ݷ���������id ��ѯ��Դ���۶����Ƿ�����Ԥ�����
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Map<String, UFBoolean> getReserveInfo(String[] bids)
  throws BusinessException;

}
