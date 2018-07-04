package nc.impl.so.ordersummary.processer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.so.pub.summary.util.ReportSummaryARForSOUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.so.report.RemoteCallService;
import nc.pubitf.arap.receivable.IArapReceivableBillPubServiceForSCM;
import nc.pubitf.so.m32.so.m30.IQuery32For30;
import nc.vo.arap.itfvo.ReceivableBillInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.parameter.AskCostPriceParaVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.so.report.ordersummary.OrderSummaryViewVO;

/**
 * ��������ͳɱ���Ϣ
 * 
 * @since 6.3
 * @version 2012-10-18 14:37:44
 * @author ������
 */
public class OrderSummaryArAndIaProcesser {

  /**
   * ����ͳɱ���Ϣ
   * 
   * @param views
   */
  public void queryArAndIa(OrderSummaryViewVO[] views) {
    // 1.ȡ��ͷid�����id
    String[] orderheadids = this.getOrderHeadIds(views);
    String[] orderbodyids = this.getOrderBodyIds(views);
    // 2.��д��Ʊ��Ϣ
    this.setInvoiceMny(views, orderheadids, orderbodyids);
    // 3.ȡ���ⵥ����ͳɱ���Ϣ������ֵ��VO
    this.setOrderArAndIa(views, orderheadids, orderbodyids);
  }

  private void setInvoiceMny(OrderSummaryViewVO[] views, String[] orderheadids,
      String[] orderbodyids) {
    Map<String, UFDouble> mapinvoice =
        this.getInvoiceIds(orderheadids, orderbodyids);
    if (null != mapinvoice && mapinvoice.size() != 0) {
      for (OrderSummaryViewVO view : views) {
        if (view.getBlargessflag().equals(UFBoolean.TRUE)) {
          view.setNinvoiceorigtaxmny(UFDouble.ZERO_DBL);
        }
        else {
          String orderbid = view.getCsaleorderbid();
          if (mapinvoice.containsKey(orderbid)) {
            UFDouble totalinvmny = mapinvoice.get(orderbid);
            view.setNinvoiceorigtaxmny(totalinvmny);
          }
        }
      }
    }
  }

  private Map<String, UFDouble> getInvoiceIds(String[] orderheadids,
      String[] orderbodyids) {
    IQuery32For30 m32srv = NCLocator.getInstance().lookup(IQuery32For30.class);
    Map<String, UFDouble> mapinvoice = null;
    try {
      mapinvoice = m32srv.getInvoiceNorigtaxmny(orderheadids, orderbodyids);
      return mapinvoice;
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return mapinvoice;

  }

  private void setOrderArAndIa(OrderSummaryViewVO[] views,
      String[] orderheadids, String[] orderbodyids) {
    this.setOrderArInfor(views, orderheadids, orderbodyids);
    this.setOrderIaInfor(views, orderbodyids);
  }

  private void setOrderIaInfor(OrderSummaryViewVO[] views, String[] orderbodyids) {

    List<AskCostPriceParaVO> costpricelist =
        new ArrayList<AskCostPriceParaVO>();
    Map<String, String> m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(SoVoTools
            .getVOsOnlyValues(views, SaleOrderBVO.CSENDSTOCKORGID));
    // �����֯��Ӧ�Ľ��������֯
    String cendstordocfin = null;

    // ƴȡ�ɱ��۵Ĳ���
    for (OrderSummaryViewVO view : views) {
      Object ob = view.getAttributeValue(SaleOrderBVO.CMATERIALID);
      AskCostPriceParaVO costparavo = new AskCostPriceParaVO();
      costparavo.setCinventoryid(null == ob ? null : ob.toString());
      ob = view.getAttributeValue(SaleOrderBVO.PK_ORG);
      costparavo.setPk_org(null == ob ? null : ob.toString());
      // �������������֯
      String orderfin =
          (String) view.getAttributeValue(SaleOrderBVO.CSETTLEORGID);
      costparavo.setPk_financeorg(null == ob ? null : ob.toString());
      // ����֯���۴������֯�Ͳֿ� ������֯��ʱ�򲻴�����ȥ���������֯�ĳɱ�
      ob = view.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGID);
      cendstordocfin = m_st_fin.get(ob);
      // ����֯����Ҫ�������֯�Ͳֿ���ȡ�ɱ�
      if (orderfin.equals(cendstordocfin)) {
        costparavo.setPk_stockorg(null == ob ? null : ob.toString());
        ob = view.getAttributeValue(SaleOrderBVO.CSENDSTORDOCID);
        costparavo.setPk_stordoc(null == ob ? null : ob.toString());
      }
      ob = view.getAttributeValue(SaleOrderBVO.VBATCHCODE);
      costparavo.setVbatch(null == ob ? null : ob.toString());
      costpricelist.add(costparavo);
    }

    // ȡ�ɱ���
    Map<String, UFDouble> costprice =
        RemoteCallService.getPriceBySCM02(costpricelist
            .toArray(new AskCostPriceParaVO[costpricelist.size()]));

    // ȡ���۶����д�����������ѳɱ�����Ľ���δ�ɱ����������
    Map<String, UFDouble[]> iainfor =
        RemoteCallService.getSOReportCostMny(orderbodyids);

    Map<String, UFDouble> ntotalreceivmnymap = new HashMap<String, UFDouble>();
    // ����Ӧ��ģ��
    if (SysInitGroupQuery.isAREnabled()) {
      ntotalreceivmnymap = RemoteCallService.getNotaxForSoorder(orderbodyids);
    }

    for (OrderSummaryViewVO view : views) {
      String orderbid = view.getCsaleorderbid();
      if (null != iainfor && iainfor.containsKey(orderbid)) {
        // ����Ҫͳ�ƵĽ�������ֶ�
        this.addTotalKeys(view, costprice, iainfor, ntotalreceivmnymap,
            m_st_fin);
      }
    }
  }

  /**
   * ����Ҫͳ�ƵĽ�������ֶ�
   * 
   * @param viewvo
   * @param costprice
   * @param costmap
   * @param m_st_fin
   */
  private void addTotalKeys(OrderSummaryViewVO viewvo,
      Map<String, UFDouble> costprice, Map<String, UFDouble[]> costmap,
      Map<String, UFDouble> ntotalreceivmnymap, Map<String, String> m_st_fin) {

    String key = this.createKey(viewvo, m_st_fin);
    // ����������
    UFDouble nordernnum = viewvo.getNnum();

    // Ӧ��������(ȷ��Ӧ������������������=ȷ��+�س壩+�ݹ�Ӧ����������
    UFDouble ntotalreceivnum =
        MathTool.add(
            (UFDouble) viewvo.getAttributeValue(SaleOrderBVO.NTOTALESTARNUM),
            (UFDouble) viewvo.getAttributeValue(SaleOrderBVO.NTOTALARNUM));

    // �ۼƳɱ�����������
    UFDouble ntotalcostnum =
        costmap.get((String) viewvo
            .getAttributeValue(SaleOrderBVO.CSALEORDERBID))[2];

    // ������
    UFBoolean bbcostsettleflag =
        (UFBoolean) viewvo.getAttributeValue(SaleOrderBVO.BBCOSTSETTLEFLAG);
    UFBoolean bbarsettleflag =
        (UFBoolean) viewvo.getAttributeValue(SaleOrderBVO.BBARSETTLEFLAG);
    if (bbcostsettleflag.booleanValue() && bbarsettleflag.booleanValue()) {
      bbcostsettleflag = UFBoolean.TRUE;
    }
    else {
      bbcostsettleflag = UFBoolean.FALSE;
    }
    UFDouble nmainnum =
        this.getMainnum(nordernnum, ntotalreceivnum, ntotalcostnum,
            bbcostsettleflag);

    // ������ģ��ȡ�۹���
    UFDouble outprice = costprice.get(key);
    // �ɱ�����ɱ����----�ɱ�����ɱ���� =
    // �������ɴ�����㵥���Ѿ��ɱ����㲿�ֵĳɱ����+�������ɴ�����㵥��δ�ɱ����㲿�ֵ�������
    // *����ģ��ȡ�ɱ����򣺽�浥��or�ο��ɱ�or�ƻ��ۣ������ݼӹ��м��㣩
    UFDouble ntotalsettlecostmny =
        this.getNtotalsettlecostmny(
            (String) viewvo.getAttributeValue(SaleOrderBVO.CSALEORDERBID),
            costmap, outprice);
    // �ɱ����---�������ɱ���� + ��������-�����������������* ����ģ��ȡ�ɱ����򣺽�浥��or�ο��ɱ�or�ƻ��ۣ������ݼӹ��м��㣩
    UFDouble ntotalcostmny =
        this.getNtotalcostmny(ntotalsettlecostmny, nmainnum, ntotalcostnum,
            outprice);

    viewvo.setNtotalcostmny(ntotalcostmny);
  }

  private String createKey(OrderSummaryViewVO viewvo,
      Map<String, String> m_st_fin) {

    String cendstordocfin =
        m_st_fin.get(viewvo.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGID));
    StringBuffer ret = new StringBuffer();
    Object ob = viewvo.getAttributeValue(SaleOrderBVO.PK_ORG);
    ret.append(null == ob ? "null|" : ob.toString() + "|");

    ob = viewvo.getAttributeValue(SaleOrderBVO.CSETTLEORGID);
    ret.append(null == ob ? "null|" : ob.toString() + "|");
    // ����֯�ʹ��˿����֯�Ӳֿ�
    if (null != ob && ob.equals(cendstordocfin)) {
      ob = viewvo.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGID);
      ret.append(null == ob ? "null|" : ob.toString() + "|");
      ob = viewvo.getAttributeValue(SaleOrderBVO.CSENDSTORDOCID);
      ret.append(null == ob ? "null|" : ob.toString() + "|");
    }
    else {
      // ����֯û�������֯�Ӳֿ�
      ret.append("null|");
      ret.append("null|");
    }

    ob = viewvo.getAttributeValue(SaleOrderBVO.CMATERIALID);
    ret.append(null == ob ? "null|" : ob.toString() + "|");
    ob = viewvo.getAttributeValue(SaleOrderBVO.VBATCHCODE);
    ret.append(null == ob ? "null" : ob.toString());

    return ret.toString();
  }

  /**
   * �ɱ�����ɱ����
   * 
   * @param key ��������id
   * @param costmap
   * @param outprice �ɱ�����
   * @return
   */
  private UFDouble getNtotalsettlecostmny(String key,
      Map<String, UFDouble[]> costmap, UFDouble outprice) {
    UFDouble[] ncost = costmap.get(key);
    if (null == ncost) {
      return UFDouble.ZERO_DBL;
    }
    UFDouble mny =
        ncost[1].multiply(null == outprice ? new UFDouble(0) : outprice);
    mny = MathTool.add(mny, ncost[0]);
    return mny;
  }

  private UFDouble getNtotalcostmny(UFDouble ntotalsettlecostmny,
      UFDouble mainnum, UFDouble ntotalcostnum, UFDouble outprice) {
    UFDouble num = MathTool.sub(mainnum, ntotalcostnum);
    UFDouble mny = num.multiply(null == outprice ? new UFDouble(0) : outprice);
    return MathTool.add(ntotalsettlecostmny, mny);
  }

  /**
   * ������
   * 
   * @param view
   */
  private UFDouble getMainnum(UFDouble ordernnum, UFDouble ntotalreceivnum,
      UFDouble ntotalcostnum, UFBoolean bbcostsettleflag) {
    UFDouble retmainnum = UFDouble.ZERO_DBL;
    // ����ر� ȡ Ӧ���������ͳɱ��������������ֵ
    if (bbcostsettleflag.booleanValue()) {
      if (MathTool.absCompareTo(ntotalreceivnum, ntotalcostnum) > 0) {
        retmainnum = ntotalreceivnum;
      }
      else {
        retmainnum = ntotalcostnum;
      }
    }
    // Ϊ����ر�ȡ ������������Ӧ���������ͳɱ��������������ֵ
    else {
      retmainnum = ordernnum;
      if (MathTool.absCompareTo(ntotalcostnum, retmainnum) > 0) {
        retmainnum = ntotalcostnum;
      }
      if (MathTool.absCompareTo(ntotalreceivnum, retmainnum) > 0) {
        retmainnum = ntotalreceivnum;
      }
    }
    return retmainnum;
  }

  private void setOrderArInfor(OrderSummaryViewVO[] views,
      String[] orderheadids, String[] orderbodyids) {
    // add by wangshu6 for v636 ��������ִ�б��� 2015-1-28 ���ж��Ƿ����ù���ģ�飬
    if(!SysInitGroupQuery.isAPEnabled()){
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006005_0", "04006005-0028")/*Ӧ��ģ��δ���ã�*/);
      return;
    }
    // end
    Map<String, ReceivableBillInfoVO> arinfo = null;
    IArapReceivableBillPubServiceForSCM arsrv =
        NCLocator.getInstance().lookup(
            IArapReceivableBillPubServiceForSCM.class);
    try {
      arinfo =
          arsrv.queryReceivableBillInfoBySrcBillExcludeTusun(orderheadids,
              orderbodyids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    for (OrderSummaryViewVO view : views) {
      String orderbid = view.getCsaleorderbid();
      // Ӧ�������� ԴͷΪ�������е�Ӧ�յ��������ϼ�
      // Ӧ�ս�� ԴͷΪ�������е�Ӧ�յ���˰�ϼƽ�
      // Ӧ����� ԴͷΪ�������е�Ӧ�յ����ϼƣ�

      UFDouble narnum = null;
      UFDouble narremainmny = null;
      UFDouble localmoney = null;
      if (null != arinfo && arinfo.containsKey(orderbid)) {
        ReceivableBillInfoVO arbvo = arinfo.get(orderbid);
        narnum = MathTool.add(narnum, arbvo.getQuantity_de());
        // Ӧ�յ���.��֯���ҽ��
        localmoney = MathTool.add(localmoney, arbvo.getLocal_money_de());
        // Ӧ�յ���.��֯�������
        narremainmny = MathTool.add(narremainmny, arbvo.getLocal_money_bal());
      }
      view.setNshouldreceivnum(narnum);
      view.setNshouldreceivmny(localmoney);
      view.setNtotalreceivmny(narremainmny);
    }
  }

  private String[] getOrderBodyIds(OrderSummaryViewVO[] views) {
    Set<String> orderbodyid = new HashSet<String>();
    for (OrderSummaryViewVO view : views) {
      orderbodyid.add(view.getCsaleorderbid());
    }
    String[] orderbodyids = new String[orderbodyid.size()];
    orderbodyid.toArray(orderbodyids);
    return orderbodyids;
  }

  private String[] getOrderHeadIds(OrderSummaryViewVO[] views) {
    Set<String> orderheadid = new HashSet<String>();
    for (OrderSummaryViewVO view : views) {
      orderheadid.add(view.getCsaleorderid());
    }
    String[] orderheadids = new String[orderheadid.size()];
    orderheadid.toArray(orderheadids);
    return orderheadids;
  }
}
