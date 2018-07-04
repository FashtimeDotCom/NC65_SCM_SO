package nc.ui.so.m32.billui.editor.headevent;

import nc.vo.pub.pfflow01.BillbusinessVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;

import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>���۷�Ʊ�������ͱ༭�¼�����
 * </ul>
 * 
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-9-10 ����10:05:41
 */
public class TrantypeEditHandler {

  /**
   * 
   * ���������������������ͱ༭ǰ�¼������ò���Լ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author fengjb
   * @time 2010-9-10 ����10:06:45
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    CardKeyValue keyValue = new CardKeyValue(e.getBillCardPanel());
    String pk_org = keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    String ctrantypecode =
        keyValue.getHeadStringValue(SaleInvoiceHVO.VTRANTYPECODE);
    String cbiztypeid = keyValue.getHeadStringValue(SaleInvoiceHVO.CBIZTYPEID);
    String srcbilltype =
        keyValue.getBodyStringValue(0, SaleInvoiceBVO.VSRCTYPE);
    BillItem trantypeItem =
        e.getBillCardPanel().getHeadItem(SaleInvoiceHVO.CTRANTYPEID);
    FilterTransTypeRefUtils filter =
        new FilterTransTypeRefUtils(trantypeItem, pk_org);
    filter.filterTranType(new String[] {
      SOBillType.Invoice.getCode()
    });
    // ����������������ָ���������ͣ���ô�������Ͳ��ɱ༭
    if (PubAppTool.isNull(srcbilltype) || PubAppTool.isNull(cbiztypeid)) {
      return;
    }
    BillbusinessVO[] busiVOs =
        PfServiceScmUtil.queryBillDest(srcbilltype, cbiztypeid);
    for (BillbusinessVO busiVO : busiVOs) {
      String businesstranstype = busiVO.getTranstype();
      if (!PubAppTool.isNull(ctrantypecode)
          && !PubAppTool.isNull(businesstranstype)
          && PubAppTool.isEqual(ctrantypecode, businesstranstype)) {
        e.setReturnValue(Boolean.FALSE);
      }
    }
  }
}
