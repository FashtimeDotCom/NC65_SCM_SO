package nc.itf.so.m33;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * ���㵥��Դ��Ϣ��ȡ��
 * 
 * @since 6.1
 * @version 2013-05-09 10:55:55
 * @author yixl
 */
public interface IM33MainTain {

  /**
   * ��ȡ���㵥��Դ��Ϣ
   * 
   * @param bids
   * @return Map<String, String>
   * @throws BusinessException
   */
  Map<String, String> getSrcInfo(String[] bids) throws BusinessException;

}
