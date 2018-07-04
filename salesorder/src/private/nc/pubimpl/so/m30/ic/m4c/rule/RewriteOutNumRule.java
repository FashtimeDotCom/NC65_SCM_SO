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
import nc.vo.so.m30.enumeration.Fretexchange;

/**
 * 
 * @description
 * ���۳����д���۶���ִ��ǰ�Ĺ�����(before)��
 * @scene
 * <b>У�飺�ۼƳ�������+�ۼ�δ��������+��ǰ����ʵ��+��ǰ����Ӧ��>=�ۼ��˻���������+�ۼ�;������
 * @param
 * ��
 *
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class RewriteOutNumRule implements IRule<SaleOrderViewVO> {

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

      UFDouble outNum =
          MathTool.add(body.getNtotaloutnum(), body.getNtotalnotoutnum())
              .add(para.getNchangenum()).add(para.getNchangenotoutnum());
      UFDouble rtLossNum =
          MathTool.add(body.getNtotalreturnnum(), body.getNtranslossnum());

      // �˻��в����жϣ��˻�����в������˻�
      if (!Fretexchange.WITHDRAW.equalsValue(body.getFretexchange())
          && MathTool.absCompareTo(outNum, rtLossNum) < 0) {
        String message =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0347")/* �ۼƳ�����������С���ۼ��˻���������+�ۼ�;������ */;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0340", null, new String[] {
                  head.getVbillcode(), body.getCrowno()
                })/* ���۶���{0}��{1}�� */;
        ExceptionUtils.wrappBusinessException(message, location);
      }

      // // �Ƿ����ر�
      // UFBoolean flag = body.getBboutendflag();
      // if (UFBoolean.TRUE.equals(flag)
      // && (UFBoolean.FALSE.equals(para.getBreturnfalg())
      // && MathTool.greaterThan(para.getNchangenum(), UFDouble.ZERO_DBL) ||
      // UFBoolean.TRUE
      // .equals(para.getBreturnfalg())
      // && MathTool.greaterThan(UFDouble.ZERO_DBL, para.getNchangenum()))) {
      // String message =
      // NCLangResOnserver.getInstance().getStrByID("4006011_0",
      // "04006011-0473")/* ���۶����ѳ���رգ�����ǩ�֣� */;
      // String location =
      // NCLangResOnserver.getInstance().getStrByID("4006011_0",
      // "04006011-0340", null, new String[] {
      // head.getVbillcode(), body.getCrowno()
      // })/* ���۶���{0}��{1}�� */;
      // ExceptionUtils.wrappBusinessException(message, location);
      // }

    }
  }
}
