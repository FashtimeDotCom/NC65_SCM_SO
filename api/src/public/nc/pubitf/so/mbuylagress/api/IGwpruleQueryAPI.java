package nc.pubitf.so.mbuylagress.api;

import nc.itf.annotation.Component;

import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.so.mbuylargess.view.BuyLargessShowViewVO;


/**
 * @description
 * <ul>
 * <li>�����������ñ���������ѯ����������ͼVO
 * <li>�����������ñ�����������Ҫ��ѯ���ֶβ�ѯ����������ͼVO
 * <li>������������������ѯ��������VO
 * <li>��������������������Ҫ��ѯ���ֶβ�ѯ��������VO
 * <li>���ݲ�ѯ������ѯ����������ͼVO
 * <li>���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ����������ͼVO
 * <li>���ݲ�ѯ������ѯ������ѯ��������VO����
 * <li>���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ��������VO����
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName �������ò�ѯ����
 * 
 * @since 6.5
 * @version 2015-10-19 ����4:14:19
 * @author ����
 */
@Component("��������")
@OpenAPI(value = OpenLevel.OPENED)
public interface IGwpruleQueryAPI  extends ISOQueryAPI{
  

  /**
  * �����������ñ���������ѯ����������ͼVO
  * 
  * @param bids �������ñ�����������
  * @return ����������ͼVO����
  * @throws BusinessException �쳣
  */
  BuyLargessShowViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
  * �����������ñ�����������Ҫ��ѯ���ֶβ�ѯ����������ͼVO
  * 
  * @param bids �������ñ�������
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ����������ͼVO����
  * @throws BusinessException �쳣
  */
  BuyLargessShowViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
  * ������������������ѯ��������VO
  * 
  * @param ids ������������
  * @return ��������VO����
  * @throws BusinessException �쳣
  */
  BuyLargessVO[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
  * ��������������������Ҫ��ѯ���ֶβ�ѯ��������VO
  * 
  * @param ids ������������
  * @param queryFields ��Ҫ��ѯ���ֶ�
  * @return ��������VO����
  * @throws BusinessException �쳣
  */
  BuyLargessVO[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ����������ͼVO
   * 
   * @param queryscheme ��ѯ����
   * @return ����������ͼVO����
   * @throws BusinessException �쳣
   */
  BuyLargessShowViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * ���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ����������ͼVO
   * 
   * @param queryscheme ��ѯ����
   * @param queryFields ��Ҫ��ѯ���ֶ�
   * @return ����������ͼVO����
   * @throws BusinessException �쳣
   */
  BuyLargessShowViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] queryFields) throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ������ѯ��������VO����
   * 
   * @param queryscheme ��ѯ����
   * @return ��������VO����
   * @throws BusinessException �쳣
   */
  BuyLargessVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * ���ݲ�ѯ��������Ҫ��ѯ���ֶβ�ѯ��������VO����
   * 
   * @param queryscheme ��ѯ����
   * @param  queryFields ��Ҫ��ѯ���ֶ�
   * @return ��������VO����
   * @throws BusinessException �쳣
   */
  BuyLargessVO[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;


}
