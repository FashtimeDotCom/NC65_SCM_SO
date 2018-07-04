package nc.pubitf.so.m30.to.pub;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ���۶����ṩTO��ѯ�۸�ӿ�
 * 
 * @since 6.0
 * @version 2010-11-6 ����09:47:22
 * @author ��־ΰ
 */

public interface IQuery30PriceForTO {

  /**
   * ��ѯ���۶���ԭ�ҵ���
   * 
   * @param bids ���۶�������ids
   * @return SaleOrderVO{�ӱ�������ԭ����˰���ۡ�ԭ�Һ�˰���ۡ�ԭ�ұ���}
   * @throws BusinessException
   */
  SaleOrderVO[] queryOrigPrice(String[] bids) throws BusinessException;
}
