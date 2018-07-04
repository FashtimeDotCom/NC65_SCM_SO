package nc.bs.so.m32.pf.function;

import java.util.List;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

import nc.pubitf.ic.split.ISplitBillByStoreAdmin;

import nc.bs.framework.common.NCLocator;

/**
 * �ṩ�����ķֵ����������ֿ�ֵ���
 * 
 * @since 6.0
 * @version 2011-7-11 ����01:41:55
 * @author ô��
 */
public class SplitByStoAdminFor4C {

  /**
   * 
   * @param vo
   * @return �ֵ�
   * @throws BusinessException
   */
  public List<String> splitByStoreAdmin(AggregatedValueObject vo)
      throws BusinessException {
    ISplitBillByStoreAdmin service =
        NCLocator.getInstance().lookup(ISplitBillByStoreAdmin.class);
    return service.splitByStoreAdmin(vo, new String[] {
      SaleInvoiceBVO.CSENDSTOCKORGID, SaleInvoiceBVO.CSENDSTORDOCID,
      SaleInvoiceBVO.CMATERIALID
    });
  }

}
