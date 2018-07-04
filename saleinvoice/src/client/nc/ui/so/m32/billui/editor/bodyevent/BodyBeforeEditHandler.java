package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.rule.IsEditableCheckRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>���۷�Ʊ����༭���¼�����
 * </ul>
 * 
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-8-10 ����10:20:22
 */
public class BodyBeforeEditHandler implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  private SaleInvoiceManageModel model;

  /**
   * ���model
   * 
   * @return SaleInvoiceManageModel
   */
  public SaleInvoiceManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    String editkey = e.getKey();
    // add by wangshu6 for 636 2015-01-09 ���۷�Ʊ�޶�֧���޸� 
    // �ж�״̬���������޸�
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    Integer status = keyValue.getHeadIntegerValue(SaleInvoiceHVO.FSTATUSFLAG);
    boolean isEditable = Boolean.FALSE;
    if (BillStatus.AUDITING.equalsValue(status)) {
      // У�鵱ǰ�༭�ֶ��Ƿ���޸ģ�
      isEditable =
          new IsEditableCheckRule().check(e.getBillCardPanel(), e.getRow(),
              e.getKey());
      if (!isEditable) {
        e.setReturnValue(Boolean.FALSE);
      }
    }
    // end 
    
    // Ĭ����Ϊ�ɱ༭
    e.setReturnValue(Boolean.TRUE);
    this.setEditByCombin(e);

    // ����
    if (SaleInvoiceBVO.CMATERIALVID.equals(editkey)) {
      MaterialEditHandler handler = new MaterialEditHandler();
      handler.beforeEdit(e);
    }
    // ˰��
    else if (SaleInvoiceBVO.CTAXCODEID.equals(editkey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.beforeEdit(e);
    }
    // ҵ��λ
    else if (SaleInvoiceBVO.CASTUNITID.equals(editkey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.beforeEdit(e);
    }
    // ������
    else if (SaleInvoiceBVO.VCHANGERATE.equals(editkey)) {
      AstChangeRateEditHandler handler = new AstChangeRateEditHandler();
      handler.beforeEdit(e);
    }
    // ���۵�λ
    else if (SaleInvoiceBVO.CQTUNITID.equals(editkey)) {
      QtUnitEditHandler handler = new QtUnitEditHandler();
      handler.beforeEdit(e);
    }
    // ���ۻ�����
    else if (SaleInvoiceBVO.VQTUNITRATE.equals(editkey)) {
      QtChangeRateHandler handler = new QtChangeRateHandler();
      handler.beforeEdit(e);
    }
    // ��Ʒ
    else if (SaleInvoiceBVO.BLARGESSFLAG.equals(editkey)) {
      LargessFlagEditHandler handler = new LargessFlagEditHandler();
      handler.beforeEdit(e);
    }
    // ���۲���
    else if (SaleInvoiceBVO.CDEPTVID.equals(editkey)) {
      DeptEditHandler handler = new DeptEditHandler();
      handler.beforeEdit(e);
    }
    // ����ҵ��Ա
    else if (SaleInvoiceBVO.CEMPLOYEEID.equals(editkey)) {
      EmployeeEditHandler handler = new EmployeeEditHandler();
      handler.beforeEdit(e);
    }
    // ��˰���
    else if (SaleInvoiceBVO.NCALTAXMNY.equals(editkey)) {
      CalTaxMnyEditHandler handler = new CalTaxMnyEditHandler();
      handler.beforeEdit(e);
    }
    // ˰��
    else if (SaleInvoiceBVO.NTAX.equals(editkey)) {
      NtaxEditHandler handler = new NtaxEditHandler();
      handler.beforeEdit(e);
    }
    // �ջ���ַ
    else if (SaleInvoiceBVO.CRECEIVEADDRID.equals(editkey)) {
      ReceAddrEditHandler handler = new ReceAddrEditHandler();
      handler.beforeEdit(e);
    }

  }

  private void setEditByCombin(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    String csaleinvoicebid =
        keyValue.getBodyStringValue(row, SaleInvoiceBVO.CSALEINVOICEBID);
    if ("isnull".equals(csaleinvoicebid)) {
      e.setReturnValue(false);
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006008_0", "04006008-0153")/*��ĩ�������಻����༭*/);
    }
  }

  /**
   * ����model
   * 
   * @param model
   */
  public void setModel(SaleInvoiceManageModel model) {
    this.model = model;
  }

}
