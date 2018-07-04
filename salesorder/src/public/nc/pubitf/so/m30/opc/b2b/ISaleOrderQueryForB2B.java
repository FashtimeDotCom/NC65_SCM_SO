package nc.pubitf.so.m30.opc.b2b;

import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * ���۶���ΪB2BԤ�����ṩ��ѯ�ӿ�
 * 
 * @since 6.5
 * @version 2014-04-02 14:44:54
 * @author zhangyfr
 */
public interface ISaleOrderQueryForB2B {

  /**
   * �������۶�����Դ��id����ѯ��B2Bָ���Ľ����������B2BԤ������д�����۸��
   * 
   * @param ids ���۶�����Դ��ID����
   * @return map<��ԴID����ԴID��Ӧ��list>
   * @throws BusinessException
   */
  public Map<String, List<SaleOrderForB2bResult>> query(String[] ids)
      throws BusinessException;

}
