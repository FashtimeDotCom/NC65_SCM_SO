package nc.ui.so.m30.billui.rule;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pubapp.pattern.log.Log;

/**
 * 
 * @since 6.0
 * @version 2010-11-22 ����12:26:57
 * @author �ս���
 */

public class BillCancelLargessApportion {
  public void process(BillCardPanel cardPanel) {
    // TODO ����ȡ����Ʒ�۸��̯
    Log.info(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0258", null, new String[]{String.valueOf(cardPanel.getRowCount())})/*����ȡ����Ʒ�۸��̯{0}*/);
  }

}
