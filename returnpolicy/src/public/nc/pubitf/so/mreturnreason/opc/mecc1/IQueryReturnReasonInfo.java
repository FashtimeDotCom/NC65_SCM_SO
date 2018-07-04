package nc.pubitf.so.mreturnreason.opc.mecc1;

import nc.vo.pub.BusinessException;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;

/**
 * �˻�ԭ��Ϊ������������Ԥ�����ṩ��ERPϵͳ�˻�ԭ����Ϣ��ѯ�ӿ�
 * 
 * ʹ�ó����������˻�����¼���˻�ԭ��ʱ����ѯERPϵͳ�ж�����˻�ԭ��
 * 
 * @since 6.0
 * @version 2011-12-28 ����02:53:05
 * @author zhangcheng
 */

public interface IQueryReturnReasonInfo {

  /**
   * ��ѯ���������е��˻�ԭ����Ϣ
   * 
   * ����ֻ�鼯�ż����˻�ԭ�򣬲���ҵ��Ԫ�����˻�ԭ��
   * 
   * @param pk_group ����PK���ǿգ�
   * @return �˻�ԭ����ϢReturnReasonInfoVO
   * @throws BusinessException
   */
  ReturnReasonInfoVO[] queryGroupReturnReason(String[] pk_group)
      throws BusinessException;

  /**
   * ���ݵ������������������֯�����۹���ģ���ѯ��������֯�ɼ����˻�ԭ�򵵰�
   * 
   * @param ������֯ID����
   * @return �˻�ԭ����ϢReturnReasonVO
   * @throws BusinessException
   * @author ������
   */
  ReturnReasonVO[] queryReturnReasonByPk_orgs(String[] pk_orgs)
      throws BusinessException;

}
