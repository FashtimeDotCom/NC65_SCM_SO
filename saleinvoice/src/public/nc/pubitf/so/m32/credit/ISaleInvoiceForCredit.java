package nc.pubitf.so.m32.credit;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * ���۷�Ʊ�������ṩ�Ľӿ�
 * 
 * @since 6.0
 * @version 2011-6-27 ����05:17:31
 * @author ô��
 */
public interface ISaleInvoiceForCredit {
  /**
   * ���ݷ�ƱID��ѯ�������ں��������
   * 
   * @param bids ���۷�Ʊ��id����
   * @return Map<String,UFDate[]> --- <���۷�Ʊ��id,UFDate[0]:�������� UFDate[1]:�������>
   * @throws BusinessException
   */
  Map<String, UFDate[]> getBusiDateBy32Bids(String[] bids)
      throws BusinessException;

  /**
   * ���ݳ��ⵥID��ѯ�������ں��������
   * 
   * @param bids ���۳��ⵥ��id����
   * @return
   * @throws BusinessException
   */
  Map<String, UFDate[]> getBusiDateBy4CBids(String[] bids)
      throws BusinessException;
}
