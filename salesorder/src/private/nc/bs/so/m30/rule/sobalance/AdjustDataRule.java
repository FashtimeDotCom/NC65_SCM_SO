package nc.bs.so.m30.rule.sobalance;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * ���ݱ����տ�����¶����տ�������
 * @scene
 * ���۶������汾���տ���ǰ
 * @param 
 * ��
 */
public class AdjustDataRule implements IRule<SoBalanceVO> {
  public AdjustDataRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    for (SoBalanceVO bill : vos) {
      this.fillupAdjustData(bill);
    }
  }

  private void fillupAdjustData(SoBalanceVO bill) {
    SoBalanceBVO[] bodyvos = bill.getChildrenVO();
    for (SoBalanceBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      // ɾ�����в�����������Ϣ
      if (vostatus != VOStatus.DELETED) {
        UFDouble norigordbalmny = bodyvo.getNorigordbalmny();
        UFDouble norigthisbalmny = bodyvo.getNorigthisbalmny();
        norigordbalmny =
            MathTool.add(norigordbalmny, norigthisbalmny);
        bodyvo.setNorigordbalmny(norigordbalmny);
        bodyvo.setNorigthisbalmny(null);
      }
    }

  }

}
