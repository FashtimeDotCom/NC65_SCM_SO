package nc.ui.so.m33.pub.editor.headevent.after;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowStateChangeEvent;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۽����ֹ������б��ͷ��ѡ������
 * </ul>
 * 
 * @author zhangcheng
 * @time 2010-10-12 ����02:49:02
 */
public class SquareRowSelectStateChangeHandler implements
    IAppEventHandler<ListHeadRowStateChangeEvent> {

  @Override
  public void handleAppEvent(ListHeadRowStateChangeEvent e) {
    this.select(e);
  }

  private void select(ListHeadRowStateChangeEvent e) {
    int srow = e.getRow();
    int erow = e.getEndRow();
    BillListPanel blp = e.getBillListPanel();
    ListPanelValueUtils util = new ListPanelValueUtils(blp);
    BillModel bm = blp.getHeadBillModel();
    int rowcnt = bm.getRowCount();
    for (int row = srow; row <= erow; ++row) {
      Object pk = util.getHeadValueAt(row, SquareOutDetailVO.PROCESSEID);
      // ������Ϊnull����
      if (VOChecker.isEmpty(pk)) {
        continue;
      }
      // ѭ������ÿһ�У��������ѭ��������һ��������ͬ��ѡ��״̬
      for (int i = 0; i < rowcnt; ++i) {
        Object ipk = util.getHeadValueAt(i, SquareOutDetailVO.PROCESSEID);
        // ������Ϊnull����
        if (VOChecker.isEmpty(pk)) {
          continue;
        }
        // ������һ��������ͬ��״̬
        if (pk.equals(ipk)) {
          bm.setRowState(i, e.getRowStaus());
        }
      }
    }
  }

}
