package nc.pubitf.so.m30.so.report;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.paravo.OrderFormReportParaVO;
import nc.vo.so.m30.paravo.OrderReturnToReportVO;

/**
 * ���۶������ۺ��ձ��Ĳ�ѯ���ݽӿ�
 * 
 * @since 6.0
 * @version 2011-1-20 ����02:43:06
 * @author ô��
 */

public interface ISaleOrderForDaily {

  /**
   * ���ۺ��ձ��Ĳ�ѯ���ݵĽӿڷ���
   * 
   * @param paravo OrderFormReportParaVO����VO
   * @return OrderReturnToReportVO ���ض���
   * @throws BusinessException
   */
  OrderReturnToReportVO[] getDailyDataFromOrder(OrderFormReportParaVO paravo)
      throws BusinessException;

}
