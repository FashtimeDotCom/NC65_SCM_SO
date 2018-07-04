package nc.ui.so.pub.util;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillTotalListener;
import nc.ui.so.pub.listener.SOSelectedRowTotalListener;

/**
 * ������棨�б���棩�ϼƴ���ֻ���ѡ����н��кϲ�����νѡ�������ָ��һ�д򹳵���Щ
 * 
 * @since 6.36
 * @version 2015-1-16 ����4:08:39
 * @author ��¼
 */
public class SOSelectedRowTotalUtil {

  /**
   * ������棨�б���棩�ϼƴ���ֻ���ѡ����н��кϲ�����νѡ�������ָ��һ�д򹳵���Щ
   * 
   * @param listPanel �б����
   * @param viewClassName ��ͼVO����
   */
  public static void selectedRowTotalProcess(BillListPanel listPanel,
      String viewClassName) {
    listPanel.getChildListPanel().setTotalRowShow(true);
    BillTotalListener totallis =
        new SOSelectedRowTotalListener(listPanel, viewClassName);
    listPanel.getBodyBillModel().addTotalListener(totallis);
  }
}
