package nc.itf.so.m30.revise;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * ���۶����޶���ҳ��ѯ����ӿ�
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-10
 */
public interface ISaleOrderReviseMaintainApp {
	/**
	 * ���۶����޶���ҳͨ��QueryScheme��ѯ
	 * 
	 * @param scheme
	 *            UI����֯�Ĳ�ѯ����
	 * @return ���յ��ݺŽ�������ĵ��ݷ�ҳ����������ʽ��ֻ�е�һҳ�ĵ�һ�ŵ��� ���б������ݡ�û�в�ѯ������ʱ�����㳤�ȵ�����
	 * @throws BusinessException
	 */
	PageQueryVO queryM30ReviseApp(IQueryScheme scheme) throws BusinessException;

	/**
	 * ���۶����޶���ҳͨ��IDs��ѯ
	 * 
	 * @param ids
	 *            ������������
	 * @return ��������ʽ��ֻ�е�һ�ŵ��ݲ��б������ݡ�
	 * @throws BusinessException
	 */
	SaleOrderHistoryVO[] queryM30ReviseApp(String[] ids) throws BusinessException;
}
