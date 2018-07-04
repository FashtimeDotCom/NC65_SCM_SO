package nc.bs.so.m33.biz.m4c.bp.square.ia;

import nc.bs.so.m33.biz.m4c.rule.toia.SquareIACloseFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toia.ToIABizFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toia.ToIACheckFor4CRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteIACostFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.itf.so.m33.ref.pcia.m4635.PCIA4635For4CServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;

public class SquareIACostFor4CBP {

  public void square(SquareOutVO[] sqvos) {
    try {
      AroundProcesser<SquareOutVO> processer =
          new AroundProcesser<SquareOutVO>(BPPlugInPoint.SquareToIABy4C);

      // ����ִ��ǰҵ�����
      this.addBeforeRule(processer);

      // ����ִ�к�ҵ�����
      this.addAfterRule(processer);

      // ���۳��ⵥ���ɱ�����ǰִ��ҵ�����
      processer.before(sqvos);

      // �����ɱ����㵥VOת��Ϊ���ɱ�������ϸVO
      SquareOutDetailVO[] bills =
          SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForIA(sqvos);

      this.saveDetail(sqvos, bills);

      // ����vo����
      I5BillVO[] i5vos =
          (I5BillVO[]) PfServiceScmUtil.executeVOChange(
              SOBillType.SquareOut.getCode(), IABillType.XSCBJZ.getCode(),
              sqvos);

      // ����I5�ı���ӿ�
      IAI5For4CServicesUtil.insertI5ForSO4CSettle(i5vos);

      // �����������Ĵ�����۳ɱ���ת���ı���ӿ� add by zhangby5 for 65
      PCIA4635For4CServicesUtil.insert4635ForSO4CSettle(sqvos);

      // ���۳��ⵥ���ɱ�����ǰִ��ҵ�����
      processer.after(sqvos);

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void addAfterRule(AroundProcesser<SquareOutVO> processer) {
    IRule<SquareOutVO> rule = null;
    rule = new SquareIACloseFor4CRule();
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

  private void saveDetail(SquareOutVO[] sqvos, SquareOutDetailVO[] bills) {
    AroundProcesser<SquareOutDetailVO> processer =
        new AroundProcesser<SquareOutDetailVO>(BPPlugInPoint.SquareToIABy4CDetail);
    IRule<SquareOutDetailVO> rule = null;

    // �ɱ�������ϸ����BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    // ��д�ۼ�Ӧ�ս�������
    rule = new RewriteIACostFor4CRule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

}
