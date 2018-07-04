package nc.pubimpl.so.m30arrange.rule;

import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.pubitf.so.m30arrange.Rewrite30ArrangePara;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 
 * @description
 * ���۶������Ż�д���۶���ִ��ǰ�Ĺ�����(before)��
 * @scene
 * <p>
 * <b>�����ۼ�***��������
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
    Map<String, Rewrite30ArrangePara> index =
        (Map<String, Rewrite30ArrangePara>) BSContext.getInstance().getSession(
            Rewrite30ArrangePara.class.getName());

    for (SaleOrderViewVO vo : vos) {
      SaleOrderBVO body = vo.getBody();
      Rewrite30ArrangePara para = index.get(body.getCsaleorderbid());

      // �����ۼư���***����/
      if (POBillType.PrayBill.isEqual(para.getBilltype())) {
        body.setNarrangepoappnum(MathTool.add(body.getNarrangepoappnum(),
            para.getNnum()));

      }
      else if (POBillType.Order.isEqual(para.getBilltype())) {
        body.setNarrangeponum(MathTool.add(body.getNarrangeponum(),
            para.getNnum()));

      }
      else if (TOBillType.TransIn.isEqual(para.getBilltype())) {
        body.setNarrangetoappnum(MathTool.add(body.getNarrangetoappnum(),
            para.getNnum()));

      }
      else if (TOBillType.TransOrder.isEqual(para.getBilltype())) {
        body.setNarrangetoornum(MathTool.add(body.getNarrangetoornum(),
            para.getNnum()));

      }
      else if (SCBillType.Order.isEqual(para.getBilltype())) {
        body.setNarrangescornum(MathTool.add(body.getNarrangescornum(),
            para.getNnum()));

      }
      else if (MMBillType.ProduceOrder.isEqual(para.getBilltype())) {
        body.setNarrangemonum(MathTool.add(body.getNarrangemonum(),
            para.getNnum()));

      }
      else if (MMBillType.LsProduceOrder.isEqual(para.getBilltype())) {
        body.setNarrangemonum(MathTool.add(body.getNarrangemonum(),
            para.getNnum()));

      }
      else if (MMBillType.PlanOrder.isEqual(para.getBilltype())) {
        body.setNtotalplonum(MathTool.add(body.getNtotalplonum(),
            para.getNnum()));

      }
      this.processArrangeFlag(body);
      // ������
      body.setCarrangepersonid(para.getCarrangepersonid());
      // ��������
      body.setTlastarrangetime(TimeUtils.getSrvBaseTime());
    }
  }

  private void processArrangeFlag(SaleOrderBVO body) {
    UFDouble arrangenum = UFDouble.ZERO_DBL;
    // �빺��
    arrangenum = MathTool.add(body.getNarrangepoappnum(), arrangenum);
    // �ɹ�����
    arrangenum = MathTool.add(body.getNarrangeponum(), arrangenum);
    // ��������
    arrangenum = MathTool.add(body.getNarrangetoappnum(), arrangenum);
    // ��������
    arrangenum = MathTool.add(body.getNarrangetoornum(), arrangenum);
    // ί�ⶩ��
    arrangenum = MathTool.add(body.getNarrangescornum(), arrangenum);
    // ��������
    arrangenum = MathTool.add(body.getNarrangemonum(), arrangenum);
    // �ƻ�����
    arrangenum = MathTool.add(body.getNtotalplonum(), arrangenum);
    // ���ں�ͬ
    arrangenum = MathTool.add(body.getNarrangeitcnum(), arrangenum);

    arrangenum = MathTool.abs(arrangenum);

    UFDouble nnum = MathTool.abs(body.getNnum());

    if (MathTool.lessThan(arrangenum, nnum)) {
      body.setBarrangedflag(UFBoolean.FALSE);
    }
    else {
      body.setBarrangedflag(UFBoolean.TRUE);
    }
  }
}
