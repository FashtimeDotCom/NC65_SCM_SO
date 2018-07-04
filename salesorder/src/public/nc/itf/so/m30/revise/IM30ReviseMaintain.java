package nc.itf.so.m30.revise;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * ���۶����޶�ά������
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>�޶�����
 * <li>�޶���ʷ��ѯ
 * <li>...
 * </ul>
 * 
 * <p>
 * 
 * @author ��־ΰ
 * 
 */
public interface IM30ReviseMaintain {

  /**
   * �޶�����
   * 
   * @param bills ���۶���
   * @return SaleOrderVO[] ���۶���
   * @throws BusinessException
   */
  SaleOrderVO[] reviseSave(SaleOrderVO[] bills) throws BusinessException;

  // �������۶����޶����淽�� add by wangshu6 for ���۶����޶�֧��������
  /**
   * �޶�����
   * 
   * @param bills ���۶����޶�vo
   * @return SaleOrderVO[] ���۶����޶�vo
   * @throws BusinessException
   */
  SaleOrderHistoryVO[] reviseOrderHisVOSave(SaleOrderHistoryVO[] bills)
      throws BusinessException;

  /**
   * �޶���ʷ��ѯ(���ؽ�����汾��������)
   * 
   * @param hid ���۶���HID
   * @return SaleOrderVO[]
   * @throws BusinessException
   */
  SaleOrderVO[] queryReviseHistory(String hid) throws BusinessException;
}
