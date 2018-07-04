package nc.itf.so.m33.maintain.m4c;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public interface ISaleOutETMaintain {
  /**
   * �ֹ��ݹ�
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualEstimate(SquareOutViewVO[] vos) throws BusinessException;

  /**
   * ȡ���ֹ��ݹ�
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualUnEstimate(SquareOutViewVO[] vos) throws BusinessException;

  /**
   * ��ѯ�����������ݹ�Ӧ������
   * 
   * @param strWhere
   * @return
   * @throws BusinessException
   */
  SquareOutViewVO[] querySquareOutFor4CManualET(IQueryScheme queryScheme)
      throws BusinessException;
}
