package nc.bs.so.m33.biz.m4453.bp.square.ia;

import java.util.List;

import nc.bs.so.m33.biz.m4453.rule.ia.ToIABizFor4453Rule;
import nc.bs.so.m33.biz.m4453.rule.ia.ToIACheckFor4453Rule;
import nc.bs.so.m33.maintain.m4453.InsertSquareWasDetailBP;
import nc.bs.so.m33.maintain.m4453.rule.detail.RewriteIACostFor4453Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m33.ref.ia.mi5.IAI5For4453ServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;

public class SquareIACostFor4453BP {

  public void square(List<SquareWasViewVO> list) {
    if (list == null || list.size() == 0) {
      return;
    }
    SquareWasViewVO[] sqvvos = list.toArray(new SquareWasViewVO[0]);
    this.square(sqvvos);
  }

  public void square(SquareWasViewVO[] sqvvos) {
    try {
      SquareWasVO[] sqvos = SquareWasVOUtils.getInstance().combineBill(sqvvos);

      AroundProcesser<SquareWasVO> processer =
          new AroundProcesser<SquareWasVO>(BPPlugInPoint.SquareToIABy4453);

      // ����ִ��ǰҵ�����
      this.addBeforeRule(processer);

      // ���۳��ⵥ���ɱ�����ǰִ��ҵ�����
      processer.before(sqvos);

      // �����ɱ����㵥VOת��Ϊ���ɱ�������ϸVO
      SquareWasDetailVO[] bills =
          SquareWasVOUtils.getInstance().changeSQVOtoSQDVOForIA(sqvos);

      this.saveDetail(sqvos, bills);

      // ����vo����
      I5BillVO[] i5vos =
          (I5BillVO[]) PfServiceScmUtil.executeVOChange(
              SOBillType.SquareWas.getCode(), IABillType.XSCBJZ.getCode(),
              sqvos);

      // ����4453�ı���ӿ�
      IAI5For4453ServicesUtil.insertI5ForSO4453Settle(i5vos);

      // ���۳��ⵥ���ɱ�����ǰִ��ҵ�����
      processer.after(sqvos);

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  private void addBeforeRule(AroundProcesser<SquareWasVO> processer) {
    // 2012-07-17 �¶�����㰲����ӱ� ̫ƽ���ƹ���Ŀ������ⵥ���ɱ����㲻���жϳɱ�����ر�
    // ;�𵥳ɱ�����ʱ�ж��Ƿ�Դͷ�����гɱ�����ر�
    // IRule<SquareWasVO> rule = new CheckBeforeCostSquareWasRule();
    // processer.addBeforeRule(rule);

    // ���ɱ���
    IRule<SquareWasVO> rule = new ToIACheckFor4453Rule();
    processer.addBeforeRule(rule);

    // �������������ҵ����
    rule = new ToIABizFor4453Rule();
    processer.addBeforeRule(rule);
  }

  private void saveDetail(SquareWasVO[] sqvos, SquareWasDetailVO[] bills) {
    AroundProcesser<SquareWasDetailVO> processer =
        new AroundProcesser<SquareWasDetailVO>(BPPlugInPoint.SquareToIABy4453Detail);

    // �ɱ�������ϸ����BP
    new InsertSquareWasDetailBP().insert(sqvos, bills);

    IRule<SquareWasDetailVO> rule = null;
    // ��д�ۼƳɱ���������
    rule = new RewriteIACostFor4453Rule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

}
