package nc.pubimpl.so.m30.so.m32.rule.pf;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.impl.pubapp.bill.rewrite.BillRewriteObject;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.pub.SORewriteParaForPFUtil;
import nc.pubimpl.so.m30.so.m32.rule.RewriteToleranceCheck;
import nc.pubimpl.so.m30.so.m32.rule.RewriteWastageToleranceCheck;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ���۷�Ʊ���桢ɾ�����̻�д���۶����ۼƿ�Ʊ����ǰҵ������չ��ǰ����
 * 
 * @author zhangby5
 * 
 */
public class PFRewrite30BeforeRule implements IRule<BillRewriteObject> {

  @Override
  public void process(BillRewriteObject[] rewriteObjs) {

    if (rewriteObjs == null || rewriteObjs.length == 0) {
      return;
    }

    SaleOrderVO[] originTargetBills =
        (SaleOrderVO[]) rewriteObjs[0].getOriginTargetBills();

    // ��ʼ����д��ͼVO
    SaleOrderViewVO[] rewriteViewVOs =
        SORewriteParaForPFUtil.initRewriteViewVOMap(rewriteObjs[0],
            originTargetBills);

    if (rewriteViewVOs == null || rewriteViewVOs.length == 0) {
      return;
    }

    // ���û�д������session��
    this.setRewrite32ParaSession(rewriteObjs[0]);

    if (BSContext.getInstance().getSession(Rewrite32Para.class.getName()) == null) {
      return;
    }

    // ���ȼ�鿪Ʊ����
    try {
      AroundProcesser<SaleOrderViewVO> processer =
          new AroundProcesser<SaleOrderViewVO>(
              ServicePlugInPoint.rewrite30NumFor32);

      processer.before(rewriteViewVOs);
      new RewriteToleranceCheck().process(rewriteViewVOs);
      new RewriteWastageToleranceCheck().process(rewriteViewVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void setRewrite32ParaSession(BillRewriteObject rewriteObjs) {
    RewritePara[] srcRewriteParas =
        SORewriteParaForPFUtil.builderRewritePara(rewriteObjs);
    Map<String, Rewrite32Para> rewrite32ParaMap =
        new HashMap<String, Rewrite32Para>();
    for (RewritePara srcRewritePara : srcRewriteParas) {
      String bid = srcRewritePara.getCsrcbid();
      if (PubAppTool.isNull(bid)) {
        continue;
      }
      Rewrite32Para repeatPara = rewrite32ParaMap.get(bid);
      // ���ñ仯��
      UFDouble nnum = UFDouble.ZERO_DBL;
      if (repeatPara != null) {
        nnum =
            MathTool.add(repeatPara.getNchangenum(), srcRewritePara.getNnum());
      }
      else {
        nnum = srcRewritePara.getNnum();
      }

      rewrite32ParaMap.put(bid, new Rewrite32Para(bid, nnum));
    }
    if (rewrite32ParaMap.size() == 0) {
      return;
    }
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(Rewrite32Para.class.getName(),
        rewrite32ParaMap);

  }

}
