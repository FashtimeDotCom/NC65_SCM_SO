package nc.itf.so.m33.ref.ic.m4c;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.pubitf.ic.m4c.m33.IRewrite4CFor33;
import nc.pubitf.ic.m4c.m33.IRewrite4CPriceFor33;
import nc.pubitf.ic.m4c.m33.ISaleOutQueryFor33;
import nc.pubitf.ic.m4c.m33.Parameter4CFor33;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class ICM4CServiceUtil {

  private ICM4CServiceUtil() {
    super();
  }

  /**
   * ������ outbids ���۳��ⵥ����id����
   * ����ֵ�� ��Դ�ڴ����۳��ⵥ���˻ص����۳��ⵥ���������۳��ⵥֱ���˻أ���;���˻صģ�ȫ��ǩ�����
   * || �������˻����۳��ⵥ -------- true
   * ���˻����۳��ⵥ����û��ȫ��ǩ��--false
   * 
   * @param outbids
   * @return
   */
  public static boolean checkSaleOutIsSignByOutBids(String[] outbids) {
    ISaleOutQueryFor33 ioutSvr =
        NCLocator.getInstance().lookup(ISaleOutQueryFor33.class);
    try {
      return ioutSvr.checkSaleOutIsSignByOutBids(outbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return true;
  }

  /**
   * �������۳��ⵥ��id��ѯ���۳��ⵥ�������ҵ�����ڡ�ǩ������
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  public static Map<String, UFDate[]> queryBizSignDateByBids(String[] bids)
      throws BusinessException {
    I4CQueryPubService bo =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    return bo.queryBizSignDateByBids(bids);
  }

  /**
   * �������۷�Ʊ��id��ѯ���۳��ⵥ�������ҵ�����ڡ�ǩ������
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  public static Map<String, UFDate[]> queryBizSignDateByInvoiceBids(
      String[] bids) throws BusinessException {
    I4CQueryPubService bo =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    return bo.queryBizSignDateByInvoiceBids(bids);
  }

  /**
   * �������۳��ⵥ������Ϣ
   * 
   * @param bids
   * @param queryItems
   * @return
   * @throws BusinessException
   */
  public static Map<String, SaleOutBodyVO> queryBodyItems(String[] bids,
      String[] queryItems) {
    I4CQueryPubService ioutSvr =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    try {
      return ioutSvr.queryBodyItems(bids, queryItems);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return new HashMap<String, SaleOutBodyVO>();
  }

  public static String[] queryRetBidsBySource(String[] bids)
      throws BusinessException {
    ISaleOutQueryFor33 ioutSvr =
        NCLocator.getInstance().lookup(ISaleOutQueryFor33.class);
    return ioutSvr.queryRedBidsBySource(bids);
  }

  /**
   * �������۳��ⵥ������id���飬ȡ�����ۼƿ�Ʊ����,�ۼ�;������,�ۼƳ���Գ�����,�ۼ�ǩ������
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  public static SaleOutBodyVO[] queryTotalNumBy4CBids(String[] bids)
      throws BusinessException {
    SaleOutBodyVO[] bvos = null;
    ISaleOutQueryFor33 bo =
        NCLocator.getInstance().lookup(ISaleOutQueryFor33.class);
    bvos = bo.query4CNumFor33(bids);
    return bvos;
  }

  /**
   * �������۳��ⵥ����id��ѯ���۳��ⵥ��ͼvo
   * 
   * @param outbids
   * @return
   * @throws BusinessException
   */
  public static SaleOutViewVO[] queryViewVOsByBids(String[] outbids)
      throws BusinessException {
    ISaleOutQueryFor33 iqry =
        NCLocator.getInstance().lookup(ISaleOutQueryFor33.class);
    return iqry.queryViewVOsByBids(outbids);
  }

  /**
   * ��д���۳��ⵥԭ�Һ�˰���ۣ�ͬʱ��ԭ�Һ�˰���ۼ������۳��ⵥ�е��۽����Ϣ����󱣴�
   * 
   * @param paraMap <���۳��ⵥ��ID,ԭ�Һ�˰����>
   */
  public static void renovatePrice(Map<String, UFDouble> paraMap) {
    IRewrite4CPriceFor33 ioutSvr =
        NCLocator.getInstance().lookup(IRewrite4CPriceFor33.class);
    try {
      ioutSvr.renovatePriceFor33(paraMap);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��д���۳��ⵥ�ۼƳ���Գ�����
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  public static void rewrite4CRushFor33(Parameter4CFor33[] paras)
      throws BusinessException {
    IRewrite4CFor33 bo = NCLocator.getInstance().lookup(IRewrite4CFor33.class);
    bo.rewrite4CRushNumFor33(paras);
  }

}
