package nc.itf.so.outsummary;

import nc.vo.pub.BusinessException;

import com.ufida.dataset.IContext;

/**
 * ���۳���ִ�л��ܽӿ�
 * 
 * @since 6.3
 * @version 2012-10-18 ����01:14:50
 * @author ������
 */
public interface IOutSummaryMaintain {
  /**
   * 
   * @param context ������
   * @return String ��ѯ��ʱ���SQL���
   * @throws BusinessException
   */
  public String queryOutSummary(IContext context) throws BusinessException;
}
