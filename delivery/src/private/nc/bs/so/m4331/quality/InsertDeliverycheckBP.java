package nc.bs.so.m4331.quality;

import java.util.HashSet;
import java.util.Set;

import nc.bs.so.m4331.quality.rule.insert.CheckNewNullRule;
import nc.bs.so.m4331.quality.rule.insert.FillNewDefaultRule;
import nc.bs.so.m4331.quality.rule.insert.Rewrite4331ForCheckRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

public class InsertDeliverycheckBP {

  public DeliveryCheckVO[] insert(DeliveryCheckVO[] bills) {
    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(bills);
    TimeLog.info("��÷�����view");/* -=notranslate=- */
    // ����ִ��ǰҵ�����
    this.addBeforeRule(bills, views);
    TimeLog.logStart();
    VOInsert<DeliveryCheckVO> bo = new VOInsert<DeliveryCheckVO>();
    DeliveryCheckVO[] vos = bo.insert(bills);
    TimeLog.info("���浥�ݵ����ݿ�");/* -=notranslate=- */
    return vos;
  }

  private void addBeforeRule(DeliveryCheckVO[] bills, DeliveryViewVO[] views) {
    CheckNewNullRule checknull = new CheckNewNullRule();
    checknull.process(bills);
    // ��д�����������ۼƺϸ����������ϸ������Ƿ��ʼ����
    Rewrite4331ForCheckRule rewrite = new Rewrite4331ForCheckRule();
    rewrite.rewrite4331(views, bills);
    // ���Ĭ��ֵ
    FillNewDefaultRule fill = new FillNewDefaultRule();
    fill.setData(bills, views);
  }

  /* ��������������Ӷ�̬�� */
  private String[] getBids(DeliveryCheckVO[] bills) {
    Set<String> set = new HashSet<String>();
    for (DeliveryCheckVO vo : bills) {
      set.add(vo.getCdeliverybid());
    }
    String[] bids = new String[set.size()];
    bids = set.toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
            "04006002-0060")/* @res "�ʼ쵥��˻�д��������������������ʧ��" */;
    locker.lock(bids, message);
    return bids;
  }

  private DeliveryViewVO[] query(DeliveryCheckVO[] bills) {
    String[] ids = this.getBids(bills);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);
    DeliveryViewVO[] views = bo.query(ids);
    if (views.length != ids.length) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
              "04006002-0061")/* @res "���ֲ����������²�ѯ������" */;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }
}
