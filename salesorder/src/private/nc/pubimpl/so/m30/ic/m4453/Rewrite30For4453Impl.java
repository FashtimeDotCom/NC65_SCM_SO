package nc.pubimpl.so.m30.ic.m4453;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.ic.m4453.rule.RewriteInvoiceStateRule;
import nc.pubimpl.so.m30.ic.m4453.rule.RewriteME35Rule;
import nc.pubimpl.so.m30.ic.m4453.rule.RewriteNtotalsigNnumRule;
import nc.pubimpl.so.m30.ic.m4453.rule.RewriteOutStateRule;
import nc.pubimpl.so.m30.ic.m4453.rule.RewriteSendStateRule;
import nc.pubimpl.so.m30.ic.m4453.rule.RewriteSetNumRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteToleranceCheck;
import nc.pubitf.so.m30.ic.m4453.IRewrite30For4453;
import nc.pubitf.so.m30.ic.m4453.Rewrite4453Para;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.SOSysInitGroupQuery;

/**
 * ;�𵥻�д���۶�������ӿ�
 * 
 * @since 6.3
 * @version 2014-06-30 16:55:07
 * @author ����
 */
public class Rewrite30For4453Impl implements IRewrite30For4453 {

  @Override
  public void rewrite30NumFor4453(Rewrite4453Para[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer,
      SaleOrderViewVO[] views) {
    // �ȼ������ݲΧ
    try {
      new RewriteToleranceCheck().process(views);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    processer.addBeforeRule(new RewriteSetNumRule());

    // �������õ���ǰ(�������ǰ��rule���ڲ㣬��ֹ��״̬���õ���Ƕ��)
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M30EngrossAction.M30LossReWrite));

    // --after-------------------------------------------

    // �������õ��ú�(�������ǰ��rule���ڲ㣬��ֹ��״̬���õ���Ƕ��)
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M30EngrossAction.M30LossReWrite));

    // Ϊ��֪ͨStateCalculateUtil.isAutoTransitInvoiceOpen���Զ���д���õģ��������ֹ���
    // �˴��ǳ���Ť����Ϊ��ʱ�����Ժ�汾��״̬����ʹ�����¿���
    BSContext.getInstance().setSession(Rewrite30For4453Impl.class.getName(),
        UFBoolean.TRUE);
    
    // ��дӪ�����ÿͻ����õ� modify by zhangby5 �˴��Ȼ�д�ͻ����õ����󴥷�����״̬�ı�
    if (SOSysInitGroupQuery.isMeEnabled()) {
      processer.addAfterRule(new RewriteME35Rule());
    }
    
    processer.addAfterRule(new RewriteOutStateRule());
    // ����򿪻�������Ʊ�򿪣�����Session��StateCalculateUtil.isAutoTransitInvoiceOpenʹ��

    processer.addAfterRule(new RewriteInvoiceStateRule());
    processer.addAfterRule(new RewriteSendStateRule());

    // ��д��������ǩ������
    processer.addAfterRule(new RewriteNtotalsigNnumRule());

  }

  private String[] lockBills(Map<String, Rewrite4453Para> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0170")/*@res ";�𵥻�д���۶���ǩ��������;�������������۶�������ʧ�ܣ�"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite4453Para> prepareParams(Rewrite4453Para[] paras) {
    Map<String, Rewrite4453Para> index = new HashMap<String, Rewrite4453Para>();
    for (Rewrite4453Para para : paras) {
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

  private SaleOrderViewVO[] query(Map<String, Rewrite4453Para> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<SaleOrderViewVO> bo =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    bo.setSharedHead(true);

    SaleOrderViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0171")/*@res "���ֲ����������²�ѯ���۶���"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private void rewrite(Rewrite4453Para[] paras) {
    TimeLog.logStart();
    Map<String, Rewrite4453Para> index = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(Rewrite4453Para.class.getName(), index);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0172")/*@res "���������"*/);

    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0173")/*@res "��ѯ���۶�������"*/);

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30NumFor4453);
    this.addRule(processer, views);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0174")/*@res "д���ݿ�ǰִ��ҵ�����"*/);

    TimeLog.logStart();
    String[] names = new String[] {
        SaleOrderBVO.NTOTALSIGNNUM, SaleOrderBVO.NTRANSLOSSNUM
    };
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, names);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0175")/*@res "�������ݿ�"*/);

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0176")/*@res "д���ݿ��ִ��ҵ�����"*/);

    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(Rewrite4453Para.class.getName());
    BSContext.getInstance().removeSession(Rewrite30For4453Impl.class.getName());
  }
}
