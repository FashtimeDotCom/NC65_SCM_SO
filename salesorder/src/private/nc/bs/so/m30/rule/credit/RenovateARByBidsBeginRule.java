package nc.bs.so.m30.rule.credit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.credit.engrossmaintain.pub.para.M30CreditPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.pubitf.credit.engrossmaintain.m30.IEngrossCreditManagerForM30;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۶��������򿪡������رա�����򿪡�����رա�����򿪡�����رա���4453��4C��4331��33��ǰ��������
 * @scene
 * ���۶��������򿪡������رա�����򿪡�����رա�����򿪡�����ر�
 * @param 
 * billaction ���۹�����Action(���۶��������򿪡������رա�����򿪡�����رա�����򿪡�����رն���)
 * @since 6.1
 * @version 2013-05-22 14:21:15
 * @author yixl
 */
public class RenovateARByBidsBeginRule implements IRule<SaleOrderViewVO> {

  private M30EngrossAction billaction;

  /**
   * ������
   * 
   * @param billaction
   */
  public RenovateARByBidsBeginRule(M30EngrossAction billaction) {
    this.billaction = billaction;
  }

  @Override
  public void process(SaleOrderViewVO[] views) {
    if (!SysInitGroupQuery.isCREDITEnabled()) {
      return;
    }
    List<String> albids = new ArrayList<String>();
    Set<String> hsSettleOrgs = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      albids.add(view.getBody().getCsaleorderbid());
      hsSettleOrgs.add(view.getBody().getCsettleorgid());
    }
    String[] bodyIDs = albids.toArray(new String[0]);
    String[] settleOrgs = hsSettleOrgs.toArray(new String[0]);

    M30CreditPara para = new M30CreditPara();
    para.setBodyIDs(bodyIDs);
    para.setBilltype(SOBillType.Order.getCode());
    para.setBillaction(this.billaction);
    para.setPk_org(settleOrgs);

    IEngrossCreditManagerForM30 mange =
        NCLocator.getInstance().lookup(IEngrossCreditManagerForM30.class);
    try {
      mange.renovateARByBidsBegin(para);
    }
    catch (BusinessException e) {

      ExceptionUtils.wrappException(e);
    }
  }
}
