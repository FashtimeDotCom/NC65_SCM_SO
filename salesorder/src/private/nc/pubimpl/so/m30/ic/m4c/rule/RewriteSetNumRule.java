package nc.pubimpl.so.m30.ic.m4c.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * ���۳����д���۶���ִ��ǰ�Ĺ�����(before)
 * @scene
 * <p><b>�����ۼƳ����������ۼ�Ӧ��Ϊ��������
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
    Map<String, Rewrite4CPara> mParas =
        (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
            Rewrite4CPara.class.getName());

    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite4CPara para = mParas.get(body.getCsaleorderbid());

      // �����ۼ�ʵ������
      UFDouble ntotaloutnum = body.getNtotaloutnum();
      ntotaloutnum = MathTool.add(ntotaloutnum, para.getNchangenum());
      if (MathTool.isDiffSign(ntotaloutnum, body.getNnum())) {
        String message = NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0368")/*�ۼƳ������������������������෴��*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0340", null, new String[]{head.getVbillcode(),body.getCrowno()})/*���۶���{0}��{1}��*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
      body.setNtotaloutnum(ntotaloutnum);

      // �����ۼ�Ӧ��δ��������
      UFDouble ntotalnotoutnum = body.getNtotalnotoutnum();
      ntotalnotoutnum =
          MathTool.add(ntotalnotoutnum, para.getNchangenotoutnum());
      // if (MathTool.isDiffSign(ntotalnotoutnum, body.getNnum())) {
      // String message = "�ۼ�Ӧ��δ�������������������������෴��";
      // String location =
      // "���۶���" + head.getVbillcode() + "��" + body.getCrowno() + "��";
      // ExceptionUtils.wrappBusinessException(message, location);
      // }
      body.setNtotalnotoutnum(ntotalnotoutnum);
    }
  }
}
