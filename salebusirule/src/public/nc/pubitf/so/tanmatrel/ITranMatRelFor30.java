package nc.pubitf.so.tanmatrel;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * �������������Ϲ�ϵҵ�������
 * 
 * @since 6.0
 * @version 2011-4-19 ����08:32:20
 * @author ף����
 */
public interface ITranMatRelFor30 {
  /**
   * �������������Ϲ�ϵҵ�������
   * 
   * @param paravos
   * @return
   * @throws BusinessException
   */
  UFBoolean checkTranMatRel(TranMatRelParaVO[] paravos)
      throws BusinessException;
}
