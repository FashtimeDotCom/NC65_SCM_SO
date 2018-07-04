package nc.pubitf.so.m30.ic.pub;

import nc.vo.pub.BusinessException;

/**
 * 
 * ���۶����ṩ������������۳��ⵥ��ǩ��;������������ʱӰ��ͻ����õ���ƾ֤�ӿ�
 * 
 * @since 6.3
 * @version 2014-06-30 19:00:55
 * @author ����
 */

public interface IArsubToVoucherForIC {

  /**
   * ���ó�����<br>
   * 
   * ���۳��ⵥǩ֤ʱ���ô˽ӿڿͻ����õ���ƾ֤<br>
   * 
   * @param data
   * 
   * @throws BusinessException
   * 
   */
  void onSaleOutSign(ArsubToVoucherData[] data) throws BusinessException;

  /**
   * ���ó�����<br>
   * 
   * ���۳��ⵥȡ��ǩ֤ʱ���ô˽ӿڿͻ����õ���ƾ֤<br>
   * 
   * @param data
   * 
   * @throws BusinessException
   */
  void onSaleOutUnSign(ArsubToVoucherData[] data) throws BusinessException;

  /**
   * ���ó�����<br>
   * 
   * ǩ��;��ǩ֤ʱ���ô˽ӿڿͻ����õ���ƾ֤<br>
   * 
   * @param data
   * 
   * @throws BusinessException
   */
  void onWastageSign(ArsubToVoucherData[] data) throws BusinessException;

  /**
   * ���ó�����<br>
   * 
   * ǩ��;��ȡ��ǩ֤ʱ���ô˽ӿڿͻ����õ���ƾ֤
   * 
   * @param data
   * @throws BusinessException
   */
  void onWastageUnSign(ArsubToVoucherData[] data) throws BusinessException;

}
