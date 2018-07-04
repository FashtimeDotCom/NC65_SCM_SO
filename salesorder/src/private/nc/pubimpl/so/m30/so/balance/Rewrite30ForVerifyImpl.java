package nc.pubimpl.so.m30.so.balance;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.so.balance.rule.Rewrite30SetTotalPayMnyRule;
import nc.pubitf.so.m30.so.balance.IRewrite30ForVerify;
import nc.pubitf.so.m30.so.balance.RewriteVerifyPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class Rewrite30ForVerifyImpl implements IRewrite30ForVerify {

  @Override
  public void rewrite30TotalPayMnyVerifyListener(RewriteVerifyPara[] paras)
      throws BusinessException {
    try {
      this.rewrite30TotalPayMny(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void rewrite30TotalPayMny(RewriteVerifyPara[] paras) {
    Map<String, RewriteVerifyPara> index = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance()
        .setSession(RewriteVerifyPara.class.getName(), index);
    TimeLog.info("���������"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderBVO[] bodys = this.query(index);
    TimeLog.info("��ѯ���۶�������"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderBVO> processer =
        new AroundProcesser<SaleOrderBVO>(
            ServicePlugInPoint.rewrite30TotalPayMnyForVerifyListener);
    this.addRuleForReceivedMny(processer);

    TimeLog.logStart();
    processer.before(bodys);
    TimeLog.info("д���ݿ�ǰִ��ҵ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderBVO.NTOTALPAYMNY
    };
    VOUpdate<SaleOrderBVO> voUpate = new VOUpdate<SaleOrderBVO>();
    bodys = voUpate.update(bodys, names);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(bodys);
    TimeLog.info("д���ݿ��ִ��ҵ�����"); /*-=notranslate=-*/

    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(RewriteVerifyPara.class.getName());
  }

  private void addRuleForReceivedMny(AroundProcesser<SaleOrderBVO> processer) {
    // �����ۼƲ���������
    processer.addBeforeRule(new Rewrite30SetTotalPayMnyRule());
  }

  private SaleOrderBVO[] query(Map<String, RewriteVerifyPara> index) {
    String[] ids = this.lockBills(index);
    VOQuery<SaleOrderBVO> voQuery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class);

    SaleOrderBVO[] bodys = voQuery.query(ids);
    if (bodys.length != index.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0171")/*@res "���ֲ����������²�ѯ���۶���"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return bodys;
  }

  private String[] lockBills(Map<String, RewriteVerifyPara> index) {
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

  private Map<String, RewriteVerifyPara> prepareParams(RewriteVerifyPara[] paras) {
    Map<String, RewriteVerifyPara> index =
        new HashMap<String, RewriteVerifyPara>();
    for (RewriteVerifyPara para : paras) {
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

}
