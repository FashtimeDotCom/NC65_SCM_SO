package nc.itf.so.ordersummary;

import nc.vo.pub.BusinessException;

import com.ufida.dataset.IContext;

/**
 * ���۶���ִ�л��ܽӿ�
 * 
 * @since 6.3
 * @version 2012-9-24 ����02:26:51
 * @author ������
 */
public interface IOrderSummaryMaintain {
  /**
   * 
   * @param context ������
   * @return String ��ѯ��ʱ���SQL���
   * @throws BusinessException
   */
  public String queryOrderSummary(IContext context) throws BusinessException;
}
