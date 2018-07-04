package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * Ԥ�������а������۶����������ͱ༭�¼�
 * 
 * @since 6.0
 * @version 2012-3-28 ����03:58:47
 * @author fengjb
 */
public class TrantypeEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    // ���˽�������
    BillItem tranitem = listPanel.getBodyItem(SaleOrderHVO.CTRANTYPEID);
    FilterTransTypeRefUtils refUtil =
        new FilterTransTypeRefUtils(tranitem, pk_org);
    refUtil.filterTranType(new String[] {
      SOBillType.Order.getCode()
    }, null);
  }

  public void afterEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    ListKeyValue keyValue =
        new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String vtrantype = keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    // ƥ��ҵ������
    String userid = AppContext.getInstance().getPkUser();
    String cbiztypeid = null;
    if (!PubAppTool.isNull(vtrantype) && !PubAppTool.isNull(pk_org)) {
      try {
        cbiztypeid =
            PfUtilClient.retBusitypeCanStart(SOBillType.Order.getCode(),
                vtrantype, pk_org, userid);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
    // ����ҵ������
    keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, cbiztypeid);
  }
}
