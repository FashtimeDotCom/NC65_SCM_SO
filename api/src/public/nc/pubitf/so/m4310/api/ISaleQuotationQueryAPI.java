package nc.pubitf.so.m4310.api;

import nc.itf.annotation.Component;

import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequoViewVO;

/**
 * @description
 * <ul>
 * <li>���ݱ��۵�����������ѯ���۵���ͼVO
 * <li>���ݱ��۵�������������Ҫ��ѯ���ֶβ�ѯ���۵���ͼVO
 * <li>���ݱ��۵�������ѯ���۵�VO
 * <li>���ݱ��۵���������Ҫ��ѯ���ֶβ�ѯ���۵�VO
 * <li>���ݲ�ѯ������ѯ���۵���ͼVO
 * <li>���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۵���ͼVO
 * <li>���ݲ�ѯ������ѯ������ѯ���۵�VO����
 * <li>���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۵�VO����
 * <li>���ݱ��۵���Դ������id��ѯ���۵���ͼVO
 * <li>���ݱ��۵���Դ������id����Ҫ��ѯ���ֶβ�ѯ���۵���ͼVO
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName ���۵���ѯ����
 * 
 * @since 6.5
 * @version 2015-10-19 ����4:14:19
 * @author ����
 */
@Component("���۵�")
@OpenAPI(value = OpenLevel.OPENED)
public interface ISaleQuotationQueryAPI  extends ISOQueryAPI{

  /**
  * ���ݱ��۵�����������ѯ���۵���ͼVO
  * 
  * @param bids ���۵�������������
  * @return ���۵���ͼVO����
  * @throws BusinessException �쳣
  */
  SalequoViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
  * ���ݱ��۵�������������Ҫ��ѯ���ֶβ�ѯ���۵���ͼVO
  * 
  * @param bids ���۵���������
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ���۵���ͼVO����
  * @throws BusinessException �쳣
  */
  SalequoViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
  * ���ݱ��۵�������ѯ���۵�VO
  * 
  * @param ids ���۵�����
  * @return ���۵�VO����
  * @throws BusinessException �쳣
  */
  AggSalequotationHVO[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
  * ���ݱ��۵���������Ҫ��ѯ���ֶβ�ѯ���۵�VO
  * 
  * @param ids ���۵�����
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ���۵�VO����
  * @throws BusinessException �쳣
  */
  AggSalequotationHVO[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ���۵���ͼVO
   * 
   * @param queryscheme ��ѯ����
   * @return ���۵���ͼVO����
   * @throws BusinessException �쳣
   */
  SalequoViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * ���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۵���ͼVO
   * 
   * @param queryscheme ��ѯ����
   * @param queryFields ��Ҫ��ѯ���ֶ�
   * @return ���۵���ͼVO����
   * @throws BusinessException �쳣
   */
  SalequoViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] queryFields) throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ������ѯ���۵�VO����
   * 
   * @param queryscheme ��ѯ����
   * @return ���۵�VO����
   * @throws BusinessException �쳣
   */
  AggSalequotationHVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * ���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۵�VO����
   * 
   * @param queryscheme ��ѯ����
   * @param  queryFields ��Ҫ��ѯ���ֶ�
   * @return ���۵�VO����
   * @throws BusinessException �쳣
   */
  AggSalequotationHVO[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
  * ���ݱ��۵���Դ������id��ѯ���۵���ͼVO
  * 
  * @param sourceBIDs ���۵���Դ��id
  * @return ���۵���ͼVO����
  * @throws BusinessException �쳣
  */
  SalequoViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs)
      throws BusinessException;

  /**
  * ���ݱ��۵���Դ������id����Ҫ��ѯ���ֶβ�ѯ���۵���ͼVO
  * 
  * @param sourceBIDs ���۵���Դ��id
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ���۵���ͼVO����
  * @throws BusinessException �쳣
  */
  SalequoViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs, String[] queryFields)
      throws BusinessException;


}
