package nc.pubitf.so.m33.so.m32;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * ���۽����ṩ�����۷�Ʊ����
 * 
 * 1.���۷�Ʊ��ʽ�������۽��㵥
 * 
 * 2.���۷�Ʊȡ������
 * 
 * @author zhangcheng
 * 
 */
public interface ISquare33For32 {

  /**
   * ���۷�Ʊȡ������
   * 
   * @param voInvoice
   * @throws BusinessException
   */
  void cancelSquareSrv(SaleInvoiceVO[] voInvoice) throws BusinessException;

  /**
   * ���۷�Ʊ����Զ���ʽ�������۽��㵥
   * 
   * @param voInvoice
   * @throws BusinessException
   */
  void pushSquareSrv(SaleInvoiceVO[] voInvoice) throws BusinessException;

  /**
   * ���㵥Ϊ���۷�Ʊ�ṩ�Ĳ�ѯ��Ʊ�н��㵥��ϸ����
   * 
   * @param invhids ��Ʊ��ͷID����
   * @param invbids ��Ʊ����ID����
   * @return Map<String,String> Key:���㵥��ϸ��ID Value:��Ӧ��Ʊ����ID
   * @throws BusinessException
   * @author ������
   * @time 2012-9-20 ����07:45:44
   */
  Map<String, String> queryInvSquareDetail(String[] invhids, String[] invbids)
      throws BusinessException;
}
