package nc.pubimpl.so.m4331.dm.m4804.rule;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * ���䵥�رմ򿪻�д������
 *
 * @since 6.0
 * @version 2011-3-21 ����11:08:21
 * @author ף����
 */
public class Renovate4331For4804Rule {

  public void renovateState(String[] bids, UFBoolean state)
      throws BusinessException {
    try {
      this.renovate(bids, state);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void lockBills(String[] bids) {
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0076")/*@res "���䵥�ֶ��رա���ʱ��д��������������������ʧ�ܡ�"*/;
    locker.lock(bids, message);
  }

  private DeliveryViewVO[] query(String[] bids, UFBoolean state) {
    this.lockBills(bids);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);
    DeliveryViewVO[] views = bo.query(bids);
    if (views.length != bids.length) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0061")/*@res "���ֲ����������²�ѯ������"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    for (DeliveryViewVO view : views) {
      view.getItem().setBtransendflag(state);
    }
    return views;
  }

  private void renovate(String[] bids, UFBoolean state) {

    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(bids, state);
    TimeLog.info("��ѯ��������Ϣ"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      DeliveryBVO.BTRANSENDFLAG
    };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, DeliveryBVO.class, names);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/
  }
}