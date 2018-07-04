package nc.pubitf.so.m4310.crm;

import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * ΪCRM�ṩ�ı��۵���ѯ�ӿ�
 * 
 * @since 6.3.1
 * @version 2013-08-06 08:41:07
 * @author ���Ʒ�
 */
public interface ISaleQuotationQueryForCRM {

  /**
   * ����CRM���������ѯ���۱��۵���ͷ�ֶ�
   * 
   * @param queryPara CRM��������
   * 
   * @return SalequotationHVO[] ���۵���ͷVO
   * @throws BusinessException
   */
  SalequotationHVO[] querySaleQuotationVOs(CRMQueryPara queryPara)
      throws BusinessException;

  /**
   * ���ݱ��۵�����ID��ѯ�����۵��ۺ�VO
   * 
   * @param id ���۵�����ID
   * @return ���۵�����VO
   * @throws BusinessException
   */
  SalequotationBVO[] querySaleQuotationVOsById(String id)
      throws BusinessException;
}
