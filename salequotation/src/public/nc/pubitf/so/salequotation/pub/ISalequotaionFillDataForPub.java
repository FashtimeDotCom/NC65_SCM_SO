package nc.pubitf.so.salequotation.pub;

import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;

/**
 * ���۵������ģ�����ɱ��۵�ʱ�ṩ�Ĳ�ȫ���ݽӿ�
 * 
 * @since 6.31
 * @version 2013-11-20 09:52:42
 * @author liujingn
 */
public interface ISalequotaionFillDataForPub {

  /**
   * �Ա��۵�����ҵ��������ݽ�����������
   * �ջ���������������˰����Ϣ���������͡�����ó�ס�˰��Ϣ
   * 
   * @param salequotationvos ���۵�VO
   * 
   * @return ���۵�VO
   * @throws BusinessException
   */
  AggSalequotationHVO[] getFillSalequotationVO(
      AggSalequotationHVO[] salequotationvos) throws BusinessException;

  /**
   * ���㱨�۵������������۽�ͨ��ָ��editkey�������������۽���㷨
   * 
   * @param salequotationvos ���۵�VO
   * 
   * @param editkey �������ݽ���㷨�ֶ�
   * 
   * @throws BusinessException
   */
  void calSalequotationNumPriceMny(AggSalequotationHVO[] salequotationvos,
      String editkey) throws BusinessException;

}
