package nc.pubitf.so.m30arrange;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * ����/�����ṩ�����ε��ݷ���ӿ�
 * 
 * @since 6.0
 * @version 2011-8-9 ����10:54:10
 * @author ��־ΰ
 */
public interface IM30ArrangeForPub {
  /**
   * �������۶���IDs��ѯ���۶���VOs
   * 
   * @param ids ���۶���ids
   * @return SaleOrderViewVO[]
   * @throws BusinessException
   */
  Map<String, Object[]> querySaleOrderVOs(String[] ids)
      throws BusinessException;
}
