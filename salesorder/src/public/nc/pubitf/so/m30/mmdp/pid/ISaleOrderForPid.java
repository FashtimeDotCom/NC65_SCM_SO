package nc.pubitf.so.m30.mmdp.pid;

import java.util.List;

import nc.vo.pub.BusinessException;

/**
 * ���۶����ṩ���ƻ���������Ľӿ�
 * 
 * @since 6.0
 * @version 2011-12-5 ����03:21:36
 * @author ô��
 */
public interface ISaleOrderForPid {

  /**
   * ���۶�����������ѯ�ƻ������ڽ��ڶ�Ӧ�ƻ���������ʱ��Σ���Ӧ����+�������ԣ������������֯ = ��������
   * �������������۶���������֮��
   * 
   * @param parammvo
   * @return
   * @throws BusinessException
   */
  List<ResultVO> queryOrderNnum(ParaMMVO parammvo) throws BusinessException;
}
