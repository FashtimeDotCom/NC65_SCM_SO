package nc.itf.so.m32;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * ���۷�Ʊ��ҳ��ѯ����ӿ�
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-10
 */
public interface ISaleInvoiceMaintainApp {
	/**
	 * ���۷�Ʊ��ҳͨ��QueryScheme��ѯ
	 * 
	 * @param scheme
	 *            UI����֯�Ĳ�ѯ����
	 * @return ���յ��ݺŽ�������ĵ��ݷ�ҳ����������ʽ��ֻ�е�һҳ�ĵ�һ�ŵ��� ���б������ݡ�û�в�ѯ������ʱ�����㳤�ȵ�����
	 * @throws BusinessException
	 */
	PageQueryVO queryM32App(IQueryScheme scheme, Boolean isLazyLoad)
			throws BusinessException;

	/**
	 * ���۷�Ʊ��ҳͨ��IDs��ѯ
	 * 
	 * @param ids
	 *            ������������
	 * @return ��������ʽ��ֻ�е�һ�ŵ��ݲ��б������ݡ�
	 * @throws BusinessException
	 */
	SaleInvoiceVO[] queryM32App(String[] ids, Boolean isLazyLoad)
			throws BusinessException;
	
	public boolean searchDownData(String pk) throws BusinessException;
}
