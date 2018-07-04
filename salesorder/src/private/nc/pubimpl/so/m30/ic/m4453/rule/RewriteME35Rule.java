package nc.pubimpl.so.m30.ic.m4453.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m30.rule.rewrite.me.RewriteData;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.me.m35meext.so.IArsubRewriteForSaleOrder;
import nc.pubitf.me.m35meext.so.IRewriteData;
import nc.pubitf.so.m30.ic.m4453.Rewrite4453Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ��д˵����<br>
 * ;�𵥱��桢ɾ��ʱ��д���۶����ۼ�;���������ۼ�ǩ��������
 * ���ʱ��������۳���رյĻ�������Ҫ��д�ͻ����õ���Ʒ�Ҹ����<br>
 * 
 * ����ʱ����<br>
 * ���¶������ۼ�ǩ��������,�ۼ�;�������������

/**
 * 
 * @description
 * ;�𵥱��桢ɾ��ʱ��д���۶����ۼ�;���������ۼ�ǩ������
 * @scene
 * ��д˵����<br>
 * ;�𵥱��桢ɾ��ʱ��д���۶����ۼ�;���������ۼ�ǩ��������
 * ���ʱ��������۳���رյĻ�������Ҫ��д�ͻ����õ���Ʒ�Ҹ����<br>
 * 
 * ����ʱ����<br>
 * ���¶������ۼ�ǩ��������,�ۼ�;�������������
 * @param
 * ��
 * @since 6.3
 * @version 2014-06-30 16:53:29
 * @author ����
 */
public class RewriteME35Rule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {

    Map<String, Rewrite4453Para> map =
        (Map<String, Rewrite4453Para>) BSContext.getInstance().getSession(
            Rewrite4453Para.class.getName());
    IArsubRewriteForSaleOrder rewrite =
        NCLocator.getInstance().lookup(IArsubRewriteForSaleOrder.class);
    List<IRewriteData> datas = new ArrayList<IRewriteData>();
    for (SaleOrderViewVO vo : vos) {
      if (vo.getBody().getBboutendflag().booleanValue()) {
        String salebid = vo.getBody().getCsaleorderbid();
        Rewrite4453Para para = map.get(salebid);
        if (para == null) {
          continue;
        }
        datas.add(this.getRewriteData(vo.getBody(), para));
      }
    }
    try {
      if (datas.size() > 0) {
        rewrite.rewriteSubByWastage(datas.toArray(new IRewriteData[0]));
      }
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappBusinessException(ex.toString());
    }
  }

  private IRewriteData getRewriteData(SaleOrderBVO bvo, Rewrite4453Para para) {
    RewriteData data = new RewriteData();
    data.setSaleorderid(bvo.getCsaleorderid());
    data.setSaleorderbid(bvo.getCsaleorderbid());
    data.setFinanceorg(bvo.getCsettleorgid());
    data.setCcurrencyid(bvo.getCcurrencyid());
    data.setNorigsubmny(bvo.getNorigsubmny());
    if (null != bvo.getBlrgcashflag() && bvo.getBlrgcashflag().booleanValue()) {
      data.setNorigsubmny(bvo.getNorigtaxmny());
    }
    data.setNnum(bvo.getNnum());
    data.setNtotalsignnum(bvo.getNtotalsignnum());
    data.setNtranslossnum(bvo.getNtranslossnum());

    data.setNcurtranslossnum(para.getNtranslossnum());
    data.setNcursignnum(para.getNsignnum());
    data.setNtotalsendnum(bvo.getNtotaloutnum());
    data.setIslrgcash(bvo.getBlrgcashflag().booleanValue());
    data.setIsoutclosed(bvo.getBboutendflag().booleanValue());
    return data;
  }
}
