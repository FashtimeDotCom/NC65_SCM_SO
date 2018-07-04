package nc.bs.so.m4331.quality;

import nc.bs.so.m4331.plugin.BP4331PlugInPoint;
import nc.bs.so.m4331.quality.rule.update.CheckUpdateNullRule;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

public class UpdateDeliveryCheckBP {

  public DeliveryCheckVO[] update(DeliveryCheckVO[] bills) {
    CompareAroundProcesser<DeliveryCheckVO> processer =
        new CompareAroundProcesser<DeliveryCheckVO>(
            BP4331PlugInPoint.updateDeliverycheck);

    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);

    TimeLog.logStart();
    VOUpdate<DeliveryCheckVO> bo = new VOUpdate<DeliveryCheckVO>();
    DeliveryCheckVO[] vos = bo.update(bills);

    TimeLog.info("�޸ı��浥�ݵ����ݿ�");/* -=notranslate=- */

    return vos;
  }

  private void addBeforeRule(CompareAroundProcesser<DeliveryCheckVO> processer) {
    // �޸ķǿ�У��
    IRule<DeliveryCheckVO> rule = new CheckUpdateNullRule();
    processer.addBeforeRule(rule);
  }

}
