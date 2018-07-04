package nc.pubitf.so.m30.api;

import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.itf.annotation.Component;

/**
 * @description
 * <ul>
 * <li>�������۶�������������ѯ���۶�����ͼVO
 * <li>�������۶���������������Ҫ��ѯ���ֶβ�ѯ���۶�����ͼVO
 * <li>�������۶���������ѯ���۶���VO
 * <li>�������۶�����������Ҫ��ѯ���ֶβ�ѯ���۶���VO
 * <li>���ݲ�ѯ������ѯ���۶�����ͼVO
 * <li>���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۶�����ͼVO
 * <li>���ݲ�ѯ������ѯ������ѯ���۶���VO����
 * <li>���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۶���VO����
 * <li>�������۶�����Դ������id��ѯ���۶�����ͼVO
 * <li>�������۶�����Դ������id����Ҫ��ѯ���ֶβ�ѯ���۶�����ͼVO
 * <li>�������۶���������ѯ���۶����Ƿ������ر�
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName ���۶�����ѯ����
 * 
 * @since 6.5
 * @version 2015-10-19 ����4:14:19
 * @author ����
 */
@Component("���۶���")
@OpenAPI(value = OpenLevel.OPENED)
public interface ISaleOrderQueryAPI extends ISOQueryAPI{

  /**
  * �������۶�������������ѯ���۶�����ͼVO
  * 
  * @param bids ���۶���������������
  * @return ���۶�����ͼVO����
  * @throws BusinessException �쳣
  */
  SaleOrderViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
  * �������۶���������������Ҫ��ѯ���ֶβ�ѯ���۶�����ͼVO
  * 
  * @param bids ���۶�����������
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ���۶�����ͼVO����
  * @throws BusinessException �쳣
  */
  SaleOrderViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
  * �������۶���������ѯ���۶���VO
  * 
  * @param ids ���۶�������
  * @return ���۶���VO����
  * @throws BusinessException �쳣
  */
  SaleOrderVO[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
  * �������۶�����������Ҫ��ѯ���ֶβ�ѯ���۶���VO
  * 
  * @param ids ���۶�������
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ���۶���VO����
  * @throws BusinessException �쳣
  */
  SaleOrderVO[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ���۶�����ͼVO
   * 
   * @param queryscheme ��ѯ����
   * @return ���۶�����ͼVO����
   * @throws BusinessException �쳣
   */
  SaleOrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * ���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۶�����ͼVO
   * 
   * @param queryscheme ��ѯ����
   * @param queryFields ��Ҫ��ѯ���ֶ�
   * @return ���۶�����ͼVO����
   * @throws BusinessException �쳣
   */
  SaleOrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] queryFields) throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ������ѯ���۶���VO����
   * 
   * @param queryscheme ��ѯ����
   * @return ���۶���VO����
   * @throws BusinessException �쳣
   */
  SaleOrderVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * ���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۶���VO����
   * 
   * @param queryscheme ��ѯ����
   * @param  queryFields ��Ҫ��ѯ���ֶ�
   * @return ���۶���VO����
   * @throws BusinessException �쳣
   */
  SaleOrderVO[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
  * �������۶�����Դ������id��ѯ���۶�����ͼVO
  * 
  * @param sourceBIDs ���۶�����Դ��id
  * @return ���۶�����ͼVO����
  * @throws BusinessException �쳣
  */
  SaleOrderViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs)
      throws BusinessException;

  /**
  * �������۶�����Դ������id����Ҫ��ѯ���ֶβ�ѯ���۶�����ͼVO
  * 
  * @param sourceBIDs ���۶�����Դ��id
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ���۶�����ͼVO����
  * @throws BusinessException �쳣
  */
  SaleOrderViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs,
      String[] queryFields) throws BusinessException;

  /**
   * 
   * �������۶���������ѯ���۶����Ƿ������ر�
   * 
   * @param saleorderids ���۶���id
   * @return ����û�йرյĶ�����id
   * @throws BusinessException
   */
  String[] getNotCloseOrder(String[] ids) throws BusinessException;

}
