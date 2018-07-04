package nc.bs.so.m33.biz.m4c.action.ar;

import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.rule.toar.FillNewChangeRateFor4CRule;
import nc.bs.so.m33.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;

public class ARIncomeFor4CAction {

  /**
   * Ӧ�ս���
   *
   * @param sqvos
   */
  public void execIncome(SquareOutVO[] svos) {
    // ����Ƿ���Դ�Ӧ��
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().filterIncomeableVO(svos);
    if (sqvos == null) {
      return;
    }

    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(ActionPlugInPoint.ARIncomeFor4C);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    TimeLog.logStart();
    processer.before(sqvos);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0011")/*@res "����ǰִ��ҵ�����"*/);

    // ��ȷ��Ӧ�մ���
    new SquareARIncomeFor4CBP().square(sqvos);
  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {

    // ���۳��ⵥӦ�ս���ǰ���ʴ���
    IRule<SquareOutVO> rule = new FillNewChangeRateFor4CRule();
    processer.addBeforeRule(rule);

  }
}