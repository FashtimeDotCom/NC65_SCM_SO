package nc.pubitf.so.m38.mmpps.planbusi;

import nc.pubitf.so.m38.mmpps.planbusi.mapvo.SalePreOrderSADemandMapVO;
import nc.vo.pub.BusinessException;

/**
 * ����Ӱ�������ѯ����-����Ԥ�����ֶ�ӳ��
 * 
 * ��������
 * a) δɾ��
 * b) �Ǻ��ֵ���
 * 
 * Լ�����ֶ�ӳ������������������ñ�����
 * 
 * @since 6.3
 * @version 2012-11-6 ����07:35:24
 * @author ������
 */
public interface IQuerySalePreOrderMapForSA {
  /**
   * 
   * ��ȡ����Ӱ�������ѯ����-����Ԥ�����ֶ�ӳ��
   * 
   * @return SalePreOrderSADemandMapVO
   * @throws BusinessException
   */
  SalePreOrderSADemandMapVO getSADemandMapVO() throws BusinessException;
}
