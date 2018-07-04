package nc.vo.so.pub.tolerance;

/**
 * ���۶����������ӿ�
 * 
 * @since 6.0
 * @version 2011-12-28 ����01:08:02
 * @author fengjb
 */
public interface IAbandonToleranceCheck {
  /**
   * �����������ݲ��쳣���
   * 
   * @author ף����
   * @time 2010-8-31 ����01:41:35
   */
  void abandonDeliveryToleranceCheck();

  /**
   * �������۷�Ʊ�ݲ��쳣���
   * 
   * @author ף����
   */
  void abandonInvoinceToleranceCheck();

  /**
   * �������۶����ݲ��쳣���
   * 
   * @author ף����
   */
  void abandonOrderToleranceCheck();

  /**
   * ����Ԥ�����ݲ��쳣���
   * 
   * @author ף����
   */
  void abandonPreOrderToleranceCheck();

  /**
   * �������۳����ݲ��쳣���
   * 
   * @author ף����
   */
  void abandonOutToleranceCheck();

  /**
   * ������ͬ�ݲ��쳣���
   */
  void abandonCtToleranceCheck();

  /**
   * ������׼�����ݲ��쳣���
   */
  void abandonCustomerPOToleranceCheck();
}
