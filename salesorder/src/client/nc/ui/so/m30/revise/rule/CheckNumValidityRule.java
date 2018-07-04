package nc.ui.so.m30.revise.rule;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class CheckNumValidityRule {

  public boolean check(BillCardPanel cardPanel, IKeyValue keyValue, int row)
      throws BusinessException {
    UFDouble num = keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
    UFDouble totalsendnum =
        keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NTOTALSENDNUM);
    UFDouble totalinvoicenum =
        keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NTOTALINVOICENUM);
    UFDouble totaloutnum =
        keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NTOTALOUTNUM);

    UFDouble maxTotalNum = UFDouble.ZERO_DBL;
    String maxTotalNumName = null;
    if (MathTool.absCompareTo(totalsendnum, maxTotalNum) > 0) {
      maxTotalNum = totalsendnum;
      maxTotalNumName =
          NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0300")/*�ۼƷ���������!*/;
    }
    if (MathTool.absCompareTo(totalinvoicenum, maxTotalNum) > 0) {
      maxTotalNum = totalinvoicenum;
      maxTotalNumName =
          NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0301")/*�ۼƿ�Ʊ������!*/;
    }
    if (MathTool.absCompareTo(totaloutnum, maxTotalNum) > 0) {
      maxTotalNum = totaloutnum;
      maxTotalNumName =
          NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0302")/*�ۼƳ���������!*/;
    }

    if (MathTool.absCompareTo(num, maxTotalNum) < 0) {

      // fengjb 2012.03.05 UE�淶
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0303", null, new String[] {
            maxTotalNumName
          })/*���۶����޶�������������С��{0}*/);
    }
    return true;
  }
}
