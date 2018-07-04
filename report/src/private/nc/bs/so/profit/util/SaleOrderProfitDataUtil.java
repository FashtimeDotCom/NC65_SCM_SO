package nc.bs.so.profit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.parameter.AskCostPriceParaVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.so.report.paravo.ProfitContext;
import nc.vo.so.report.profit.OrderProfitViewVO;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.so.report.RemoteCallService;

import nc.pub.smart.context.SmartContext;

import nc.ui.querytemplate.querytree.QueryScheme;

public class SaleOrderProfitDataUtil {

  /**
   * ������֯���ζ�Ӧ��ϵ
   */
  private Map<String, String> saleorglevelmap = new HashMap<String, String>();

  /**
   * ���췽��
   */
  public SaleOrderProfitDataUtil() {
    // TODO
  }

  public void processData(OrderProfitViewVO[] viewvos, SmartContext content) {
    TimeLog.logStart();
    String key =
        com.ufida.report.anareport.FreeReportContextKey.KEY_IQUERYCONDITION;
    com.ufida.report.anareport.base.BaseQueryCondition condition =
        (com.ufida.report.anareport.base.BaseQueryCondition) content
            .getAttribute(key);
    QueryScheme scheme = (QueryScheme) condition.getUserObject();
    ConditionVO[] conds = (ConditionVO[]) scheme.get("logicalcondition");
    // ������֯���ܼ���
    UFDouble saleorglevel = UFDouble.ZERO_DBL;
    // ���Ϸ��༶��
    UFDouble cmaterialmarbasclasslevel = UFDouble.ZERO_DBL;
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ProfitContext.SALEORGLEVEL)) {
        // ������֯����
        saleorglevel = new UFDouble(cond.getValue());

      }
      else if (cond.getFieldCode().equals(
          ProfitContext.CMATERIALMARBASCLASSLEVEL)) {
        cmaterialmarbasclasslevel = new UFDouble(cond.getValue());
      }
    }
    // ��ȡ���۶����ӱ�ID��
    List<String> bidlist = new ArrayList<String>();
    List<String> saleorgidlist = new ArrayList<String>();
    List<String> materidlist = new ArrayList<String>();
    List<AskCostPriceParaVO> costpricelist =
        new ArrayList<AskCostPriceParaVO>();

    Map<String, String> m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(SoVoTools
            .getVOsOnlyValues(viewvos, SaleOrderBVO.CSENDSTOCKORGID));
    // �����֯��Ӧ�Ľ��������֯
    String cendstordocfin = null;

    for (OrderProfitViewVO viewvo : viewvos) {
      Object ob = viewvo.getAttributeValue(SaleOrderBVO.CSALEORDERBID);
      bidlist.add(null == ob ? null : ob.toString());
      ob = viewvo.getAttributeValue(SaleOrderBVO.PK_ORG);
      saleorgidlist.add(null == ob ? null : ob.toString());
      ob = viewvo.getAttributeValue(SaleOrderBVO.CMATERIALID);
      materidlist.add(null == ob ? null : ob.toString());
      AskCostPriceParaVO costparavo = new AskCostPriceParaVO();
      costparavo.setCinventoryid(null == ob ? null : ob.toString());
      ob = viewvo.getAttributeValue(SaleOrderBVO.PK_ORG);
      costparavo.setPk_org(null == ob ? null : ob.toString());
      // �������������֯
      String orderfin =
          (String) viewvo.getAttributeValue(SaleOrderBVO.CSETTLEORGID);
      costparavo.setPk_financeorg(null == ob ? null : ob.toString());
      // ����֯���۴������֯�Ͳֿ� ������֯��ʱ�򲻴�����ȥ���������֯�ĳɱ�
      ob = viewvo.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGID);
      cendstordocfin = m_st_fin.get(ob);
      // ����֯����Ҫ�������֯�Ͳֿ���ȡ�ɱ�
      if (orderfin.equals(cendstordocfin)) {
        costparavo.setPk_stockorg(null == ob ? null : ob.toString());
        ob = viewvo.getAttributeValue(SaleOrderBVO.CSENDSTORDOCID);
        costparavo.setPk_stordoc(null == ob ? null : ob.toString());
      }
      ob = viewvo.getAttributeValue(SaleOrderBVO.VBATCHCODE);
      costparavo.setVbatch(null == ob ? null : ob.toString());
      costpricelist.add(costparavo);
    }
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006005_0", "04006005-0002")/* @res "�������ݣ�׼��5000ȡ�ɱ��۵Ĳ���" */);
    Map<String, String> materialmarbasmap = new HashMap<String, String>();
    if (!MathTool.isZero(cmaterialmarbasclasslevel)) {
      String[] cmaterialids =
          materidlist.toArray(new String[materidlist.size()]);
      materialmarbasmap =
          RemoteCallService.queryMarBasClassIDByClassLevelAndMaterialOIDs(
              cmaterialids, cmaterialmarbasclasslevel.intValue());
    }
    Map<String, UFDouble[]>  costmap = new HashMap<String, UFDouble[]>();
   // ���ô������ģ��
    if (SysInitGroupQuery.isIAEnabled()) {
      costmap= RemoteCallService.getSOCostMny(bidlist.toArray(new String[bidlist
            .size()]));
    }
    Map<String, UFDouble> ntotalreceivmnymap = new HashMap<String, UFDouble>();
    // ����Ӧ��ģ��
    if (SysInitGroupQuery.isAREnabled()) {
      ntotalreceivmnymap =
          RemoteCallService.getNotaxForSoorder(bidlist
              .toArray(new String[bidlist.size()]));
    }

    TimeLog.logStart();
    Map<String, UFDouble> costprice =
        RemoteCallService.getPriceBySCM02(costpricelist
            .toArray(new AskCostPriceParaVO[costpricelist.size()]));
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006005_0", "04006005-0003")/* @res "�������ݣ�ȡ5000���ݵĳɱ���" */);
    TimeLog.logStart();
    for (OrderProfitViewVO viewvo : viewvos) {
      // ����Ҫͳ�ƵĽ�������ֶ�
      this.addTotalKeys(viewvo, costprice, costmap, ntotalreceivmnymap,
          m_st_fin);

      this.processLevel(viewvo, materialmarbasmap, saleorglevel.intValue());

    }
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006005_0", "04006005-0004")/* @res "�������ݣ�5000�����ݵļ���ʹ�����" */);
  }

  private void addTotalKeys(OrderProfitViewVO viewvo,
      Map<String, UFDouble> costprice, Map<String, UFDouble[]> costmap,
      Map<String, UFDouble> ntotalreceivmnymap, Map<String, String> m_st_fin) {

    String key = this.createKey(viewvo, m_st_fin);
    // ����������
    UFDouble nordernnum = viewvo.getNordernnum();
    // ��������˰����
    UFDouble nnetprice = viewvo.getNnetprice();
    // Ӧ��������(ȷ��Ӧ������������������=ȷ��+�س壩+�ݹ�Ӧ����������
    UFDouble ntotalreceivnum =
        MathTool.add(viewvo.getNtotalestarnum(), viewvo.getNtotalarnum());
    // �ۼƳɱ�����������
    UFDouble ntotalcostnum = viewvo.getNtotalcostnum();
    UFDouble[] values =
        costmap.get(viewvo.getAttributeValue(SaleOrderBVO.CSALEORDERBID));
    if (!ArrayUtils.isEmpty(values) && values.length > 2) {
      ntotalcostnum = values[2];
    }
    // Ӧ����˰���
    UFDouble ntotalreceivmny =
        ntotalreceivmnymap.get(viewvo
            .getAttributeValue(SaleOrderBVO.CSALEORDERBID));

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
    // ��˰���=Ӧ����˰��� + �������� - Ӧ����������* ����������˰����(�����ݼӹ��м���)
    UFDouble nntaxmny =
        this.getNmny(ntotalreceivmny, nmainnum, ntotalreceivnum, nnetprice);
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
    // ��˰���� = ��˰��� / ������(���湫ʽ����)
    UFDouble nntaxprice = UFDouble.ZERO_DBL;
    // �ɱ�����(���湫ʽ����)
    UFDouble ncostprice = UFDouble.ZERO_DBL;
    // if (!MathTool.isZero(nmainnum)) {
    // nntaxprice = nntaxmny.div(nmainnum);
    // ncostprice = ntotalcostmny.div(nmainnum);
    // }
    // ë��
    UFDouble nprofitmny = MathTool.sub(nntaxmny, ntotalcostmny);
    // ë����
    UFDouble nprofitcate = UFDouble.ZERO_DBL;

    viewvo.setNmainnum(nmainnum);
    viewvo.setNtotalreceivnum(ntotalreceivnum);
    viewvo.setNtotalreceivmny(ntotalreceivmny);
    viewvo.setNntaxmny(nntaxmny);
    viewvo.setNntaxprice(nntaxprice);
    viewvo.setNtotalcostnum(ntotalcostnum);
    viewvo.setNtotalsettlecostmny(ntotalsettlecostmny);
    viewvo.setNtotalcostmny(ntotalcostmny);
    viewvo.setNcostprice(ncostprice);
    viewvo.setNprofitmny(nprofitmny);
    viewvo.setNprofitcate(nprofitcate);
  }

  private String createKey(OrderProfitViewVO viewvo,
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
   * ����������
   * 
   * @param ordernnum ����������
   * @param ntotalreceivnum Ӧ��������
   * @param ntotalcostnum �ɱ�����������
   * @param bbcostsettleflag
   * @return
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

  /**
   * ��˰���=Ӧ����˰��� + �������� - Ӧ����������* ����������˰����
   * 
   * @param ntotalallmny
   * @param mainnum
   * @param ntotalallnum
   * @param nnetprice
   * @return
   */
  private UFDouble getNmny(UFDouble ntotalallmny, UFDouble mainnum,
      UFDouble ntotalallnum, UFDouble nnetprice) {
    UFDouble price = nnetprice;
    if (null == nnetprice) {
      price = new UFDouble(0);
    }

    UFDouble ret = MathTool.sub(mainnum, ntotalallnum).multiply(price);
    return MathTool.add(ntotalallmny, ret);
  }

  private UFDouble getNtotalcostmny(UFDouble ntotalsettlecostmny,
      UFDouble mainnum, UFDouble ntotalcostnum, UFDouble outprice) {
    UFDouble num = MathTool.sub(mainnum, ntotalcostnum);
    UFDouble mny = num.multiply(null == outprice ? new UFDouble(0) : outprice);
    return MathTool.add(ntotalsettlecostmny, mny);
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

  private void processLevel(OrderProfitViewVO viewvo,
      Map<String, String> materialmarbasmap, int saleorglevel) {
    // Ϊĩ���򲻴���Ӧ�û������౾�����ĩ����
    if (saleorglevel != 0) {
      String cmarid =
          viewvo.getAttributeValue(SaleOrderBVO.CMATERIALID).toString();
      viewvo.setAttributeValue(MaterialVO.PK_MARBASCLASS,
          materialmarbasmap.get(cmarid));
      String oldsaleorgid =
          viewvo.getAttributeValue(SaleOrderBVO.PK_ORG).toString();

      String newsaleorgid = this.saleorglevelmap.get(oldsaleorgid);
      if (null == newsaleorgid) {
        newsaleorgid =
            RemoteCallService.querySaleorgIDByLevel(oldsaleorgid, saleorglevel);
        this.saleorglevelmap.put(oldsaleorgid, newsaleorgid);
      }
      viewvo.setAttributeValue(SaleOrderBVO.PK_ORG, newsaleorgid);
    }
  }

}
