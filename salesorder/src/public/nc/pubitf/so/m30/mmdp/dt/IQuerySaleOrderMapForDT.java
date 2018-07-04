package nc.pubitf.so.m30.mmdp.dt;

import nc.pubitf.so.m30.mmdp.dt.mapvo.SaleOrderDTDemandMapVO;
import nc.vo.pub.BusinessException;

/**
 * ������ٲ�ѯ����-���۶����ֶ�ӳ��
 * 
 * ��������
 * a) δɾ��
 * b) �Ǻ��ֵ���
 * 
 * Լ�����ֶ�ӳ������������������ñ�����
 * 
 * @since 6.3
 * @version 2012-11-6 ����07:57:24
 * @author ������
 */
public interface IQuerySaleOrderMapForDT {
  /**
   * 
   * ������ٲ�ѯ����-���۶����ֶ�ӳ��
   * 
   * @return SaleOrderDTDemandMapVO
   * @throws BusinessException
   */
  SaleOrderDTDemandMapVO getDTDemandMapVO() throws BusinessException;
}
