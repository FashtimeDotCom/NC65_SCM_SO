package nc.bs.so.m33.biz.m32.action.ar;

import nc.bs.so.m33.biz.m32.bp.square.toar.SquareARIncomeFor32BP;
import nc.bs.so.m33.biz.m32.rule.toar.AdjustIncomeFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toar.CheckADIncomeDataRule;
import nc.bs.so.m33.biz.m32.rule.toar.FillNewChangeRateFor32Rule;
import nc.bs.so.m33.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ��Ӧ�ն���
 * ���γ��ⵥ���������Զ�Ӧ�ս��㣬���۷�Ʊ���ݵ��۵Ĳ��촫���ȷ��Ӧ��
 * 
 * @author zhangcheng
 * 
 */
public class ADIncomeFor32Action {

  public void execIncome(SquareInvVO[] vos) {
    AroundProcesser<SquareInvVO> processer =
        new AroundProcesser<SquareInvVO>(ActionPlugInPoint.ADIncomeFor32);

    // ����ִ��ǰҵ�����
    SquareInvVO[] filtervos = this.addBeforeRule(processer, vos);
    if (VOChecker.isEmpty(filtervos)) {
      return;
    }

    // �����ȷ��Ӧ�մ���
    new SquareARIncomeFor32BP().square(filtervos);
  }

  private SquareInvVO[] addBeforeRule(AroundProcesser<SquareInvVO> processer,
      SquareInvVO[] vos) {
    // ��Ӧ��ҵ��У��
    IRule<SquareInvVO> rule = new CheckADIncomeDataRule();
    processer.addBeforeRule(rule);

    // ���˲�Ӧ������
    AdjustIncomeFor32Rule filterrule = new AdjustIncomeFor32Rule();
    SquareInvVO[] filtervos = filterrule.process(vos);
    if (VOChecker.isEmpty(filtervos)) {
      return null;
    }

    // ���۷�ƱӦ�ս���ǰ���ʴ���
    rule = new FillNewChangeRateFor32Rule();
    processer.addBeforeRule(rule);
    processer.before(filtervos);

    return filtervos;
  }

}
