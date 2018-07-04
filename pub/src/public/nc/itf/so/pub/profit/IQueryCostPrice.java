package nc.itf.so.pub.profit;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * ë��������Ҫ������ȡ�ɱ���ͳɱ����ۡ�������/�㷨����
 * <ol>
 * <li>���ݿ����֯�Ͳֿ�������ȡ�ɱ���
 * <li>���ݻ�ȡ�ĳɱ������Ϻ�����������ȡ�ɱ�����
 * </ol>
 * <b>����ʾ����</b>����˵��
 * 
 * <pre>
 * ����Ƭ��
 * </pre>
 * 
 * @since 6.0
 * @version 2011-7-13 ����15:05:00
 * @author ������
 * @see
 */

public interface IQueryCostPrice {

  /**
   * ë��������Ҫ������ȡ�ɱ���ͳɱ�����
   * 
   * @param cstockorgids �����֯ID����
   * @param cstordocids �ֿ�ID����
   * @param cmaterialids ����ID����
   * @param vbatchs ��������
   * @return Map<Key�������֯+�ֿ�+����+���Σ�Value���ɱ�����>
   * @throws BusinessException
   * @see
   */

  Map<String, UFDouble> queryCostPrice(String[] cstockorgids,
      String[] cstordocids, String[] cmaterialids, String[] vbatchs)
      throws BusinessException;
}
