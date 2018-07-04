package nc.pubitf.so.m30.mmdp.altc;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ����������Ľӿ�
 * 
 * @since 6.36
 * @version 2015-1-22 ����5:12:05
 * @author ��¼
 */
public interface IQueryToExcuteSaleOrderViewVOs {

  /**
   * �������۶�����ϸ������ѯδִ�д����㡢��������δ�رյ����۶���
   * Ϊ�ƻ������ṩ
   * 
   * @param csaleorderbids ���۶�����ϸ����
   * @return ���۶�����ͼ
   * @throws BusinessException �쳣
   */
  SaleOrderViewVO[] queryToExcuteSaleOrderViewVOs4PO(String[] csaleorderbids)
      throws BusinessException;

  /**
   * �������۶�����ϸ������ѯδִ�д����㡢��������δ�رյ����۶���
   * Ϊ���������ṩ
   * 
   * @param csaleorderbids ���۶�����ϸ����
   * @return ���۶�����ͼ
   * @throws BusinessException �쳣
   */
  SaleOrderViewVO[] queryToExcuteSaleOrderViewVOs4MO(String[] csaleorderbids)
      throws BusinessException;
}
