package nc.pubimpl.so.m32.ic.m4c;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

import nc.pubitf.so.m32.ic.m4c.IRewrite32For4C;
import nc.pubitf.so.m32.ic.m4c.RewritePara32For4C;

import nc.bs.so.m32.plugin.ServicePlugInPoint;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.so.m32.ic.m4c.rule.RewriteOutNumRule;

/**
 * ���۷�Ʊ�ṩ�����۳��ⵥ�Ļ�д�ӿ�ʵ����
 * 
 * @since 6.3
 * @version 2012-12-21 ����10:37:33
 * @author yaogj
 */
public class Rewrite32For4CImpl implements IRewrite32For4C {

  @Override
  public void rewrite32OutNumFor4C(RewritePara32For4C[] paras)
      throws BusinessException {

    TimeLog.logStart();
    Map<String, RewritePara32For4C> mappara = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(RewritePara32For4C.class.getName(),
        mappara);

    TimeLog.info("�ϲ��������"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleInvoiceViewVO[] views = this.queryViewVO(mappara);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0055")/*@res "��ѯ���۷�Ʊ����"*/);

    AroundProcesser<SaleInvoiceViewVO> processer =
        new AroundProcesser<SaleInvoiceViewVO>(
            ServicePlugInPoint.rewrite32OutNumFor4C);

    this.addRule(processer);

    TimeLog.logStart();
    processer.before(views);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0056")/*@res "д���ݿ�ǰִ��ҵ�����"*/);

    TimeLog.logStart();
    String[] updatekeys = new String[] {
      SaleInvoiceBVO.NSHOULDOUTNUM, SaleInvoiceBVO.NTOTALOUTNUM
    };

    ViewUpdate<SaleInvoiceViewVO> bo = new ViewUpdate<SaleInvoiceViewVO>();
    views = bo.update(views, SaleInvoiceBVO.class, updatekeys);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0057")/*@res "�������ݿ�"*/);

    TimeLog.logStart();
    processer.after(views);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0058")/*@res "д���ݿ��ִ��ҵ�����"*/);

    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(RewritePara32For4C.class.getName());

  }

  /**
   * ����������������ӻ�д����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @author fengjb
   * @time 2010-5-11 ����01:39:43
   */
  private void addRule(AroundProcesser<SaleInvoiceViewVO> processer) {
    IRule<SaleInvoiceViewVO> rule = new RewriteOutNumRule();
    processer.addBeforeRule(rule);
  }

  /**
   * ���������������������۳��ⵥ��д���۷�Ʊʱ����Ƶ��ķ�Ʊ������
   * <p>
   * <b>����˵��</b>
   * 
   * @param mappara
   * @return <p>
   * @author ��ӱ�
   * @time 2010-3-24 ����11:25:31
   */
  private String[] lockBills(Map<String, RewritePara32For4C> mappara) {

    String[] invoicebids = mappara.keySet().toArray(new String[0]);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
            "04006008-0059")/*@res "���۳��ⵥ��д���۷�Ʊ�ۼƳ����������Է�Ʊ�������ʧ��"*/;
    locker.lock(invoicebids, message);
    return invoicebids;

  }

  /**
   * ��������������Ԥ�����д������
   * <p>
   * <b>����˵��</b>
   * 
   * @param paras
   * @return <p>
   * @author ��ӱ�
   * @time 2010-3-24 ����09:38:59
   */
  private Map<String, RewritePara32For4C> prepareParams(
      RewritePara32For4C[] paras) {

    Map<String, RewritePara32For4C> hmpara =
        new HashMap<String, RewritePara32For4C>();

    for (RewritePara32For4C para : paras) {
      hmpara.put(para.getCsaleinvoicebid(), para);
    }
    return hmpara;

  }

  /**
   * ����������������ѯ��ͼVO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param mappara
   * @return <p>
   * @author fengjb
   * @time 2010-5-11 ����01:40:10
   */
  private SaleInvoiceViewVO[] queryViewVO(
      Map<String, RewritePara32For4C> mappara) {

    String[] invoicebids = this.lockBills(mappara);
    ViewQuery<SaleInvoiceViewVO> bo =
        new ViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class);
    bo.setSharedHead(true);

    SaleInvoiceViewVO[] views = bo.query(invoicebids);
    if (views.length != mappara.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
              "04006008-0060")/*@res "���ڲ��������������²�ѯ���۶���"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;

  }

}
