package nc.pubitf.so.m33.ic.m4453;

import nc.vo.ic.m4453.entity.WastageVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>���۽�������;���ṩ�Ľӿ�</b>
 * 
 * <ul>
 * <li>;��ǩ��ʱ����soSquare
 * <li>;��ȡ��ǩ��ʱ����cancelSoSquare
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-8-25 ����09:36:23
 */
public interface ISquareAcionFor4453 {

  /**
   * ��������������;��ȡ��ǩ�ֵ�ʱ����ã�ȡ�����۽���
   * <p>
   * <b>����˵��</b>
   * 
   * @param wasvos
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-8-31 ����01:39:08
   */
  void cancelSoSquare(WastageVO[] wasvos) throws BusinessException;

  /**
   * ��������������;��ǩ�ֵ�ʱ����ã��������۽���
   * <p>
   * <b>����˵��</b>
   * 
   * @param wasvos
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-8-31 ����01:39:08
   */
  void soSquare(WastageVO[] wasvos) throws BusinessException;

}
