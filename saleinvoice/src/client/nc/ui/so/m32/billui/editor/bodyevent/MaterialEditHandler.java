package nc.ui.so.m32.billui.editor.bodyevent;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.pub.SaleInvoiceKeyRela;
import nc.vo.so.m32.rule.SrcBillInfoRule;
import nc.vo.so.m32.rule.UnitChangeRateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>���ϱ༭�¼�����
 * </ul>
 * 
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-9-10 ����10:22:44
 */
public class MaterialEditHandler {
  /**
   * �����������������ϱ༭���¼���
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����10:50:10
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    // �༭������ҵ��λ�ͱ��۵�λΪ����Ĭ�����۵�λ
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    String materialid =
        keyValue.getBodyStringValue(row, SaleInvoiceBVO.CMATERIALVID);
    Map<String, String> mapunit = null;
    mapunit = MaterialPubService.querySaleMeasdocIDByPks(new String[] {
      materialid
    });

    String defaultunit = null;
    if (null != mapunit) {
      defaultunit = mapunit.get(materialid);
    }

    if (VOChecker.isEmpty(defaultunit)) {
      String unitid = keyValue.getBodyStringValue(row, SaleInvoiceBVO.CUNITID);
      keyValue.setBodyValue(row, SaleInvoiceBVO.CASTUNITID, unitid);
      keyValue.setBodyValue(row, SaleInvoiceBVO.CQTUNITID, unitid);
    }
    else {
      keyValue.setBodyValue(row, SaleInvoiceBVO.CASTUNITID, defaultunit);
      keyValue.setBodyValue(row, SaleInvoiceBVO.CQTUNITID, defaultunit);
    }
    // ���㻻����
    UnitChangeRateRule changeraterule = new UnitChangeRateRule(keyValue);
    changeraterule.calcAstChangeRate(row);
    changeraterule.calcQtChangeRate(row);

    SaleInvoiceKeyRela invoicerela = new SaleInvoiceKeyRela();
    // ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue, invoicerela);
    taxInfo.setTaxInfoByHeadPos(new int[] {
      row
    });
  }

  /**
   * �����������������۷�Ʊ���ϱ༭ǰ�¼���
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @author ��ӱ�
   * @time 2010-3-12 ����03:35:46
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    // �Ƿ�������ε���
    CardKeyValue cardkeyvalue = new CardKeyValue(e.getBillCardPanel());
    SrcBillInfoRule srcbillrule = new SrcBillInfoRule(cardkeyvalue);
    boolean value = srcbillrule.isExitSrc(e.getRow());
    e.setReturnValue(Boolean.valueOf(value));
    
    // ����ֻ�ܲ��շ���/�ۿ�����
    BillItem materialitem =
        e.getBillCardPanel().getBodyItem(SaleInvoiceBVO.CMATERIALVID);
    UIRefPane ref = (UIRefPane) materialitem.getComponent();
    FilterMaterialRefUtils filtermaterial = new FilterMaterialRefUtils(ref);
    filtermaterial.filterIsSealedShow(UFBoolean.FALSE);
    filtermaterial.filterRefByFeeOrDiscount(UFBoolean.TRUE, UFBoolean.TRUE);
  }
}
