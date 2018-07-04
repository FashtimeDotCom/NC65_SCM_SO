package nc.pubitf.so.m30.mm.mmpac;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ���۶����ṩ�������������ӿڷ���
 * 
 * @since 6.0
 * @version 2011-5-6 ����09:25:06
 * @author ��־ΰ
 */
public interface ISaleOrderForMMPac {
  /**
   * ����FromWhere����ѯ���۶�����ͼVO
   * 
   * @param fromwheresql
   * @return SaleOrderViewVO[] ���۶�����ͼVO[]
   * @throws BusinessException
   */
  SaleOrderViewVO[] querySaleOrderViews(String fromwheresql)
      throws BusinessException;

}
