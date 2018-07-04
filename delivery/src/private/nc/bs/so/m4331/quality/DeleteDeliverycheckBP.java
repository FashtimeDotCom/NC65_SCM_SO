package nc.bs.so.m4331.quality;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

import nc.bs.so.m4331.quality.rule.delete.CheckEnableDeleteRule;
import nc.bs.so.m4331.quality.rule.delete.Rewrite4331OnDeleteRule;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.pub.LockOperator;

public class DeleteDeliverycheckBP {

  private void addRule(DeliveryViewVO[] views, boolean isCheck) {
    // ����Ƿ��ܹ�ɾ��
    CheckEnableDeleteRule delete = new CheckEnableDeleteRule();
    delete.checkDelete(views, isCheck);
    // ��ն�Ӧ������������ۼƺϸ���ۼƲ��ϸ�����
    Rewrite4331OnDeleteRule rewrite = new Rewrite4331OnDeleteRule();
    rewrite.rewrite4331(views);
  }

  public void delete(DeliveryCheckVO[] bills, boolean isCheck) {

    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(bills);
    this.addRule(views, isCheck);
    TimeLog.info("ɾ��ǰִ��ҵ�����"); /*-=notranslate=-*/
    TimeLog.logStart();
    VODelete<DeliveryCheckVO> bo = new VODelete<DeliveryCheckVO>();
    bo.delete(bills);
    TimeLog.info("д���ݿ⣬ɾ������"); /*-=notranslate=-*/
    TimeLog.logStart();
    TimeLog.info("ɾ����ִ��ҵ�����"); /*-=notranslate=-*/
  }

  /*
   * ��������������Ӷ�̬��
   */
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
            "04006002-0060")/*@res "�ʼ쵥��˻�д��������������������ʧ��"*/;
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
              "04006002-0061")/*@res "���ֲ����������²�ѯ������"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }
}
