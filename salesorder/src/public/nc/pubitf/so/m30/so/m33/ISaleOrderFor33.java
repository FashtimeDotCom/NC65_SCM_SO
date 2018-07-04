package nc.pubitf.so.m30.so.m33;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * ���۶����ṩ�����۽���ӿڷ���
 * 
 * @since 6.0
 * @version 2011-1-28 ����04:07:37
 * @author ��־ΰ
 */
public interface ISaleOrderFor33 {

  /**
   * �������۶���bid��ѯ���۶�����������
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Map<String, UFDate> get30BusiDateBy30Bids(String[] bids)
      throws BusinessException;

  /**
   * �������۶���bid��ѯ���ۺ�ͬ��������
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Map<String, UFDate> getZ3BusiDateBy30Bids(String[] bids)
      throws BusinessException;

  /**
   * �������۶���bid��ѯ���۶�����ͼVO��Ϣ�������㷢Ʊ
   * 
   * @param bids ���۶���bid[]
   * @return Map<bid, For33SquareInvVO> Map<���۶�������ID, For33SquareInvVO>
   * @throws BusinessException
   */
  Map<String, For33SquareInvVO> query30ViewInfoForSquareInv(String[] bids)
      throws BusinessException;

  /**
   * �������۶���bid��ѯ���۶�����ͼVO��Ϣ�����������
   * 
   * @param bids ���۶���bid[]
   * @return Map<bid, For33SquareOutVO> Map<���۶�������ID, For33SquareOutVO>
   * @throws BusinessException
   */
  Map<String, For33SquareOutVO> query30ViewInfoForSquareOut(String[] bids)
      throws BusinessException;
}
