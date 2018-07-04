package nc.itf.so.m30.self;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * TODO tianft �������ã���ɾ����
 */
public interface ISaleOrder {

  /**
   * ɾ��VO
   * 
   * @param todelVO
   * @throws BusinessException
   */
  void deleteSaleOrder(SaleOrderVO todelVO) throws BusinessException;

  /**
   * ����VO
   * 
   * @param newVO
   * @return
   * @throws BusinessException
   */
  SaleOrderVO insertSaleOrder(SaleOrderVO newVO) throws BusinessException;

  /**
   * �޸�VO
   * 
   * @param updateVO
   * @return
   * @throws BusinessException
   */
  SaleOrderVO updateSaleOrder(SaleOrderVO updateVO) throws BusinessException;

  /**
   * ����where������ѯ���۶�������
   * 
   * @param where
   * @return
   * @throws Exception
   */
  SaleOrderVO[] querySaleOrder(String where) throws BusinessException;

}
