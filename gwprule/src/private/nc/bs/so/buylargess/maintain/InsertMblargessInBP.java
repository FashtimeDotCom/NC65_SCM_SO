package nc.bs.so.buylargess.maintain;

import nc.bs.so.buylargess.maintain.rule.BuyLargessDefaultValueRule;
import nc.bs.so.buylargess.maintain.rule.BuyLargessIntegralCheck;
import nc.bs.so.buylargess.maintain.rule.BuyLargessPriorityCodeRule;
import nc.bs.so.buylargess.maintain.rule.BuyLargessUniqueCheck;
import nc.bs.so.buylargess.maintain.rule.BuyLargessValidateCheck;
import nc.bs.so.buylargess.plugin.BPMblargessPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

public class InsertMblargessInBP {

  public BuyLargessVO[] insert(BuyLargessVO[] bills) {
    AroundProcesser<BuyLargessVO> processer =
        new AroundProcesser<BuyLargessVO>(
            BPMblargessPlugInPoint.InsertMblargessInBP);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);
    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("����ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillInsert<BuyLargessVO> bo = new BillInsert<BuyLargessVO>();
    BuyLargessVO[] vos = bo.insert(bills);
    TimeLog.info("���浥�ݵ����ݿ�"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("�����ִ��ҵ�����"); /*-=notranslate=-*/

    return vos;
  }

  private void addBeforeRule(AroundProcesser<BuyLargessVO> processer) {
    // ������ʼ���ںͽ�ֹ����
    IRule<BuyLargessVO> rule = new BuyLargessDefaultValueRule();
    processer.addBeforeRule(rule);
    // ����������У��
    rule = new BuyLargessIntegralCheck();
    processer.addBeforeRule(rule);
    // ���ݺϷ���У��
    rule = new BuyLargessValidateCheck();
    processer.addBeforeRule(rule);
    // ����Ψһ��У��
    rule = new BuyLargessUniqueCheck();
    processer.addBeforeRule(rule);
    // ����������
    rule = new BuyLargessPriorityCodeRule();
    processer.addBeforeRule(rule);
  }

}
