package nc.pubimpl.so.m30.it.m5801.rule;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.pubitf.so.m30.it.m5801.Rewrite5801Para;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���ں�ͬ��д���۶����ۼư��Ž��ں�ͬ����
 * @scene
 * ���ں�ͬ��д���۶����ۼư��Ž��ں�ͬ����ʱ�����ۼư��Ž��ں�ͬ������
 * @param
 * ��
 * @since JCK 6.31
 * @version 2014-03-19 15:40:55
 * @author zhangyfr
 */
public class RewriteSetNumRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {

    @SuppressWarnings("unchecked")
    Map<String, Rewrite5801Para> mParas =
        (Map<String, Rewrite5801Para>) BSContext.getInstance().getSession(
            Rewrite5801Para.class.getName());

    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite5801Para para = mParas.get(body.getCsaleorderbid());

      // ԭ�ۼư��Ž��ں�ͬ����
      UFDouble narrangeitcnum = body.getNarrangeitcnum();
      // ���ۼư��Ž��ں�ͬ����
      narrangeitcnum = MathTool.add(narrangeitcnum, para.getNchangenum());
      if (MathTool.isDiffSign(narrangeitcnum, body.getNnum())) {
        String message =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0484")/*�ۼư��Ž��ں�ͬ���������������������෴��*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0340", null, new String[] {
                  head.getVbillcode(), body.getCrowno()
                })/*���۶���{0}��{1}��*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
      body.setNarrangeitcnum(narrangeitcnum);
    }

  }

}
