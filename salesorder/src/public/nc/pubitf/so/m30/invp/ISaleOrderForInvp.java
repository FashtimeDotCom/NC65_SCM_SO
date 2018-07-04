package nc.pubitf.so.m30.invp;

import nc.vo.pub.BusinessException;

import nc.pubitf.invp.plan.IReqResultForInvp;

/**
 * ���۶����ṩ���ƻ��ӿ�
 * 
 * @since 6.0
 * @version 2010-12-14 ����01:32:43
 * @author ��־ΰ
 */
public interface ISaleOrderForInvp {

  /**
   * ȡ��ȡ��VO
   * 
   * @param sendStockOrg �����֯
   * @param tempName ��ʱ��pk_material ����OID,dstart ��ʼʱ��,dend ����ʱ�䣩
   * @param needRed �Ƿ�������ֵ���
   * @return ��ѯ����VO
   * @throws BusinessException
   */
  IReqResultForInvp getVO(String sendStockOrg, String tempName, boolean needRed)
      throws BusinessException;

}
