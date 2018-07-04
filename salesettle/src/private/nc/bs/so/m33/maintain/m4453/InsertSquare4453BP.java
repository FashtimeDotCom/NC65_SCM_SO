package nc.bs.so.m33.maintain.m4453;

import nc.bs.so.m33.maintain.m4453.rule.square.InsSQ4453CheckRule;
import nc.bs.so.m33.maintain.m4453.rule.square.InsSQ4453DefaultDataRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m33.m4453.entity.SquareWasVO;

public class InsertSquare4453BP {

  public SquareWasVO[] insert(SquareWasVO[] bills) {
    AroundProcesser<SquareWasVO> processer =
        new AroundProcesser<SquareWasVO>(BPPlugInPoint.InsertSquare4453BP);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    // ����ִ�к�ҵ�����
    // this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0011")/*@res "����ǰִ��ҵ�����"*/);

    TimeLog.logStart();
    BillInsert<SquareWasVO> bo = new BillInsert<SquareWasVO>();
    SquareWasVO[] vos = bo.insert(bills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0039")/*@res "���浥�ݵ����ݿ�"*/);

    TimeLog.logStart();
    processer.after(vos);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0040")/*@res "�����ִ��ҵ�����"*/);

    return vos;
  }

  private void addBeforeRule(AroundProcesser<SquareWasVO> processer) {

    // ���۽��㵥����ǰ����Ĭ�����ݹ���
    IRule<SquareWasVO> rule = new InsSQ4453DefaultDataRule();
    processer.addBeforeRule(rule);

    // ���۽��㵥����У�����
    rule = new InsSQ4453CheckRule();
    processer.addBeforeRule(rule);

  }

}