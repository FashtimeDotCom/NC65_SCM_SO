package nc.ui.so.pub.editable;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.vo.so.pub.SOItemKey;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 
 * 
 * @since 6.0
 * @version 2011-4-25 ����03:38:00
 * @author ��ӱ�
 */
public class SOCardEditableSetter {
  public static final String[] BODY_MNYKEY = new String[] {
    SOItemKey.NORIGTAXMNY, SOItemKey.NORIGMNY, SOItemKey.NORIGDISCOUNT,
    SOItemKey.NTAXMNY, SOItemKey.NMNY, SOItemKey.NTAX, SOItemKey.NDISCOUNT
  };

  // ���۹������ֶ�
  public static final String[] BODY_PRICEKEY = new String[] {
    // ����λԭ��
    SOItemKey.NORIGTAXPRICE,
    SOItemKey.NORIGPRICE,
    SOItemKey.NORIGTAXNETPRICE,
    SOItemKey.NORIGNETPRICE,
    // ԭ��
    SOItemKey.NQTORIGTAXPRICE, SOItemKey.NQTORIGPRICE,
    SOItemKey.NQTORIGTAXNETPRC,
    SOItemKey.NQTORIGNETPRICE,
    // ����λ����
    SOItemKey.NTAXPRICE, SOItemKey.NPRICE, SOItemKey.NTAXNETPRICE,
    SOItemKey.NNETPRICE,
    // ����
    SOItemKey.NQTTAXPRICE, SOItemKey.NQTPRICE, SOItemKey.NQTTAXNETPRICE,
    SOItemKey.NQTNETPRICE
  };

  public void setBodyPriceMnyEdit(BillCardPanel cardPanle, int row,
      boolean editable) {
    int[] rows = new int[] {
      row
    };
    this.setBodyPriceMnyEdit(cardPanle, rows, editable);
  }

  public void setBodyPriceMnyEdit(BillCardPanel cardPanle, int[] rows,
      boolean editable) {

    for (int row : rows) {
      for (String key : SOCardEditableSetter.BODY_PRICEKEY) {
        BillItem bodyitem = cardPanle.getBodyItem(key);
        cardPanle.setCellEditable(row, key, editable && bodyitem.isEdit());
      }
      for (String key : SOCardEditableSetter.BODY_MNYKEY) {
        BillItem bodyitem = cardPanle.getBodyItem(key);
        cardPanle.setCellEditable(row, key, editable && bodyitem.isEdit());
      }

    }
  }

  /**
   * ������ת�����ε��ݽ��湫���༭��
   * 
   * @param cardPanle
   */
  public void setHeadEditForRef(BillCardPanel cardPanle) {
    // ��������ΪNull���ſ��Ա༭
    Object tranType =
        cardPanle.getHeadItem(SOItemKey.CTRANTYPEID).getValueObject();
    if (VOChecker.isEmpty(tranType)) {
      cardPanle.getHeadItem(SOItemKey.CTRANTYPEID).setEdit(true);
    }
    else {
      cardPanle.getHeadItem(SOItemKey.CTRANTYPEID).setEdit(false);
    }

  }

}
