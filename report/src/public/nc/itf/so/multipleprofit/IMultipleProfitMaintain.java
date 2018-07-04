package nc.itf.so.multipleprofit;

import com.ufida.dataset.IContext;

import nc.vo.pub.BusinessException;

/**
 * �ۺ�ë��������ѯ�ӿ�
 * 
 * @since 6.3
 * @version 2012-10-18 14:13:10
 * @author zhangkai4
 */
public interface IMultipleProfitMaintain {

  /**
   * ���ɲ�ѯ���ۺ�ë����������sql���
   * 
   * @param context ������
   * @return String ��ѯ��ʱ���SQL���
   * @throws BusinessException
   */
  public String queryMultipleProfit(IContext context) throws BusinessException;
}
