package nc.pubitf.so.m4331.opc.mecc1;

import nc.vo.pub.BusinessException;

/**
 * ��������������������Ԥ�����ṩ��ERPϵͳ������������Ϣ�ӿ�
 * 
 * ʹ�ó����������������϶�����ѯASN������Ϣ
 * 
 * @since 6.0
 * @version 2011-12-28 ����02:30:31
 * @author zhangcheng
 */

public interface IQuerySendInfo {

  /**
   * ���ݷ���������id��ѯ��������������ϵ�ˡ�������ϵ�绰��Ҫ���ջ�����
   * 
   * @param bids ����������id
   * @return ReturnSendInfoVO[]
   * @throws BusinessException
   */
  ReturnSendInfoVO[] query(String[] bids) throws BusinessException;

}
