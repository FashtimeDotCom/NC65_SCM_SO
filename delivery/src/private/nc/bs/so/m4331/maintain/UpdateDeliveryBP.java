package nc.bs.so.m4331.maintain;

import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPBeforeRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m4331.maintain.rule.insert.CheckBillCodeRule;
import nc.bs.so.m4331.maintain.rule.insert.CheckValityRule;
import nc.bs.so.m4331.maintain.rule.material.MaterielDistributeCheckRule;
import nc.bs.so.m4331.maintain.rule.reverse.AutoReserveRule;
import nc.bs.so.m4331.maintain.rule.reverse.ReserveUpdateRule;
import nc.bs.so.m4331.maintain.rule.update.CheckUpdateNullRule;
import nc.bs.so.m4331.maintain.rule.update.FillUpdateDefaultRule;
import nc.bs.so.m4331.maintain.rule.update.RewriteBillUpdateRule;
import nc.bs.so.m4331.plugin.BP4331PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetUpdateAuditInfoRule;
import nc.vo.so.m4331.entity.DeliveryVO;

public class UpdateDeliveryBP {

  public DeliveryVO[] update(DeliveryVO[] bills, DeliveryVO[] originBills) {
    CompareAroundProcesser<DeliveryVO> processer =
        new CompareAroundProcesser<DeliveryVO>(BP4331PlugInPoint.UpdateAction);
    // ����ִ��ǰҵ�����
    this.addBeforeRule(processer);
    // ����ִ�к�ҵ�����
    this.addAfterRule(processer);

    // ��������� Ҫ����originBills ���Ե�������
    AroundProcesser<DeliveryVO> atpprocesser =
        new AroundProcesser<DeliveryVO>(BP4331PlugInPoint.UpdateActionForATP);
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // �޸�ǰ���������
      IRule<DeliveryVO> rule = new DeliveryVOATPBeforeRule();
      atpprocesser.addBeforeRule(rule);
    }

    TimeLog.logStart();
    atpprocesser.before(originBills);
    processer.before(bills, originBills);
    TimeLog.info("�޸ı���ǰִ��ҵ�����"); /* -=notranslate=- */
    TimeLog.logStart();
    BillUpdate<DeliveryVO> bo = new BillUpdate<DeliveryVO>();
    DeliveryVO[] vos = bo.update(bills, originBills);
    TimeLog.info("�޸ı��浥�ݵ����ݿ�"); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(bills, originBills);
    atpprocesser.after(originBills);
    TimeLog.info("�޸ı����ִ��ҵ�����"); /* -=notranslate=- */

    return vos;
  }

  private void addAfterRule(CompareAroundProcesser<DeliveryVO> processer) {
    // ����ռ�ü��
    IRule<DeliveryVO> rule =
        new RenovateARByHidsEndRule(M4331EngrossAction.M4331Edit);
    processer.addAfterRule(rule);
    // ���ݺŹ���
    rule = new CheckBillCodeRule();
    processer.addAfterRule(rule);
    // ��д��Դ����
    ICompareRule<DeliveryVO> comRule = new RewriteBillUpdateRule();
    processer.addAfterRule(comRule);
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ����Ԥ������
      rule = new ReserveUpdateRule();
      processer.addAfterRule(rule);
      // �Զ�Ԥ��
      rule = new AutoReserveRule();
      processer.addAfterRule(rule);
    }

  }

  private void addBeforeRule(CompareAroundProcesser<DeliveryVO> processer) {

    // �޸ķǿ�У��
    IRule<DeliveryVO> rule = new CheckUpdateNullRule();
    processer.addBeforeRule(rule);
    // ���ݺϷ���У��
    rule = new CheckValityRule();
    processer.addBeforeRule(rule);
    // ���ϺͿ����֯����ж�
    rule = new MaterielDistributeCheckRule();
    processer.addBeforeRule(rule);

    // �����Ϣ:����޸��ˡ�����޸�ʱ��
    rule = new SetUpdateAuditInfoRule<DeliveryVO>();
    processer.addBeforeRule(rule);

    // ���Ĭ��ֵ
    ICompareRule<DeliveryVO> fillRule = new FillUpdateDefaultRule();
    processer.addBeforeRule(fillRule);
    // ����ռ�ü��
    rule = new RenovateARByHidsBeginRule(M4331EngrossAction.M4331Edit);
    processer.addBeforeRule(rule);
  }

}
