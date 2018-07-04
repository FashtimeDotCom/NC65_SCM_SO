package nc.impl.so.m4331.action.assist.rule;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

import nc.pubitf.so.m30.so.m4331.IRewrite30For4331;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.pubitf.to.m5x.so.m4331.IRewrite5XFor4331;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.bill.convertor.BillToViewConvertor;

/**
 * �����ֶ��ر�ʱ ��д��Դ����
 * 
 * @since 6.0
 * @version 2011-2-28 ����03:35:55
 * @author ף����
 */
public class RewriteSrcRule {

  private List<Rewrite4331Para> saleorderList;

  private List<nc.pubitf.to.m5x.so.m4331.Rewrite4331Para> tranorderList;

  /**
   * ��д��Դ����
   * 
   * @param bills
   * @param action �ǹرն��� ���Ǵ򿪶��� ΪY�ǹر� N�Ǵ�
   */
  public void rewriteSrc(DeliveryVO[] bills, UFBoolean isclose) {
    if (null == bills) {
      return;
    }
    IObjectConvert<DeliveryVO, DeliveryViewVO> convert =
        new BillToViewConvertor<DeliveryVO, DeliveryViewVO>(
            DeliveryViewVO.class);
    DeliveryViewVO[] views = convert.convert(bills);
    this.saleorderList = new ArrayList<Rewrite4331Para>();
    this.tranorderList =
        new ArrayList<nc.pubitf.to.m5x.so.m4331.Rewrite4331Para>();
    for (DeliveryViewVO view : views) {
      UFBoolean state = view.getItem().getBoutendflag();
      // ״̬ û�з����仯���� ������д����
      if (state.equals(isclose)) {
        continue;
      }
      this.initRewriteInfo(view, isclose);
    }
    this.rewriteSaleOrder();
    this.rewriteTranOrder();
  }

  private void initRewriteInfo(DeliveryViewVO view, UFBoolean isclose) {
    String srcBid = view.getItem().getCsrcbid();
    UFDouble num = view.getItem().getNnum();
    UFDouble totalOutNum = view.getItem().getNtotaloutnum();

    UFDouble reValue = UFDouble.ZERO_DBL;
    if (!isclose.booleanValue()) {
      // �򿪻�д���εı仯��Ϊ ������-�ۼƳ�������
      reValue = MathTool.sub(num, totalOutNum);
    }
    else {
      // �ر� ��д���εı仯��Ϊ �ۼƳ�������-������
      reValue = MathTool.sub(totalOutNum, num);
    }
    if (reValue.compareTo(UFDouble.ZERO_DBL) == 0) {
      return;
    }
    UFBoolean bclosesrcflag = view.getItem().getBclosesrcflag();
    String srcBilltype = view.getItem().getVsrctype();
    if (SOBillType.Order.getCode().equals(srcBilltype)) {
      Rewrite4331Para para =
          new Rewrite4331Para(srcBid, reValue, bclosesrcflag, isclose);
      this.saleorderList.add(para);
    }
    else if (TOBillType.TransOrder.getCode().equals(srcBilltype)) {
      nc.pubitf.to.m5x.so.m4331.Rewrite4331Para para =
          new nc.pubitf.to.m5x.so.m4331.Rewrite4331Para(srcBid, reValue,
              bclosesrcflag);
      this.tranorderList.add(para);
    }
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
    if (this.tranorderList.size() == 0) {
      return;
    }
    int size = this.tranorderList.size();
    nc.pubitf.to.m5x.so.m4331.Rewrite4331Para[] paras =
        new nc.pubitf.to.m5x.so.m4331.Rewrite4331Para[size];
    paras = this.tranorderList.toArray(paras);
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
