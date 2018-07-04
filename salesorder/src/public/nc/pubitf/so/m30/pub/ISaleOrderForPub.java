package nc.pubitf.so.m30.pub;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ���۶����ṩ�ⲿ�Ĺ����ӿ�
 * 
 * @since 6.0
 * @version 2011-8-26 ����12:24:07
 * @author ô��
 */
/**
 * 
 * @since 6.0
 * @version 2011-12-30 ����12:34:30
 * @author ô��
 */
public interface ISaleOrderForPub {

  /**
   * ���ݱ���IDS��ѯ���۶�������VO����
   * ��ֻ������ֶ��ǱȲ�ѯ��ͼVO Ч�ʸ�
   * 
   * @param ids ����id����
   * @param names �ֶ�
   * @return
   * @throws BusinessException
   */
  SaleOrderBVO[] querySaleOrderBVOs(String[] bids, String[] names)
      throws BusinessException;

  /**
   * ���ݱ�ͷIDS��ѯ���۶�����ͷVO����
   * ��ֻ���ͷ�ֶ��ǱȲ�ѯ��ͼVO Ч�ʸ�
   * 
   * @param ids ��ͷid����
   * @param names �ֶ�
   * @return
   * @throws BusinessException
   */
  SaleOrderHVO[] querySaleOrderHVOs(String[] ids, String[] names)
      throws BusinessException;

  /**
   * ����bids��ѯ���۶���ViewVOsָ��ֵ
   * 
   * @param bids ��������id[]
   * @param String[] ��Ҫ��ѯ��ֵ
   * @return SaleOrderViewVO[] ���۶���ViewVO[]
   * @throws BusinessException
   */
  SaleOrderViewVO[] querySaleOrderViewVOs(String[] bids, String[] names)
      throws BusinessException;

  /**
   * ����bids��ѯ���۶���ViewVOsָ��ֵ
   * 
   * @param bids ��������id[]
   * @return SaleOrderViewVO[] ���۶���ViewVO[]
   * @throws BusinessException
   */
  SaleOrderViewVO[] querySaleOrderViewVOs(String[] bids)
      throws BusinessException;

  /**
   * 
   * ��ѯ���۶����Ƿ������ж��ر�
   * 
   * @param saleorderids ���۶���id
   * @return ����û�йرյĶ������ݺ�
   * @throws BusinessException
   */
  String[] getNotCloseOrder(String[] saleorderids) throws BusinessException;

}
