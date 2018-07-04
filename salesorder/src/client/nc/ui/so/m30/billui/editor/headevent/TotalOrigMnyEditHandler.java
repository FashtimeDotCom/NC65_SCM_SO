package nc.ui.so.m30.billui.editor.headevent;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyUpdateByHead;
import nc.vo.so.pub.rule.BodyValueRowRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * ��ͷ��˰�ϼƱ༭�¼�
 * 
 * @since 6.3
 * @version 2013-03-14 10:16:10
 * @author yixl
 */
public class TotalOrigMnyEditHandler {

  /**
   * �༭���¼�
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    UFDouble newtotalmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);
    // 1.�����������ۿ�
    List<Integer> listchgrow = this.calculateNewDiscountRate(keyValue);

    // 2.���±��������ۿ�
    BodyUpdateByHead updaterule = new BodyUpdateByHead(keyValue);
    updaterule.updateAllBodyValueByHead(SaleOrderBVO.NDISCOUNTRATE,
        SaleOrderHVO.NDISCOUNTRATE);

    // 3.�������۽������
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    BodyValueRowRule couutil = new BodyValueRowRule(keyValue);
    int[] rows = couutil.getMarNotNullRows();
    calculator.calculate(rows, SaleOrderHVO.NDISCOUNTRATE);

    // 4.����β��
    this.processMnyAdjust(newtotalmny, keyValue, listchgrow, calculator);

  }

  private void processMnyAdjust(UFDouble newtotalmny, IKeyValue keyValue,
      List<Integer> listchgrow, SaleOrderCalculator calculator) {
    UFDouble curtotal = UFDouble.ZERO_DBL;
    if (listchgrow.size() > 0) {
      int size = listchgrow.size() - 1;
      for (int i = 0; i < size; i++) {
        UFDouble origmny =
            keyValue.getBodyUFDoubleValue(listchgrow.get(i).intValue(),
                SaleOrderBVO.NORIGTAXMNY);
        curtotal = MathTool.add(curtotal, origmny);
      }
      UFDouble adjustmny = MathTool.sub(newtotalmny, curtotal);
      int lastrow = listchgrow.get(size).intValue();
      keyValue.setBodyValue(lastrow, SaleOrderBVO.NORIGTAXMNY, adjustmny);
      calculator.setChangePrice(UFBoolean.TRUE);
      calculator.calculate(lastrow, SaleOrderBVO.NORIGTAXMNY);
    }
  }

  private List<Integer> calculateNewDiscountRate(IKeyValue keyValue) {
    // ԭ�ۿ۳���Ʒ�ۿۺ�Ľ��=�������������ۺ�˰���ۡ���Ʒ�ۿ�
    UFDouble bodysumdismny = UFDouble.ZERO_DBL;
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();

    List<Integer> listchgrow = new ArrayList<Integer>();
    for (int row : rows) {
      UFBoolean largessflag =
          keyValue.getBodyUFBooleanValue(row, SaleOrderBVO.BLARGESSFLAG);
      if (null != largessflag && largessflag.booleanValue()) {
        continue;
      }
      UFDouble nqtunitnum =
          keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NQTUNITNUM);
      if (null == nqtunitnum) {
        nqtunitnum = UFDouble.ZERO_DBL;
      }

      UFDouble nqtorigtaxprice =
          keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NQTORIGTAXPRICE);
      if (null == nqtorigtaxprice) {
        nqtorigtaxprice = UFDouble.ZERO_DBL;
      }

      UFDouble nitemdiscountrate =
          keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NITEMDISCOUNTRATE);
      if (null == nitemdiscountrate) {
        nitemdiscountrate = UFDouble.ONE_DBL;
      }
      else {
        nitemdiscountrate = nitemdiscountrate.div(SOConstant.ONEHUNDRED);
      }
      UFDouble tempItemDiscountmny =
          nqtunitnum.multiply(nqtorigtaxprice).multiply(nitemdiscountrate);
      bodysumdismny = MathTool.add(bodysumdismny, tempItemDiscountmny);

      listchgrow.add(Integer.valueOf(row));
    }

    // �����ۿ۵ļ��㹫ʽsΪ����ͷ�����ۿ�=��ͷ�޸ĺ��ܽ��/����ԭ�ۿ۳���Ʒ�ۿۺ�Ľ�
    UFDouble ntotalorigmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);
    if (null == ntotalorigmny) {
      ntotalorigmny = UFDouble.ZERO_DBL;
    }

    if (UFDouble.ZERO_DBL.compareTo(bodysumdismny) != 0) {
      UFDouble newdiscountrate = ntotalorigmny.div(bodysumdismny).multiply(100);
      keyValue.setHeadValue(SaleOrderHVO.NDISCOUNTRATE, newdiscountrate);
    }

    return listchgrow;
  }
}
