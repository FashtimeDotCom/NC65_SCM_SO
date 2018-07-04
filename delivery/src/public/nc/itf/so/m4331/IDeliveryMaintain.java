package nc.itf.so.m4331;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOParameterVO;

/**
 * <b>������Ҫ������¹��ܣ�</b> <li>�����������ӿڣ� <li>��������ѯ <li>�������������� <li>�������޸ı��� <li>����������ɾ��
 * <li>������������� <li>��������������
 */
public interface IDeliveryMaintain {
  /**
   * ��������������������������˹��ܡ�
   */
  DeliveryVO[] approveDelivery(DeliveryVO[] vos) throws BusinessException;

  /**
   * ������������������������ɾ�����ܡ�
   */
  void deleteDelivery(DeliveryVO[] vos) throws BusinessException;

  /**
   * ���������������������������湦�ܡ�
   */
  DeliveryVO[] insertDelivery(DeliveryVO[] insertvos) throws BusinessException;

  /**
   * ����������������������ʽ���湦�ܡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param voDeliverys
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-5-31 ����03:15:37
   */
  DeliveryVO[] pushWriteDelivery(SOParameterVO paravo) throws BusinessException;

  DeliveryVO[] queryDelivery(String sql) throws BusinessException;

  /**
   * ���ݷ���������id ��ѯ��������Ϣ
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  DeliveryVO[] queryDelivery(String[] bids) throws BusinessException;

  /**
   * �����������������������������ⵥת����ѯ��
   */
  DeliveryViewVO[] queryDeliveryFor4804(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * �������������������������۳��ⵥת����ѯ��
   */
  DeliveryVO[] queryDeliveryFor4C(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * �����������������������������ⵥת����ѯ��
   */
  DeliveryVO[] queryDeliveryFor4Y(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * ��ѯ��������ͼvo
   * 
   * @param sql
   * @return
   * @throws BusinessException
   */
  DeliveryViewVO[] queryViewVO(String sql) throws BusinessException;

  /**
   * �����������������������������ܡ�
   */
  DeliveryVO[] unapproveDelivery(DeliveryVO[] vos) throws BusinessException;

  /**
   * ���������������������޸ı��湦�ܡ�
   */
  DeliveryVO[] updateDelivery(DeliveryVO[] updatevos, DeliveryVO[] originBills)
      throws BusinessException;
}
