package nc.pubitf.so.m30.ic.m4453;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * ���۶����ṩ��;�𵥽ӿڷ���
 * 
 * @since 6.0
 * @version 2011-1-17 ����09:29:44
 * @author ��־ΰ
 */
public interface ISaleOrderFor4453 {

  /**
   * �������۶�������ID��ѯ������֯
   * 
   * @param hids ���۶���HIDs
   * @return Map<String, String> Map<���۶���HID, ������֯ID>
   * @throws BusinessException
   */
  Map<String, String> querySaleOrgsByIDs(String[] hids)
      throws BusinessException;

}
