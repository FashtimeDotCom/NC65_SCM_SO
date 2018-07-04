package nc.ui.so.m38.billui.editor.bodyevent;

import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.util.SOFreeUtil;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.pub.util.BodyEditEventUtil;

/**
 * Ԥ��������༭���¼�
 * 
 * @since 6.0
 * @version 2011-5-24 ����06:12:43
 * @author
 */
public class BodyAfterEditHandler implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  private PreOrderEditor editor;

  /**
   * 
   * 
   * @return gg
   */
  public PreOrderEditor getEditor() {
    return this.editor;
  }

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {

    int[] editrows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    if (null == editrows) {
      return;
    }

    BillCardPanel cardPanel = e.getBillCardPanel();
    boolean istotalshow = cardPanel.getBodyPanel().isTatolRow();
    cardPanel.getBodyPanel().setTotalRowShow(false);

    String editKey = e.getKey();
    // ����
    if (PreOrderBVO.CMATERIALVID.equals(editKey)) {
      MaterialEditHandler handler = new MaterialEditHandler();
      handler.afterEdit(e);
    }// �ͻ�������(V63�¼�)
    else if (PreOrderBVO.CCUSTMATERIALID.equals(editKey)) {
      CustMaterialEditHandler handler = new CustMaterialEditHandler();
      handler.afterEdit(e);
    }
    // ����
    else if (PreOrderBVO.NASTNUM.equals(editKey)) {
      AstNumEditHandler handler = new AstNumEditHandler();
      handler.afterEdit(e);
    }
    // ������
    else if (PreOrderBVO.NNUM.equals(editKey)) {
      NumEditHandler handler = new NumEditHandler();
      handler.afterEdit(e);
    }
    // ���۵�λ����
    else if (PreOrderBVO.NQTUNITNUM.equals(editKey)) {
      QtUnitNumEditHandler handler = new QtUnitNumEditHandler();
      handler.afterEdit(e);
    }
    // ��λ
    else if (PreOrderBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.afterEdit(e);
    }
    // ���ۼ�����λ
    else if (PreOrderBVO.CQTUNITID.equals(editKey)) {
      QtUnitEditHandler handler = new QtUnitEditHandler();
      handler.afterEdit(e);
    }
    // ��Ʒ��־
    else if (PreOrderBVO.BLARGESSFLAG.equals(editKey)) {
      LargessFlagEditHandler handler = new LargessFlagEditHandler();
      handler.afterEdit(e);
    }
    // ���������֯
    else if (PreOrderBVO.CSENDSTOCKORGVID.equals(editKey)) {
      SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
      handler.afterEdit(e);
    }
    // �����ֿ�
    else if (PreOrderBVO.CSENDSTORDOCID.equals(editKey)) {
      SendStordocEditHandler handler = new SendStordocEditHandler();
      handler.afterEdit(e);
    }
    // ���������֯
    else if (PreOrderBVO.CSETTLEORGVID.equals(editKey)) {
      SettleOrgEditHandler handler = new SettleOrgEditHandler();
      handler.afterEdit(e);
    }
    // �ջ�����
    else if (PreOrderBVO.CRECEIVEAREAID.equals(editKey)) {
      ReceiveAreaEditHandler handler = new ReceiveAreaEditHandler();
      handler.afterEdit(e);
    }
    // �۸���
    else if (PreOrderBVO.CPRICEITEMID.equals(editKey)) {
      PriceItemEditHandler handler = new PriceItemEditHandler();
      handler.afterEdit(e);
    }
    // �ջ���ַ
    else if (PreOrderBVO.CRECEIVEADDRID.equals(editKey)) {
      ReceiveAddressEditHandler handler = new ReceiveAddressEditHandler();
      handler.afterEdit(e);
    }
    // �ջ��ͻ�
    else if (PreOrderBVO.CRECEIVECUSTID.equals(editKey)) {
      ReceiveCustEditHandler handler = new ReceiveCustEditHandler();
      handler.afterEdit(e);
    }
    // ��������/����
    else if (PreOrderBVO.CSENDCOUNTRYID.equals(editKey)) {
      SendCountryEditHandler handler = new SendCountryEditHandler();
      handler.afterEdit(e);
    }
    // ��˰����/����
    else if (PreOrderBVO.CTAXCOUNTRYID.equals(editKey)) {
      TaxCountryEditHandler handler = new TaxCountryEditHandler();
      handler.afterEdit(e);
    }
    // �ջ�����/����
    else if (PreOrderBVO.CRECECOUNTRYID.equals(editKey)) {
      ReceiveCountryEditHandler handler = new ReceiveCountryEditHandler();
      handler.afterEdit(e);
    }
    // ˰��
    else if (PreOrderBVO.CTAXCODEID.equals(editKey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.afterEdit(e);
    }
    // ��˰���
    else if (PreOrderBVO.FTAXTYPEFLAG.equals(editKey)) {
      TaxTypeFlagEditHandler handler = new TaxTypeFlagEditHandler();
      handler.afterEdit(e);
    }// ��������(V63�¼�)
    else if (PreOrderBVO.CPRODUCTORID.equals(editKey)) {
      ProductorEditHandler handler = new ProductorEditHandler();
      handler.afterEdit(e);
    }// ��Ӧ��(V63�¼�)
    else if (PreOrderBVO.CVENDORID.equals(editKey)) {
      VendorEditHandler handler = new VendorEditHandler();
      handler.afterEdit(e);
    }
    // ���ɸ�������1-10
    else if (SOFreeUtil.isFreeKey(editKey)) {
      FreeEditHandler handler = new FreeEditHandler();
      handler.afterEdit(e);
    }
    // ���κ�
    else if (PreOrderBVO.VBATCHCODE.equals(editKey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.setEditor(this.editor);
      handler.afterEdit(e);
    }
    // �����ȼ�
    else if (PreOrderBVO.CQUALITYLEVELID.equals(editKey)) {
      QualitylevelEditHandler handler = new QualitylevelEditHandler();
      handler.afterEdit(e);
    }
    else {
      // �༭����ִ���������۽�����(calculator�ڻ���˵�����Ҫ������ֶ�)
      PreOrderCalculator calculator =
          new PreOrderCalculator(e.getBillCardPanel());
      calculator.calculate(editrows, editKey);
    }
    cardPanel.getBodyPanel().setTotalRowShow(istotalshow);
  }

  /**
   * 
   * 
   * @param editor
   */
  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }
}
