package nc.pubitf.so.m33.arap;

import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * ���۽����Ӧ�յ��ṩ�Ĳ�ѯ��Ӧ������ص������ڵķ���
 * Ӧ�յ��������۽��㴫�������ո���Э�飬�����Լ�����Ч����
 * ��Ҫ�����ո���Э������Ϲ涨�ĸ��൥�����ڽ��м���
 * ���۽����ṩ�����������ڵĲ�ѯ�������綩�������ⵥ����Ʊ������
 * 
 * @since 6.0
 * @version 2011-6-11 ����09:52:55
 * @author zhangcheng
 */
public interface IQueryAccountDateFromM33 {

  /**
   * �������۽�����ϸID,��ָ�����������ͣ���ѯ��ص�������
   * �˴����ñ�����Դ��ͬ��һ��Ӧ�յ�
   * 
   * @param map <���۽�����ϸID,ָ����������>
   *          String Ӧ�յ���Դ��������
   * @return <���۽�����ϸID,ָ���������͵ľ��嵥������><p>
   *         ע�ⷵ�ص�����ֵ�ʹ������һһ��Ӧ,���û��ȡ����ص�ֵ���ᱨ��<p>
   *         �������List<AccountDateType>�ж������ڡ���Ʊ�������<p>
   *         ����ֵ���Ƕ������ڵ�ֵ�����û��ȡ����Ʊ������ڣ������쳣<p>
   */
  Map<String, UFDate[]> queryAccountDate(
      Map<String, List<AccountDateType>> map, String billType)
      throws BusinessException;

}
