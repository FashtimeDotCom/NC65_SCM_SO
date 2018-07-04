package nc.pubimpl.so.m30.so.m4331;

import java.util.HashMap;
import java.util.Map;

import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.pubitf.so.m30.so.m4331.IRewrite30For4331;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.so.m30.so.m4331.rule.RewriteOutCloseRule;
import nc.pubimpl.so.m30.so.m4331.rule.RewritePriceNumRule;
import nc.pubimpl.so.m30.so.m4331.rule.RewriteSendStateRule;
import nc.pubimpl.so.m30.so.m4331.rule.RewriteSetNumRule;
import nc.pubimpl.so.m30.so.m4331.rule.RewriteToleranceCheck;

/**
 * ��������д���۶�������ӿ�ʵ���ࡣ
 * 
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class Rewrite30For4331Impl implements IRewrite30For4331 {

  @Override
  public void rewrite30SendNumFor4331(Rewrite4331Para[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer,
      SaleOrderViewVO[] views, UFBoolean bboutendflag) throws BusinessException {
    // -------- ִ��ǰ���� ----------------

    // ���ȼ�鷢���ݲ�
    new RewriteToleranceCheck().process(views);

    // �����ۼƷ�������
    processer.addBeforeRule(new RewriteSetNumRule());
    
    // ���ô����۸�� jilu for �㰲��������
    processer.addBeforeRule(new RewritePriceNumRule());

    // �������õ���ǰ(�������ǰ��rule���ڲ㣬��ֹ��״̬���õ���Ƕ��)
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M30EngrossAction.M30SendReWrite));

    // -------- ִ�к���� ----------------
    // �������õ��ú�(�������ǰ��rule���ڲ㣬��ֹ��״̬���õ���Ƕ��)
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M30EngrossAction.M30SendReWrite));

    // ִ�к���״̬����
    processer.addAfterRule(new RewriteSendStateRule());
    // �����ʱ���±����г���ر�
    if (!bboutendflag.booleanValue()) {
      processer.addAfterRule(new RewriteOutCloseRule());
    }
  }

  private String[] lockBills(Map<String, Rewrite4331Para> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0189")/*@res "��������д���۶����ۼƷ��������������۶�������ʧ��"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite4331Para> prepareParams(Rewrite4331Para[] paras) {
    Map<String, Rewrite4331Para> index = new HashMap<String, Rewrite4331Para>();
    for (Rewrite4331Para para : paras) {
      String key = para.getCsaleorderbid();
      if (index.containsKey(key)) {
        UFDouble num = this.GetNoNullDouble(para.getNchangenum());
        num = num.add(this.GetNoNullDouble(index.get(key).getNchangenum()));
        Rewrite4331Para newpara =
            new Rewrite4331Para(key, num, para.getBclosed(),
                para.getBboutendflag());
        index.remove(key);
        index.put(key, newpara);
      }
      else {
        index.put(para.getCsaleorderbid(), para);
      }
    }
    return index;
  }

  private UFDouble GetNoNullDouble(UFDouble value) {
    if (value == null) {
      return UFDouble.ZERO_DBL;
    }
    return value;

  }

  private SaleOrderViewVO[] query(Map<String, Rewrite4331Para> index) {
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

  private void rewrite(Rewrite4331Para[] paras) throws BusinessException {
    UFBoolean bboutendflag = paras[0].getBboutendflag();

    Map<String, Rewrite4331Para> index = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(Rewrite4331Para.class.getName(), index);
    TimeLog.info("���������"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);
    TimeLog.info("��ѯ���۶�������"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30SendNumFor4331);
    this.addRule(processer, views, bboutendflag);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("д���ݿ�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderBVO.NTOTALSENDNUM
    };
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, names);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("д���ݿ��ִ��ҵ�����"); /*-=notranslate=-*/

    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(Rewrite4331Para.class.getName());
  }

}
