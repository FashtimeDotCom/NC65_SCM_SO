package nc.itf.so.salequotation;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;

public interface ISalequotationService {

  /**
   * ��������
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   */
  Object approve(AggSalequotationHVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * �رյ���
   * 
   * @param aggVO
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] close(AggSalequotationHVO[] aggVOs) throws BusinessException;

  /**
   * �ύ����
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   */
  AggSalequotationHVO[] commit(AggSalequotationHVO[] vos,
      AbstractCompiler2 script) throws BusinessException;

  /**
   * ɾ��
   * 
   * @param object
   * @throws Exception
   */
  void delete(AggSalequotationHVO[] object) throws Exception;

  /**
   * ���ϵ���
   * 
   * @param aggVO
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] invalidate(AggSalequotationHVO[] aggVOs)
      throws Exception;

  /**
   * �򿪵���
   * 
   * @param aggVO
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] open(AggSalequotationHVO[] aggVOs) throws Exception;

  AggSalequotationHVO[] saveBase(AggSalequotationHVO[] aggVOs) throws Exception;

  /**
   * ���󵥾�
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   */
  AggSalequotationHVO[] unApprove(AggSalequotationHVO[] vos,
      AbstractCompiler2 script) throws BusinessException;
}
