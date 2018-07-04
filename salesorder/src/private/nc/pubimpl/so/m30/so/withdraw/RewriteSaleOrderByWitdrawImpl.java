package nc.pubimpl.so.m30.so.withdraw;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.bs.so.m30.rule.maintainprocess.NullRule;
import nc.bs.so.m30.rule.maintainprocess.RewriteReturnNumRule;
import nc.bs.so.m30.rule.rewrite.m30.CheckWithdrawNumRule;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.so.m30.so.withdraw.IRewriteSaleOrderByWithdraw;
import nc.pubitf.so.m30.so.withdraw.Rewrite30Para;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class RewriteSaleOrderByWitdrawImpl implements
    IRewriteSaleOrderByWithdraw {

  @Override
  public void rewrite30NumForWithdraw(Rewrite30Para[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {

      ExceptionUtils.marsh(ex);
    }

  }

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author zhangcheng
   * @time 2010-9-10 ����04:09:42
   */
  private void addAfterRule(AroundProcesser<SaleOrderViewVO> processer) {
    processer.addAfterRule(new NullRule<SaleOrderViewVO>());

    // ��д�������ۣ��˻�����
    processer.addAfterRule(new RewriteReturnNumRule());

  }

  private void addBeforeRule(AroundProcesser<SaleOrderViewVO> processer) {
    // ִ��ǰ����
    IRule<SaleOrderViewVO> rule = new CheckWithdrawNumRule();
    processer.addBeforeRule(rule);

  }

  private Map<String, Rewrite30Para> prepareParams(Rewrite30Para[] paras) {
    Map<String, Rewrite30Para> index = new HashMap<String, Rewrite30Para>();
    for (Rewrite30Para para : paras) {
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

  private SaleOrderViewVO[] query(Map<String, Rewrite30Para> index) {
    String[] ids = index.keySet().toArray(new String[0]);
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

  private void rewrite(Rewrite30Para[] paras) {

    TimeLog.logStart();

    // ׼�������������������ݻ�ȡ
    Map<String, Rewrite30Para> index = this.prepareParams(paras);

    // ��ѯ������ƽVO
    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);

    TimeLog.info("��ѯ���۶�������"); /*-=notranslate=-*/

    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(Rewrite30Para.class.getName(), index);

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30NumForWithdraw);

    // �����˿�����
    for (SaleOrderViewVO viewvo : views) {
      this.setRewiteNum(viewvo, index);
    }

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(views);

    TimeLog.info("���û�д����ǰ�����"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderBVO.NTOTALRETURNNUM
    };
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, names);

    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/

    // ע���
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(views);

    TimeLog.info("���û�д����������"); /*-=notranslate=-*/

    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(Rewrite30Para.class.getName());
  }

  private void setRewiteNum(SaleOrderViewVO vo, Map<String, Rewrite30Para> index) {
    SaleOrderBVO body = vo.getBody();
    Rewrite30Para para = index.get(body.getCsaleorderbid());
    UFDouble ntotalreturnnum = body.getNtotalreturnnum();

    ntotalreturnnum = MathTool.sub(ntotalreturnnum, para.getNnum());
    body.setNtotalreturnnum(ntotalreturnnum);
  }

}
