package nc.pubimpl.so.m30.ic.m4480.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * Ԥ����д���۶���ִ��ǰ�Ĺ�����(before)��
 * @scene
 * <p><b>����Ƿ���ֶ���
 * <p><b>����Ƿ����� < �ۼƳ������� + Ԥ������(2011-10-14 ��ӱ� ������ У��Ÿ�Ԥ��ȥУ��)
 * @param
 * ��
 *
 * @version 6.0
 * @since 6.0
 * @author ��־ΰ
 * @time 2010-9-20 ����11:24:21
 */
public class RewriteCheckRule implements IRule<SaleOrderViewVO> {

  // private Map<String, Rewrite4480Para> index;

  @Override
  public void process(SaleOrderViewVO[] vos) {
    // this.index =
    // (Map<String, Rewrite4480Para>) BSContext.getInstance().getSession(
    // Rewrite4480Para.class.getName());
    for (SaleOrderViewVO vo : vos) {
      this.checkRedBill(vo);
      // 2011-10-14 ��ӱ� ������ У��Ÿ�Ԥ��ȥУ��
      // this.checkReqrsNumRange(vo);
    }
  }

  private void checkRedBill(SaleOrderViewVO vo) {
    SaleOrderBVO body = vo.getBody();

    if (MathTool.compareTo(body.getNnum(), UFDouble.ZERO_DBL) < 0) {
      String message =
          NCLangResOnserver.getInstance().getStrByID("4006011_0",
              "04006011-0344")/*���� < 0�����ֶ������ܱ�Ԥ��*/;

      ExceptionUtils.wrappBusinessException(message);
    }
  }

  // private void checkReqrsNumRange(SaleOrderViewVO vo) {
  // SaleOrderHVO head = vo.getHead();
  // SaleOrderBVO body = vo.getBody();
  // Rewrite4480Para para = this.index.get(body.getCsaleorderbid());
  //
  // UFDouble icNum =
  // MathTool.add(MathTool.add(body.getNreqrsnum(), para.getNreqrsnum()),
  // body.getNtotaloutnum());
  //
  // if (MathTool.compareTo(body.getNnum(), icNum) < 0) {
  // String message =
  // NCLangResOnserver.getInstance().getStrByID("4006011_0",
  // "04006011-0345")/*���� < �ۼƳ������� + Ԥ������*/;
  // String location =
  // NCLangResOnserver.getInstance().getStrByID("4006011_0",
  // "04006011-0340", null, new String[] {
  // head.getVbillcode(), body.getCrowno()
  // })/*���۶���{0}��{1}��*/;
  //
  // ExceptionUtils.wrappBusinessException(location + message, location);
  // }
  // }
}
