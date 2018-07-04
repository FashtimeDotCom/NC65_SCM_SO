package nc.bs.so.m33.maintain.m32.rule.square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.bd.income.IncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvHVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.pub.biz.toia.ProcessToIA;
import nc.vo.so.m33.pub.biz.toia.ProcessToIAPara;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.reference.uap.bd.payterm.PaytermService;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.to.settlerule.TOSettleRuleServicesUtil;

import nc.pubitf.to.settlerule.ic.MatchSettleRuleResult;
import nc.pubitf.to.settlerule.so.MatchSettleRuleVOForSo;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۷�Ʊ����ǰĬ��ֵ������
 * @scene
 * ���۷�Ʊ����ǰ
 * @param
 * ��
 * @since 6.1
 * @version 2012-10-31 14:31:45
 * @author ��ӱ�
 */
public class InsSQ32DefaultDataRule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {

    SquareInvViewVO[] svvos =
        SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(vos);

    // �Ӷ�����������
    this.setDataFromSaleOrder(svvos);

    // �����Ƿ񴫳ɱ�
    this.setBCostFlag(svvos);

    // �����Ƿ�Ӧ��
    this.setARFlag(svvos);

    // ������Ч����
    this.setEffectdate(svvos);
  }

  private void setBCostFlag(SquareInvViewVO[] svvos) {
    // ���ֵ���֯����֯���ݲ��������������
    List<SquareInvViewVO[]> list = this.filterSingleSpanData(svvos);
    SquareInvViewVO[] singlevos = list.get(0);
    SquareInvViewVO[] spanlevos = list.get(1);

    // ���óɱ���:����֯
    this.setCostOrgForSingle(singlevos);

    // �����Ƿ񴫳ɱ�:����֯������֯����
    this.setBCostForPubFlag(svvos);

    // ���õ���֯�Ƿ񴫳ɱ�
    this.setBCostForSingleFlag(singlevos);

    // ���ÿ���֯�Ƿ񴫳ɱ�
    this.setBCostForSpanFlag(spanlevos);
  }

  private void setBCostForSingleFlag(SquareInvViewVO[] singlevos) {

    if (null == singlevos || singlevos.length == 0) {
      return;
    }
    // �ų��Ѿ����ò����ɱ�����
    List<SquareInvViewVO> l_single_cost = new ArrayList<SquareInvViewVO>();
    for (SquareInvViewVO view : singlevos) {
      if (view.getItem().getBcost().booleanValue()) {
        l_single_cost.add(view);
      }
    }
    if (l_single_cost.size() == 0) {
      return;
    }

    SquareInvViewVO[] svvos = new SquareInvViewVO[l_single_cost.size()];
    l_single_cost.toArray(svvos);

    ProcessToIAPara[] paras = this.getProcessToIAPara(svvos);
    ProcessToIA iaprc = new ProcessToIA();
    Map<String, UFBoolean> ret = iaprc.getSingleToIAResult(paras);
    int index = 0;
    for (SquareInvViewVO view : svvos) {
      UFBoolean flag = ret.get(String.valueOf(index));
      view.getItem().setBcost(flag);
      index++;
    }

  }

  private List<SquareInvViewVO[]> filterSingleSpanData(SquareInvViewVO[] svvos) {
    // ��ȡ�����֯��Ӧ�Ĳ�����֯
    Map<String, String> m_st_fin = this.queryFinanceOrgIDByStockOrgID(svvos);

    List<SquareInvViewVO> l_single = new ArrayList<SquareInvViewVO>();
    List<SquareInvViewVO> l_span = new ArrayList<SquareInvViewVO>();
    // ���ֵ���֯������֯��������
    for (SquareInvViewVO view : svvos) {
      String pkorg = view.getHead().getPk_org();
      String sendstock = view.getItem().getCsendstockorgid();
      boolean bfeediscount =
          view.getItem().getBlaborflag().booleanValue()
              || view.getItem().getBdiscountflag().booleanValue();
      // �����֯Ϊ��,�����ۿ�����
      if (PubAppTool.isNull(sendstock) || bfeediscount) {
        view.getItem().setBcost(UFBoolean.FALSE);
      }
      // ����֯
      else if (pkorg.equals(m_st_fin.get(sendstock))) {
        l_single.add(view);
      }
      // ����֯
      else {
        l_span.add(view);
      }
    }

    List<SquareInvViewVO[]> list = new ArrayList<SquareInvViewVO[]>();
    int size = l_single.size();
    if (size > 0) {
      list.add(l_single.toArray(new SquareInvViewVO[size]));
    }
    else {
      list.add(null);
    }
    size = l_span.size();
    if (size > 0) {
      list.add(l_span.toArray(new SquareInvViewVO[size]));
    }
    else {
      list.add(null);
    }

    return list;
  }

  private Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> getMatchSettleRuleResult(
      SquareInvViewVO[] svvos) {
    // ׼����ȡ�ڲ�����������
    List<MatchSettleRuleVOForSo> l_matchSettle =
        new ArrayList<MatchSettleRuleVOForSo>();
    for (SquareInvViewVO view : svvos) {
      MatchSettleRuleVOForSo mso =
          this.getMatchSettleRuleVOForSo(view.getHead(), view.getItem());
      l_matchSettle.add(mso);
    }

    // ��ȡ�ڲ��������
    Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr = null;
    try {
      m_index_cr = TOSettleRuleServicesUtil.matchSettlerule(l_matchSettle);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    if (m_index_cr == null || m_index_cr.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0036")/*@res "����֯����û��ƥ�䵽�ڲ����׽������"*/);
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
  private MatchSettleRuleVOForSo getMatchSettleRuleVOForSo(SquareInvHVO hvo,
      SquareInvBVO bvo) {
    MatchSettleRuleVOForSo mstoVo = new MatchSettleRuleVOForSo();
    mstoVo.setPk_group(hvo.getPk_group());
    // Դͷ������������
    mstoVo.setCtranstype(bvo.getVfirsttrantype());
    // ���������֯
    mstoVo.setCoutstockorgid(bvo.getCsendstockorgid());
    // ����oid
    mstoVo.setCinventoryid(bvo.getCmaterialid());
    // ���������֯
    mstoVo.setCinfinanceorgid(bvo.getPk_org());
    return mstoVo;
  }

  private ProcessToIAPara[] getProcessToIAPara(SquareInvViewVO[] svvos) {
    ProcessToIAPara[] paras = new ProcessToIAPara[svvos.length];
    int index = 0;
    for (SquareInvViewVO view : svvos) {
      SquareInvBVO bvo = view.getItem();
      // ���ⵥ��������
      String saleOutTransType = null;
      if (ICBillType.SaleOut.getCode().equals(bvo.getVsrctype())) {
        saleOutTransType = bvo.getVsrctrantype();
      }
      // ����
      String materialvid = bvo.getCmaterialvid();
      // �ֿ�
      String stordocid = bvo.getCsendstordocid();
      // ���������֯
      String pk_org = view.getHead().getPk_org();
      paras[index] = new ProcessToIAPara();
      paras[index].setId(String.valueOf(index));
      paras[index].setFinorgoid(pk_org);
      paras[index].setMaterialvid(materialvid);
      paras[index].setSaleOutTransType(saleOutTransType);
      paras[index].setStordocid(stordocid);
      paras[index].setBdiscountflag(ValueUtils.getBoolean(bvo
          .getBdiscountflag()));
      paras[index].setBlaborflag(ValueUtils.getBoolean(bvo.getBlaborflag()));
      index++;
    }
    return paras;
  }

  private Map<String, String> queryFinanceOrgIDByStockOrgID(
      SquareInvViewVO[] svvos) {
    // �������ݿ����֯ID��ȡ��Ӧ�Ĳ�����֯ID <�����֯�������֯�����Ĳ�����֯>
    SquareInvBVO[] bvos = SquareInvVOUtils.getInstance().getSquareInvBVO(svvos);
    Map<String, String> m_st_fin = null;
    m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(SoVoTools
            .getVOsOnlyValues(bvos, SquareInvBVO.CSENDSTOCKORGID));
    // <���������֯,��Ӧ�Ĳ�����֯>
    return m_st_fin;
  }

  private void setARFlag(SquareInvViewVO[] svvos) {
    for (SquareInvViewVO view : svvos) {
      boolean blar = view.getItem().getBlargessflag().booleanValue();
      view.getItem().setBincome(UFBoolean.valueOf(!blar));
    }
  }

  private void setBCostForPubFlag(SquareInvViewVO[] svvos) {
    if (!VOChecker.isEmpty(svvos)) {
      ProcessToIAPara[] paras = this.getProcessToIAPara(svvos);
      ProcessToIA iaprc = new ProcessToIA();
      Map<String, UFBoolean> ret = iaprc.getPubToIAResult(paras);
      int index = 0;
      for (SquareInvViewVO view : svvos) {
        UFBoolean flag = ret.get(String.valueOf(index));
        view.getItem().setBcost(flag);
        index++;
      }
    }
  }

  private void setBCostForSpanFlag(SquareInvViewVO[] spanvos) {

    if (null == spanvos || spanvos.length == 0) {
      return;
    }
    // �ų��Ѿ����ò����ɱ�����
    List<SquareInvViewVO> l_span_cost = new ArrayList<SquareInvViewVO>();
    for (SquareInvViewVO view : spanvos) {
      if (view.getItem().getBcost().booleanValue()) {
        l_span_cost.add(view);
      }
    }
    if (l_span_cost.size() == 0) {
      return;
    }

    SquareInvViewVO[] spanlevos = new SquareInvViewVO[l_span_cost.size()];
    l_span_cost.toArray(spanlevos);
    Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr =
        this.setCostOrgForSpan(spanlevos);
    if (VOChecker.isEmpty(m_index_cr)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0036")/*@res "����֯����û��ƥ�䵽�ڲ����׽������"*/);
    }

    if (!VOChecker.isEmpty(spanlevos)) {
      // �����Ƿ񴫴��
      for (SquareInvViewVO view : spanlevos) {
        MatchSettleRuleVOForSo para =
            this.getMatchSettleRuleVOForSo(view.getHead(), view.getItem());
        // �ڲ��������
        MatchSettleRuleResult mrlt = m_index_cr.get(para);
        // �����Ƿ���Դ����(�ɱ�������߷�����Ʒ)
        if (mrlt == null) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006010_0", "04006010-0036")/*@res "����֯����û��ƥ�䵽�ڲ����׽������"*/);
        }
        else {
          view.getItem().setBcost(mrlt.getSendtoia());
        }
      }
    }

  }

  private void setCostOrgByFinance(SquareInvViewVO[] svvos) {
    SquareInvBVO[] bvos = SquareInvVOUtils.getInstance().getSquareInvBVO(svvos);
    String[] financeorgids =
        SoVoTools.getVOsOnlyValues(bvos, SquareInvBVO.PK_ORG);
    Map<String, String> m_fico = null;
    m_fico =
        CostRegionPubService
            .getDefaultCostRegionMapByFinanceOrgIDS(financeorgids);

    if (m_fico != null) {
      for (SquareInvViewVO view : svvos) {
        view.getItem().setCcostorgid(m_fico.get(view.getHead().getPk_org()));
      }
    }
  }

  /**
   * ���������֯����������֯�ͽ��������֯��һ�µ���TO�ӿڻ�ȡ�ɱ���
   * 
   * @param l_matchSettle
   * @param l_unEqList
   */
  private void setCostOrgBySpan(
      Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr,
      SquareInvViewVO[] svvos) {
    // ͨ���ڲ����׽���������� �ɱ���
    List<SquareInvViewVO> l_ByFinanceList =
        this.setCostOrgByTOSettleRule(m_index_cr, svvos);

    // ͨ�����۽��������֯���� �ɱ���
    int size = l_ByFinanceList.size();
    if (size > 0) {
      this.setCostOrgByFinance(l_ByFinanceList
          .toArray(new SquareInvViewVO[size]));
    }
  }

  /**
   * ���������֯����������֯�ͽ��������֯һ�µ���UAP�ӿڻ�ȡ�ɱ���
   * 
   * @param m_stockOrg
   * @param l_eqList
   */
  private void setCostOrgByStockOrgsAndStordocs(String[] stockorgids,
      String[] stordocids, SquareInvViewVO[] svvos) {
    // <key:�����֯id+�ֿ�id��valueΪ�ɱ���id>
    Map<String, String> m_CostRegion =
        CostRegionPubService.queryCostRegionIDByStockOrgsAndStordocs(
            stockorgids, stordocids);
    if (m_CostRegion == null || m_CostRegion.size() == 0) {
      return;
    }
    SquareInvBVO bvo = null;
    for (SquareInvViewVO view : svvos) {
      bvo = view.getItem();
      String key = bvo.getCsendstockorgid() + bvo.getCsendstordocid();
      bvo.setCcostorgid(m_CostRegion.get(key));
    }

  }

  private List<SquareInvViewVO> setCostOrgByTOSettleRule(
      Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr,
      SquareInvViewVO[] svvos) {
    String costorgid = null;
    // ��Ҫͨ�����۽��������֯�ҳɱ����vo
    List<SquareInvViewVO> l_ByFinanceList = new ArrayList<SquareInvViewVO>();
    for (SquareInvViewVO view : svvos) {
      SquareInvBVO bvo = view.getItem();
      MatchSettleRuleVOForSo para =
          this.getMatchSettleRuleVOForSo(view.getHead(), bvo);
      // �ڲ��������
      MatchSettleRuleResult mrlt = m_index_cr.get(para);
      if (mrlt == null) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006010_0", "04006010-0036")/*@res "����֯����û��ƥ�䵽�ڲ����׽������"*/);
      }
      else {
        costorgid = mrlt.getCostRegion();
        // ���óɱ���
        if (costorgid == null) {
          l_ByFinanceList.add(view);
        }
        else {
          bvo.setCcostorgid(costorgid);
        }
      } // end else
    }
    return l_ByFinanceList;
  }

  private void setCostOrgForSingle(SquareInvViewVO[] singlevos) {
    if (null == singlevos || singlevos.length == 0) {
      return;
    }
    // �����֯
    List<String> s_stockOrg = new ArrayList<String>();
    // �ֿ�
    List<String> s_storddoc = new ArrayList<String>();

    for (SquareInvViewVO view : singlevos) {
      // �����֯
      String stockOrg = view.getItem().getCsendstockorgid();
      s_stockOrg.add(stockOrg);

      // �ֿ�
      String storddoc = view.getItem().getCsendstordocid();
      s_storddoc.add(storddoc);
    }

    String[] stockorgids = s_stockOrg.toArray(new String[s_stockOrg.size()]);
    String[] stordocids = s_storddoc.toArray(new String[s_storddoc.size()]);
    this.setCostOrgByStockOrgsAndStordocs(stockorgids, stordocids, singlevos);

  }

  private Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> setCostOrgForSpan(
      SquareInvViewVO[] svvos) {
    Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr =
        new HashMap<MatchSettleRuleVOForSo, MatchSettleRuleResult>();
    if (!VOChecker.isEmpty(svvos)) {
      // ��ȡ�ڲ��������
      m_index_cr = this.getMatchSettleRuleResult(svvos);
      this.setCostOrgBySpan(m_index_cr, svvos);
    }
    return m_index_cr;
  }

  private void setDataFromSaleOrder(SquareInvViewVO[] svvos) {
    String[] ordbids =
        SoVoTools.getVOsOnlyValues(SquareInvVOUtils.getInstance()
            .getSquareInvBVO(svvos), SquareInvBVO.CFIRSTBID);

    if (VOChecker.isEmpty(ordbids)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0037")/*@res "���ݴ������۷�Ʊû��Դͷ��Ϣ"*/);
    }

    Map<String, SaleOrderViewVO> map = new HashMap<String, SaleOrderViewVO>();
    try {
      SaleOrderViewVO[] views =
          SOSaleOrderServicesUtil.querySaleOrderViewVOs(ordbids, new String[] {
            SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.CPRODLINEID,
            SaleOrderHVO.CCHANNELTYPEID, SaleOrderBVO.VCTCODE
          });

      if (VOChecker.isEmpty(views)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006010_0", "04006010-0038")/*@res "���ݴ������۷�ƱԴͷӦ�������۶���"*/);
      }

      for (SaleOrderViewVO view : views) {
        map.put(view.getBody().getCsaleorderbid(), view);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    // cchanneltypeid cprolineid
    for (SquareInvViewVO view : svvos) {
      SaleOrderViewVO m30view = map.get(view.getItem().getCfirstbid());
      if (!VOChecker.isEmpty(m30view)) {
        SquareInvBVO bvo = view.getItem();
        bvo.setCchanneltypeid(m30view.getHead().getCchanneltypeid());
        bvo.setCprolineid(m30view.getBody().getCprodlineid());
        bvo.setVctcode(m30view.getBody().getVctcode());
      }
    }
  }

  private void setEffectdate(SquareInvViewVO[] svvos) {
    String[] paytermids =
        SoVoTools.getVOsOnlyValues(SquareInvVOUtils.getInstance()
            .getSquareInvHVO(svvos), SquareInvHVO.CPAYTERMID);
    Map<String, IncomeVO> map = null;
    if (!VOChecker.isEmpty(paytermids)) {
      map = PaytermService.queryIncomeByPk(paytermids);
    }
    if (VOChecker.isEmpty(map)) {
      // ���ո���Э�飬ֱ��ȡ��������
      this.setEffectdateWithNoPayterm(svvos);
    }
    else {
      this.setEffectdateWithPayterm(map, svvos);
    }
  }

  private void setEffectdateWithNoPayterm(SquareInvViewVO[] svvos) {
    for (SquareInvViewVO view : svvos) {
      view.getItem().setDeffectdate(view.getHead().getDbilldate());
    }
  }

  private void setEffectdateWithPayterm(Map<String, IncomeVO> map,
      SquareInvViewVO[] svvos) {
    for (SquareInvViewVO view : svvos) {
      IncomeVO pay = map.get(view.getHead().getCpaytermid());
      UFDate deffdate = pay.getEffectdate();
      view.getItem().setDeffectdate(deffdate);
    }
  }

}
