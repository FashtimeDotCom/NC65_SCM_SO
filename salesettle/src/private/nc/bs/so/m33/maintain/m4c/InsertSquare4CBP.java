package nc.bs.so.m33.maintain.m4c;

import nc.bs.so.m33.maintain.m4c.rule.square.InsSQ4CCheckRule;
import nc.bs.so.m33.maintain.m4c.rule.square.InsSQ4CDefaultDataRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m33.m4c.entity.SquareOutVO;

public class InsertSquare4CBP {

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {

    // ���۽��㵥����ǰ����Ĭ�����ݹ���
    IRule<SquareOutVO> rule = new InsSQ4CDefaultDataRule();
    processer.addBeforeRule(rule);

    // ���۽��㵥����У�����
    rule = new InsSQ4CCheckRule();
    processer.addBeforeRule(rule);

  }

  public SquareOutVO[] insert(SquareOutVO[] bills) {
    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(BPPlugInPoint.InsertSquare4CBP);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    // ����ִ�к�ҵ�����
    // this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("����ǰִ��ҵ�����");/*-=notranslate=-*/

    TimeLog.logStart();
    BillInsert<SquareOutVO> bo = new BillInsert<SquareOutVO>();
    SquareOutVO[] vos = bo.insert(bills);
    TimeLog.info("���浥�ݵ����ݿ�");/*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("�����ִ��ҵ�����");/*-=notranslate=-*/

    return vos;
  }

  /*
   * private void addAfterRule(AroundProcesser<SquareOutVO> processer) { }
   */

}
