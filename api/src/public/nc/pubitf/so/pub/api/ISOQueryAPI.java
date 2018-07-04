package nc.pubitf.so.pub.api;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

/**
 * @description
 * ���۹����ѯAPI�ӿ�
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-9 ����1:59:16
 * @author ����
 */
public interface ISOQueryAPI {

  /**
   * ���ݱ���������ѯ������ͼVO
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
   * ���ݱ�������+��Ҫ��ѯ���ֶβ�ѯ������ͼVO
   * @param bids
   * @param queryFields
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
   * ���ݱ�ͷ������ѯ����VO
   * @param ids
   * @return
   * @throws BusinessException
   */
  Object[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
   * ���ݱ�ͷ������ѯ+��Ҫ��ѯ���ֶβ�ѯ����VO
   * @param ids
   * @param queryFields
   * @return
   * @throws BusinessException
   */
  Object[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ������ͼVO
   * 
   * @param queryscheme
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * ���ݲ�ѯ����+��Ҫ��ѯ���ֶβ�ѯ������ͼVO
   * @param queryscheme
   * @param queryFields
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ��ѯ����VO
   * @param queryscheme
   * @return
   * @throws BusinessException
   */
  Object[] queryVOByScheme(IQueryScheme queryscheme) throws BusinessException;

  /**
   * ���ݲ�ѯ����+��Ҫ��ѯ���ֶβ�ѯ����VO
   * @param queryscheme
   * @param queryFields
   * @return
   * @throws BusinessException
   */
  Object[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
   * ������Դ���ݱ���id��ѯ������ͼVO
   * @param sourceBIDs
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOBySourceBIDs(String[] sourceBIDs)
      throws BusinessException;

  /**
   * ������Դ���ݱ���id+��Ҫ��ѯ���ֶβ�ѯ������ͼVO
   * @param sourceBIDs
   * @param queryFields
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOBySourceBIDs(String[] sourceBIDs, String[] queryFields)
      throws BusinessException;

}
