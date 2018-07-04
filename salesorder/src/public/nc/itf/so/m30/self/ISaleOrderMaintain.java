/**
 * 
 */
package nc.itf.so.m30.self;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ���۶���ά������
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>��ѯ������
 * </ul>
 * 
 * <p>
 * 
 * @author gdsjw
 * 
 */
public interface ISaleOrderMaintain {

	SaleOrderVO[] querySaleorder(String[] hids) throws BusinessException;

	SaleOrderVO[] querySaleOrder(String sql) throws BusinessException;

	SaleOrderVO[] querySaleOrderFor21(IQueryScheme queryScheme)
			throws BusinessException;

	SaleOrderVO[] querySaleOrderFor5801(IQueryScheme queryScheme)
			throws BusinessException;

	SaleOrderVO[] querySaleOrderFor30Return(IQueryScheme queryScheme)
			throws BusinessException;

	SaleOrderVO[] querySaleOrderFor32(IQueryScheme queryScheme)
			throws BusinessException;

	SaleOrderVO[] querySaleOrderFor4331(IQueryScheme queryScheme)
			throws BusinessException;

	SaleOrderVO[] querySaleOrderFor4816(IQueryScheme queryScheme)
			throws BusinessException;

	SaleOrderVO[] querySaleOrderFor4C(IQueryScheme queryScheme)
			throws BusinessException;

	SaleOrderVO[] querySaleOrderForCoop(IQueryScheme queryScheme)
			throws BusinessException;

	SaleOrderVO[] querySaleOrderForCoop(IQueryScheme queryScheme,
			String pk_puorg) throws BusinessException;

	SaleOrderViewVO[] querySaleorderForTbb(String sql) throws BusinessException;

	AggregatedValueObject querySaleOrderFor30Revise(Class<? extends AbstractBill> c, String pk_SaleOrderHistory)
			throws BusinessException;
}
