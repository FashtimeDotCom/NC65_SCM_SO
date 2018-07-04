package nc.pubitf.so.m30.pub;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * ���۶��������ģ���������۶���ʱ�ṩ�Ĳ�ȫ���ݽӿ�
 * 
 * @since 6.31
 * @version 2013-11-20 08:55:35
 * @author liujingn
 */
public interface ISaleOrderFillDataForPub {

  /**
   * �����۶�������ҵ��������ݽ�����������
   * ҵ�����̡���Ʊ�ͻ�������״̬���������ڡ��ƻ��������ڡ��ƻ��������ڡ������ۿۡ���Ʒ�ۿۡ���״̬�����ҡ���������
   * ��˰��Ϣ��ء��տ�Э����Ϣ�� ֱ�˲֡����š�ȫ�ֻ��ʼ���
   * 
   * @param ordervos ���۶���VO
   * @return ���۶���VO
   * @throws BusinessException
   */
  SaleOrderVO[] getFillSaleorderVO(SaleOrderVO[] ordervos)
      throws BusinessException;

  /**
   * �������۶��������������۽�ͨ��ָ��editkey�������������۽���㷨
   * 
   * @param ordervos ���۶���VO
   * 
   * @param editkey �������۽���㷨�ֶ�
   * 
   * @throws BusinessException
   */
  void calSaleOrderNumpriceMny(SaleOrderVO[] ordervos, String editkey)
      throws BusinessException;

}
