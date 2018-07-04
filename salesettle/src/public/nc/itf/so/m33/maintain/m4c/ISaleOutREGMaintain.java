package nc.itf.so.m33.maintain.m4c;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public interface ISaleOutREGMaintain {
  /**
   * �ֹ����뷢����Ʒ
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualRegister(SquareOutViewVO[] vos) throws BusinessException;

  /**
   * ȡ���ֹ����뷢����Ʒ
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualUnRegister(SquareOutViewVO[] vos) throws BusinessException;

  /**
   * ��ѯ���������ķ�����Ʒ����
   * 
   * @param strWhere
   * @return
   * @throws BusinessException
   */
  SquareOutViewVO[] querySquareOutFor4CManualREG(IQueryScheme queryScheme)
      throws BusinessException;
}
