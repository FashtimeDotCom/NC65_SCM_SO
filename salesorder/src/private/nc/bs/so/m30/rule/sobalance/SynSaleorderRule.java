/**
 * $�ļ�˵��$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-10 ����08:03:03
 */
package nc.bs.so.m30.rule.sobalance;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.so.balance.IRewrite30ForBalance;
import nc.pubitf.so.m30.so.balance.RewriteBalancePara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * �����տ������ϵ��ͬ����д���۶�����ͷʵ���տ���,ʵ��Ԥ�տ���
 * @scene
 * ���۶��������տ������ϵ��
 * @param 
 * ��
 * @since 6.0
 * @version 2011-5-28 ����03:24:26
 * @author ��־ΰ
 */
public class SynSaleorderRule implements IRule<SoBalanceVO> {

  /**
   * �������������۶�����һ����һ����ֻҪ�Ѷ������տ����Ԥ�տ�����յ����۶����ϼ���
   */
  @Override
  public void process(SoBalanceVO[] vos) {
    int length = vos.length;
    RewriteBalancePara[] paras = new RewriteBalancePara[length];
    for (int i = 0; i < length; i++) {
      String id = vos[i].getParentVO().getCsaleorderid();
      UFDouble totalpaymny = vos[i].getParentVO().getNtotalpaymny();
      UFDouble totalpremny = this.getTotalPreMny(vos[i]);
      paras[i] = new RewriteBalancePara(id, totalpaymny, totalpremny);
    }
    IRewrite30ForBalance api =
        NCLocator.getInstance().lookup(IRewrite30ForBalance.class);
    try {
      api.rewrite30ReceivedMnyForBalance(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private UFDouble getTotalPreMny(SoBalanceVO soBalanceVO) {
    UFDouble ret = UFDouble.ZERO_DBL;
    SoBalanceBVO[] bodys = soBalanceVO.getChildrenVO();
    for (SoBalanceBVO body : bodys) {
      boolean preflag =
          body.getBpreceiveflag() == null ? false : body.getBpreceiveflag()
              .booleanValue();
      if (preflag) {
        ret = MathTool.add(ret, body.getNorigordbalmny());
      }
    }
    return ret;
  }

}
