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
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * @description
 * ���۷�������������������ռ�ü��
 * @scene
 * ������:�������桢�޸ı��桢��ˡ�����ɾ��������
 * @param
 * billaction ���۹�������ť
 */
public class RenovateARByHidsEndRule implements IRule<DeliveryVO> {

  private M4331EngrossAction billaction;

  public RenovateARByHidsEndRule(M4331EngrossAction billaction) {
    this.billaction = billaction;
  }

  @Override
  public void process(DeliveryVO[] vos) {
    // �������ģ��δ���ã�������
    if (!SysInitGroupQuery.isCREDITEnabled()) {
      return;
    }
    List<String> alhids = new ArrayList<String>();
    Set<String> hsSettleOrgs = new HashSet<String>();
    for (DeliveryVO vo : vos) {
      alhids.add(vo.getParentVO().getCdeliveryid());
      for (DeliveryBVO bvo : vo.getChildrenVO()) {
        hsSettleOrgs.add(bvo.getCsettleorgid());
      }
    }
    String[] headIDs = alhids.toArray(new String[0]);
    String[] settleOrgs = hsSettleOrgs.toArray(new String[0]);

    M4331CreditPara para = new M4331CreditPara();
    para.setHeadIDs(headIDs);
    para.setBilltype(SOBillType.Delivery.getCode());
    para.setBillaction(this.billaction);
    para.setPk_org(settleOrgs);

    IEngrossCreditManagerForM4331 mange =
        NCLocator.getInstance().lookup(IEngrossCreditManagerForM4331.class);
    try {
      mange.renovateARByHidsEnd(para);
    }
    catch (BusinessException e) {

      ExceptionUtils.wrappException(e);
    }
  }
}
