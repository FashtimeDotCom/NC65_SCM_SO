package nc.pubitf.so.m4331.so.m30;

import nc.vo.pub.lang.UFDouble;

/**
 * ���۶����޶�ʱ�����ķ������ļ۸�
 * 
 * @since 6.0
 * @version 2011-5-27 ����01:27:20
 * @author ף����
 */
public interface IDeliveryPriceParaFor30 {
  /**
   * ������۶�������id
   * 
   * @return
   */
  String getHid();

  /**
   * ����λ������˰����
   * 
   * @return
   */
  UFDouble getNnetprice();

  /**
   * ����λԭ����˰����
   * 
   * @return
   */
  UFDouble getNorignetprice();

  /**
   * ����λԭ����˰����
   * 
   * @return
   */
  UFDouble getNorigprice();

  /**
   * ����λԭ�Һ�˰����
   * 
   * @return
   */
  UFDouble getNorigtaxnetprice();

  /**
   * ����λԭ�Һ�˰����
   * 
   * @return
   */
  UFDouble getNorigtaxprice();

  /**
   * ����λ������˰����
   * 
   * @return
   */
  UFDouble getNprice();

  /**
   * ���۵�λ������˰����
   * 
   * @return
   */
  UFDouble getNqtnetprice();

  /**
   * ���۵�λԭ����˰����
   * 
   * @return
   */
  UFDouble getNqtorignetprice();

  /**
   * ���۵�λԭ����˰����
   * 
   * @return
   */
  UFDouble getNqtorigprice();

  /**
   * ���۵�λԭ�Һ�˰����
   * 
   * @return
   */
  UFDouble getNqtorigtaxnetprc();

  /**
   * ���۵�λԭ�Һ�˰����
   * 
   * @return
   */
  UFDouble getNqtorigtaxprice();

  /**
   * ���۵�λ������˰����
   * 
   * @return
   */
  UFDouble getNqtprice();

  /**
   * ���۵�λ���Һ�˰����
   * 
   * @return
   */
  UFDouble getNqttaxnetprice();

  /**
   * ���۵�λ���Һ�˰����
   * 
   * @return
   */
  UFDouble getNqttaxprice();

  /**
   * ����λ���Һ�˰����
   * 
   * @return
   */
  UFDouble getNtaxnetprice();

  /**
   * ����λ���Һ�˰����
   * 
   * @return
   */
  UFDouble getNtaxprice();
  
  /**
   * ������
   * @return
   */
  String getCmffileid();
}
