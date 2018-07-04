package nc.pubimpl.so.m30arrange.rule;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import nc.pubitf.so.m30arrange.Rewrite30ArrangePara;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 
 * @description
 * ���۶������Ż�д���۶���ִ��ǰ�Ĺ�����(before)��
 * @scene
 * <p>
 * <b>�����ۼư����������ܴ������۶���������
 * <p>
 * <b>��������������ԭ�����Ƿ�һ��
 * @param
 * ��
 *
 * @since 6.0
 * @version 2010-12-13 ����02:41:06
 * @author ��־ΰ
 */
public class RewriteCheckArrangeNumRule implements IRule<SaleOrderViewVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {
    Map<String, Rewrite30ArrangePara> index =
        (Map<String, Rewrite30ArrangePara>) BSContext.getInstance().getSession(
            Rewrite30ArrangePara.class.getName());
    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite30ArrangePara para = index.get(body.getCsaleorderbid());

      /**
       * �ۼư���ί�ⶩ�������� : narrangescornum
       * �ۼư����빺�������� : narrangepoappnum
       * �ۼư��ŵ������������� : narrangetoornum
       * �ۼư��ŵ������������� : narrangetoappnum
       * �ۼư����������������� : narrangemonum
       * �ۼư��Ųɹ����������� : narrangeponum
       * �ۼ����ɼƻ�����������: ntotalplonum
       */
      // �ۼ��ܰ��������� = �ۼư���ί�ⶩ��������
      UFDouble totalArrangeNum = body.getNarrangescornum();
      // �ۼ��ܰ��������� += �ۼư����빺��������
      totalArrangeNum =
          MathTool.add(totalArrangeNum, body.getNarrangepoappnum());
      // �ۼ��ܰ��������� += �ۼư��ŵ�������������
      totalArrangeNum =
          MathTool.add(totalArrangeNum, body.getNarrangetoornum());
      // �ۼ��ܰ��������� += �ۼư��ŵ�������������
      totalArrangeNum =
          MathTool.add(totalArrangeNum, body.getNarrangetoappnum());
      // �ۼ��ܰ��������� += �ۼư�����������������
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNarrangemonum());
      // �ۼ��ܰ��������� += �ۼư��Ųɹ�����������
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNarrangeponum());
      // �ۼ��ܰ��������� += �ۼ����ɼƻ�����������
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNtotalplonum());
      // �ۼ��ܰ��������� += �ۼư������ɽ��ں�ͬ������
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNarrangeitcnum());
      // �ۼ��ܰ��������� += �������ۼ��ܰ���������
      totalArrangeNum = MathTool.add(totalArrangeNum, para.getNnum());
      UFDouble rate = UFDouble.ZERO_DBL;

      rate =
          SOSysParaInitUtil.getSO13(head.getPk_org()) == null ? UFDouble.ZERO_DBL
              : SOSysParaInitUtil.getSO13(head.getPk_org());

      // �ɰ�������
      rate = rate.div(new UFDouble(100));
      UFDouble canrate = MathTool.add(UFDouble.ONE_DBL, rate);
      UFDouble canarrangenum = body.getNnum().multiply(canrate);
      if (MathTool.absCompareTo(totalArrangeNum, canarrangenum) > 0) {
        String message =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0366", null, new String[] {
                  head.getVbillcode(), body.getCrowno()
                })/*�ۼ��ܰ��������� > ���������� : ���۶���({0})��{1}��*/;

        ExceptionUtils.wrappBusinessException(message);
      }
      if (MathTool.isDiffSign(totalArrangeNum, body.getNnum())) {
        String message =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0367")/*�ۼư��������������������������෴��*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0340", null, new String[] {
                  head.getVbillcode(), body.getCrowno()
                })/*���۶���{0}��{1}��*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
    }
  }

}
