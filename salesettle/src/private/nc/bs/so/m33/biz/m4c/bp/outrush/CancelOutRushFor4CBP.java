package nc.bs.so.m33.biz.m4c.bp.outrush;

import java.util.List;

import nc.bs.so.m33.maintain.m4c.DeleteSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteOutRush4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.trade.checkrule.VOChecker;

public class CancelOutRushFor4CBP implements
    ICancelSquareAction<SquareOutDetailVO> {

  @Override
  public void cancelSquare(List<SquareOutDetailVO> list) {
    SquareOutDetailVO[] vos = list.toArray(new SquareOutDetailVO[0]);
    this.cancelSquare(vos);
  }

  public void cancelSquare(SquareOutDetailVO[] rushVOs) {
    if (VOChecker.isEmpty(rushVOs)) {
      return;
    }

    AroundProcesser<SquareOutDetailVO> processer =
        new AroundProcesser<SquareOutDetailVO>(BPPlugInPoint.CancelOutRush);
    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    processer.before(rushVOs);

    // ȡ��������ϸ
    new DeleteSquareOutDetailBP().delete(rushVOs);

    processer.after(rushVOs);
  }

  private void addBeforeRule(AroundProcesser<SquareOutDetailVO> processer) {
    // ��д�ۼƳ���Գ�����
    IRule<SquareOutDetailVO> rule = new RewriteOutRush4CRule();
    processer.addBeforeRule(rule);
  }

}
