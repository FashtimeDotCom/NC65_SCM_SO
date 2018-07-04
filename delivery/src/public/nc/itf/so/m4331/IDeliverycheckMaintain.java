package nc.itf.so.m4331;

import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

/**
 * �������ʼ�ӿ�
 * 
 * @since 6.0
 * @version 2010-12-21 ����09:40:11
 * @author ף����
 */
public interface IDeliverycheckMaintain {
  /**
   * ������������������������ɾ�����ܡ�
   * isCheck true �����Ǳ������ false �������ʼ챨���������
   */
  void deleteDeliverycheck(DeliveryCheckVO[] vos, boolean isCheck)
      throws BusinessException;

  /**
   * ���������������������������湦�ܡ�
   */
  DeliveryCheckVO[] insertDeliverycheck(DeliveryCheckVO[] vos)
      throws BusinessException;

  /**
   * ��ѯ����ʼ�vo
   */
  DeliveryCheckVO[] queryDeliveryCheckVO(String sql);

  /**
   * ���·������ʼ��
   * 
   * @param vos
   * @return
   */
  DeliveryCheckVO[] updateDeliverycheck(DeliveryCheckVO[] vos);
}
