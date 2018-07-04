package nc.vo.so.m32.util;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.paravo.Info30For32Para;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m35.entity.ArsubInterfaceVO;

import nc.pubitf.so.m30.balend.BalEndPara;
import nc.pubitf.so.m30.balend.ISaleOrderBalEndSrv;
import nc.pubitf.so.m30.pub.ISaleOrderForPub;
import nc.pubitf.so.m30.so.m32.IRewrite30For32;
import nc.pubitf.so.m30.so.m32.ISaleOrderFor32;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.pubitf.so.m33.so.m32.ISquare33For32;
import nc.pubitf.so.m33.so.m32.ISquareAcionFor32;
import nc.pubitf.so.m35.so.m32.IArsubToSaleInvoice;

import nc.bs.framework.common.NCLocator;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * �������۹�����������Ľӿ�
 * 
 * @since 6.0
 * @version 2012-2-8 ����01:47:17
 * @author ô��
 */
public class RemoteFormSOUtil {

  /************ ���㵥�ṩ�Ľӿ� *****************/

  /**
   * �Զ�Ӧ�ս��㶯��
   * 
   * @param voInvoices
   */
  public static void autoSquareIncomeSrv(SaleInvoiceVO[] voInvoices) {
    try {
      ISquareAcionFor32 squaresrv =
          NCLocator.getInstance().lookup(ISquareAcionFor32.class);
      squaresrv.autoSquareIncomeSrv(voInvoices);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * �Զ��ɱ����㶯��
   * 
   * @param voInvoices
   */
  public static void autoSquareCostSrv(SaleInvoiceVO[] voInvoices) {
    try {
      ISquareAcionFor32 squaresrv =
          NCLocator.getInstance().lookup(ISquareAcionFor32.class);
      squaresrv.autoSquareCostSrv(voInvoices);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��Ӧ��
   * 
   * @param voInvoices
   */
  public static void squareAdjustIncomeSrv(SaleInvoiceVO[] voInvoices) {
    try {
      ISquareAcionFor32 squaresrv =
          NCLocator.getInstance().lookup(ISquareAcionFor32.class);
      squaresrv.squareAdjustIncomeSrv(voInvoices);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���������Զ�����ر�,�����Զ�Ӧ�ս���رպ��Զ��ɱ�����ر�
   * 
   * @param para
   */
  public static void processAutoBalEnd(BalEndPara para) {
    ISaleOrderBalEndSrv srv =
        NCLocator.getInstance().lookup(ISaleOrderBalEndSrv.class);
    try {
      srv.processAutoBalEnd(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۷�Ʊ����Զ���ʽ�������۽��㵥
   * 
   * @param vos
   */
  public static void pushSquareSrv(SaleInvoiceVO[] vos) {
    ISquare33For32 squaresrv =
        NCLocator.getInstance().lookup(ISquare33For32.class);
    try {
      squaresrv.pushSquareSrv(vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۷�Ʊȡ������
   * 
   * @param vos
   */
  public static void cancelSquareSrv(SaleInvoiceVO[] vos) {
    ISquare33For32 squaresrv =
        NCLocator.getInstance().lookup(ISquare33For32.class);
    try {
      squaresrv.cancelSquareSrv(vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /***************** ���۶����ṩ�Ľӿ� ********************/

  /**
   * �������۶�������id��ѯɢ��id
   * 
   * @param ids
   * @return Map<String, Info30For32Para>
   */
  public static Map<String, Info30For32Para> queryInfosByOrderIDs(String[] ids) {
    ISaleOrderFor32 service =
        NCLocator.getInstance().lookup(ISaleOrderFor32.class);
    Map<String, Info30For32Para> ret = null;
    try {
      ret = service.queryInfosByOrderBIDs(ids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ret;
  }

  /**
   * ����32�������ƺ�32����Դͷ����id���ƹ�����˵�30�ѳ���رչ���SQLƬ��
   * 
   * @param invbodytable
   * @param cfirstbid
   * @return IQueryScheme
   */
  public static IQueryScheme getOutEndSQL4Filter32(String invbodytable,
      String cfirstbid) {
    ISaleOrderFor32 srv = NCLocator.getInstance().lookup(ISaleOrderFor32.class);
    IQueryScheme filterorder = null;
    try {
      filterorder = srv.getOutEndSQL4Filter32(invbodytable, cfirstbid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return filterorder;
  }

  /**
   * ��ѯ���۶���ViewVOsָ��ֵ
   * 
   * @param bids
   * @param names
   * @return SaleOrderViewVO
   */
  public static SaleOrderViewVO[] querySaleOrderViewVOs(String[] bids,
      String[] names) {
    ISaleOrderForPub squaresrv =
        NCLocator.getInstance().lookup(ISaleOrderForPub.class);
    try {
      return squaresrv.querySaleOrderViewVOs(bids, names);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return null;

  }

  /**
   * ���۷�Ʊ��д���۶���
   * 
   * @param paras
   */
  public static void rewrite30NumFor32(Rewrite32Para[] paras) {
    IRewrite30For32 api = NCLocator.getInstance().lookup(IRewrite30For32.class);
    try {
      api.rewrite30NumFor32(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  /******** ���۷��õ��ṩ�Ľӿ� ************/

  /**
   * �������۷�Ʊ��д��ֹ�ϵ
   * 
   * @param arsubvo
   * @param tempvo
   * @throws BusinessException
   */
  public static void writeArsubRelation(ArsubInterfaceVO[] arsubvo,
      OffsetTempVO[] tempvo) throws BusinessException {
    IArsubToSaleInvoice service =
        NCLocator.getInstance().lookup(IArsubToSaleInvoice.class);
    service.writeArsubRelation(arsubvo, tempvo);
  }

  /**
   * �������۷�Ʊ��д��ֹ�ϵ
   * 
   * @param arsubvo
   * @param tempvo
   * @throws BusinessException
   */
  public static void writeArsubRelation(ArsubInterfaceVO[] arsubvo,
      OffsetTempVO tempvo) throws BusinessException {
    IArsubToSaleInvoice service =
        NCLocator.getInstance().lookup(IArsubToSaleInvoice.class);
    service.writeArsubRelation(arsubvo, tempvo);
  }

  /**
   * ���۷�Ʊ��ˡ�ȡ�����ʱ��д����˳�ֽ��
   * 
   * @param invoiceids
   * @param ischeck
   */
  public static void writeNoriginvoicemny(String[] invoiceids, boolean ischeck) {
    IArsubToSaleInvoice arsubsrv =
        NCLocator.getInstance().lookup(IArsubToSaleInvoice.class);
    try {
      arsubsrv.writeNoriginvoicemny(invoiceids, ischeck);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
