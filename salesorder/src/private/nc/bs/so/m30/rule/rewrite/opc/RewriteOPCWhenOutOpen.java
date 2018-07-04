package nc.bs.so.m30.rule.rewrite.opc;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.opc.opcapi.so.IRewriteOutStatus;
import nc.vo.opc.param.DocIDPara;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * @description
 * ���۶�������򿪻�д�������۳����״̬
 * @scene
 * ���۶�������򿪺�
 * @param
 * ��
 * @author ����
 * @since 6.1
 * @time 2012-04-11 ����13:49:07
 */
public class RewriteOPCWhenOutOpen implements IRule<SaleOrderViewVO> {
  @Override
  public void process(SaleOrderViewVO[] vos) {
    if (!SysInitGroupQuery.isOPCEnabled()) {
      return;
    }
    List<DocIDPara> listParas = new ArrayList<DocIDPara>();
    for (SaleOrderViewVO viewvo : vos) {
      SaleOrderBVO body = viewvo.getBody();
      DocIDPara rewritepara =
          new DocIDPara(body.getCsrcid(), body.getCsrcbid());
      if (PubAppTool
          .isEqual(body.getVsrctype(), OPCBillType.OPCORDER.getCode())) {
        listParas.add(rewritepara);
      }
    }
    if (listParas.size() == 0) {
      return;
    }
    try {
      IRewriteOutStatus rewrite =
          NCLocator.getInstance().lookup(IRewriteOutStatus.class);
      rewrite.openOut(listParas.toArray(new DocIDPara[listParas.size()]));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

  }
}
