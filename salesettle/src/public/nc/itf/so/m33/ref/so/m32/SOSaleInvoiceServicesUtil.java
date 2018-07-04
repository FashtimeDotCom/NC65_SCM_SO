package nc.itf.so.m33.ref.so.m32;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m32.credit.ISaleInvoiceForCredit;
import nc.pubitf.so.m32.so.m33.IRewrite32For33;
import nc.pubitf.so.m32.so.m33.RewritePara32For33OnVerify;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SOSaleInvoiceServicesUtil {

  /**
   * ���ݷ�ƱID��ѯ�������ں��������
   * 
   * @param bids ���۷�Ʊ��id����
   * @return Map<String,UFDate[]> --- <���۷�Ʊ��id,UFDate[0]:�������� UFDate[1]:�������>
   * @throws BusinessException
   */
  public static Map<String, UFDate[]> getBusiDateBy32Bids(String[] bids)
      throws BusinessException {
    ISaleInvoiceForCredit bo =
        NCLocator.getInstance().lookup(ISaleInvoiceForCredit.class);
    return bo.getBusiDateBy32Bids(bids);
  }

  /**
   * ���ݳ��ⵥID��ѯ�������ں��������
   * 
   * @param bids ���۳��ⵥ��id����
   * @return
   * @throws BusinessException
   */
  public static Map<String, UFDate[]> getBusiDateBy4CBids(String[] bids)
      throws BusinessException {
    ISaleInvoiceForCredit bo =
        NCLocator.getInstance().lookup(ISaleInvoiceForCredit.class);
    return bo.getBusiDateBy4CBids(bids);
  }

  /**
   * ��д���۷�Ʊ���ۼƲ���������
   * 
   * @param paras
   */
  public static void reWritePaymnyOnVerfy(RewritePara32For33OnVerify[] paras) {
    IRewrite32For33 bo = NCLocator.getInstance().lookup(IRewrite32For33.class);
    try {
      bo.reWritePaymnyOnVerfy(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
