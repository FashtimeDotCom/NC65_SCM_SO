package nc.pubitf.so.m38.mmdp.dt;

import nc.pubitf.so.m38.mmdp.dt.mapvo.SalePreOrderDTDemandMapVO;
import nc.vo.pub.BusinessException;

/**
 * ������ٲ�ѯ����-����Ԥ�����ֶ�ӳ��
 * 
 * ��������
 * a) δɾ��
 * b) �Ǻ��ֵ���
 * 
 * Լ�����ֶ�ӳ������������������ñ�����
 * 
 * @since 6.3
 * @version 2012-11-6 ����04:51:52
 * @author ������
 */
public interface IQuerySalePreOrderMapForDT {
  /**
   * 
   * ��ȡ������ٲ�ѯ����-����Ԥ�����ֶ�ӳ��
   * 
   * @return SalePreOrderDTDemandMapVO
   * @throws BusinessException
   */
  SalePreOrderDTDemandMapVO getDTDemandMapVO() throws BusinessException;
}
