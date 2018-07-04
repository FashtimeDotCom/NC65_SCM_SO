package nc.pubitf.so.m30.so.balance;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ���۶����ṩ��������������ӿ�
 * 
 * @since 6.0
 * @version 2011-8-18 ����11:35:39
 * @author ��־ΰ
 */
public interface ISaleOrderForBalance {

  /**
   * �������۶�����ͷHIDs��Ѱ����Views
   * 
   * @param ids
   * @return ������ͼVO
   * @throws BusinessException
   */
  SaleOrderViewVO[] querySaleOrderViewVOByHIDs(String[] ids)
      throws BusinessException;
}
