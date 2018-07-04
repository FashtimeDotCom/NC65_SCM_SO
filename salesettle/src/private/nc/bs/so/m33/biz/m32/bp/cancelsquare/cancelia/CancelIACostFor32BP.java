package nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelia;

import java.util.List;

import nc.bs.so.m33.biz.m32.rule.toia.SquareIAOpenFor32Rule;
import nc.bs.so.m33.maintain.m32.DeleteSquare32DetailBP;
import nc.bs.so.m33.maintain.m32.rule.detail.RewriteIACostFor32Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.itf.so.m33.ref.ia.mi5.IAI5For32ServicesUtil;
import nc.itf.so.m33.ref.pcia.m4635.PCIA4635For32ServicesUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * ȡ���ɱ�����
 * 
 * @author zhangcheng
 */
public class CancelIACostFor32BP implements
ICancelSquareAction<SquareInvDetailVO> {

  @Override
  public void cancelSquare(List<SquareInvDetailVO> list) {

    if (list == null || list.size() == 0) {
      return;
    }

    try {

      SquareInvDetailVO[] vos = list.toArray(new SquareInvDetailVO[0]);

      AroundProcesser<SquareInvVO> processer =
          new AroundProcesser<SquareInvVO>(BPPlugInPoint.CancelIACostFor32BP);

      // ����ִ�к�ҵ�����
      this.addAfterRule(processer);

      SquareInvVO[] svos =
          (SquareInvVO[]) BSContext.getInstance().getSession(
              SquareInvVO.class.getName());
      // ����Ƿ���Դ����
      svos = SquareInvVOUtils.getInstance().filterCostableVO(svos);

      processer.before(svos);

      // ȡ�����ɱ�����
      IAI5For32ServicesUtil.deleteI5ForSO32UnSettle(
          SoVoTools.getVOsOnlyValues(vos, SquareInvDetailVO.CSQUAREBILLID),
          SoVoTools.getVOPKValues(vos));

      // ȡ�����ɱ�����ɾ���������Ĵ�����۳ɱ���ת�� add by zhangby5 for 65
      PCIA4635For32ServicesUtil.delete4635ForSO32UnSettle(
          SoVoTools.getVOsOnlyValues(vos, SquareInvDetailVO.CSQUAREBILLID),
          SoVoTools.getVOPKValues(vos));

      // ȡ��������ϸ
      this.delDetail(vos);

      processer.after(svos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void addAfterRule(AroundProcesser<SquareInvVO> processer) {
    IRule<SquareInvVO> rule = new SquareIAOpenFor32Rule();
    processer.addAfterRule(rule);
  }

  private void delDetail(SquareInvDetailVO[] vos) {
    AroundProcesser<SquareInvDetailVO> processer =
        new AroundProcesser<SquareInvDetailVO>(
            BPPlugInPoint.CancelIACostFor32DetailBP);

    // ȡ��������ϸ
    new DeleteSquare32DetailBP().delete(vos);

    // ��д�ۼƳɱ���������
    IRule<SquareInvDetailVO> rule = new RewriteIACostFor32Rule();
    processer.addAfterRule(rule);
    processer.after(vos);
  }

}
