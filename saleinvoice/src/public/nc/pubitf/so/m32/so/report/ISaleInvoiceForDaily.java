package nc.pubitf.so.m32.so.report;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32.paravo.InvoiceFormReportParaVO;
import nc.vo.so.m32.paravo.InvoiceReturnToReportVO;

public interface ISaleInvoiceForDaily {
  /**
   * ���ۺ��ձ��Ĳ�ѯ���ݵĽӿڷ���
   * 
   * @param paravo ����VO
   * @return ���ض���
   * @throws BusinessException
   */
  InvoiceReturnToReportVO[] getDailyDataFromInvoice(
      InvoiceFormReportParaVO paravo) throws BusinessException;
}
