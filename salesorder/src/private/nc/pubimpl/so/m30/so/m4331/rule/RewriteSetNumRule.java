package nc.pubimpl.so.m30.so.m4331.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * ��������д���۶���ִ��ǰ�Ĺ�����(before)��
 * @scene
 * <p><b>�����ۼƷ�������
 * <p><b>��������������ԭ�����Ƿ�һ��
 * @param
 * ��
 *
 * @since 6.0
 * @version 2010-12-13 ����02:41:06
 * @author ��־ΰ
 */
public class RewriteSetNumRule implements IRule<SaleOrderViewVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {
    Map<String, Rewrite4331Para> mParas =
        (Map<String, Rewrite4331Para>) BSContext.getInstance().getSession(
            Rewrite4331Para.class.getName());

    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite4331Para para = mParas.get(body.getCsaleorderbid());

      // ԭ�ۼƷ�������
      UFDouble ntotalsendnum = body.getNtotalsendnum();
      // ���ۼƷ�������
      ntotalsendnum = MathTool.add(ntotalsendnum, para.getNchangenum());
      if (MathTool.isDiffSign(ntotalsendnum, body.getNnum())) {
        String message = NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0388")/*�ۼƷ������������������������෴��*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0340", null, new String[]{head.getVbillcode(),body.getCrowno()})/*���۶���{0}��{1}��*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
      body.setNtotalsendnum(ntotalsendnum);
    }
  }
}
