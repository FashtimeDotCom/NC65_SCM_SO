package nc.itf.so.m38;

import nc.vo.so.m38.entity.PreOrderHVO;

import nc.vo.pub.BusinessException;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * Ԥ�����������ܲ���
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 *  <li>Ԥ���������رղ�����
 *  <li>Ԥ���������򿪲�����
 *  <li>Ԥ�����йرղ�����
 *  <li>Ԥ�����д򿪲�����
 *  <li>Ԥ�����۸���ɡ�
 *  <li>Ԥ�����޶���
 * </ul> 
 *
 * <p>
 * @since 6.0
 * @author ��־ΰ
 * @time 2010-04-09 ����02:43:18
 */
public interface IPreOrderAssitFunc {

  /**
   * ��������������Ԥ���������رղ���
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] closePreOrder(PreOrderVO[] bills) throws BusinessException;  

  /**
   * ��������������Ԥ�����йرղ���
   * 
   * @param bill ����VO
   * @param rows ѡ����
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] closePreOrderRows(PreOrderVO bill, int[] rows)
      throws BusinessException;

  /**
   * ��������������Ԥ���������򿪲���
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] openPreOrder(PreOrderVO[] bills) throws BusinessException;

  /**
   * ��������������Ԥ�����д򿪲���
   * 
   * @param bill ����VO
   * @param rows ѡ����
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] openPreOrderRows(PreOrderVO bill, int[] rows)
      throws BusinessException;

}
