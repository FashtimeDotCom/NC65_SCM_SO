package nc.bs.so.m30.rule.sobalance;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;

/**
 * @description
 * 1���Զ�ɾ������������Ϊ0�Ĳ�����������տ��ϵ
 * 2���Զ�ɾ��û���ӱ��¼�Ķ����տ������ϵ
 * @scene
 * ���۶��������տ������ϵǰ
 * @param 
 * ��
 */
public class AutoDeleteDataRule implements IRule<SoBalanceVO> {
  public AutoDeleteDataRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    for (SoBalanceVO bill : vos) {
      // ����ǲ�ȫVO������ʱ��Ҫ������״̬
      this.autoDeleteRow(bill);
      this.autoDeleteBill(bill);
    }
  }

  private void autoDeleteRow(SoBalanceVO bill) {
    SoBalanceBVO[] bodyvos = bill.getChildrenVO();
    for (SoBalanceBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      // 
      if (vostatus == VOStatus.DELETED) {
        continue;
      }
      int fibaltype = bodyvo.getFibaltype().intValue();
      if (SoBalanceType.SOBALANCE_FINBAL.getIntValue() == fibaltype) {
        UFDouble norigaccbalmny = bodyvo.getNorigaccbalmny();
        if ((norigaccbalmny == null)
            || MathTool.isZero(norigaccbalmny)) {
          bodyvo.setDr(Integer.valueOf(1));
          bodyvo.setStatus(VOStatus.DELETED);
        }
      }
    }
  }

  private void autoDeleteBill(SoBalanceVO bill) {
    SoBalanceHVO headvo = bill.getParentVO();
    SoBalanceBVO[] bodyvos = bill.getChildrenVO();
    boolean existrow = false;
    for (SoBalanceBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      // 
      if (vostatus != VOStatus.DELETED) {
        existrow = true;
      }
    }
    if (!existrow) {
      headvo.setDr(Integer.valueOf(1));
      headvo.setStatus(VOStatus.DELETED);
    }
  }

}
