package nc.itf.so.m4331;

import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOParameterVO;

/**
 * �������������ܲ���
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������رղ�����
 * <li>�����������򿪲�����
 * <li>�������йرղ�����
 * <li>�������д򿪲�����
 * <li>������Ԥ����
 * </ul>
 * <p>
 * 
 * @author ף����
 * @time 2010-04-09 ����02:43:18
 */
public interface IDeliveryAssitFunc {

  /**
   * �������������������������رղ���
   * 
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] closeDelivery(DeliveryVO[] bills) throws BusinessException;
  
  /**
   * �������������������������رղ���
   * 
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] closeDelivery(SOParameterVO paravo) throws BusinessException;

  /**
   * ���������������������йرղ���
   * 
   * @param bill
   *          ����VO
   * @param rows
   *          ѡ����
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] closeDeliveryRows(DeliveryVO bill, int[] rows)
      throws BusinessException;

  /**
   * ���������������������д򿪲���
   * 
   * @param bill
   *          ����VO
   * @param rows
   *          ѡ����
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] openDeiveryRows(SOParameterVO paravo, int[] rows)
      throws BusinessException;

  /**
   * �������������������������򿪲���
   * 
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] openDelivery(SOParameterVO paravo) throws BusinessException;

  /**
   * ��������������������Ԥ��
   * 
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] preKeepDelivery(DeliveryVO[] bills) throws BusinessException;
}
