package nc.itf.so.invoicesummary;

import nc.vo.pub.BusinessException;

import com.ufida.dataset.IContext;

/**
 * ���۷�Ʊִ�л��ܽӿ�
 * 
 * @since 6.3
 * @version 2012-9-23 ����10:16:40
 * @author ������
 */
public interface IInvSummaryMaintain {
  /**
   * 
   * @param context ������
   * @return String ��ѯ��ʱ���SQL���
   * @throws BusinessException
   */
  public String queryInvSummary(IContext context) throws BusinessException;
}
