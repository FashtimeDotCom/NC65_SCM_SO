package nc.pubimpl.so.m4331.ic.m4480;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pubimpl.so.m4331.ic.m4480.rule.RewriteBeforeRule;
import nc.pubitf.so.m4331.ic.m4480.IRewrite4331For4480;
import nc.pubitf.so.m4331.ic.m4480.RewritePara4331For4480;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

public class Rewrite4331For4480Impl implements IRewrite4331For4480 {

  private void addRule(DeliveryViewVO[] views,
      Map<String, RewritePara4331For4480> index) {
    RewriteBeforeRule rule = new RewriteBeforeRule();
    rule.process(views, index);
  }

  private String[] lockBills(Map<String, RewritePara4331For4480> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0080")/*@res "Ԥ����д������Ԥ����������������������ʧ�ܡ�"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, RewritePara4331For4480> prepareParams(
      RewritePara4331For4480[] paras) {
    Map<String, RewritePara4331For4480> index =
        new HashMap<String, RewritePara4331For4480>();
    for (RewritePara4331For4480 para : paras) {
      index.put(para.getCdeliverBid(), para);
    }
    return index;
  }

  private DeliveryViewVO[] query(Map<String, RewritePara4331For4480> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);
    DeliveryViewVO[] views = bo.query(ids);
    // Ԥ����ɾ�������б���ȥ����У�飨add by zhangby5��
    // if (views.length != index.size()) {
    // String message =
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0081")/*@res
    // "���ֲ����������²�ѯ��������"*/;
    // ExceptionUtils.wrappBusinessException(message);
    // }
    return views;
  }

  private void rewrite(RewritePara4331For4480[] paras) {
    TimeLog.logStart();
    Map<String, RewritePara4331For4480> index = this.prepareParams(paras);
    TimeLog.info("���������"); /*-=notranslate=-*/
    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(index);
    TimeLog.info("��ѯ��������Ϣ ��"); /*-=notranslate=-*/
    this.addRule(views, index);
    TimeLog.logStart();
    TimeLog.info("д���ݿ�ǰִ��ҵ�����"); /*-=notranslate=-*/
    TimeLog.logStart();
    String[] names = new String[] {
      DeliveryBVO.NREQRSNUM
    };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, DeliveryBVO.class, names);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/
    TimeLog.logStart();
    TimeLog.info("д���ݿ��ִ��ҵ�����"); /*-=notranslate=-*/
  }

  @Override
  public void rewrite4331ReqrsNum(RewritePara4331For4480[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}