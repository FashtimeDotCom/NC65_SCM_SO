package nc.pubitf.so.m30.mmpps.planbusi;

import nc.pubitf.so.m30.mmpps.planbusi.mapvo.SaleOrderSADemandMapVO;
import nc.vo.pub.BusinessException;

/**
 * ����Ӱ�������ѯ����-���۶����ֶ�ӳ��
 * 
 * ��������
 * a) δɾ��
 * b) �Ǻ��ֵ���
 * 
 * Լ�����ֶ�ӳ������������������ñ�����
 * 
 * @since 6.3
 * @version 2012-11-6 ����08:00:59
 * @author ������
 */
public interface IQuerySaleOrderMapForSA {
  /**
   * 
   * ��ȡ����Ӱ�������ѯ����-���۶����ֶ�ӳ��
   * 
   * @return SaleOrderSADemandMapVO
   * @throws BusinessException
   */
  SaleOrderSADemandMapVO getSADemandMapVO() throws BusinessException;
}
