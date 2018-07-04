package nc.pubitf.so.m4331.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * �������ṩ�����۶����Ľӿ�
 * 
 * @since 6.0
 * @version 2011-1-26 ����01:55:19
 * @author ף����
 */
public interface IDeliveryFor30 {

  /**
   * �������۶�������id ��ѯ�������Ƿ�����Ԥ�����
   * 
   * @param bids
   * @return �������Ƿ�����Ԥ��
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryReverseFlag(String[] bids)
      throws BusinessException;

  /**
   * ���۶����޶��۸񣬸��·������ļ۸�
   * 
   * @param paraMap keyΪ���۶�������id valueΪ�仯��key�ͱ仯���ֵ
   * @throws BusinessException
   */
  void renovatePrice(Map<String, IDeliveryPriceParaFor30> paraMap)
      throws BusinessException;

  /**
   * ���ݶ���id����ѯ��˷�������˵�����
   * 
   * @param srcBids
   * @return �������������
   * @throws BusinessException
   */
  Map<String, UFDouble> queryAppNum(String[] srcBids) throws BusinessException;

  /**
   * ��д���������������ر�״̬
   * 
   * @param orderids ����id����
   * @param orderbids ��������id����
   * @param isclose �ǹرջ��Ǵ� trueΪ�ر� , flaseΪ��
   * @throws BusinessException
   */
  void rewriteArSettle(SaleOrderViewVO[] viewvos) throws BusinessException;
}
