package nc.itf.so.salequotation;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;

/**
 * ���۵���ҳ��ѯ����ӿ�
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-11
 */
public interface ISalequotationMaintainApp {
	/**
	 * ���۵���ҳͨ��QueryScheme��ѯ
	 * 
	 * @param scheme
	 *            UI����֯�Ĳ�ѯ����
	 * @return ���յ��ݺŽ�������ĵ��ݷ�ҳ����������ʽ��ֻ�е�һҳ�ĵ�һ�ŵ��� ���б������ݡ�û�в�ѯ������ʱ�����㳤�ȵ�����
	 * @throws BusinessException
	 */
	PageQueryVO queryM4310App(IQueryScheme scheme)
			throws BusinessException;

	/**
	 * ���۵���ҳͨ��IDs��ѯ
	 * 
	 * @param ids
	 *            ������������
	 * @return ��������ʽ��ֻ�е�һ�ŵ��ݲ��б������ݡ�
	 * @throws BusinessException
	 */
	AggSalequotationHVO[] queryM4310App(String[] ids)
			throws BusinessException;
}
