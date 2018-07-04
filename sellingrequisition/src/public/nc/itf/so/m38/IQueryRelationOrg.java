package nc.itf.so.m38;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * ���۶�������Ҫ���ݿͻ���������֯�����ϻ�ȡ���ϵķ��������֯�����������֯ID��Ӧ����֯ID����������ID��Ĭ��������֯��������/�㷨����
 * <ol>
 * <li>���ù��������ȡ���������֯
 * <li>���ù��������ȡ���������֯ID��Ӧ����֯ID����������ID
 * <li>���ù��������ȡĬ��������֯
 * </ol>
 * 
 * @since 6.0
 * @version 2011-7-29 ����15:05:00
 * @author ������
 * @see
 */
public interface IQueryRelationOrg {

  /**
   * ���ݿͻ���������֯�����ϻ�ȡ���ϵķ��������֯�����������֯ID��Ӧ����֯ID����������ID��Ĭ��������֯
   * 
   * @param customerID �ͻ�ID
   * @param saleOrg ������֯
   * @param materialIDS ����ID����
   * @param sendStordocIDS �ֿ����������֯����
   * @return
   *         Map<Key�����ϣ�Value��String[]{���������֯�����������֯ID��Ӧ����֯ID����������ID��Ĭ��������֯��ֱ�˲�}
   *         >
   * @throws BusinessException
   * @see
   */
  Map<String, String[]> querySaleRelationOrg(String customerID, String saleOrg,
      String[] materialIDS) throws BusinessException;

}
