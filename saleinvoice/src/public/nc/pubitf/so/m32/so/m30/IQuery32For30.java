package nc.pubitf.so.m32.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

public interface IQuery32For30 {

  /**
   * ��ö������η�Ʊ����������
   * 
   * @param ids
   * @param bids ������ID
   * @return ������IDΪKEY ��������ΪVALUE��ֵ
   * @throws BusinessException
   */
  Map<String, UFDouble> getInvoiceApproveNum(String[] ids, String[] bids)
      throws BusinessException;

  /**
   * ��鶩�������η�Ʊ�Ƿ�ȫ�����
   * 
   * @param orderid
   * @param orderbids ������ID
   * @return �Ƿ�ȫ�����
   * @throws BusinessException
   */
  UFBoolean[] isInvoiceAllApprove(String[] orderid, String[] orderbids)
      throws BusinessException;

  /**
   * �����Ƿ�������η�Ʊ
   * 
   * @param orderhids
   * 
   * @return �Ƿ�������η�Ʊ
   * @throws BusinessException
   */
  Map<String, UFBoolean> isExistNextInvoice(String[] orderhids)
      throws BusinessException;

  /**
   * ��ö������η�Ʊ�Ŀ�Ʊ����˰�ϼƣ�
   * 
   * @param ids
   * @param bids ������ID
   * @return ������IDΪKEY ��Ʊ���ΪVALUE��ֵ
   * @throws BusinessException
   */
  Map<String, UFDouble> getInvoiceNorigtaxmny(String[] ids, String[] bids)
      throws BusinessException;
}
