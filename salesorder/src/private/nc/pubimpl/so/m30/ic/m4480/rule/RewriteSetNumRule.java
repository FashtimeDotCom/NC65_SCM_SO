package nc.pubimpl.so.m30.ic.m4480.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.ic.m4480.Rewrite4480Para;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * Ԥ����д���۶���ִ��ǰ�Ĺ�����(before)��
 * @scene
 * <p><b>�����ۼ�Ԥ������
 * <p><b>��������������ԭ�����Ƿ�һ��
 * @param
 * ��
 *
 * @since 6.0
 * @version 2011-6-11 ����11:09:35
 * @author ��־ΰ
 */
public class RewriteSetNumRule implements IRule<SaleOrderViewVO> {

  private Map<String, Rewrite4480Para> index;

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {
    this.index =
        (Map<String, Rewrite4480Para>) BSContext.getInstance().getSession(
            Rewrite4480Para.class.getName());
    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite4480Para para = this.index.get(body.getCsaleorderbid());
      // ����Ԥ������
      UFDouble nreqrsnum = body.getNreqrsnum();
      nreqrsnum = MathTool.add(nreqrsnum, para.getNreqrsnum());
      if (MathTool.isDiffSign(nreqrsnum, body.getNnum())) {
        String message = NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0346")/*�ۼ�Ԥ�����������������������෴��*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0340", null, new String[]{head.getVbillcode(),body.getCrowno()})/*���۶���{0}��{1}��*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
      body.setNreqrsnum(nreqrsnum);
    }
  }
}
