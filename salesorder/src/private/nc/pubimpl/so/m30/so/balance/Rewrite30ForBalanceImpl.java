package nc.pubimpl.so.m30.so.balance;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderHVO;

import nc.pubitf.so.m30.so.balance.IRewrite30ForBalance;
import nc.pubitf.so.m30.so.balance.RewriteBalancePara;

import nc.bs.so.m30.plugin.ServicePlugInPoint;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.so.m30.so.balance.rule.Rewrite30SetReceivedMnyRule;

/**
 * Ԥ������ϵ��д���۶���
 * 
 * @since 6.1
 * @version 2013-06-05 08:53:05
 * @author yixl
 */
public class Rewrite30ForBalanceImpl implements IRewrite30ForBalance {

  @Override
  public void rewrite30ReceivedMnyForBalance(RewriteBalancePara[] paras)
      throws BusinessException {
    try {
      this.rewrite30ReceivedMny(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void rewrite30ReceivedMny(RewriteBalancePara[] paras) {
    Map<String, RewriteBalancePara> index = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(RewriteBalancePara.class.getName(),
        index);
    TimeLog.info("���������"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderHVO[] heads = this.query(index);
    TimeLog.info("��ѯ���۶�������"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderHVO> processer =
        new AroundProcesser<SaleOrderHVO>(
            ServicePlugInPoint.rewrite30ReceivedMnyForBalance);
    processer.addBeforeRule(new Rewrite30SetReceivedMnyRule());

    TimeLog.logStart();
    processer.before(heads);
    TimeLog.info("д���ݿ�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderHVO.NRECEIVEDMNY, SaleOrderHVO.NPRECEIVEMNY
    };
    VOUpdate<SaleOrderHVO> voUpate = new VOUpdate<SaleOrderHVO>();
    heads = voUpate.update(heads, names);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(heads);
    TimeLog.info("д���ݿ��ִ��ҵ�����"); /*-=notranslate=-*/

    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(RewriteBalancePara.class.getName());
  }

  private SaleOrderHVO[] query(Map<String, RewriteBalancePara> index) {
    String[] ids = this.lockBills(index);
    VOQuery<SaleOrderHVO> voQuery =
        new VOQuery<SaleOrderHVO>(SaleOrderHVO.class);

    SaleOrderHVO[] heads = voQuery.query(ids);
    if (heads.length != index.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0171")/*@res "���ֲ����������²�ѯ���۶���"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return heads;
  }

  private String[] lockBills(Map<String, RewriteBalancePara> index) {
    int size = index.size();
    String[] ids = new String[size];
    ids = index.keySet().toArray(ids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0185")/*@res "����������д���۶���ʵ���տ�������۶�����ͷʧ��"*/;
    locker.lock(ids, message);
    return ids;
  }

  private Map<String, RewriteBalancePara> prepareParams(
      RewriteBalancePara[] paras) {
    Map<String, RewriteBalancePara> index =
        new HashMap<String, RewriteBalancePara>();
    for (RewriteBalancePara para : paras) {
      index.put(para.getCsaleorderid(), para);
    }
    return index;
  }
}
