package nc.itf.so.m4310.profit;

import nc.vo.pub.BusinessException;
import nc.vo.so.entry.ProfitVO;

/**
 * ���۱��۵�ë��Ԥ����������/�㷨����
 * 
 * ���ݴ�������۱��۵�ͷID�ļ��ϼ������ж����н���ë��Ԥ��
 * 
 * @since 6.0
 * @version 2011-9-2 ����10:54:51
 * @author ô��
 */
public interface ISaleQuotationProfitCal {

  /**
   * ë��������Ҫ������ȡ�ɱ���ͳɱ�����
   * 
   * @param hids ���۶���ͷID����
   * @return ����õ�ë��VO
   * @throws BusinessException
   * @see
   */
  ProfitVO[] caculate4310Profit(String[] hids) throws BusinessException;

}
