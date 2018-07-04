package nc.bs.so.m4331.maintain;

import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m4331.maintain.rule.delete.CheckEnableDeleteRule;
import nc.bs.so.m4331.maintain.rule.delete.ReturnBillCodeRule;
import nc.bs.so.m4331.maintain.rule.delete.RewriteBillDeleteRule;
import nc.bs.so.m4331.maintain.rule.reverse.ReserveDeleteRule;
import nc.bs.so.m4331.plugin.BP4331PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryVO;

public class DeleteDeliveryBP {

  public void delete(DeliveryVO[] bills) {

    AroundProcesser<DeliveryVO> processer =
        new AroundProcesser<DeliveryVO>(BP4331PlugInPoint.DeleteAction);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("ɾ��ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillDelete<DeliveryVO> bo = new BillDelete<DeliveryVO>();
    bo.delete(bills);
    TimeLog.info("д���ݿ⣬ɾ������"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(bills);
    TimeLog.info("ɾ����ִ��ҵ�����"); /*-=notranslate=-*/

  }

  private void addAfterRule(AroundProcesser<DeliveryVO> processer) {
    // ����ռ�ü��
    IRule<DeliveryVO> rule =
        new RenovateARByHidsEndRule(M4331EngrossAction.M4331Delete);
    processer.addAfterRule(rule);
    rule = new RewriteBillDeleteRule();
    processer.addAfterRule(rule);
    rule = new ReturnBillCodeRule();
    processer.addAfterRule(rule);
  }

  /**
   * �����������������ɾ��ǰ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author ף����
   * @time 2010-1-22 ����08:57:00
   */
  private void addBeforeRule(AroundProcesser<DeliveryVO> processer) {
    // У��ɾ��
    IRule<DeliveryVO> rule = new CheckEnableDeleteRule();
    processer.addBeforeRule(rule);

    // ����ռ�ü��
    rule = new RenovateARByHidsBeginRule(M4331EngrossAction.M4331Delete);
    processer.addBeforeRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ����Ԥ������
      rule = new ReserveDeleteRule();
      processer.addBeforeRule(rule);
    }

  }

}
