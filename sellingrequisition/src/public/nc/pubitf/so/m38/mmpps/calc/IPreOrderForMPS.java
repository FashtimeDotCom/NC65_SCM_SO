package nc.pubitf.so.m38.mmpps.calc;

import nc.vo.pub.BusinessException;

import nc.pubitf.mmpub.sdmanage.IDemandResult;

/**
 * Ԥ�����ṩ��������MPS����ӿ�
 * 
 * @since 6.1
 * @version 2011-12-09 08:59
 * @author ������
 * 
 */

public interface IPreOrderForMPS {

  /**
   * ��ѯ����Ԥ������δ�ر�����
   * 
   * @return ����δ�رյ����۶����������
   * @throws BusinessException
   */
  IDemandResult getDemandForPreOrder() throws BusinessException;

}
