package nc.itf.so.m30.ref.ct.mz3;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ct.saledaily.salegather.ISaleQueryForGatherBill;
import nc.pubitf.ct.saledaily.saleorder.ISaleQueryForSaleOrder;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ��ͬ�ṩ���з��񹤾���
 * 
 * @since 6.0
 * @version 2011-5-4 ����11:43:39
 * @author ��־ΰ
 */
public class CTmZ3ServicesUtil {

  /**
   * ���ݺ�ͬbidsȡ��ͬҵ����ϢVO{���Ͽ��Ʒ�ʽ�����ϻ�������ID������}
   */
  public static Map<String, CtBusinessVO> queryCtBusinessByPks(String[] ctbids)
      throws BusinessException {
    ISaleQueryForSaleOrder service =
        NCLocator.getInstance().lookup(ISaleQueryForSaleOrder.class);
    Map<String, CtBusinessVO> retMap = null;
    try {
      retMap = service.queryCtBusinessByPksFor30(ctbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
    return retMap;
  }

  /**
   * �������ۺ�ͬ��id��ѯ�������ۺ�ͬ�ĵ�������
   * 
   * @param ������String[] bids --- ���ۺ�ͬ��id����
   * @return Map<String,UFDate> --- <���ۺ�ͬ��id,UFDate:���ۺ�ͬ�ĵ�������>
   * @throws BusinessException
   */
  public static Map<String, UFDate> queryValidateDayForGatherBill(String[] bids)
      throws BusinessException {
    ISaleQueryForGatherBill service =
        NCLocator.getInstance().lookup(ISaleQueryForGatherBill.class);
    return service.queryValidateDayForGatherBill(bids);
  }

}
