package nc.pubitf.so.m32.crm;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

import nc.pubitf.so.m4310.crm.CRMQueryPara;

/**
 * ΪCRM�ṩ�����۷�Ʊ��ѯ�ӿ�
 * 
 * @since 6.3.1
 * @version 2013-08-06 20:27:17
 * @author ���Ʒ�
 */
public interface ISaleInvoiceQueryForCRM {

  /**
   * ����CRM���������ѯ���۷�Ʊ
   * 
   * @param queryPara CRM��������
   * 
   * @return ���۷�Ʊ��ͷVO
   * @throws BusinessException
   */
  public SaleInvoiceHVO[] querySaleInvoice(CRMQueryPara queryPara)
      throws BusinessException;

  /**
   * ����������ѯ���۷�Ʊ
   * 
   * @param id ���۶�������ID
   * 
   * @return ���۶�������VO
   * @throws BusinessException
   */
  public SaleInvoiceBVO[] querySaleInvoiceById(String id)
      throws BusinessException;
}
