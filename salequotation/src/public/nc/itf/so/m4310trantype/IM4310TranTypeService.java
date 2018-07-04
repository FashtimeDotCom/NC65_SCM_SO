package nc.itf.so.m4310trantype;

import nc.vo.pub.BusinessException;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;

public interface IM4310TranTypeService {

  /**
   * ���ҵ��������µ����н�������
   * 
   * @param pkGroup
   *          ����
   * @param pkBilltype
   *          ��������
   * @return
   * @throws BusinessException
   */
  M4310TranTypeVO[] queryAllTranType(String pkGroup, String pkBilltype)
      throws BusinessException;

  /**
   * �����ض��Ľ�������
   * 
   * @param pk_group
   *          ����
   * @param pk_billtypecode
   *          ��������
   * @return
   * @throws BusinessException
   */
  M4310TranTypeVO queryTranType(String pk_group, String pk_billtypecode)
      throws BusinessException;

  /**
   * ���������ض��Ľ�������
   * 
   * @param pk_group
   *          ����
   * @param pk_billtypecode
   *          ������������
   * @return
   * @throws BusinessException
   */
  M4310TranTypeVO[] queryTranType(String pk_group, String[] pk_billtypecode)
      throws BusinessException;
}
