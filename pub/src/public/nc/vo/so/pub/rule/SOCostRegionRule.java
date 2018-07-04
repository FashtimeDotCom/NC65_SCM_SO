package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.pubitf.to.settlerule.ic.MatchSettleRuleResult;
import nc.pubitf.to.settlerule.so.IMatchSettleruleServiceForSo;
import nc.pubitf.to.settlerule.so.MatchSettleRuleVOForSo;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ���۹���ɱ���ȡ��������
 * 
 * @since 6.5
 * @version 2015-6-13 ����02:36:21
 * @author ����
 */
public class SOCostRegionRule {

  /**
   * �ɱ����ѯ</br>
   * 
   * ����֯��ͨ�������֯+�ֿ��óɱ���</br>
   * ����֯������ͨ���ڲ����׽�������óɱ�����ν��������֯��óɱ���
   * @param ���������֯old���ֿ�id�������������͡��������oid������oid
   * @return paras Map<��ѯ����,�ɱ���>
   */
  public Map<String, String> queryCostRegion(CostRegionPara[] paras) {
    // ���ֵ���֯����֯����
    List<CostRegionPara[]> list = this.filterSingleSpanData(paras);
    CostRegionPara[] singlevos = list.get(0);
    CostRegionPara[] spanlevos = list.get(1);

    // ���óɱ���:����֯
    this.setCostOrgForSingle(singlevos);

    // ���óɱ���:����֯
    this.setCostOrgForSpan(spanlevos);

    Map<String, String> costregionmap = retCostRegion(paras);

    return costregionmap;
  }

  /**
   * �����ɱ�map��key
   * 
   * @param cinfinanceorgid ������֯
   * @param cmaterialid ����
   * @param tOrdertantype ��������
   * @param stockorgid ���������֯
   * @param stordocid �ֿ�
   * @return
   */
  public String getCostRegionMapKey(String cinfinanceorgid, String cmaterialid,
      String tOrdertantype, String stockorgid, String stordocid) {
    StringBuffer costpara = new StringBuffer();
    costpara.append(cinfinanceorgid);
    costpara.append(cmaterialid);
    costpara.append(tOrdertantype);
    costpara.append(stockorgid);
    costpara.append(stordocid);
    return costpara.toString();
  }

  private Map<String, String> retCostRegion(CostRegionPara[] paras) {
    Map<String, String> costregionmap = new HashMap<String, String>();
    if (null == paras || paras.length == 0) {
      return costregionmap;
    }
    for (CostRegionPara para : paras) {
      String costparakey =
          getCostRegionMapKey(para.getCinfinanceorgid(), para.getCmaterialid(),
              para.getOrdertantype(), para.getStockorgid(), para.getStordocid());
      costregionmap.put(costparakey, para.getCcostorgid());
    }
    return costregionmap;
  }

  private void setCostOrgForSpan(CostRegionPara[] paras) {
    if (VOChecker.isEmpty(paras)) {
      return;
    }
    // ��ȡ�ڲ��������
    Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr =
        this.getMatchSettleRuleResult(paras);

    // ͨ���ڲ����׽���������� �ɱ���
    List<CostRegionPara> l_ByFinanceList =
        this.setCostOrgByTOSettleRule(m_index_cr, paras);

    // ͨ�����۽��������֯���� �ɱ���
    int size = l_ByFinanceList.size();
    if (size > 0) {
      this.setCostOrgByFinance(l_ByFinanceList
          .toArray(new CostRegionPara[size]));
    }

  }

  private void setCostOrgByFinance(CostRegionPara[] paras) {
    Set<String> stockorgidset = new HashSet<String>();
    for (CostRegionPara para : paras) {
      stockorgidset.add(para.getCinfinanceorgid());
    }
    Map<String, String> m_fico =
        CostRegionPubService
            .getDefaultCostRegionMapByFinanceOrgIDS(stockorgidset
                .toArray(new String[stockorgidset.size()]));

    if (m_fico != null) {
      for (CostRegionPara para : paras) {
        para.setCcostorgid(m_fico.get(para.getCinfinanceorgid()));
      }
    }
  }

  private List<CostRegionPara> setCostOrgByTOSettleRule(
      Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr,
      CostRegionPara[] paras) {
    String costorgid = null;
    // ��Ҫͨ�����۽��������֯�ҳɱ����vo
    List<CostRegionPara> l_ByFinanceList = new ArrayList<CostRegionPara>();
    for (CostRegionPara para : paras) {

      MatchSettleRuleVOForSo matchpara = this.getMatchSettleRuleVOForSo(para);
      // �ڲ��������
      MatchSettleRuleResult mrlt = m_index_cr.get(matchpara);
      if (mrlt == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006010_0", "04006010-0036")/*
                                                                     * @res
                                                                     * "����֯����û��ƥ�䵽�ڲ����׽������"
                                                                     */);
      }
      else {
        costorgid = mrlt.getCostRegion();
        // ���óɱ���
        if (costorgid == null) {
          l_ByFinanceList.add(para);
        }
        else {
          para.setCcostorgid(costorgid);
        }
      } // end else
    }
    return l_ByFinanceList;
  }

  private Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> getMatchSettleRuleResult(
      CostRegionPara[] paras) {
    // ׼����ȡ�ڲ�����������
    List<MatchSettleRuleVOForSo> l_matchSettle =
        new ArrayList<MatchSettleRuleVOForSo>();
    for (CostRegionPara para : paras) {
      MatchSettleRuleVOForSo mso = this.getMatchSettleRuleVOForSo(para);
      l_matchSettle.add(mso);
    }

    // ��ȡ�ڲ��������
    Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr = null;
    try {
      if (!SysInitGroupQuery.isTOEnabled()) {
        new HashMap<MatchSettleRuleVOForSo, MatchSettleRuleResult>();
      }
      IMatchSettleruleServiceForSo bo =
          NCLocator.getInstance().lookup(IMatchSettleruleServiceForSo.class);
      m_index_cr = bo.matchSettleruleForSo(l_matchSettle);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    if (m_index_cr == null || m_index_cr.size() == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006010_0", "04006010-0036")/*
                                                                   * @res
                                                                   * "����֯����û��ƥ�䵽�ڲ����׽������"
                                                                   */);
    }
    return m_index_cr;
  }

  /**
   * ׼���ڲ��������ӿڲ���VO
   * 
   * @param hvo
   * @param bvo
   * @param index
   * @return
   */
  private MatchSettleRuleVOForSo getMatchSettleRuleVOForSo(CostRegionPara para) {
    MatchSettleRuleVOForSo mstoVo = new MatchSettleRuleVOForSo();
    String pk_group = AppContext.getInstance().getPkGroup();
    mstoVo.setPk_group(pk_group);
    // Դͷ������������
    mstoVo.setCtranstype(para.getOrdertantype());
    // ���������֯
    mstoVo.setCoutstockorgid(para.getStockorgid());
    // ����oid
    mstoVo.setCinventoryid(para.getCmaterialid());
    // ���������֯
    mstoVo.setCinfinanceorgid(para.getCinfinanceorgid());
    return mstoVo;
  }

  /**
   * ���������֯����������֯�ͽ��������֯һ�µ���UAP�ӿڻ�ȡ�ɱ���
   * 
   * @param m_stockOrg
   * @param l_eqList
   */
  private void setCostOrgByStockOrgsAndStordocs(String[] stockorgids,
      String[] stordocids, CostRegionPara[] paras) {
    // <key:�����֯id+�ֿ�id��valueΪ�ɱ���id>
    Map<String, String> m_CostRegion =
        CostRegionPubService.queryCostRegionIDByStockOrgsAndStordocs(
            stockorgids, stordocids);
    if (m_CostRegion == null || m_CostRegion.size() == 0) {
      return;
    }
    for (CostRegionPara para : paras) {
      String key = para.getStockorgid() + para.getStordocid();
      para.setCcostorgid(m_CostRegion.get(key));
    }

  }

  private void setCostOrgForSingle(CostRegionPara[] paras) {
    if (null == paras || paras.length == 0) {
      return;
    }
    // �����֯
    List<String> s_stockOrg = new ArrayList<String>();
    // �ֿ�
    List<String> s_storddoc = new ArrayList<String>();

    for (CostRegionPara para : paras) {
      // �����֯
      String stockOrg = para.getStockorgid();
      s_stockOrg.add(stockOrg);
      // �ֿ�
      String storddoc = para.getStordocid();
      s_storddoc.add(storddoc);
    }

    String[] stockorgids = s_stockOrg.toArray(new String[s_stockOrg.size()]);
    String[] stordocids = s_storddoc.toArray(new String[s_storddoc.size()]);
    this.setCostOrgByStockOrgsAndStordocs(stockorgids, stordocids, paras);

  }

  private List<CostRegionPara[]> filterSingleSpanData(CostRegionPara[] paras) {
    // ��ȡ�����֯��Ӧ�Ĳ�����֯
    Map<String, String> m_st_fin = this.queryFinanceOrgIDByStockOrgID(paras);

    List<CostRegionPara> l_single = new ArrayList<CostRegionPara>();
    List<CostRegionPara> l_span = new ArrayList<CostRegionPara>();
    // ���ֵ���֯������֯��������
    for (CostRegionPara para : paras) {
      String pkorg = para.getCinfinanceorgid();
      String sendstock = para.getStockorgid();
      // ����֯
      if (pkorg.equals(m_st_fin.get(sendstock))) {
        l_single.add(para);
      }
      // ����֯
      else {
        l_span.add(para);
      }
    }

    List<CostRegionPara[]> list = new ArrayList<CostRegionPara[]>();
    int size = l_single.size();
    if (size > 0) {
      list.add(l_single.toArray(new CostRegionPara[size]));
    }
    else {
      list.add(null);
    }
    size = l_span.size();
    if (size > 0) {
      list.add(l_span.toArray(new CostRegionPara[size]));
    }
    else {
      list.add(null);
    }

    return list;
  }

  private Map<String, String> queryFinanceOrgIDByStockOrgID(
      CostRegionPara[] paras) {
    Set<String> stockorgidset = new HashSet<String>();
    for (CostRegionPara para : paras) {
      stockorgidset.add(para.getStockorgid());
    }
    Map<String, String> m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(stockorgidset
            .toArray(new String[stockorgidset.size()]));
    // <���������֯,��Ӧ�Ĳ�����֯>
    return m_st_fin;
  }

}
