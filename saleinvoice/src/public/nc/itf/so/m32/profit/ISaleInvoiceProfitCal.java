package nc.itf.so.m32.profit;

import nc.vo.pub.BusinessException;
import nc.vo.so.entry.ProfitVO;

/**
 * ���۷�Ʊë��Ԥ����������/�㷨����
 * <ol>
 * <li>���ݴ��������Ʊ����ͷID�ļ��ϼ������ж����н���ë��Ԥ��
 * <li>
 * </ol>
 * <b>����ʾ����</b>����˵��
 * 
 * <pre>
 * ����Ƭ��
 * </pre>
 * 
 * @since 6.0
 * @version 2011-7-14 ����9:05:00
 * @author ������
 */
public interface ISaleInvoiceProfitCal {

  /**
   * ë��������Ҫ������ȡ�ɱ���ͳɱ�����
   * 
   * @param hids ���۷�ƱͷID����
   * @return ����õ�ë��VO
   * @throws BusinessException
   */
  ProfitVO[] caculate32Profit(String[] hids) throws BusinessException;

}
