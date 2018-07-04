package nc.itf.so.m4331;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * ��������ҳ��ѯ����ӿ�
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-10
 */
public interface IDeliveryMaintainApp {
	/**
	 * ���۷�Ʊ��ҳͨ��QueryScheme��ѯ
	 * 
	 * @param scheme
	 *            UI����֯�Ĳ�ѯ����
	 * @return ���յ��ݺŽ�������ĵ��ݷ�ҳ����������ʽ��ֻ�е�һҳ�ĵ�һ�ŵ��� ���б������ݡ�û�в�ѯ������ʱ�����㳤�ȵ�����
	 * @throws BusinessException
	 */
	PageQueryVO queryM4331App(IQueryScheme scheme) throws BusinessException;

	/**
	 * ���۷�Ʊ��ҳͨ��IDs��ѯ
	 * 
	 * @param ids
	 *            ������������
	 * @return ��������ʽ��ֻ�е�һ�ŵ��ݲ��б������ݡ�
	 * @throws BusinessException
	 */
	DeliveryVO[] queryM4331App(String[] ids) throws BusinessException;
}
