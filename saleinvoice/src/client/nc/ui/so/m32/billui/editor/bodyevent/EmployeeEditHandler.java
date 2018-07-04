package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

/**
 * 
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 *  <li>����ҵ��Ա�༭�¼�����
 * </ul> 
 *
 * <p>
 * @version ���汾�� 6.0
 * @since 
 * @author fengjb
 * @time 2010-9-10 ����10:21:32
 */
public class EmployeeEditHandler {

  /**
   * �����������������۷�Ʊ����ҵ��Ա�༭ǰ�¼���
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
    BillItem employeeitem = cardPanel.getBodyItem(SaleInvoiceBVO.CEMPLOYEEID);    
    FilterPsndocRefUtils filter = FilterPsndocRefUtils
        .createFilterPsndocRefUtilsOfSO((UIRefPane) employeeitem.getComponent());
        filter.filterItemRefByOrg(saleorg);
    // ����Զ���λ������
    String pk_dept =
        keyValue.getBodyStringValue(e.getRow(), SaleInvoiceBVO.CDEPTID);
    filter.fixFocusByPK(pk_dept);

  }
}
