package nc.itf.so.m33.maintain.m4c;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public interface ISaleOutSettleMaintain {

  /**
   * ����Գ�
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualOutRush(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos)
      throws BusinessException;

  /**
   * �����������������۳��ⵥ�ֹ�����
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return ���ؽ������Ȼ�����ٴν���ļ�¼
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-7-1 ����01:53:10
   */
  SquareOutViewVO[] manualSquare(SquareOutViewVO[] vos)
      throws BusinessException;

  /**
   * ȡ������Գ�
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualUnOutRush(SquareOutViewVO[] vos) throws BusinessException;

  /**
   * ȡ���ֹ�����
   * 
   * @param vos
   * @throws BusinessException
   */
  SquareOutViewVO[] manualUnSquare(SquareOutViewVO[] vos)
      throws BusinessException;

  /**
   * ��ѯ���������Ľ�������
   * 
   * @param strWhere
   * @return
   * @throws BusinessException
   */
  SquareOutViewVO[] querySquareOutFor4CManualSquare(IQueryScheme queryScheme)
      throws BusinessException;
}
