package nc.pubitf.so.custmatrel;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * ���۶����ͻ����Ϲ�ϵ���
 * 
 * @since 6.0
 * @version 2011-4-19 ����08:09:21
 * @author ף����
 */
public interface ICustMatRelFor30 {
  /**
   * ����Ƿ��������۶����ͻ����Ϲ�ϵ
   * 
   * @param paravos
   * @return
   * @throws BusinessException
   */
  void checkCustMatRel(CustMatRelParaVO[] paravos) throws BusinessException;

  /**
   * ��ÿ������Ĳ�������Ƿ��������۶����ͻ����Ϲ�ϵ����
   * 
   * @param paravos
   * @return
   * @throws BusinessException
   */
  UFBoolean[] getCustMatRelSaleFlag(CustMatRelParaVO[] paravos)
      throws BusinessException;
}
