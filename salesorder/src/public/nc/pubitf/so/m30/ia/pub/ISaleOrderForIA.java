package nc.pubitf.so.m30.ia.pub;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * ���۶����ṩ���������ӿ�
 * 
 * @since 6.0
 * @version 2010-11-6 ����03:56:31
 * @author ��־ΰ
 */
public interface ISaleOrderForIA {

  /**
   * ���ֱ�����ͱ���Ƿ�ֱ�˲ɹ�
   * 
   * @param ctrantypeids ��������IDs
   * @return Map<String, UFBoolean> Map<ctrantypeid, �Ƿ�ֱ�˲ɹ�>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException;
}
