package nc.bs.so.m30.maintain;

import nc.bs.scmpub.rule.CrossRuleValidateRule;
import nc.bs.so.m30.maintain.rule.update.RewriteBillUpdateRule;
import nc.bs.so.m30.maintain.rule.update.RewritePromotePriceUpdateRule;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.rule.billcode.CheckUniqueBillCodeRule;
import nc.bs.so.m30.rule.billcode.UpdateBillCodeRule;
import nc.bs.so.m30.rule.businessinfo.SaveTransferMsgRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m30.rule.feature.ClearMffileSRCRule;
import nc.bs.so.m30.rule.feature.FeatureSelectSaveRule;
import nc.bs.so.m30.rule.feature.RestMffileSRCRule;
import nc.bs.so.m30.rule.m35.ArsubOffsetBeforeSaveRule;
import nc.bs.so.m30.rule.m35.LrgCashMarCheckRule;
import nc.bs.so.m30.rule.maintaincheck.CheckCanUpdateWhenAuditing;
import nc.bs.so.m30.rule.maintaincheck.CheckDateRule;
import nc.bs.so.m30.rule.maintaincheck.CheckLrgTotalMoney;
import nc.bs.so.m30.rule.maintaincheck.CheckNumPriceMnyRule;
import nc.bs.so.m30.rule.maintaincheck.CheckSaveBillRule;
import nc.bs.so.m30.rule.maintaincheck.CheckSettleOrgRepeat;
import nc.bs.so.m30.rule.maintainprocess.FillupFretexchangeRule;
import nc.bs.so.m30.rule.maintainprocess.FillupRedundanceDataRule;
import nc.bs.so.m30.rule.maintainprocess.ThisGatheringRule;
import nc.bs.so.m30.rule.maintainprocess.UpdateSoBalanceWhenUpdateM30HeadRule;
import nc.bs.so.m30.rule.maintainprocess.UpdateSoBalanceWhenUpdateRule;
import nc.bs.so.m30.rule.reserve.AutoReserveRule;
import nc.bs.so.m30.rule.reserve.ReserveUpdateRule;
import nc.bs.so.m30.rule.rewrite.m28.RewriteForPriceAuditBillRule;
import nc.bs.so.m30.rule.rewrite.m28.RewriteForPriceAuditBillUpdateRule;
import nc.bs.so.m30.rule.rewrite.m5805.RewriteForM5805InsertRule;
import nc.bs.so.m30.rule.rewrite.m5805.RewriteForM5805UpdateRule;
import nc.bs.so.m30.rule.rewrite.price.RewritePriceFormRule;
import nc.bs.so.pub.rule.CheckApproverRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetUpdateAuditInfoRule;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * �޸ı��涯��
 * 
 * @author gdsjw
 */
public class UpdateSaleOrderBP {

  public SaleOrderVO[] update(SaleOrderVO[] bills, SaleOrderVO[] originBills) {
    CompareAroundProcesser<SaleOrderVO> processer =
        new CompareAroundProcesser<SaleOrderVO>(BP30PlugInPoint.UpdateBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills, originBills);

    TimeLog.info("�����޸ı���ǰBP�����"); /* -=notranslate=- */

    TimeLog.logStart();
    BillUpdate<SaleOrderVO> bo = new BillUpdate<SaleOrderVO>();
    SaleOrderVO[] vos = bo.update(bills, originBills);

    TimeLog.info("�����޸ĵ��ݵ����ݿ�"); /* -=notranslate=- */

    // ע���
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(vos, originBills);

    TimeLog.info("�����޸ı����BP�����"); /* -=notranslate=- */

    return vos;
  }

  private void addAfterRule(CompareAroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;

    // �տ������ϵ
    rule = new UpdateSoBalanceWhenUpdateRule();
    processer.addAfterRule(rule);

    // ����
    rule = new RenovateARByHidsEndRule(M30EngrossAction.M30Edit);
    processer.addAfterRule(rule);

    // �����տ������
    rule = new ThisGatheringRule();
    processer.addAfterRule(rule);

    // ��鵥�ݺ��Ƿ��ظ�
    rule = new CheckUniqueBillCodeRule();
    processer.addAfterRule(rule);

    // ��д��Դ��Դͷ���ݣ���д���۵�����ͬ��Ԥ�������������ۡ����۶��������ⵥ���������
    ICompareRule<SaleOrderVO> compareRule = new RewriteBillUpdateRule();
    processer.addAfterRule(compareRule);

    // �����ڲ�������Ϣ
    rule = new SaveTransferMsgRule();
    processer.addAfterRule(rule);

    // ����۸����
    rule = new RewritePriceFormRule();
    processer.addAfterRule(rule);

    // ����Ѿ����տ������ϵ���ö����ܽ����¶���������ͷ�����ܽ��
    rule = new UpdateSoBalanceWhenUpdateM30HeadRule();
    processer.addAfterRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ������
      rule = new SaleOrderVOATPAfterRule();
      processer.addAfterRule(rule);
      // ����Ԥ������
      rule = new ReserveUpdateRule();
      processer.addAfterRule(rule);
      // �Զ�Ԥ��
      rule = new AutoReserveRule();
      processer.addAfterRule(rule);
    }
    
    // ��д���������۸� jilu for �㰲��������
    if (SysInitGroupQuery.isPRICEEnabled()) {
      compareRule = new RewritePromotePriceUpdateRule();
      processer.addAfterRule(compareRule);
    }
    
    // �������д���
    compareRule = new RestMffileSRCRule();
    processer.addAfterRule(compareRule);
    
    
     /**
      *��д�۸������� wangzym 2017-06-06
     */
    compareRule = new RewriteForPriceAuditBillUpdateRule();
    processer.addAfterRule(compareRule);
    /**
     *��д��ʷ����Э�� wangzym 2017-08-30
     */
    compareRule = new nc.bs.so.m30.rule.rewrite.LS41.RewriteForLS41BillUpdateRule();
    processer.addAfterRule(compareRule);
    
    //add by zhangjjs 2018-3-19
	//��д������ϸ�����ӱ��ۼ����������۶��������� vbdef14
    compareRule = new RewriteForM5805UpdateRule();
	processer.addAfterRule(compareRule);
  }

  private void addBeforeRule(CompareAroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;
    
    // �������Ƿ�֧���������޸�
    rule = new CheckCanUpdateWhenAuditing();
    processer.addBeforeRule(rule);
    
    // add by wangshu6 for 636 2014-01-20 ���۶���������֧���޶�
    // ��鵱ǰ�������ǲ��������ˣ� �����������״̬���ҵ�ǰ�����˲��������ˣ��������޸�
    rule = new CheckApproverRule();
    processer.addBeforeRule(rule);
    // end
    
    
    // ��ȫ���ݹ���
    rule = new FillupRedundanceDataRule();
    processer.addBeforeRule(rule);
    
    // ��ȫ�˻������
    rule = new FillupFretexchangeRule();
    processer.addBeforeRule(rule);

    // ��ȫ�����Ϣ:����޸��ˡ�����޸�ʱ��
    rule = new SetUpdateAuditInfoRule<SaleOrderVO>();
    processer.addBeforeRule(rule);

    // �޸�ʱ���ݺŹ���
    ICompareRule<SaleOrderVO> compareRule = new UpdateBillCodeRule();
    processer.addBeforeRule(compareRule);

    // �������۽�������
    rule = new CheckNumPriceMnyRule();
    processer.addBeforeRule(rule);
    // �����������֯�Ƿ�һ��
    rule = new CheckSettleOrgRepeat();
    processer.addBeforeRule(rule);
    // ���ڼ�����
    /**wangzym 2017-06-20
	 * ���ݰ�����Ŀ���ι�ǿҪ��ȥ���������ڵļ�����
	 * 
	 */
    /*rule = new CheckDateRule();
    processer.addBeforeRule(rule);*/
    
    // ����������
    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    // ����
    rule = new RenovateARByHidsBeginRule(M30EngrossAction.M30Edit);
    processer.addBeforeRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ������
      rule = new SaleOrderVOATPBeforeRule();
      processer.addBeforeRule(rule);
    }

    // ���۶�������ʱ������Ʒ�Ҹ���Χ���
    rule = new LrgCashMarCheckRule();
    processer.addBeforeRule(rule);

    // ���۶�������ʱ��Ʒ�Ҹ����õ���ֱ���ǰ����
    rule = new ArsubOffsetBeforeSaveRule();
    processer.addBeforeRule(rule);

    // ����ͷ�ͱ�����Ʒ��˰�ϼ��Ƿ�һ��
    rule = new CheckLrgTotalMoney();
    processer.addBeforeRule(rule);
    
    // ���۶���������ѡ�䱣��
    rule = new FeatureSelectSaveRule();
    processer.addBeforeRule(rule);
    
    //����У�����
    rule = new CrossRuleValidateRule<SaleOrderVO>();
    processer.addBeforeRule(rule);
    
    // �������д���
    compareRule = new ClearMffileSRCRule();
    processer.addBeforeRule(compareRule);
  
  }

}
