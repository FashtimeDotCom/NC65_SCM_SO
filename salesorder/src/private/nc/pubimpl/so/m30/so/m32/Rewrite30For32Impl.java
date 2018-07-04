package nc.pubimpl.so.m30.so.m32;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.so.m32.rule.RewriteInvoiceStateRule;
import nc.pubimpl.so.m30.so.m32.rule.RewriteSetNumRule;
import nc.pubimpl.so.m30.so.m32.rule.RewriteToleranceCheck;
import nc.pubitf.so.m30.so.m32.IRewrite30For32;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ���۷�Ʊ��д���۶�������ӿ�ʵ���ࡣ
 *
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class Rewrite30For32Impl implements IRewrite30For32 {

  @Override
  public void rewrite30NumFor32(Rewrite32Para[] paras) throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer,
      SaleOrderViewVO[] views) throws BusinessException {
    // -------- ִ��ǰ���� ----------------

    // ���ȼ�鿪Ʊ����
    new RewriteToleranceCheck().process(views);
    // ִ��ǰ������ÿ�Ʊ����
    processer.addBeforeRule(new RewriteSetNumRule());

    // -------- ִ�к���� ----------------

    // ִ�к�Ʊ״̬����
    processer.addAfterRule(new RewriteInvoiceStateRule());

  }

  private String[] lockBills(Map<String, Rewrite32Para> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0186")/*@res "���۷�Ʊ��д���۶����ۼƿ�Ʊ�����������۶�������ʧ��"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite32Para> prepareParams(Rewrite32Para[] paras) {
    Map<String, Rewrite32Para> index = new HashMap<String, Rewrite32Para>();
    for (Rewrite32Para para : paras) {
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

  private SaleOrderViewVO[] query(Map<String, Rewrite32Para> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<SaleOrderViewVO> bo =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    bo.setSharedHead(true);

    SaleOrderViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0171")/*@res "���ֲ����������²�ѯ���۶���"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private void rewrite(Rewrite32Para[] paras) throws BusinessException {
    TimeLog.logStart();
    Map<String, Rewrite32Para> index = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(Rewrite32Para.class.getName(), index);
    TimeLog.info("���������"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);
    TimeLog.info("��ѯ���۶�������"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30NumFor32);
    this.addRule(processer, views);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("д���ݿ�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderBVO.NTOTALINVOICENUM
    };
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, names);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("д���ݿ��ִ��ҵ�����"); /*-=notranslate=-*/

    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(Rewrite32Para.class.getName());
  }

}