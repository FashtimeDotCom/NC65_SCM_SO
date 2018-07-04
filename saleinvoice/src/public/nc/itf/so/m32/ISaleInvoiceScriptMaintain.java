package nc.itf.so.m32;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * ���۷�Ʊ���� ����ҵ����־���õĽӿ�
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>�������޸ġ�ɾ��������
 * </ul>
 * 
 * <p>
 * 
 * @author buxh
 * 
 */

public interface ISaleInvoiceScriptMaintain {

  /**
   * ���۷�Ʊ�޸ı��湦�ܡ�
   * 
   * @param vos
   * @param userObj
   * @param originBills
   * @return ���º��vo
   * @throws BusinessException
   */
  SaleInvoiceVO[] saleInvoiceUpdate(SaleInvoiceVO[] vos, PfUserObject userObj,
      SaleInvoiceVO[] originBills) throws BusinessException;

  /**
   * ���۶����������湦�ܡ�
   * 
   * @param vos
   * @param userObj
   * @return ������vo
   * @throws BusinessException
   */
  SaleInvoiceVO[] saleInvoiceInsert(SaleInvoiceVO[] vos, PfUserObject userObj)
      throws BusinessException;

  /**
   * ���۷�Ʊɾ������
   * 
   * @param vos
   * @throws BusinessException
   */
  void saleInvoiceDelete(SaleInvoiceVO[] vos) throws BusinessException;

}
