package nc.pubitf.so.m30.so.m4331;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * ���۶����ṩ�����۷�����/�������Žӿڷ���
 * 
 * @since 6.0
 * @version 2011-1-21 ����11:37:58
 * @author ��־ΰ
 */
public interface ISaleOrderFor4331 {

  /**
   * ��ѯ���۶����������Ƿ�����Ԥ��
   * 
   * @param bids ���۶���bids
   * @return Map<String, UFBoolean> Map<���۶���bid, �Ƿ�Ԥ��>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsReserved(String[] bids)
      throws BusinessException;

  /**
   * �������۶���BIDs��ѯ���۶���ViewVOs
   * 
   * @param bids ���۶���bids
   * @return SaleOrderViewVO[]
   * @throws BusinessException
   */
  Object[] querySaleOrderViewVOsFor4331Arrange(String[] bids)
      throws BusinessException;
}
