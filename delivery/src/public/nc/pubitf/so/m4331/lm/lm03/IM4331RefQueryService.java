package nc.pubitf.so.m4331.lm.lm03;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * <p>������Ҫʵ�ֹ��ܣ�</p>
 *
 * <li>>��ѯ���ε��� ������ά�� ����ӿ�</li>
 * 
 * @author lyw
 * @version 6.5
 * @time 2017��3��1�� ����8:52:51
 */
public interface IM4331RefQueryService {
	public DeliveryVO[] queryM4331ForLM03(IQueryScheme queryScheme)
			throws BusinessException;

}
