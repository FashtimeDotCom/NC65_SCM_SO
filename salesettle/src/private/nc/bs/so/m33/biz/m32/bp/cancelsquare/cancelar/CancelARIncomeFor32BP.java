package nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar;

import java.util.List;

import nc.bs.so.m33.biz.m32.rule.toar.SquareAROpenFor32Rule;
import nc.bs.so.m33.maintain.m32.DeleteSquare32DetailBP;
import nc.bs.so.m33.maintain.m32.rule.detail.RewriteARIncomeFor32Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.pub.votools.SoVoTools;

public class CancelARIncomeFor32BP implements
ICancelSquareAction<SquareInvDetailVO> {

  @Override
  public void cancelSquare(List<SquareInvDetailVO> list) {

    if (list == null || list.size() == 0) {
      return;
    }

    SquareInvDetailVO[] vos = list.toArray(new SquareInvDetailVO[0]);

    AroundProcesser<SquareInvVO> processer =
        new AroundProcesser<SquareInvVO>(BPPlugInPoint.CancelARIncomeFor32BP);

    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);

    SquareInvVO[] svos =
        (SquareInvVO[]) BSContext.getInstance().getSession(
            SquareInvVO.class.getName());

    processer.before(svos);

    // ȡ��������ȷ��Ӧ��
    ArapServicesForSOUtil.delApBillBySOSquareDid(SoVoTools.getVOPKValues(vos));

    // ȡ��������ϸ
    this.delDetail(vos);

    processer.after(svos);
  }

  private void addAfterRule(AroundProcesser<SquareInvVO> processer) {
    IRule<SquareInvVO> rule = new SquareAROpenFor32Rule();
    processer.addAfterRule(rule);
  }

  private void delDetail(SquareInvDetailVO[] vos) {
    AroundProcesser<SquareInvDetailVO> processer =
        new AroundProcesser<SquareInvDetailVO>(
            BPPlugInPoint.CancelARIncomeFor32DetailBP);

    // ȡ��������ϸ
    new DeleteSquare32DetailBP().delete(vos);

    // ��д�ۼ�Ӧ�ս�������
    IRule<SquareInvDetailVO> rule = new RewriteARIncomeFor32Rule();
    processer.addAfterRule(rule);
    processer.after(vos);
  }

}
