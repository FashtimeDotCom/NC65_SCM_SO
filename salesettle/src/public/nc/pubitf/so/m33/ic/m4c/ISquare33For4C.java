package nc.pubitf.so.m33.ic.m4c;

import java.util.Map;

import nc.vo.ic.m4c.entity.SaleOutVO;
import nc.vo.pub.BusinessException;

/**
 * ���۽����ṩ�����۳��ⵥ����
 * 
 * 1.���۳��ⵥ��ʽ�������۽��㵥
 * 
 * 2.���۳��ⵥȡ������
 * 
 * @author zhangcheng
 * 
 */
public interface ISquare33For4C {

  /**
   * ���۳��ⵥȡ��ǩ��ʱ����ȡ������
   * 
   * @param saleOutVOs
   * @throws BusinessException
   */
  void cancelSquareSrv(SaleOutVO[] saleOutVOs) throws BusinessException;

  /**
   * ���۳��ⵥǩ��ʱ�����Զ���ʽ�������۽��㵥
   * 
   * @param saleOutVOs
   * @throws BusinessException
   */
  void pushSquareSrv(SaleOutVO[] saleOutVOs) throws BusinessException;

  /**
   * ������������������Գ�
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param blueOutVO -- ���ֳ��ⵥ
   * @param redOutVOs -- ���ֳ��ⵥ����
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-7-20 ����07:45:44
   */
  void squareOutRush(SaleOutVO blueOutVO, SaleOutVO[] redOutVOs)
      throws BusinessException;

  /**
   * ���㵥Ϊ���۳��ⵥ�ṩ�Ĳ�ѯ���ⵥ�н��㵥��ϸ����
   * 
   * @param outhids ���ⵥ��ͷID����
   * @param outbids ���ⵥ����ID����
   * @return Map<String,String> Key:���㵥��ϸ��ID Value:��Ӧ���ⵥ����ID
   * @throws BusinessException
   * 
   * @author ������
   * @time 2012-9-20 ����07:45:44
   */
  Map<String, String> queryOutSquareDetail(String[] outhids, String[] outbids)
      throws BusinessException;
}
