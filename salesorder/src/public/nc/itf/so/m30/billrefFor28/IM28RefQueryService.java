/**
 * 
 */
package nc.itf.so.m30.billrefFor28;

import java.util.HashMap;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.it.m5801.entity.ContractVO;
import nc.vo.lm.lsdlxy.AggLsxywtHVO;
import nc.vo.pp.m28.entity.PriceAuditVO;
import nc.vo.pub.BusinessException;

/**
 * @author wangzym
 * @version 2017��6��6�� ����9:35:00
 */
public interface IM28RefQueryService {
	// ���ε���AggVo
	public PriceAuditVO[] query28For30(IQueryScheme queryScheme)
			throws BusinessException;
	// ���ε���AggVo
	public AggLsxywtHVO[] queryLS41For30(IQueryScheme queryScheme)
			throws BusinessException;

	// ɾ��ʱҪ�ж����ε����Ƿ��Ѿ����ɽ��ں�ͬ�����������������ɾ�������ܽ��ں�ͬ�Ƿ�ɾ�������ж�dr=0��
	public HashMap<String, Integer> queryForSaleOrderDel(String pk_Head[])
			throws BusinessException;
}
