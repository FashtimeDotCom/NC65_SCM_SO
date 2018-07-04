/**
 * 
 */
package nc.pubitf.so.m30.sobalance;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;

/**
 * @author gdsjw
 *
 */
public interface ISOBalanceQueryForArap {

  /**
   * ��ѯ�ɺ������
   * @param paybillrowid �տ��ID 
   * @param saleorderid ���۶���ID
   * @return
   * @throws BusinessException
   */
  SoBalanceViewVO[] querySoBalanceAccbalmnyForVerify(
      String paybillrowid, String saleorderid) throws BusinessException;

}
