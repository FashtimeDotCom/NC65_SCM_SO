package nc.itf.so.m30.profit;

import nc.vo.pub.BusinessException;
import nc.vo.so.entry.ProfitVO;

/**
 * ���۶���ë��Ԥ����������/�㷨����
 * <ol>
 * <li>���ݴ�������۶���ͷID�ļ��ϼ������ж����н���ë��Ԥ��
 * <li>
 * </ol>
 * <b>����ʾ����</b>����˵��
 * 
 * <pre>
 * ����Ƭ��
 * </pre>
 * 
 * @since 6.0
 * @version 2011-7-13 ����16:05:00
 * @author ������
 * @see
 */
public interface ISaleOrderProfitCal {

  /**
   * ë��������Ҫ������ȡ�ɱ���ͳɱ�����
   * 
   * @param hids ���۶���ͷID����
   * @return ����õ�ë��VO
   * @throws BusinessException
   * @see
   */
  ProfitVO[] caculate30Profit(String[] hids) throws BusinessException;

}
