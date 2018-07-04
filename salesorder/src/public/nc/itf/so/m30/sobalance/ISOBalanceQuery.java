/**
 * 
 */
package nc.itf.so.m30.sobalance;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;

/**
 * @author gdsjw
 * 
 */
public interface ISOBalanceQuery {
  // ���ݶ���IDs��ѯ�տ������ϵ
  SoBalanceVO[] querySoBalanceVOBySaleOrderIDs(String[] saleorderids)
      throws BusinessException;

  // �����տ��IDs��ѯ�տ������ƽ��ϵ
  SoBalanceViewVO[] querySoBalanceViewByGatheringBillBodyIDs(
      String[] paybillrowids) throws BusinessException;

}
