package nc.pubimpl.so.m30.so.m32.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * ���۷�Ʊ��д���۶���ִ��ǰ�Ĺ�����(before)��
 * @scene
 * <p><b>�����ۼƿ�Ʊ����
 * <p><b>��������������ԭ�����Ƿ�һ��
 * @param
 * ��
 *
 * @since 6.0
 * @version 2010-01-28 ����13:49:07
 * @author ��־ΰ
 */
public class RewriteSetNumRule implements IRule<SaleOrderViewVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {
    Map<String, Rewrite32Para> mParas =
        (Map<String, Rewrite32Para>) BSContext.getInstance().getSession(
            Rewrite32Para.class.getName());

    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite32Para para = mParas.get(body.getCsaleorderbid());
      // �����ۼƿ�Ʊ����
      UFDouble ntotalinvoicenum = body.getNtotalinvoicenum();
      ntotalinvoicenum = MathTool.add(ntotalinvoicenum, para.getNchangenum());
      if (MathTool.isDiffSign(ntotalinvoicenum, body.getNnum())) {
        String message = NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0354")/*�ۼƿ�Ʊ���������������������෴��*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0340", null, new String[]{head.getVbillcode(),body.getCrowno()})/*���۶���{0}��{1}��*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
      body.setNtotalinvoicenum(ntotalinvoicenum);
    }
  }
}
