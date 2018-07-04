package nc.pubitf.so.m4331.api;

import nc.itf.annotation.Component;

import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;


/**
 * @description
 * <ul>
 * <li>���ݷ���������������ѯ��������ͼVO
 * <li>���ݷ�����������������Ҫ��ѯ���ֶβ�ѯ��������ͼVO
 * <li>���ݷ�����������ѯ������VO
 * <li>���ݷ�������������Ҫ��ѯ���ֶβ�ѯ������VO
 * <li>���ݲ�ѯ������ѯ��������ͼVO
 * <li>���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ��������ͼVO
 * <li>���ݲ�ѯ������ѯ������ѯ������VO����
 * <li>���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ������VO����
 * <li>���ݷ�������Դ������id��ѯ��������ͼVO
 * <li>���ݷ�������Դ������id����Ҫ��ѯ���ֶβ�ѯ��������ͼVO
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName ��������ѯ����
 * 
 * @since 6.5
 * @version 2015-10-19 ����4:14:19
 * @author ����
 */
@Component("������")
@OpenAPI(value = OpenLevel.OPENED)
public interface IDeliveryQueryAPI  extends ISOQueryAPI{

  /**
  * ���ݷ���������������ѯ��������ͼVO
  * 
  * @param bids ������������������
  * @return ��������ͼVO����
  * @throws BusinessException �쳣
  */
  DeliveryViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
  * ���ݷ�����������������Ҫ��ѯ���ֶβ�ѯ��������ͼVO
  * 
  * @param bids ��������������
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ��������ͼVO����
  * @throws BusinessException �쳣
  */
  DeliveryViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
  * ���ݷ�����������ѯ������VO
  * 
  * @param ids ����������
  * @return ������VO����
  * @throws BusinessException �쳣
  */
  DeliveryVO[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
  * ���ݷ�������������Ҫ��ѯ���ֶβ�ѯ������VO
  * 
  * @param ids ����������
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ������VO����
  * @throws BusinessException �쳣
  */
  DeliveryVO[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ��������ͼVO
   * 
   * @param queryscheme ��ѯ����
   * @return ��������ͼVO����
   * @throws BusinessException �쳣
   */
  DeliveryViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * ���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ��������ͼVO
   * 
   * @param queryscheme ��ѯ����
   * @param queryFields ��Ҫ��ѯ���ֶ�
   * @return ��������ͼVO����
   * @throws BusinessException �쳣
   */
  DeliveryViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] queryFields) throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ������ѯ������VO����
   * 
   * @param queryscheme ��ѯ����
   * @return ������VO����
   * @throws BusinessException �쳣
   */
  DeliveryVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * ���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ������VO����
   * 
   * @param queryscheme ��ѯ����
   * @param  queryFields ��Ҫ��ѯ���ֶ�
   * @return ������VO����
   * @throws BusinessException �쳣
   */
  DeliveryVO[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
  * ���ݷ�������Դ������id��ѯ��������ͼVO
  * 
  * @param sourceBIDs ��������Դ��id
  * @return ��������ͼVO����
  * @throws BusinessException �쳣
  */
  DeliveryViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs)
      throws BusinessException;

  /**
  * ���ݷ�������Դ������id����Ҫ��ѯ���ֶβ�ѯ��������ͼVO
  * 
  * @param sourceBIDs ��������Դ��id
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ��������ͼVO����
  * @throws BusinessException �쳣
  */
  DeliveryViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs, String[] queryFields)
      throws BusinessException;


}
