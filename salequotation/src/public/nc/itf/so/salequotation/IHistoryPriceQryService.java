package nc.itf.so.salequotation;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.salequotation.entity.FindHistoryPriceParameter;

/**
 * ѯ��ʷ���۷���
 * 
 * @author chenyyb
 * 
 */
public interface IHistoryPriceQryService {

  /**
   * ѯ��ʷ����
   * 
   * @param paraVOs
   *          ѯ�۲���
   * @param tranTypeVO
   *          ��������
   * @return
   * @throws BusinessException
   */
  UFDouble[] findHistoryPrice(FindHistoryPriceParameter[] paraVOs,
      M4310TranTypeVO tranTypeVO) throws BusinessException;
}
