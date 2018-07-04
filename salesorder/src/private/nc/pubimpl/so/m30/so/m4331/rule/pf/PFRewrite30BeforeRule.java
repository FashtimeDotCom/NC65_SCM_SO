package nc.pubimpl.so.m30.so.m4331.rule.pf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.impl.pubapp.bill.rewrite.BillRewriteObject;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.pub.SORewriteParaForPFUtil;
import nc.pubimpl.so.m30.so.m4331.rule.RewritePriceNumRule;
import nc.pubimpl.so.m30.so.m4331.rule.RewriteToleranceCheck;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * �������������̻�д���۶����ۼƷ�������ǰҵ������չ��ǰ����
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
    this.setRewrite4331ParaSession(rewriteObjs);

    if (BSContext.getInstance().getSession(Rewrite4331Para.class.getName()) == null) {
      return;
    }

    try {
      AroundProcesser<SaleOrderViewVO> processer =
          new AroundProcesser<SaleOrderViewVO>(
              ServicePlugInPoint.rewrite30SendNumFor4331);
      processer.before(rewriteViewVOs);
      // ���ȼ�鷢���ݲ�
      new RewriteToleranceCheck().process(rewriteViewVOs);
      // ���ô����۸�� jilu for �㰲��������
      new RewritePriceNumRule().process(rewriteViewVOs);
      // �������õ���ǰ(�������ǰ��rule���ڲ㣬��ֹ��״̬���õ���Ƕ��)
      new RenovateARByBidsBeginRule(M30EngrossAction.M30SendReWrite)
          .process(rewriteViewVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  private void setRewrite4331ParaSession(BillRewriteObject[] rewriteObjs) {

    DeliveryVO[] deliveryVOs =
        (DeliveryVO[]) rewriteObjs[0].getCurrentSrcBills();
    // ��ɾ������ʱ������ƽ̨����Ķ������ԭʼVOΪ�գ���Ҫ������ǰ��ԭʼVO
    if (deliveryVOs == null || deliveryVOs.length == 0) {
      deliveryVOs = (DeliveryVO[]) rewriteObjs[0].getOriginSrcBills();
    }

    RewritePara[] rewriteParas =
        SORewriteParaForPFUtil.builderRewritePara(rewriteObjs[0]);

    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    for (DeliveryVO vo : deliveryVOs) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        map.put(bvo.getCsrcbid(), bvo.getBclosesrcflag());
      }
    }

    int size = rewriteParas.length;
    List<Rewrite4331Para> parasList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      // ����ƽ̨������Դ�����ӱ�IDΪ��Դ�к�
      String bid = rewriteParas[i].getCsrcbid();
      if (PubAppTool.isNull(bid)) {
        continue;
      }
      UFDouble nnum = rewriteParas[i].getNnum();
      UFBoolean closeflag = map.get(bid);
      parasList.add(new Rewrite4331Para(bid, nnum, closeflag, UFBoolean.TRUE));
    }
    if (parasList.size() == 0) {
      return;
    }

    Map<String, Rewrite4331Para> index =
        this.prepareParams(parasList.toArray(new Rewrite4331Para[parasList
            .size()]));
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(Rewrite4331Para.class.getName(), index);
  }

  private Map<String, Rewrite4331Para> prepareParams(Rewrite4331Para[] paras) {
    Map<String, Rewrite4331Para> index = new HashMap<String, Rewrite4331Para>();
    for (Rewrite4331Para para : paras) {
      String key = para.getCsaleorderbid();
      if (index.containsKey(key)) {
        UFDouble num = this.GetNoNullDouble(para.getNchangenum());
        num = num.add(this.GetNoNullDouble(index.get(key).getNchangenum()));
        Rewrite4331Para newpara =
            new Rewrite4331Para(key, num, para.getBclosed(),
                para.getBboutendflag());
        index.remove(key);
        index.put(key, newpara);
        continue;
      }
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

  private UFDouble GetNoNullDouble(UFDouble value) {
    if (value == null) {
      return UFDouble.ZERO_DBL;
    }
    return value;

  }

}
