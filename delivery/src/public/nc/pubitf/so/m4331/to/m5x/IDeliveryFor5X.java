package nc.pubitf.so.m4331.to.m5x;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * �������ṩ�����������Ľӿ�
 * 
 * @since 6.0
 * @version 2011-1-26 ����01:55:19
 * @author ף����
 */
public interface IDeliveryFor5X {
  /**
   * ���ݵ�����������id ��ѯ�������Ƿ�����Ԥ�����
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryReverseFlag(String[] bids)
      throws BusinessException;
}
