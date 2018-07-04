package nc.bs.so.salequotation.rule;


import nc.vo.pubapp.util.TimeUtils;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.vo.trade.checkrule.VOChecker;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۱��۵�����ǰ�������
 * @scene
 * ���۱��۵�����ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-11-11 ����04:40:31
 * @author ������
 */
public class FillDataBeforeRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    for(AggSalequotationHVO vo: vos) {
      this.setBodyDefValue(vo);
      this.setHeadDefValue(vo);
    }
  }
  
  private void setBodyDefValue(AggSalequotationHVO vo) {
    //���ñ�������������֯������
    SalequotationHVO hvo = vo.getParentVO();
    String orgid = hvo.getPk_org();
    String pk_group = hvo.getPk_group();
    SalequotationBVO[] bvos = vo.getChildrenVO();
    for(SalequotationBVO bvo : bvos) {      
      bvo.setPk_group(pk_group);
      bvo.setPk_org(orgid);
    }
    
    // Ϊ�к�Ϊ�յ��в����кš�
    VORowNoUtils.setVOsRowNoByRule(bvos, SalequotationBVO.CROWNO);
  }
  
  private void setHeadDefValue(AggSalequotationHVO vo) {
    SalequotationHVO hvo = vo.getParentVO();
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    //����
    if(VOChecker.isEmpty(hvo.getPk_group())) {
      hvo.setPk_group(pk_group);
    }
    //ʧЧ����
//    if(VOChecker.isEmpty(hvo.getDenddate())) {
//      hvo.setDenddate(TimeUtils.getStartDate(hvo.getDbilldate()));
//    }
//    // �ⲿƽ̨����ʱ��һ��������״̬
//    if (null == hvo.getFstatusflag()) {
//      hvo.setFstatusflag(BillStatus.FREE.getIntegerValue());
//    }
    
  }

}
