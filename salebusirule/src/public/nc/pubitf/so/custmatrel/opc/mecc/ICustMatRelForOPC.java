package nc.pubitf.so.custmatrel.opc.mecc;

import nc.vo.pub.BusinessException;

/**
 * ���ۿͻ����Ϲ�ϵ���������ͳһ���������ṩ�Ľӿ�
 * ���ڲ�ѯ���������Ŀͻ����ϼ���
 * ���������������֯+�ͻ�+���ϼ��ϵõ��������۵Ŀͻ�+���ϼ���
 * 
 * @since 6.0
 * @version 2011-12-28 ����03:31:43
 * @author ����
 */

public interface ICustMatRelForOPC {

  /**
   * ���������������֯+�ͻ�+���ϼ��ϵõ��������۵Ŀͻ�+���ϼ���
   * 
   * @param paravos CustMatRelParaForOPCVO[]
   * @return ���ݿͻ����Ϲ�ϵ������˳����������Ŀͻ�+���ϼ���
   * @throws BusinessException
   */
  CustMatRelParaForOPCVO[] filterData(CustMatRelParaForOPCVO[] paravos)
      throws BusinessException;

}
