package nc.bs.so.m38.maintain;

import nc.bs.so.m38.maintain.rule.delete.DeleteBillAfterRule;
import nc.bs.so.m38.maintain.rule.delete.DeleteBillBeforeRule;
import nc.bs.so.m38.maintain.rule.delete.DeletePriceFormRule;
import nc.bs.so.m38.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;

public class DeletePreOrderBP {
  public void delete(PreOrderVO[] bills) {
    AroundProcesser<PreOrderVO> processer =
        new AroundProcesser<PreOrderVO>(BPPlugInPoint.DeleteBP);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("����ɾ��ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillDelete<PreOrderVO> bo = new BillDelete<PreOrderVO>();
    bo.delete(bills);
    TimeLog.info("д���ݿ⣬ɾ������"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(bills);
    TimeLog.info("����ɾ����BP�����"); /*-=notranslate=-*/
  }

  private void addAfterRule(AroundProcesser<PreOrderVO> processer) {
    // �˻ص��ݺŹ���
    IRule<PreOrderVO> rule = new DeleteBillAfterRule();
    processer.addAfterRule(rule);

    // ɾ���۸������Ϣ
    rule = new DeletePriceFormRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<PreOrderVO> processer) {
    // ɾ��ǰ���ݺϷ���У��
    IRule<PreOrderVO> rule = new DeleteBillBeforeRule();
    processer.addBeforeRule(rule);

  }
}
