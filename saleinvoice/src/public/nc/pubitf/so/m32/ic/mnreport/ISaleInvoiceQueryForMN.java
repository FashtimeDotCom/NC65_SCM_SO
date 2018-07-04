package nc.pubitf.so.m32.ic.mnreport;

import nc.vo.ic.icreport.param.mncommon.MNReportQueryParam;
import nc.vo.ic.icreport.param.mncommon.MNRptTempTableWrapperParam;
import nc.vo.pub.BusinessException;

/**
 * ���۷�ƱΪ���ʻ�����ȡ��
 *
 * @since 6.1
 * @version 2012-02-21 ����15:00:56
 * @author ������
 *
 */
public interface ISaleInvoiceQueryForMN {

	/**
	 * �����۷�Ʊȡ�������뵽���ʱ������ʱ����
	 *
	 * @param reportQueryParam ��ѯ����
	 * @param rptTableWrapper ��ʱ�����
	 * @throws BusinessException
	 */
	public MNRptTempTableWrapperParam querySendAndInvoice(
			MNReportQueryParam reportQueryParam)
	throws BusinessException;

}
