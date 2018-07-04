package nc.itf.so.m33.ref.so.m30;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m30.balend.BalEndPara;
import nc.pubitf.so.m30.balend.BalOpenPara;
import nc.pubitf.so.m30.balend.ISaleOrderBalEndSrv;
import nc.pubitf.so.m30.ic.m4c.ISaleOrderFor4C;
import nc.pubitf.so.m30.so.m33.IRewrite30For33;
import nc.pubitf.so.m30.so.m33.ISaleOrderFor33;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m32.so.m33.IRewrite32For33;
import nc.pubitf.so.m32.so.m33.RewritePara32For33;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class SOSaleOrderServicesUtil {

  private SOSaleOrderServicesUtil() {
    super();
  }

  /**
   * �������۶���bid��ѯ���۶�����������
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  public static Map<String, UFDate> get30BusiDateBy30Bids(String[] bids)
      throws BusinessException {
    ISaleOrderFor33 bo = NCLocator.getInstance().lookup(ISaleOrderFor33.class);
    return bo.get30BusiDateBy30Bids(bids);
  }

  /**
   * �������۶���bid��ѯ���ۺ�ͬ��������
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  public static Map<String, UFDate> getZ3BusiDateBy30Bids(String[] bids)
      throws BusinessException {
    ISaleOrderFor33 bo = NCLocator.getInstance().lookup(ISaleOrderFor33.class);
    return bo.getZ3BusiDateBy30Bids(bids);
  }

  /**
   * ��������رյ���
   * 
   * @param para
   * @throws BusinessException
   */
  public static void processAutoBalEnd(BalEndPara para)
      throws BusinessException {
    ISaleOrderBalEndSrv bo =
        NCLocator.getInstance().lookup(ISaleOrderBalEndSrv.class);
    bo.processAutoBalEnd(para);
  }

  /**
   * ��������򿪵���
   * 
   * @param para
   * @throws BusinessException
   */
  public static void processAutoBalOpen(BalOpenPara para)
      throws BusinessException {
    ISaleOrderBalEndSrv bo =
        NCLocator.getInstance().lookup(ISaleOrderBalEndSrv.class);
    bo.processAutoBalOpen(para);
  }

  /**
   * ����bids��ѯ���۶���ViewVOsָ��ֵ
   * 
   * @param bids ��������id[]
   * @param String[] ��Ҫ��ѯ��ֵ
   * @return SaleOrderViewVO[] ���۶���ViewVO[]
   * @throws BusinessException
   */
  public static SaleOrderViewVO[] querySaleOrderViewVOs(String[] bids,
      String[] names) throws BusinessException {
    ISaleOrderFor4C bo = NCLocator.getInstance().lookup(ISaleOrderFor4C.class);
    return bo.querySaleOrderViewVOs(bids, names);
  }

  public static void rewrite30ARFor33(Rewrite33Para[] paras)
      throws BusinessException {
    IRewrite30For33 bo = NCLocator.getInstance().lookup(IRewrite30For33.class);
    bo.rewrite30ARFor33(paras);
  }

  public static void rewrite30ETFor33(Rewrite33Para[] paras)
      throws BusinessException {
    IRewrite30For33 bo = NCLocator.getInstance().lookup(IRewrite30For33.class);
    bo.rewrite30ETFor33(paras);
  }

  public static void rewrite30IAFor33(Rewrite33Para[] paras)
      throws BusinessException {
    IRewrite30For33 bo = NCLocator.getInstance().lookup(IRewrite30For33.class);
    bo.rewrite30IAFor33(paras);
  }

  public static void rewrite30OutRushFor33(Rewrite33Para[] paras)
      throws BusinessException {
    IRewrite30For33 bo = NCLocator.getInstance().lookup(IRewrite30For33.class);
    bo.rewrite30OutRushFor33(paras);
  }

  /**
   * ��д���۷�Ʊ���ۼƽ�����Ϣ
   * 
   * @param paras
   * @throws BusinessException
   */
  public static void reWriteBalNumMny(RewritePara32For33[] paras)
      throws BusinessException {
    IRewrite32For33 bo = NCLocator.getInstance().lookup(IRewrite32For33.class);
    bo.reWriteBalNumMny(paras);
  }

  /**
   * �Ƿ񶩵��ɱ�����ر�
   */
  UFBoolean[] isCostBalEnd(String[] saleorderbids) throws BusinessException {
    ISaleOrderBalEndSrv bo =
        NCLocator.getInstance().lookup(ISaleOrderBalEndSrv.class);
    return bo.isCostBalEnd(saleorderbids);
  }

  /**
   * �Ƿ񶩵��������ر�
   */
  UFBoolean[] isIncomeBalEnd(String[] saleorderbids) throws BusinessException {
    ISaleOrderBalEndSrv bo =
        NCLocator.getInstance().lookup(ISaleOrderBalEndSrv.class);
    return bo.isIncomeBalEnd(saleorderbids);
  }

}
