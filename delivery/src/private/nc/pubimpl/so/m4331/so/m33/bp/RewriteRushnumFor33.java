package nc.pubimpl.so.m4331.so.m33.bp;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m4331.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.so.m4331.so.m33.RewriteRushNumPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * ��д�������Գ�����
 * 
 * @author ף����
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class RewriteRushnumFor33 {

  public void rewrite(RewriteRushNumPara[] paras) {
    this.rewriteRushnum(paras);
  }

  private void addRule(DeliveryViewVO[] views) {
    Map<String, RewriteRushNumPara> index =
        (Map<String, RewriteRushNumPara>) BSContext.getInstance().getSession(
            RewriteRushNumPara.class.getName());
    for (DeliveryViewVO view : views) {
      String cdeliverybid = view.getItem().getCdeliverybid();
      UFDouble arnum = index.get(cdeliverybid).getRushnum();
      UFDouble totalRushnum = view.getItem().getNtotalrushnum();

      totalRushnum = MathTool.add(arnum, totalRushnum);
      view.getItem().setNtotalrushnum(totalRushnum);
    }
  }

  private String[] lockBills(Map<String, RewriteRushNumPara> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
            "04006002-0094")/* @res "��д������ȷ��Ӧ������ʱ����ʧ�ܣ�" */;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, RewriteRushNumPara> prepareParams(
      RewriteRushNumPara[] paras) {
    Map<String, RewriteRushNumPara> index =
        new HashMap<String, RewriteRushNumPara>();
    for (RewriteRushNumPara para : paras) {
      index.put(para.getCdeliverybid(), para);
    }
    return index;
  }

  private DeliveryViewVO[] query(Map<String, RewriteRushNumPara> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);
    DeliveryViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
              "04006002-0061")/* @res "���ֲ����������²�ѯ������" */;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private void rewriteRushnum(RewriteRushNumPara[] paras) {
    Map<String, RewriteRushNumPara> index = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(RewriteRushNumPara.class.getName(),
        index);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0096")/* @res "���������" */);

    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(index);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0097")/* @res "��ѯ����������" */);
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(
            ServicePlugInPoint.rewriteRushnumFor33);
    this.addRule(views);

    TimeLog.logStart();
    processer.before(views);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0098")/* @res "д���ݿ�ǰִ��ҵ�����" */);

    TimeLog.logStart();
    String[] names = new String[] {
        DeliveryBVO.NTOTALRUSHNUM
    };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    views = bo.update(views, DeliveryBVO.class, names);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0099")/* @res "�������ݿ�" */);

    TimeLog.logStart();
    processer.after(views);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0100")/* @res "д���ݿ��ִ��ҵ�����" */);
    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(RewriteRushNumPara.class.getName());
  }
}
