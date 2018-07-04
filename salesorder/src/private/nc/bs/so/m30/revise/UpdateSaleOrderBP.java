package nc.bs.so.m30.revise;

import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.revise.rule.CheckApprovebleRule;
import nc.bs.so.m30.revise.rule.ReviseApproveStateRule;
import nc.bs.so.m30.revise.rule.ReviseOutStateRule;
import nc.bs.so.m30.revise.rule.ReviseSendStateRule;
import nc.bs.so.m30.revise.rule.Rewrite4331WhenReviseRule;
import nc.bs.so.m30.revise.rule.Rewrite4CWhenReviseRule;
import nc.bs.so.m30.revise.rule.ReviseInvoiceStateRule;
import nc.bs.so.m30.rule.m35.ArsubOffsetAfterApproveRule;
import nc.bs.so.m30.rule.m35.ArsubOffsetUpdateRule;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m30.revise.action.rule.ReviseFeatureCheckRule;
import nc.impl.so.m30.revise.action.rule.UpdateSaleorderProRule;
import nc.impl.so.m30.revise.action.rule.UpdateSobalanceRule;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ���۶����޶����¡����۶�����UpdateBP
 * <p>
 * �޶�updateBP�ڰ�������updateBP���޶�updateBP�����޶��߼�
 * </p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author ��־ΰ
 * @time 2010-8-11 ����01:27:22
 */
public class UpdateSaleOrderBP {

  public SaleOrderVO[] update(SaleOrderVO[] bills, SaleOrderVO[] originBills) {
    CompareAroundProcesser<SaleOrderVO> processer =
        new CompareAroundProcesser<SaleOrderVO>(BP30PlugInPoint.ReviseUpdateBP);
    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0069")/* @res "�����޸ı���ǰBP�����" */);

    // ����nc.bs.so.m30.maintain.UpdateSaleOrderBP������۶�������
    TimeLog.logStart();
    nc.bs.so.m30.maintain.UpdateSaleOrderBP updateBP =
        new nc.bs.so.m30.maintain.UpdateSaleOrderBP();
    SaleOrderVO[] ret = updateBP.update(bills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0070")/* @res "�����޸ĵ��ݵ����ݿ�" */);

    // ע���
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(bills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0071")/* @res "�����޸ı����BP�����" */);

    return ret;
  }

  private void addAfterRule(CompareAroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;
    // ��֪�������������۶����Ѹļ�
    rule = new Rewrite4331WhenReviseRule();
    processer.addAfterRule(rule);
    // ��֪���ⵥ�������۶����Ѹļ�
    rule = new Rewrite4CWhenReviseRule();
    processer.addAfterRule(rule);

    // ��˰�ϼƱ仯���¶����տ������ϵ
    rule = new UpdateSobalanceRule();
    processer.addAfterRule(rule);

    ICompareRule<SaleOrderVO> compareRule = null;
    // �޶��������򿪳���
    compareRule = new ReviseOutStateRule();
    processer.addAfterRule(compareRule);
    // �޶��������򿪷���
    compareRule = new ReviseSendStateRule();
    processer.addAfterRule(compareRule);
    //�޶�������������
    compareRule = new  ReviseInvoiceStateRule();
    processer.addAfterRule(compareRule);

    // ���۶����޶�������������ж���״̬�Ĵ��� add by zhangby5
    rule = new ReviseApproveStateRule();
    processer.addAfterRule(rule);

    // ��������Ʒ�Ҹ���ɾ�� add by wangshu6 for �޶��������Ʒ�Ը� 2015041
    rule = new ArsubOffsetUpdateRule();
    processer.addAfterRule(rule);

    // �����������Ʒ�Ը�����
    rule = new ArsubOffsetAfterApproveRule();
    processer.addAfterRule(rule);

    // ���۶����޶��󣬰�����VO���붩���������
    rule = new UpdateSaleorderProRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(CompareAroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;
    // У���ͷ����¼����Զ������Ƿ�����
    rule = new UserDefSaveRule<SaleOrderVO>(new Class[] {
      SaleOrderHVO.class, SaleOrderBVO.class
    });
    processer.addBeforeRule(rule);

    ICompareRule<SaleOrderVO> comparerule = new CheckApprovebleRule();
    processer.addBeforeRule(comparerule);
    
    comparerule = new ReviseFeatureCheckRule();
    processer.addBeforeRule(comparerule);

  }
}
