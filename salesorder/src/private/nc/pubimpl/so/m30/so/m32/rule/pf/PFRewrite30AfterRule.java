package nc.pubimpl.so.m30.so.m32.rule.pf;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.impl.pubapp.bill.rewrite.BillRewriteObject;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.pub.SORewriteParaForPFUtil;
import nc.pubimpl.so.m30.so.m32.rule.RewriteInvoiceStateRule;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ���۷�Ʊ���桢ɾ�����̻�д���۶����ۼƿ�Ʊ������ҵ������չ�ܺ����
 * 
 * @author zhangby5
 * 
 */
public class PFRewrite30AfterRule implements IRule<BillRewriteObject> {

  @Override
  public void process(BillRewriteObject[] rewriteObjs) {

    if (rewriteObjs == null || rewriteObjs.length == 0) {
      return;
    }

    SaleOrderVO[] currentTargetBills =
        (SaleOrderVO[]) rewriteObjs[0].getCurrentTargetBills();

    // ��ʼ����д��ͼVO
    SaleOrderViewVO[] rewriteViewVOs = SORewriteParaForPFUtil.initRewriteViewVOMap(rewriteObjs[0],
            currentTargetBills);

    if (rewriteViewVOs == null || rewriteViewVOs.length == 0) {
      return;
    }
    
    if (BSContext.getInstance().getSession(Rewrite32Para.class.getName()) == null) {
      return;
    }
    
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30NumFor32);
    
    processer.after(rewriteViewVOs);

    // ִ�к�Ʊ״̬����
    new RewriteInvoiceStateRule().process(rewriteViewVOs);
    BSContext.getInstance().removeSession(Rewrite32Para.class.getName());
  }

}
