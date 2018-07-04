package nc.itf.so.m33.ref.pcia.m4635;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.so.m33.ref.to.settlerule.TOSettleRuleServicesUtil;
import nc.vo.pcto.settlerule.para.MatchSettleRuleParaVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;

/**
 * ���۽��㴫�������Ĵ������
 * 
 * @author ����
 *
 */
public class SquareTOPCIARule<E extends AbstractDataView> {

  private IMatchSettleRulePara settlerulepara;

  private E[] dataviews;

  public SquareTOPCIARule(
      IMatchSettleRulePara settlerulepara, E[] dataviews) {
    if (settlerulepara == null) {
      this.settlerulepara = new MatchSettleRuleParaImpl();
    }
    else {
      this.settlerulepara = settlerulepara;
    }
    this.dataviews = dataviews;
  }
  

  /**
   * ��ȡ�����Դ��������Ĵ����VO
   * 
   * @return ���������Ĵ����VO
   */
  public E[] geToPCIASquareVO() {
    List<E> dataviewlist = new ArrayList<E>();
    for (E dataview : dataviews) {
      if (isProfitcenter(dataview)) {
        dataviewlist.add(dataview);
      }
    }
 
    Map<MatchSettleRuleParaVO, UFBoolean> m_index_cr =
        this.getMatchSettleRuleResult(dataviewlist);
    
    List<E> retlist = new ArrayList<E>();
    for (E dataview : dataviews) {
      // ����������
      if(isProfitcenter(dataview)){
        MatchSettleRuleParaVO paravo =
            settlerulepara.getMatchSettleRuleParaVO(dataview);
        UFBoolean istoia = m_index_cr.get(paravo);
        if (istoia != null && istoia.booleanValue()) {
          retlist.add(dataview);
        }
      }
      //����������
      else{
        retlist.add(dataview);
      }
     
    }
    if(retlist==null || retlist.size()==0){
     return null;
    }
    ListToArrayTool<E>  tool=new ListToArrayTool<E>();
    return  tool.convertToArray(retlist);
  }
  
  private boolean isProfitcenter(E dataview){
    Object csprofitcenterid =
        dataview.getAttributeValue(SOItemKey.CSPROFITCENTERID);
    Object cprofitcenterid = dataview.getAttributeValue(SOItemKey.CPROFITCENTERID);
    // ����������
    if (!PubAppTool.isEqual(csprofitcenterid, cprofitcenterid)) {
     return true;
    }else{
      return false;
    }
  }
  

  private Map<MatchSettleRuleParaVO, UFBoolean> getMatchSettleRuleResult(List<E> dataviewlist) {
    if(dataviewlist.size()==0){
     return new HashMap<MatchSettleRuleParaVO, UFBoolean>();
    }
    // ׼����ȡ�ڲ�����������
    List<MatchSettleRuleParaVO> l_matchSettle =
        new ArrayList<MatchSettleRuleParaVO>();
    for (E view : dataviewlist) {
      l_matchSettle.add(settlerulepara.getMatchSettleRuleParaVO(view));
    }

    MatchSettleRuleParaVO[] matchSettleparas =
        l_matchSettle.toArray(new MatchSettleRuleParaVO[l_matchSettle.size()]);
    Map<MatchSettleRuleParaVO, UFBoolean> m_index_cr =
        TOSettleRuleServicesUtil.matchSettleRule(matchSettleparas);
    if (m_index_cr == null || m_index_cr.size() == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006010_0", "04006010-0154")/*
                                                                   * @res
                                                                   * "����֯����û��ƥ�䵽�ڲ����׽������"
                                                                   */);
    }
    return m_index_cr;
  }
}
