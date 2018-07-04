package nc.bs.so.salequotation.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * @description
 * ���۱��۵�����ǰ�����ͷ:����������˰�ϼ�
 * @scene
 * ���۱��۵��������޸ı���ǰ
 * @param
 * ��
 */
public class FillHeadSumDataRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    for (AggSalequotationHVO vo : vos) {
      this.sumOneBill(vo);
    }
  }

  private void sumOneBill(AggSalequotationHVO bill) {
    SalequotationHVO header = bill.getParentVO();
    UFDouble tastnum = UFDouble.ZERO_DBL;
    UFDouble ttaxmny = UFDouble.ZERO_DBL;

    SalequotationBVO[] items = bill.getChildrenVO();
    for (SalequotationBVO item : items) {
      int vostatus = item.getStatus();
      if (vostatus == VOStatus.DELETED) {
        continue;
      }
      UFDouble astnum = item.getNassistnum();
      if (astnum != null) {
        tastnum = tastnum.add(astnum);
      }
      // ��Ʒ�н��ڱ�ͷ����
      UFBoolean largessflag = item.getBlargessflag();
      if (largessflag != null && largessflag.booleanValue()) {
        continue;
      }
      ttaxmny = MathTool.add(ttaxmny, item.getNorigtaxmny());
    }

    header.setNtotalnum(tastnum);
    header.setNtotalmny(ttaxmny);
  }
}
