package nc.bs.so.m30.maintain;

import nc.bs.so.m30.maintain.rule.delete.RewriteBillDeleteRule;
import nc.bs.so.m30.maintain.rule.delete.RewritePromotePriceDeleteRule;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.rule.billcode.ReturnBillCodeRule;
import nc.bs.so.m30.rule.businessinfo.DeleteTransferMsgRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m30.rule.feature.FeatureSelectDeleteRule;
import nc.bs.so.m30.rule.maintaincheck.CheckDeletableRule;
import nc.bs.so.m30.rule.maintainprocess.DeletePriceFormWhenDelRule;
import nc.bs.so.m30.rule.maintainprocess.DeleteSoBalanceWhenDelRule;
import nc.bs.so.m30.rule.pu.Rewrite21DeleteRule;
import nc.bs.so.m30.rule.reserve.ReserveDeleteRule;
import nc.bs.so.m30.rule.rewrite.LS41.DelRewriteForLS41BillRule;
import nc.bs.so.m30.rule.rewrite.m28.DelRewriteForPriceAuditBillRule;
import nc.bs.so.m30.rule.rewrite.m35.Rewrite35WhenDelete;
import nc.bs.so.m30.rule.rewrite.m5805.RewriteForM5805DeleteRule;
import nc.bs.so.m30.rule.rewrite.m5805.RewriteForM5805InsertRule;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ɾ��BP
 * 
 * @author gdsjw
 */
public class DeleteSaleOrderBP {

  public SaleOrderVO[] delete(SaleOrderVO[] bills) {
    AroundProcesser<SaleOrderVO> processer =
        new AroundProcesser<SaleOrderVO>(BP30PlugInPoint.DeleteBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("����ɾ��ǰBP�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillDelete<SaleOrderVO> bo = new BillDelete<SaleOrderVO>();
    bo.delete(bills);

    TimeLog.info("д���ݿ⣬ɾ������"); /*-=notranslate=-*/

    // ע���
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(bills);

    TimeLog.info("����ɾ����BP�����"); /*-=notranslate=-*/

    return bills;
  }

  private void addAfterRule(AroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;
    // �տ������ϵ
    rule = new DeleteSoBalanceWhenDelRule();
    processer.addAfterRule(rule);

    // ɾ���۸����
    rule = new DeletePriceFormWhenDelRule();
    processer.addAfterRule(rule);

    // ����
    rule = new RenovateARByHidsEndRule(M30EngrossAction.M30Delete);
    processer.addAfterRule(rule);

    // �˻ص��ݺŹ���
    rule = new ReturnBillCodeRule();
    processer.addAfterRule(rule);

    // ��д��Դ��Դͷ���ݣ���д���۵�����ͬ��Ԥ�������������ۡ����۶��������ⵥ���������
    rule = new RewriteBillDeleteRule();
    processer.addAfterRule(rule);

    // ��д�ɹ�����
    rule = new Rewrite21DeleteRule();
    processer.addAfterRule(rule);

    // ��д���۷��õ�
    rule = new Rewrite35WhenDelete();
    processer.addAfterRule(rule);

    // �����ڲ�������Ϣ
    rule = new DeleteTransferMsgRule();
    processer.addAfterRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ������
      rule = new SaleOrderVOATPAfterRule();
      processer.addAfterRule(rule);
    }
    
    // ��д�����۸�� jilu for �㰲��������
    if (SysInitGroupQuery.isPRICEEnabled()) {
      rule = new RewritePromotePriceDeleteRule();
      processer.addAfterRule(rule);
    }
    
    // ɾ�������뵵����Դ��Ϣ
    rule = new FeatureSelectDeleteRule();
    processer.addAfterRule(rule);
    /**
     * wangzym	���ӻ�д�۸������� 2017-06-07
     */
    rule=new DelRewriteForPriceAuditBillRule();
    processer.addAfterRule(rule);
    /**
     * wangzym	���ӻ���ʷ����Э��2017-08-31
     */
    rule=new DelRewriteForLS41BillRule();
    processer.addAfterRule(rule);
    
    //add by zhangjjs 2018-3-19
	//��д������ϸ�����ӱ��ۼ����������۶��������� vbdef14
	rule = new RewriteForM5805DeleteRule();
	processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SaleOrderVO> processer) {
    // ɾ��ǰ���ݺϷ���У��
    IRule<SaleOrderVO> rule = new CheckDeletableRule();
    processer.addBeforeRule(rule);

    // ����
    rule = new RenovateARByHidsBeginRule(M30EngrossAction.M30Delete);
    processer.addBeforeRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ������
      rule = new SaleOrderVOATPBeforeRule();
      processer.addBeforeRule(rule);

      // ����Ԥ������
      rule = new ReserveDeleteRule();
      processer.addBeforeRule(rule);
    }

  }

}
