package nc.pubitf.so.m32.ic.m4453;

import nc.vo.pub.BusinessException;

/**
 * ���۷�Ʊ��ǩ��;�𵥵Ľӿ�
 * 
 * @since 6.0
 * @version 2011-9-7 ����10:20:06
 * @author ô��
 */
public interface ISaleInvoiceFor4453 {

  /**
   * �ж�ǩ��;�� �Ƿ������η�Ʊ
   * 
   * @param ids ���ⵥ��ͷID
   * @param bids ���ⵥ����id
   * @throws BusinessException
   */
  void isHasDest(String[] ids, String[] bids) throws BusinessException;
}
