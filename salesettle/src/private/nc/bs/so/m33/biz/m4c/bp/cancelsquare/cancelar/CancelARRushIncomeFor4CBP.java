package nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar;

import java.util.List;

import nc.bs.so.m33.maintain.m4c.DeleteSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteARRushIncomeFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

public class CancelARRushIncomeFor4CBP implements
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
        new AroundProcesser<SquareOutDetailVO>(
            BPPlugInPoint.CancelARRushIncomeFor4CBP);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    processer.before(rushVOs);

    // ȡ��������ȷ��Ӧ��
    ArapServicesForSOUtil.unEstVerify(AggVOUtil.getDistinctFieldArray(rushVOs,
        SquareOutDetailVO.PROCESSEID, String.class));

    // ȡ��������ϸ
    new DeleteSquareOutDetailBP().delete(rushVOs);

    processer.after(rushVOs);
  }

  private void addBeforeRule(AroundProcesser<SquareOutDetailVO> processer) {
    // ��д�ۼ�Ӧ�ս�������
    IRule<SquareOutDetailVO> rule = new RewriteARRushIncomeFor4CRule();
    processer.addBeforeRule(rule);
  }

}
