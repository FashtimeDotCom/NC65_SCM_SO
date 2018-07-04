package nc.itf.so.m38.arrange;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m38.entity.PreOrderViewVO;

/**
 * <p>
 * <b>Ԥ�������ŷ���ӿڣ����ܣ�</b>
 * 
 * <ul>
 * <li>��ѯԤ����viewVO
 * <li>
 * <li>...
 * </ul>
 * 
 * @since 6.0
 * @version 2010-6-29 ����09:36:43
 * @author ��־ΰ
 */
public interface IPreOrderArrange {

  /**
   * ��ѯԤ����viewVO
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  PreOrderViewVO[] queryPreOrderViewVO(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * ����������������ѯԤ����viewVO��
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderViewVO[] queryPreOrderViewVO(String sql) throws BusinessException;
  
  PreOrderViewVO[] queryPreOrderViewVO(String[] prebids) throws BusinessException;
}
