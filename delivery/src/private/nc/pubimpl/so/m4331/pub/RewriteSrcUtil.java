package nc.pubimpl.so.m4331.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryViewVO;

import nc.pubitf.so.m30.so.m4331.IRewrite30For4331;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.pubitf.to.m5x.so.m4331.IRewrite5XFor4331;

import nc.bs.framework.common.NCLocator;

/**
 * ��д��Դ����
 * 
 * @since 6.0
 * @version 2011-2-28 ����10:49:25
 * @author ף����
 */
public class RewriteSrcUtil {

  /**
   * �����д���۶�����Ϣ
   */
  List<Rewrite4331Para> saleorderList;

  /**
   * �����д����������Ϣ
   */
  List<nc.pubitf.to.m5x.so.m4331.Rewrite4331Para> tranOrderList;

  private RewriteVOUtil voutil;

  /**
   * 
   * @param util
   */
  public RewriteSrcUtil(RewriteVOUtil util) {
    this.voutil = util;
  }

  /**
   * ��д��Դ����
   * 
   * @param valueMap key ����������id value ������Ҫ��д��Դ���ݵı仯��
   */
  public void rewriteSrc(Map<String, UFDouble> valueMap) {
    // �����д���۶�����Ϣ
    this.saleorderList = new ArrayList<Rewrite4331Para>();
    // �����д����������Ϣ
    this.tranOrderList =
        new ArrayList<nc.pubitf.to.m5x.so.m4331.Rewrite4331Para>();
    DeliveryViewVO[] views = this.voutil.getAllRewriteViewVO();
    for (DeliveryViewVO view : views) {
      String bid = view.getItem().getCdeliverybid();
      UFDouble reValue = valueMap.get(bid);
      if (reValue.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      String srcBilltype = view.getItem().getVsrctype();
      String srcBid = view.getItem().getCsrcbid();
      // �Ƿ�ر����ε���
      UFBoolean bclosesrcflag = view.getItem().getBclosesrcflag();
      if (SOBillType.Order.getCode().equals(srcBilltype)) {
        Rewrite4331Para para =
            new Rewrite4331Para(srcBid, reValue, bclosesrcflag, UFBoolean.TRUE);
        this.saleorderList.add(para);
      }
      else if (TOBillType.TransOrder.getCode().equals(srcBilltype)) {
        nc.pubitf.to.m5x.so.m4331.Rewrite4331Para para =
            new nc.pubitf.to.m5x.so.m4331.Rewrite4331Para(srcBid, reValue,
                bclosesrcflag);
        this.tranOrderList.add(para);
      }
    }
    this.rewriteSaleOrder();
    this.rewriteTranOrder();
  }

  /**
   * ��д���۶���
   */
  private void rewriteSaleOrder() {
    if (this.saleorderList.size() == 0) {
      return;
    }
    Rewrite4331Para[] paras = new Rewrite4331Para[this.saleorderList.size()];
    paras = this.saleorderList.toArray(paras);
    IRewrite30For4331 api =
        NCLocator.getInstance().lookup(IRewrite30For4331.class);
    try {
      api.rewrite30SendNumFor4331(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  /**
   * ��д��������
   */
  private void rewriteTranOrder() {
    if (this.tranOrderList.size() == 0) {
      return;
    }
    int size = this.tranOrderList.size();
    nc.pubitf.to.m5x.so.m4331.Rewrite4331Para[] paras =
        new nc.pubitf.to.m5x.so.m4331.Rewrite4331Para[size];
    paras = this.tranOrderList.toArray(paras);
    IRewrite5XFor4331 api =
        NCLocator.getInstance().lookup(IRewrite5XFor4331.class);
    try {
      api.rewrite5XSendNumFor4331(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }
}
