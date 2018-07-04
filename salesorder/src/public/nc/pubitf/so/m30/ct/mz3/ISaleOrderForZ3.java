package nc.pubitf.so.m30.ct.mz3;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * ���۶����ṩ�����ۺ�ͬ�ӿڷ���
 * 
 * @since 6.0
 * @version 2011-1-21 ����10:09:36
 * @author ��־ΰ
 */
public interface ISaleOrderForZ3 {

  /**
   * ���ݺ�ͬhids��ѯ��������۶�������Ӧ�յ���ԭ�����
   * 
   * @param cthids ��ͬhids
   * @return Map<��ͬhid,ԭ�����ϼ�>
   * @throws BusinessException
   */
  Map<String, UFDouble> queryOrigCurrencyBalance(String[] cthids)
      throws BusinessException;

  /**
   * ��ѯ��ͬ�����Ƿ��������۶���
   * 
   * @param cthids ��ͬhid
   * @return Map<String, UFBoolean> key:��ͬHid value���Ƿ������ε���,��true;��false
   * @throws BusinessException
   */
  Map<String, UFBoolean> isExistNextOrder(String[] cthids)
      throws BusinessException;
}
