package nc.pubitf.so.m30.dm.m4804;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * ���۶����ṩ�����䵥����ӿ�
 * 
 * @since 6.0
 * @version 2011-6-7 ����11:20:56
 * @author ��־ΰ
 */
public interface ISaleOrderFor4804 {

  /**
   * �������۶���BIDs��ѯ���������֯ID
   * 
   * @param bids ���۶���BIDs
   * @return Map<String, String> Map<���۶���BID, ���������֯OID>
   * @throws BusinessException
   */
  Map<String, String> querySettleOrgsByBIDs(String[] bids)
      throws BusinessException;

}
