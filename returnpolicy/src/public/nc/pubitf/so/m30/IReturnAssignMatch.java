package nc.pubitf.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * �����˻����߼��ӿ�
 * 
 * @since 6.0
 * @version 2011-4-14 ����01:36:48
 * @author ף����
 */
public interface IReturnAssignMatch {
  /**
   * so���˻��������
   * @param matchparas
   * @return
   * @throws BusinessException
   */
  Map<String, String> matchReturnAssign(ReturnAssignMatchVO[] matchvos)
      throws BusinessException;
  /**
   * ƥ���˻�����
   * 
   * @param matchvos
   * @return
   * @throws BusinessException
   */
  Map<String,String> matchReturnPolicy(ReturnAssignMatchVO[] matchvos)
      throws BusinessException;
}
