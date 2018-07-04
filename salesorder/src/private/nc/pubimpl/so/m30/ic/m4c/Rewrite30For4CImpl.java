package nc.pubimpl.so.m30.ic.m4c;

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
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubimpl.so.m30.ic.m4c.rule.Rewrite35WhenOutNumChange;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteExchangeOutRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteOPCOutNumRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteOutNumRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteOutStateRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewritePriceNumRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteSetNumRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteToleranceCheck;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteZ3ByRowStateRule;
import nc.pubitf.so.m30.ic.m4c.IRewrite30For4C;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * �����д���۶����ۼƳ�������ʵ��
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * 
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-7-12 ����04:50:48
 */
public class Rewrite30For4CImpl implements IRewrite30For4C {

  @Override
  public void rewrite30NumFor4C(Rewrite4CPara[] paras) throws BusinessException {
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

    // �ȼ������ݲΧ
    new RewriteToleranceCheck().process(views);
    processer.addBeforeRule(new RewriteOutNumRule());
    processer.addBeforeRule(new RewriteExchangeOutRule());
    // ִ��ǰ��������ۼƳ�������
    processer.addBeforeRule(new RewriteSetNumRule());
    processer.addBeforeRule(new Rewrite35WhenOutNumChange());
    // �������ۼ۸���������ִ���� jilu for �㰲��������
    processer.addBeforeRule(new RewritePriceNumRule());
    // end
    // �������õ���ǰ(�������ǰ��rule���ڲ㣬��ֹ��״̬���õ���Ƕ��)
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M30EngrossAction.M30OutReWrite));

    // -------- ִ�к���� ----------------

    // �������õ��ú�(�������ǰ��rule���ڲ㣬��ֹ��״̬���õ���Ƕ��)
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M30EngrossAction.M30OutReWrite));

    // Ϊ��֪ͨStateCalculateUtil.isAutoTransitInvoiceOpen���Զ���д���õģ��������ֹ���
    // �˴��ǳ���Ť����Ϊ��ʱ�����Ժ�汾��״̬����ʹ�����¿���
    BSContext.getInstance().setSession(Rewrite30For4CImpl.class.getName(),
        UFBoolean.TRUE);
    // ��д��������:�ۼƳ�������
    if(SysInitGroupQuery.isOPCEnabled()){
      processer.addAfterRule(new RewriteOPCOutNumRule());
    }
    processer.addAfterRule(new RewriteOutStateRule());

    // ��д���۶�������ʱ����������йرջ�Ҫ��д���κ�ͬ
    processer.addAfterRule(new RewriteZ3ByRowStateRule());
    
  }

  private String[] lockBills(Map<String, Rewrite4CPara> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0180")/*@res "������۳��ⵥ��д���۶����ۼƳ��������������۶�������ʧ��"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite4CPara> prepareParams(Rewrite4CPara[] paras) {
    Map<String, Rewrite4CPara> index = new HashMap<String, Rewrite4CPara>();
    for (Rewrite4CPara para : paras) {
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

  private SaleOrderViewVO[] query(Map<String, Rewrite4CPara> index) {
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

  private void rewrite(Rewrite4CPara[] paras) throws BusinessException {
    TimeLog.logStart();
    Map<String, Rewrite4CPara> index = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(Rewrite4CPara.class.getName(), index);
    TimeLog.info("���������"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);
    TimeLog.info("��ѯ���۶�������"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30NumFor4C);
    this.addRule(processer, views);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("д���ݿ�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderBVO.NTOTALNOTOUTNUM, SaleOrderBVO.NTOTALOUTNUM
    };
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, names);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("д���ݿ��ִ��ҵ�����"); /*-=notranslate=-*/

    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(Rewrite4CPara.class.getName());
    BSContext.getInstance().removeSession(Rewrite30For4CImpl.class.getName());
  }

}
