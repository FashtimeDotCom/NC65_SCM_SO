package nc.bs.so.m33.biz.m4c.bp.square.ia;

import nc.bs.so.m33.biz.m4c.rule.toia.ToIABizFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toia.ToIACheckFor4CRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteIARegsiterFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;

public class SquareIARegisterDebitFor4CBP {

  public void square(SquareOutVO[] sqvos) {
    try {
      AroundProcesser<SquareOutDetailVO> afprocesser =
          new AroundProcesser<SquareOutDetailVO>(BPPlugInPoint.SquareToIARegisterDebitBy4C);

      AroundProcesser<SquareOutVO> processer =
          new AroundProcesser<SquareOutVO>(BPPlugInPoint.SquareToIARegisterDebitBy4CDetail);

      // ����ִ��ǰҵ�����
      this.addBeforeRule(processer);

      // ����ִ�к�ҵ�����
      this.addAfterRule(afprocesser);

      SquareOutDetailVO[] bills = this.storeDetail(sqvos);

      // ���۳��ⵥ��������Ʒǰִ��ҵ�����
      processer.before(sqvos);

      // ����vo����
      I5BillVO[] i5vos =
          (I5BillVO[]) PfServiceScmUtil.executeVOChange(
              SOBillType.SquareOut.getCode(), IABillType.XSCBJZ.getCode(),
              sqvos);

      // ����I5�����۳��ⵥ������Ʒ�ӿ�
      IAI5For4CServicesUtil.insertI5ForSO4CIntransit(i5vos);

      afprocesser.after(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void addAfterRule(AroundProcesser<SquareOutDetailVO> processer) {
    // ��д�ۼƳɱ���������
    IRule<SquareOutDetailVO> rule = new RewriteIARegsiterFor4CRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {
    // ���ɱ���
    IRule<SquareOutVO> rule = new ToIACheckFor4CRule();
    processer.addBeforeRule(rule);

    // �������������ҵ����
    rule = new ToIABizFor4CRule();
    processer.addBeforeRule(rule);
  }

  private SquareOutDetailVO[] storeDetail(SquareOutVO[] sqvos) {
    // ����������Ʒ���㵥VOת��Ϊ��������Ʒ������ϸVO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForREG(sqvos);

    // ������Ʒ��ϸ����BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    return bills;
  }

}
