package nc.pubitf.so.m30.so.m38;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOParameterVO;

/**
 * ���۶���������������ʽ���湫������ӿ�
 * 
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-6-30 ����11:37:24
 */
public interface IPushSave30For38Arrange {

  /**
   * Ԥ�������ţ��������۶�����
   * 
   * @param bills ������ĵ���VO���顣���θ���VO���ա��ֵ�����Ҫ�������۶�����ʱ�����
   * @return SaleOrderVO[] ���۶���VO[]
   * @throws BusinessException
   */
  SaleOrderVO[] pushSave30For38Arrange(SOParameterVO vo)
      throws BusinessException;
}
