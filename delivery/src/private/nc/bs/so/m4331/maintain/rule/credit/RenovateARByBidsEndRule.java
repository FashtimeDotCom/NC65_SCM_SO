package nc.bs.so.m4331.maintain.rule.credit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.credit.engrossmaintain.m4331.IEngrossCreditManagerForM4331;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.credit.engrossmaintain.pub.para.M4331CreditPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * @description
 * ���۷������ж���������ռ�ü��
 * @scene
 * ���������йرա��д򿪡���4C��33����д���������޶��۸������
 * @param
 * billaction ���۹�������ť
 */
public class RenovateARByBidsEndRule implements IRule<DeliveryViewVO> {

  private M4331EngrossAction billaction;

  public RenovateARByBidsEndRule(M4331EngrossAction billaction) {
    this.billaction = billaction;
  }

  @Override
  public void process(DeliveryViewVO[] views) {
    // �������ģ��δ���ã�������
    if (!SysInitGroupQuery.isCREDITEnabled()) {
      return;
    }
    List<String> albids = new ArrayList<String>();
    Set<String> hsSettleOrgs = new HashSet<String>();
    for (DeliveryViewVO view : views) {
      albids.add(view.getItem().getCdeliverybid());
      hsSettleOrgs.add(view.getItem().getCsettleorgid());
    }
    String[] bodyIDs = albids.toArray(new String[0]);
    String[] settleOrgs = hsSettleOrgs.toArray(new String[0]);
    M4331CreditPara para = new M4331CreditPara();
    para.setBodyIDs(bodyIDs);
    para.setBilltype(SOBillType.Delivery.getCode());
    para.setBillaction(this.billaction);
    para.setPk_org(settleOrgs);
    IEngrossCreditManagerForM4331 mange =
        NCLocator.getInstance().lookup(IEngrossCreditManagerForM4331.class);
    try {
      mange.renovateARByBidsEnd(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
