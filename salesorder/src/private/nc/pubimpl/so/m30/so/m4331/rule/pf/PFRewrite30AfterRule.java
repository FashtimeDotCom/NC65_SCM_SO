package nc.pubimpl.so.m30.so.m4331.rule.pf;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.impl.pubapp.bill.rewrite.BillRewriteObject;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.pub.SORewriteParaForPFUtil;
import nc.pubimpl.so.m30.so.m4331.rule.RewriteSendStateRule;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * �������������̻�д���۶���
 * @scene
 * �������������̻�д���۶����ۼƷ���������ҵ������չ�ܺ����
 * @param
 * ��
 *
 * @since 6.5
 * @version 2015-10-19 ����5:38:07
 * @author zhangby5
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
    SaleOrderViewVO[] rewriteViewVOs =
        SORewriteParaForPFUtil.initRewriteViewVOMap(rewriteObjs[0],
            currentTargetBills);

    if (rewriteViewVOs == null || rewriteViewVOs.length == 0) {
      return;
    }
    
    if (BSContext.getInstance().getSession(Rewrite4331Para.class.getName()) == null) {
      return;
    }

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30SendNumFor4331);
    processer.after(rewriteViewVOs);

    // �������õ��ú�(�������ǰ��rule���ڲ㣬��ֹ��״̬���õ���Ƕ��)
    new RenovateARByBidsEndRule(M30EngrossAction.M30SendReWrite)
        .process(rewriteViewVOs);
    new RewriteSendStateRule().process(rewriteViewVOs);
    BSContext.getInstance().removeSession(Rewrite4331Para.class.getName());
  }

}
