package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.editable.SOCardEditableSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ���е����ֶα༭�¼�
 * 
 * @since 6.3
 * @version 2013-8-20 ����03:40:18
 * @author ����
 */
@SuppressWarnings("restriction")
public class AllPricesEditHandle {

  /**
   * ���۽��༭ǰ�¼�
   * 
   * @param e
   * @param billform
   */
  public void beforeEdit(CardBodyBeforeEditEvent e, SaleOrderBillForm billform) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String editkey = e.getKey();
    int row = e.getRow();

    // 1.ȡ�������Ͳ���
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, m30transvo);
    // 2.ѯ�۲���,�ж��Ƿ�ѯ��
    Integer askrule = config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
      return;
    }

    // 3.��edit=�۸�ʱ���ж��Ƿ�ɱ༭
    Boolean bmodifyaskedqt = config.isModifyAskSucess();
    Boolean bmodifyunaskedqt = config.isModifyAskFail();

    // 4.ȡ����ǰ�༭���ֵ���ж��Ƿ�ѯ�۳ɹ�
    // --��ǰ��Ϊ�գ�Ӧ����ѯ��ʧ��
    if (MathTool.isZero(keyValue.getBodyUFDoubleValue(row, editkey))) {
      // ѯ��ʧ���Ƿ���޸�
      if (bmodifyunaskedqt) {
        return;
      }
      setPriceMnyCellEditable(cardPanel, row, false);
    }
    // --��ǰ�Ϊ�գ�Ӧ����ѯ�۳ɹ�
    else {
      // ѯ�۳ɹ��Ƿ�ɸ�
      if (bmodifyaskedqt || bmodifyunaskedqt) {
        return;
      }
      setPriceMnyCellEditable(cardPanel, row, false);
    }
  }

  /**
   * ���õ��۽��༭��
   * 
   * @param cardPanel
   * @param row
   * @param editable
   */
  private void setPriceMnyCellEditable(BillCardPanel cardPanel, int row,
      boolean editable) {
    for (String key : SOCardEditableSetter.BODY_PRICEKEY) {
      cardPanel.setCellEditable(row, key, editable);
    }
    for (String key : SOCardEditableSetter.BODY_MNYKEY) {
      cardPanel.setCellEditable(row, key, editable);
    }
  }
}
