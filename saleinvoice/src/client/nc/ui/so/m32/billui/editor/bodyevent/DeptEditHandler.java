package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

/**
 * 
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 *  <li>���۲��ű༭�¼�����
 * </ul> 
 *
 * <p>
 * @version ���汾�� 6.0
 * @since 
 * @author fengjb
 * @time 2010-9-10 ����10:21:08
 */
public class DeptEditHandler {

  /**
   * �����������������۷�Ʊ���岿�ű༭ǰ�¼���
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-3-12 ����04:09:10
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // ������֯Ϊ�գ����ɱ༭
    String saleorg =
        keyValue.getBodyStringValue(e.getRow(), SaleInvoiceBVO.CSALEORGID);
    if (PubAppTool.isNull(saleorg)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // ������֯��Ϊ�գ�ֻ�ܲ���������֯�Ĳ��ŵ���
    BillItem deptitem = cardPanel.getBodyItem(SaleInvoiceBVO.CDEPTVID);
    FilterDeptRefUtils filter = FilterDeptRefUtils
        .createFilterDeptRefUtilsOfSO((UIRefPane) deptitem.getComponent());
        filter.filterItemRefByOrg(saleorg);

  }
}
