package nc.bs.so.salequotation.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * @description
 * ���۱��۵��ύǰ���õ���״̬
 * @scene
 * ���۱��۵��ύǰ
 * @param
 * ��
 */
public class SalequoCommitStateChgRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    // TODO Auto-generated method stub
    if (vos == null) {
      return;
    }
    for (AggSalequotationHVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
      ((SalequotationHVO) vo.getParentVO()).setFstatusflag(Integer
          .valueOf(BillStatusEnum.C_AUDITING));
    }
  }

}
