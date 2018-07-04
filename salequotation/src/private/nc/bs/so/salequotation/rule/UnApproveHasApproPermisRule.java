package nc.bs.so.salequotation.rule;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.vo.scmpub.res.billtype.SOBillType;

import nc.vo.pubapp.pub.power.BillPowerChecker;

import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۱��۵���������û��Ƿ����������Ȩ��
 * @scene
 * ���۱��۵�����ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-11-7 ����04:00:11
 * @author ������
 */
public class UnApproveHasApproPermisRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    for(AggSalequotationHVO vo : vos){
      this.validateApproPermis(vo);
    }
  }
  
  private void validateApproPermis(AggSalequotationHVO vo){
    
    boolean approPermis = 
      BillPowerChecker.hasApproverPermission(vo, SOBillType.SaleQuotation.getCode());
    if(!approPermis){
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().
          getStrByID("4006002_0", "04006002-0176")/*@res "�����������Ȩ�ޡ�"*/);
    }    
  }
  
  
  

}
