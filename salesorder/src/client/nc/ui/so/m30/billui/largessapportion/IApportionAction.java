package nc.ui.so.m30.billui.largessapportion;

import java.util.List;

import nc.ui.pub.bill.BillCardPanel;

/**
 * ��Ʒ�۸��̯�㷨
 * 
 * @since 6.0
 * @version 2010-12-2 ����09:19:38
 * @author �ս���
 */

public interface IApportionAction {

  void apportion(BillCardPanel cardPanel, List<Integer> rowlist,
      boolean istaxprior);

}
