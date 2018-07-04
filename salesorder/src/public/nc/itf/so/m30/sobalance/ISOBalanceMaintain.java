/**
 * $�ļ�˵��$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-24 ����04:21:18
 */
package nc.itf.so.m30.sobalance;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author gdsjw
 * @time 2010-6-24 ����04:21:18
 */
public interface ISOBalanceMaintain {
  SoBalanceVO[] querySoBalanceVO(String where, Map<String, String[]> key)
      throws BusinessException;

  SoBalanceVO[] querySoBalanceVO(IQueryScheme queryScheme)
      throws BusinessException;

  SoBalanceVO[] updateSoBalanceVO(SoBalanceVO[] bill) throws BusinessException;

  SoBalanceVO[] insertSoBalanceVO(SoBalanceVO[] bill) throws BusinessException;

  // SoBalanceVO[] deleteSoBalanceVO(SoBalanceVO[] bill)
  // throws BusinessException;

}
