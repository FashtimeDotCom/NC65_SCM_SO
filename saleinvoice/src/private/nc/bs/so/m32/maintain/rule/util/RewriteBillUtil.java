package nc.bs.so.m32.maintain.rule.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m32.maintain.rule.insert.Rewrite4CFor32Para;
import nc.cmbd.vo.scmpub.res.billtype.SOBillType;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.env.BSContext;
import nc.pubitf.ic.m4c.m32.IRewrite4CFor32;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;
import nc.vo.so.pub.tolerance.IAbandonToleranceCheck;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ��д������
 * 
 * @since 6.3
 * @version 2012-12-21 ����09:06:23
 * @author yaogj
 */
public class RewriteBillUtil {

  private Map<String, String> mapOrg = new HashMap<String, String>();

  /**
   * 
   * RewriteBillUtil �Ĺ�����
   */
  public RewriteBillUtil() {
    // ��ʼ��ʱ��Ҫ���ü���־λ
    this.setBusinessCheckFlag();
  }

  /**
   * ����������֯
   * 
   * @param voInvoices
   */
  public void catcheOrg(SaleInvoiceVO[] voInvoices) {
    for (SaleInvoiceVO invoice : voInvoices) {
      for (SaleInvoiceBVO bvo : invoice.getChildrenVO()) {
        if (ICBillType.SaleOut.getCode().equals(bvo.getVsrctype())) {
          this.mapOrg.put(bvo.getCsrcbid(), bvo.getCsaleorgid());
        }
      }
    }

  }

  /**
   * ��������������������Դ30�Ļ�д��¼����Դͷ���ݡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param firstParaList
   * @param srcParaList
   * @return <p>
   * @author fengjb
   * @time 2010-5-11 ����06:55:09
   */
  public List<RewritePara> filtrateSrc30(List<RewritePara> firstParaList,
      List<RewritePara> srcParaList) {
    // �����ԴΪ�գ�ֱ�ӷ��ش���Դͷ
    if (VOChecker.isEmpty(srcParaList)) {
      return firstParaList;
    }
    Set<String> hsBid = new HashSet<String>();
    for (RewritePara para : srcParaList) {
      hsBid.add(para.getCsrcbid());
    }

    List<RewritePara> filter = new ArrayList<RewritePara>();

    for (RewritePara para : firstParaList) {
      if (!hsBid.contains(para.getCsrcbid())) {
        filter.add(para);
      }
    }
    return filter;
  }

  /**
   * ������������������Դͷ���ݻ�д�����ࡣ
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-5-11 ����06:37:17
   */
  public BillRewriter getFirstBillRewriter() {
    // ���÷�ƱԴͷ�������͡�ID��BID�������ֶ�
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(SaleInvoiceBVO.VFIRSTTYPE);
    mapping.setCsrcidKey(SaleInvoiceBVO.CFIRSTID);
    mapping.setCsrcbidKey(SaleInvoiceBVO.CFIRSTBID);
    mapping.setNnumKey(SaleInvoiceBVO.NNUM);

    // ��ӷ�ƱԴͷ��������
    BillRewriter tool = new BillRewriter(mapping);

    // ���۶���
//    tool.addSRCHeadClazz(SOBillType.Order.getCode(), SaleOrderHVO.class);
//    tool.addSRCItemClazz(SOBillType.Order.getCode(), SaleOrderBVO.class);

    return tool;
  }

  /**
   * ��������������������Դ���ݻ�д�����ࡣ
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-28 ����04:09:25
   */
  public BillRewriter getSrcBillRewriter() {

    // ���÷�Ʊ���ε������͡�ID��BID�������ֶ�
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(SaleInvoiceBVO.VSRCTYPE);
    mapping.setCsrcidKey(SaleInvoiceBVO.CSRCID);
    mapping.setCsrcbidKey(SaleInvoiceBVO.CSRCBID);
    mapping.setNnumKey(SaleInvoiceBVO.NNUM);
    mapping.setSrcTSKey(SaleInvoiceBVO.SRCTS);
    // ��ӷ�Ʊ���ε�������
    BillRewriter tool = new BillRewriter(mapping);
    // ���۶���������ƽ̨��д add by zhangby5
     tool.addSRCHeadClazz("30", SaleOrderHVO.class);
     tool.addSRCItemClazz("30", SaleOrderBVO.class);

    // ���۳��ⵥ
    tool.addSRCHeadClazz(ICBillType.SaleOut.getCode(), SaleOutHeadVO.class);
    tool.addSRCItemClazz(ICBillType.SaleOut.getCode(), SaleOutBodyVO.class);

    return tool;

  }

  /**
   * ���۷�Ʊ�²���ʱ��дԴͷ���۶���
   * 
   * @param paraList ��д����
   */
  public void reWriteFirst30(List<RewritePara> paraList) {

    int size = paraList.size();
    Rewrite32Para[] paras = new Rewrite32Para[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      paras[i] = new Rewrite32Para(bid, nnum);
    }

    RemoteFormSOUtil.rewrite30NumFor32(paras);

  }

  /**
   * ���۷�Ʊ����ʱ��д��Դ���۶���
   * 
   * @param paraList ��д����
   */
  public void reWriteSrc30(List<RewritePara> paraList) {

    int size = paraList.size();
    Rewrite32Para[] paras = new Rewrite32Para[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      paras[i] = new Rewrite32Para(bid, nnum);
    }

    RemoteFormSOUtil.rewrite30NumFor32(paras);

  }

  /**
   * �����������������۷�Ʊ����ʱ��д��Դ���۳��ⵥ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param paraList
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-30 ����03:11:21
   */
  public void reWriteSrc4C(List<RewritePara> paraList) {
    int size = paraList.size();
    Rewrite4CFor32Para[] paras = new Rewrite4CFor32Para[size];
    for (int i = 0; i < size; i++) {
      RewritePara para = paraList.get(i);
      String voicebid = para.getCbill_bid();
      String hid = para.getCsrcid();
      String bid = para.getCsrcbid();
      UFDouble nnum = para.getNnum();
      paras[i] = new Rewrite4CFor32Para(voicebid, hid, bid, nnum);
    }
    this.setToleranceCheckPara(paras);

    IRewrite4CFor32 api = NCLocator.getInstance().lookup(IRewrite4CFor32.class);
    try {
      api.rewrite4CAccInNumFor32(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private String getSO08(String pk_org) {
    String so08 = null;

    so08 = SOSysParaInitUtil.getSO08(pk_org);
    // so08 =
    // SysParaInitQuery.getParaString(pk_org, ParameterList.SO08.getCode());

    return so08;
  }

  /**
   * 
   * �����������������ÿ�Ʊ��д���ε��ݼ����Ʊ�־��
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author fengjb
   * @time 2010-8-31 ����02:41:15
   */
  @SuppressWarnings("unchecked")
  private void setBusinessCheckFlag() {
    Map<String, Boolean> busicheck =
        (Map<String, Boolean>) BSContext.getInstance().getSession(
            BusinessCheck.class.getName());
    if (VOChecker.isEmpty(busicheck)) {
      return;
    }

    IAbandonToleranceCheck service =
        NCLocator.getInstance().lookup(IAbandonToleranceCheck.class);
    // ������Ʊ����
    Boolean tocheckorder =
        busicheck.get(BusinessCheck.OrderToleranceCheck.getCheckCode());
    if (null != tocheckorder && !tocheckorder.booleanValue()) {
      service.abandonOrderToleranceCheck();
    }
    // ���ⵥ��Ʊ����
    Boolean tocheckout =
        busicheck.get(BusinessCheck.OutToleranceCheck.getCheckCode());
    if (null != tocheckout && !tocheckout.booleanValue()) {
      service.abandonOutToleranceCheck();
    }
  }

  /**
   * 
   * �����������������У�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param paras
   *          <p>
   * @author fengjb
   * @time 2010-9-1 ����03:32:03
   */
  private void setToleranceCheckPara(Rewrite4CFor32Para[] paras) {

    Map<String, String> mapCheckPara = new HashMap<String, String>();
    for (Rewrite4CFor32Para para : paras) {
      String orgid = this.mapOrg.get(para.getBid());
      if (mapCheckPara.containsKey(orgid)) {
        para.setSO08(mapCheckPara.get(orgid));
      }
      else {
        String so08 = this.getSO08(orgid);
        mapCheckPara.put(orgid, so08);
        para.setSO08(so08);
      }
    }
  }
}
