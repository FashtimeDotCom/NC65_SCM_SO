package nc.pubimpl.so.m30.ic.m4c.rule;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;

/**
 * 
 * @description
 * ���۳����д���۶���ִ��ǰ�Ĺ�����(before)��
 * @scene
 * <p><b>�����г����дʱ����˻���ȫ��������ܻ�������
 * @param
 * ��
 *
 * @since 6.0
 * @version 2010-12-15 ����10:07:18
 * @author ��־ΰ
 */
public class RewriteExchangeOutRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {
    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Integer fretexchange = body.getFretexchange();
      if (!fretexchange.equals(Fretexchange.EXCHANGE.value())) {
        return;
      }
      // �������ͣ��˻����֮����ܻ���������
      UFBoolean bcanchangeout = this.getBcanchangeout(head);
      if (bcanchangeout.booleanValue()) {
        String cexchangesrcretid = body.getCexchangesrcretid();
        ViewQuery<SaleOrderViewVO> bo =
            new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
        bo.setSharedHead(true);

        SaleOrderViewVO[] viewvos = bo.query(new String[] {
          cexchangesrcretid
        });
        if (viewvos.length != 1) {
          String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0178")/*@res "���ֲ�������ˢ�º�����ҵ��"*/;
          ExceptionUtils.wrappBusinessException(message);
        }
        SaleOrderBVO srcbvo = viewvos[0].getBody();
        UFDouble nnum = srcbvo.getNnum();
        UFDouble ntotaloutnum = srcbvo.getNtotaloutnum();
        if (MathTool.compareTo(nnum, ntotaloutnum) != 0) {
          String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0179")/*@res "�˻���ȫ��������ܻ�������"*/;
          ExceptionUtils.wrappBusinessException(message);
        }
      }
    }
  }

  private UFBoolean getBcanchangeout(SaleOrderHVO head) {
    M30TranTypeVO trantype = null;
    try {
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      trantype = service.queryTranTypeVO(head.getCtrantypeid());
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }

    return trantype == null ? UFBoolean.FALSE : trantype.getBcanchangeout();
  }
}