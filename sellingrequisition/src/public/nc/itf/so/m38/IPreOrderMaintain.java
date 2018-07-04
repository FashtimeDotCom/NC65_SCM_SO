package nc.itf.so.m38;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * Ԥ����ά������
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>Ԥ����ɾ��������
 * <li>Ԥ�����������������
 * <li>Ԥ�����޸ı��������
 * <li>Ԥ������ѯ������
 * </ul>
 * <p>
 * 
 * @since 6.0
 * @version 2010-11-3 ����10:41:33
 * @author ��־ΰ
 */
public interface IPreOrderMaintain {
  /**
   * ��������������Ԥ����ɾ��������
   * 
   * @throws BusinessException
   * @since 6.0
   */
  void deletePreOrder(PreOrderVO[] bills) throws BusinessException;

  /**
   * ��������������Ԥ�����������������
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO insertPreOrder(PreOrderVO bill) throws BusinessException;

  /**
   * Ԥ����ʧЧ�ӿ�
   * 
   * @param vos
   * @throws BusinessException
   */
  PreOrderVO[] invalidationPreorder(PreOrderVO[] vos) throws BusinessException;

  /**
   * ��������������Ԥ������ѯ����
   * 
   * @param queryScheme
   * @throws BusinessException
   */
  PreOrderVO[] queryPreOrder(IQueryScheme queryScheme) throws BusinessException;

  /**
   * ��������������Ԥ������ѯ������
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] queryPreOrder(String sql) throws BusinessException;

  /**
   * ���������������ṩ�����۶�������Ԥ������ѯ����
   * 
   * @param queryScheme
   * @throws BusinessException
   */
  PreOrderVO[] queryPreOrderFor30(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * ��������������Ԥ�����޸ı��������
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] updatePreOrder(PreOrderVO[] bill, PreOrderVO[] originBill)
      throws BusinessException;

}
