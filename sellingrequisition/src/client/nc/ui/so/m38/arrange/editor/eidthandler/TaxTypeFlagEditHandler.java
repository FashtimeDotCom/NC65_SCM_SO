package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.m38.arrange.pub.M38ArrangeModelCalculator;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class TaxTypeFlagEditHandler {

  public void afterEdit(BillListPanel listPanel, PushBillEvent e) {

    int[] rows = new int[] {
      e.getEditEvent().getRow()
    };
    // ���õ��۽���㷨����˰�ʴ�����
    M38ArrangeModelCalculator calculator =
        new M38ArrangeModelCalculator(listPanel);
    calculator.calculate(rows, SaleOrderBVO.NTAXRATE);
  }
}
