package nc.pubitf.so.m30.pu.m20;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * ���۶����ṩ���빺������ӿ�
 * 
 * @since 6.0
 * @version 2011-5-11 ����02:59:33
 * @author ��־ΰ
 */
public interface ISaleOrderFor20 {

  /**
   * ��ѯ���۶����������͵�ֱ�������Ƿ�ֱ�˲ɹ�����
   * 
   * @param pk_group ����
   * @param vtrantypecodes ���۶�����������[]
   * @return Map<String, UFBoolean> Map<���۶�����������,�Ƿ�ֱ�˲ɹ�>
   */
  Map<String, UFBoolean> queryIsDirectPOType(String pk_group,
      String[] vtrantypecodes) throws BusinessException;
}
