/**
 * $�ļ�˵��$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-24 ����04:21:18
 */
package nc.itf.so.custmatrel;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * @author gdsjw
 */
public interface ICustMatRelMaintain {
  CustMatRelVO queryByOrg(String pk_org) throws BusinessException;

  CustMatRelVO update(CustMatRelVO bill) throws BusinessException;

  CustMatRelVO insert(CustMatRelVO bill) throws BusinessException;

  CustMatRelVO delete(CustMatRelVO bill) throws BusinessException;
	/**
	 * ����where������ѯ����
	 * 
	 * @param queryScheme
	 * @return
	 * @throws BusinessException
	 */
	CustMatRelVO[] queryCustMatRel(IQueryScheme queryScheme)
			throws BusinessException;

	/**
	 * ����XLS
	 * 
	 * @param bills
	 * @return
	 * @throws BusinessException
	 */
	void importXLS(CustMatRelVO[] bills) throws BusinessException;
}
