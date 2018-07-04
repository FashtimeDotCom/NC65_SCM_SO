package nc.bs.so.m4331.maintain.rule.util;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.so.m30.so.m4331.IRewrite30For4331;
import nc.pubitf.to.m5x.so.m4331.IRewrite5XFor4331;
import nc.pubitf.to.m5x.so.m4331.Rewrite4331Para;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.to.m5x.entity.BillHeaderVO;
import nc.vo.to.m5x.entity.BillItemVO;

/**
 * ��������д������
 * 
 * @since 6.1
 * @version 2013-05-21 20:21:37
 * @author yixl
 */
public class RewriteBillUtil {

  /**
   * ��������������������Դ���ݻ�д�����ࡣ
   * 
   * @author ף����
   * @time 2010-4-28 ����04:09:25
   */
  public BillRewriter getSrcBillRewriter() {
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(DeliveryBVO.VSRCTYPE);
    mapping.setCsrcidKey(DeliveryBVO.CSRCID);
    mapping.setCsrcbidKey(DeliveryBVO.CSRCBID);
    mapping.setNnumKey(DeliveryBVO.NNUM);
    mapping.setSrcTSKey(DeliveryBVO.SRCTS);

    // ��ӷ��������ε�������
    BillRewriter tool = new BillRewriter(mapping);
    // ���۶���������ƽ̨��д add by zhangby5
    //��ʱ��������ƽ̨��д modify by wangzy
     tool.addSRCHeadClazz("30", SaleOrderHVO.class);
     tool.addSRCItemClazz("30", SaleOrderBVO.class);
    // ��������
    tool.addSRCHeadClazz(TOBillType.TransOrder.getCode(), BillHeaderVO.class);
    tool.addSRCItemClazz(TOBillType.TransOrder.getCode(), BillItemVO.class);

    return tool;

  }

  /**
   * ���������������������������޸ġ�ɾ��ʱ��д���۶���
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @author ף����
   * @param paraList
   * @param map
   * @time 2010-1-30 ����02:45:49
   */
  public void reWriteSrc30(List<RewritePara> paraList,
      Map<String, UFBoolean> map) {

    int size = paraList.size();
    nc.pubitf.so.m30.so.m4331.Rewrite4331Para[] paras =
        new nc.pubitf.so.m30.so.m4331.Rewrite4331Para[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      UFBoolean closeflag = map.get(bid);
      paras[i] =
          new nc.pubitf.so.m30.so.m4331.Rewrite4331Para(bid, nnum, closeflag,
              UFBoolean.TRUE);
    }
    this.reWriteSrc30(paras);
  }

  /**
   * ��������������������������ɾ�����޸�ʱ��д��������
   * 
   * @author ף����
   * @param map
   * @time 2010-6-9 ����03:07:09
   */
  public void reWriteSrc5X(List<RewritePara> paraList,
      Map<String, UFBoolean> map) {
    int size = paraList.size();
    Rewrite4331Para[] paras = new Rewrite4331Para[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      UFBoolean closeflag = map.get(bid);
      paras[i] = new Rewrite4331Para(bid, nnum, closeflag);
    }
    this.reWriteSrc5X(paras);
  }

  /*
   * ��д���۶���
   */
  private void reWriteSrc30(nc.pubitf.so.m30.so.m4331.Rewrite4331Para[] paras) {
    IRewrite30For4331 api =
        NCLocator.getInstance().lookup(IRewrite30For4331.class);
    try {
      api.rewrite30SendNumFor4331(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  /*
   * ��д��������
   */
  private void reWriteSrc5X(Rewrite4331Para[] paras) {
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
