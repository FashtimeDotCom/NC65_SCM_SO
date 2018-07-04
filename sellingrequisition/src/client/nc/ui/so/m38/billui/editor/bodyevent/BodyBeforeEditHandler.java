package nc.ui.so.m38.billui.editor.bodyevent;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * Ԥ��������༭ǰ�¼��ɷ���
 * 
 * @since 6.0
 * @version 2011-6-9 ����09:56:28
 * @author fengjb
 */
public class BodyBeforeEditHandler implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

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
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    // Ĭ��Ϊ�ɱ༭
    e.setReturnValue(true);
    String editKey = e.getKey();
    // ��齻�������Ƿ�Ϊ��
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String trantypeid = keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(trantypeid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006012_0", "04006012-0012")/*@res "����¼�뽻������"*/);
    }
    // �۸���
    else if (PreOrderBVO.CPRICEITEMID.equals(editKey)) {
      PriceItemEditHandler handler = new PriceItemEditHandler();
      handler.beforeEdit(e);
    }
    // �ͻ������루V63�¼ӣ�
    else if (PreOrderBVO.CCUSTMATERIALID.equals(editKey)) {
      CustMaterialEditHandler handler = new CustMaterialEditHandler();
      handler.beforeEdit(e);
    }
    // ����
    if (PreOrderBVO.CMATERIALVID.equals(editKey)) {
      MaterialEditHandler handler = new MaterialEditHandler();
      handler.beforeEdit(e);
    }
    // ҵ��λ
    else if (PreOrderBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.beforeEdit(e);
    }
    // ҵ��λ������
    else if (PreOrderBVO.VCHANGERATE.equals(editKey)) {
      ChangeRateEditHandler handler = new ChangeRateEditHandler();
      handler.beforeEdit(e);
    }
    // ���۵�λ
    else if (PreOrderBVO.CQTUNITID.equals(editKey)) {
      QtUnitEditHandler handler = new QtUnitEditHandler();
      handler.beforeEdit(e);
    }
    // ���۵�λ������
    else if (PreOrderBVO.VQTUNITRATE.equals(editKey)) {
      QtUnitRateEditHandler handler = new QtUnitRateEditHandler();
      handler.beforeEdit(e);
    }
    // ��Ʒ��־
    else if (PreOrderBVO.BLARGESSFLAG.equals(editKey)) {
      LargessFlagEditHandler handler = new LargessFlagEditHandler();
      handler.beforeEdit(e);
    }
    // ��Ʒ�ۿ�
    else if (PreOrderBVO.NITEMDISCOUNTRATE.equals(editKey)) {
      ItemDisRateEditHandler handler = new ItemDisRateEditHandler();
      handler.beforeEdit(e);
    }
    // ���������֯
    else if (PreOrderBVO.CSENDSTOCKORGVID.equals(editKey)) {
      SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
      handler.beforeEdit(e);
    }
    // �����ֿ�
    else if (PreOrderBVO.CSENDSTORDOCID.equals(editKey)) {
      SendStordocEditHandler handler = new SendStordocEditHandler();
      handler.beforeEdit(e);
    }
    // ������֯
    else if (PreOrderBVO.CTRAFFICORGVID.equals(editKey)) {
      TrafficOrgEditHandler handler = new TrafficOrgEditHandler();
      handler.beforeEdit(e);
    }
    // �۱�����
    else if (PreOrderBVO.NEXCHANGERATE.equals(editKey)) {
      ExchangerateEditHandler handler = new ExchangerateEditHandler();
      handler.beforeEdit(e);
    }
    // ȫ���۱�����
    else if (PreOrderBVO.NGLOBALEXCHGRATE.equals(editKey)) {
      GlobalExchgRateEditHandler handler = new GlobalExchgRateEditHandler();
      handler.beforeEdit(e);
    }
    // �����۱�����
    else if (PreOrderBVO.NGROUPEXCHGRATE.equals(editKey)) {
      GroupExchgRateEditHandler handler = new GroupExchgRateEditHandler();
      handler.beforeEdit(e);
    }
    // ��˰���
    else if (PreOrderBVO.NCALTAXMNY.equals(editKey)) {
      CalTaxMnyEditHandler handler = new CalTaxMnyEditHandler();
      handler.beforeEdit(e);
    }
    // ˰��
    else if (PreOrderBVO.CTAXCODEID.equals(editKey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.beforeEdit(e);
    }
    // ���κ�
    else if (PreOrderBVO.VBATCHCODE.equals(editKey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.setEditor(this.editor);
      handler.beforeEdit(e);
    }
    // �ջ���ַ
    else if (PreOrderBVO.CRECEIVEADDRID.equals(editKey)) {
      ReceiveAddressEditHandler handler = new ReceiveAddressEditHandler();
      handler.beforeEdit(e);
    }

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
