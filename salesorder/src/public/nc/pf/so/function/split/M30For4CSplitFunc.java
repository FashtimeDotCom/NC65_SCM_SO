package nc.pf.so.function.split;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.split.ISplitBillByStoreAdmin;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * ���۶��������ⵥ �ṩ�ķֵ�����
 * 
 * @since 6.0
 * @version 2011-7-12 ����04:04:01
 * @author fengjb
 */
public class M30For4CSplitFunc {

  /**
   * ���տ��Ա�ֵ�
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public List<String> splitByStoreAdmin(AggregatedValueObject vo)
      throws BusinessException {

    ISplitBillByStoreAdmin service =
        NCLocator.getInstance().lookup(ISplitBillByStoreAdmin.class);
    return service.splitByStoreAdmin(vo, new String[] {
      SaleOrderBVO.CSENDSTOCKORGID, SaleOrderBVO.CSENDSTORDOCID,
      SaleOrderBVO.CMATERIALID
    });
  }
}
