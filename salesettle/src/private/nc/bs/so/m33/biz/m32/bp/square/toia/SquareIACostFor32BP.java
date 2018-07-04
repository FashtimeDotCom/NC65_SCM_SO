package nc.bs.so.m33.biz.m32.bp.square.toia;

import nc.bs.so.m33.biz.m32.rule.toia.SquareIACloseFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toia.ToIABizFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toia.ToIACheckFor32Rule;
import nc.bs.so.m33.maintain.m32.InsertSquare32DetailBP;
import nc.bs.so.m33.maintain.m32.rule.detail.RewriteIACostFor32Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.ref.ia.mi5.IAI5For32ServicesUtil;
import nc.itf.so.m33.ref.pcia.m4635.PCIA4635For32ServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;

public class SquareIACostFor32BP {

  public void square(SquareInvVO[] sqvos) {
    try {

      AroundProcesser<SquareInvVO> processer =
          new AroundProcesser<SquareInvVO>(BPPlugInPoint.SquareToIABy32);

      // ����ִ��ǰҵ�����
      this.addBeforeRule(processer);

      // ����ִ�к�ҵ�����
      this.addAfterRule(processer);

      // ��Ʊ���ɱ�����ǰִ��ҵ�����
      processer.before(sqvos);

      // �����ɱ����㵥VOת��Ϊ���ɱ�������ϸVO
      SquareInvDetailVO[] bills =
          SquareInvVOUtils.getInstance().changeSQVOtoSQDVOForIA(sqvos);

      this.saveDetail(sqvos, bills);

      // ����vo����
      I5BillVO[] i5vos = new VOChange4332ToI5Util().exchange(sqvos);

      // ����I5�ı���ӿ�
      IAI5For32ServicesUtil.insertI5ForSO32Settle(i5vos);

      // �������۷�Ʊ��ͨ���㵽�������Ĵ������ӿ� add by zhangby5 for 65
      PCIA4635For32ServicesUtil.insert4635ForSO32Settle(sqvos);

      processer.after(sqvos);

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void addAfterRule(AroundProcesser<SquareInvVO> processer) {
    IRule<SquareInvVO> rule = new SquareIACloseFor32Rule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareInvVO> processer) {
    // ���ɱ���
    IRule<SquareInvVO> rule = new ToIACheckFor32Rule();
    processer.addBeforeRule(rule);

    // �������������ҵ����
    rule = new ToIABizFor32Rule();
    processer.addBeforeRule(rule);

  }

  private void saveDetail(SquareInvVO[] sqvos, SquareInvDetailVO[] bills) {
    AroundProcesser<SquareInvDetailVO> processer =
        new AroundProcesser<SquareInvDetailVO>(
            BPPlugInPoint.SquareToIABy32Detail);

    // �ɱ�������ϸ����BP
    new InsertSquare32DetailBP().insert(sqvos, bills);

    // ��д�ۼƳɱ���������
    IRule<SquareInvDetailVO> rule = new RewriteIACostFor32Rule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

}
