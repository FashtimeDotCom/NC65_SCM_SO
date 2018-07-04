package nc.bs.so.m30.rule.sobalance;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * �����տ������ϵ�������տ���ǰ��1��������ϵ��ͷ���������տ�����������Ѻ������ļ���Ͳ�ȫ����
 * @scene
 * ���۶��������տ������ϵ�������տ���ǰ
 * @param 
 * ��
 */
public class CalculateHeadSumDataRule implements IRule<SoBalanceVO> {
  public CalculateHeadSumDataRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    for (SoBalanceVO bill : vos) {
      // ����ǲ�ȫVO������ʱ��Ҫ������״̬
      this.calculateheadsumdata(bill);
    }
  }

  private void calculateheadsumdata(SoBalanceVO bill) {
    SoBalanceBVO[] bodyvos = bill.getChildrenVO();
    UFDouble ntotalpaymny = UFDouble.ZERO_DBL;
    UFDouble ntotalorigbalmny = UFDouble.ZERO_DBL;
    for (SoBalanceBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      if (vostatus != VOStatus.DELETED) {
        UFDouble norigordbalmny = bodyvo.getNorigordbalmny();
        UFDouble norigaccbalmny = bodyvo.getNorigaccbalmny();
        ntotalpaymny = MathTool.add(ntotalpaymny, norigordbalmny);
        ntotalorigbalmny =
            MathTool.add(ntotalorigbalmny, norigaccbalmny);
      }
    }
    SoBalanceHVO headvo = bill.getParentVO();
    headvo.setNtotalpaymny(ntotalpaymny);
    headvo.setNtotalorigbalmny(ntotalorigbalmny);
    headvo.setStatus(VOStatus.UPDATED);
  }

}
