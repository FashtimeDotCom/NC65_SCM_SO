package nc.bs.so.m30.rule.sobalance;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * �����տ������ϵǰ�����������ݣ��������������֯��
 * @scene
 * ���۶��������տ������ϵǰ
 * @param 
 * ��
 */
public class FillupRedundanceDataRule implements IRule<SoBalanceVO> {
  public FillupRedundanceDataRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    for (SoBalanceVO bill : vos) {
      this.fillupRowByHead(bill);
    }
  }

  private void fillupRowByHead(SoBalanceVO bill) {
    // 
    SoBalanceHVO headvo = bill.getParentVO();

    // InvocationInfoProxy proxy = InvocationInfoProxy.getInstance();
    // String groupid = proxy.getGroupId();
    // String userId = proxy.getUserId();

    //
    //String pk = headvo.getPrimaryKey();

    String orgid = headvo.getPk_org();
    // UFDate billdate = headvo.getDbilldate();
    SoBalanceBVO[] bodyvos = bill.getChildrenVO();
    for (SoBalanceBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      // ɾ�����в�����������Ϣ
      if (vostatus != VOStatus.DELETED) {
        bodyvo.setPk_org(orgid);
        // bodyvo.setDbilldate(billdate);
      }
    }
  }

}
