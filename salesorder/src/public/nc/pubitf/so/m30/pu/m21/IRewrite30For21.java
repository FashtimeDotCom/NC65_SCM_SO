package nc.pubitf.so.m30.pu.m21;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * �ɹ�����Эͬ���۶�����д�ӿ�
 * 
 * @since 6.0
 * @version 2011-3-18 ����04:51:24
 * @author ף����
 */
public interface IRewrite30For21 {
  /**
   * ��д�ɹ��������ݺ�
   * 
   * @param codeMap
   * @throws BusinessException
   */
  void rewriteBillCode(Map<String, String> codeMap) throws BusinessException;

  /**
   * Эͬ�������ɵĲɹ�������ɾ��ʱ�������۶���
   * 
   * @param ids
   * @throws BusinessException
   */
  void renovate30For21Delete(String[] ids) throws BusinessException;
}
