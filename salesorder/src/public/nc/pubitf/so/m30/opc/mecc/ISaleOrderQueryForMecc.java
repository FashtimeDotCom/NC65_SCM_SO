package nc.pubitf.so.m30.opc.mecc;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ���۶����ṩ������ͳһ�������ĵĽӿ�
 * �ӿڱ����ó�����
 * �������ۼ��ɲ���ʱ�ṩ�û����Ż���ASN��ѯʱ,��������Ĳ�ѯ������
 * ���ҳ�����������Ԥ������Ż���Ԥ�����ļ��ϣ��ٲ������Ӧ�����۶����򼯺�,
 * ��������������۶�����Ӧ�ĳ��ⵥ��Ϣ��
 * 
 * @since 6.0
 * @version 2012-2-13 ����03:41:13
 * @author ����
 */

public interface ISaleOrderQueryForMecc {

  /**
   * ���ݶ���ͳһ��������Ԥ����ID����ID��ȡ�������۶�����Ϣ
   * ע�⣺����Ԥ����ͷID����ID��ֵ���� �ظ���Ҳ����Ϊ�ա�
   * 
   * @param messids ����ͳһ��������Ԥ����id
   * @param messbids ����ͳһ��������Ԥ��������id
   * @param fieldnames ��ѯ���ֶ�
   * @return
   * @throws BusinessException
   */
  SaleOrderBVO[] query(String[] messids, String[] messbids, String[] fieldnames)
      throws BusinessException;

  /**
   * ������������۶�����Ų�ѯ���۶�����Ϣ
   * 
   * @param saleorerbids ���۶�����ID����
   * @param fieldnames ������ѯ���ֶ�����
   * @return ���۶���ViewVO����
   * @throws BusinessException
   * @author ������
   */
  SaleOrderViewVO[] querySaleOrderViewVO(String[] saleorerbids,
      String[] fieldnames) throws BusinessException;

}
