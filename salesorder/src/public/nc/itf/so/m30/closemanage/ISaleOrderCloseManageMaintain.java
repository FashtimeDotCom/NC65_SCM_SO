package nc.itf.so.m30.closemanage;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.SOParameterVO;

/**
 * ���۶����رչ���ά���ӿ�
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>���۶�����ѯ
 * <li>���۶�������ر�
 * <li>���۶��������
 * <li>���۶�����Ʊ�ر�
 * <li>���۶�����Ʊ��
 * <li>���۶��������ر�
 * <li>���۶���������
 * <li>���۶�������ر�
 * <li>���۶��������
 * <li>���۶����йر�
 * <li>���۶����д�
 * <li>���۶��������ر�
 * <li>���۶���������
 * <li>���۶�������
 * <li>���۶����ⶳ
 * </ul>
 * 
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-6-17 ����10:25:34
 */
public interface ISaleOrderCloseManageMaintain {

  /**
   * ���������������ر��������۶�����
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderVO[] closeSaleOrder(SaleOrderVO[] bills) throws BusinessException;

  /**
   * ���������������ر����۶�����Ʊ��
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] closeSaleOrderInvoice(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * ���������������ر����۶������⡣
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] closeSaleOrderOut(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * ���������������ر����۶����С�
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] closeSaleOrderRow(SaleOrderViewVO[] views)
      throws BusinessException;

  /**
   * ���������������ر����۶���������
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] closeSaleOrderSend(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * ���������������ر����۶������㡣
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] closeSaleOrderSettle(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * ���������������������������
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderVO[] freezeSaleOrder(SaleOrderVO[] billVOs) throws BusinessException;

  /**
   * �����������������������۶�����
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SOParameterVO[] openSaleOrder(SaleOrderVO[] vos, boolean isAbandonATP)
      throws BusinessException;

  /**
   * �������������������۶�����Ʊ��
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] openSaleOrderInvoice(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * �������������������۶������⡣
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SOParameterVO[] openSaleOrderOut(SaleOrderVO[] vos, boolean isAbandonATP)
      throws BusinessException;

  /**
   * �������������������۶����С�
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] openSaleOrderRow(SaleOrderViewVO[] views)
      throws BusinessException;

  /**
   * �������������������۶���������
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] openSaleOrderSend(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * �������������������۶������㡣
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] openSaleOrderSettle(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * ����������������ѯ���۶���viewVO��
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] querySaleOrderViewVO(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * �������������������ⶳ������
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderVO[] unFreezeSaleOrder(SaleOrderVO[] billVOs,
      Map<String, Boolean> businessCheckMap) throws BusinessException;
}
