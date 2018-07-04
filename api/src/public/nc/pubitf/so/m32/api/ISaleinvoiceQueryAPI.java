package nc.pubitf.so.m32.api;

import nc.itf.annotation.Component;

import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

/**
 * @description
 * <ul>
 * <li>�������۷�Ʊ����������ѯ���۷�Ʊ��ͼVO
 * <li>�������۷�Ʊ������������Ҫ��ѯ���ֶβ�ѯ���۷�Ʊ��ͼVO
 * <li>�������۷�Ʊ������ѯ���۷�ƱVO
 * <li>�������۷�Ʊ��������Ҫ��ѯ���ֶβ�ѯ���۷�ƱVO
 * <li>���ݲ�ѯ������ѯ���۷�Ʊ��ͼVO
 * <li>���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۷�Ʊ��ͼVO
 * <li>���ݲ�ѯ������ѯ������ѯ���۷�ƱVO����
 * <li>���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۷�ƱVO����
 * <li>�������۷�Ʊ��Դ������id��ѯ���۷�Ʊ��ͼVO
 * <li>�������۷�Ʊ��Դ������id����Ҫ��ѯ���ֶβ�ѯ���۷�Ʊ��ͼVO
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName ���۷�Ʊ��ѯ����
 * 
 * @since 6.5
 * @version 2015-10-19 ����4:14:19
 * @author ����
 */
@Component("���۷�Ʊ")
@OpenAPI(value = OpenLevel.OPENED)
public interface ISaleinvoiceQueryAPI extends ISOQueryAPI{
  

  /**
  * �������۷�Ʊ����������ѯ���۷�Ʊ��ͼVO
  * 
  * @param bids ���۷�Ʊ������������
  * @return ���۷�Ʊ��ͼVO����
  * @throws BusinessException �쳣
  */
  SaleInvoiceViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
  * �������۷�Ʊ������������Ҫ��ѯ���ֶβ�ѯ���۷�Ʊ��ͼVO
  * 
  * @param bids ���۷�Ʊ��������
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ���۷�Ʊ��ͼVO����
  * @throws BusinessException �쳣
  */
  SaleInvoiceViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
  * �������۷�Ʊ������ѯ���۷�ƱVO
  * 
  * @param ids ���۷�Ʊ����
  * @return ���۷�ƱVO����
  * @throws BusinessException �쳣
  */
  SaleInvoiceVO[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
  * �������۷�Ʊ��������Ҫ��ѯ���ֶβ�ѯ���۷�ƱVO
  * 
  * @param ids ���۷�Ʊ����
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ���۷�ƱVO����
  * @throws BusinessException �쳣
  */
  SaleInvoiceVO[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ���۷�Ʊ��ͼVO
   * 
   * @param queryscheme ��ѯ����
   * @return ���۷�Ʊ��ͼVO����
   * @throws BusinessException �쳣
   */
  SaleInvoiceViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * ���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۷�Ʊ��ͼVO
   * 
   * @param queryscheme ��ѯ����
   * @param queryFields ��Ҫ��ѯ���ֶ�
   * @return ���۷�Ʊ��ͼVO����
   * @throws BusinessException �쳣
   */
  SaleInvoiceViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] queryFields) throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ������ѯ���۷�ƱVO����
   * 
   * @param queryscheme ��ѯ����
   * @return ���۷�ƱVO����
   * @throws BusinessException �쳣
   */
  SaleInvoiceVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * ���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ���۷�ƱVO����
   * 
   * @param queryscheme ��ѯ����
   * @param  queryFields ��Ҫ��ѯ���ֶ�
   * @return ���۷�ƱVO����
   * @throws BusinessException �쳣
   */
  SaleInvoiceVO[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
  * �������۷�Ʊ��Դ������id��ѯ���۷�Ʊ��ͼVO
  * 
  * @param sourceBIDs ���۷�Ʊ��Դ��id
  * @return ���۷�Ʊ��ͼVO����
  * @throws BusinessException �쳣
  */
  SaleInvoiceViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs)
      throws BusinessException;

  /**
  * �������۷�Ʊ��Դ������id����Ҫ��ѯ���ֶβ�ѯ���۷�Ʊ��ͼVO
  * 
  * @param sourceBIDs ���۷�Ʊ��Դ��id
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ���۷�Ʊ��ͼVO����
  * @throws BusinessException �쳣
  */
  SaleInvoiceViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs, String[] queryFields)
      throws BusinessException;


}
