package nc.itf.so.outprifit;

import com.ufida.dataset.IContext;

import nc.vo.pub.BusinessException;

/**
 * ���۳���ë�������ӿ�
 * 
 * @since 6.3
 * @version 2012-08-28 09:57:56
 * @author ������
 */
public interface IOutProfitMaintain {

  /**
   * 
   * @param context ������
   * @return String ��ѯ��ʱ���SQL���
   * @throws BusinessException
   */
  public String queryOutPrifit(IContext context) throws BusinessException;
}
