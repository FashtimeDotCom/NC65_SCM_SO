package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.m38.arrange.pub.M38ArrangeModelCalculator;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;

public class OrigCurrencyEditHandler {

  public void afterEdit(BillListPanel listPanel, PushBillEvent e) {
    int row = e.getEditEvent().getRow();
    ListKeyValue keyValue =
        new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    int[] rows = new int[] {
      row
    };
    // 1.����������۱�����
    SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
    exraterule.calcBodyExchangeRates(rows);
    // 2.ȫ�ֱ�λ�һ���
    SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
    if (globalraterule.isGlobalExchgRateChange(SaleOrderHVO.CORIGCURRENCYID)) {
      globalraterule.calcGlobalExchangeRate(rows);
    }

    // 3.���ű�λ�һ���
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    if (groupraterule.isGroupExchgRateChange(SaleOrderHVO.CORIGCURRENCYID)) {
      groupraterule.calcGroupExchangeRate(rows);
    }
    // 4.�������۽������
    M38ArrangeModelCalculator calculator =
        new M38ArrangeModelCalculator(listPanel);
    calculator.calculate(rows, SaleOrderBVO.NEXCHANGERATE);
  }

}
