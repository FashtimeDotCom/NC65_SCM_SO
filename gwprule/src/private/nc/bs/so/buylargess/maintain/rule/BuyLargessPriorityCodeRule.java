package nc.bs.so.buylargess.maintain.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.para.IPriorityCode;
import nc.vo.pub.VOStatus;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.so.pub.para.CustBaseclPriorityCode;
import nc.vo.so.pub.para.CustSaleclPriorityCode;
import nc.vo.so.pub.para.MarBaseclPriorityCode;
import nc.vo.so.pub.para.MarSaleclPriorityCode;
import nc.vo.so.pub.para.SaleOrgPriorityCode;
import nc.vo.so.pub.para.SinglePriorityCode;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.so.pub.util.PriorityCodeGenUtil;

/**
 * @description
 * �����������������ɹ���
 * @scene
 * �������� ����ǰ����
 * @param
 * ��
 * @since 6.3
 */
public class BuyLargessPriorityCodeRule implements IRule<BuyLargessVO> {

  @Override
  public void process(BuyLargessVO[] vos) {
    // ���������ɹ��� ������֯ (00) + ����(0) + ���Ϸ���(00) + �ͻ�(0) + �ͻ�����
    String pk_group = BSContext.getInstance().getGroupID();
    boolean ismarbase = BaseSaleClassUtil.isMarBaseClass(pk_group);
    boolean iscustbase = BaseSaleClassUtil.isCustBaseClass(pk_group);

    for (BuyLargessVO larvo : vos) {
      BuyLargessHVO head = larvo.getParentVO();
      if (VOStatus.UNCHANGED == head.getStatus()) {
        continue;
      }
      IPriorityCode[] pricodeitems =
          this.getPriorityCodeItems(head, ismarbase, iscustbase);
      String pricode = PriorityCodeGenUtil.genPriorityCode(pricodeitems);
      head.setCprioritycode(pricode);
    }
  }

  public IPriorityCode[] getPriorityCodeItems(BuyLargessHVO head,
      boolean ismarbase, boolean iscustbase) {
    IPriorityCode[] codeitems = new IPriorityCode[5];
    // ������֯
    String pk_org = head.getPk_org();
    String pk_group = BSContext.getInstance().getGroupID();
    codeitems[0] = new SaleOrgPriorityCode(pk_org, pk_group);
    // ����
    codeitems[1] = new SinglePriorityCode(head.getCbuymarid());
    // ���Ϸ���
    if (ismarbase) {
      codeitems[2] =
          new MarBaseclPriorityCode(head.getPk_marbasclass(), pk_org);
    }
    else {
      codeitems[2] =
          new MarSaleclPriorityCode(head.getPk_marsaleclass(), pk_org);
    }
    // �ͻ�
    codeitems[3] = new SinglePriorityCode(head.getPk_customer());
    // �ͻ�����
    if (iscustbase) {
      codeitems[4] = new CustBaseclPriorityCode(head.getPk_custclass(), pk_org);
    }
    else {
      codeitems[4] =
          new CustSaleclPriorityCode(head.getPk_custsaleclass(), pk_org);
    }
    return codeitems;

  }
}
