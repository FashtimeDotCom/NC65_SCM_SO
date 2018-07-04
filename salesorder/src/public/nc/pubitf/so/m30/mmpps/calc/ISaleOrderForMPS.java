package nc.pubitf.so.m30.mmpps.calc;

import nc.vo.pub.BusinessException;

import nc.pubitf.mmpub.sdmanage.IDemandResult;

/**
 * ���۶����ṩ��������MPS����ӿ�
 * 
 * @since 6.1
 * @version 2011-12-05 16:30
 * @author ������
 */

public interface ISaleOrderForMPS {

  /**
   * ��ѯ����Ԥ������δ�ر�����
   * 
   * @return ����δ�رյ����۶����������
   * @throws BusinessException
   */

  IDemandResult getDemandFroSaleOrder() throws BusinessException;

}
