package nc.ui.so.pub.util;

import java.util.List;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmpub.ref.FilterPriceTypeRefUtils;

public class BodyEditEventUtil {

  private static BodyEditEventUtil util = new BodyEditEventUtil();

  private BodyEditEventUtil() {
    // ����ģʽ
  }

  public static BodyEditEventUtil getInstance() {
    return BodyEditEventUtil.util;
  }

  public int[] getAfterEditRow(CardBodyAfterEditEvent e) {

    // �ڱ༭���������ק����
    int[] rows = null;
    if (CardBodyAfterEditEvent.BATCHCOPYEND == e.getAfterEditEventState()) {
      List<Integer> listrow = e.getAfterEditIndexList();
      rows = new int[listrow.size()];
      for (int i = 0, iloop = listrow.size(); i < iloop; i++) {
        rows[i] = listrow.get(i).intValue();
      }
    }
    else if (CardBodyAfterEditEvent.NOTBATCHCOPY == e.getAfterEditEventState()) {
      rows = new int[] {
        e.getRow()
      };
    }
    return rows;
  }

  /**
   * ���ݼ�Ŀ����˼۸������
   * 
   * ��֧�ּ�Ŀ��ͼ۸���ڱ�������
   * 
   * @param e ����༭ǰ�¼�
   * @param tariffdef ��Ŀ��pk
   * @param pk_pricetype �۸���pk
   */
  public void filterPricetype(CardBodyBeforeEditEvent e, String tariffdef,
      String pricetype) {
    // ���ݼ�Ŀ����˼۸���
    CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());
    String tariff = util.getBodyStringValue(e.getRow(), tariffdef);
    if (tariff != null) {
      UIRefPane refPane =
          (UIRefPane) util.getBodyItem(pricetype).getComponent();
      FilterPriceTypeRefUtils utils = new FilterPriceTypeRefUtils(refPane);
      utils.filterByTariffDefID(tariff);
    }
  }

}
