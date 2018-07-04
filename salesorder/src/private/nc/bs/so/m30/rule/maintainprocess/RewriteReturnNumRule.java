package nc.bs.so.m30.rule.maintainprocess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.opc.opcapi.so.IRewriteExecuteInfo;
import nc.pubitf.so.m30.so.withdraw.Rewrite30Para;
import nc.vo.opc.param.so.RewriteCustomerPOPara;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * @description
 * ���۳����д���۶���ִ�к�Ĺ�����(After)����д�������۱�׼�����ۼ��˻�����
 * @scene
 * ���۶����˻�ʱ��д�������۱�׼�����ۼ��˻�����ǰ
 * @param
 * ��
 * @author ����
 * @since 6.1
 * @time 2010-04-9 ����13:49:07
 */
public class RewriteReturnNumRule implements IRule<SaleOrderViewVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {

    if (!SysInitGroupQuery.isOPCEnabled()) {
      return;
    }
    Map<String, Rewrite30Para> map =
        (Map<String, Rewrite30Para>) BSContext.getInstance().getSession(
            Rewrite30Para.class.getName());
    List<RewriteCustomerPOPara> ListParas =
        new ArrayList<RewriteCustomerPOPara>();
    for (SaleOrderViewVO viewvo : vos) {
      SaleOrderBVO body = viewvo.getBody();
      Rewrite30Para rewritePara = map.get(body.getCsaleorderbid());
      RewriteCustomerPOPara rewritepara =
          new RewriteCustomerPOPara(body.getCsrcbid(), rewritePara.getNnum());
      if (PubAppTool
          .isEqual(body.getVsrctype(), OPCBillType.OPCORDER.getCode())) {
        ListParas.add(rewritepara);
      }
    }
    if (ListParas.size() == 0) {
      return;
    }
    try {
      IRewriteExecuteInfo rewrite =
          NCLocator.getInstance().lookup(IRewriteExecuteInfo.class);
      rewrite.rewriteTotalReturnNum(ListParas
          .toArray(new RewriteCustomerPOPara[ListParas.size()]));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
