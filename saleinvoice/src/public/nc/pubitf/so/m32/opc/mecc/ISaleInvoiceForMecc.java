package nc.pubitf.so.m32.opc.mecc;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.so.m32.opc.mecc.SaleInvoiceQueryConditionVO;

/**
 * ��ѯ��Ʊ��Ϣ�ӿ�
 * 
 * @since 6.3
 * @version 2012-10-22 ����07:41:31
 * @author ������
 */
public interface ISaleInvoiceForMecc {
  /**
   * �������۶�����ѯ��Ʊ
   * 
   * @param saleorderhids ���۶�����ͷID����
   * @param saleorderbids ���۶�������ID����
   * @param fieldnames ��Ʊ�ֶ�����
   * @return ���۷�ƱviewVO����
   * @throws BusinessException
   */
  SaleInvoiceViewVO[] querySaleInvoiceByOrder(String[] saleorderhids,
      String[] saleorderbids, String[] fieldnames) throws BusinessException;

  /**
   * ���ݿͻ����������估����״̬��ѯ��Ʊ��Ϣ
   * 
   * @param condition ��ѯ����VO
   * @return ���۷�ƱviewVO����
   * @throws BusinessException
   */
  SaleInvoiceViewVO[] querySaleInvoiceByCondition(
      SaleInvoiceQueryConditionVO condition, String[] fieldnames)
      throws BusinessException;
}
