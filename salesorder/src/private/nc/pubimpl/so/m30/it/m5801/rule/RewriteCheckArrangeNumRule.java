package nc.pubimpl.so.m30.it.m5801.rule;

import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import nc.pubitf.so.m30.it.m5801.Rewrite5801Para;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.env.BSContext;

/**
 * ����"����/ֱ�˰����ݲ����"���ɰ���������
 * 
 * @since JCK 6.31
 * @version 2014-03-19 15:29:26
 * @author zhangyfr
 */
public class RewriteCheckArrangeNumRule {

  /**
   * 
   * @param vos
   */
  public void process(SaleOrderViewVO[] vos) {
    @SuppressWarnings("unchecked")
    Map<String, Rewrite5801Para> index =
        (Map<String, Rewrite5801Para>) BSContext.getInstance().getSession(
            Rewrite5801Para.class.getName());
    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite5801Para para = index.get(body.getCsaleorderbid());

      /**
       * �ۼư���ί�ⶩ�������� : narrangescornum
       * �ۼư����빺�������� : narrangepoappnum
       * �ۼư��ŵ������������� : narrangetoornum
       * �ۼư��ŵ������������� : narrangetoappnum
       * �ۼư����������������� : narrangemonum
       * �ۼư��Ųɹ����������� : narrangeponum
       * �ۼ����ɼƻ�����������: ntotalplonum
       * �ۼ����ɽ��ں�ͬ������: narrangeitcnum
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
      // �ۼ��ܰ��������� += �ۼ����ɽ��ں�ͬ������
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNarrangeitcnum());
      // �ۼ��ܰ��������� += �������ۼ��ܰ���������
      totalArrangeNum = MathTool.add(totalArrangeNum, para.getNchangenum());
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
                "04006011-0485");/*���ɵĽ��ں�ͬ�������������۶����ɰ����������ں�ͬ���ܱ���*/

        ExceptionUtils.wrappBusinessException(message);
      }
      // �����Ƿ��Դ�������
      if (!(MathTool.absCompareTo(totalArrangeNum, body.getNnum()) < 0)) {
        body.setBarrangedflag(UFBoolean.TRUE);
        body.setCarrangepersonid(AppContext.getInstance().getPkUser());
        body.setTlastarrangetime(AppContext.getInstance().getServerTime());
      }
      else {
        body.setBarrangedflag(UFBoolean.FALSE);
        body.setCarrangepersonid("~");
        body.setTlastarrangetime(null);
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
