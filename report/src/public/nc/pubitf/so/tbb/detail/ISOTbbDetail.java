package nc.pubitf.so.tbb.detail;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.tb.obj.NtbParamVO;

/**
 * Ԥ���������۶���
 * 
 * @since 6.0
 * @version 2011-6-24 ����04:17:57
 * @author ף����
 */
public interface ISOTbbDetail {
  // TODO ��ӱ� 2012.03.14
  SaleOrderViewVO[] getSaleorderDetail(NtbParamVO ntbparamvos)
      throws BusinessException;

  SaleInvoiceViewVO[] getInvoiceDetail(NtbParamVO ntbparamvos)
      throws BusinessException;

}
