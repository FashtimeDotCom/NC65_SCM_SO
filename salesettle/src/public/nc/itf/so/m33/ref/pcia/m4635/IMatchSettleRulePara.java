package nc.itf.so.m33.ref.pcia.m4635;


import nc.vo.pcto.settlerule.para.MatchSettleRuleParaVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
/**
 * 
 * ƥ���ڲ���������������ӿ�<br/>
 * 
 * ���ó�����ƥ���ڲ�����������ѯ����ʱ����
 * 
 * @author ����
 *
 */
public interface IMatchSettleRulePara {
  
  /**
   * ƥ���ڲ���������������ӿ�
   * 
   * @param dataview ��ͼVO
   * @return ƥ���������Ľ���������VO 
   */
  MatchSettleRuleParaVO getMatchSettleRuleParaVO(AbstractDataView  dataview);

}
