package nc.pubitf.so.m4331.dm.m4804;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * ���䵥��д������
 * 
 * @since 6.0
 * @version 2011-3-17 ����01:51:26
 * @author ף����
 */
public interface IRewrite4331For4804 {
  /**
   * ���䵥�ֶ��رա��򿪷���������״̬
   * 
   * @param stateMap key����������id UFBoolean ����״̬
   * @throws BusinessException
   */
  void renovateState(String[] bids, UFBoolean state) throws BusinessException;

  /**
   * ���䵥���� �޸� ɾ�� ��д�������ۼ���������
   * 
   * @param paras
   * @throws BusinessException
   */
  void rewriteTransnum(RewritePara4331For4804[] paras) throws BusinessException;
}
