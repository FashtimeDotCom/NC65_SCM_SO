package nc.itf.so.m38.profit;

import nc.vo.pub.BusinessException;
import nc.vo.so.entry.ProfitVO;

/**
 * Ԥ����ë��Ԥ����������/�㷨����
 * <ol>
 * <li>���ݴ����Ԥ����ͷID�ļ��ϼ������ж����н���ë��Ԥ��
 * </ol>
 * 
 * @since 6.0
 * @version 2011-7-14 ����10:05:00
 * @author ������
 * @see
 */
public interface IPreOrderProfitCal {

  /**
   * ë��������Ҫ������ȡ�ɱ���ͳɱ�����
   * 
   * @param hids Ԥ����ͷID����
   * @return ����õ�ë��VO
   * @throws BusinessException
   * @see
   */
  ProfitVO[] caculate38Profit(String[] hids) throws BusinessException;

}
