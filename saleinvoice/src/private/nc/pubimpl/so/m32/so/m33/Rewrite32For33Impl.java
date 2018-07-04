package nc.pubimpl.so.m32.so.m33;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

import nc.pubitf.so.m32.so.m33.IRewrite32For33;
import nc.pubitf.so.m32.so.m33.RewritePara32For33;
import nc.pubitf.so.m32.so.m33.RewritePara32For33OnVerify;

import nc.bs.so.m32.plugin.ServicePlugInPoint;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * ���۷�Ʊ�ṩ�����۽���Ľӿ�
 * 
 * @since 6.3
 * @version 2012-12-21 ����10:38:43
 * @author yaogj
 */
public class Rewrite32For33Impl implements IRewrite32For33 {

  @Override
  public void reWriteBalNumMny(RewritePara32For33[] paras)
      throws BusinessException {

    TimeLog.logStart();
    Map<String, RewritePara32For33> mappara = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(RewritePara32For33.class.getName(),
        mappara);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0054")/*@res "�ϲ��������"*/);

    TimeLog.logStart();
    SaleInvoiceViewVO[] views = this.queryViewVO(mappara);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0055")/*@res "��ѯ���۷�Ʊ����"*/);

    AroundProcesser<SaleInvoiceViewVO> processer =
        new AroundProcesser<SaleInvoiceViewVO>(
            ServicePlugInPoint.rewrite32BalFor33);

    // this.addRule(processer);

    TimeLog.logStart();
    processer.before(views);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0056")/*@res "д���ݿ�ǰִ��ҵ�����"*/);

    // vo�����û�дֵ
    for (SaleInvoiceViewVO view : views) {
      RewritePara32For33 para =
          mappara.get(view.getItem().getCsaleinvoicebid());
      UFDouble oldarnum = view.getItem().getNtotalincomenum();
      UFDouble oldmny = view.getItem().getNtotalincomemny();
      UFDouble oldianum = view.getItem().getNtotalcostnum();
      view.getItem().setNtotalincomenum(
          MathTool.add(oldarnum, para.getNtotalIncomeNum()));
      view.getItem().setNtotalincomemny(
          MathTool.add(oldmny, para.getNtotalIncomeMny()));
      view.getItem().setNtotalcostnum(
          MathTool.add(oldianum, para.getNtotalCostNum()));
    }

    TimeLog.logStart();
    String[] updatekeys =
        new String[] {
          SaleInvoiceBVO.NTOTALINCOMENUM, SaleInvoiceBVO.NTOTALINCOMEMNY,
          SaleInvoiceBVO.NTOTALCOSTNUM
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
    BSContext.getInstance().removeSession(RewritePara32For33.class.getName());

  }

  @Override
  public void reWritePaymnyOnVerfy(RewritePara32For33OnVerify[] paras)
      throws BusinessException {
    String sql =
        "update so_saleinvoice_b set " + SaleInvoiceBVO.NTOTALPAYMNY
            + "=isnull(" + SaleInvoiceBVO.NTOTALPAYMNY + ",0)+? where "
            + SaleInvoiceBVO.CSALEINVOICEBID + "=?";
    JavaType[] types = new JavaType[2];
    types[0] = JavaType.UFDouble;
    types[1] = JavaType.String;
    List<List<Object>> datas = new ArrayList<List<Object>>();
    List<String> bids = new ArrayList<String>();
    for (RewritePara32For33OnVerify rewrite : paras) {
      List<Object> data = new ArrayList<Object>();
      data.add(rewrite.getNtotalpaymny());
      data.add(rewrite.getCsaleinvoicebid());
      datas.add(data);
      bids.add(rewrite.getCsaleinvoicebid());
    }
    LockOperator locker = new LockOperator();
    locker.lock(
        bids.toArray(new String[bids.size()]),
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
            "04006008-0125")/*@res "���۽��㵥��д���۷�Ʊ�տ���Է�Ʊ�������ʧ��"*/);
    // "���۽��㵥��д���۷�Ʊ�տ���Է�Ʊ�������ʧ��");
    DataAccessUtils datautils = new DataAccessUtils();
    datautils.update(sql, types, datas);
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
  private String[] lockBills(Map<String, RewritePara32For33> mappara) {

    String[] invoicebids = mappara.keySet().toArray(new String[0]);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
            "04006008-0062")/*@res "���۽��㵥��д���۷�Ʊ�ۼƳ����������Է�Ʊ�������ʧ��"*/;
    locker.lock(invoicebids, message);
    return invoicebids;

  }

  private Map<String, RewritePara32For33> prepareParams(
      RewritePara32For33[] paras) {

    Map<String, RewritePara32For33> hmpara =
        new HashMap<String, RewritePara32For33>();

    for (RewritePara32For33 para : paras) {
      hmpara.put(para.getCsaleinvoicebid(), para);
    }
    return hmpara;

  }

  private SaleInvoiceViewVO[] queryViewVO(
      Map<String, RewritePara32For33> mappara) {

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
