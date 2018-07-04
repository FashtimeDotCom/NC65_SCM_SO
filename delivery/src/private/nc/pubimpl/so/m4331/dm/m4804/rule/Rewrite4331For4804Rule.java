package nc.pubimpl.so.m4331.dm.m4804.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pubitf.so.m4331.dm.m4804.RewritePara4331For4804;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * ���䵥��д�������ۼ���������
 *
 * @since 6.0
 * @version 2011-3-21 ����11:42:57
 * @author ף����
 */
public class Rewrite4331For4804Rule {

  public void rewriteTransnum(RewritePara4331For4804[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private String[] lockBills(Map<String, RewritePara4331For4804> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0077")/*@res "���䵥��д��������������������ʧ�ܡ�"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, RewritePara4331For4804> prepareParams(
      RewritePara4331For4804[] paras) {
    Map<String, RewritePara4331For4804> index =
        new HashMap<String, RewritePara4331For4804>();
    for (RewritePara4331For4804 para : paras) {
      index.put(para.getCdeliverybid(), para);
    }
    return index;
  }

  private DeliveryViewVO[] query(Map<String, RewritePara4331For4804> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);

    DeliveryViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0078")/*@res "���䵥��д������ʱ�����ֲ��������ѯ��"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    this.renovateViews(index, views);
    return views;
  }

  private void renovateViews(Map<String, RewritePara4331For4804> index,
      DeliveryViewVO[] views) {
    for (DeliveryViewVO view : views) {
      String bid = view.getItem().getCdeliverybid();
      UFDouble renum = index.get(bid).getTransnum();
      UFDouble num = view.getItem().getNnum();
      UFDouble totaltranNum = view.getItem().getNtotaltransnum();
      totaltranNum = MathTool.add(totaltranNum, renum);
      view.getItem().setNtotaltransnum(totaltranNum);
      if (MathTool.compareTo(totaltranNum, num) < 0) {
        view.getItem().setBtransendflag(UFBoolean.FALSE);
        continue;
      }
      view.getItem().setBtransendflag(UFBoolean.TRUE);
    }
  }

  private void rewrite(RewritePara4331For4804[] paras) {
    Map<String, RewritePara4331For4804> index = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    TimeLog.info("���������"); /*-=notranslate=-*/
    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(index);
    TimeLog.info("��ѯ��������Ϣ��"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      DeliveryBVO.NTOTALTRANSNUM, DeliveryBVO.BTRANSENDFLAG
    };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, DeliveryBVO.class, names);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/
  }
}