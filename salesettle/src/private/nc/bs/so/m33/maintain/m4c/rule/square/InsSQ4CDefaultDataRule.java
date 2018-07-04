package nc.bs.so.m33.maintain.m4c.rule.square;

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
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.biz.toia.ProcessToIA;
import nc.vo.so.m33.pub.biz.toia.ProcessToIAPara;
import nc.vo.so.paravo.Para4CFor32SignBiz;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.reference.uap.bd.payterm.PaytermService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.to.settlerule.TOSettleRuleServicesUtil;

import nc.pubitf.so.ic.m4c.ISaleFor4CParaQuery;
import nc.pubitf.to.settlerule.ic.MatchSettleRuleResult;
import nc.pubitf.to.settlerule.so.MatchSettleRuleVOForSo;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۳��ⵥ����Ĭ��ֵ������
 * @scene
 * ���۳��ⵥ���㱣��ǰ
 * @param
 * ��
 * @since 6.1
 * @version 2012-10-31 14:34:50
 * @author ��ӱ�
 */
public class InsSQ4CDefaultDataRule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {

    SquareOutViewVO[] svvos =
        SquareOutVOUtils.getInstance().changeToSaleSquareViewVO(vos);

    // �Ӷ�����������
    this.setDataFromSaleOrder(svvos);

    // �����Ƿ����ǩ�տ�Ʊ��־
    this.setBreturnoutstockFlag(svvos);

    // �����Ƿ񴫳ɱ�
    this.setBCostFlag(svvos);

    // �����Ƿ�Ӧ��
    this.setARFlag(svvos);

    // ������Ч����
    this.setEffectdate(svvos);
  }

  private void filterSingleSpanData(List<SquareOutViewVO> l_single,
      List<SquareOutViewVO> l_span, SquareOutViewVO[] svvos) {
    // ��ȡ�����֯��Ӧ�Ĳ�����֯
    Map<String, String> m_st_fin = this.queryFinanceOrgIDByStockOrgID(svvos);

    // ���ֵ���֯������֯��������
    for (SquareOutViewVO view : svvos) {
      String pkorg = view.getHead().getPk_org();
      String sendstock = view.getHead().getCsendstockorgid();
      // ����֯
      if (pkorg.equals(m_st_fin.get(sendstock))) {
        l_single.add(view);
      }
      // ����֯
      else {
        l_span.add(view);
      }
    }
  }

  private Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> getMatchSettleRuleResult(
      SquareOutViewVO[] svvos) {
    // ׼����ȡ�ڲ�����������
    List<MatchSettleRuleVOForSo> l_matchSettle =
        new ArrayList<MatchSettleRuleVOForSo>();
    for (SquareOutViewVO view : svvos) {
      // �ڲ��������ӿڲ���VO
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

    if (m_index_cr == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006010_0", "04006010-0036")
      /*@res "����֯����û��ƥ�䵽�ڲ����׽������"*/);
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
  private MatchSettleRuleVOForSo getMatchSettleRuleVOForSo(SquareOutHVO hvo,
      SquareOutBVO bvo) {
    MatchSettleRuleVOForSo mstoVo = new MatchSettleRuleVOForSo();
    mstoVo.setPk_group(hvo.getPk_group());
    // Դͷ������������
    mstoVo.setCtranstype(bvo.getVfirsttrantype());
    // ���������֯
    mstoVo.setCoutstockorgid(hvo.getCsendstockorgid());
    // ����oid
    mstoVo.setCinventoryid(bvo.getCmaterialid());
    // ���������֯
    mstoVo.setCinfinanceorgid(bvo.getPk_org());
    return mstoVo;
  }

  private ProcessToIAPara[] getProcessToIAPara(SquareOutViewVO[] svvos) {
    ProcessToIAPara[] paras = new ProcessToIAPara[svvos.length];
    int index = 0;
    for (SquareOutViewVO view : svvos) {
      SquareOutBVO bvo = view.getItem();
      // ���ⵥ��������
      String saleOutTransType = view.getHead().getCtrantypeid();
      /*yixl 2013-03-18�޸� �� ���VsrctypeΪ4C�ᵼ���õ������ͱ���(4C)
       *��ic_transtype������ctrantypeid='4C'��������ѯ��¼
       * if (ICBillType.SaleOut.getCode().equals(bvo.getVsrctype())) {
        saleOutTransType = bvo.getVsrctrantype();
      }*/
      // ����
      String materialvid = bvo.getCmaterialvid();
      // �ֿ�
      String stordocid = view.getHead().getCsendstordocid();
      // ���������֯
      String pk_org = view.getHead().getPk_org();
      paras[index] = new ProcessToIAPara();
      paras[index].setId(String.valueOf(index));
      paras[index].setFinorgoid(pk_org);
      paras[index].setMaterialvid(materialvid);
      paras[index].setSaleOutTransType(saleOutTransType);
      paras[index].setStordocid(stordocid);
      index++;
    }
    return paras;
  }

  private Map<String, String> queryFinanceOrgIDByStockOrgID(
      SquareOutViewVO[] svvos) {
    // �������ݿ����֯ID��ȡ��Ӧ�Ĳ�����֯ID <�����֯�������֯�����Ĳ�����֯>
    SquareOutHVO[] hvos = SquareOutVOUtils.getInstance().getSquareOutHVO(svvos);
    Map<String, String> m_st_fin = null;
    m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(SoVoTools
            .getVOsOnlyValues(hvos, SquareOutHVO.CSENDSTOCKORGID));
    // <���������֯,��Ӧ�Ĳ�����֯>
    return m_st_fin;
  }

  private void setARFlag(SquareOutViewVO[] svvos) {
    for (SquareOutViewVO view : svvos) {
      boolean blar =
          view.getItem().getBlargessflag() == null ? false : view.getItem()
              .getBlargessflag().booleanValue();
      view.getItem().setBincome(UFBoolean.valueOf(!blar));
    }
  }

  private void setBCostFlag(SquareOutViewVO[] svvos) {
    // ���ֵ���֯����֯����
    List<SquareOutViewVO> l_single = new ArrayList<SquareOutViewVO>();
    List<SquareOutViewVO> l_span = new ArrayList<SquareOutViewVO>();
    this.filterSingleSpanData(l_single, l_span, svvos);

    // ���õ���֯������֯�����Ƿ񴫳ɱ�����
    this.setBCostForPubFlag(svvos);
    // ���õ���֯�Ƿ񴫳ɱ�
    this.setBCostForSingleFlag(l_single);
    // ���ÿ���֯�Ƿ񴫳ɱ�
    this.setBCostForSpanFlag(l_span);

  }

  private void setBCostForSingleFlag(List<SquareOutViewVO> l_single) {
    // �ų��Ѿ����ò����ɱ�����
    List<SquareOutViewVO> l_single_cost = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : l_single) {
      if (view.getItem().getBcost().booleanValue()) {
        l_single_cost.add(view);
      }
    }
    if (l_single_cost.size() == 0) {
      return;
    }

    SquareOutViewVO[] svvos = new SquareOutViewVO[l_single_cost.size()];
    l_single_cost.toArray(svvos);

    ProcessToIAPara[] paras = this.getProcessToIAPara(svvos);
    ProcessToIA iaprc = new ProcessToIA();
    Map<String, UFBoolean> ret = iaprc.getSingleToIAResult(paras);
    int index = 0;
    for (SquareOutViewVO view : svvos) {
      UFBoolean flag = ret.get(String.valueOf(index));
      view.getItem().setBcost(flag);
      index++;
    }
  }

  private void setBCostForPubFlag(SquareOutViewVO[] svvos) {
    if (!VOChecker.isEmpty(svvos)) {
      ProcessToIAPara[] paras = this.getProcessToIAPara(svvos);
      ProcessToIA iaprc = new ProcessToIA();
      Map<String, UFBoolean> ret = iaprc.getPubToIAResult(paras);
      int index = 0;
      for (SquareOutViewVO view : svvos) {
        UFBoolean flag = ret.get(String.valueOf(index));
        view.getItem().setBcost(flag);
        index++;
      }
    }
  }

  private void setBCostForSpanFlag(List<SquareOutViewVO> l_span) {

    // �ų��Ѿ����ò����ɱ�����
    List<SquareOutViewVO> l_span_cost = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : l_span) {
      if (view.getItem().getBcost().booleanValue()) {
        l_span_cost.add(view);
      }
    }
    if (l_span_cost.size() == 0) {
      return;
    }

    SquareOutViewVO[] svvos = new SquareOutViewVO[l_span_cost.size()];
    l_span_cost.toArray(svvos);

    Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr =
        this.getMatchSettleRuleResult(svvos);

    // �����Ƿ񴫴��
    for (SquareOutViewVO view : svvos) {
      MatchSettleRuleVOForSo para =
          this.getMatchSettleRuleVOForSo(view.getHead(), view.getItem());
      // �ڲ��������
      MatchSettleRuleResult mrlt = m_index_cr.get(para);
      // �����Ƿ���Դ����(�ɱ�������߷�����Ʒ)
      if (mrlt == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006010_0", "04006010-0036")
        /*@res "����֯����û��ƥ�䵽�ڲ����׽������"*/);
      }
      else {
        view.getItem().setBcost(mrlt.getSendtoia());
      }
    }

  }

  /**
   * �����Ƿ����ǩ�տ�Ʊ��־
   * 
   * @param view
   */
  private void setBreturnoutstockFlag(SquareOutViewVO[] view) {
    SquareOutVO[] svos = SquareOutVOUtils.getInstance().combineBill(view);
    Para4CFor32SignBiz[] paras = new Para4CFor32SignBiz[svos.length];
    int i = 0;
    for (SquareOutVO svo : svos) {
      String cbiztypeid = svo.getParentVO().getCbiztypeid();
      // ���۳��ⵥ������֯�ڱ�ͷ���������۳��ⵥ�����㵥����������֯һ��
      // TODO �����޸�ԭģ�ͽ����۳��ⵥ�����㵥������֯������ͷ
      String csaleorgid = svo.getChildrenVO()[0].getCsaleorgid();
      paras[i] = new Para4CFor32SignBiz(cbiztypeid, csaleorgid);
      i++;
    }
    ISaleFor4CParaQuery pqs =
        NCLocator.getInstance().lookup(ISaleFor4CParaQuery.class);
    Para4CFor32SignBiz[] rets = null;
    try {
      rets = pqs.querySignNumBusitype(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    this.setBreturnoutstockFlagByPara(svos, rets);

  }

  private void setBreturnoutstockFlagByPara(SquareOutVO[] svos,
      Para4CFor32SignBiz[] rets) {
    if (VOChecker.isEmpty(rets)) {
      for (SquareOutVO svo : svos) {
        svo.getParentVO().setBreturnoutstock(UFBoolean.FALSE);
      }
    }
    else {
      Map<String, UFBoolean> mpara = new HashMap<String, UFBoolean>();
      for (Para4CFor32SignBiz para : rets) {
        mpara.put(para.getPk_org() + para.getCbizid(), para.getIsSign());
      }
      for (SquareOutVO svo : svos) {
        SquareOutHVO hvo = svo.getParentVO();
        String key =
            svo.getChildrenVO()[0].getCsaleorgid()
                + svo.getParentVO().getCbiztypeid();
        UFBoolean isSignflag = mpara.get(key);
        UFBoolean isReturnflag =
            ValueUtils.getUFBoolean(hvo.getBreturnoutstock());
        hvo.setBreturnoutstock(UFBoolean.valueOf(isSignflag.booleanValue()
            && isReturnflag.booleanValue()));
      }
    }
  }

  private void setDataFromSaleOrder(SquareOutViewVO[] svvos) {
    String[] ordbids =
        SoVoTools.getVOsOnlyValues(SquareOutVOUtils.getInstance()
            .getSquareOutBVO(svvos), SquareOutBVO.CFIRSTBID);

    Map<String, SaleOrderViewVO> map = new HashMap<String, SaleOrderViewVO>();
    try {
      SaleOrderViewVO[] views =
          SOSaleOrderServicesUtil.querySaleOrderViewVOs(ordbids, new String[] {
            SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.CPRODLINEID,
            SaleOrderHVO.CCHANNELTYPEID, SaleOrderHVO.CPAYTERMID,
            SaleOrderHVO.CCUSTBANKACCID, SaleOrderBVO.VCTCODE
          });
      for (SaleOrderViewVO view : views) {
        map.put(view.getBody().getCsaleorderbid(), view);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    // cchanneltypeid cprolineid
    for (SquareOutViewVO view : svvos) {
      SaleOrderViewVO m30view = map.get(view.getItem().getCfirstbid());
      if (!VOChecker.isEmpty(m30view)) {
        SquareOutBVO bvo = view.getItem();
        bvo.setCchanneltypeid(m30view.getHead().getCchanneltypeid());
        bvo.setCprolineid(m30view.getBody().getCprodlineid());
        bvo.setCpaytermid(m30view.getHead().getCpaytermid());
        bvo.setCcustbankaccid(m30view.getHead().getCcustbankaccid());
        bvo.setVctcode(m30view.getBody().getVctcode());
      }
    }
  }

  private void setEffectdate(SquareOutViewVO[] svvos) {
    String[] paytermids =
        SoVoTools.getVOsOnlyValues(SquareOutVOUtils.getInstance()
            .getSquareOutBVO(svvos), SquareOutBVO.CPAYTERMID);
    Map<String, IncomeVO> map = null;
    if (!VOChecker.isEmpty(paytermids)) {
      map = PaytermService.queryIncomeByPk(paytermids);
    }
    // ����Ӧ�յ���Ч����
    this.setEffectdateWithPayterm(map, svvos);
  }

  private void setEffectdateWithPayterm(Map<String, IncomeVO> map,
      SquareOutViewVO[] svvos) {
    for (SquareOutViewVO view : svvos) {
      String cpaytermid = view.getItem().getCpaytermid();
      // ���ո���Э�飬ֱ��ȡ��������.���۳��ⵥ��Դ��������۶���ʱ���еĿ���û���տ�Э�顣
      if (PubAppTool.isNull(cpaytermid)) {
        view.getItem().setDeffectdate(view.getHead().getDbilldate());
      }
      else {
        IncomeVO pay = map.get(cpaytermid);
        UFDate deffdate = pay.getEffectdate();
        view.getItem().setDeffectdate(deffdate);
      }
    }
  }

}
