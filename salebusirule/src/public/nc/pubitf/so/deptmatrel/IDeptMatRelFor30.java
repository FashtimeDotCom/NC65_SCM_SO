package nc.pubitf.so.deptmatrel;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * ���������Ϲ�ϵҵ�������
 * 
 * @since 6.0
 * @version 2011-4-19 ����08:27:47
 * @author ף����
 */
public interface IDeptMatRelFor30 {
  /**
   * ���������Ϲ�ϵҵ�������
   * 
   * @param paravos
   * @return
   * @throws BusinessException
   */
  UFBoolean checkDeptMatRel(DeptMatRelParaVO[] paravos)
      throws BusinessException;
}
