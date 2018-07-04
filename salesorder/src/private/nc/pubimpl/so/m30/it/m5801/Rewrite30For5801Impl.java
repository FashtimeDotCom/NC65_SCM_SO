package nc.pubimpl.so.m30.it.m5801;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.pubitf.so.m30.it.m5801.IRewrite30For5801;
import nc.pubitf.so.m30.it.m5801.Rewrite5801Para;

import nc.bs.so.m30.plugin.ServicePlugInPoint;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.so.m30.it.m5801.rule.RewriteCheckArrangeNumRule;
import nc.pubimpl.so.m30.it.m5801.rule.RewriteSetNumRule;

/**
 * ���ں�ͬ�����۶�����д�ӿڵ�ʵ����
 * 
 * @since JCK 6.31
 * @version 2014-03-19 14:32:15
 * @author zhangyfr
 */
public class Rewrite30For5801Impl implements IRewrite30For5801 {

  @Override
  public void rewriteNarrangeItcNumFor5801(Rewrite5801Para[] paras)
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
    // -------- ִ��ǰ���� ----------------

    // ���ȼ�鷢���ݲ�
    new RewriteCheckArrangeNumRule().process(views);

    // �����ۼƷ�������
    processer.addBeforeRule(new RewriteSetNumRule());

    // -------- ִ�к���� ----------------

  }

  private String[] lockBills(Map<String, Rewrite5801Para> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0483")/*@res "���ں�ͬ��д���۶����ۼư��Ž��ں�ͬ�����������۶�������ʧ��"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite5801Para> prepareParams(Rewrite5801Para[] paras) {
    Map<String, Rewrite5801Para> index = new HashMap<String, Rewrite5801Para>();
    for (Rewrite5801Para para : paras) {
      String key = para.getCsaleorderbid();
      if (index.containsKey(key)) {
        UFDouble num = this.GetNoNullDouble(para.getNchangenum());
        num = num.add(this.GetNoNullDouble(index.get(key).getNchangenum()));
        Rewrite5801Para newpara = new Rewrite5801Para(key, num);
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

  private SaleOrderViewVO[] query(Map<String, Rewrite5801Para> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<SaleOrderViewVO> bo =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    bo.setSharedHead(true);

    SaleOrderViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0171")/*@res "���ֲ����������²�ѯ���۶���"*/;
      //�������۶����޶��Ѿ�����ͨ���Ѿ���д��ɣ�������ľ��ǿ�ֱ�ӷ���views�����ˣ�������������˵��
      return views;
      //ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private void rewrite(Rewrite5801Para[] paras) {

    Map<String, Rewrite5801Para> index = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(Rewrite5801Para.class.getName(), index);
    TimeLog.info("�������"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);
    TimeLog.info("��ѯ���۶�������"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30ArrangeItcNumFor5801);
    this.addRule(processer, views);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("д���ݿ�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names =
        new String[] {
          SaleOrderBVO.NARRANGEITCNUM, SaleOrderBVO.BARRANGEDFLAG,
          SaleOrderBVO.CARRANGEPERSONID, SaleOrderBVO.TLASTARRANGETIME
        };
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, names);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("д���ݿ��ִ��ҵ�����"); /*-=notranslate=-*/

    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(Rewrite5801Para.class.getName());
  }

}
