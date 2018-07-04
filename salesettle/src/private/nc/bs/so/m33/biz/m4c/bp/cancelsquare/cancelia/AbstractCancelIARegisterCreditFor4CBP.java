package nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia;

import java.util.List;

import nc.bs.so.m33.maintain.m4c.DeleteSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteIARegsiterFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;

/**
 * ȡ�����۳�������㵥������Ʒ������ȡ������Գ�ʱ����
 * 
 * @since 6.0
 * @version 2011-7-20 ����04:52:50
 * @author zhangcheng
 */
public abstract class AbstractCancelIARegisterCreditFor4CBP implements
ICancelSquareAction<SquareOutDetailVO> {

  @Override
  public void cancelSquare(List<SquareOutDetailVO> list) {
    if (list == null || list.size() == 0) {
      return;
    }
    SquareOutDetailVO[] vos = list.toArray(new SquareOutDetailVO[0]);
    this.cancelSquare(vos);
  }

  public void cancelSquare(SquareOutDetailVO[] vos) {
    AroundProcesser<SquareOutDetailVO> processer =
        new AroundProcesser<SquareOutDetailVO>(
            BPPlugInPoint.CancelIARegisterCreditFor4C);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    processer.before(vos);

    // ȡ��������Ʒ����
    this.cancelIA(vos);

    // ȡ��������ϸ
    new DeleteSquareOutDetailBP().delete(vos);

    processer.after(vos);
  }

  /**
   * ȡ��������Ʒ����
   * 
   * @param vos
   */
  protected abstract void cancelIA(SquareOutDetailVO[] vos);

  private void addBeforeRule(AroundProcesser<SquareOutDetailVO> processer) {
    // ��д�ۼƷ�����Ʒ����
    IRule<SquareOutDetailVO> rule = new RewriteIARegsiterFor4CRule();
    processer.addAfterRule(rule);
  }

}
